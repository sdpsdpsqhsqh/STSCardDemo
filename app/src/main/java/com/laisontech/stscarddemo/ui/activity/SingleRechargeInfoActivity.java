package com.laisontech.stscarddemo.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.bean.MonthBillItemInfo;
import com.laisontech.stscarddemo.constant.Constants;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import butterknife.InjectView;

/**
 * Created by SDP on 2017/3/16.
 */
public class SingleRechargeInfoActivity extends BaseActivity {

    @InjectView(R.id.tv_operator_account_single)
    TextView mAccountSingle;
    @InjectView(R.id.tv_operator_recharge_money_single)
    TextView mRechargeMoneySingle;
    @InjectView(R.id.tv_operator_recharge_purchases_single)
    TextView mRechargePurchasesSingle;
    @InjectView(R.id.tv_operator_recharge_amount_single)
    TextView mRechargeAmountSingle;
    @InjectView(R.id.tv_token_single)
    TextView mTokenSingle;
    private SystemSettingUtils.ActionBarView mActionBarView;
    private MonthBillItemInfo mItemInfo;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_single_recharge_info;
    }

    @Override
    protected void initData() {
        mActionBarView = SystemSettingUtils.setActionBar(this, false);
        mItemInfo = (MonthBillItemInfo) getIntent().getSerializableExtra(Constants.MONTH_BILL_ITEM_INF0);

    }

    @Override
    protected void initEvent() {
        mActionBarView.tv.setText("账单详情");
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRechargeMoneySingle.setText(mItemInfo.getRechargeMoney());
        mRechargeAmountSingle.setText(mItemInfo.getRechargeMoney());
        mRechargePurchasesSingle.setText(Float.parseFloat(mItemInfo.getRechargeMoney())/10+"m³");
    }
}
