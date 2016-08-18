package com.ycode.android.zhuanlanc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ycode.android.zhuanlanc.ContentActivity;
import com.ycode.android.zhuanlanc.R;
import com.ycode.android.zhuanlanc.adapter.LifeReAdapter;
import com.ycode.android.zhuanlanc.app.AppApiConstant;
import com.ycode.android.zhuanlanc.baseui.BaseFragment;
import com.ycode.android.zhuanlanc.bean.GirlBean;
import com.ycode.android.zhuanlanc.bean.TechBean;
import com.ycode.android.zhuanlanc.networks.GirlRetrofitSingle;
import com.ycode.android.zhuanlanc.networks.TechRetrofitSingle;
import com.ycode.android.zhuanlanc.util.ToastUtil;

import java.sql.Ref;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Author:    yangjiadong
 * Time :     2016/8/17
 * Email:      caibiy666@gmail.com
 */
public class LifeFragment extends BaseFragment {
    @Bind(R.id.fragment_life_recycleView)
    RecyclerView recyclerView;
    @Bind(R.id.fragment_life_SwipeContainer)
    SwipeRefreshLayout swipeContainer;
    private LifeReAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.life_layout,null);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }
    public void Refresh(){
        GirlRetrofitSingle.getInstance().onSub("福利", 30, new Random().nextInt(10)+1, new Subscriber<GirlBean>() {
            @Override
            public void onCompleted() {
                TechRetrofitSingle.getSinIns().setSubscriber(AppApiConstant.QUERY_LIFE, new Subscriber<TechBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TechBean techBean) {
                        List<TechBean.PostsBean> list=techBean.getPosts();
                        adapter.clearItems();
                        adapter.addItems(list);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GirlBean girlBean) {
                adapter.setGirlList(girlBean.getResults());
            }
        });

    }
    private void initData() {
       LifeReAdapter.mInterface mInterface=new LifeReAdapter.mInterface() {

           @Override
           public void LinClick(TechBean.PostsBean bean, GirlBean.ResultsBean GirlBean) {
               Intent intent=new Intent(mActivity, ContentActivity.class);
               intent.putExtra("desc",bean.getExcerpt());
               intent.putExtra("title",bean.getTitle());
               intent.putExtra("url",bean.getUrl());
                intent.putExtra("iurl",GirlBean.getUrl());
               startActivity(intent);
           }
       };
        adapter=new LifeReAdapter(mActivity,mInterface);

        GridLayoutManager gridLayout=new GridLayoutManager(mActivity,2);
        recyclerView.setLayoutManager(gridLayout);
        recyclerView.setAdapter(adapter);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_purple,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light
        );
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
                swipeContainer.setRefreshing(false);
            }
        });
        Refresh();
    }
}
