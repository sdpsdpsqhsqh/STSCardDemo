package com.laisontech.stscarddemo.utils;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;

/**
 * Created by SDP on 2017/3/13.
 */
public class SystemSettingUtils {
    //设置ActionBar
    public static ActionBarView setActionBar(AppCompatActivity app,boolean isNeedElevation){
        ActionBarView viewList = new ActionBarView();
        ActionBar actionBar = app.getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View view = LayoutInflater.from(app).inflate(R.layout.base_actionbar_layout,null);
        TextView tv = (TextView) view.findViewById(R.id.tv_actionBar_title);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_actionBar_back);
        if (!isNeedElevation){
            actionBar.setElevation(0);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.RIGHT_OF,R.id.iv_actionBar_back);
            params.leftMargin = dp2px(app.getApplicationContext(),20);
            tv.setLayoutParams(params);
        }else {
            tv.setGravity(Gravity.CENTER);
        }
        viewList.tv = tv;
        viewList.iv = iv;
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        actionBar.setCustomView(view, params);

        return viewList;
    }
    public static class ActionBarView{
        public ImageView iv;
        public TextView tv;
    }
    public static int dp2px(Context context, int px){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,context.getResources().getDisplayMetrics());
    }
}
