package com.example.josephsibiya.geoalert.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.josephsibiya.geoalert.R;
import com.example.josephsibiya.geoalert.UpdateGeofenceActivity;
import com.example.josephsibiya.geoalert.UpdateLecturerActivity;
import com.example.josephsibiya.geoalert.models.GeofenceLocations;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.button1;

/**
 * Created by josephsibiya on 2017/09/12.
 */

public class GeofenceAdapter extends RecyclerView.Adapter<GeofenceAdapter.GeofenceViewHolder> implements View.OnLongClickListener{

    public ArrayList<GeofenceLocations> locationsArrayList;
    private Context context;


    public GeofenceAdapter(ArrayList<GeofenceLocations> locationsArrayList, Context context) {
        this.locationsArrayList = locationsArrayList;
        this.context = context;
    }

    @Override
    public GeofenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.geofence_cardview, parent, false);

        view.setOnLongClickListener(this);
        return new GeofenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GeofenceViewHolder holder, int position)
    {
        GeofenceLocations locations = locationsArrayList.get(position);

        holder.bind(locations);
    }

    @Override
    public int getItemCount() {
        return locationsArrayList.size();
    }

    public void setFilter(List<GeofenceLocations> LocaModels) {
        locationsArrayList = new ArrayList<>();
        locationsArrayList.addAll(LocaModels);
        notifyDataSetChanged();
    }

    @Override
    public boolean onLongClick(View view) {

        PopupMenu popup = new PopupMenu(context, view);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.update, popup.getMenu());
        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                int id = item.getItemId();

                switch (id){
                    case R.id.updat:
                        Intent intent = new Intent(context, UpdateGeofenceActivity.class);
                        context.startActivity(intent);
                }
                return false;
            }
    });
        return false;
    }


    static class GeofenceViewHolder extends RecyclerView.ViewHolder
    {

        private TextView name;
        private TextView longitude;
        private TextView latitude;
       // private TextView radius;

        public GeofenceViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.geoName);
            longitude = itemView.findViewById(R.id.geoLongi);
            latitude = itemView.findViewById(R.id.geoLat);

        }

        public void bind(GeofenceLocations locations) {
            name.setText("Name: " + locations.getName());
            longitude.setText("Longitude: " +  String.valueOf(locations.getLatitude()));
            longitude.setText("Latitude: " +  String.valueOf(locations.getLongitude()));
        }
    }
}
