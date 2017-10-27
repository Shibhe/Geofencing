package com.example.josephsibiya.geoalert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.example.josephsibiya.geoalert.Configuration.GetMacAddress;
//import com.example.josephsibiya.geoalert.SQLite.Student;
import com.example.josephsibiya.geoalert.connection.IPAddress;
import com.example.josephsibiya.geoalert.models.StudentModel;
import com.example.josephsibiya.geoalert.providers.JSONParser;
import com.example.josephsibiya.geoalert.providers.sendEmail;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
    private StudentModel studentModel;
    private IPAddress ipAddress;
    private EditText mac;
    private GetMacAddress getMacAddr;
    private JSONParser jsonParser;
    ProgressDialog progressDialog;

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

        //final String macAddress = getMacAddr.getMacAddress();
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surname.setError(null);
                initials.setError(null);
                studNumber.setError(null);
                IDNo.setError(null);
                gender.setError(null);
                email.setError(null);
                //mac.setText(macAddress);

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
                }

                if (email.length() == 0 && !email.getText().toString().matches(emailPattern)) {
                    Toast.makeText(AddStudentActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    view = email;
                } else {

                    registerUser(surname.getText().toString(), initials.getText().toString(), studNumber.getText().toString(), IDNo.getText().toString(), gender.getText().toString(), email.getText().toString(), mac.getText().toString());
                    //Intent i = new Intent(AddStudentActivity.this, DashActivity.class);
                    //startActivity(i);

                }

            }
        });
    }

    private void registerUser(final String surn, final String ini, final String studNum, final String IDNo, final String gender,
                              final String email, final String macAddress){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> param = new ArrayList<>();

                param.add(new BasicNameValuePair("surname", surn));
                param.add(new BasicNameValuePair("initials", ini));
                param.add(new BasicNameValuePair("studNum", studNum));
                param.add(new BasicNameValuePair("IDNo", IDNo));
                param.add(new BasicNameValuePair("gender", gender));
                param.add(new BasicNameValuePair("email", email));
                param.add(new BasicNameValuePair("macAddress", macAddress));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost("http://geoalert.000webhostapp.com/addStudent.php");

                    httpPost.setEntity(new UrlEncodedFormEntity(param));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (IOException ignored) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                progressDialog.dismiss();
                Toast.makeText(AddStudentActivity.this, "Student Submit Successfully", Toast.LENGTH_LONG).show();

            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = new ProgressDialog(AddStudentActivity.this);
                progressDialog.setMessage("Please wait while adding student....");
                progressDialog.setIndeterminate(false);
                progressDialog.setTitle("Adding Student");
                progressDialog.show();
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(surn, ini, studNum, IDNo, gender, email, macAddress);
    }

}

