package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    // declare variables for login widgets
    private String user;
    private String pass;
    EditText user1, pass1;

    public String getUser() {
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //assign textbox inputs to user and password variables
        user1 = (EditText) findViewById(R.id.edtxt_userID);
        pass1 = (EditText) findViewById(R.id.edtxt_password);

        // Debug, logcat calls to find value of button id and variable
        Log.d("User ID 1", String.valueOf(user1));
        Log.d("Password 1", String.valueOf(pass1));

        // call click listen method
        onClickListenerButton(null);

    }

// listener method for login, once
//   POST request needs to lie within listener method so credentials will be verified
//       when the login button clicked
        public void onClickListenerButton(View v) {


            // the string variable containing the query parameter for Drive Record (day, week, month)
            final String user = user1.getText().toString();
            final String pass = pass1.getText().toString();
            Log.d("UserID", user);         // debug to validate variable contents
            Log.d("Password", pass);

// code for POST - get radio button value for time (day, week or month) to NodeJS for MySQL
//    via api driveRecordTime, then get back query results
            // URL address for this api using my laptop IP address, with api call

            // need to define api url
            String URL4 = "http://192.168.1.69:3000/api/Login";

            // get requestQ object via volley
            RequestQueue requestQueue4 = Volley.newRequestQueue(this);

            StringRequest myStringRequest1 = new StringRequest(
                    Request.Method.POST,
                    URL4,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            // AS forces try-catch, need catch for null Array error
                            try {
                                // create json object from onResponse
                                final JSONObject obj = new JSONObject(response);
                                // JSON results in array, create array from JSON object
                                final JSONArray results = obj.getJSONArray("results");

                                //for (int i = 0; i < 2; i++)
                                {
                                    // create new object, get array results w/index 0
                                    JSONObject record = results.getJSONObject(0);

                                    String retEmail = null;
                                    String retPass = null;
                                   // int retDriverID = 0;

                                    retEmail = record.getString("email");
                                    retPass = record.getString("password");
                                   // retDriverID = record.getInt("driverID");

                                  //  RetrieveDriverName thisOne = new RetrieveDriverName();
                                   // thisOne.setDriverID(retDriverID);

                                   // retDriverID = record.getInt("driverID");

                                    // debug - ouptut return variables to logcat
                                    //Log.d("record", retEmail);
                                    //Log.d("record", retPass);

                                    // query is userID and password specific, if it returns a value, successful
                                    if(retEmail != null && retPass != null){

                                        // login successful
                                        // temp toast message, gravity is locator for toast message
                                        Toast toast = Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER, 0, 210);
                                        toast.show();

                                        //Intent intent = new Intent(this, MainActivity.class);
                                        //this.startActivity(intent);
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    }
                                }

                                // if credential query == null, unsuccessful
                            } catch (JSONException e) {
                                e.printStackTrace();

                                // login failed toast message
                                Toast toast = Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 210);
                                toast.show();

                                // clear out unsuccessful values from UserID and Password fields
                                user1.getText().clear();
                                pass1.getText().clear();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                // the parameters passed (POSTed)
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user", user);
                    params.put("pass", pass);
                    return params;
                }
            };

            // add requestQ object to the requestQ
            requestQueue4.add(myStringRequest1);

    }

}
