package com.ycode.android.zhuanlanc;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ycode.android.zhuanlanc.adapter.AricleEayyAdapter;
import com.ycode.android.zhuanlanc.adapter.AricleRecyclerAdapter;
import com.ycode.android.zhuanlanc.bean.CaibiyBean;
import com.ycode.android.zhuanlanc.networks.RetrofitSingle;
import com.ycode.android.zhuanlanc.util.NetWorkUtil;

import butterknife.Bind;

import butterknife.ButterKnife;
import rx.Subscriber;


public class MainActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener,RecyclerArrayAdapter.OnLoadMoreListener {
   @Bind(R.id.mainActivity_RecyclerView)
   EasyRecyclerView mRecyclerView;
    @Bind(R.id.custom_Toolbar)
    Toolbar toolbar;
    @Bind(R.id.no_network)
     LinearLayout noNetLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if(NetWorkUtil.isNetConnected(this)){
            RetrofitSingle retrofitSingle=RetrofitSingle.getSinIns();
            retrofitSingle.setSubscriber(this, new Subscriber<CaibiyBean>() {
                @Override
                public void onCompleted() {
                    Toast.makeText(MainActivity.this,"completed",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(CaibiyBean caibiyBean) {
                   // AricleRecyclerAdapter adapter=new AricleRecyclerAdapter(MainActivity.this);
                    AricleEayyAdapter adapter=new AricleEayyAdapter(MainActivity.this);
                    adapter.addAll(caibiyBean.getPosts());
                    //adapter.addItems(caibiyBean.getPosts());
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerView.setRefreshListener(MainActivity.this);
                    doWithZhuanLan(adapter);
                    mRecyclerView.setAdapterWithProgress(adapter);
                }
            });
        }else{
            noNetLinearLayout.setVisibility(View.VISIBLE);
        }

        };

    private void doWithZhuanLan(final RecyclerArrayAdapter<CaibiyBean.PostsBean> adapter) {
        mRecyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.load_more_layout, this);
        adapter.setNoMore(R.layout.no_more_layout);
        adapter.setError(R.layout.error_layout);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }
    @Override
    public void onRefresh() {
        Toast.makeText(MainActivity.this,"重新刷新数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(MainActivity.this,"加载更多",Toast.LENGTH_SHORT).show();
    }
}
