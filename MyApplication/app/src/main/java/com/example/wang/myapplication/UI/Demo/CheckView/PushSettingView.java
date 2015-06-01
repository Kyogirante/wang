package com.example.wang.myapplication.UI.Demo.CheckView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.wang.myapplication.R;

/**
 * Created by wang on 2015/5/25.
 */
public class PushSettingView extends View{
    public static interface SettingChangedListener{
        void onPushSettingChanged(int index);
    }

    private Bitmap bitmapPushChecked;
    private Bitmap bitmapPushNor;
    private Bitmap bitmapPushBackground;

    private int orange;
    private TextPaint textPaint;
    private int margin10,margin15;

    private static final String TEXT_1 = "数量较少";
    private static final String TEXT_2 = "数量适中";
    private static final String TEXT_3 = "数量较多";

    private static final String TOAST_TEXT_1 = "非常顺路，推送订单数量较少";
    private static final String TOAST_TEXT_2 = "比较顺路，推送订单数量适中";
    private static final String TOAST_TEXT_3 = "一般顺路，推送订单数量较多";

    private int gray;
    private Rect srcRect;
    private Rect destRect;

    private boolean text1Checked = false;
    private boolean text2Checked = true;
    private boolean text3Checked = false;

    private float settingDescSize;
    private float settingCheckDescSize;
    private float settingToastSize;
    private SettingChangedListener blankListener = new SettingChangedListener() {
        @Override
        public void onPushSettingChanged(int index) {

        }
    };

    private SettingChangedListener settingChangedListener = blankListener;

    public PushSettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PushSettingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PushSettingView(Context context) {
        super(context);
        init();
    }

    private void init(){
        Resources resources = getResources();
        bitmapPushChecked = BitmapFactory.decodeResource(resources, R.drawable.ic_flag_red);
        bitmapPushNor = BitmapFactory.decodeResource(resources, R.drawable.ic_flag_gray);
        bitmapPushBackground = BitmapFactory.decodeResource(resources,
                R.drawable.bg_push_setting_selector);

        orange = resources.getColor(R.color.orange);
        gray = resources.getColor(R.color.text_color_gray);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        settingDescSize = resources.getDimension(R.dimen.text_size_14);
        settingCheckDescSize = resources.getDimension(R.dimen.text_size_16);
        textPaint.setTextSize(settingDescSize);

        margin10 = resources.getDimensionPixelOffset(R.dimen.margin_padding_10);
        margin15 = resources.getDimensionPixelOffset(R.dimen.margin_padding_15);

        srcRect = new Rect(0,0,bitmapPushBackground.getWidth(),bitmapPushBackground.getHeight());
        destRect = new Rect();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec)+getPaddingLeft()+getPaddingRight();
        int height = (int) (bitmapPushChecked.getHeight()+ margin10
                + textPaint.getTextSize() + margin10/2
                + settingToastSize + margin10
                + getPaddingTop()
                +getPaddingBottom())
                ;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int left = (int) (textPaint.measureText(TEXT_1) / 2);
        int top = (bitmapPushChecked.getHeight() - bitmapPushBackground.getHeight()) / 2;
        int right = (int) (getMeasuredWidth()-textPaint.measureText(TEXT_3)/2);
        int bottom = top+bitmapPushBackground.getHeight();
        srcRect.set(0,0,bitmapPushBackground.getWidth(),bitmapPushBackground.getHeight());
        destRect.set(left , top ,right , bottom);
        canvas.drawBitmap(bitmapPushBackground,srcRect,destRect,null);

        drawCircleAndText(canvas, 0, TEXT_1, text1Checked);
        drawCircleAndText(canvas,
                (int) (getMeasuredWidth() / 2 - textPaint.measureText(TEXT_2)/2),
                TEXT_2,text2Checked);
        drawCircleAndText(canvas,
                (int) (getMeasuredWidth() - textPaint.measureText(TEXT_3)),
                TEXT_3,text3Checked);

        drawToast(canvas);
    }

    private void drawToast(Canvas canvas) {
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(settingToastSize);
        final String text = text1Checked? TOAST_TEXT_1:(text2Checked?TOAST_TEXT_2:TOAST_TEXT_3);
        float textWidth = textPaint.measureText(text);
        int width = (int) (margin10*2+ textWidth);
        int height = (int) (settingToastSize+margin10);
        final int dx = text1Checked? 0 :
                (text2Checked ?
                        (getMeasuredWidth() - width) / 2 :
                        getMeasuredWidth() - width
                );
        srcRect.set(0,0,1,1);
        destRect.set(0,0,width,height);
        canvas.save();

        //画Toast信息
//        int dy = (int) (bitmapPushChecked.getHeight()+margin10+
//                settingDescSize+margin10/2+bitmapTriangle.getHeight());
//        canvas.translate(dx,dy);
//        canvas.clipRect(destRect);
//        canvas.drawBitmap(bitmapToast, srcRect, destRect, null);
//        canvas.drawText(text,margin10,destRect.height()-margin10/2-3,textPaint);

        canvas.restore();
    }

    private void drawCircleAndText(Canvas canvas,
                                   int dx,String text,boolean checked){
        canvas.save();
        final Bitmap circle = checked? bitmapPushChecked:bitmapPushNor;
        final int color = checked?orange:gray;
        final float size = checked?settingCheckDescSize:settingDescSize;
        textPaint.setColor(color);
        textPaint.setTextSize(size);
        canvas.translate(dx,0);

        int settingDescBottom = (int) (circle.getHeight()+margin10+settingDescSize);
        canvas.drawText(text,0,text.length(),0,settingDescBottom,textPaint);
        float textWidth = textPaint.measureText(text);
        canvas.drawBitmap(circle, (textWidth -
                circle.getWidth()) / 2, 0, null);

        if (checked){
            //画三角
//            canvas.drawBitmap(bitmapTriangle,(textWidth -
//                    bitmapTriangle.getWidth()) / 2, settingDescBottom+margin10/2,null);
        }

        canvas.restore();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        if (action == MotionEvent.ACTION_DOWN){
            return true;
        }
        if (action == MotionEvent.ACTION_UP){
            float x = MotionEventCompat.getX(event, 0);
            if (isClickText1(x)){
                text1Checked = true;
                text2Checked = false;
                text3Checked = false;
                settingChangedListener.onPushSettingChanged(1);
            }else if (isClickText2(x)){
                text2Checked = true;
                text1Checked = false;
                text3Checked = false;
                settingChangedListener.onPushSettingChanged(2);
            }else if (isClickText3(x)){
                text3Checked = true;
                text2Checked = false;
                text1Checked = false;
                settingChangedListener.onPushSettingChanged(3);
            }
            invalidate();
            return true;
        }

        return super.onTouchEvent(event);
    }

    private boolean isClickText1(float x){
        return x>=0 && x<=textPaint.measureText(TEXT_1);
    }

    private boolean isClickText2(float x){
        float textWith = textPaint.measureText(TEXT_2);
        int screenMiddle = getMeasuredWidth()/2;
        return x>= screenMiddle-textWith/2 && x<= screenMiddle+textWith/2;
    }

    private boolean isClickText3(float x){
        return x>= getMeasuredWidth()-textPaint.measureText(TEXT_3);
    }

    public int getChecked(){
        return text1Checked?1:text2Checked?2:3;
    }

    public void setChecked(int index){
        text1Checked = index == 1;
        text2Checked = index == 2;
        text3Checked = index == 3;
        invalidate();
    }

    public static String getCheckText(int index){
        return index==1?TEXT_1:index== 2? TEXT_2:TEXT_3;
    }

    public SettingChangedListener getSettingChangedListener() {
        return settingChangedListener;
    }

    public void setSettingChangedListener(SettingChangedListener settingChangedListener) {
        if (settingChangedListener == null){
            this.settingChangedListener = blankListener;
        }else {
            this.settingChangedListener = settingChangedListener;
        }
    }
}
