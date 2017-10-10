package com.example.josephsibiya.geoalert.Configuration;

import com.example.josephsibiya.geoalert.connection.IPAddress;

/**
 * Created by hp on 2017/09/19.
 */

public class ConfigClass {

    IPAddress address = new IPAddress();

    public  final String URL_LOGIN = "http://" + address.getIpAddress() + "/geofence-scripts/login.php";
    public  final String URL_ADDGEO = "http://" + address.getIpAddress() + "/geofence-scripts/add_geofence.php";
    public    final String URL_ADDSTU = "http://" + address.getIpAddress() + "/geofence-scripts/addStudent.php";
    public    final String URL_DELETESTU = "http://" + address.getIpAddress() + "/geofence-scripts/delete_student.php";
    public    final String URL_UPDATESTU = "http://" + address.getIpAddress() + "/geofence-scripts/update_student.php";
    public    final String URL_LISTSTU = "http://" + address.getIpAddress() + "/geofence-scripts/get_all_student.php";
    public  final String URL_DELETEGEO = "http://" + address.getIpAddress() + "/geofence-scripts/delete_geofence.php";
    public  final String URL_UPDATEGEO = "http://" + address.getIpAddress() + "/geofence-scripts/update_geofence.php";
    public  final String URL_LISTGEO = "http://" + address.getIpAddress() + "/geofence-scripts/list_all_geofence.php";
    public  final String URL_UPDATELECTURE = "http://" + address.getIpAddress() + "/geofence-scripts/update_lecturer.php";
    public  final String URL_DETAILSLECTURE = "http://" + address.getIpAddress() + "/geofence-scripts/list_lecturer.php";
}
