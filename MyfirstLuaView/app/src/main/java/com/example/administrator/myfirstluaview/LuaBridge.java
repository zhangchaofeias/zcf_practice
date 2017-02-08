package com.example.administrator.myfirstluaview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class LuaBridge {
    private Activity mActivity;
    private static String mLuaData;

    public LuaBridge(Activity activity) {
        mActivity = activity;
    }

    public void showLuaData(String str) {
        setLuaData(str);
    }

    public String getTextString() {
        return "data from native";
    }

    public static String getLuaData() {
        return mLuaData;
    }

    public void setLuaData(String luaData) {
        mLuaData = luaData;
    }
}
