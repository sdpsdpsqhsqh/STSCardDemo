package com.laisontech.stscarddemo.ui.activity;

import android.support.v7.widget.CardView;
import android.view.View;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/13.
 */
public class SettingActivity extends BaseActivity {

    CardView mCvSetPortAddress;
    CardView mCvAboutUs;
    private SystemSettingUtils.ActionBarView mActionBarView;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        mActionBarView = SystemSettingUtils.setActionBar(this, true);
    }

    @Override
    protected void initEvent() {
        mActionBarView.tv.setText("设置");
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.cv_set_port_address, R.id.cv_about_us})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_set_port_address:
                openActivity(SetPortAddressActivity.class);
                break;
            case R.id.cv_about_us:
                openActivity(AboutUsActivity.class);
                break;
        }
    }
}
