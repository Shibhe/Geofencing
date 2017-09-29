package com.example.josephsibiya.geoalert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.josephsibiya.geoalert.Adapters.GeofenceAdapter;
import com.example.josephsibiya.geoalert.models.GeofenceLocations;

import java.util.ArrayList;

public class GeofenceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GeofenceAdapter geofenceAdapter;
    private ArrayList<GeofenceLocations> geofenceLocationsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofence);


        recyclerView = (RecyclerView) findViewById(R.id.rvGeofence);
        geofenceLocationsArrayList = new ArrayList<>();
        geofenceAdapter = new GeofenceAdapter(geofenceLocationsArrayList, GeofenceActivity.this);
        recyclerView.setAdapter(geofenceAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(CreateHelperCallBack());
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private ItemTouchHelper.Callback CreateHelperCallBack()
    {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
                ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteItem(viewHolder.getAdapterPosition());

            }
        };
        return simpleCallback;
    }

    private void deleteItem(int pos)
    {
        geofenceAdapter.locationsArrayList.remove(pos);
        geofenceAdapter.notifyDataSetChanged();
    }


    private void moveItem(int pos, int newPos)
    {
        GeofenceLocations geofenceLocations = geofenceAdapter.locationsArrayList.get(pos);
        geofenceAdapter.locationsArrayList.remove(pos);
        geofenceAdapter.locationsArrayList.add(newPos, geofenceLocations);
        geofenceAdapter.notifyItemMoved(pos, newPos);
    }

}
