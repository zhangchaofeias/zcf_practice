package com.taobao.luaview.view.foreground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.RelativeLayout;

/**
 * Relative Layout can set foreground drawable
 */
public class ForegroundRelativeLayout extends RelativeLayout implements IForeground {
    private ForegroundDelegate mForegroundDelegate;
    private boolean enableForeground;

    public ForegroundRelativeLayout(Context context) {
        super(context);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mForegroundDelegate = new ForegroundDelegate();
        }
    }

    @Override
    public int getForegroundGravity() {
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return mForegroundDelegate.getForegroundGravity();
        } else {
            return super.getForegroundGravity();
        }
    }

    @Override
    public void setForegroundGravity(int foregroundGravity) {
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mForegroundDelegate.setForegroundGravity(this, foregroundGravity);
        } else {
            super.setForegroundGravity(foregroundGravity);
        }
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return super.verifyDrawable(who) || (who == mForegroundDelegate.getForeground());
        } else {
            return super.verifyDrawable(who);
        }
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mForegroundDelegate.jumpDrawablesToCurrentState();
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mForegroundDelegate.drawableStateChanged(this);
        }
    }


    @Override
    public void setForeground(Drawable foreground) {
        enableForeground = foreground != null;
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mForegroundDelegate.setForeground(this, foreground);
        } else {
            super.setForeground(foreground);
        }
    }

    @Override
    public void clearForeground() {
        enableForeground = false;
    }

    @Override
    public Drawable getForeground() {
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return mForegroundDelegate.getForeground();
        } else {
            return super.getForeground();
        }
    }

    @Override
    public boolean hasForeground() {
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return mForegroundDelegate.getForeground() != null;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return super.getForeground() != null;
        }
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mForegroundDelegate.onLayout(changed, left, top, right, bottom);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mForegroundDelegate.onSizeChanged(w, h, oldw, oldh);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mForegroundDelegate.draw(this, canvas);
        }
    }

    @Override
    public void drawableHotspotChanged(float x, float y) {
        super.drawableHotspotChanged(x, y);
        if (enableForeground && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mForegroundDelegate.drawableHotspotChanged(x, y);
        }
    }
}