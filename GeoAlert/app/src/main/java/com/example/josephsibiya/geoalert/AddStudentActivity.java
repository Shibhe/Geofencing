package com.example.josephsibiya.geoalert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.josephsibiya.geoalert.services.AddData;
//import com.example.josephsibiya.geoalert.services.AddStudentService;

public class AddStudentActivity extends AppCompatActivity {

    //private ProgressDialog pDialog;
    private EditText surname;
    private EditText initials;
    private EditText studNumber;
    private EditText IDNo;
    private EditText macAddress;
    private EditText gender;
    private Button addStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        surname = (EditText) findViewById(R.id.surname);
        initials = (EditText) findViewById(R.id.initials);
        studNumber = (EditText) findViewById(R.id.studNum);
        IDNo = (EditText) findViewById(R.id.IDNo);
        gender = (EditText) findViewById(R.id.Gender);
        macAddress = (EditText) findViewById(R.id.MacAddress);
        addStudent = (Button) findViewById(R.id.btnAddStudent);


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudent();
            }
        });
    }

    private void AddStudent()
    {
        String type = "AddStudent";
        String studName = surname.getText().toString();
        String studInitials = initials.getText().toString();
        String studnumb = studNumber.getText().toString();
        String studIDNo = IDNo.getText().toString();
        String studMacAddress = macAddress.getText().toString();
        String gen = gender.getText().toString();

        new AddData(AddStudentActivity.this).execute(type, studName,studInitials,studIDNo, gen, studnumb,studMacAddress);

    }
}

