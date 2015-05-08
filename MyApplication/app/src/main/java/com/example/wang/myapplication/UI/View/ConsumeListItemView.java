package com.example.wang.myapplication.UI.View;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wang.myapplication.Bean.ConsumeBean;
import com.example.wang.myapplication.R;

/**
 * Created by wang on 2015/5/5.
 */
public class ConsumeListItemView extends LinearLayout implements View.OnClickListener{

    private TextView consume_cost;

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
    public void onFinishInflate(){
        inflate(getContext(), R.layout.consume_listview_item,this);
        consume_cost = (TextView)findViewById(R.id.consume_cost);
        consume_cost.setOnClickListener(this);
        super.onFinishInflate();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b){
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
    }

    public void setData(ConsumeBean bean){
        consume_cost.setText(String.valueOf(bean.getCost()));
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
                consume_cost.setText("u click m");
                break;
            default:
                break;
        }
    }
}
