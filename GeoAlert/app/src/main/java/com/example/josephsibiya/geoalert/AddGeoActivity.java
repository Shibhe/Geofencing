package com.example.josephsibiya.geoalert;

import android.content.DialogInterface;
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
    private EditText name, longitude, latitude, radius;
    private Button add_geofence;
   // private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_geo);

        name = (EditText) findViewById(R.id.geoName);
        longitude = (EditText) findViewById(R.id.geoLongi);
        latitude = (EditText) findViewById(R.id.geoLat);
        radius = (EditText) findViewById(R.id.geoRadius);

        add_geofence = (Button) findViewById(R.id.addGeo);
        //Initialize database referrence
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        add_geofence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitGeofence();
            }
        });
    }

     private void SubmitGeofence()
     {
         String type = "AddGeofence";
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

         new AddData(AddGeoActivity.this).execute(type, geoName, geoLat,geoLongi,geoRadius);
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
