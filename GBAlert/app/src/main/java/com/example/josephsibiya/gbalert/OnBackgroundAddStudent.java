package com.example.josephsibiya.gbalert;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by josephsibiya on 2017/08/19.
 */

public class OnBackgroundAddStudent extends AsyncTask<String, Void, String> {

    private Context cntx;

    public OnBackgroundAddStudent(Context context) {
        cntx = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String url_login = "http://172.31.120.181/addStudent.php";
        if (type == "AddGeo")
        {
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
                String post_data = URLEncoder.encode("stud_name","UTF-8")+"="+URLEncoder.encode(stud_name,"UTF-8")+"&"
                        +URLEncoder.encode("initials","UTF-8")+"="+URLEncoder.encode(initials,"UTF-8")+"&"
                        +URLEncoder.encode("IDNo","UTF-8")+"="+URLEncoder.encode(IDNo,"UTF-8")+"&"
                        +URLEncoder.encode("Gender","UTF-8")+"="+URLEncoder.encode(Gender,"UTF-8")+"&"
                +URLEncoder.encode("studNum","UTF-8")+"="+URLEncoder.encode(studNum,"UTF-8")+"&"
                +URLEncoder.encode("studMac","UTF-8")+"="+URLEncoder.encode(studMac,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = reader.readLine();

                while (line != null)
                {
                    result += line;
                }

                bufferedWriter.close();
                reader.close();
                return  result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public AlertDialog alertDialog;

    @Override
    protected void onPostExecute(String s) {
        AlertDialog alertDialog = new AlertDialog.Builder(cntx).create();
        alertDialog.setTitle("Student");


    }

    @Override
    protected void onPreExecute() {
        alertDialog.setMessage("Successfully Added");
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
