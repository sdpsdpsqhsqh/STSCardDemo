package com.laisontech.stscarddemo.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.laisontech.stscarddemo.constant.Constants;

/**
 * Created by SDP on 2017/3/10.
 */
public class BaseApplication extends Application{
    private static BaseApplication mInstance;
    public static Context mContext;
    public static SharedPreferences mFirstInstallSP;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        if (mContext==null){
            mContext = getApplicationContext();
        }
        mFirstInstallSP = getSharedPreferences(Constants.FIRST_INSTALL_SP_NAME,Context.MODE_PRIVATE);
    }

    public static BaseApplication getInstance() {
        if (mInstance==null) {
            mInstance = new BaseApplication();
        }
        return mInstance;
    }
}
