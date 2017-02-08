package com.example.administrator.customviewdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {

    private ContralVolView mContralVolView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContralVolView = (ContralVolView) findViewById(R.id.contralVolView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            mContralVolView.update(false);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            mContralVolView.update(true);
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }
}
