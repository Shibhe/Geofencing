package com.example.josephsibiya.geoalert.models;

/**
 * Created by josephsibiya on 2017/09/12.
 */

public class GeofenceLocations {

    private int Id;
    private String name;
    private double latitude;
    private double lognitude;
    private double radius;

    public GeofenceLocations(int id, String name, double latitude, double lognitude, double radius) {
        Id = id;
        this.name = name;
        this.latitude = latitude;
        this.lognitude = lognitude;
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.latitude = -26.089056667409437;
    }

    public double getLognitude() {
        return lognitude;
    }

    public void setLognitude(double lognitude) {
        this.lognitude = 28.02309428953201;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
