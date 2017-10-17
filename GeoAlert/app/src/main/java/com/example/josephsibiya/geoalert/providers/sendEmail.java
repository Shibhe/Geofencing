package com.example.josephsibiya.geoalert.providers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by reversidesoftwaresolutions on 10/9/17.
 */

public class sendEmail {

    private Context context;
    private String emailAddress;
    private String message;
    private String subject;

    public sendEmail(Context context, String emailAddress, String message, String subject) {
        this.context = context;
        this.emailAddress = emailAddress;
        this.message = message;
        this.subject = subject;
    }

    public void StatusEmai() {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.d("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
