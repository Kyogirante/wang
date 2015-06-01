package com.example.wang.myapplication.UI.Demo.CheckView;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by wang on 2015/5/25.
 */
public class WaitingToPayTextView extends LinearLayout{

    public WaitingToPayTextView(Context context) {
        super(context);
    }

    public WaitingToPayTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //LayoutInflater.from(context).inflate(,null);
    }

    public WaitingToPayTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public WaitingToPayTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
