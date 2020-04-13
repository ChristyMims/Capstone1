package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DatabaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        toolbar=findViewById(R.id.myToolBar);

        setSupportActionBar(toolbar);

        // code block to get api drivers/4  - the display driver query
        // URL address, using my laptop IP address, with api call
        String URL = "http://192.168.1.69:3000/api/drivers/4";

        // get requestQ object
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // construct actual request, want a JSON object
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                // endpoint
                URL,
                null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        // create TextView object for driver
                        TextView txt_driverName = findViewById(R.id.txt_driverName);
                        //Log.e("REST response", response.toString());
                        //textView2.setText("REST response", response.toString());
                        //txt_driverName.setText(response.toString());

                        // parse the JSON object response
                        // response JSON ojbect:  "results": "fname","Tabitha","lname","Fleetwood"
                        // results are in an Array []

                        // AS forces the try catch protection ...
                        try {
                            // already have object named response that got text string from MySQL
                            // create JSON array node
                            JSONArray results = response.getJSONArray("results");

                                // create JSON object that gets text from response string
                                JSONObject q = results.getJSONObject(0);
                                // get first and last name
                                String fname = q.getString("fname");
                                String lname = q.getString("lname");

                                // output results to txtView box
                                txt_driverName.setText(fname + " " + lname);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                // if error
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("REST response", error.toString());
                    }
                }
        );
        // add to requestQ
        requestQueue.add(objectRequest);


        // code block to get api drivers/record/4 - the driver's record query
        // URL address for this api using my laptop IP address, with api call
        String URL2 = "http://192.168.1.69:3000/api/drivers/record/4";

        // get requestQ object
        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        // construct actual request, want a JSON object
        JsonObjectRequest objectRequest2 = new JsonObjectRequest(
                Request.Method.GET,
                // endpoint
                URL2,
                null,
                new Response.Listener<JSONObject>()
                {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response2)
                    {
                        // create TextView object associated with Driver Record
                        TextView txt_drivRec = findViewById(R.id.txt_drivRec);
                        //Log.e("REST response", response.toString());
                        //textView2.setText("REST response", response.toString());
                        //txt_driverName.setText(response.toString());

                        // parse the JSON object response
                        // response JSON ojbect:  "results": "service","Uber","No_Trips","2", "Duration", "00:01:00"
                        // results is in an Array [] (see console.log(results) output)

                        // AS forces the try catch response ...
                        try {
                            // already have object named response2 that got text string from MySQL
                            // create JSON array node
                            JSONArray results2 = response2.getJSONArray("results");

                            // two elements/lines in array, for loop of 2
                            //for (int i = 0; i < 2; i++)
                            {
                                // create JSON object that gets text from response string
                                JSONObject q2 = results2.getJSONObject(0);
                                // get driver record string variables
                                String service = q2.getString("service");
                                String No_Trips = q2.getString("No_Trips");
                                String Duration = q2.getString("Duration");

                                // output results to txtView box
                                //txt_drivRec.setText(service + "       " + No_Trips + "        " + Duration);

                                // create JSON object that gets text from response string
                                JSONObject q3 = results2.getJSONObject(1);
                                // get driver record string variables
                                String service2 = q3.getString("service");
                                String No_Trips2 = q3.getString("No_Trips");
                                String Duration2 = q3.getString("Duration");

                                // output results to txtView box
                                // clunky, but could not figure out for loop, kept writing over line 1
                                //    set lines to 2 and min lines to 2
                                txt_drivRec.setText(service + "       "
                                        + No_Trips + getString(R.string.tab) + "    "
                                        + Duration + "\n"
                                        + service2 + getString(R.string.tab) + "    "
                                        + No_Trips2 + getString(R.string.tab) + "    "
                                        + Duration2);
                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                // if error
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("REST response", error.toString());
                    }
                }

        );
        // add to requestQ
        requestQueue2.add(objectRequest2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // Added to handle action from overflow menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            case R.id.action_records:
                break;
            case R.id.sign_out:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    // new method for Done button to return Home on click
    //   create this method, then go to .xml button attributes, On Click, select this method
    public void buttonOnClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }




}
