package com.example.josephsibiya.geoalert.services;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.josephsibiya.geoalert.Configuration.Config;
import com.example.josephsibiya.geoalert.DashboardActivity;

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
 * Created by josephsibiya on 2017/09/12.
 */

public class LoginService extends AsyncTask<String, Void, String> {
    private ProgressDialog pDialog;
    private Context context;
    private String username;
    private String password;
    private String type;

    public LoginService(Context context, String username, String password, String type) {
        this.context = context;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];

        // TODO: attempt authentication against a network service.


        if (type == "login") {
            try {

                Thread.activeCount();

                username = params[1];
                password = params[2];


                URL url = new URL(Config.URL_LOGIN);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")+"&"
                        + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = null;
                String line = reader.readLine();

                while (line != null) {
                    result += line;
                }

                bufferedWriter.close();
                reader.close();
                connection.disconnect();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {

        pDialog = new ProgressDialog(context);
        pDialog.setIndeterminate(false);
        pDialog.setMessage("Logging...");
        pDialog.setCancelable(true);
        pDialog.show();

    }

    @Override
    protected void onPostExecute(String results) {
        pDialog.cancel();
        Toast.makeText(context, results, Toast.LENGTH_SHORT).show();
    }
}
