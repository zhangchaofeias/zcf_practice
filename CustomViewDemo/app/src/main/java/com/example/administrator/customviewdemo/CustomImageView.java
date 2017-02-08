package com.example.administrator.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Create by zcf on 2017/2/6 0006 15:15
 */
public class CustomImageView extends View {
    private static final int IMAGE_SCALE_FITXY = 0;
    private static final int IMAGE_SCALE_CENTER = 1;
    private int mWidth;
    private int mHeight;
    private String mText;
    private int mTextColor;
    private int mTextSize;
    private Bitmap mImage;
    private int mImageScale;
    private Paint mPaint;
    private Rect mTextRect;
    private Rect mImageRect;


    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray allType = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CustomImageView, defStyleAttr, 0);
        if (allType != null) {
            for (int i = 0; i < allType.getIndexCount(); i++) {
                int oneType = allType.getIndex(i);
                switch (oneType) {
                    case R.styleable.CustomImageView_titleText:
                        mText = allType.getString(oneType);
                        break;
                    case R.styleable.CustomImageView_titleTextColor:
                        mTextColor = allType.getColor(oneType, Color.BLACK);
                        break;
                    case R.styleable.CustomImageView_titleTextSize:
                        mTextSize = allType.getDimensionPixelSize(oneType, (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                        break;
                    case R.styleable.CustomImageView_image:
                        mImage = BitmapFactory.decodeResource(getResources(), allType.getResourceId(oneType, 0));
                        break;
                    case R.styleable.CustomImageView_imageScaleType:
                        mImageScale = allType.getInt(oneType, 0);
                        break;
                }
            }
        }
        allType.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mImageRect = new Rect();
        mTextRect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mTextRect);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthdSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthdSize;
        } else {
            int imageWidth = getPaddingLeft() + mImage.getWidth() + getPaddingRight();
            int textWidth = getPaddingLeft() + mTextRect.width() + getPaddingRight();
            mWidth = Math.max(imageWidth, textWidth);
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = getPaddingTop() + mImage.getHeight() + mTextRect.height() + getPaddingBottom();
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.RED);
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);

        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);
        if (mTextRect.width() > mWidth) {
            TextPaint paint = new TextPaint(mPaint);
            String msg = TextUtils.ellipsize(mText, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), getPaddingTop() + mTextRect.height(), mPaint);
        } else {
            canvas.drawText(mText, mWidth / 2 - mTextRect.width() / 2, getPaddingTop() + mTextRect.height(), mPaint);
        }

        if (mImageScale == IMAGE_SCALE_FITXY) {
            mImageRect.left = getPaddingLeft();
            mImageRect.top = getPaddingTop() + mTextRect.height();
            mImageRect.right = mWidth - getPaddingRight();
            mImageRect.bottom = mHeight - getPaddingBottom();
        } else {
            mImageRect.left = mWidth / 2 - mImage.getWidth() / 2;
            mImageRect.top = (mHeight - getPaddingTop() - mTextRect.height()) / 2 - mImage.getHeight() / 2
                    + getPaddingTop() + mTextRect.height();
            mImageRect.right = mWidth / 2 + mImage.getWidth() / 2;
            mImageRect.bottom = mImageRect.top + mImage.getHeight();
        }

        canvas.drawBitmap(mImage, null, mImageRect, mPaint);
    }
}
