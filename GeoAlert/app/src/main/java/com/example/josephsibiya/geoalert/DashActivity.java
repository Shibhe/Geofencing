package com.example.josephsibiya.geoalert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.josephsibiya.geoalert.Adapters.LecturerAdapter;
import com.example.josephsibiya.geoalert.Adapters.StudentAdapter;
import com.example.josephsibiya.geoalert.Configuration.SQLiteHandler;
import com.example.josephsibiya.geoalert.Configuration.SessionManager;
import com.example.josephsibiya.geoalert.models.LecturerModel;
import com.example.josephsibiya.geoalert.models.StudentModel;

import java.util.HashMap;

public class DashActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;
    private LecturerModel model;
    private SessionManager session;
    private LecturerModel studentModel;
    private LecturerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //intent = getIntent();
        //String surname = intent.getStringExtra("surname");
        //final String initials = intent.getStringExtra("initials");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler

        // Displaying the user details on the screen
       // txtName.setText(studentModel.getSurname());
        //txtEmail.setText(studentModel.getInitials());

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //logoutUser();
                adapter.lecturerModels.clear();

                if (adapter.lecturerModels == null){
                    intent = new Intent(DashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navigation_near_by) {
            intent = new Intent(DashActivity.this, NearByActivity.class);
            startActivity(intent);
            return true;
        }
         else if (id == R.id.navigation_view_student) {
            intent = new Intent(DashActivity.this, StudentActivity.class);
            startActivity(intent);
            return  true;

        } else if (id == R.id.nav_more_app) {
            //More application
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id="+ BuildConfig.APPLICATION_ID));
            startActivity(intent);
            return true;

        } else if (id == R.id.navigation_manage_geo) {
            intent = new Intent(DashActivity.this, GeofenceActivity.class);
            startActivity(intent);
            return  true;

        } else if (id == R.id.nav_share) {

            //Share application application
            final String appPackageName = BuildConfig.APPLICATION_ID;
            final String appName = getString(R.string.app_name);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareBodyText = "https://play.google.com/store/apps/details?id=" +
                    appName;
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, appName);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(shareIntent, getString(R.string
                    .share_with)));

            return true;

        } else if (id == R.id.show_location) {
            intent = new Intent(DashActivity.this, ShowLocationsActivity.class);
            startActivity(intent);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    /**private void logoutUser() {
        session = new SessionManager(DashActivity.this);
        session.setLogin(false);

        // Launching the login activity
        Intent intent = new Intent(DashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }**/
}
