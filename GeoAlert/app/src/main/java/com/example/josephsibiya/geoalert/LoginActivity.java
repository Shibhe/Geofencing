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

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.email_sign_in_button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

       // RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
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

        username.setError(null);
        password.setError(null);

        if (!TextUtils.isEmpty(user)){
            username.setError("REQUIRED");
            view = username;
            cancel = true;
        }

        if (!TextUtils.isEmpty(pswd) && !isPasswordValid(pswd)){
            password.setError("REQUIRED");
            view = username;
            cancel = true;
        }

        if (cancel){
            view.requestFocus();
        }
        else {
            new LoginService(LoginActivity.this, user, pswd, type).execute(type, user, pswd);
        }
           // new LoginService(LoginActivity.this, user, pswd, type).execute(type, user, pswd);
           // intent = new Intent(LoginActivity.this, DashboardActivity.class);
            //startActivity(intent);

        ///else{
         //   Toast.makeText(LoginActivity.this, "All field must not be empty", Toast.LENGTH_LONG).show();
        //}
        //setEditingEnabled(false);
        //return  status;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }



    /**private  void setEditingEnabled(boolean enabled)
    {
        username.setEnabled(enabled);
        password.setEnabled(enabled);

        if (enabled){
            login.setVisibility(View.VISIBLE);
        }
        else{
            login.setVisibility(View.GONE);
        }
    }**/
}




