package com.example.wang.myapplication.UI.Animation.Cirlce;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wang on 2015/5/13.
 */
public class MyCircleAnimationView extends View {
    public final static float RADIUS = 48f;

    private float increase_length = 0f;

    private Point circle_center;

    private Paint mPaint;


    public MyCircleAnimationView(Context context) {
        super(context);
    }

    public MyCircleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        circle_center = getCircle_center();
    }

    public MyCircleAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public MyCircleAnimationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onDraw(Canvas canvas){
        if(mPaint == null){
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(4);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas){
        canvas.translate(this.getWidth()/2,this.getHeight()/2f);
        canvas.drawCircle(circle_center.getX(), circle_center.getY(), RADIUS + increase_length, mPaint);
    }

    private void startAnimation(){
        ValueAnimator animator = ValueAnimator.ofObject( new RadiusEvaluator(), RADIUS, 4*RADIUS);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                increase_length = (float) animation.getAnimatedValue();
                if(increase_length == 2 * RADIUS){
                    increase_length = 0;
                }
                invalidate();
            }
        });

        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setDuration(1000);
        animator.start();

//        AnimatorSet animSet = new AnimatorSet();
//        animSet.play(animator);
//        animSet.start();

    }

    private Point getCircle_center(){
        int[] location = new int[2];
        this.getLocationOnScreen(location);
        Point circle_center = new Point(location[0] + this.getWidth()/2f, location[1] + this.getHeight()/2f);
        return circle_center;
    }
}
