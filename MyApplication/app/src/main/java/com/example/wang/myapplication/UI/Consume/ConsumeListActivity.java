package com.example.wang.myapplication.UI.Consume;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.Bean.ConsumeBean;
import com.example.wang.myapplication.Dao.ConsumeDao;
import com.example.wang.myapplication.R;
import com.example.wang.myapplication.UI.Home.MainActivity;
import com.example.wang.myapplication.UI.View.ConsumeListItemView;
import com.example.wang.myapplication.Utils.AppUtils;
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
    private TextView desc_msg;

    private boolean showEditMenu = true;
    private boolean showMenu = true;

    private LinearLayout item_layout;
    private float XDownRaw = 0;
    private float XMoveRaw = 0;
    private float move_distance;
    private int xDown;
    private int yDown;
    private int itemPosition;

    private boolean SHOW_DEL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_list);
        desc_msg = (TextView)findViewById(R.id.desc_msg);

        add_btn = (Button)findViewById(R.id.btn_add);
        del_btn = (Button)findViewById(R.id.btn_del);

        add_btn.setOnClickListener(this);
        del_btn.setOnClickListener(this);

        listView = (UserDefaultListView)findViewById(R.id.consume_listView);
    }

    @Override
    public void onResume(){
        super.onResume();
        adapter = getAdapter();
        if(adapter.getCount() < 1){
            listView.setVisibility(View.GONE);
            desc_msg.setVisibility(View.VISIBLE);
            showMenu = false;
        } else {
            listView.setVisibility(View.VISIBLE);
            desc_msg.setVisibility(View.GONE);

            footerView = LayoutInflater.from(this).inflate(R.layout.consume_list_footer, null, false);
            listView.addFooterView(footerView);

            listView.setAdapter(getAdapter());

            listView.setOnTouchListener(onTouchListener);

            listView.setOnItemClickListener(onItemClickListener);

            listView.setOnItemLongClickListener(onItemLongClickListener);

            listView.setOnScrollListener(onScrollListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(showMenu){
            getMenuInflater().inflate(R.menu.menu_consume_allselect, menu);
            getMenuInflater().inflate(R.menu.menu_consume_list, menu);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        if(showMenu){
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
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_edit:
                Log.e(AppUtils.LOG_TAG, "menu action_edit");
                break;
            case R.id.action_all_select:
                Log.e(AppUtils.LOG_TAG, "menu ");
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
        //get data from DB;
        List<ConsumeBean> data = new ArrayList<>();
        data = ConsumeDao.queryAllConsume(getApplicationContext());
        return data;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Log.e(AppUtils.LOG_TAG, "click");
        int id = v.getId();
        switch (id){
            case R.id.btn_add:
                EditConsumeActivity.intentAction(this, null);
                overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
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
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    XDownRaw = event.getRawX();//相对屏幕坐标
                    xDown = (int)event.getX();
                    yDown = (int)event.getY();//相对容器坐标
                    itemPosition = listView.pointToPosition(xDown,yDown);//无效返回-1
                    ConsumeListItemView consumeListItemView = (ConsumeListItemView)listView.getChildAt(itemPosition);
                    if(consumeListItemView == null){
                        break;
                    }
                    item_layout = (LinearLayout)consumeListItemView.findViewById(R.id.item_layout);
                    break;
                case MotionEvent.ACTION_MOVE:
                    XMoveRaw = event.getRawX();
                    move_distance = XMoveRaw - XDownRaw;
                    SHOW_DEL = move_distance > 0 ? true : false;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    private AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            add_btn.setVisibility(View.GONE);
            del_btn.setVisibility(View.VISIBLE);
            ConsumeListActivity.this.invalidateOptionsMenu();
            return true;//true表示该触发事件已经处理完毕
        }
    };

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            EditConsumeActivity.intentAction(ConsumeListActivity.this,adapter.getList().get(i));
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
        }
    };

    //可以考虑直接把布局改成scrollview
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
