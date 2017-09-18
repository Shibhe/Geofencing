package com.example.josephsibiya.geoalert.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
        String url_login = "";

        if (type == "AddStudent") {
            try {
                String stud_name = params[1];
                String initials = params[2];
                String IDNo = params[3];
                String Gender = params[4];
                String studNum = params[5];
                String studMac = params[6];

                URL url = new URL(url_login);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("stud_name", "UTF-8") + "=" + URLEncoder.encode(stud_name, "UTF-8") + "&"
                        + URLEncoder.encode("initials", "UTF-8") + "=" + URLEncoder.encode(initials, "UTF-8") + "&"
                        + URLEncoder.encode("IDNo", "UTF-8") + "=" + URLEncoder.encode(IDNo, "UTF-8") + "&"
                        + URLEncoder.encode("Gender", "UTF-8") + "=" + URLEncoder.encode(Gender, "UTF-8") + "&"
                        + URLEncoder.encode("studNum", "UTF-8") + "=" + URLEncoder.encode(studNum, "UTF-8") + "&"
                        + URLEncoder.encode("studMac", "UTF-8") + "=" + URLEncoder.encode(studMac, "UTF-8");

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
                String name = params[1];
                String latitude = params[2];
                String longitude = params[3];
                String radius = params[4];


                URL url = new URL(url_login);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(latitude, "UTF-8") + "&"
                        + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(longitude, "UTF-8") + "&"
                        + URLEncoder.encode("radius", "UTF-8") + "=" + URLEncoder.encode(radius, "UTF-8");

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

        /**ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();**/
        Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
