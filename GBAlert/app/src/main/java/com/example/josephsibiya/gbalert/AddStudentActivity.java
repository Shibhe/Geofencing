package com.example.josephsibiya.gbalert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity implements Student {

    Button addStudent;
    EditText surname, init, idNum, gender, studNum, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        addStudent = (Button) findViewById(R.id.btnAddStudent);
        surname = (EditText) findViewById(R.id.surname);
        init = (EditText) findViewById(R.id.initials);
        idNum = (EditText) findViewById(R.id.IDNo);
        gender = (EditText) findViewById(R.id.Gender);
        studNum = (EditText) findViewById(R.id.studNum);
        address = (EditText) findViewById(R.id.MacAddress);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "AddStud";
                String sname = surname.getText().toString();
                String ini = init.getText().toString();
                String id = idNum.getText().toString();
                String gen = gender.getText().toString();
                String snum = studNum.getText().toString();
                String addr = address.getText().toString();

                OnBackgroundAddStudent onBackgroundAddStudent = new OnBackgroundAddStudent(AddStudentActivity.this);

                onBackgroundAddStudent.execute(type, sname, ini, id, gen, snum, addr);
            }
        });
    }

    @Override
    public void createStudent() {

    }

    @Override
    public void removeStudent() {

    }

    @Override
    public void updateStudent() {

    }
}
