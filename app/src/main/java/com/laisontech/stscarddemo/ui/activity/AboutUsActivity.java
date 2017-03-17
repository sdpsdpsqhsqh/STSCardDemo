package com.laisontech.stscarddemo.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseActivity;
import com.laisontech.stscarddemo.constant.Constants;
import com.laisontech.stscarddemo.utils.SystemSettingUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/15.
 */
public class AboutUsActivity extends BaseActivity {

    @InjectView(R.id.id_txt_version)
    TextView idTxtVersion;
    @InjectView(R.id.tv_call_phone_our_company)
    TextView tvCallPhoneOurCompany;
    @InjectView(R.id.tv_view_company_web_address)
    TextView tvViewCompanyWebAddress;
    @InjectView(R.id.iv_link)
    ImageView ivLink;
    @InjectView(R.id.iv_facebook)
    ImageView ivFacebook;
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

    @OnClick({R.id.tv_call_phone_our_company, R.id.tv_view_company_web_address,R.id.iv_link, R.id.iv_facebook})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_call_phone_our_company:
                //TODO 启动打电话的View
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Constants.COMPANY_TELEPHONE_NUMBER));
                startActivity(intent);
                break;
            case R.id.tv_view_company_web_address:
                //// TODO: 打开我们的网址
                openActivity(Constants.COMPANY_WEB_ADDRESS);
                break;

            case R.id.iv_link:
                openActivity(Constants.COMPANY_LINK_ADDRESS);
                break;
            case R.id.iv_facebook:
                openActivity(Constants.COMPANY_FACE_BOOK_ADDRESS);
                break;
        }
    }
    private void openActivity(String str){
        Intent intent = new Intent(this,CompanyWebViewActivity.class);
        intent.putExtra("LINK_URL",str);
        startActivity(intent);
    }
}
