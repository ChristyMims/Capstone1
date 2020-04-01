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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

/*
    // toggle objects, to return text for now
    ToggleActivity toggleU = new ToggleActivity("Offline");
    ToggleActivity toggleL = new ToggleActivity("Online");

    String textUberStatus = toggleU.findUberStatus();
    String textLyftStatus = toggleL.findLyftStatus();
*/

/*
    //@Override
    private android.widget.TextView findViewbyId(int txt_UberStatus) {

        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewbyId(R.id.txt_UberStatus);
        textView.setText(toggleU.getUberStatus());
        return textView;
    }
*/



}
