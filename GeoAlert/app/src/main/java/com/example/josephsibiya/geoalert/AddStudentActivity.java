package com.example.josephsibiya.geoalert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity {

    //private ProgressDialog pDialog;
    private EditText surname;
    private EditText initials;
    private EditText studNumber;
    private EditText IDNo;
    private EditText macAddress;
    private Button addStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //surname = (EditText) findViewById(R.id.surname);
        //initials = (EditText) findViewById(R.id.initials);
        //studNumber = (EditText) findViewById(R.id.studNum);
       // IDNo = (EditText) findViewById(R.id.IDNo);
        //macAddress = (EditText) findViewById(R.id.MacAddress);
        //addStudent = (Button) findViewById(R.id.btnAddStudent);

    }
}

