package com.example.josephsibiya.geoalert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.josephsibiya.geoalert.services.LoginService;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button login;

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
                Login(view);
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Login(View view) {
        String user = username.getText().toString();
        String pswd = password.getText().toString();
        String type = "login";

        if (TextUtils.isEmpty(user))
        {
            username.setError("Username Required");
        }

        if (TextUtils.isEmpty(pswd))
        {
            password.setError("Password Required");
        }

        //setEditingEnabled(false);

        LoginService loginService = new LoginService(LoginActivity.this);
        loginService.execute(type, user, pswd);
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
