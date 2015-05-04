package com.example.wang.myapplication.UI.AutoViewPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang.myapplication.R;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wang on 2015/4/24.
 */
public class AutoViewPageActivity extends Activity implements ViewPager.OnPageChangeListener {

    private String TAG = "WANG_TAG";
    private int currentItem = -1;
    private ArrayList<ImageView> imageViewList;
    private TextView tvDescription;
    private String[] imageDescriptions;
    private ViewPager mViewPager;
    private ScheduledExecutorService scheduledExecutorService;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPager.setCurrentItem(msg.arg1%imageViewList.size());
        }
    };

    @Override
    protected void onStart(){
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_view_page);
        initView();
    }

    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tvDescription = (TextView) findViewById(R.id.tv_image_description);

        prepareData();

        ViewPagerAdapter adapter = new ViewPagerAdapter(imageViewList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(this);

        tvDescription.setText(imageDescriptions[0]);

        mViewPager.setCurrentItem(0);
    }

    private void prepareData() {
        imageViewList = new ArrayList<ImageView>();
        int[] imageResIDs = getImageResIDs();
        imageDescriptions = getImageDescription();

        ImageView iv;
        for (int i = 0; i < imageResIDs.length; i++) {
            iv = new ImageView(this);
            iv.setBackgroundResource(imageResIDs[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewList.add(iv);
        }
    }

    private int[] getImageResIDs() {
        return new int[]{
                R.drawable.bg1,
                R.drawable.bg2,
                R.drawable.bg3,
        };
    }

    private String[] getImageDescription() {
        return new String[]{
                "第一个引导页面",
                "第二个引导页面",
                "第三个引导页面",
        };
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        Log.v(TAG, "arg0------>" + String.valueOf(arg0));
        Log.v(TAG,"arg1------>" + String.valueOf(arg1));
        Log.v(TAG,"arg2------>" + String.valueOf(arg2));

    }

    @Override
    public void onPageSelected(int position) {
            tvDescription.setText(imageDescriptions[position]);
            if(currentItem!=position){
                currentItem = position;
            }

    }

    @Override
    protected void onStop(){
        super.onStop();
        scheduledExecutorService.shutdown();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void intentAction(Context context){
        Intent intent = new Intent(context, AutoViewPageActivity.class);
        context.startActivity(intent);
    }

    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (AutoViewPageActivity.this) {
                currentItem ++;
                Message msg = new Message();
                msg.arg1 = currentItem;
                handler.sendMessage(msg);
            }
        }

    }
}
