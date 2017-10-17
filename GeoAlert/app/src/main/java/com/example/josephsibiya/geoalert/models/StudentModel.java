package com.example.josephsibiya.geoalert.models;

import com.google.firebase.database.Exclude;

import java.net.IDN;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by josephsibiya on 2017/09/12.
 */

public class StudentModel {

    private int Id;
    private String surname;
    private String initials;
    private String studNum;
    private String email;
    private String gender;
    private String IDNo;

    public StudentModel(int id, String surname, String initials, String studNum, String email, String gender, String IDNo) {
        Id = id;
        this.surname = surname;
        this.initials = initials;
        this.studNum = studNum;
        this.email = email;
        this.gender = gender;
        this.IDNo = IDNo;

    }

    public StudentModel() {

    }


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

    public String getStudNum() {
        return studNum;
    }

    public void setStudNum(String studNum) {
        this.studNum = studNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIDNo() {
        return IDNo;
    }

    public void setIDNo(String IDNo) {
        this.IDNo = IDNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", Id);
        result.put("surname", surname);
        result.put("initials", initials);
        result.put("studNum", studNum);
        result.put("IDNo", IDNo);
        result.put("gender", gender);
        result.put("email", email);

        return result;
    }
}
