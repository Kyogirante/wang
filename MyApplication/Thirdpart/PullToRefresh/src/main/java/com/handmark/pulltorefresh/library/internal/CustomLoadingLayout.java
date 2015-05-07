package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

/**
 * Created by wang on 2015/5/7.
 */
public class CustomLoadingLayout extends LoadingLayout{


    public CustomLoadingLayout(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        if(attrs.hasValue(R.styleable.PullToRefresh_ptrFooterLayout)){
            int layoutId = attrs.getResourceId(R.styleable.PullToRefresh_ptrFooterLayout,-1);
            if(layoutId == -1){
                layoutId = R.layout.pull_to_refresh_header_vertical;
            }
            LayoutInflater.from(context).inflate(layoutId,this);
        }
    }

    /**
     * Callbacks for derivative Layouts
     */
    @Override
    protected int getDefaultDrawableResId() {
        return 0;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {

    }

    @Override
    protected void refreshingImpl() {

    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {

    }
}
