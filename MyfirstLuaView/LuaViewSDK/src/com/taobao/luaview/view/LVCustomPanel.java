package com.taobao.luaview.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.taobao.luaview.userdata.ui.UDCustomPanel;
import com.taobao.luaview.userdata.ui.UDView;
import com.taobao.luaview.userdata.ui.UDViewGroup;
import com.taobao.luaview.util.LuaUtil;
import com.taobao.luaview.util.LuaViewUtil;
import com.taobao.luaview.view.interfaces.ILVNativeViewProvider;
import com.taobao.luaview.view.interfaces.ILVViewGroup;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

/**
 * LuaView-Container
 * 容器类
 *
 * @author song
 * @date 15/8/20
 */
public abstract class LVCustomPanel extends LVViewGroup implements ILVViewGroup, ILVNativeViewProvider {

    public LVCustomPanel(Globals globals, LuaValue metaTable, Varargs varargs) {
        super(globals, metaTable, varargs);
        initPanel();
    }

    @NonNull
    public UDViewGroup createUserdata(Globals globals, LuaValue metaTable, Varargs varargs) {
        return new UDCustomPanel(this, globals, metaTable, varargs);
    }


    @Override
    public void addLVView(final View view, Varargs a) {
        if(this != view) {
            final ViewGroup.LayoutParams layoutParams = LuaViewUtil.getOrCreateLayoutParams(view);
            super.addView(LuaViewUtil.removeFromParent(view), layoutParams);
        }
    }

    public void show() {
        LVCustomPanel.this.setVisibility(View.VISIBLE);
    }

    public void hide() {
        LVCustomPanel.this.setVisibility(View.GONE);
    }

    /**
     * 初始化Panel
     */
    public abstract void initPanel();

    /**
     * 子类实现该方法，用于Lua回调该方法
     */
    public void callLuaCallback(Object... objs) {
        UDView userdata = getUserdata();
        if(userdata != null) {
            final LuaValue callback = userdata.getCallback();
            LuaUtil.callFunction(callback, objs);
        }
    }

    //获取native view
    @Override
    public View getNativeView() {
        if (getChildCount() > 0 && getChildAt(0) != null) {
            return getChildAt(0);
        }
        return null;
    }
}
