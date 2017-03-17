package com.laisontech.stscarddemo.ui.activity;

import android.view.View;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

/**
 * Created by SDP on 2017/3/15.
 */
public class AboutUsActivity extends BaseActivity{

    private SystemSettingUtils.ActionBarView mActionBarView;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initData() {
        mActionBarView = SystemSettingUtils.setActionBar(this, true);
    }

    @Override
    protected void initEvent() {
        mActionBarView.tv.setText("关于我们");
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
