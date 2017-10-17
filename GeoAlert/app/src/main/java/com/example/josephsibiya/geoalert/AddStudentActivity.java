package com.example.josephsibiya.geoalert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.example.josephsibiya.geoalert.Configuration.GetMacAddress;
//import com.example.josephsibiya.geoalert.SQLite.Student;
import com.example.josephsibiya.geoalert.connection.IPAddress;
import com.example.josephsibiya.geoalert.models.StudentModel;
import com.example.josephsibiya.geoalert.providers.JSONParser;
import com.example.josephsibiya.geoalert.providers.sendEmail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    //private Student student = new Student(AddStudentActivity.this);
    private StudentModel studentModel;
    private IPAddress ipAddress;
    private EditText mac;
    private GetMacAddress getMacAddr;
    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    private JSONParser jsonParser;

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
        mac = (EditText) findViewById(R.id.macAddress);
        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);

        getMacAddr = new GetMacAddress(AddStudentActivity.this);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("Student").setValue("1");

        final String macAddress = getMacAddr.getMacAddress();
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surname.setError(null);
                initials.setError(null);
                studNumber.setError(null);
                IDNo.setError(null);
                gender.setError(null);
                email.setError(null);
                mac.setText(macAddress);

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


                }

            }
        });
    }



    // [END write_fan_out]
    /**private void registerUser(final String surn, final String ini, final String studNum, final String IDNo, final String gender,
                              final String email, final String macAddress) {

        // Building Parameters
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("surname", surn));
        params.add(new BasicNameValuePair("initials", ini));
        params.add(new BasicNameValuePair("studNum", studNum));
        params.add(new BasicNameValuePair("IDNo", IDNo));
        params.add(new BasicNameValuePair("gender", gender));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("macAddress", macAddress));

        hideDialog();
        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest("http://"+ ipAddress.getIpAddress() + "/geofence-scripts/addStudent.php",
                "POST", params);

        // check log cat fro response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt("success");

            if (success == 1) {
                showDialog();
                // successfully created product
                Intent i = new Intent(AddStudentActivity.this, DashActivity.class);
                startActivity(i);

                // closing this screen
                finish();
            } else {
                // failed to create product
                Toast.makeText(AddStudentActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }**/

}

