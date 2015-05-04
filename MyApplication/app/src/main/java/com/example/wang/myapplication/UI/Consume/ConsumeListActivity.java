package com.example.wang.myapplication.UI.Consume;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.R;

public class ConsumeListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_list);
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
}
