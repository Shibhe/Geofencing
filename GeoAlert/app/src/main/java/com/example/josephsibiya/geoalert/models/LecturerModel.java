package com.example.josephsibiya.geoalert.models;

/**
 * Created by hp on 2017/09/18.
 */

public class LecturerModel {
    private int Id;
    private String surname;
    private String initials;
    private String stuffNum;
    private String username;
    private String email;
    private String password;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuffNum() {
        return stuffNum;
    }

    public void setStuffNum(String stuffNum) {
        this.stuffNum = stuffNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
