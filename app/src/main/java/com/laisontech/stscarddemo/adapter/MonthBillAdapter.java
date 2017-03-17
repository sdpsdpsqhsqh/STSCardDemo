package com.laisontech.stscarddemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.adapter.base_adapter.CommonAdapter;
import com.laisontech.stscarddemo.adapter.base_adapter.CommonViewHolder;
import com.laisontech.stscarddemo.bean.MonthBillItemInfo;
import com.laisontech.stscarddemo.constant.Constants;
import com.laisontech.stscarddemo.ui.activity.SingleRechargeInfoActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by SDP on 2017/3/16.
 */
public class MonthBillAdapter extends CommonAdapter<MonthBillItemInfo> {
    private Context context;
    public MonthBillAdapter(Context mContext, List<MonthBillItemInfo> mData) {
        super(mContext, mData);
        context = mContext;
    }

    @Override
    public int layoutId() {
        return R.layout.adapter_month_bill;
    }

    @Override
    public void convert(CommonViewHolder holder, final MonthBillItemInfo monthBillItemInfo,int po) {
        //该date可以是用户充值到账的系统时间这样比较简单操作
        String date = monthBillItemInfo.getDate();
        String rechargeMoney = monthBillItemInfo.getRechargeMoney();
        ((TextView) holder.getView(R.id.tv_weekday_bill_month)).setText(getWeekOfDate(date));
        ((TextView) holder.getView(R.id.tv_time_bill_month)).setText(getTime(date));
        ((TextView) holder.getView(R.id.tv_money_bill_month)).setText("-" + rechargeMoney + "￥");
        holder.getView(R.id.ll_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SingleRechargeInfoActivity.class);
                intent.putExtra(Constants.MONTH_BILL_ITEM_INF0,monthBillItemInfo);
                context.startActivity(intent);
            }
        });
    }

    private String getWeekOfDate(String dateStr) {
        String str[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        long time = Long.parseLong(dateStr);
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week0fDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week0fDay < 0) {
            week0fDay = 0;
        }
        if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1==week0fDay){
            return "今天";
        }else if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-2==week0fDay){
            return "昨天";
        }
        return str[week0fDay];
    }
    private String getTime(String dateStr){
        long time = Long.parseLong(dateStr);
        Date date = new Date(time);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }
}
