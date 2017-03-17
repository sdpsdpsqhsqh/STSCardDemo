package com.laisontech.stscarddemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by SDP on 2017/3/16.
 */
public class DefaultScrollViewVSListView extends ListView{
    public DefaultScrollViewVSListView(Context context) {
        this(context,null);
    }

    public DefaultScrollViewVSListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DefaultScrollViewVSListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,

                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
