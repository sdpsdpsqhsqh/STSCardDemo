package com.laisontech.stscarddemo.ui.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/13.
 */
public class GoToBuyActivity extends BaseActivity {

    @InjectView(R.id.et_operator_num)
    EditText etOperatorNum;
    @InjectView(R.id.et_operator_name)
    EditText etOperatorName;
    @InjectView(R.id.et_operator_numID)
    EditText etOperatorNumID;
    @InjectView(R.id.et_operator_num_readCard)
    EditText etOperatorNumReadCard;
    @InjectView(R.id.btn_go_to_buy)
    Button mBtnBuy;
    private SystemSettingUtils.ActionBarView mActionBarView;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_go_to_buy;
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
        mActionBarView.tv.setText("购买");
    }

    @OnClick(R.id.btn_go_to_buy)
    public void onClick() {
        String operatorName = etOperatorName.getText().toString().trim();
        String operatorNum = etOperatorNum.getText().toString().trim();
        String operatorNumID = etOperatorNumID.getText().toString().trim();
        String operatorNumReadCard = etOperatorNumReadCard.getText().toString().trim();

        if (operatorNum.isEmpty()){
            Toast.makeText(GoToBuyActivity.this, "不能不输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(new Intent(this,BuyActivity.class));
    }
}
