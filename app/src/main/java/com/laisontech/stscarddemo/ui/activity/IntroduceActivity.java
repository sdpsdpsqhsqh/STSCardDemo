package com.laisontech.stscarddemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.app.BaseApplication;
import com.laisontech.stscarddemo.constant.Constants;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/15.
 */
public class IntroduceActivity extends Activity implements ViewPager.OnPageChangeListener {

    private static final int LAST_IMAGE_POINT = 3;
    private static final int POINT_BIG_SIZE = 11;
    private static final int POINT_SMALL_SIZE = 8;
    @InjectView(R.id.vp_introduce)
    ViewPager mVpIntroduce;
    @InjectView(R.id.ll_container)
    LinearLayout mContainer;
    @InjectView(R.id.btn_experience)
    TextView mBtnExperience;

    //初始化数据源
    private int[] mIntroduceImageRes;
    private List<ImageView> mIntroduceImageList;
    private List<ImageView> mPointViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        ButterKnife.inject(this);
        initData();
        initEvent();
    }


    protected void initData() {
        mIntroduceImageRes = new int[]{R.drawable.jieshao1, R.drawable.jieshao2, R.drawable.jieshao3, R.drawable.jieshao4};
        mIntroduceImageList = new ArrayList<>();
        mPointViewList = new ArrayList<>();
        for (int i = 0; i < mIntroduceImageRes.length; i++) {
            //初始化一张图片,并且设置参数最后添加到集合中
            ImageView ivBackground = new ImageView(this);
            RelativeLayout.LayoutParams ivBgParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            ivBackground.setLayoutParams(ivBgParams);
            ivBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ivBackground.setImageResource(mIntroduceImageRes[i]);
            mIntroduceImageList.add(ivBackground);
            //初始化指示点，并最终添加到LinearLayout中
            ImageView ivPoint = new ImageView(this);
            if (i == 0) {
                setPointParams(ivPoint, POINT_BIG_SIZE,R.drawable.point_shape_big);
            } else {
                setPointParams(ivPoint, POINT_SMALL_SIZE,R.drawable.point_shape_small);
            }
            mPointViewList.add(ivPoint);
            mContainer.addView(ivPoint);
        }
    }
    protected void initEvent() {
        mVpIntroduce.setAdapter(new IntroduceAdapter());
        mVpIntroduce.addOnPageChangeListener(this);
    }

    @OnClick(R.id.btn_experience)
    public void onClick() {
        SharedPreferences.Editor edit = BaseApplication.mFirstInstallSP.edit();
        edit.putBoolean(Constants.FIRST_INSTALL,true).commit();
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    private void setPointParams(ImageView ivPoint, int width,int drawableID) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(SystemSettingUtils.dp2px(this, width), SystemSettingUtils.dp2px(this, width));
        params.setMargins(SystemSettingUtils.dp2px(this, 5), 0, SystemSettingUtils.dp2px(this, 5), 0);
        ivPoint.setImageResource(drawableID);
        ivPoint.setLayoutParams(params);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override
    public void onPageSelected(int position) {
        //设置出现点击按钮
        if (position==LAST_IMAGE_POINT){
            mBtnExperience.setClickable(true);
           mBtnExperience.setText("点击体验吧...");
        }else {
            mBtnExperience.setClickable(false);
            mBtnExperience.setText("App简单浏览");
        }
        //设置指示器的大小改变
        for(int i=0;i<mPointViewList.size();i++){
            if (i==position){
                setPointParams(mPointViewList.get(i),POINT_BIG_SIZE,R.drawable.point_shape_big);
            }else {
                setPointParams(mPointViewList.get(i),POINT_SMALL_SIZE,R.drawable.point_shape_small);
            }
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {}

   private class IntroduceAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mIntroduceImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mIntroduceImageList.get(position));
            return mIntroduceImageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView(mIntroduceImageList.get(position));
        }
    }
}
