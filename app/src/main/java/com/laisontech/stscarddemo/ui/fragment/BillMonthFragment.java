package com.laisontech.stscarddemo.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.base.BaseFragment;
import com.laisontech.stscarddemo.ui.activity.MonthSalesInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDP on 2017/3/14.
 */
public class BillMonthFragment extends BaseFragment {

//    private TextView mTotalSales;
//    private TextView mTotalIncome;
    private TextView mTvSeeInfo;
    private PieChart mPieChart;
    private String salesStr;
    private String income;
    private List<PieEntry> pieDataEntities;
    private float sales;

    @Override
    protected int setContentViewID() {
        return R.layout.fragment_bill_month;
    }

    @Override
    protected void initViews(View contentView) {
//        mTotalSales = (TextView) contentView.findViewById(R.id.tv_total_sales);
//        mTotalIncome = (TextView) contentView.findViewById(R.id.tv_total_income);
        mTvSeeInfo = (TextView) contentView.findViewById(R.id.tv_touch_see_more_info);
        mPieChart = (PieChart) contentView.findViewById(R.id.pieChart_bill);
    }

    @Override
    protected void initData() {
        //通过解析网络数据将数据添加进去
        Bundle arguments = getArguments();
        String month = arguments.getString("MONTH");
        int index = month.indexOf("月");
        salesStr = month.substring(0, index);
        sales = Integer.parseInt(salesStr) * 100;
        income = month.substring(index + 2, month.length());

        pieDataEntities = new ArrayList<>();
        PieEntry entityIncome = new PieEntry(Integer.parseInt(income),"总收入");
        PieEntry entitySales = new PieEntry(sales,"总售水");
        pieDataEntities.add(entityIncome);
        pieDataEntities.add(entitySales);

        PieData pieData = new PieData();
        PieDataSet dataSet = new PieDataSet(pieDataEntities," 单位:元");
        dataSet.setColors(Color.parseColor("#FF1874CD"), Color.RED,Color.BLACK);
        pieData.setDataSet(dataSet);
        pieData.setValueTextSize(18);
        pieData.setValueTextColor(Color.WHITE);

        mPieChart.setDescription(null);
        mPieChart.setDrawHoleEnabled(false);
        Legend legend = mPieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        mPieChart.setData(pieData);
    }

    @Override
    protected void initEvent() {
//        mTotalSales.setText(sales+"");
//        mTotalIncome.setText(income);
        mTvSeeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Month",salesStr);
                openActivity(bundle,MonthSalesInfoActivity.class);
            }
        });
    }
}
