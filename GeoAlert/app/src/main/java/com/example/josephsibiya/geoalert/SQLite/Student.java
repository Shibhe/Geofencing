package com.example.josephsibiya.geoalert.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.HashMap;

/**
 * Created by reversidesoftwaresolutions on 10/9/17.
 */

public class Student extends SQLiteOpenHelper {

    private static final String TAG = Student.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "geoAlert";

    // Login table name
    private static final String TABLE_USER = "student";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "surname";
    private static final String KEY_INIT = "initials";
    private static final String KEY_IDNo = "IDNo";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_STUDNUM = "studNum";
    private static final String KEY_EMAIL = "email";

    public Student(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                 + KEY_INIT + " TEXT,"
                + KEY_IDNo + " TEXT,"
                +  KEY_GENDER + " TEXT,"
                + KEY_STUDNUM + " TEXT,"
                +  KEY_EMAIL + " TEXT,";

        db.execSQL(CREATE_STUDENT_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String name, String initials, String IDno, String gender, String studNum, String emails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_INIT, initials); // Email
        values.put(KEY_IDNo, IDno); // Email
        values.put(KEY_GENDER, gender); // Email
        values.put(KEY_STUDNUM, studNum); // Email
        values.put(KEY_EMAIL, emails); // Created At

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("surname", cursor.getString(1));
            user.put("initials", cursor.getString(2));
            user.put("IDNo", cursor.getString(3));
            user.put("gender", cursor.getString(4));
            user.put("studNum", cursor.getString(5));
            user.put("email", cursor.getString(6));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
}
