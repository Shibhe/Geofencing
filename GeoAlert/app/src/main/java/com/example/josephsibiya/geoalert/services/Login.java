package com.example.josephsibiya.geoalert.services;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.models.LecturerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by reversidesoftwaresolutions on 9/29/17.
 */


public class Login extends AsyncTask<Void, Void, Void> {

    private LecturerModel model;

    ConfigClass config  = new ConfigClass();
    @Override
    protected Void doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;


        try {
            URL loginUrl = new URL(config.URL_LOGIN);
            urlConnection = (HttpURLConnection) loginUrl.openConnection();
            urlConnection.setRequestMethod("POST");
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
                 String stuffNum;
                 String username;
                 String password;


                Id = compObj.getInt("id");
                surname = compObj.getString("surname");
                initials = compObj.getString("initials");
                stuffNum = compObj.getString("stuffNum");
                username = compObj.getString("username");
                password = compObj.getString("password");


                model = new LecturerModel(Id,surname,initials,stuffNum,username,password);

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
}
