package com.example.josephsibiya.geoalert.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.josephsibiya.geoalert.Adapters.StudentAdapter;
import com.example.josephsibiya.geoalert.models.StudentModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by josephsibiya on 2017/09/12.
 */

public class FetchStudent extends AsyncTask<Void, Void, Void> {

    private StudentAdapter adapter;
    private Context context;

    public FetchStudent(StudentAdapter adapter, Context context) {
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL("");
            urlConnection = (HttpURLConnection) url.openConnection();
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

            JSONArray array = new JSONArray(buffer.toString());

            for (int x = 0; x < array.length(); x++) {

                JSONObject compObj = (JSONObject) array.get(x);

                 int Id;
                 String surname;
                 String initials;
                 String studNum;
                 String gender;
                 String IDNo;
                 String macAddress;

                StudentModel model = new StudentModel();

                Id = compObj.getInt("StudID");
                surname = compObj.getString("Surname");
                initials = compObj.getString("Initials");
                studNum = compObj.getString("StudNum");
                gender = compObj.getString("Gender");
                IDNo = compObj.getString("IDNo");
                macAddress = compObj.getString("MacAddress");


                model.setGender(gender);
                model.setId(Id);
                model.setMacAddress(macAddress);
                model.setStudNum(studNum);
                model.setIDNo(IDNo);
                model.setSurname(surname);
                model.setInitials(initials);

                adapter.numStudents.add(model);

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (final IOException e) {
                    Log.e("MainActivity", "Error closing stream", e);
                }
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait while loading students...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
}
