package com.example.wang.myapplication.UI.Consume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.Bean.ConsumeBean;
import com.example.wang.myapplication.Dao.ConsumeDao;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.Utils.IntentActionUtils;
import com.example.wang.myapplication.Utils.KeyUtils;

public class EditConsumeActivity extends BaseActivity implements View.OnClickListener{
    private ConsumeBean bean;
    private Button save_btn;
    private EditText cost_edit;
    private EditText category_edit;
    private EditText describe_msg_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_consume);

        cost_edit = (EditText)findViewById(R.id.cost_edit);
        category_edit = (EditText)findViewById(R.id.category_edit);
        describe_msg_edit = (EditText)findViewById(R.id.describe_msg_edit);

        save_btn = (Button)findViewById(R.id.btn_save);
        save_btn.setOnClickListener(this);

        Intent intent = this.getIntent();
        if(intent.getSerializableExtra(KeyUtils.COMSUMEB_EAN) != null){
            bean = (ConsumeBean)intent.getSerializableExtra(KeyUtils.COMSUMEB_EAN);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_consume, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_del:
                break;
            case android.R.id.home:
                Intent intent = new Intent(this, ConsumeListActivity.class);
                intent.setAction(IntentActionUtils.TO_CONSUME_LIST);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                this.startActivity(intent);
                this.finish();
                overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_save:
                ConsumeBean bean = new ConsumeBean();
                bean.setCost(Double.valueOf(cost_edit.getText().toString()));
                bean.setCategory(category_edit.getText().toString());
                bean.setDescribe_msg(describe_msg_edit.getText().toString());
                ConsumeDao.insertConsume(getApplicationContext(),bean);
                break;
            default:
                break;
        }
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
