package com.example.josephsibiya.geoalert;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.josephsibiya.geoalert.Adapters.GeofenceAdapter;
import com.example.josephsibiya.geoalert.Adapters.StudentAdapter;
import com.example.josephsibiya.geoalert.Configuration.ConfigClass;
import com.example.josephsibiya.geoalert.Configuration.JSONParser;
import com.example.josephsibiya.geoalert.models.StudentModel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    String pid;

    // Progress Dialog
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private ArrayList<StudentModel> studentModels = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button addStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        addStudent = (Button) findViewById(R.id.addStudent);

        recyclerView = (RecyclerView) findViewById(R.id.rvStudent);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        recyclerView = (RecyclerView) findViewById(R.id.rvStudent);

        studentModels = new ArrayList<>();
        studentAdapter = new StudentAdapter(StudentActivity.this, studentModels);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       /** swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);

                studentModels = new ArrayList<>();
                studentAdapter = new StudentAdapter(StudentActivity.this, studentModels);
                recyclerView.setAdapter(studentAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(StudentActivity.this));


                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(CreateHelperCallBack());
                itemTouchHelper.attachToRecyclerView(recyclerView);
            }
        });**/

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });


        new GetAllStudent(studentAdapter, StudentActivity.this).execute();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(CreateHelperCallBack());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private ItemTouchHelper.Callback CreateHelperCallBack()
    {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
                ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                deleteItem(viewHolder.getAdapterPosition());

            }
        };
        return simpleCallback;
    }

    private void deleteItem(int pos)
    {
        studentAdapter.numStudents.remove(pos);
        new DeleteStudent().execute();
        studentAdapter.notifyDataSetChanged();
    }


    private void moveItem(int pos, int newPos)
    {
        StudentModel studentModel = studentAdapter.numStudents.get(pos);
        //geofenceAdapter.locationsArrayList.get(pos);
        studentAdapter.numStudents.remove(pos);
        studentAdapter.numStudents.add(newPos, studentModel);
        studentAdapter.notifyItemMoved(pos, newPos);
    }

    public class GetAllStudent extends AsyncTask<Void, Void, Void> {

        ConfigClass config  = new ConfigClass();
        private StudentAdapter adapter;
        private Context context;
        private ProgressDialog pDialog;

        public GetAllStudent( StudentAdapter adapter, Context context) {
            this.adapter = adapter;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (pDialog == null){
                Toast.makeText(StudentActivity.this, "No results", Toast.LENGTH_LONG).show();
            }
            else {
                pDialog = new ProgressDialog(context);
                pDialog.setMessage("Loading students. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;


            try {
                URL loginUrl = new URL(config.URL_LISTSTU);
                urlConnection = (HttpURLConnection) loginUrl.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-Auth-Token", "1ef07188cb3a49c48ea1ce543a8b8212");
                urlConnection.connect();
                InputStream stream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }

                while (buffer.length() == 0) {
                    return null;
                }


                JSONObject object = new JSONObject(buffer.toString());

                JSONArray array = (JSONArray) object.get("tblStudent");

                for (int x = 0; x < array.length(); x++) {

                    JSONObject compObj = (JSONObject) array.get(x);

                    int Id;
                    String surname;
                    String initials;
                    String studNum;
                    String email;
                    String gender;
                    String IDNo;


                    Id = compObj.getInt("id");
                    surname = compObj.getString("surname");
                    initials = compObj.getString("initials");
                    email = compObj.getString("email");
                    gender = compObj.getString("gender");
                    studNum = compObj.getString("studNum");
                    IDNo = compObj.getString("IDNo");


                    StudentModel model = new StudentModel();

                    model.setId(Id);
                    model.setGender(gender);
                    model.setEmail(email);
                    model.setIDNo(IDNo);
                    model.setInitials(initials);
                    model.setStudNum(studNum);
                    model.setSurname(surname);

                    adapter.numStudents.add(model);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();
        }
    }

    public class DeleteStudent extends AsyncTask<String, String, String> {

        private Context context;
        private ArrayList<StudentModel> models;
        private ProgressDialog pDialog;
        private ConfigClass aClass;

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(StudentActivity.this);
            pDialog.setMessage("Deleting Student...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Deleting product
         * */
        protected String doInBackground(String... args) {

            // Check for success tag
            int success;
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("id", pid));

                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                        aClass.URL_DELETESTU, "POST", params);

                // check your log for json response
                Log.d("Delete Product", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    // product successfully deleted
                    // notify previous activity by sending code 100
                    Intent i = getIntent();
                    // send result code 100 to notify about product deletion
                    setResult(100, i);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();

        }

    }

}
