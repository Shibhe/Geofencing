package com.example.josephsibiya.geoalert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.Configuration.JSONParser;
//import com.example.josephsibiya.geoalert.SQLite.Student;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UpDateActivity extends AppCompatActivity {

    String pid;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    private static final String TAG_SUCCESS = "success";

    private  EditText surname;
    private EditText initials;
    private EditText email;
    private Button update;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private ConfigClass configClass;

    //Update student
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date);

        surname = (EditText) findViewById(R.id.surname);
        initials = (EditText) findViewById(R.id.initials);
        email = (EditText) findViewById(R.id.email);
        update = (Button) findViewById(R.id.btnSave);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (surname.length() == 0) {
                    Toast.makeText(UpDateActivity.this, "Surname characters must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = surname;
                }

                if (initials.length() == 0 && initials.length() > 4) {
                    Toast.makeText(UpDateActivity.this, "Initials characters must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = initials;
                }
                if (email.length() == 0 && !email.getText().toString().matches(emailPattern)) {
                    Toast.makeText(UpDateActivity.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    view = email;
                } else {

                    new SaveStudentDetails(surname.getText().toString(), initials.getText().toString(),email.getText().toString()).execute();
                    //registerUser(surname.getText().toString(), initials.getText().toString(), studNumber.getText().toString(), IDNo.getText().toString(), gender.getText().toString(), email.getText().toString());
                    //Toast.m
                }
            }
        });
    }
    /**
     * Background Async Task to  Save product Details
     * */
    class SaveStudentDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */


         String studSurname;
         String studInitial;
         String studEmailAddress;

        public SaveStudentDetails(String studSurname, String studInitial, String studEmailAddress) {
            this.studSurname = studSurname;
            this.studInitial = studInitial;
            this.studEmailAddress = studEmailAddress;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (pDialog == null){
                    Toast.makeText(UpDateActivity.this, "Something went wrong, check your internet connection", Toast.LENGTH_LONG).show();
            }
            else {
                pDialog = new ProgressDialog(UpDateActivity.this);
                pDialog.setMessage("Saving product ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }
        }

        /**
         * Saving product
         * */
        protected String doInBackground(String... args) {

            // getting updated data from EditTexts


            configClass = new ConfigClass();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("id", pid));
            params.add(new BasicNameValuePair("surname", studSurname));
            params.add(new BasicNameValuePair("initials", studInitial));
            params.add(new BasicNameValuePair("email", studEmailAddress));

            // sending modified data through http request
            // Notice that update product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(configClass.URL_UPDATESTU,
                    "POST", params);

            // check json success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully updated

                    Intent i = getIntent();
                    // send result code 100 to notify about product update
                    setResult(100, i);
                    finish();
                } else {
                    // failed to update product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product uupdated
            pDialog.dismiss();
        }
    }
}
