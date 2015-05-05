package com.example.wang.myapplication.UI.Consume;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wang.myapplication.Utils.Bean;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.UI.View.ConsumeListItemView;
import com.example.wang.myapplication.Utils.ArrayListAdapter;

/**
 * Created by wang on 2015/5/5.
 */
public class ConsumeListAdapter extends ArrayListAdapter<Bean>{

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ConsumeListItemView itemView;
        if(convertView == null){
            itemView = ConsumeListItemView.build(getContext());
            viewHolder = new ViewHolder();
            viewHolder.cost = (TextView)convertView.findViewById(R.id.consume_cost);
            convertView.setTag(viewHolder);
        }else {
            itemView = (ConsumeListItemView)convertView;
            viewHolder =(ViewHolder)convertView.getTag();
        }
        itemView.setData(viewHolder);
        return itemView;
    }

    //use to cache list view
    public final class ViewHolder{
        public TextView cost;
    }
}
