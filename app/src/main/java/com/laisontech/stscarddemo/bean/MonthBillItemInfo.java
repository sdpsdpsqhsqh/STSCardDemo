package com.laisontech.stscarddemo.bean;

import java.io.Serializable;

/**
 * Created by SDP on 2017/3/16.
 */
public class MonthBillItemInfo implements Serializable{
    private String date;
    private String rechargeMoney;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(String rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    @Override
    public String toString() {
        return "MonthBillItemInfo{" +
                "date='" + date + '\'' +
                ", rechargeMoney='" + rechargeMoney + '\'' +
                '}';
    }
}
