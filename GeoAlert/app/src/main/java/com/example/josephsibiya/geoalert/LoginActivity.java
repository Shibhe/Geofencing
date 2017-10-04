package com.example.josephsibiya.geoalert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.josephsibiya.geoalert.Adapters.AndroidLoginController;
import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.models.LecturerModel;
import com.example.josephsibiya.geoalert.services.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity  {

    private EditText username, password;
    private Button btnLogin;
    private Intent intent;
    ConfigClass config  = new ConfigClass();
    ProgressDialog progressDialog;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LecturerModel lecturerModel;

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
                    login(username.getText().toString(), password.getText().toString());
                    intent = new Intent(LoginActivity.this, DashActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void login(final String userNa, final String passWord){
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                config.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // Now store the user in SQLite
                        JSONObject user = jObj.getJSONObject("tblLecturer");
                        String userN = user.getString("username");
                        String pwd = user.getString("password");

                        // Inserting row in users table
                        lecturerModel.setUsername(userN);
                        lecturerModel.setPassword(pwd);
                        //session.setLoggedin(true);

                        if (userN.equals(userNa) && pwd.equals(passWord)){
                            intent = new Intent(LoginActivity.this, DashActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(LoginActivity.this, "Unknown Error occurred", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("username", userNa);
                params.put("password", passWord);

                return params;
            }

        };

        // Adding request to request queue
        //AndroidLoginController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}




