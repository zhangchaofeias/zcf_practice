package g7.zcf.amap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;

import java.text.SimpleDateFormat;

import g7.zcf.R;


public class LocationServiceActivity extends Activity implements View.OnClickListener, LocationSource, AMapLocationListener {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private MapView mapView;
    private AMap amap;
    private Button btn_normal, btn_satellite, btn_night, btn_navi;
    private Button btn_lukuang, btn_xuanzhuan, btn_stop;
    private boolean showTraffic = false;
    private CameraUpdateFactory cameraUpdate = new CameraUpdateFactory();
    float i = 0;
    private AMapLocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_service);

        initView();
        mapView.onCreate(savedInstanceState);
        initData();
        startLoaction();
    }

    private void initView() {
        mapView = (MapView) findViewById(R.id.mapView);
        btn_normal = (Button) findViewById(R.id.btn_normal);
        btn_normal.setOnClickListener(this);
        btn_satellite = (Button) findViewById(R.id.btn_satellite);
        btn_satellite.setOnClickListener(this);
        btn_night = (Button) findViewById(R.id.btn_night);
        btn_night.setOnClickListener(this);
        btn_navi = (Button) findViewById(R.id.btn_navi);
        btn_navi.setOnClickListener(this);

        btn_lukuang = (Button) findViewById(R.id.btn_lukuang);
        btn_lukuang.setOnClickListener(this);
        btn_xuanzhuan = (Button) findViewById(R.id.btn_xuanzhuan);
        btn_xuanzhuan.setOnClickListener(this);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(this);
    }

    private void initData() {
        amap = mapView.getMap();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal:
                amap.setMapType(AMap.MAP_TYPE_NORMAL);
                break;
            case R.id.btn_satellite:
                amap.setMapType(AMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.btn_night:
                amap.setMapType(AMap.MAP_TYPE_NIGHT);
                break;
            case R.id.btn_navi:
                amap.setMapType(AMap.MAP_TYPE_NAVI);
                break;
            case R.id.btn_lukuang:
                showOrNotShowTraffic();
                break;
            case R.id.btn_xuanzhuan:
                xuanzhuanMap();
                break;
            case R.id.btn_stop:
                locationClient.stopLocation();
                break;
        }

    }

    private void xuanzhuanMap() {
        amap.setPointToCenter(0, 0);
        amap.animateCamera(cameraUpdate.changeBearing(90), new AMap.CancelableCallback() {
            @Override
            public void onFinish() {
                Toast.makeText(LocationServiceActivity.this, "onFinish()", Toast.LENGTH_SHORT).show();
                amap.setPointToCenter(0, 0);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LocationServiceActivity.this, "onCancel()", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void startLoaction() {
        locationClient = new AMapLocationClient(getApplicationContext());//新建定位客户端，
        locationClient.setLocationListener(this);
        AMapLocationClientOption option = new AMapLocationClientOption();//定位服务的参数，设置间隔，模式什么的
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setNeedAddress(true);
        option.setInterval(5000);
        locationClient.setLocationOption(option);
        //locationClient.startLocation();//启动服务
    }


    private void showOrNotShowTraffic() {
        if (!showTraffic) {
            amap.setTrafficEnabled(true);
            showTraffic = true;
        } else {
            amap.setTrafficEnabled(false);
            showTraffic = false;
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }
}
