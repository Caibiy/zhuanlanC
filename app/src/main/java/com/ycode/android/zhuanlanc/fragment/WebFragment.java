package com.ycode.android.zhuanlanc.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;

import com.ycode.android.zhuanlanc.R;
import com.ycode.android.zhuanlanc.baseui.BaseFragment;
import com.ycode.android.zhuanlanc.bean.AndroidBean;
import com.ycode.android.zhuanlanc.networks.AndroidRetrofitSingle;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Author:    yangjiadong
 * Time :     2016/8/15
 * Email:      caibiy666@gmail.com
 */
public class WebFragment extends BaseFragment {
    @Bind(R.id.fragment_android_recycleView)
    RecyclerView recyclerView;
    @Bind(R.id.SwipeContainer)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.fragment_android_floatButton)
    FloatingActionButton floatingActionButton;
    @OnClick(R.id.fragment_android_floatButton)
    public void click(View v){
        Animator animator= AnimatorInflater.loadAnimator(mActivity,R.animator.animator_rotation);
        animator.setTarget(v);
        animator.setInterpolator(new CycleInterpolator(1));
        animator.start();
        Refresh();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tech_layout,null);
        return view;
    }

    public void Refresh(){
        AndroidRetrofitSingle.getSinIns().setSubscriber(mActivity, new Subscriber<AndroidBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AndroidBean androidBean) {

            }
        });

    }


}
