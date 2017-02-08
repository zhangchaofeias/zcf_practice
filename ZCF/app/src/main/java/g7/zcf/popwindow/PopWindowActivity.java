package g7.zcf.popwindow;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import g7.zcf.R;

public class PopWindowActivity extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow();
            }
        });
    }

    private void showPopWindow() {
        View popView = LayoutInflater.from(PopWindowActivity.this).inflate(R.layout.pop_window_view, null);
        PopupWindow popWindow = new PopupWindow(popView, LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        popWindow.setFocusable(true);
        popWindow.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        popWindow.setAnimationStyle(R.style.pop_window);
        popWindow.showAsDropDown(button);
    }
}
