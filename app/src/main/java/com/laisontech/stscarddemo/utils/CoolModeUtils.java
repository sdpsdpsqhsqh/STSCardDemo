package com.laisontech.stscarddemo.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SDP on 2017/3/17.
 */
public class CoolModeUtils {
    public static Animator getTranslationAnim(ViewGroup viewGroup,String propertyName, float values){
        return ObjectAnimator.ofFloat(viewGroup,"translationY",viewGroup.getTranslationY(),values);
    }
    public static Animator getTranslationAnim(View view,String propertyName, float values){
        return ObjectAnimator.ofFloat(view,"translationY",view.getTranslationY(),values);
    }
}
