package com.example.huoyunren.popwindow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mListView = (ListView) findViewById(R.id.listView);
        initData();
        mListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, list));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, ALifeCycle.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, BBroadCastExample.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, CServiceExample.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    private void initData() {
        list.add("活动");
        list.add("广播");
        list.add("服务");
    }
}
