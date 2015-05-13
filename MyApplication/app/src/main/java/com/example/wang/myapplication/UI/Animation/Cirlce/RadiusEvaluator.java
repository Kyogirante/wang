package com.example.wang.myapplication.UI.Animation.Cirlce;

import android.animation.TypeEvaluator;

/**
 * Created by wang on 2015/5/13.
 */
public class RadiusEvaluator implements TypeEvaluator{

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        float start = (float)startValue;
        float end = (float)endValue;
        return start + fraction * ( end - start);
    }
}
