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

public class GeofenceAdapter extends RecyclerView.Adapter<GeofenceAdapter.GeofenceViewHolder>{

    public ArrayList<GeofenceLocations> locationsArrayList = new ArrayList<>();


    public GeofenceAdapter(ArrayList<GeofenceLocations> locationsArrayList) {
        this.locationsArrayList = locationsArrayList;
    }

    @Override
    public GeofenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geofence_cardview, parent, false);

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


    static class GeofenceViewHolder extends RecyclerView.ViewHolder
    {

        private TextView name;
        private TextView longitude;
        private TextView latitude;

        public GeofenceViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.geoName);
            longitude = itemView.findViewById(R.id.geoLongi);
            latitude = itemView.findViewById(R.id.geoLat);

        }

        public void bind(GeofenceLocations locations) {
            name.setText("Name: " + locations.getName());
            longitude.setText("Longitude: " +  String.valueOf(locations.getLatitude()));
            latitude.setText("Latitude: " +  String.valueOf(locations.getLongitude()));
        }
    }
}
