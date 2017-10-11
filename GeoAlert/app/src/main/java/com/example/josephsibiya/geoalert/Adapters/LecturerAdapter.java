package com.example.josephsibiya.geoalert.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.josephsibiya.geoalert.R;
import com.example.josephsibiya.geoalert.models.GeofenceLocations;
import com.example.josephsibiya.geoalert.models.LecturerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reversidesoftwaresolutions on 10/10/17.
 */

public class LecturerAdapter extends RecyclerView.Adapter<LecturerAdapter.LecturerViewHolder>{

    public ArrayList<LecturerModel> lecturerModels;
    private Context context;


    public LecturerAdapter(ArrayList<LecturerModel> lecturerModels, Context context) {
        this.lecturerModels = lecturerModels;
        this.context = context;
    }

    @Override
    public LecturerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.lecturer_details, parent, false);

        return new LecturerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LecturerViewHolder holder, int position)
    {
        LecturerModel model = lecturerModels.get(position);

        holder.bind(model);

    }

    @Override
    public int getItemCount() {
        return lecturerModels.size();
    }


    static class LecturerViewHolder extends RecyclerView.ViewHolder
    {

        private TextView surname;
        private TextView initials;
        private TextView stuff;
        // private TextView radius;

        public LecturerViewHolder(View itemView) {
            super(itemView);

            surname = itemView.findViewById(R.id.lectSurname);
            initials = itemView.findViewById(R.id.lectInit);
            stuff = itemView.findViewById(R.id.lectNumber);
        }

        public void bind(LecturerModel locations) {
            surname.setText("Surname: " + locations.getSurname());
            initials.setText("Initials: " +  locations.getInitials());
            stuff.setText("Stuff No.: " +  locations.getStuffNum());
        }
    }
}
