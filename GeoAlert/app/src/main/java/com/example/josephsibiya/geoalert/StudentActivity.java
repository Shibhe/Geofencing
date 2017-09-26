package com.example.josephsibiya.geoalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.example.josephsibiya.geoalert.Adapters.StudentAdapter;
import com.example.josephsibiya.geoalert.models.StudentModel;
import com.example.josephsibiya.geoalert.services.FetchStudent;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private ArrayList<StudentModel> studentModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        recyclerView = (RecyclerView) findViewById(R.id.rvStudent);
        studentModels = new ArrayList<>();
        studentAdapter = new StudentAdapter(StudentActivity.this, studentModels);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        new FetchStudent(studentAdapter, StudentActivity.this).execute();

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
        studentAdapter.numStudents.remove(pos);
        studentAdapter.notifyDataSetChanged();
    }


    private void moveItem(int pos, int newPos)
    {
        StudentModel studentModel = studentAdapter.numStudents.get(pos);
        //geofenceAdapter.locationsArrayList.get(pos);
        studentAdapter.numStudents.remove(pos);
        studentAdapter.numStudents.add(newPos, studentModel);
        studentAdapter.notifyItemMoved(pos, newPos);
    }
}
