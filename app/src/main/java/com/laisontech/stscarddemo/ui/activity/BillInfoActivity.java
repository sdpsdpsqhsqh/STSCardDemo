package com.laisontech.stscarddemo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.ui.fragment.BillMonthFragment;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;
import com.laisontech.stscarddemo.view.CustomViewPager;
import com.laisontech.stscarddemo.view.ParentViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by SDP on 2017/3/13.
 */
public class BillInfoActivity extends BaseActivity {
    //不知道网络数据什么情况。暂时模拟数据
    @InjectView(R.id.tabLayout_bill)
    TabLayout mTabLayoutBill;
    @InjectView(R.id.viewPager_bill)
    ParentViewPager mViewPagerBill;
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mBillMonthInfos = new ArrayList<>();
    private SystemSettingUtils.ActionBarView mActionBarView;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_bill_info;
    }

    @Override
    protected void initData() {
        mBillMonthInfos.add("3月\n"+"2017");
        mBillMonthInfos.add("2月\n"+"2017");
        mBillMonthInfos.add("1月\n"+"2017");
        mBillMonthInfos.add("12月\n"+"2016");
        mBillMonthInfos.add("11月\n"+"2016");
        mBillMonthInfos.add("10月\n"+"2016");

        for(int i=0;i<mBillMonthInfos.size();i++){
            Bundle bundle = new Bundle();
            bundle.putString("MONTH",mBillMonthInfos.get(i));
            BillMonthFragment fragment = new BillMonthFragment();
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
    }

    @Override
    protected void initEvent() {
        mViewPagerBill.setOffscreenPageLimit(mFragments.size());
        mActionBarView = SystemSettingUtils.setActionBar(this, false);
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mActionBarView.tv.setText("账单");

        mViewPagerBill.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mTabLayoutBill.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayoutBill.setupWithViewPager(mViewPagerBill);
    }
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mBillMonthInfos.get(position);
        }
    }
}
