package com.example.wang.myapplication.UI.Consume;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wang.myapplication.Bean.ConsumeBean;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.UI.View.ConsumeListItemView;
import com.example.wang.myapplication.Utils.ArrayListAdapter;

import java.util.List;

/**
 * Created by wang on 2015/5/5.
 */
public class ConsumeListAdapter extends ArrayListAdapter<ConsumeBean>{

    public ConsumeListAdapter(){
        super();
    }

    public ConsumeListAdapter(List<ConsumeBean> mList, Context context){
        super(mList,context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConsumeBean bean = getList().get(position);
        ConsumeListItemView itemView;
        ViewHolder viewHolder;
        if(convertView == null){
            itemView = ConsumeListItemView.build(getContext());
            viewHolder = new ViewHolder();
            viewHolder.cost = (TextView)itemView.findViewById(R.id.consume_cost);
            viewHolder.category =(TextView)itemView.findViewById(R.id.consume_category);
            viewHolder.describe_msg = (TextView)itemView.findViewById(R.id.consume_describe_msg);
            itemView.setTag(viewHolder);
        }else {
            itemView = (ConsumeListItemView)convertView;
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.cost.setText("¥" + "\n" + String.valueOf(bean.getCost()));
        viewHolder.category.setText(bean.getCategory());
        viewHolder.describe_msg.setText(bean.getDescribe_msg());

        itemView.setId(bean.getId());
        return itemView;
    }

    public class ViewHolder{
        public TextView cost;
        public TextView category;
        public TextView describe_msg;
    }

}
