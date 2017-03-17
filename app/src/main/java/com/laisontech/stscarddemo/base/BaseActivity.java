package com.laisontech.stscarddemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by SDP on 2017/3/10.
 */
public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOtherMethodBeforeLoadingContentView();
        //填充布局
        setContentView(setContentViewID());
        //初始化控件
        ButterKnife.inject(this);
        //加载资源
        initData();
        //控件等的操作事件
        initEvent();
    }
    //处理在加载布局前需要设置的方法等
    protected void setOtherMethodBeforeLoadingContentView() {}
    protected abstract @LayoutRes int setContentViewID();
    protected abstract void initData();
    protected abstract void initEvent();
    protected void openActivity(Bundle bundle,Class<?> clz){
        Intent intent = new Intent(this,clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    protected void openActivity(Class<?> clz){
        Intent intent = new Intent(this,clz);
        startActivity(intent);
    }
}
