package com.ycode.android.zhuanlanc.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
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

import com.ycode.android.zhuanlanc.R;
import com.ycode.android.zhuanlanc.adapter.AndroidRecAdapter;
import com.ycode.android.zhuanlanc.baseui.BaseFragment;

import com.ycode.android.zhuanlanc.bean.AndroidBean;
import com.ycode.android.zhuanlanc.networks.AndroidRetrofitSingle;


import java.util.List;

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
    AndroidRecAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tech_layout,null);
        ButterKnife.bind(this,view);
        initData();
        return view;
    }
    public void Refresh(){

        AndroidRetrofitSingle.getSinIns().setSubscriber(mActivity, new Subscriber<AndroidBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(floatButton,"出错啦!"+e.getMessage(),Snackbar.LENGTH_LONG)
                        .setAction("Action",null).show();
            }

            @Override
            public void onNext(AndroidBean androidBean) {
                List<AndroidBean.PostsBean> listPost=androidBean.getPosts();
                adapter.clearItems();
                adapter.addItems(listPost);
                adapter.notifyDataSetChanged();
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                recyclerView.setAdapter(adapter);
            }
        });
    }
    private void initData() {
        adapter=new AndroidRecAdapter(mActivity);
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
}
