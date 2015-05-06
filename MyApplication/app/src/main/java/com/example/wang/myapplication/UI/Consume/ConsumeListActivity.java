package com.example.wang.myapplication.UI.Consume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.Bean.ConsumeBean;
import com.example.wang.myapplication.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2015/5/5.
 */
public class ConsumeListActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private ConsumeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_list);
        listView = (PullToRefreshListView)findViewById(R.id.consume_listView);
        listView.setAdapter(getAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consume_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            //handle edit event
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ConsumeListAdapter getAdapter(){
        adapter = new ConsumeListAdapter(getData(),this);
        return adapter;
    }

    private List<ConsumeBean> getData(){
        //get data from DB
        List<ConsumeBean> data = new ArrayList<>();
        ConsumeBean bean;
        for(int i = 0; i < 10; i ++){
            bean = new ConsumeBean();
            bean.setCost(i*2.0f);
            bean.setCategory(""+i);
            data.add(bean);
        }

        return data;
    }

    public static void intentAction(Context context){
        Intent intent = new Intent(context,ConsumeListActivity.class);
        context.startActivity(intent);
    }
}
