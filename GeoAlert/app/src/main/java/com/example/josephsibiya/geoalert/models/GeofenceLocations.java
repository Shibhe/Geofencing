package com.example.josephsibiya.geoalert.models;

/**
 * Created by josephsibiya on 2017/09/12.
 */

public class GeofenceLocations {

    private int Id;
    private String name;
    private double latitude;
    private double longitude;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = "Joseph";
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
