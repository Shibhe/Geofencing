package com.example.josephsibiya.geoalert;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josephsibiya.geoalert.Adapters.LecturerAdapter;
import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.Configuration.CustomHttpClient;
import com.example.josephsibiya.geoalert.Configuration.SessionManager;
import com.example.josephsibiya.geoalert.connection.IPAddress;
import com.example.josephsibiya.geoalert.connection.internetConn;
import com.example.josephsibiya.geoalert.models.LecturerModel;
import com.example.josephsibiya.geoalert.providers.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.email_sign_in_button);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnPswReset = (Button) findViewById(R.id.btn_reset_password);


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
                        validateUserTask task = new validateUserTask();
                        task.execute(username.getText().toString(), password.getText().toString());
                    }
                }
            });
    }


  String response = null;

  private class validateUserTask extends AsyncTask<String, Void, String> {
      @Override
      protected String doInBackground(String... params) {
          // TODO Auto-generated method stub
          ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
          postParameters.add(new BasicNameValuePair("username", params[0] ));
          postParameters.add(new BasicNameValuePair("password", params[1] ));
          String res = null;
          try {
              response = CustomHttpClient.executeHttpPost("http://geoalert.000webhostapp.com/login.php", postParameters);
              res = response;
              res= res.replaceAll("\\s+","");
          }
          catch (Exception e) {
              //txt_Error.setText(e.toString());
              Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
          }
          return res;
      }//close doInBackground

      @Override
      protected void onPreExecute() {
          super.onPreExecute();

          progressDialog = new ProgressDialog(LoginActivity.this);
          progressDialog.setMessage("Logging in....");
          progressDialog.setIndeterminate(false);
          progressDialog.setTitle("Login status");
          progressDialog.show();
      }

      @Override
      protected void onPostExecute(String result) {
          if(result.length() > 1){
              //navigate to Main Menu
              progressDialog.dismiss();
              Intent i = new Intent(LoginActivity.this, DashActivity.class);
              i.putExtra("username", result);
              startActivity(i);
          }
          else{
              Toast.makeText(LoginActivity.this, "Sorry!! Incorrect Username or Password", Toast.LENGTH_SHORT).show();
          }
      }//close onPostExecute
  }// close validateUserTask
}//close LoginActivity
