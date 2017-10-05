package com.example.josephsibiya.geoalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.example.josephsibiya.geoalert.Adapters.GeofenceAdapter;
import com.example.josephsibiya.geoalert.models.GeofenceLocations;

import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

public class GeofenceActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GeofenceAdapter geofenceAdapter;
    private ArrayList<GeofenceLocations> geofenceLocationsArrayList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button addGeofence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geofence);

        addGeofence = (Button) findViewById(R.id.addGeofence);

        recyclerView = (RecyclerView) findViewById(R.id.rvGeofence);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);

        recyclerView = (RecyclerView) findViewById(R.id.rvGeofence);
        geofenceLocationsArrayList = new ArrayList<>();
        geofenceAdapter = new GeofenceAdapter(geofenceLocationsArrayList, GeofenceActivity.this);
        recyclerView.setAdapter(geofenceAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);


        /**swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);

                geofenceLocationsArrayList = new ArrayList<>();
                geofenceAdapter = new GeofenceAdapter(geofenceLocationsArrayList, GeofenceActivity.this);
                recyclerView.setAdapter(geofenceAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(GeofenceActivity.this));


                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(CreateHelperCallBack());
                itemTouchHelper.attachToRecyclerView(recyclerView);
            }
        });**/

        addGeofence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeofenceActivity.this, GeoMapsActivity.class);
                startActivity(intent);
            }
        });

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
