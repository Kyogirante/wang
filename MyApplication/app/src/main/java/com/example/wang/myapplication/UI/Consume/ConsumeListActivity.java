package com.example.wang.myapplication.UI.Consume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by wang on 2015/5/5.
 */
public class ConsumeListActivity extends BaseActivity {

    private PullToRefreshListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_list);
        listView = (PullToRefreshListView)findViewById(R.id.consume_listView);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getData()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consume_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            //TODO handle edit event
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String[] getData(){
        //TODO get data from DB
        String[] strs = new String[] {
                "first", "second", "third", "fourth", "fifth"
        };//定义一个String数组用来显示ListView的内容
        return strs;
    }

    public static void intentAction(Context context){
        Intent intent = new Intent(context,ConsumeListActivity.class);
        context.startActivity(intent);
    }
}
