package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;


    // toggle objects, to return text for now
    ToggleActivity toggleU = new ToggleActivity("Offline");
    ToggleActivity toggleL = new ToggleActivity("Online");

    //String textUberStatus = toggleU.findUberStatus();
    //String textLyftStatus = toggleL.findLyftStatus();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // textview - retrieval of placeholder text from Toggle objects
        // Uber
        //setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.txt_UberStatus);
        textView.setText(toggleU.findUberStatus());
        // lyft
        TextView textView2 = (TextView) findViewById(R.id.txt_LyftStatus);
        textView2.setText(toggleL.findLyftStatus());


        // toolbar/menu options display
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);



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
                break;
            case R.id.action_records:
                Intent intent = new Intent(this, DatabaseActivity.class);
                this.startActivity(intent);
                break;
            case R.id.sign_out:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


}
