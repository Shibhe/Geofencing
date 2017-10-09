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

public class UpdateGeofenceActivity extends AppCompatActivity {


    String pid;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    private static final String TAG_SUCCESS = "success";

    private EditText longitude;
    private EditText latitude;
    private Button update;
    private ConfigClass configClass;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_geofence);

        longitude = (EditText) findViewById(R.id.inputLongitude);
        latitude = (EditText) findViewById(R.id.inputLatitude);
        update = (Button) findViewById(R.id.btnSaveGeo);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (longitude.length() == 0) {
                    Toast.makeText(UpdateGeofenceActivity.this, "GeofenceSQLite longitude characters must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = longitude;
                }

                if (latitude.length() == 0) {
                    Toast.makeText(UpdateGeofenceActivity.this, "GeofenceSQLite latitude must be more than 0 characters", Toast.LENGTH_SHORT).show();
                    view = latitude;
                }
                else{
                    new SaveGeofenceDetails(name,Double.parseDouble(longitude.getText().toString()), Double.parseDouble(latitude.getText().toString())).execute();
                }
            }
        });
    }

    /**
     * Background Async Task to  Save product Details
     * */
    class SaveGeofenceDetails extends AsyncTask<String, String, String> {

        String name = null;
        Double longitude = 0.0;
        Double latitude = 0.0;

        public SaveGeofenceDetails(String name, Double longitude, Double latitude) {
            this.name = name;
            this.longitude = longitude;
            this.latitude = latitude;
        }

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (pDialog == null){
                Toast.makeText(UpdateGeofenceActivity.this, "Something went wrong, check your internet connection", Toast.LENGTH_LONG).show();
            }
            else {
                pDialog = new ProgressDialog(UpdateGeofenceActivity.this);
                pDialog.setMessage("Saving geofence ...");
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
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("latitude", longitude.toString()));
            params.add(new BasicNameValuePair("longitude", latitude.toString()));

            // sending modified data through http request
            // Notice that update product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(configClass.URL_UPDATEGEO,
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
            // dismiss the dialog once geofence uupdated
            pDialog.dismiss();
        }
    }
}
