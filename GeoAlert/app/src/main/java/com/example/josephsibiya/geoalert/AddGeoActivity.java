package com.example.josephsibiya.geoalert;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.josephsibiya.geoalert.models.GeofenceLocations;
import com.example.josephsibiya.geoalert.services.AddData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddGeoActivity extends AppCompatActivity {

   // private SeekBar seekBar;
    private EditText name, longitude, latitude;
    private Button btn_Add_Geofence;
    private String regexStr = "^[0-9]*$";
   // private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_geo);

        name = (EditText) findViewById(R.id.Geoname);
        longitude = (EditText) findViewById(R.id.longi);
        latitude = (EditText) findViewById(R.id.lati);
        //radius = (EditText) findViewById(R.id.rad);

        btn_Add_Geofence = (Button) findViewById(R.id.addGeo);
        //Initialize database referrence
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        btn_Add_Geofence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                name.setError(null);
                longitude.setError(null);
                latitude.setError(null);
                //radius.setError(null);

                if (name.length() == 0){
                    Toast.makeText(AddGeoActivity.this, "Field must not be empty", Toast.LENGTH_SHORT).show();
                    view = name;
                }

                if (longitude.length() == 0 && !longitude.getText().toString().matches(regexStr)){
                    //longitude.setError( "Field must not be empty and must be a number" );
                    Toast.makeText(AddGeoActivity.this, "Field must not be empty and must be a number", Toast.LENGTH_SHORT).show();
                    view = longitude;
                }

                if (latitude.length() == 0 && !latitude.getText().toString().matches(regexStr)){
                    Toast.makeText(AddGeoActivity.this, "Field must not be empty and must be a number", Toast.LENGTH_SHORT).show();
                    view = latitude;
                }

                /**if (radius.length() == 0 && !radius.getText().toString().matches(regexStr)){
                    Toast.makeText(AddGeoActivity.this, "Field must not be empty and must be a number", Toast.LENGTH_SHORT).show();
                    view = radius;
                }**/
                else {
                    SubmitGeofence();
                    Toast.makeText(AddGeoActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();

                    name.setText(null);
                    longitude.setText(null);
                    latitude.setText(null);
                    name.isFocused();
                   // radius.setText(null);
                }
            }
            });
        }

     private void SubmitGeofence()
     {
         final String type = "AddGeofence";
         final String geoName = name.getText().toString();
         final  String geoLat = latitude.getText().toString();
         final  String geoLongi = longitude.getText().toString();
          //String geoRadius = radius.getText().toString();

         new Thread(new Runnable() {
             public void run() {
                 // a potentially  time consuming task
                 new AddData(AddGeoActivity.this).execute(type, geoName, geoLat,geoLongi);
             }
         }).start();


     }
   /** private void SubmitGeofence()
    {
        final String geoName = name.getText().toString();
        final String geoLat = latitude.getText().toString();
        final String geoLongi = longitude.getText().toString();
        final String geoRadius = radius.getText().toString();

        //geoLatitude Requires
        if (TextUtils.isEmpty(geoLat))
        {
            latitude.setError("Field Required baba");
        }

        if (TextUtils.isEmpty(geoLongi))
        {
            latitude.setError("Field Required baba");
        }

        if (TextUtils.isEmpty(geoRadius))
        {
            latitude.setError("Field Required baba");
        }

        setEditingEnabled(false);

        mDatabase.child("geofence").child("Id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GeofenceLocations geofenceLocations = dataSnapshot.getValue(GeofenceLocations.class);


                if (geofenceLocations == null){

                    Toast.makeText(AddGeoActivity.this, "Error: Could not fetch data ", Toast.LENGTH_SHORT).show();
                }

                else{
                    writeNewGeofence( 1,  geoName ,Double.parseDouble(geoLat) , Double.parseDouble(geoLongi), Double.parseDouble(geoRadius));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private  void setEditingEnabled(boolean enabled)
    {
        name.setEnabled(enabled);
        latitude.setEnabled(enabled);
        longitude.setEnabled(enabled);
        radius.setEnabled(enabled);

        if (enabled){
            add_geofence.setVisibility(View.VISIBLE);
        }
        else{
            add_geofence.setVisibility(View.GONE);
        }
    }

    private void  writeNewGeofence(int Id, String name, Double lat, Double lon, Double radius ){
        //String key = mDatabase.child("geofence").push().getKey();

        GeofenceLocations geofenceLocations = new GeofenceLocations(Id, name, lat, lon, radius);

        String id = String.valueOf(Id);
        mDatabase.child("geofence").child(id).setValue(geofenceLocations);
    }**/
}
