package com.example.wang.myapplication.UI.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.UI.Animation.AnimationActivity;
import com.example.wang.myapplication.UI.Animation.Arcs;
import com.example.wang.myapplication.UI.AutoViewPage.AutoViewPageActivity;
import com.example.wang.myapplication.UI.Consume.ConsumeListActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button viewpager_btn;
    private Button listview_btn;
    private Button animation_btn;
    private Button arcs_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager_btn = (Button)findViewById(R.id.viewpager_btn);
        listview_btn = (Button)findViewById(R.id.listview_btn);
        animation_btn =(Button)findViewById(R.id.animation_btn);
        arcs_btn = (Button)findViewById(R.id.arcs_btn);

        viewpager_btn.setOnClickListener(this);
        listview_btn.setOnClickListener(this);
        animation_btn.setOnClickListener(this);
        arcs_btn.setOnClickListener(this);

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
            case R.id.animation_btn:
                AnimationActivity.intentAction(this);
                break;
            case R.id.arcs_btn:
                Intent intent = new Intent(this, Arcs.class);
                startActivity(intent);
            default:
                break;
        }
        overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
    }
}
