package g7.zcf.amap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.CoordinateConverter.CoordType;
import com.amap.api.location.DPoint;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.overlay.DrivingRouteOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;
import com.amap.api.services.route.RouteSearch.FromAndTo;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;
import com.amap.api.trace.TraceOverlay;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import g7.zcf.R;

public class Mylocation extends Activity implements LocationSource, AMapLocationListener,
        View.OnClickListener, RadioGroup.OnCheckedChangeListener, OnRouteSearchListener, TraceListener {
    private MapView mapView;
    private AMap aMap;
    private OnLocationChangedListener locationChangedListener;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationClientOption;
    private AMapLocation myLocation;
    private Button btnMyLocation;
    private RadioGroup radioGroup;
    private DriveRouteResult mDriveRouteResult;
    private LatLng GDLatLng1;
    private LatLng GDLatLng2;
    private Context mContext;
    private LBSTraceClient mTraceClient;
    private List<TraceLocation> mTraceList;
    private int mLineId = 10;
    private ConcurrentMap<Integer, TraceOverlay> mOverlayList = new ConcurrentHashMap<Integer, TraceOverlay>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylocation);
        mapView = (MapView) findViewById(R.id.gaodeMapView);
        mapView.onCreate(savedInstanceState);//此方法必须重写
        aMap = mapView.getMap();//从控件里获取到地图实体
        aMap.setLocationSource(this);//设置定位监听
        aMap.setMyLocationEnabled(true);//设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        mContext = getApplicationContext();
        initUMShare();
        intiView();

    }

    private void initUMShare() {
        //微信 appid appsecret
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }

    private void intiView() {
        initMyLocationStyle();//自定义我的位置的图标样式
        aMap.getUiSettings().setZoomControlsEnabled(false);//层级按钮是否显示
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);//设置定位模式
        btnMyLocation = (Button) findViewById(R.id.myLocationButton);
        btnMyLocation.setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        findViewById(R.id.UMShare).setOnClickListener(this);
        findViewById(R.id.showLine).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               showLine();
            }
        });
        findViewById(R.id.jiupian).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GJJP();
            }
        });

    }

    private void GJJP() {
        intiData();
        TraceOverlay mTraceOverlay = new TraceOverlay(mapView.getMap());
        mOverlayList.put(mLineId, mTraceOverlay);
        List<LatLng> mapList = traceLocationToMap(mTraceList);
        mTraceOverlay.setProperCamera(mapList);
        mTraceClient = new LBSTraceClient(mContext);
        mTraceClient.queryProcessedTrace(mLineId, mTraceList, LBSTraceClient.TYPE_BAIDU, this);
    }

    private void intiData() {
        mTraceList = new ArrayList<>();
        TraceLocation traceLocation0 = new TraceLocation();
        traceLocation0.setLatitude(30.552024);
        traceLocation0.setLongitude(104.052729);
        traceLocation0.setTime(1427646660);
        TraceLocation traceLocation1 = new TraceLocation();
        traceLocation1.setLatitude(30.552988);
        traceLocation1.setLongitude(104.052693);
        traceLocation1.setTime(1427646660);
        TraceLocation traceLocation2 = new TraceLocation();
        traceLocation2.setLatitude(30.552957);
        traceLocation2.setLongitude(104.054382);
        traceLocation2.setTime(1427647260);

        mTraceList.add(traceLocation0);
        mTraceList.add(traceLocation1);
        mTraceList.add(traceLocation2);
    }

    /**
     * 轨迹纠偏点转换为地图LatLng
     *
     * @param traceLocationList
     * @return
     */
    public List<LatLng> traceLocationToMap(List<TraceLocation> traceLocationList) {
        List<LatLng> mapList = new ArrayList<LatLng>();
        for (TraceLocation location : traceLocationList) {
            LatLng latlng = new LatLng(location.getLatitude(),
                    location.getLongitude());
            mapList.add(latlng);
        }
        return mapList;
    }

    private void showLine() {
        DPoint BDLatLng1 = new DPoint(30.54851, 104.052783);
        DPoint BDLatLng2 = new DPoint(30.55742, 104.075025);
        DPoint dPoint1 = null;
        DPoint dPoint2 = null;
        CoordinateConverter converter = new CoordinateConverter(Mylocation.this);
        converter.from(CoordType.BAIDU);
        try {
            converter.coord(BDLatLng1);
            dPoint1 = converter.convert();
            converter.coord(BDLatLng2);
            dPoint2 = converter.convert();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<LatLng> latLngList = new ArrayList<>();
        GDLatLng1 = new LatLng(dPoint1.getLatitude(), dPoint1.getLongitude());
        GDLatLng2 = new LatLng(dPoint2.getLatitude(), dPoint2.getLongitude());
        driverLine();
    }

    private void driverLine() {
        RouteSearch search = new RouteSearch(Mylocation.this);
        search.setRouteSearchListener(this);
        FromAndTo fromAndTo = new FromAndTo(new LatLonPoint(GDLatLng1.latitude, GDLatLng1.longitude),
                new LatLonPoint(GDLatLng2.latitude, GDLatLng2.longitude));
        DriveRouteQuery query = new DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault, null, null, "");
        search.calculateDriveRouteAsyn(query);
    }

    private void initMyLocationStyle() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_mylocation)));
        myLocationStyle.strokeColor(Color.GREEN);
        myLocationStyle.strokeWidth(10);
        aMap.setMyLocationStyle(myLocationStyle);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        locationChangedListener = onLocationChangedListener;//把监听到变化传递出去，以便在onLocationChanged中使用
        if (locationClient == null) {
            locationClient = new AMapLocationClient(this);
            locationClient.setLocationListener(this);//设置位置变化监听
            locationClientOption = new AMapLocationClientOption();
            locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            locationClient.setLocationOption(locationClientOption);
            locationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        locationChangedListener = null;
        if (locationClient != null) {
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        myLocation = aMapLocation;
        if (locationChangedListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                locationChangedListener.onLocationChanged(aMapLocation);// 这个是最重要的，显示系统小蓝点
                //locationsource中的activate()方法中传进来的参数跟AMapLocationListener中监听到的aMapLocation位置信息
                //结合，在当前位置显示系统小蓝点
            } else {
                Toast.makeText(Mylocation.this, "location failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    //必须重写
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    //必须重写
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    @Override
    //必须重写
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (locationClient != null) {
            locationClient.onDestroy();
        }
    }

    @Override
    //必须重写
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myLocationButton:
                if (myLocation != null) {
                    LatLng myLocationLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(myLocationLatLng));
                }
                break;
            case R.id.UMShare:
                UMShare();
                break;
        }
    }

    private void UMShare() {
        Log.LOG = false;
        UMShareListener umShareListener = new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA share_media) {
                Toast.makeText(Mylocation.this, "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                Toast.makeText(Mylocation.this, "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                Toast.makeText(Mylocation.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        };
        UMImage image = new UMImage(Mylocation.this, BitmapFactory.decodeResource(getResources(),
                R.drawable.zcf_icon));
        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
                        SHARE_MEDIA.QQ
                };
        new ShareAction(this).setDisplayList(displaylist)
                .withText("呵呵")
                .withTitle("title")
                .withTargetUrl("http://www.baidu.com")
                .withMedia(image)
                .setListenerList(umShareListener)
                .open();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btn_dingwei:
                aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
                break;
            case R.id.btn_zhuisui:
                aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
                break;
            case R.id.btn_xuanzhuan:
                aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        aMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == 1000) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mDriveRouteResult = result;
                    final DrivePath drivePath = mDriveRouteResult.getPaths()
                            .get(0);
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                            mContext, aMap, drivePath,
                            mDriveRouteResult.getStartPos(),
                            mDriveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(true);//设置节点marker是否显示
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();

                } else if (result != null && result.getPaths() == null) {
                    Toast.makeText(Mylocation.this, "对不起，没有搜索到相关数据！", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(Mylocation.this, "对不起，没有搜索到相关数据！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Mylocation.this, errorCode, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    @Override
    public void onRequestFailed(int i, String s) {
        Toast.makeText(mContext, "轨迹纠偏失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTraceProcessing(int i, int i1, List<LatLng> list) {
        if (list == null) {
            return;
        }
        if (mOverlayList.containsKey(i)) {
            Toast.makeText(mContext, "onTraceProcessing", Toast.LENGTH_SHORT).show();
            Toast.makeText(mContext, "onTraceProcessing,list.size()"+ list.size(), Toast.LENGTH_SHORT).show();
            TraceOverlay overlay = mOverlayList.get(i);
            overlay.setTraceStatus(TraceOverlay.TRACE_STATUS_PROCESSING);
            overlay.add(list);
        }
    }

    @Override
    public void onFinished(int i, List<LatLng> list, int i1, int i2) {
        if (mOverlayList.containsKey(i)) {
            Toast.makeText(mContext, "已获取轨迹纠偏数据list", Toast.LENGTH_SHORT).show();
            Toast.makeText(mContext, "list.size():"+list.size(), Toast.LENGTH_SHORT).show();
            TraceOverlay overlay = mOverlayList.get(i);
            overlay.setTraceStatus(TraceOverlay.TRACE_STATUS_FINISH);
        }
    }
}
