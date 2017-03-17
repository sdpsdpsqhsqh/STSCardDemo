package com.laisontech.stscarddemo.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
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
public class CompanyWebViewActivity extends Activity implements View.OnTouchListener {
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
    private float mFirstTouchY;
    private float mCurrentTouchY;
    private float mTouchSlop;
    private Animator mAnimatorTitle;
    private Animator mAnimatorContent;
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
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        showHideLl(true);
    }

    protected void initEvent() {
        mTvWebAddress.setText(mLinkUrl);
        mProgressWebView.loadUrl(mLinkUrl);
        mProgressWebView.setOnTouchListener(this);
    }

    @OnClick({R.id.iv_back_wed, R.id.iv_refresh_web})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_wed:
                mProgressWebView.destroyDrawingCache();
                finish();
                break;
            case R.id.iv_refresh_web:
                mProgressWebView.refreshWeb();
                break;
        }
    }

    private void showHideLl(boolean b) {
        if (mAnimatorTitle!=null&&mAnimatorTitle.isRunning()){
            mAnimatorTitle.cancel();
        }
        if (mAnimatorContent!=null&&mAnimatorContent.isRunning()){
            mAnimatorTitle.cancel();
        }
        if (b){
            mAnimatorTitle = ObjectAnimator.ofFloat(mLlTopWed,"translationY"
                    ,mLlTopWed.getTranslationY(),0);
            mAnimatorContent = ObjectAnimator.ofFloat(mProgressWebView,"translationY"
                    ,mProgressWebView.getTranslationY(),
                    getResources().getDimension(R.dimen.ll_web_height));
        }else {
            mAnimatorTitle = ObjectAnimator.ofFloat(mLlTopWed, "translationY",
                    mLlTopWed.getTranslationY(), -mLlTopWed.getHeight());
            mAnimatorContent = ObjectAnimator.ofFloat(mProgressWebView, "translationY",
                    mProgressWebView.getTranslationY(),0);
        }
        mAnimatorTitle.start();
        mAnimatorContent.start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstTouchY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentTouchY = event.getY();
                if (mCurrentTouchY - mFirstTouchY > mTouchSlop) {
                    //下滑动
                    showHideLl(true);
                } else if (mFirstTouchY - mCurrentTouchY > mTouchSlop) {
                    //上滑动
                    showHideLl(false);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
