package com.ycode.android.zhuanlanc.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ycode.android.zhuanlanc.ContentActivity;
import com.ycode.android.zhuanlanc.R;
import com.ycode.android.zhuanlanc.adapter.BaseRecyclerAdapter;
import com.ycode.android.zhuanlanc.adapter.TechRecAdapter;
import com.ycode.android.zhuanlanc.app.AppApiConstant;
import com.ycode.android.zhuanlanc.baseui.BaseFragment;

import com.ycode.android.zhuanlanc.bean.GirlBean;
import com.ycode.android.zhuanlanc.bean.TechBean;
import com.ycode.android.zhuanlanc.networks.GirlRetrofitSingle;
import com.ycode.android.zhuanlanc.networks.TechRetrofitSingle;


import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Author:    yangjiadong
 * Time :     2016/8/15
 * Email:      caibiy666@gmail.com
 */
public class AndroidFragment extends BaseFragment {

    @Bind(R.id.fragment_android_floatButton)
    FloatingActionButton floatButton;
    @OnClick(R.id.fragment_android_floatButton)
    public void Click(View v){
        Animator animator= AnimatorInflater.loadAnimator(mActivity,R.animator.animator_rotation);
        animator.setTarget(v);
        animator.setInterpolator(new CycleInterpolator(1));
        animator.start();
    Refresh();
    }
    @Bind(R.id.fragment_android_recycleView)
    RecyclerView recyclerView;
    @Bind(R.id.SwipeContainer)
    SwipeRefreshLayout swipeContainer;
    TechRecAdapter adapter;
    List<GirlBean.ResultsBean>GirlList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tech_layout,null);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }
    public void Refresh(){
        GirlRetrofitSingle.getInstance().onSub("福利", 30,new Random().nextInt(10)+1, new Subscriber<GirlBean>() {
            @Override
            public void onCompleted() {
                TechRetrofitSingle.getSinIns().setSubscriber(AppApiConstant.QUERY_ANDROID, new Subscriber<TechBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Snackbar.make(floatButton,"内容无法获取",Snackbar.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onNext(TechBean androidBean) {
                        List<TechBean.PostsBean> listPost=androidBean.getPosts();
                        adapter.clearItems();
                        adapter.addItems(listPost);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(floatButton,"妹子无法获取",Snackbar.LENGTH_LONG)
                        .show();

            }

            @Override
            public void onNext(GirlBean girlBean) {
              GirlList=girlBean.getResults();
              adapter.setGirlList(GirlList);
              adapter.notifyDataSetChanged();
            }
        });

    }

    private void initData() {
        TechRecAdapter.myInterface mInter=new TechRecAdapter.myInterface(){

            @Override
            public void rootViewClick(TechBean.PostsBean postsBean, GirlBean.ResultsBean girlBean) {
                Intent intent=new Intent(mActivity, ContentActivity.class);
                intent.putExtra("desc",postsBean.getExcerpt());
                intent.putExtra("title",postsBean.getTitle());
                intent.putExtra("url",postsBean.getUrl());
                intent.putExtra("iurl",girlBean.getUrl());
                startActivity(intent);
            }
        };
        adapter=new TechRecAdapter(mActivity,mInter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(adapter);
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Refresh();
                swipeContainer.setRefreshing(false);
            }
        });

        Refresh();

    }
    public void jumpActivity(Intent intent, BaseRecyclerAdapter adapter, int position){

    }
}
