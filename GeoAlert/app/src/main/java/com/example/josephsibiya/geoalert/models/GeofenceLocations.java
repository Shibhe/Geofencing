package com.example.josephsibiya.geoalert.models;

/**
 * Created by josephsibiya on 2017/09/12.
 */

public class GeofenceLocations {

    private int Id;
    private double latitude;
    private double longitude;

    public GeofenceLocations(int id, double latitude, double lognitude) {
        Id = id;
        this.latitude = latitude;
        this.longitude = lognitude;
    }


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

    public double getLognitude() {
        return longitude;
    }

    public void setLognitude(double longitude) {
        this.longitude = longitude;
    }


}
