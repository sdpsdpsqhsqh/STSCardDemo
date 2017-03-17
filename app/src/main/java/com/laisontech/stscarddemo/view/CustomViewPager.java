package com.laisontech.stscarddemo.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;

/**
 * Created by SDP on 2017/3/14.
 */
public class CustomViewPager extends ViewPager{
    public CustomViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
