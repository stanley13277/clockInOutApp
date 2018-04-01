package com.example.clock.clockin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DebugActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedpreds";
    public static final String TEXT = "text";
    public static final String CLOCLINTIME = "clickintime";
    public static final String CLOCLOUTTIME = "clickouttime";
    public static final String TOTALWORKINGHOURS = "totalworkinghours";
    public static final String STARTBREAKTIME = "startbreaktime";
    public static final String ENDBREAKTIME = "endbreaktime";
    public static final String TEMPBREAKTIME = "tempbreaktime";
    public static final float TOTALBREAKTIME = 0.00f;
    public static final int NUMBEROFBREAK = 0;
    private String text = "original";
    String clockInTime = null;
    String clockOutTime = null;
    String totalWorkingHours = null;
    String startBreakTime = null;
    String endBreakTime = null;
    String tempBreakTime = null;
    float totalBreakTime = 0.00f;
    int numberOfBreak = 0;

    public void save(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT,"saved data");
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT,"empty");

    }

    public void clearData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT,"");
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /////below are for debug
        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        Button buttonDisplay = (Button) findViewById(R.id.buttonDebugDisplay);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                loadData();
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

            }
        });



        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
                Toast.makeText(getApplicationContext(), "clear", Toast.LENGTH_SHORT).show();
            }
        });
        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
