package com.example.wang.myapplication.UI.Animation.CanvasCircle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;

public class CanvasCircleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_circle);
    }

    public static void intentAction(Context context){
        Intent intent = new Intent(context, CanvasCircleActivity.class);
        context.startActivity(intent);
    }
}
