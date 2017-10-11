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

    IPAddress address = new IPAddress();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ipAddress = (EditText) findViewById(R.id.ipAddress);
        Button next = (Button) findViewById(R.id.btnAdd);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ipAddress.setError(null);

                if (ipAddress.length() == 0) {
                    Toast.makeText(MainActivity.this, "Can't be empty", Toast.LENGTH_SHORT).show();
                    view = ipAddress;
                }
                else {
                    address = new IPAddress();
                    address.setIpAddress(ipAddress.getText().toString());
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
