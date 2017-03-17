package com.laisontech.stscarddemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.laisontech.stscarddemo.R;


/**
 * Created by 80926 on 2016/12/3.
 */

public class ChangeColorIconViewText extends View {
    private int mColor = Color.GREEN;
    private Bitmap mIconBitmap;
    private String mText = "微信";
    private int mTextSize = sp2px(12);
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;
    private float mAlpha;//渐变色
    private Rect mIconRect;
    private Rect mTextBound;
    private Paint mTextPaint;
    private static final String INSTANCE_STATUS = "instance_status";//存储bundle
    private static final String STATUS_ALPHA = "status_alpha";//用于存放alpha

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS,super.onSaveInstanceState());
        bundle.putFloat(STATUS_ALPHA,mAlpha);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            mAlpha = bundle.getFloat(STATUS_ALPHA);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public ChangeColorIconViewText(Context context) {
        this(context, null);
    }

    public ChangeColorIconViewText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChangeColorIconViewText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorIconViewText);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int index = ta.getIndex(i);
            switch (index) {
                case R.styleable.ChangeColorIconViewText_icon:
                    BitmapDrawable drawable = (BitmapDrawable) ta.getDrawable(index);
                    mIconBitmap = drawable.getBitmap();
                    break;
                case R.styleable.ChangeColorIconViewText_text:
                    mText = ta.getString(index);
                    break;
                case R.styleable.ChangeColorIconViewText_text_size:
                    mTextSize = (int) ta.getDimension(index, sp2px(12));
                    break;
                case R.styleable.ChangeColorIconViewText_color:
                    mColor = ta.getColor(index, Color.GREEN);
                    break;
            }
        }
        ta.recycle();

        //初始化
        mTextBound = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(Color.parseColor("#BABABA"));
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBound);

    }

    private int sp2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, px, getResources().getDisplayMetrics());
    }

    private int dp2px(int px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight()
                , getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - mTextBound.height());
        //绘制icon的范围
        int iconLeft = getMeasuredWidth() / 2 - iconWidth / 2;
        int iconTop = (getMeasuredHeight() - mTextBound.height()) / 2 - iconWidth / 2;
        mIconRect = new Rect(iconLeft, iconTop, iconLeft + iconWidth, iconTop + iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);//绘制icon

        int alpha = (int) Math.ceil(255 * mAlpha);//向上取一
        setupTargetBitmap(alpha);//准备Bitmap

        //绘制文字
        //绘制源文本
        drawSourceText(canvas, alpha);
        //绘制变色的文本
        drawTargetText(canvas, alpha);
        canvas.drawBitmap(mBitmap, 0, 0, null);

        canvas.restore();

    }

    //在内存中绘制可变色的Icon
    private void setupTargetBitmap(int alpha) {
        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);

        mCanvas.drawRect(mIconRect, mPaint);//设置纯色

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);//可以通过改变alpha来更改图标的颜色

        mCanvas.drawBitmap(mIconBitmap, null, mIconRect, mPaint);//此时绘制的是纯绿色的图标
    }

    private void drawSourceText(Canvas canvas, int alpha) {
        mTextPaint.setColor(Color.GRAY);//灰色
        mTextPaint.setAlpha(255 - alpha);
        canvas.drawText(mText, mIconRect.left
                + mIconRect.width() / 2
                - mTextBound.width() / 2-dp2px(1),
                mIconRect.bottom
                        + mTextBound.height()-dp2px(1)
                , mTextPaint);
    }

    private void drawTargetText(Canvas canvas, int alpha) {
        mTextPaint.setColor(mColor);
        mTextPaint.setAlpha(alpha);
        canvas.drawText(mText, mIconRect.left
                        + mIconRect.width() / 2
                        - mTextBound.width() / 2-dp2px(1),
                mIconRect.bottom
                        + mTextBound.height()-dp2px(1)
                , mTextPaint);
    }

    public void setIconAlpha(float alpha) {
        this.mAlpha = alpha;
        invalidateView();
    }

    private void invalidateView() {
        //不论是ui线程，还是ui线程都可以调用
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();//主线程中
        } else {
            postInvalidate();//子线程中
        }
    }
}
