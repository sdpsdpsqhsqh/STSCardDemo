package com.laisontech.stscarddemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.view.ProgressWebView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/17.
 */
public class CompanyWebViewActivity extends Activity {
    @InjectView(R.id.progress_webView)
    ProgressWebView mProgressWebView;
    @InjectView(R.id.iv_back_wed)
    ImageView mIvBackWed;
    @InjectView(R.id.tv_web_address)
    TextView mTvWebAddress;
    @InjectView(R.id.iv_refresh_web)
    ImageView mIvRefreshWeb;
    @InjectView(R.id.ll_top_wed)
    LinearLayout mLlTopWed;
    private String mLinkUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.inject(this);
        initData();
        initEvent();
    }

    protected void initData() {
        mLinkUrl = getIntent().getStringExtra("LINK_URL");
    }

    protected void initEvent() {
        mTvWebAddress.setText(mLinkUrl);
        mProgressWebView.loadUrl(mLinkUrl);
    }

    @OnClick({R.id.iv_back_wed, R.id.iv_refresh_web})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_wed:
                mProgressWebView.destroyDrawingCache();
                mProgressWebView.removeAllViews();
                finish();
                break;
            case R.id.iv_refresh_web:
                mProgressWebView.refreshWeb();
                break;
        }
    }
}
