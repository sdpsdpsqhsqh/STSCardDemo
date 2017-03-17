package com.laisontech.stscarddemo.ui.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.constant.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends Activity {
    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.cb_remeber_password)
    CheckBox cbRemeberPassword;
    @InjectView(R.id.cb_auto_login)
    CheckBox cbAutoLogin;
    @InjectView(R.id.tv_change_service_address)
    TextView tvChangeServiceAddress;
    @InjectView(R.id.login_form)
    LinearLayout loginForm;
    @InjectView(R.id.iv_account_delete)
    ImageView ivAccountDelete;
    @InjectView(R.id.iv_password_delete)
    ImageView ivPasswordDelete;
    //保存是在账号密码正确，且能够返回正确登录的数据
    private SharedPreferences mSp;
    private String mAccountPassword;
    private String namestr;
    private String passwordstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSp = getSharedPreferences(Constants.LOGIN_SP_NAME, Context.MODE_PRIVATE);
        boolean isAuto = mSp.getBoolean(Constants.LOGIN_AUTO, false);
        mAccountPassword = mSp.getString(Constants.LOGIN_AP, "");
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        if (isAuto) {
            //自动登录，并且账号密码不为空直接进行网络请求进入主界面
            setAP();
            startActivity(new Intent(this, MainActivity.class));
            cbRemeberPassword.setChecked(true);
            cbAutoLogin.setChecked(true);
        } else {
            if (!mAccountPassword.isEmpty()) {
                setAP();
                cbRemeberPassword.setChecked(true);
            }
        }
        initEvent();
    }

    private void setAP() {
        String[] aps = mAccountPassword.split("&");
        if (aps.length == 2) {
            name.setText(aps[0]);
            password.setText(aps[1]);
        }
    }

    private void initEvent() {
        cbAutoLogin.setOnCheckedChangeListener(autoListener);
        cbRemeberPassword.setOnCheckedChangeListener(remeberListener);
        setEditText();
    }

    private void setEditText() {
        name.setSelection(name.getText().toString().length());
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    ivAccountDelete.setVisibility(View.GONE);
                }else {
                    ivAccountDelete.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    ivPasswordDelete.setVisibility(View.GONE);
                }else {
                    ivPasswordDelete.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @OnClick({R.id.btn_login, R.id.tv_change_service_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                namestr = name.getText().toString().trim();
                passwordstr = password.getText().toString().trim();
                if (namestr.isEmpty() || passwordstr.isEmpty()) {
                    name.setText("");
                    this.password.setText("");
                    return;
                }

                if (cbAutoLogin.isChecked()) {
                    mSp.edit().putBoolean(Constants.LOGIN_AUTO, true).apply();
                    mSp.edit().putString(Constants.LOGIN_AP, namestr + "&" + passwordstr).apply();
                } else {
                    if (cbRemeberPassword.isChecked()) {
                        mSp.edit().putString(Constants.LOGIN_AP, namestr + "&" + passwordstr).apply();
                    } else {
                        mSp.edit().putString(Constants.LOGIN_AP, "").apply();
                    }
                    mSp.edit().putBoolean(Constants.LOGIN_AUTO, false).apply();
                }

                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tv_change_service_address:
                startActivity(new Intent(this, SetPortAddressActivity.class));
                break;
        }
    }

    private CompoundButton.OnCheckedChangeListener autoListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                setTextColor(cbAutoLogin, R.color.colorProject);
                cbRemeberPassword.setChecked(true);
                setTextColor(cbRemeberPassword, R.color.colorProject);
            } else {
                setTextColor(cbAutoLogin, R.color.colorCB);
            }
        }
    };
    private CompoundButton.OnCheckedChangeListener remeberListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                setTextColor(cbRemeberPassword, R.color.colorProject);

            } else {
                setTextColor(cbRemeberPassword, R.color.colorCB);
            }
        }
    };

    private void setTextColor(CheckBox cb, int color) {
        cb.setTextColor(getResources().getColor(color));
    }
}