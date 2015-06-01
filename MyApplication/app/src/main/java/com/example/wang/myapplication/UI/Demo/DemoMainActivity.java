package com.example.wang.myapplication.UI.Demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.UI.Demo.CheckView.CheckTestActivity;

/**
 * Created by wang on 2015/5/25.
 */
public class DemoMainActivity extends BaseActivity implements View.OnClickListener{

    private Button check_view_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_main);
        check_view_btn = (Button)findViewById(R.id.check_view_btn);

        check_view_btn.setOnClickListener(this);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.check_view_btn:
                CheckTestActivity.intentAction(this);
                break;
            default:
                break;
        }
    }

    public static void intentAction(Context context){
        Intent intent = new Intent(context, DemoMainActivity.class);
        context.startActivity(intent);
    }
}
