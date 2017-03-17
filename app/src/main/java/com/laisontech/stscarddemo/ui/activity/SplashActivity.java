package com.laisontech.stscarddemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.laisontech.stscarddemo.R;
import com.laisontech.stscarddemo.app.BaseApplication;
import com.laisontech.stscarddemo.constant.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by SDP on 2017/3/10.
 */
public class SplashActivity extends Activity {
    @InjectView(R.id.iv_company_logo)
    ImageView ivCompanyLogo;
    @InjectView(R.id.tv_company_name)
    TextView tvCompanyName;
    @InjectView(R.id.tv_version)
    TextView tvVersion;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp = BaseApplication.mFirstInstallSP;
        ButterKnife.inject(this);
        ivCompanyLogo.setVisibility(View.INVISIBLE);
        setVersionInfo();
        setAnimation();
    }

    private void setVersionInfo() {
       PackageManager packageManager =  getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            if (packageInfo!=null){
                tvVersion.setText("当前版本 "+packageInfo.versionName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setAnimation() {
        Animation nameAnim = AnimationUtils.loadAnimation(this, R.anim.anim_app_name);
        final Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.anim_app_icon);
        tvCompanyName.setAnimation(nameAnim);
        nameAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivCompanyLogo.setAnimation(logoAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        logoAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean isInstall = sp.getBoolean(Constants.FIRST_INSTALL, false);
                        if (isInstall){
                            startActivity(LoginActivity.class);
                        }else {
                            startActivity(IntroduceActivity.class);
                        }
                    }
                },500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });


    }

    private void startActivity(Class clz) {
        startActivity(new Intent(this, clz));
        finish();
    }
}
