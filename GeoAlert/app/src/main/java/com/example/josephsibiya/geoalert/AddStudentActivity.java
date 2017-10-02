package com.example.josephsibiya.geoalert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josephsibiya.geoalert.models.StudentModel;

import static java.lang.Integer.parseInt;
//import com.example.josephsibiya.geoalert.services.AddStudentService;

public class AddStudentActivity extends AppCompatActivity {

    //private ProgressDialog pDialog;
    private EditText surname;
    private EditText initials;
    private EditText studNumber;
    private EditText IDNo;
    private EditText email;
    private EditText gender;
    private Button btnAddStudent;
    private String regexStr = "^[0-9]*$";
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String StudGender;
    private String genderCode;
    private StudentModel studentModel = new StudentModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        surname = (EditText) findViewById(R.id.surname);
        initials = (EditText) findViewById(R.id.initials);
        studNumber = (EditText) findViewById(R.id.studNum);
        IDNo = (EditText) findViewById(R.id.IDNo);
        gender = (EditText) findViewById(R.id.Gender);
        email = (EditText) findViewById(R.id.email);
        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);


        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surname.setError(null);
                initials.setError(null);
                studNumber.setError(null);
                IDNo.setError(null);
                gender.setError(null);
                email.setError(null);

                if (surname.length() == 0){
                    Toast.makeText(AddStudentActivity.this, "Surname characters must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = surname;
                }

                if (initials.length() == 0 && initials.length() > 4){
                    Toast.makeText(AddStudentActivity.this, "Initials characters must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = initials;
                }

                if (studNumber.length() == 0 && !studNumber.getText().toString().matches(regexStr) && studNumber.length() > 9){
                    Toast.makeText(AddStudentActivity.this, "Please enter valid email Student number contain" , Toast.LENGTH_SHORT).show();
                    view = studNumber;
                }

                if (IDNo.length() == 0 && !studNumber.getText().toString().matches(regexStr) && IDNo.length() > 13){
                    Toast.makeText(AddStudentActivity.this, "Please enter valid ID number", Toast.LENGTH_SHORT).show();
                    view = IDNo;
                }
                else{
                    genderCode = IDNo.getText().toString().substring(6, 10);
                    StudGender = parseInt(genderCode) < 5000 ? "Female" : "Male";

                    studentModel.setGender(StudGender);
                    gender.setText(StudGender);
                }

                if (email.length() == 0 && !email.getText().toString().matches(emailPattern)){
                    Toast.makeText(AddStudentActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    view = email;
                }
                else{
                    Toast.makeText(AddStudentActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /**final String type = "AddStudent";
        final String studSurname = surname.getText().toString();
        final String studInitial = initials.getText().toString();
        final String studNum = studNumber.getText().toString();
        final String studIDNo = IDNo.getText().toString();
        final String studEmailAddress = email.getText().toString();
        final String gen = gender.getText().toString();**/

    }
}

