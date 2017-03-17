package com.laisontech.stscarddemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/13.
 */
public class BalanceInfoActivity extends BaseActivity {

    @InjectView(R.id.tv_balance)
    TextView mTvBalance;
    private SystemSettingUtils.ActionBarView mActionBarView;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_balance_info;
    }

    @Override
    protected void initData() {
        mActionBarView = SystemSettingUtils.setActionBar(this, false);
    }

    @Override
    protected void initEvent() {
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mActionBarView.tv.setText("余额");
        //判断当前网络进行刷新 按钮的出现于消息
//        if (false){
//
//        }
    }

    @OnClick(R.id.cv_recharge_balance)
    public void onClick() {
        Bundle bundle = new Bundle();
        bundle.putString("rest","rest");
        openActivity(bundle,RechargeActivity.class);
    }
}
