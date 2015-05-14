package com.example.wang.myapplication.UI.Animation.AnimationBackground;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.UI.Animation.AnimationDirectoryActivity;
import com.example.wang.myapplication.Utils.IntentActionUtils;

public class AnimationActivity extends BaseActivity{

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView = (ImageView)findViewById(R.id.animation_image_view);

        imageView.setBackgroundResource(R.drawable.spin_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable)imageView.getBackground();
        frameAnimation.start();

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, AnimationDirectoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }

    public static void intentAction(Context context){
        Intent intent = new Intent(context, AnimationActivity.class);
        intent.setAction(IntentActionUtils.TO_ANIMATION);
        context.startActivity(intent);
    }
}
