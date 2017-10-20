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

import com.example.josephsibiya.geoalert.R;
import com.example.josephsibiya.geoalert.UpdateLecturerActivity;
import com.example.josephsibiya.geoalert.models.GeofenceLocations;
import com.example.josephsibiya.geoalert.models.LecturerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by reversidesoftwaresolutions on 10/10/17.
 */

public class LecturerAdapter extends RecyclerView.Adapter<LecturerAdapter.LecturerViewHolder> implements View.OnLongClickListener{

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

        view.setOnLongClickListener(this);

        return new LecturerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LecturerViewHolder holder, int position)
    {
        LecturerModel model = lecturerModels.get(position);

        holder.bind(model);

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
                        Intent intent = new Intent(context, UpdateLecturerActivity.class);
                        context.startActivity(intent);
                }
                return false;
            }
        });
        return false;
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
