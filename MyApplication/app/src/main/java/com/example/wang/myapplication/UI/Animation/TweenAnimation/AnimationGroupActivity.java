package com.example.wang.myapplication.UI.Animation.TweenAnimation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;

public class AnimationGroupActivity extends BaseActivity implements View.OnClickListener{

    private TextView animation_text;
    private Button scale_btn;
    private Button rotate_btn;
    private Button translate_btn;
    private Button alpha_btn;
    private Button circle_btn;
    private Button canvas_circle_btn;

    private TextView circle_text;
    private View circle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_group);

        animation_text = (TextView)findViewById(R.id.animation_text);
        circle_text = (TextView)findViewById(R.id.circle_text);

        circle_view = findViewById(R.id.circle_view);

        scale_btn = (Button)findViewById(R.id.scale_btn);
        rotate_btn = (Button)findViewById(R.id.rotate_btn);
        translate_btn = (Button)findViewById(R.id.translate_btn);
        alpha_btn = (Button)findViewById(R.id.alpha_btn);
        circle_btn = (Button)findViewById(R.id.circle_btn);
        canvas_circle_btn = (Button)findViewById(R.id.canvas_circle_btn);

        scale_btn.setOnClickListener(this);
        rotate_btn.setOnClickListener(this);
        translate_btn.setOnClickListener(this);
        alpha_btn.setOnClickListener(this);
        circle_btn.setOnClickListener(this);
        canvas_circle_btn.setOnClickListener(this);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scale_btn:
                AnimationSet scale_animation_set = new AnimationSet(true);
                //以自身为尺度缩放中心,从原大小扩张到1.5倍
                ScaleAnimation scale_animation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                scale_animation_set.addAnimation(scale_animation);
                scale_animation.setDuration(500);//1s钟
                scale_animation.setRepeatCount(Integer.MAX_VALUE);
                scale_animation.setRepeatMode(Animation.RESTART);
                animation_text.startAnimation(scale_animation_set);
//                Animation animation = AnimationUtils.loadAnimation(AnimationGroupActivity.this, R.anim.XXX);
//                xxView.setAnimation(animation);
                break;
            case R.id.translate_btn:
                AnimationSet translate_animation_set = new AnimationSet(true);
                //以自身为坐标系和长度单位，从(0,0)移动到(0,1) f表示倍数
                TranslateAnimation translate_animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 1f);
                translate_animation_set.addAnimation(translate_animation);
                translate_animation.setDuration(500);//1s钟
                animation_text.startAnimation(translate_animation_set);
                break;
            case R.id.alpha_btn:
                //这里设置ture参数表示共享interpolator
                AnimationSet alpha_animation_set = new AnimationSet(true);
                //从完全不透明到完全透明
                AlphaAnimation alpha_animation = new AlphaAnimation(1.0f, 0.0f);
                alpha_animation_set.addAnimation(alpha_animation);
                alpha_animation.setDuration(1000);//1s钟
                animation_text.startAnimation(alpha_animation_set);
                break;
            case R.id.rotate_btn:
                AnimationSet rotate_animation_set = new AnimationSet(true);
                //以自身中心为圆心，旋转360度
                RotateAnimation rotate_animation = new RotateAnimation(0, 360,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                rotate_animation_set.addAnimation(rotate_animation);
                rotate_animation.setDuration(1000);//1s钟
                animation_text.startAnimation(rotate_animation_set);
                break;
            case R.id.circle_btn:
//                AnimationSet scale_animation_set2 = new AnimationSet(true);
//                //以自身为尺度缩放中心,从原大小扩张到2倍
//                ScaleAnimation scale_animation2 = new ScaleAnimation(1.0f, 2f, 1.0f, 2f,
//                        Animation.RELATIVE_TO_SELF, 0.5f,
//                        Animation.RELATIVE_TO_SELF, 0.5f);
//                scale_animation_set2.addAnimation(scale_animation2);
//                scale_animation2.setDuration(500);//1s钟
//                scale_animation2.setRepeatCount(Integer.MAX_VALUE);
//                scale_animation2.setRepeatMode(Animation.RESTART);
                Animation scale_animation_set2 = AnimationUtils.loadAnimation(AnimationGroupActivity.this, R.anim.circle_scale);
                circle_text.startAnimation(scale_animation_set2);
                break;
            case R.id.canvas_circle_btn:

                break;
            default:
                break;
        }
    }

    public static void intentAction(Context context){
        Intent intent = new Intent(context, AnimationGroupActivity.class);
        context.startActivity(intent);
    }
}
