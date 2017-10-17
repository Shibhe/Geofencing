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
import com.example.josephsibiya.geoalert.Adapters.LecturerAdapter;
import com.example.josephsibiya.geoalert.Configuration.AppController;
import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.Configuration.SessionManager;
import com.example.josephsibiya.geoalert.connection.IPAddress;
import com.example.josephsibiya.geoalert.connection.internetConn;
import com.example.josephsibiya.geoalert.models.LecturerModel;
import com.example.josephsibiya.geoalert.providers.JSONParser;
import com.example.josephsibiya.geoalert.providers.sendEmail;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//import com.example.josephsibiya.geoalert.SQLite.Lecturer;

public class LoginActivity extends AppCompatActivity  {

    private EditText username, password;
    private Button btnLogin, btnPswReset;
    private Intent intent;
    ProgressDialog progressDialog;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LecturerModel lecturerModel;
    private internetConn internetConn;
    private SessionManager session;
    private JSONParser jsonParser;
    ConfigClass config  = new ConfigClass();
    private LecturerAdapter adapter;
    private ProgressDialog pDialog;
    private IPAddress ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.email_sign_in_button);
        username = (EditText) findViewById(R.id.username);
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

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    username.setError(null);
                    password.setError(null);

                    if (username.length() == 0 && username.isFocused()) {
                        Toast.makeText(LoginActivity.this, "Username required", Toast.LENGTH_SHORT).show();
                        view = username;
                    }

                    if (password.length() == 0 && username.isFocused()) {
                        Toast.makeText(LoginActivity.this, "Password required", Toast.LENGTH_SHORT).show();
                        view = username;
                    } else {
                        checkLogin(username.getText().toString(), password.getText().toString(), ipAddre);
                    }
                }
            });

    }


  private void checkLogin(final String email, final String password, final String ip) {
      // Tag used to cancel the request
      String tag_string_req = "req_login";

      progressDialog.setMessage("Logging in ...");
      showDialog();

      StringRequest strReq = new StringRequest(Request.Method.POST,
              "http://geoalert.000webhostapp.com/login.php", new Response.Listener<String>() {

          @Override
          public void onResponse(String response) {
              Log.d(TAG, "Login Response: " + response.toString());
              hideDialog();

              try {

                  JSONObject jObj = new JSONObject(response);
                  int error = jObj.getInt("success");

                  session = new SessionManager(LoginActivity.this);
                  // Check for error node in json
                  if (error == 1) {
                      // user successfully logged in
                      // Create login session
                      session.setLogin(true);

                      sendEmail send = new sendEmail(LoginActivity.this, email, "You've successfully logged in", "Successfully logged in");
                      send.StatusEmai();

                      JSONObject user = jObj.getJSONObject("tblLecturer");
                      int id = user.getInt("id");
                      String surname = user.getString("surname");
                      String initials = user.getString("initials");
                      String stuffNum = user.getString("stuffNum");
                      String email = user.getString("email");
                      String password = user
                              .getString("password");

                      lecturerModel = new LecturerModel();

                      lecturerModel.setId(id);
                      lecturerModel.setPassword(password);
                      lecturerModel.setInitials(initials);
                      lecturerModel.setStuffNum(stuffNum);
                      lecturerModel.setSurname(surname);
                      lecturerModel.setEmail(email);

                      adapter.lecturerModels.add(lecturerModel);

                      session.setLogin(true);

                      // Launch main activity
                      intent = new Intent(LoginActivity.this,
                              DashActivity.class);
                      intent.putExtra("surname", surname);
                      intent.putExtra("initials", initials);
                      startActivity(intent);
                      finish();

                  } else {
                      // Error in login. Get the error message
                      String errorMsg = jObj.getString("message");
                      Toast.makeText(LoginActivity.this,
                              errorMsg, Toast.LENGTH_LONG).show();
                  }
              } catch (JSONException e) {
                  // JSON error
                  e.printStackTrace();
                  Toast.makeText(LoginActivity.this, "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
              }

          }
      }, new Response.ErrorListener() {

          @Override
          public void onErrorResponse(VolleyError error) {
              Log.e(TAG, "Login Error: " + error.getMessage());
              Toast.makeText(LoginActivity.this,
                      error.getMessage(), Toast.LENGTH_LONG).show();
              hideDialog();
          }
      }) {

          @Override
          protected Map<String, String> getParams() {
              // Posting parameters to login url
              Map<String, String> params = new HashMap<String, String>();
              params.put("username", email);
              params.put("password", password);

              return params;
          }

      };

      // Adding request to request queue
      AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
  }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}




