package com.laisontech.stscarddemo.bean;

/**
 * Created by SDP on 2017/3/16.
 */
public class HomeTab {
    private int resId;
    private String tab;

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "HomeTab{" +
                "resId=" + resId +
                ", tab='" + tab + '\'' +
                '}';
    }
}
