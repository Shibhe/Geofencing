package com.example.josephsibiya.geoalert;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.josephsibiya.geoalert.services.LoginService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends AppCompatActivity  {

    private EditText username, password;
    private Button login;
    private Intent intent;
    private LoginService mAuthTask = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.email_sign_in_button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username.setError(null);
                password.setError(null);

                if (username.length() == 0 && username.length() > 9){
                    Toast.makeText(LoginActivity.this, "Invalid or Incorrect username", Toast.LENGTH_SHORT).show();
                    view = username;
                }

                if (password.length() == 0 && password.length() > 12){
                    Toast.makeText(LoginActivity.this, "Invalid or Incorrect username", Toast.LENGTH_SHORT).show();
                    view = username;
                }
                else{
                    Login();
                    intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void Login() {

        String user = username.getText().toString();
        String pswd = password.getText().toString();
        String type = "login";
        boolean cancel = false;
        View view = null;

        if (mAuthTask != null){
            return;
        }

        new LoginService(LoginActivity.this, user, pswd, type).execute(type, user, pswd);
    }

}




