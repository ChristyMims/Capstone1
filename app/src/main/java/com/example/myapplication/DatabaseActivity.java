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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DatabaseActivity extends AppCompatActivity {

    private String period2;

    public String getPeriod2() {
        return period2;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Toolbar toolbar;
        toolbar = findViewById(R.id.myToolBar);

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
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // create TextView object for driver's name
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
                            // alternate way:    txt_driverName.setText(String.format("%s %s", fname, lname));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                // if error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST response", error.toString());
                    }
                }
        );
        // add to requestQ
        requestQueue.add(objectRequest);

/*
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
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response2) {
                        // create TextView object associated with Driver Record
                        TextView txt_drivRec = findViewById(R.id.txt_drivRec);

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
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REST response", error.toString());
                    }
                }

        );
        // add to requestQ
        requestQueue2.add(objectRequest2);
*/

// call radio button listener method to get Driver Record time variable
        onClickListenerButton(null);
        // test, get value from method assign to period2
        //period2 = onClickListenerButton(null);
        //Log.d("Rad Button var period2", period2);

    }


// listener method for radioGroup radio buttons
//   POST request needs to lie within listener method so Drive Record summary
//   will change every time a different radio button is selected
    public void onClickListenerButton(View v) {

        // radio group button selection
        // declare var radioTime, instance of radio group
        final RadioGroup radioTime = findViewById(R.id.rg_duration);

        // get int value of selected button in radio group
        int selectedButton = radioTime.getCheckedRadioButtonId();
        // declare var radioButton, instance of radio button, set = selected button
        RadioButton radioButton = (RadioButton) findViewById(selectedButton);

        // test textview id of txt_tommy to get and displany rad button value
        // TextView txt_tommy = (TextView) findViewById(R.id.txt_tommy);
        // radio button integer right now, have to set text and convert it to string
        // txt_tommy.setText(radioButton.getText());

        // temp toast message
        Toast.makeText(DatabaseActivity.this, radioButton.getText(), Toast.LENGTH_LONG).show();

        // Debug, logcat calls to find value of button id and variable
        //Log.d("Selected Button ID", String.format("value = %d", selectedButton));
        //Log.d("Rad Button var period", radioButton.getText().toString());

        // the string variable containing the query parameter for Drive Record (day, week, month)
        final String period = radioButton.getText().toString();
        Log.d("Rad Button var period", period);         // debug to validate variable contents

// code for POST - get radio button value for time (day, week or month) to NodeJS for MySQL
//    via api driveRecordTime, then get back query results
        // URL address for this api using my laptop IP address, with api call

        // need to define api url
        String URL3 = "http://192.168.1.69:3000/api/driveRecordTime";

        // get requestQ object via volley
        RequestQueue requestQueue3 = Volley.newRequestQueue(this);

        StringRequest myStringRequest = new StringRequest(
                Request.Method.POST,
                URL3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // create TextView object for Drive Record
                        TextView txt_drivRec = findViewById(R.id.txt_drivRec);
                        // declare variables needed for DriveRecord query/output
                        String service, trips, duration;
                        //String trips;
                        //String duration;
                        String service2, trips2, duration2;
                        //String trips2;
                        //String duration2;

                        // AS forces try-catch, need catch for null Array error
                        try {
                            // create json object from onResponse
                            final JSONObject obj = new JSONObject(response);
                            // JSON results in array, create array from JSON object
                            final JSONArray results = obj.getJSONArray("results");

                            //Log.d("null Array results", String.valueOf((results)));

                    // clunky I know, can't make listview or recyclerView work right
                    //    now, otherwise if you call TextView from within for loop, it will
                    //    over write results

                            //for (int i = 0; i < 2; i++)
                            {
                                // create new object, get array results w/index 0
                                JSONObject record = results.getJSONObject(0);

                                //Log.d("null object results", String.valueOf((record)));

                                // now that we have array object, get value based on their name
                                service = record.getString("service");
                                trips = record.getString("No_Trips");
                                duration = record.getString(("Duration"));
                                // create the json object for index 1
                                JSONObject record2 = results.getJSONObject(1);
                                // delcare variables, get their results from json array
                                service2 = record2.getString("service");
                                trips2 = record2.getString("No_Trips");
                                duration2 = record2.getString(("Duration"));

                                // since only two service providers, can use txt_drivRec textView to
                                //    display all variable results.  Not optimal, but works for now.
                                txt_drivRec.setText(service + getString(R.string.tab) + "  "
                                        + trips + getString(R.string.tab) + "    "
                                        + duration + "\n"
                                        + service2 + getString(R.string.tab) + "   "
                                        + trips2 + getString(R.string.tab) + "    "
                                        + duration2);

                                //txt_drivRec.setText("\n");
                                //service = results.getJSONObject(1)
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                            // for null array error, print default 0 trips, 00:00 time`
                            service = "Uber";
                            trips = "0";
                            duration = "00:00";
                            service2 = "Lyft";
                            trips2 = "0";
                            duration2 = "00:00";
                            txt_drivRec.setText(service + getString(R.string.tab) + "  "
                                    + trips + getString(R.string.tab) + "    "
                                    + duration + "\n"
                                    + service2 + getString(R.string.tab) + "   "
                                    + trips2 + getString(R.string.tab) + "    "
                                    + duration2);
                        }

                        //String service = response.toString();
                        //txt_drivRec.setText(service);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("time", period);
                return params;
            }
        };

        // add requestQ object to the requestQ
        requestQueue3.add(myStringRequest);

        //return period;
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
