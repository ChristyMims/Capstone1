package com.example.myapplication;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class RetrieveDriverName {

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;

    // url to get all products list
    private static String url_all_products = "https://api.androidhive.info/android_connect/get_all_products.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_DRIVER = "driver";
    private static final String TAG_ID = "id";
    private static final String TAG_FNAME = "fname";
    private static final String TAG_LNAME = "lname";

    // products JSONArray
    JSONArray products = null;






}
