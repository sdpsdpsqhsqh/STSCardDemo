package com.laisontech.stscarddemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.adapter.MonthBillAdapter;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.bean.MonthBillItemInfo;
import com.laisontech.stscarddemo.constant.Constants;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by SDP on 2017/3/14.
 */
public class MonthSalesInfoActivity extends BaseActivity {
    @InjectView(R.id.lv_month)
    ListView mLvMonth;
    private String mWatchMonth = "";
    private SystemSettingUtils.ActionBarView mActionBarView;
    private List<MonthBillItemInfo> mData = new ArrayList<>();
    private MonthBillAdapter mAdapter;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_month_sales_info;
    }

    @Override
    protected void initData() {
        mWatchMonth = getIntent().getExtras().getString("Month");
        mActionBarView = SystemSettingUtils.setActionBar(this, false);
        //TODO 根据获取的月份，通过get或者post请求从网端获取到请求的数据，从而解析，将数据放在List中，这里先模拟数据
        for(int i=0;i<20;i++){
            MonthBillItemInfo itemInfo = new MonthBillItemInfo();
            itemInfo.setDate((System.currentTimeMillis()-10000000*i)+"");
            itemInfo.setRechargeMoney(20+i*13+"");
            mData.add(itemInfo);
        }
    }

    @Override
    protected void initEvent() {
        mActionBarView.tv.setText(mWatchMonth + "月账单");
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapter = new MonthBillAdapter(this,mData);
        mLvMonth.setAdapter(mAdapter);
    }
}
