package com.example.administrator.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Create by zcf on 2017/2/7 0007 11:17
 */
public class CustomCircleProgressBar extends View {

    private int mFirstColor;
    private int mSecondColor;
    private int mCircleWidth;
    private int mSpeed;
    private Paint mPaint;
    private int mProgress;

    public CustomCircleProgressBar(Context context) {
        this(context, null);
    }

    public CustomCircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray allType = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomCircleProgressBar, defStyleAttr, 0);
        if (allType != null) {
            int count = allType.getIndexCount();
            for (int i = 0; i < count; i++) {
                int attr = allType.getIndex(i);
                switch (attr) {
                    case R.styleable.CustomCircleProgressBar_firstColor:
                        mFirstColor = allType.getColor(attr, Color.GREEN);
                        break;
                    case R.styleable.CustomCircleProgressBar_secondColor:
                        mSecondColor = allType.getColor(attr, Color.RED);
                        break;
                    case R.styleable.CustomCircleProgressBar_circleWidth:
                        mCircleWidth = allType.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                        break;
                    case R.styleable.CustomCircleProgressBar_speed:
                        mSpeed = allType.getInt(attr, 1);
                        break;
                }
            }
        }
        allType.recycle();

        mPaint = new Paint();
        new Thread() {
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
                        mProgress = 0;
                        int tempColor = mFirstColor;
                        mFirstColor = mSecondColor;
                        mSecondColor = tempColor;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setAntiAlias(true);
        int center = getWidth() / 2;
        int radius = center - mCircleWidth / 2;
        mPaint.setColor(mFirstColor);
        canvas.drawCircle(center, center, radius, mPaint);
        mPaint.setColor(mSecondColor);
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        canvas.drawArc(rectF, -90, mProgress, false, mPaint);
    }
}
