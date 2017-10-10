package com.example.josephsibiya.geoalert.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.josephsibiya.geoalert.R;
import com.example.josephsibiya.geoalert.models.GeofenceLocations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josephsibiya on 2017/09/12.
 */

public class GeofenceAdapter extends RecyclerView.Adapter<GeofenceAdapter.GeofenceViewHolder>{

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
