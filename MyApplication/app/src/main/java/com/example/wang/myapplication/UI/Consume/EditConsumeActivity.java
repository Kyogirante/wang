package com.example.wang.myapplication.UI.Consume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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
import com.example.wang.myapplication.Utils.Strings;

public class EditConsumeActivity extends BaseActivity implements View.OnClickListener{
    private ConsumeBean bean;
    private Button save_btn;
    private EditText cost_edit;
    private EditText category_edit;
    private EditText describe_msg_edit;

    private boolean isUpdate = false;

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
            cost_edit.setText(String.valueOf(bean.getCost()));
            category_edit.setText(bean.getCategory());
            describe_msg_edit.setText(bean.getDescribe_msg());
            isUpdate = true;
        } else {
            bean = new ConsumeBean();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isUpdate){
            getMenuInflater().inflate(R.menu.menu_edit_consume, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_del:
                boolean flag = ConsumeDao.deleteConsume(getApplicationContext(), bean);
                if(flag){
                    showShortMsgToast("删除成功~");
                }
                finishActivity();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        finishActivity();
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
                if(Strings.isNulOrEmpty(cost_edit.getText().toString())){
                    showShortMsgToast("消费不能为空~");
                    return;
                }
                bean.setCost(Double.valueOf(cost_edit.getText().toString()));
                bean.setCategory(getTextViewString(category_edit.getText()));
                bean.setDescribe_msg(getTextViewString(describe_msg_edit.getText()));
                if(!isUpdate){
                    long flag = ConsumeDao.insertConsume(getApplicationContext(), bean);
                    if(flag >= 0){
                        showShortMsgToast("保存成功~");
                        finishActivity();
                    }
                } else {
                    boolean flag = ConsumeDao.updateConsume(getApplicationContext(),bean);
                    if(flag){
                        showShortMsgToast("修改成功~");
                        finishActivity();
                    }
                }
                break;
            default:
                break;
        }
    }

    private String getTextViewString(Editable editable){
        return Strings.isNulOrEmpty(editable.toString()) ? "待定" : editable.toString();
    }

    public static void intentAction(Context context,ConsumeBean bean){
        Intent intent = new Intent(context, EditConsumeActivity.class);
        intent.setAction(IntentActionUtils.TO_EDIT);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KeyUtils.COMSUMEB_EAN,bean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private void finishActivity(){
        Intent intent = new Intent(this, ConsumeListActivity.class);
        intent.setAction(IntentActionUtils.TO_CONSUME_LIST);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }
}
