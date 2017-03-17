package com.laisontech.stscarddemo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.ui.activity.BalanceInfoActivity;
import com.laisontech.stscarddemo.ui.activity.BillInfoActivity;
import com.laisontech.stscarddemo.ui.activity.ConnectPrinterActivity;
import com.laisontech.stscarddemo.ui.activity.OperatorInfoActivity;
import com.laisontech.stscarddemo.ui.activity.SettingActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/13.
 */
public class OperatorFragment extends Fragment {

    @InjectView(R.id.ll_operator_account)
    RelativeLayout mOperatorAccount;
    @InjectView(R.id.ll_operator_balance)
    RelativeLayout mOperatorBalance;
    @InjectView(R.id.ll_operator_bill)
    RelativeLayout mOperatorBill;
    @InjectView(R.id.ll_operator_connect)
    RelativeLayout mOperatorConnect;
    @InjectView(R.id.ll_operator_settings)
    RelativeLayout mOperatorSettings;

    public static OperatorFragment newInstance() {

        Bundle args = new Bundle();

        OperatorFragment fragment = new OperatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    protected void initData() {

    }

    protected void initEvent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_operator, container, false);
        ButterKnife.inject(this, rootView);
        initData();
        initEvent();
        return rootView;
    }


    private void openActivity(Class<?> clz) {
        startActivity(new Intent(getContext(), clz));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ll_operator_account, R.id.ll_operator_balance, R.id.ll_operator_bill, R.id.ll_operator_connect, R.id.ll_operator_settings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_operator_account:
                openActivity(OperatorInfoActivity.class);
                break;
            case R.id.ll_operator_balance:
                openActivity(BalanceInfoActivity.class);
                break;
            case R.id.ll_operator_bill:
                openActivity(BillInfoActivity.class);
                break;
            case R.id.ll_operator_connect:
                openActivity(ConnectPrinterActivity.class);
                break;
            case R.id.ll_operator_settings:
                openActivity(SettingActivity.class);
                break;
        }
    }
}
