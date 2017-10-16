package com.example.josephsibiya.geoalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.josephsibiya.geoalert.connection.IPAddress;

public class MainActivity extends AppCompatActivity {

    private EditText ipAddress;
    private Button submit;
    private IPAddress setIPAddress ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipAddress = (EditText) findViewById(R.id.ipAddress);

        submit = (Button) findViewById(R.id.btnIpAddress);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipAddress.setError(null);

                if (ipAddress.length() == 0 && ipAddress.isFocused()  || !ipAddress.getText().toString().contains(".")) {
                    Toast.makeText(MainActivity.this, "IP address required or invalid IP address", Toast.LENGTH_SHORT).show();
                    view = ipAddress;
                }

                else{
                    setIPAddress = new IPAddress();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    setIPAddress.setIpAddress(ipAddress.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }
}
