package g7.zcf.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import g7.zcf.R;

public class FragmentDemoActivity extends FragmentActivity {
    private String Tag = "zcf_fragment";
    private View contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
        Log.d(Tag, "activity onCreate");

        contentView = findViewById(R.id.fragmentContentView);
        TitleFragment titleFragment = new TitleFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentContentView, titleFragment);
        ft.commit();
    }

    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.d(Tag, "activity onCreateView" + this);
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        Log.d(Tag, "activity onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(Tag, "activity onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(Tag, "activity onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d(Tag, "activity onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(Tag, "activity onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(Tag, "activity onRestart");
        super.onRestart();
    }
}
