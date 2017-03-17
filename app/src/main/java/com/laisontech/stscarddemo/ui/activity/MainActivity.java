package com.laisontech.stscarddemo.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.ui.fragment.HomeFragment;
import com.laisontech.stscarddemo.ui.fragment.OperatorFragment;
import com.laisontech.stscarddemo.view.ChangeColorIconViewText;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private static final int HOME_TAB_POSITION = 0;
    private static final int OPERATOR_TAB_POSITION = 1;
    @InjectView(R.id.vp_main)
    ViewPager mVpMain;
    @InjectView(R.id.home_tab_main)
    ChangeColorIconViewText mHomeTabMain;
    @InjectView(R.id.mine_tab_main)
    ChangeColorIconViewText mOperatorTabMain;
    private List<ChangeColorIconViewText> mBtnTabs;
    private List<Fragment> mFragments;
    private ActionBar mActionBar;
    private TextView mTvContent;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mBtnTabs = new ArrayList<>();
        mBtnTabs.add(mHomeTabMain);
        mBtnTabs.add(mOperatorTabMain);

        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(OperatorFragment.newInstance());
        mHomeTabMain.setIconAlpha(1.0f);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setElevation(0);
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View view = LayoutInflater.from(this).inflate(R.layout.action_home,null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT);
        mActionBar.setCustomView(view, params);
        mTvContent = (TextView) view.findViewById(R.id.tv_home);
    }

    @Override
    protected void initEvent() {

        mVpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        mVpMain.addOnPageChangeListener(this);
    }

    @OnClick({R.id.home_tab_main, R.id.mine_tab_main})
    public void onClick(View view) {
        initButton();
        switch (view.getId()) {
            case R.id.home_tab_main:
                mTvContent.setText("首页");
                mVpMain.setCurrentItem(HOME_TAB_POSITION, false);
                mBtnTabs.get(HOME_TAB_POSITION).setIconAlpha(1.0f);
                break;
            case R.id.mine_tab_main:
                mTvContent.setText("我的");
                mVpMain.setCurrentItem(OPERATOR_TAB_POSITION, false);
                mBtnTabs.get(OPERATOR_TAB_POSITION).setIconAlpha(1.0f);
                break;
        }
    }

    private void initButton() {
        for (ChangeColorIconViewText tab : mBtnTabs) {
            tab.setIconAlpha(0.0f);
        }
    }
    //滚动的时候根据手指滑动的位置改变更改下面的tab的颜色
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset>0){
            ChangeColorIconViewText left = mBtnTabs.get(position+1);
            ChangeColorIconViewText right = mBtnTabs.get(position);
            left.setIconAlpha(positionOffset);
            right.setIconAlpha(1-positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (position==HOME_TAB_POSITION){
            mTvContent.setText("首页");
        }else {

            mTvContent.setText("我的");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
