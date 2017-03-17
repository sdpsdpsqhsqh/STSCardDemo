package com.laisontech.stscarddemo.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.ui.fragment.RechargeFragment;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/14.
 */
public class RechargeActivity extends BaseActivity {

    @InjectView(R.id.ll_recharge_btn)
    LinearLayout mLlRechargeBtn;
    @InjectView(R.id.et_recharge_number)
    EditText mEtRechargeNumber;
    @InjectView(R.id.btn_recharge)
    Button mBtnRecharge;
    @InjectView(R.id.cv_popup_push_position)
    CardView cvPopupPushPosition;
    @InjectView(R.id.iv_icon_charge)
    ImageView ivIconCharge;
    @InjectView(R.id.tv_name_charge)
    TextView tvNameCharge;
    @InjectView(R.id.tv_introduce_charge)
    TextView tvIntroduceCharge;
    ImageView ivEnterRecharge;
    private SystemSettingUtils.ActionBarView mActionBarView;
    private ImageView mIvBack;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initData() {
        mActionBarView = SystemSettingUtils.setActionBar(this, false);
    }

    @Override
    protected void initEvent() {
        mActionBarView.tv.setText("账户充值");
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.ll_recharge_btn, R.id.btn_recharge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_recharge_btn:

                break;
            case R.id.btn_recharge:
                String rechargeNumber = mEtRechargeNumber.getText().toString().trim();
                if (rechargeNumber.isEmpty()){
                    Toast.makeText(RechargeActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                RechargeFragment rechargeFragment = new RechargeFragment();
                rechargeFragment.show(getFragmentManager(),"test");
                //TODO 进行购买
                break;
        }
    }
}
