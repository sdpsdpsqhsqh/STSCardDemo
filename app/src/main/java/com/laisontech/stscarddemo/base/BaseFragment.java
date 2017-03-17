package com.laisontech.stscarddemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laisontech.stscarddemo.app.BaseApplication;

import butterknife.ButterKnife;

/**
 * Created by SDP on 2017/3/10.
 */
public abstract class BaseFragment extends Fragment{

    public static Activity mContext;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        setOtherMethodBeforeLoadingContentView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(setContentViewID(),container,false);
        initViews(contentView);
        return contentView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
    }

    //处理在加载布局前需要设置的方法等
    protected void setOtherMethodBeforeLoadingContentView() {}
    protected abstract @LayoutRes int setContentViewID();
    protected abstract void initViews(View contentView);
    protected abstract void initData();
    protected abstract void initEvent();

    protected void openActivity(Class<?> clz){
        getActivity().startActivity(new Intent(getContext(),clz));
    }
    protected void openActivity(Bundle bundle,Class<?> clz){
        Intent intent = new Intent(getContext(), clz);
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }
}
