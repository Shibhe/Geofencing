package com.example.josephsibiya.geoalert.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.josephsibiya.geoalert.R;
import com.example.josephsibiya.geoalert.models.StudentModel;

import java.util.ArrayList;

/**
 * Created by josephsibiya on 2017/09/12.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

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

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position)
    {
        StudentModel studentModel = numStudents.get(position);

        //holder.IDNo.setText(studentModel.getIDNo());
        holder.initials.setText(studentModel.getInitials());
        //holder.macAddress.setText(studentModel.getMacAddress());
        holder.studNumber.setText(studentModel.getStudNum());
        holder.surname.setText(studentModel.getSurname());

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
        //private EditText IDNo;
        //private EditText macAddress;
       // private Button addStudent;

        public StudentViewHolder(View itemView) {
            super(itemView);

            surname = itemView.findViewById(R.id.studSurname);
            initials = itemView.findViewById(R.id.studInit);
            studNumber = itemView.findViewById(R.id.studNumber);
            //IDNo = itemView.findViewById(R.id.IDNo);
            //macAddress = itemView.findViewById(R.id.MacAddress);
            //addStudent = itemView.findViewById(R.id.btnAddStudent);
        }
    }
}
