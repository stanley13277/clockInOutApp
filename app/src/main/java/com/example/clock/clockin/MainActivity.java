package com.example.clock.clockin;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String clockInTime = null;
    String clockOutTime = null;
    String totalWorkingHours = null;
    String startBreakTime = null;
    String endBreakTime = null;
    String tempBreakTime = null;
    float totalBreakTime = 0.00f;
    int numberOfBreak = 0;

    public String getTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat n = new SimpleDateFormat("HH:mm");
        String time =  n.format(calendar.getTime()).toString();
        return time;
    }

    public String getDate(){
        String out = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        out = formatter.format(date);
        return out;
    }

    public String gethrs(String startTime, String endTime) {
        float startMins = minsConverter(startTime);
        float endMins = minsConverter(endTime);
        float diff = endMins -startMins;
        float totalhrs = diff/60;
        DecimalFormat df = new DecimalFormat("#.00");
        String out = df.format(totalhrs);
       return out;
    }

    public int minsConverter(String s){
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        return hoursInMins + mins;
    }

    public String emailContent(){
        String out = null;
        DecimalFormat df = new DecimalFormat("#.00");
        String finalWorkingHours = df.format(Float.parseFloat(gethrs(clockInTime,clockOutTime))-totalBreakTime);
        out = "Clock In: "+ clockInTime + "\n" + "Clock Out: " + clockOutTime + "\n" + "Start Break: : "+ startBreakTime + "\n" + "End Break: " + endBreakTime + "\n"+"Total Break time(hrs): " + totalBreakTime + "\n" + "Total Working Hours: "
                + finalWorkingHours + "\n" + "Total Working hours without - break: " + totalWorkingHours;
        return out;
    }



    ///////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textView
        final TextView clockInText = (TextView) findViewById(R.id.clockInText);
        final TextView clockOutText = (TextView) findViewById(R.id.clockOutText);
        final TextView totalWorkHrsText = (TextView) findViewById(R.id.totalHoursText);
        final TextView startBreakText = (TextView) findViewById(R.id.startBreakText);
        final TextView endBreakText = (TextView) findViewById(R.id.endBreakText);

        //sent button
        Button sent = (Button) findViewById(R.id.Sent);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toThisEmail = "stanley13277@gmail.com";
                String subject = "Soul origin " + getDate();
                String message = emailContent();
                Log.d("Debug","test");
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[] {toThisEmail});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"Choose app to sent mail"));
                clockInText.setText("");
                clockInText.setHint("Finished work today");
                clockOutText.setText("");
                clockOutText.setHint("Finished work today");
            }
        });

        //clockin button
        Button clockIn = (Button) findViewById(R.id.clockIn);
        clockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clockInText.setText(getTime());
                clockInTime = getTime();
                Toast.makeText(getApplicationContext(), "Clock in at: " + getTime(), Toast.LENGTH_SHORT).show();
            }
        });


        //clockout button
        Button clockOut = (Button) findViewById(R.id.clockOut);
        clockOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clockOutText.setText(getTime());
                clockOutTime = getTime();
                totalWorkHrsText.setText(gethrs(clockInTime,clockOutTime));
                totalWorkingHours = gethrs(clockInTime,clockOutTime);
                Toast.makeText(getApplicationContext(), "Total Working hrs:  " + gethrs(clockInTime,clockOutTime)+"hrs", Toast.LENGTH_SHORT).show();

            }
        });

        //startbreak button
        Button startBreak = (Button) findViewById(R.id.startBreak);
        startBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBreakTime = getTime();
                startBreakText.setText(getTime());
                Toast.makeText(getApplicationContext(), "Start Break at: " + getTime(), Toast.LENGTH_SHORT).show();
            }
        });

        //endBreak button
        Button endBreak = (Button) findViewById(R.id.endBreak);
        endBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endBreakTime = getTime();
                endBreakText.setText(getTime());
                numberOfBreak = numberOfBreak+1;
                tempBreakTime = gethrs(startBreakTime,endBreakTime);
                totalBreakTime = totalBreakTime+ Float.parseFloat(tempBreakTime);
                Toast.makeText(getApplicationContext(), "Having Break for: " + gethrs(startBreakTime,endBreakTime) + "hrs", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
