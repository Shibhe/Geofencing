package com.example.josephsibiya.gbalert;

/**
 * Created by josephsibiya on 2017/08/23.
 */

public class Config {

    //Student
    public static final String URL_CREATE_STUDENT="http://10.0.0.15/createStudent.php";
    public static final String URL_UPDATE_STUDENT="http://10.0.0.15/updateStudent.php";
    public static final String URL_DELETE_STUDENT="http://10.0.0.15/deleteStudent.php";
    public static final String URL_READ_STUDENT="http://10.0.0.15/retrieveStudent.php";

    //Geofence
    public static final String URL_CREATE_GEOFENCE="http://10.0.0.15/createGeofence.php";
    public static final String URL_UPDATE_GEOFENCE="http://10.0.0.15/updateGeofence.php";
    public static final String URL_DELETE_GEOFENCE="http://10.0.0.15/deleteGeo.php";
    public static final String URL_READ_GEOFENCE="http://10.0.0.15/retrieveGeo.php";

    public static final String URL_LOGIN="http://10.0.0.15/login.php";
    public static final String URL_CREATE_CREDENTIALS="http://10.0.0.15/photo/create_pwd_user.php";

    //Lecturer
    public static final String URL_UPDATE_LECTURE="http://10.0.0.15/addGeo.php";
    public static final String URL_READ_LECTURE="http://10.0.0.15/addGeo.php";
    public static final String URL_CHANGE_PASSWORD="http://10.0.0.15/addGeo.php";

    //Keys that will be used to send the request to php scripts - student
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_EMAIL_ADDRESS = "email";
    public static final String KEY_STUDENT_NUMBER = "studNum";
    public static final String KEY_MAC_ADDRESS = "macAddress";
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DOB = "dob";

    //Keys that will be used to send the request to php scripts - geofence
    public static final String KEY_GEO_ID = "user_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LAT = "latitude";
    public static final String KEY_LON = "longitude";
    public static final String KEY_RADIUS = "radius";


}
