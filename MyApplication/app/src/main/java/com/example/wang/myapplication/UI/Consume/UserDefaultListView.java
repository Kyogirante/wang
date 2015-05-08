package com.example.wang.myapplication.UI.Consume;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by wang on 2015/5/8.
 */
public class UserDefaultListView extends ListView{
    public UserDefaultListView(Context context) {
        super(context);
    }

    public UserDefaultListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserDefaultListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public UserDefaultListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
