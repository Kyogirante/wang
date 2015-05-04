package com.example.wang.myapplication.UI.Consume;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2015/5/4.
 */
public abstract class ListViewAdapter<T> extends BaseAdapter{

    private List<T> mList;

    public ListViewAdapter(){

    }

    public ListViewAdapter(List<T> mList){
        this.mList = mList;
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

    public List<T> getmList() {
        return mList;
    }

    public void setmList(List<T> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void setList(T[] list){
        List<T> arrayList = new ArrayList<T>(list.length);
        for(T t:list){
            arrayList.add(t);
        }
        setmList(arrayList);
    }
    //use to cache list view
    public final class ViewHold{

    }
}
