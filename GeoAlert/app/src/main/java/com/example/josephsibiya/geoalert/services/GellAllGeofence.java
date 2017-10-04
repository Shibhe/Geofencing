package com.example.josephsibiya.geoalert.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.josephsibiya.geoalert.Adapters.GeofenceAdapter;
import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.models.GeofenceLocations;
import com.example.josephsibiya.geoalert.models.StudentModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by reversidesoftwaresolutions on 9/29/17.
 */

public class GellAllGeofence extends AsyncTask<Void, Void, Void> {

    ConfigClass config  = new ConfigClass();
    private Context context;
    private GeofenceAdapter adapter;
    private ProgressDialog pDialog;

    public GellAllGeofence(Context context, GeofenceAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }


    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading locations. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }


    @Override
    protected Void doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;


        try {
            URL loginUrl = new URL(config.URL_LISTGEO);
            urlConnection = (HttpURLConnection) loginUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            //urlConnection.setRequestProperty("X-Auth-Token", "1ef07188cb3a49c48ea1ce543a8b8212");
            urlConnection.connect();
            InputStream stream = urlConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }

            while (buffer.length() == 0) {
                return null;
            }

            JSONObject object = new JSONObject(buffer.toString());

            JSONArray array = (JSONArray) object.get("tblGeofence");

            for (int x = 0; x < array.length(); x++) {

                JSONObject compObj = (JSONObject) array.get(x);
                JSONObject stat = new JSONObject();

                 int Id;
                 String name;
                 double latitude;
                 double longitude;
                String status;


                Id = compObj.getInt("id");
                name = compObj.getString("name");
                latitude = compObj.getDouble("latitude");
                longitude = compObj.getDouble("longitude");
                status = stat.get("success").toString();


                GeofenceLocations model = new GeofenceLocations();

                model.setId(Id);
                model.setName(name);
                model.setLatitude(latitude);
                model.setLongitude(longitude);
                model.setStatus(status);


                adapter.locationsArrayList.add(model);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pDialog.dismiss();
        adapter.notifyDataSetChanged();
    }
}
