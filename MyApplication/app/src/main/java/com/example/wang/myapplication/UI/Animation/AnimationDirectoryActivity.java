package com.example.wang.myapplication.UI.Animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.UI.Animation.AnimationBackground.AnimationActivity;
import com.example.wang.myapplication.UI.Animation.AnimationDemo.Arcs;
import com.example.wang.myapplication.UI.Animation.CanvasCircle.CanvasCircleActivity;
import com.example.wang.myapplication.UI.Animation.TweenAnimation.AnimationGroupActivity;

public class AnimationDirectoryActivity extends BaseActivity implements View.OnClickListener{

    private Button animation_btn;
    private Button arcs_btn;
    private Button animation_group_btn;
    private Button canvas_animation_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_directory_acitivity);

        animation_btn =(Button)findViewById(R.id.animation_btn);
        arcs_btn = (Button)findViewById(R.id.arcs_btn);
        animation_group_btn = (Button)findViewById(R.id.animation_group_btn);
        canvas_animation_btn = (Button)findViewById(R.id.canvas_animation_btn);

        animation_btn.setOnClickListener(this);
        arcs_btn.setOnClickListener(this);
        animation_group_btn.setOnClickListener(this);
        canvas_animation_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.animation_btn:
                AnimationActivity.intentAction(this);
                break;
            case R.id.arcs_btn:
                Intent intent = new Intent(this, Arcs.class);
                startActivity(intent);
                break;
            case R.id.animation_group_btn:
                AnimationGroupActivity.intentAction(this);
                break;
            case R.id.canvas_animation_btn:
                CanvasCircleActivity.intentAction(this);
                break;
            default:
                break;
        }
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
    }

    public static void intentAction(Context context){
        Intent intent = new Intent(context, AnimationDirectoryActivity.class);
        context.startActivity(intent);
    }
}
