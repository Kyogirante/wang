package com.example.wang.myapplication.UI.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.UI.Animation.AnimationDirectoryActivity;
import com.example.wang.myapplication.UI.AutoViewPage.AutoViewPageActivity;
import com.example.wang.myapplication.UI.Consume.ConsumeListActivity;
import com.example.wang.myapplication.UI.Demo.DemoMainActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button viewpager_btn;
    private Button listview_btn;
    private Button animation_btn;
    private Button test_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager_btn = (Button)findViewById(R.id.viewpager_btn);
        listview_btn = (Button)findViewById(R.id.listview_btn);
        animation_btn = (Button)findViewById(R.id.animation_directory_btn);
        test_btn = (Button)findViewById(R.id.test_btn);

        viewpager_btn.setOnClickListener(this);
        listview_btn.setOnClickListener(this);
        animation_btn.setOnClickListener(this);
        test_btn.setOnClickListener(this);

        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.viewpager_btn:
                AutoViewPageActivity.intentAction(this);
                break;
            case R.id.listview_btn:
                ConsumeListActivity.intentAction(this);
                break;
            case R.id.animation_directory_btn:
                AnimationDirectoryActivity.intentAction(this);
                break;
            case R.id.test_btn:
                DemoMainActivity.intentAction(this);
                break;
            default:
                break;
        }
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
    }
}
