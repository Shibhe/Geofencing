package com.example.josephsibiya.gbalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Button btnButton;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnButton = (Button) findViewById(R.id.email_sign_in_button);
        username = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "Login";
                String user = username.getText().toString();
                String pwd = password.getText().toString();

                OnBackgroundLogin onBackgroundLogin = new OnBackgroundLogin(LoginActivity.this);
                onBackgroundLogin.execute(type, user, pwd);
                Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(i);
            }
        });
    }
}
