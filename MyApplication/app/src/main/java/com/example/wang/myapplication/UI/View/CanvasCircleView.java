package com.example.wang.myapplication.UI.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.wang.myapplication.R;

/**
 * Created by wang on 2015/5/14.
 */
public class CanvasCircleView extends View{
    private final static float RADIUS = 100f;
    private final static float MAX_RADIUS = 400f;

    private Paint mPaint;
    private Paint circlePaint1;
    private Paint circlePaint2;
    private Paint textPaint;
    private Paint linePaint;

    private float increase_length_one;
    private float increase_length_two;

    public CanvasCircleView(Context context) {
        super(context);

    }

    public CanvasCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        increase_length_one = 0;
        increase_length_two = (RADIUS-MAX_RADIUS)/2.0f;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.light_red));
        mPaint.setStyle(Paint.Style.FILL);//填充

        circlePaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint1.setColor(getResources().getColor(R.color.dark_red));
        circlePaint1.setStyle(Paint.Style.STROKE);
        circlePaint1.setStrokeWidth(5f);

        circlePaint2 = new Paint(circlePaint1);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(getResources().getColor(R.color.text_color_white));
        textPaint.setTextSize(getResources().getDimension(R.dimen.text_size_18));

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(getResources().getColor(R.color.blue));
    }

    public CanvasCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public CanvasCircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        increase_length_one += 5;
        increase_length_two += 5;
        if(increase_length_one > MAX_RADIUS - RADIUS){
            increase_length_one = 0;
        }
        if(increase_length_two > MAX_RADIUS - RADIUS){
            increase_length_two = 0;
        }

        drawCircle(canvas);
        drawText(canvas);
        invalidate();
    }

    private void drawCircle(Canvas canvas){

        setCircleAlpha();
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, RADIUS + increase_length_one, circlePaint1);
        if(increase_length_two >= 0){
            canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, RADIUS + increase_length_two, circlePaint2);
        }

        canvas.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight(), linePaint);
        canvas.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2, linePaint);
    }

    //设置成255在最外圈的时候回变成不透明
    private void setCircleAlpha(){
        circlePaint1.setAlpha(256 - (int)(255 * (increase_length_one / (MAX_RADIUS - RADIUS))));
        if(increase_length_two > 0){
            circlePaint2.setAlpha(256 - (int)(255*(increase_length_two/(MAX_RADIUS-RADIUS))));
        }

    }
    private void drawText(Canvas canvas){
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, RADIUS, mPaint);
        canvas.drawText("确定",
                this.getWidth()/2 - textPaint.measureText("确定")/2,
                this.getHeight()/2 + getTextHeight(textPaint)/2,
                textPaint);
    }

    private float getTextHeight(Paint paint){
        return (float)Math.sqrt(paint.getTextSize()*paint.getTextSize()/2);
    }
}
