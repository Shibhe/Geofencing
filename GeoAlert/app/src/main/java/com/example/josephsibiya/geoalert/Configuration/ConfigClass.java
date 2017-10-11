package com.example.josephsibiya.geoalert.Configuration;

import com.example.josephsibiya.geoalert.connection.IPAddress;

/**
 * Created by hp on 2017/09/19.
 */

public class ConfigClass {

    IPAddress address = new IPAddress();

    public  final String URL_LOGIN = "http://192.168.2.47/geofence-scripts/login.php";
    public  final String URL_ADDGEO = "http://192.168.2.47/geofence-scripts/add_geofence.php";
    public    final String URL_ADDSTU = "http://192.168.2.47/geofence-scripts/addStudent.php";
    public    final String URL_DELETESTU = "http://192.168.2.47/geofence-scripts/delete_student.php";
    public    final String URL_UPDATESTU = "http://192.168.2.47/geofence-scripts/update_student.php";
    public    final String URL_LISTSTU = "http://192.168.2.47/geofence-scripts/get_all_student.php";
    public  final String URL_DELETEGEO = "http://192.168.2.47/geofence-scripts/delete_geofence.php";
    public  final String URL_UPDATEGEO = "http://192.168.2.47/geofence-scripts/update_geofence.php";
    public  final String URL_LISTGEO = "http://192.168.2.47/geofence-scripts/list_all_geofence.php";
    public  final String URL_UPDATELECTURE = "http://192.168.2.47/geofence-scripts/update_lecturer.php";
    public  final String URL_DETAILSLECTURE = "http://192.168.2.47/geofence-scripts/list_lecturer.php";
}
