
package com.example.wang.myapplication.UI.Demo.CheckView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wang.myapplication.BaseActivity;
import com.example.wang.myapplication.Bean.ConsumeBean;
import com.example.wang.myapplication.Dao.ConsumeDao;
import com.example.wang.myapplication.R;

import java.util.List;

/**
 * Created by wang on 2015/6/9.
 */
public class DetailActivity extends BaseActivity{
    public final static String SHARED_IMAGE = "detail:image";

    private ImageView imageView;
    private RecyclerView recyclerView;//recyclerview不建议和srollerview一起使用，如果将其放在srollerview中，必须设置其高度，不然不显示
    private SwipeRefreshLayout refreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter adapter;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            refreshLayout.setEnabled(linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
        }
    };

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView)findViewById(R.id.sharedImage);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshLayout);

        initView();
        //这句话有必要，用于分辨动画中规定的元素，确定是唯一名称
        //ViewCompat.setTransitionName(imageView, SHARED_IMAGE);
        imageView.setTransitionName(SHARED_IMAGE);
    }

    private void initView(){
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);

                new CountDownTimer(2000,2000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        refreshLayout.setRefreshing(false);
                    }
                }.start();
            }
        });
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setOnScrollListener(onScrollListener);

        adapter = new MyAdapter(getData());
        recyclerView.setAdapter(adapter);

    }

    private List<ConsumeBean> getData(){
        return ConsumeDao.queryAllConsume(getApplicationContext());
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private List<ConsumeBean> mList;

        public MyAdapter(List<ConsumeBean> list){
            mList = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Log.v("wang", "conCreateViewHolder");
            View itemView = LayoutInflater.from(DetailActivity.this).inflate(R.layout.consume_listview_item,viewGroup,false);
            MyViewHolder viewHolder = new MyViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            Log.v("wang","onBindViewHolder");
            myViewHolder.cost.setText(String.valueOf(mList.get(i).getCost()));
            myViewHolder.category.setText(mList.get(i).getCategory());
            myViewHolder.describe_msg.setText(mList.get(i).getDescribe_msg());
        }

        @Override
        public int getItemCount() {
            return mList != null ? mList.size() : 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cost;
        TextView category;
        TextView describe_msg;
        public MyViewHolder(View itemView) {
            super(itemView);
            cost = (TextView)itemView.findViewById(R.id.consume_cost);
            category = (TextView)itemView.findViewById(R.id.consume_category);
            describe_msg = (TextView)itemView.findViewById(R.id.consume_describe_msg);
        }
    }
}
