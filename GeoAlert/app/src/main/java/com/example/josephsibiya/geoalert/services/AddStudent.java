package com.example.josephsibiya.geoalert.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.josephsibiya.geoalert.models.StudentModel;

import java.util.List;

/**
 * Created by reversidesoftwaresolutions on 9/29/17.
 */

public class AddStudent  extends AsyncTask<Void, Void, Void> {

    private String surname, initials, studNum, gender, idNo, macAddress;
    private ProgressDialog progressDialog;
    Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait while adding Student...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public AddStudent(Context context, String surname, String initials, String studNum, String gender, String idNo, String macAddress) {
        this.surname = surname;
        this.initials = initials;
        this.studNum = studNum;
        this.gender = gender;
        this.idNo = idNo;
        this.macAddress = macAddress;
        this.context = context;
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
