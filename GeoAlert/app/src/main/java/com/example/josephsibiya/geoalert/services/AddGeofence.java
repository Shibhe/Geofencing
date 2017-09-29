package com.example.josephsibiya.geoalert.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by reversidesoftwaresolutions on 9/29/17.
 */

public class AddGeofence extends AsyncTask<Void, Void, Void> {

    private String latitude, longitude;
    private ProgressDialog progressDialog;
    Context context;

    public AddGeofence(String latitude, String longitude, Context context) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait while adding Geofence...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
    }
}
