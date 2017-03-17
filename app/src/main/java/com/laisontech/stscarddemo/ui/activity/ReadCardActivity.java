package com.laisontech.stscarddemo.ui.activity;

import android.view.View;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

/**
 * Created by SDP on 2017/3/13.
 */
public class ReadCardActivity extends BaseActivity{

    private SystemSettingUtils.ActionBarView mViewList;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_read_card;
    }

    @Override
    protected void initData() {
        mViewList = SystemSettingUtils.setActionBar(this,true);
    }

    @Override
    protected void initEvent() {
        mViewList.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mViewList.tv.setText("读卡");
    }
}
