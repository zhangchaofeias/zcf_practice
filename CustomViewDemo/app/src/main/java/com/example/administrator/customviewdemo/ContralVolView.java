package com.example.administrator.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Create by zcf on 2017/2/7 0007 14:44
 */
public class ContralVolView extends View {

    private Paint mPaint;
    private int mFirstColor;
    private int mSecondColor;
    private int mCirclewidth;
    private int mCount;
    private int mSpiltSize;
    private int mCurrent = 5;
    private Bitmap mImage;
    private Rect mImageRect;

    public ContralVolView(Context context) {
        this(context, null);
    }

    public ContralVolView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContralVolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray allType = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ContralVolView,
                defStyleAttr, 0);
        if (allType != null) {
            for (int i = 0; i < allType.getIndexCount(); i++) {
                int attrIndex = allType.getIndex(i);
                switch (attrIndex) {
                    case R.styleable.ContralVolView_firstColor:
                        mFirstColor = allType.getColor(attrIndex, Color.GREEN);
                        break;
                    case R.styleable.ContralVolView_secondColor:
                        mSecondColor = allType.getColor(attrIndex, Color.RED);
                        break;
                    case R.styleable.ContralVolView_circleWidth:
                        mCirclewidth = allType.getDimensionPixelSize(attrIndex, (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                        break;
                    case R.styleable.ContralVolView_count:
                        mCount = allType.getInt(attrIndex, 10);
                        break;
                    case R.styleable.ContralVolView_splitSize:
                        mSpiltSize = allType.getDimensionPixelSize(attrIndex, (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                        break;
                    case R.styleable.ContralVolView_image:
                        mImage = BitmapFactory.decodeResource(getResources(), allType.getResourceId(attrIndex, 0));
                        break;
                }
            }
        }
        allType.recycle();

        mPaint = new Paint();
        mImageRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCirclewidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        int center = getWidth() / 2;
        int radius = center - mCirclewidth / 2;
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        float itemSize = (360 * 1.0f - mCount * mSpiltSize) / mCount;

        for (int i = 0; i < mCurrent; i++) {
            mPaint.setColor(mFirstColor);
            canvas.drawArc(rectF, i * (itemSize + mSpiltSize), itemSize, false, mPaint);
        }
        for (int i = mCurrent; i < mCount; i++) {
            mPaint.setColor(mSecondColor);
            canvas.drawArc(rectF, i * (itemSize + mSpiltSize), itemSize, false, mPaint);
        }

        int innerRadius = radius - mCirclewidth / 2;
        mImageRect.left = mCirclewidth + (int) (innerRadius - Math.sqrt(2) / 2 * innerRadius);
        mImageRect.top = mCirclewidth + (int) (innerRadius - Math.sqrt(2) / 2 * innerRadius);
        mImageRect.right = mCirclewidth + innerRadius + (int) (Math.sqrt(2) /2 * innerRadius);
        mImageRect.bottom = mCirclewidth + innerRadius + (int) (Math.sqrt(2) /2 * innerRadius);

        canvas.drawBitmap(mImage, null, mImageRect, mPaint);
    }

    public void update(boolean add) {
        if (add){
            mCurrent++;
            if (mCurrent > mCount) {
                mCurrent = mCurrent % mCount;
            }
        } else {
            mCurrent--;
            if (mCurrent < 0) {
                mCurrent = mCount - Math.abs(mCurrent) % mCount;
            }
        }

        invalidate();
    }
}
