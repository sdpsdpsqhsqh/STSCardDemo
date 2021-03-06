package com.laisontech.stscarddemo.ui.activity;

import android.view.View;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

/**
 * Created by SDP on 2017/3/13.
 */
public class OperatorInfoActivity extends BaseActivity{

    private SystemSettingUtils.ActionBarView mActionBarView;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_operator_info;
    }

    @Override
    protected void initData() {
        mActionBarView = SystemSettingUtils.setActionBar(this,true);
    }

    @Override
    protected void initEvent() {
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mActionBarView.tv.setText("操作员");
    }
}
