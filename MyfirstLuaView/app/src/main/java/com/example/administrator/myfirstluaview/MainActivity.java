package com.example.administrator.myfirstluaview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taobao.luaview.global.LuaView;

public class MainActivity extends Activity {

    private RelativeLayout mLuaViewContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LuaView view = LuaView.create(this);
        view.registerPanel(LuaUseNative.class);
        view.register("bridge", new LuaBridge(this));
        view.load("bridgeexample.lua");
        mLuaViewContainer = (RelativeLayout) findViewById(R.id.luaView);
        mLuaViewContainer.addView(view);

        final TextView text = (TextView) findViewById(R.id.textView);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(LuaBridge.getLuaData());
            }
        });

    }
}
