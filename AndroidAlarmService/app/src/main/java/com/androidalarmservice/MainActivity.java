package com.androidalarmservice;

import java.util.Calendar;



import android.app.Activity;

import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Intent;

import android.os.Bundle;

import android.os.SystemClock;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
    private PendingIntent pendingIntent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStart = (Button) findViewById(R.id.startalarm);


        buttonStart.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View arg0) {

                // TODO Auto-generated method stub

                                               Intent myIntent = new Intent(MainActivity.this, MyAlarmService.class);

                TimePicker i= (TimePicker)findViewById(R.id.Min);
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY)*60;
                int minute = c.get(Calendar.MINUTE);
                int hour1= i.getCurrentHour()*60;
                int minute1=i.getCurrentMinute();
                int diff=(hour1+minute1)-(hour+minute);
                   pendingIntent = PendingIntent.getService(MainActivity.this, 0, myIntent, 0);


                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


                Calendar calendar = Calendar.getInstance();

                calendar.setTimeInMillis(System.currentTimeMillis());

                calendar.add(Calendar.MINUTE, diff);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                String y = Integer.toString(diff);
                Toast.makeText(MainActivity.this, y, Toast.LENGTH_LONG).show();

            }
        });



    }


}

