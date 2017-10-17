package com.example.josephsibiya.geoalert.providers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by reversidesoftwaresolutions on 10/9/17.
 */

public class GetAddressTask extends AsyncTask<Location, Void, String> {
    private Context mContext;
    private Address addresses;
    private Double latitude, longitude;

    public GetAddressTask(Context mContext, Double latitude, Double longitude) {
        this.mContext = mContext;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    /*
    * When the task finishes, onPostExecute() displays the address. */
    @Override
    protected void onPostExecute(String address) {
        // Display the current address in the UI
        Toast.makeText(mContext, address, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(Location... params) {
        Geocoder geocoder =
                new Geocoder(mContext, Locale.getDefault());
        // Get the current location from the input parameter list {

        Location loc = params[0];
        // Create a list to contain the result address List<Address> addresses = null;
        try {
            addresses = (Address) geocoder.getFromLocation(latitude,
                    longitude, 1);
        } catch (IOException e1) {
            Log.e("LocationSampleActivity",
                    "IO Exception in getFromLocation()");
            e1.printStackTrace();
            return ("IO Exception trying to get address");
        } catch (IllegalArgumentException e2) {
            // Error message to post in the log
            String errorString = "Illegal arguments " + Double.toString(loc.getLatitude()) +
                    ", " +
                    Double.toString(loc.getLongitude()) +
                    " passed to address service";
            Log.e("LocationSampleActivity", errorString);
            e2.printStackTrace();
            return errorString;
        }

        /*
        * Format the first line of address (if available), * city, and country name.
        */
        String addressText = String.format(
                "%s, %s, %s",
                // If there's a street address, add it address.getMaxAddressLineIndex() > 0 ?

                addresses.getAddressLine(0) + ":" + "",
                // Locality is usually a city
                addresses.getLocality(),
                // The country of the address
                addresses.getCountryName());
        // Return the text
        return addressText.toString();
    }
}
// AsyncTask class


