package com.example.huoyunren.popwindow;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CServiceExample extends AppCompatActivity implements View.OnClickListener {

    private ServiceConnection mServiceConnection;
    private EditText inputName, inputSex;
    private TextView showName, showSex;
    private CMyService.StudentBinder student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_example);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                student = (CMyService.StudentBinder) service;
                if (student != null) {
                    showName.setText(student.getName());
                    showSex.setText(student.getSex());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        inputName = (EditText) findViewById(R.id.input_name);
        inputSex = (EditText) findViewById(R.id.input_sex);
        showName = (TextView) findViewById(R.id.show_name);
        showSex = (TextView) findViewById(R.id.show_sex);
        findViewById(R.id.start_service).setOnClickListener(this);
        findViewById(R.id.stop_service).setOnClickListener(this);
        findViewById(R.id.bind_service).setOnClickListener(this);
        findViewById(R.id.unbind_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CServiceExample.this, CMyService.class);
        switch (v.getId()) {
            case R.id.start_service:
                intent.putExtra(CMyService.KEY_NAME, inputName.getText().toString());
                intent.putExtra(CMyService.KEY_SEX, inputSex.getText().toString());
                intent.putExtra(CMyService.KEY_NAME_CHANGED, "gaofengxia");
                intent.putExtra(CMyService.KEY_SEX_CHANGED, "femail");
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
            case R.id.bind_service:
                intent.putExtra(CMyService.KEY_NAME, inputName.getText().toString());
                intent.putExtra(CMyService.KEY_SEX, inputSex.getText().toString());
                intent.putExtra(CMyService.KEY_NAME_CHANGED, "gaofengxia");
                intent.putExtra(CMyService.KEY_SEX_CHANGED, "femail");
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(mServiceConnection);
                showName.setText("");
                showSex.setText("");
                break;
        }

    }
}
