package com.example.huoyunren.popwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ALifeCycle extends Activity {

    public static final String TAG = "ZCF_MainActivity";
    private Button caluteBtn, showBtn;
    private EditText input;
    private TextView showInput, system, date, calendar;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ALifeCycle.this, ALifeCycleTwo.class));
            }
        });
        initView();
        caluteBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                try {
                    cal.setTime(format.parse(input.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                showInput.setText(cal.getTimeInMillis() + "");
            }
        });

        showBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                system.setText(System.currentTimeMillis() + "");
                date.setText(new Date().getTime() + "");
                calendar.setText(Calendar.getInstance().getTimeInMillis() + "");
            }
        });
    }

    private void initView() {
        caluteBtn = (Button) findViewById(R.id.caluatebtn);
        showBtn = (Button) findViewById(R.id.showBtn);
        input = (EditText) findViewById(R.id.input);
        showInput = (TextView) findViewById(R.id.showInput);
        system = (TextView) findViewById(R.id.system);
        date = (TextView) findViewById(R.id.date);
        calendar = (TextView) findViewById(R.id.calendar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState()");
    }
}