package com.example.josephsibiya.gbalert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class AddGeoActivity extends AppCompatActivity  implements Geofence{

    EditText addGeoName, addRadius, longi, lati;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_geo);

    }

    @Override
    public void createGeofence() {

    }

    @Override
    public void updateGeofence() {

    }

    @Override
    public void deleteGeofence() {

    }
}
