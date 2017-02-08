package com.example.administrator.myfirstluaview;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.taobao.luaview.util.LuaViewUtil;
import com.taobao.luaview.view.LVCustomPanel;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;

/**
 * .
 */
public class LuaUseNative extends LVCustomPanel {
    public LuaUseNative(Globals globals, LuaValue metaTable, Varargs varargs) {
        super(globals, metaTable, varargs);
    }

    @Override
    public void initPanel() {
        final TextView textView = new TextView(getContext());
        textView.setText("test lua use native");
        LayoutParams params = LuaViewUtil.createRelativeLayoutParamsMM();
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(textView, params);

        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                callLuaCallback("s");
                Toast.makeText(getContext(), "hahah", Toast.LENGTH_LONG).show();
            }
        });
    }
}
