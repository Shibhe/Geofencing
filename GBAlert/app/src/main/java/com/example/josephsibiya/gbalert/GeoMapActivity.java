package com.example.josephsibiya.gbalert;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GeoMapActivity extends FragmentActivity
        implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener,
        LocationListener,
        ResultCallback<Status> {

    private static final long GEO_DURATION = 60 * 60 * 1000;
    private static final String GEOFENCE_REQ_ID = "My Geofence";
    private static final float GEOFENCE_RADIUS = 500.0f; // in meters
    private final int GEOFENCE_REQ_CODE = 0;

    private GoogleMap map;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private MapFragment mapFragment;
    private static final String TAG = GeoMapActivity.class.getSimpleName();
    private static final String Notification_Msg = "Notify me";

    public static Intent makeNotificationIntent(Context context, String msg) {
        Intent intent = new Intent(context, GeoMapActivity.class);
        intent.putExtra(Notification_Msg, msg);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // initialize GoogleMaps
        initGMaps();
        // create GoogleApiClient
        createGoogleApi();
    }

    private void createGoogleApi() {
        Log.d(TAG, "createGoogleApi()");

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(GeoMapActivity.this)
                    .addApi(LocationServices.API)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        googleApiClient.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.geofence, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.geofence:

                startGeofence();
                return true;

            case R.id.clear:
                clearGeofence();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private final int REQ_PERMISSION = 101;

    private boolean checkPermission() {
        Log.d(TAG, "checkPermission()");
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)) == PackageManager.PERMISSION_GRANTED;
    }

    private void askPermission() {
        Log.d(TAG, "askPermission()");
        ActivityCompat.requestPermissions(this, new String[]
                {
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, REQ_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQ_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastKnownLocation();
                } else {
                    permissionDenied();
                }
                break;
            }
        }
    }

    public void permissionDenied() {
        Log.d(TAG, "permissionDenied()");
    }

    public void initGMaps() {
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // Callback called when Map is ready
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady()");
        map = googleMap;
        map.setOnMapClickListener(this);
        map.setOnMarkerClickListener(this);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.d(TAG, "onMapClick(" + latLng + ")");
        markerForGeofence(latLng);
    }

    public boolean onMarkerClick(Marker marker) {
        Log.d(TAG, "onMarkerClickListener" + marker.getPosition());
        return false;
    }

    private LocationRequest locationRequest;

    private final int UPDATE_INTERVAL = 1000;
    private final int FASTEST_INTERVAL = 1000;

    private void startLocationUpdates() {
        Log.d(TAG, "startLocationUpdates()");

        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        if (checkPermission()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged [" + location + "]");
        lastLocation = location;
        writeActualLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG,"onStatusChanged()");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG,"onProviderEnabled()");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG,"onProviderDisabled()");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected()");
        getLastKnownLocation();
        recoverGeofenceMarker();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "onConnectionSuspended()");
    }


    // GoogleApiClient.OnConnectionFailedListener fail
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed()");
    }

    // Get last known location
    private void getLastKnownLocation() {
        Log.d(TAG, "getLastKnownLocation()");
        if (checkPermission()) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (lastLocation != null) {
                Log.i(TAG, "LasKnown location. " +
                        "Long: " + lastLocation.getLongitude() +
                        " | Lat: " + lastLocation.getLatitude());
                writeLastLocation();
                startLocationUpdates();
            } else {
                Log.w(TAG, "No location retrieved yet");
                startLocationUpdates();
            }
        } else askPermission();
    }

    // Write location coordinates on UI
    private void writeActualLocation(Location location) {
        //textLat.setText("Lat: " + location.getLatitude());
        //textLong.setText("Long: " + location.getLongitude());
        //markerLocation(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    private void writeLastLocation() {
        writeActualLocation(lastLocation);
    }

    private Marker locationMarker;

    // Create a Location Marker
    private void markerLocation(LatLng latLng) {
        Log.i(TAG, "markerLocation(" + latLng + ")");
        String title = latLng.latitude + ", " + latLng.longitude;
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(title);
        if (map != null) {
            // Remove the anterior marker
            if (locationMarker != null)
                locationMarker.remove();
            locationMarker = map.addMarker(markerOptions);
            float zoom = 14f;
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
            map.animateCamera(cameraUpdate);
        }
    }


    private Marker geoFenceMarker;

    // Create a marker for the geofence creation
    private void markerForGeofence(LatLng latLng) {
        Log.i(TAG, "markerForGeofence(" + latLng + ")");
        String title = latLng.latitude + ", " + latLng.longitude;
        // Define marker options
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                .title(title);
        if (map != null) {
            // Remove last geoFenceMarker
            if (geoFenceMarker != null)
                geoFenceMarker.remove();

            geoFenceMarker = map.addMarker(markerOptions);
        }
    }

    // Create a Geofence
    private Geofence createGeofence(LatLng latLng, float radius) {
        Log.d(TAG, "createGeofence");
        return new Geofence.Builder()
                .setRequestId(GEOFENCE_REQ_ID)
                .setCircularRegion(latLng.latitude, latLng.longitude, radius)
                .setExpirationDuration(GEO_DURATION)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER
                        | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();
    }

    // Create a Geofence Request
    private GeofencingRequest createGeofenceRequest(Geofence geofence) {
        Log.d(TAG, "createGeofenceRequest");
        return new GeofencingRequest.Builder()
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .addGeofence(geofence)
                .build();
    }


    private PendingIntent geoFencePendingIntent;

    private PendingIntent createGeofencePendingIntent() {
        Log.d(TAG, "createGeofencePendingIntent");
        if (geoFencePendingIntent != null)
            return geoFencePendingIntent;

        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        return PendingIntent.getService(
                this, GEOFENCE_REQ_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // Add the created GeofenceRequest to the device's monitoring list
    private void addGeofence(GeofencingRequest request) {
        Log.d(TAG, "addGeofence");
        if (checkPermission())
            LocationServices.GeofencingApi.addGeofences(
                    googleApiClient,
                    request,
                    createGeofencePendingIntent()
            ).setResultCallback(this);
    }


    @Override
    public void onResult(@NonNull Status status) {
        Log.i(TAG, "onResult: " + status);
        if (status.isSuccess()) {
            saveGeofence();
            drawGeofence();

        } else {
            // inform about fail
        }
    }

    // Draw Geofence circle on GoogleMap
    private Circle geoFenceLimits;

    private void drawGeofence() {
        Log.d(TAG, "drawGeofence()");

        if (geoFenceLimits != null)
            geoFenceLimits.remove();

        CircleOptions circleOptions = new CircleOptions()
                .center(geoFenceMarker.getPosition())
                .strokeColor(Color.argb(50, 70, 70, 70))
                .fillColor(Color.argb(100, 150, 150, 150))
                .radius(GEOFENCE_RADIUS);
        geoFenceLimits = map.addCircle(circleOptions);
    }


    // Start Geofence creation process
    private void startGeofence() {
        Log.i(TAG, "startGeofence()");
        if (geoFenceMarker != null) {
            Geofence geofence = createGeofence(geoFenceMarker.getPosition(), GEOFENCE_RADIUS);
            GeofencingRequest geofenceRequest = createGeofenceRequest(geofence);
            addGeofence(geofenceRequest);
        } else {
            Log.e(TAG, "Geofence marker is null");
        }
    }

    private final  String KEY_GEOFENCE_LAT = "Geofence Lat";
    private final  String KEY_GEOFENCE_LON = "Geofence Long";

    private void saveGeofence()
    {
        Log.d(TAG, "saveGeofence");

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong(KEY_GEOFENCE_LAT, Double.doubleToLongBits(geoFenceMarker.getPosition().latitude));
        editor.putLong(KEY_GEOFENCE_LON, Double.doubleToLongBits(geoFenceMarker.getPosition().longitude));
        editor.apply();
    }

    private  void recoverGeofenceMarker()
    {
        Log.d(TAG, "recoverGeofenceMarker()");

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        if (sharedPreferences.contains(KEY_GEOFENCE_LAT) && sharedPreferences.contains(KEY_GEOFENCE_LON))
        {
            double lat = Double.longBitsToDouble(sharedPreferences.getLong(KEY_GEOFENCE_LAT, -1));
            double lon = Double.longBitsToDouble(sharedPreferences.getLong(KEY_GEOFENCE_LON, -1));

            LatLng latLng = new LatLng(lat, lon);
            markerForGeofence(latLng);
            drawGeofence();
        }
    }


    private void clearGeofence() {
        Log.d(TAG, "clearGeofence()");
        LocationServices.GeofencingApi.removeGeofences(googleApiClient, createGeofencePendingIntent()).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess())
                {
                    removeGeofenceDraw();
                }
            }
        });
    }
    private void removeGeofenceDraw()
    {
        Log.d(TAG, "removeGeofenceDraw()");
        if (geoFenceMarker == null)
        {
            geoFenceMarker.remove();
        }
        if (geoFenceLimits == null)
        {
            geoFenceLimits.remove();
        }
    }


}