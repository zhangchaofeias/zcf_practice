package g7.zcf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import g7.zcf.Fragment.FragmentDemoActivity;
import g7.zcf.amap.LocationServiceActivity;
import g7.zcf.mpchart.MPChartActivity;
import g7.zcf.phoneconnnect.PhoneContactActivity;
import g7.zcf.popwindow.PopWindowActivity;

public class MainActivity extends Activity {

    private List<String> mListData = new ArrayList<>();

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        initData();
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mListData));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, LocationServiceActivity.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, MPChartActivity.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, PopWindowActivity.class);
                        break;
                    case 3:
                        intent.setClass(MainActivity.this, PhoneContactActivity.class);
                        break;
                    case 4:
                        intent.setClass(MainActivity.this, FragmentDemoActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    private void initData() {
        mListData.add("高德地图");
        mListData.add("MP图表");
        mListData.add("POPWINDOW");
        mListData.add("手机联系人");
        mListData.add("Fragment");
    }
}
