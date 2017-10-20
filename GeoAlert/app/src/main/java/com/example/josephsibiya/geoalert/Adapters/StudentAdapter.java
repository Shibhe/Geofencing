package com.example.josephsibiya.geoalert.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.josephsibiya.geoalert.R;
import com.example.josephsibiya.geoalert.UpDateActivity;
import com.example.josephsibiya.geoalert.UpdateLecturerActivity;
import com.example.josephsibiya.geoalert.models.GeofenceLocations;
import com.example.josephsibiya.geoalert.models.StudentModel;

import java.util.ArrayList;

/**
 * Created by josephsibiya on 2017/09/12.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> implements View.OnLongClickListener {

    private Context context;
    //private StudentModel model;
    public ArrayList<StudentModel> numStudents;

    public StudentAdapter(Context context, ArrayList<StudentModel> numStudents) {
        this.context = context;
        this.numStudents = numStudents;
    }


    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.student_cardview, parent, false);

        view.setOnLongClickListener(this);
        return new StudentViewHolder(view);
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
                        Intent intent = new Intent(context, UpDateActivity.class);
                        context.startActivity(intent);
                }
                return false;

            }
        });
        return false;
    }
    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position)
    {
        StudentModel studentModel =  numStudents.get(position);

        holder.bind(studentModel);

    }

    @Override
    public int getItemCount() {
        return numStudents.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder
    {

        private EditText surname;
        private EditText initials;
        private EditText studNumber;


        public StudentViewHolder(View itemView) {
            super(itemView);

            surname = itemView.findViewById(R.id.studSurname);
            initials = itemView.findViewById(R.id.studInit);
            studNumber = itemView.findViewById(R.id.studNumber);
        }

        public void bind(StudentModel model) {

            surname.setText("Surname: " + model.getSurname());
            initials.setText("Initials: " + model.getInitials());
            studNumber.setText("Student No.: " + model.getStudNum());
        }

    }
}
