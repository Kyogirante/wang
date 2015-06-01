package com.example.wang.myapplication.UI.Demo.CheckView;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;

/**
 * Created by wang on 2015/5/25.
 */
public class CheckTestActivity extends BaseActivity implements View.OnClickListener{

    private Button btn1;
    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_test);
        btn1 = (Button)findViewById(R.id.btn1);
        text1 = (TextView)findViewById(R.id.text1);

        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Log.v("wang","click");
                if(text1.getVisibility() == View.GONE){
                    revealAnimationToVisibility();
                } else {
                    revealAnimationToGone();
                }

                break;
            default:
                break;
        }
    }

    private void revealAnimationToVisibility(){
        int cx = text1.getWidth()/2;//这个坐标是相对于view内部的坐标(0,0)=左上角
        int cy = text1.getHeight()/2;

        int finalRadius = Math.max(text1.getWidth(),text1.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(text1,cx,cy,0,finalRadius);
        text1.setVisibility(View.VISIBLE);
        anim.setDuration(1000);
        anim.start();
    }

    private void revealAnimationToGone(){
        int cx = text1.getWidth()/2;//这个坐标是相对于view内部的坐标(0,0)=左上角
        int cy = text1.getHeight()/2;

        int initialRadius = text1.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(text1, cx, cy, initialRadius, 0);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                text1.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        anim.setDuration(1000);
        anim.start();
    }
    public static void intentAction(Context context){
        Intent intent = new Intent(context, CheckTestActivity.class);
        context.startActivity(intent);
    }

}
