package com.laisontech.stscarddemo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.adapter.HomeTabAdapter;
import com.laisontech.stscarddemo.base.BaseFragment;
import com.laisontech.stscarddemo.bean.HomeTab;
import com.laisontech.stscarddemo.ui.activity.BuyActivity;
import com.laisontech.stscarddemo.ui.activity.GoToBuyActivity;
import com.laisontech.stscarddemo.ui.activity.ReadCardActivity;
import com.laisontech.stscarddemo.view.CircleMenuLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by SDP on 2017/3/13.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    ImageView operatorIcon;
    TextView tvOperatorNum;
    TextView tvOperatorAccount;
    private GridView mGridView;
    private int[] images = new int[]{R.drawable.readcard,R.drawable.buy1,R.drawable.other};
    private String[] tabs = new String[]{"读卡","购买","其他"};
    private List<HomeTab> homeTabs;
    private HomeTabAdapter mAdapter;
    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews(View contentView) {
        operatorIcon = (ImageView) contentView.findViewById(R.id.operator_icon);
        tvOperatorAccount = (TextView) contentView.findViewById(R.id.tv_operator_account);
        tvOperatorNum = (TextView) contentView.findViewById(R.id.tv_operator_num);
        mGridView = (GridView) contentView.findViewById(R.id.girdview);
    }

    @Override
    protected int setContentViewID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        homeTabs = new ArrayList<>();
        for(int i=0;i<images.length;i++){
            HomeTab homeTab = new HomeTab();
            homeTab.setResId(images[i]);
            homeTab.setTab(tabs[i]);
            homeTabs.add(homeTab);
        }
    }

    @Override
    protected void initEvent() {
        mAdapter = new HomeTabAdapter(getContext(),homeTabs);
        mGridView.setAdapter(mAdapter);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.operator_icon:
                //TODO 打开照片或者选择照片替换，并且上传至服务器，先从服务器检查有无照片，没有设置默认的，有加载当前照片
                break;
        }
    }
}
