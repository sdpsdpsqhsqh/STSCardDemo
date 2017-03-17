package com.laisontech.stscarddemo.bean;

/**
 * Created by SDP on 2017/3/14.
 */
public class PayMethod {
    private String name;
    private int icon;
    private String introduce;

    public PayMethod(String name, int icon, String introduce) {
        this.name = name;
        this.icon = icon;
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "PayMethod{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
