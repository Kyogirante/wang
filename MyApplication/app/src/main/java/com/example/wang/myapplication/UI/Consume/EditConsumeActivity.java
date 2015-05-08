package com.example.wang.myapplication.UI.Consume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.Bean.ConsumeBean;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.Utils.IntentActionUtils;
import com.example.wang.myapplication.Utils.KeyUtils;

public class EditConsumeActivity extends BaseActivity {
    private ConsumeBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_consume);
        Intent intent = this.getIntent();
        bean = (ConsumeBean)intent.getSerializableExtra(KeyUtils.COMSUMEB_EAN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_consume, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_del) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void intentAction(Context context,ConsumeBean bean){
        Intent intent = new Intent(context, EditConsumeActivity.class);
        intent.setAction(IntentActionUtils.TO_EDIT);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KeyUtils.COMSUMEB_EAN,bean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
