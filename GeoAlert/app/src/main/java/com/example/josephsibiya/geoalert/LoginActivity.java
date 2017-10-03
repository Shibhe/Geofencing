package com.example.josephsibiya.geoalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity  {

    private EditText username, password;
    private Button btnLogin;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.email_sign_in_button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username.setError(null);
                password.setError(null);

                if (username.length() == 0  && username.isFocused()){
                    Toast.makeText(LoginActivity.this, "Username required", Toast.LENGTH_SHORT).show();
                    view = username;
                }

                if (password.length() == 0 && username.isFocused()){
                    Toast.makeText(LoginActivity.this, "Password required", Toast.LENGTH_SHORT).show();
                    view = username;
                }
                else{
                    intent = new Intent(LoginActivity.this, DashActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}




