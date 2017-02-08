package com.example.administrator.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Create by zcf on 2017/2/6 0006 11:00
 */
public class CustomView extends View {

    private String mText;
    private int mTextColor;
    private int mTextSize;
    private Paint mPaint;
    private Rect mBounds;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView,
                defStyleAttr, 0);
        if (array != null) {
            int count = array.getIndexCount();
            for (int i = 0; i < count; i++) {
                int attr = array.getIndex(i);
                switch (attr) {
                    case R.styleable.CustomView_titleText:
                        mText = array.getString(attr);
                        break;
                    case R.styleable.CustomView_titleTextColor:
                        mTextColor = array.getColor(attr, Color.BLACK);
                        break;
                    case R.styleable.CustomView_titleTextSize:
                        mTextSize = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                        break;
                }
            }
        }
        array.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mBounds = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mBounds);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = getPaddingLeft() + widthSize + getPaddingRight();
        } else {
            width = getPaddingLeft() + mBounds.width() + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = getPaddingTop() + heightSize + getPaddingBottom();
        } else {
            height = getPaddingTop() + mBounds.height() + getPaddingBottom();
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTextColor);
        canvas.drawText(mText, 100, 100, mPaint);
    }
}
