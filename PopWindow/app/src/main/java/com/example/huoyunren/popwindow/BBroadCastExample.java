package com.example.huoyunren.popwindow;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BBroadCastExample extends AppCompatActivity {

    private LocalBroadcastManager manager;
    private myReceiver mReceiver = new myReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_example);
        manager =LocalBroadcastManager.getInstance(BBroadCastExample.this);
        registBrocast();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadCast();
            }
        });
    }

    private void registBrocast() {
        IntentFilter filter = new IntentFilter("ZCF_BROADCAST");
        manager.registerReceiver(mReceiver, filter);
    }

    private void sendBroadCast() {
        Intent intent = new Intent("ZCF_BROADCAST");
        manager.sendBroadcast(intent);
    }

    private class myReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(BBroadCastExample.this, "receiver broadcast", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(mReceiver);
    }
}
