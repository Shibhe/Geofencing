package com.example.josephsibiya.geoalert.providers;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by reversidesoftwaresolutions on 10/2/17.
 */

public class PlaceContract {

    public static final String AUTHORITY = "com.delaroystudios.locationgeo";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    public static final String PATH_PLACES = "places";

    public static final class PlaceEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLACES).build();

        public static final String TABLE_NAME = "places";
        public static final String COLUMN_PLACE_ID = "placeID";
    }
}
