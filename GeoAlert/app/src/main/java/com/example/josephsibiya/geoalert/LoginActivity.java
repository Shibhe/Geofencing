package com.example.josephsibiya.geoalert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.josephsibiya.geoalert.Adapters.LecturerAdapter;
import com.example.josephsibiya.geoalert.Configuration.AppController;
import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.Configuration.SessionManager;
import com.example.josephsibiya.geoalert.connection.IPAddress;
import com.example.josephsibiya.geoalert.connection.internetConn;
import com.example.josephsibiya.geoalert.models.LecturerModel;
import com.example.josephsibiya.geoalert.providers.JSONParser;
import com.example.josephsibiya.geoalert.providers.sendEmail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//import com.example.josephsibiya.geoalert.SQLite.Lecturer;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btnLogin, btnPswReset, btnSignup;
    private Intent intent;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LecturerModel lecturerModel;
    private internetConn internetConn;
    private SessionManager session;
    private JSONParser jsonParser;
    ConfigClass config = new ConfigClass();
    private LecturerAdapter adapter;
    private ProgressDialog pDialog;
    private IPAddress ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.email_sign_in_button);
        username = (EditText) findViewById(R.id.username);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        password = (EditText) findViewById(R.id.password);
        btnPswReset = (Button) findViewById(R.id.btn_reset_password);

        intent = getIntent();
        final String ipAddre = intent.getStringExtra("ipAddress");

        ipAddress = new IPAddress();
        ipAddress.setIpAddress(ipAddre);

        btnPswReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, DashActivity.class));
            }
        });

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                final String passwrd = password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passwrd)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, passwrd)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {

                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()){
                                    if (passwrd.length() < 5){
                                        password.setError(getString(R.string.minimum_password));
                                    }
                                    else{
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                }
                                else {
                                    Intent intent = new Intent(LoginActivity.this, DashActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });


    }
}