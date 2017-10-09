package com.example.josephsibiya.geoalert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.josephsibiya.geoalert.Configuration.AppController;
import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.SQLite.Student;
import com.example.josephsibiya.geoalert.models.StudentModel;
import com.example.josephsibiya.geoalert.providers.sendEmail;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
//import com.example.josephsibiya.geoalert.services.AddStudentService;

public class AddStudentActivity extends AppCompatActivity {


    private static final String TAG = AddStudentActivity.class.getSimpleName();


    private ProgressDialog pDialog;
    private EditText surname;
    private EditText initials;
    private EditText studNumber;
    private Intent intent;
    private EditText IDNo;
    private EditText email;
    private EditText gender;
    private Button btnAddStudent;
    private String regexStr = "^[0-9]*$";
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String StudGender;
    private String genderCode;
    private Student student = new Student(AddStudentActivity.this);
    private StudentModel studentModel;
    private ConfigClass configClass = new ConfigClass();

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

                if (surname.length() == 0) {
                    Toast.makeText(AddStudentActivity.this, "Surname characters must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = surname;
                }

                if (initials.length() == 0 && initials.length() > 4) {
                    Toast.makeText(AddStudentActivity.this, "Initials characters must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = initials;
                }

                if (studNumber.length() == 0 && !studNumber.getText().toString().matches(regexStr) && studNumber.length() > 9) {
                    Toast.makeText(AddStudentActivity.this, "Please enter valid email Student number contain", Toast.LENGTH_SHORT).show();
                    view = studNumber;
                }

                if (IDNo.length() == 0 && !studNumber.getText().toString().matches(regexStr) && IDNo.length() > 13) {
                    Toast.makeText(AddStudentActivity.this, "Please enter valid ID number", Toast.LENGTH_SHORT).show();
                    view = IDNo;
                } else {
                    genderCode = IDNo.getText().toString().substring(6, 10);
                    StudGender = parseInt(genderCode) < 5000 ? "Female" : "Male";

                    studentModel.setGender(StudGender);
                    gender.setText(StudGender);
                }

                if (email.length() == 0 && !email.getText().toString().matches(emailPattern)) {
                    Toast.makeText(AddStudentActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    view = email;
                } else {

                    registerUser(surname.getText().toString(), initials.getText().toString(), studNumber.getText().toString(), IDNo.getText().toString(), gender.getText().toString(), email.getText().toString());
                    //Toast.makeText(AddStudentActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void registerUser(final String surn, final String ini, final String studNum, final String IDNo, final String gender,
                              final String email) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog = new ProgressDialog(AddStudentActivity.this);
        if (pDialog == null) {
            Toast.makeText(AddStudentActivity.this, "Something went wrong, check your internet connection", Toast.LENGTH_LONG).show();
            intent = new Intent(AddStudentActivity.this, AddStudentActivity.class);
            startActivity(intent);
        } else {
            pDialog.setMessage("Registering ...");
            showDialog();

            StringRequest strReq = new StringRequest(Request.Method.POST,
                    configClass.URL_ADDSTU, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Register Response: " + response.toString());
                    hideDialog();

                    try {
                        JSONObject jObj = new JSONObject(response);
                        boolean error = jObj.getBoolean("error");
                        if (!error) {
                            // User successfully stored in MySQL
                            // Now store the user in sqlite

                            sendEmail send = new sendEmail(AddStudentActivity.this, email, "You've been monitered", "Successfully");
                            send.StatusEmai();

                            String uid = jObj.getString("uid");

                            JSONObject user = jObj.getJSONObject("student");
                            String sur = user.getString("surname");
                            String init = user.getString("initials");
                            String IDNo = user.getString("IDNo");
                            String gender = user.getString("gender");
                            String studN = user.getString("studNum");
                            String email = user.getString("email");

                            // Inserting row in users table
                            student.addUser(sur, init, IDNo, gender, studN, email);

                            Toast.makeText(getApplicationContext(), "Student successfully Added.!", Toast.LENGTH_LONG).show();

                            // Launch login activity
                            Intent intent = new Intent(
                                    AddStudentActivity.this,
                                    DashActivity.class);
                            startActivity(intent);
                            finish();
                        } else {

                            // Error occurred in registration. Get the error
                            // message
                            String errorMsg = jObj.getString("error_msg");
                            Toast.makeText(getApplicationContext(),
                                    errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Registration Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();
                    hideDialog();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting params to register url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("surname", surn);
                    params.put("initials", ini);
                    params.put("studNum", studNum);
                    params.put("IDNo", IDNo);
                    params.put("gender", gender);
                    params.put("email", email);
                    //params.put("macAddress", macAdd);

                    return params;
                }

            };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        }
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}

