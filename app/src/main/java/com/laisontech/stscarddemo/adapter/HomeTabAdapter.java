package com.laisontech.stscarddemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.adapter.base_adapter.CommonAdapter;
import com.laisontech.stscarddemo.adapter.base_adapter.CommonViewHolder;
import com.laisontech.stscarddemo.bean.HomeTab;
import com.laisontech.stscarddemo.ui.activity.BuyActivity;
import com.laisontech.stscarddemo.ui.activity.GoToBuyActivity;
import com.laisontech.stscarddemo.ui.activity.ReadCardActivity;

import java.util.List;

/**
 * Created by SDP on 2017/3/16.
 */
public class HomeTabAdapter extends CommonAdapter<HomeTab>{
    private Context mContext;
    public HomeTabAdapter(Context mContext, List<HomeTab> mData) {
        super(mContext, mData);
        this.mContext = mContext;
    }

    @Override
    public int layoutId() {
        return R.layout.adapter_home_tab;
    }

    @Override
    public void convert(CommonViewHolder holder, HomeTab homeTab, final int position) {
        ((TextView)holder.getView(R.id.tv_home_tab)).setText(homeTab.getTab());
        ((ImageView)holder.getView(R.id.iv_home_tab)).setImageResource(homeTab.getResId());
       LinearLayout ll = holder.getView(R.id.ll_home_tab);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                   mContext.startActivity(new Intent(mContext,ReadCardActivity.class));
                }else if(position==1){
                    mContext.startActivity(new Intent(mContext,GoToBuyActivity.class));
                }else if(position==2){

                }
            }
        });
    }
}
