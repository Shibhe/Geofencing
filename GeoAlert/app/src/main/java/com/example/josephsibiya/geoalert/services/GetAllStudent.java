package com.example.josephsibiya.geoalert.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.josephsibiya.geoalert.Adapters.StudentAdapter;
import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.models.LecturerModel;
import com.example.josephsibiya.geoalert.models.StudentModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.IDN;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by reversidesoftwaresolutions on 9/29/17.
 */

public class GetAllStudent extends AsyncTask<Void, Void, Void> {

    ConfigClass config  = new ConfigClass();
    private StudentAdapter adapter;
    private Context context;
    private ProgressDialog pDialog;

    public GetAllStudent( StudentAdapter adapter, Context context) {
        this.adapter = adapter;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading students. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;


        try {
            URL loginUrl = new URL("https://api.myjson.com/bins/154yph");
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

            JSONArray array = (JSONArray) object.get("tblStudent");

            for (int x = 0; x < array.length(); x++) {

                JSONObject compObj = (JSONObject) array.get(x);

                 int Id;
                 String surname;
                 String initials;
                 String studNum;
                 String email;
                 String gender;
                 String IDNo;
                 //String status;


                Id = compObj.getInt("id");
                surname = compObj.getString("surname");
                initials = compObj.getString("initials");
                email = compObj.getString("email");
                gender = compObj.getString("gender");
                studNum = compObj.getString("studNum");
                IDNo = compObj.getString("IDNo");
                //status = statu.toString();


                StudentModel model = new StudentModel();

                model.setId(Id);
                model.setGender(gender);
                model.setEmail(email);
                model.setIDNo(IDNo);
                model.setInitials(initials);
                model.setStudNum(studNum);
                model.setSurname(surname);

                adapter.numStudents.add(model);
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
    }
}
