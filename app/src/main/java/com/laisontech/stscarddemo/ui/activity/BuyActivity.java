package com.laisontech.stscarddemo.ui.activity;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/13.
 */
public class BuyActivity extends BaseActivity {
    @InjectView(R.id.tv_operator_num_readCard)
    TextView tvOperatorNumReadCard;
    @InjectView(R.id.tv_operator_name_readCard)
    TextView tvOperatorNameReadCard;
    @InjectView(R.id.tv_operator_cardID_readCard)
    TextView tvOperatorCardIDReadCard;
    @InjectView(R.id.tv_operator_phone_num_readCard)
    TextView tvOperatorPhoneNumReadCard;
    @InjectView(R.id.tv_operator_last_time_readCard)
    TextView tvOperatorLastTimeReadCard;
    @InjectView(R.id.tv_operator_last_money_readCard)
    TextView tvOperatorLastMoneyReadCard;
    @InjectView(R.id.btn_buy)
    Button btnBuy;
    private SystemSettingUtils.ActionBarView mActionBarView;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_buy;
    }

    @Override
    protected void initData() {
        mActionBarView = SystemSettingUtils.setActionBar(this, true);
    }

    @Override
    protected void initEvent() {
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mActionBarView.tv.setText("购买中");
    }

    @OnClick(R.id.btn_buy)
    public void onClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.buy_dialog_layout,null);
        builder.setView(view);
        builder.create().show();
    }
}
