package com.example.wang.myapplication.UI.Consume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.Bean.ConsumeBean;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.UI.Home.MainActivity;
import com.example.wang.myapplication.Utils.IntentActionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2015/5/5.
 */
public class ConsumeListActivity extends BaseActivity implements View.OnClickListener{

    private UserDefaultListView listView;
    private ConsumeListAdapter adapter;
    private View footerView;
    private Button add_btn;
    private Button del_btn;
    private boolean showEditMenu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_list);
        add_btn = (Button)findViewById(R.id.btn_add);
        del_btn = (Button)findViewById(R.id.btn_del);
        listView = (UserDefaultListView)findViewById(R.id.consume_listView);

        footerView = LayoutInflater.from(this).inflate(R.layout.consume_list_footer, null, false);
        listView.addFooterView(footerView);

        add_btn.setOnClickListener(this);
        del_btn.setOnClickListener(this);

        listView.setAdapter(getAdapter());

        listView.setOnItemClickListener(onItemClickListener);

        listView.setOnItemLongClickListener(onItemLongClickListener);

        listView.setOnScrollListener(onScrollListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consume_allselect, menu);
        getMenuInflater().inflate(R.menu.menu_consume_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.menu.menu_consume_list:
                break;
            case R.menu.menu_consume_allselect:
                break;
            default:
                break;
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

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_add:
                EditConsumeActivity.intentAction(this, null);
                break;
            case R.id.btn_del:
                showLengthMsgToast("u click btn_del");
                add_btn.setVisibility(View.VISIBLE);
                del_btn.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        if(showEditMenu){
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(true);
            showEditMenu = false;
        } else {
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(false);
            //需要修改设置参数地方
            showEditMenu = true;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
        this.finish();
        overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out);
    }
    /**
     * define listener
     */
    private AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            add_btn.setVisibility(View.GONE);
            del_btn.setVisibility(View.VISIBLE);
            ConsumeListActivity.this.invalidateOptionsMenu();
            return true;
        }
    };

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            EditConsumeActivity.intentAction(ConsumeListActivity.this,adapter.getList().get(i));
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
        }
    };

    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {

        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if(visibleItemCount == totalItemCount){
                footerView.setVisibility(View.GONE);
            }else{
                footerView.setVisibility(View.VISIBLE);
            }
        }
    };

    public static void intentAction(Context context){
        Intent intent = new Intent(context, ConsumeListActivity.class);
        intent.setAction(IntentActionUtils.TO_CONSUME_LIST);
        context.startActivity(intent);
    }

}
