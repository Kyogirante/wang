package com.example.wang.myapplication.UI.View;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wang.myapplication.R;
import com.example.wang.myapplication.Utils.AppUtils;

/**
 * Created by wang on 2015/5/5.
 */
public class ConsumeListItemView extends LinearLayout implements View.OnClickListener, View.OnTouchListener{

    private int consume_id;
    private TextView consume_cost;
    private TextView consume_category;
    private TextView consume_describe_msg;

    private LinearLayout item_Layout;
    private TextView consume_del_item;

    public ConsumeListItemView(Context context) {
        super(context);
    }

    public ConsumeListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConsumeListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public ConsumeListItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate(){
        inflate(getContext(), R.layout.consume_listview_item,this);
        consume_cost = (TextView)findViewById(R.id.consume_cost);
        consume_category = (TextView)findViewById(R.id.consume_category);
        consume_describe_msg = (TextView)findViewById(R.id.consume_describe_msg);

        item_Layout = (LinearLayout)findViewById(R.id.item_layout);
        consume_del_item = (TextView)findViewById(R.id.consume_del_item_textView);
        consume_cost.setOnClickListener(this);
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b){
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
    }

    public TextView getConsume_cost() {
        return consume_cost;
    }

    public TextView getConsume_category() {
        return consume_category;
    }

    public TextView getConsume_describe_msg() {
        return consume_describe_msg;
    }

    public void setId(int id){
        this.consume_id = id;
    }

    public static ConsumeListItemView build(Context context){
        ConsumeListItemView itemView = new ConsumeListItemView(context);
        itemView.onFinishInflate();
        return itemView;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.consume_cost:
                new AlertDialog.Builder(getContext(),AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                        .setTitle("具体信息")
                        .setMessage(getShowMsg())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.v(AppUtils.LOG_TAG, "consume id = " + consume_id);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return false;
    }

    public String getShowMsg(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("消费: ");
        stringBuilder.append(consume_cost.getText().toString());
        stringBuilder.append("TT类别: ");
        stringBuilder.append(consume_category.getText().toString());
        stringBuilder.append("TT备注: ");
        stringBuilder.append(consume_describe_msg.getText().toString());
        return stringBuilder.toString().replaceAll("\n","").replaceAll("TT", "\n");
    }

}
