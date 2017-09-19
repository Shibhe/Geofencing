package com.example.josephsibiya.geoalert.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.josephsibiya.geoalert.Configuration.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by hp on 2017/09/18.
 */

public class AddData extends AsyncTask<String, Void, String> {

    private Context context;
    private ProgressDialog pDialog;

    public AddData(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String...params) {

        String type = params[0];
       // String url_addStudent = "http://192.168.2.198/geofence/addStudent.php";
       // String url_addGeofence = "http://192.168.2.198/geofence/addGeofence.php";

        if (type == "AddStudent") {
            try {
                String stud_name = params[1];
                String initials = params[2];
                String IDNo = params[3];
                String Gender = params[4];
                String studNum = params[5];
                String studMac = params[6];

                URL url = new URL(Config.URL_ADDSTU);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Surname", "UTF-8") + "=" + URLEncoder.encode(stud_name, "UTF-8")+"&"
                        + URLEncoder.encode("Initials", "UTF-8") + "=" + URLEncoder.encode(initials, "UTF-8")+"&"
                        + URLEncoder.encode("IDNo", "UTF-8") + "=" + URLEncoder.encode(IDNo, "UTF-8")+"&"
                        + URLEncoder.encode("Gender", "UTF-8") + "=" + URLEncoder.encode(Gender, "UTF-8")+"&"
                        + URLEncoder.encode("StudNum", "UTF-8") + "=" + URLEncoder.encode(studNum, "UTF-8")+"&"
                        + URLEncoder.encode("MacAddress", "UTF-8") + "=" + URLEncoder.encode(studMac, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = reader.readLine();

                while (line != null) {
                    result += line;
                }

                bufferedWriter.close();
                reader.close();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type == "AddGeofence"){
            try {
                String geo_name = params[1];
                String geo_lati = params[2];
                String geo_longi = params[3];
                String geo_radius = params[4];


                URL url = new URL(Config.URL_ADDGEO);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("GeoName", "UTF-8") + "=" + URLEncoder.encode(geo_name, "UTF-8") + "&"
                        + URLEncoder.encode("GeoLatitude", "UTF-8") + "=" + URLEncoder.encode(geo_lati, "UTF-8") + "&"
                        + URLEncoder.encode("GeoLongitude", "UTF-8") + "=" + URLEncoder.encode(geo_longi, "UTF-8") + "&"
                        + URLEncoder.encode("GeoRadius", "UTF-8") + "=" + URLEncoder.encode(geo_radius, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = reader.readLine();

                while (line != null) {
                    result += line;
                }

                bufferedWriter.close();
                reader.close();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
