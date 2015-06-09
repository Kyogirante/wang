
package com.example.wang.myapplication.UI.Demo.CheckView;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;

/**
 * Created by wang on 2015/6/9.
 */
public class DetailActivity extends BaseActivity{
    public final static String SHARED_IMAGE = "detail:image";

    private ImageView imageView;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView)findViewById(R.id.sharedImage);

        //这句话有必要，用于分辨动画中规定的元素，确定是唯一名称
        ViewCompat.setTransitionName(imageView, SHARED_IMAGE);
    }
}
