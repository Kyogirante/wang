package com.example.wang.myapplication.Utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2015/5/4.
 */
public abstract class ArrayListAdapter<T> extends BaseAdapter{

    private List<T> mList;
    private Context mContext;

    public ArrayListAdapter(){

    }

    public ArrayListAdapter(List<T> mList, Context context){
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList == null ? null : mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public abstract View getView(int i, View view, ViewGroup viewGroup);

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(T[] list){
        List<T> arrayList = new ArrayList<T>(list.length);
        for(T t:list){
            arrayList.add(t);
        }
        setList(arrayList);
    }
}
