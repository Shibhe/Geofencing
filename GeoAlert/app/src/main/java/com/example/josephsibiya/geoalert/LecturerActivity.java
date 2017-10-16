package com.example.josephsibiya.geoalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.josephsibiya.geoalert.Adapters.LecturerAdapter;
import com.example.josephsibiya.geoalert.models.LecturerModel;

import java.util.ArrayList;

public class LecturerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LecturerAdapter geofenceAdapter;
    private ArrayList<LecturerModel> geofenceLocationsArrayList = new ArrayList<>();
    private Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);

        update = (Button) findViewById(R.id.updateLect);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LecturerActivity.this, UpdateLecturerActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.rvLect);
        geofenceLocationsArrayList = new ArrayList<>();



        geofenceAdapter = new LecturerAdapter(geofenceLocationsArrayList, LecturerActivity.this);
        recyclerView.setAdapter(geofenceAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
