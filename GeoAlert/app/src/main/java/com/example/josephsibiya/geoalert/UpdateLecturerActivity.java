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

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.squareup.okhttp.internal.Internal.logger;

public class UpdateLecturerActivity extends AppCompatActivity {

    String id;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    private static final String TAG_SUCCESS = "success";

    private EditText surname, initials, password, confPass;
    private Button update;
    private ConfigClass configClass;
    StringBuilder retVal = new StringBuilder();
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lecturer);

        surname = (EditText) findViewById(R.id.inputLectSurname);
        initials = (EditText) findViewById(R.id.inputLectInitials);
        password = (EditText) findViewById(R.id.inputPwd);
        confPass = (EditText) findViewById(R.id.inputConfPwd);
        update = (Button) findViewById(R.id.btnSaveLec);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (surname.length() == 0) {
                    Toast.makeText(UpdateLecturerActivity.this, "Surname characters must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = surname;
                }

                if (initials.length() == 0 && initials.length() > 4) {
                    Toast.makeText(UpdateLecturerActivity.this, "Initials characters must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = initials;
                }

                if (password.length() == 0 || password.length() > 0){
                    status = validateNewPass(password.getText().toString(), confPass.getText().toString());
                    Toast.makeText(UpdateLecturerActivity.this, status, Toast.LENGTH_SHORT).show();
                    if (status != "success"){
                        view = password;
                    }
                }
                else{


                    new SaveLectureDetails(surname.getText().toString(), initials.getText().toString(), password.getText().toString()).execute();
                }
            }
        });

    }

    /**
     * Background Async Task to  Save product Details
     * */
    class SaveLectureDetails extends AsyncTask<String, String, String> {

        String surna, initials, password;

        public SaveLectureDetails(String surna, String initials, String password) {
            this.surna = surna;
            this.initials = initials;
            this.password = password;
        }


        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (pDialog == null){
                Toast.makeText(UpdateLecturerActivity.this, "Something went wrong, check your internet connection", Toast.LENGTH_LONG).show();
            }
            else {
                pDialog = new ProgressDialog(UpdateLecturerActivity.this);
                pDialog.setMessage("Saving lecturer ...");
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
            params.add(new BasicNameValuePair("id", id));
            params.add(new BasicNameValuePair("surname", surna));
            params.add(new BasicNameValuePair("initials", initials));
            params.add(new BasicNameValuePair("password", password));

            // sending modified data through http request
            // Notice that update product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(configClass.URL_UPDATELECTURE,
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
                    Toast.makeText(UpdateLecturerActivity.this, "Unable to update", Toast.LENGTH_SHORT).show();

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

    public String validateNewPass(String pass1, String pass2){
        StringBuilder retVal = new StringBuilder();

        if(pass1.length() < 1 || pass2.length() < 1 )retVal.append("Empty fields <br>");

        if (pass1 != null && pass2 != null) {

            if (pass1.equals(pass2)) {
                logger.info(pass1 + " = " + pass2);

                pass1 = pass2;
                boolean hasUppercase = !pass1.equals(pass1.toLowerCase());
                boolean hasLowercase = !pass1.equals(pass1.toUpperCase());
                boolean hasNumber = pass1.matches(".*\\d.*");
                boolean noSpecialChar = pass1.matches("[a-zA-Z0-9 ]*");

                if (pass1.length() < 11) {
                    logger.info(pass1 + " is length < 11");
                    retVal.append("Password is too short. Needs to have 11 characters <br>");
                }

                if (!hasUppercase) {
                    logger.info(pass1 + " <-- needs uppercase");
                    retVal.append("Password needs an upper case <br>");
                }

                if (!hasLowercase) {
                    logger.info(pass1 + " <-- needs lowercase");
                    retVal.append("Password needs a lowercase <br>");
                }

                if (!hasNumber) {
                    logger.info(pass1 + "<-- needs a number");
                    retVal.append("Password needs a number <br>");
                }

                if(noSpecialChar){
                    logger.info(pass1 + "<-- needs a specail character");
                    retVal.append("Password needs a special character i.e. !,@,#, etc.  <br>");
                }
            }else{
                logger.info(pass1 + " != " + pass2);
                retVal.append("Passwords don't match<br>");
            }
        }else{
            logger.info("Passwords = null");
            retVal.append("Passwords Null <br>");
        }
        if(retVal.length() == 0){
            logger.info("Password validates");
            retVal.append("Success");
        }

        return retVal.toString();

    }
}
