package com.laisontech.stscarddemo.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/13.
 */
public class SetPortAddressActivity extends BaseActivity {
    @InjectView(R.id.et_port_address)
    EditText etPortAddress;
    @InjectView(R.id.ll_change_address)
    LinearLayout llChangeAddress;
    @InjectView(R.id.btn_change_address)
    Button btnChangeAddress;
    private SystemSettingUtils.ActionBarView mActionBarView;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_setport_address;
    }

    @Override
    protected void initData() {
        mActionBarView = SystemSettingUtils.setActionBar(this, true);
    }

    @Override
    protected void initEvent() {
        mActionBarView.tv.setText("更改服务器地址");
        mActionBarView.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etPortAddress.setSelection(etPortAddress.getText().toString().length());
    }

    @OnClick(R.id.btn_change_address)
    public void onClick() {
    }
}
