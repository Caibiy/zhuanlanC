 package com.ycode.android.zhuanlanc.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycode.android.zhuanlanc.R;
import com.ycode.android.zhuanlanc.bean.AndroidBean;
import com.ycode.android.zhuanlanc.util.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:    yangjiadong
 * Time :     2016/8/16
 * Email:      caibiy666@gmail.com
 */
public class AndroidRecAdapter extends  BaseRecyclerAdapter<AndroidBean.PostsBean> {
   /*  int[]mPosition;*/
   private final static int FADE_DURATION = 2000; // in milliseconds
    private int lastPosition=-1;
    private Context context;
    public AndroidRecAdapter(Context context) {
        super(context);
        this.context=context;
    }
  /*  public void initPositions(){
       int size=getItemCount();
        mPosition=new int[]{};
        for(int i=0;i<size;i++){
            mPosition[i]=i;
        }
        Log.i("Count",mPosition.length+"xxxxx");
    }*/
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new AndroidViewHolder(mLayInflater.inflate(R.layout.fragment_android_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof  AndroidViewHolder){
                ((AndroidViewHolder) holder).tv_Cato.setText(NetWorkUtil.getCate(getItemData(position).getCategories()));
                ((AndroidViewHolder) holder).tv_Date.setText(NetWorkUtil.FormatTime(getItemData(position).getDate()));//
                ((AndroidViewHolder) holder).tv_Title.setText(getItemData(position).getTitle());
                ((AndroidViewHolder) holder).tv_Intro.setText(NetWorkUtil.ReplaceHtmlP(getItemData(position).getExcerpt()));
/*
                setAnimation(((AndroidViewHolder) holder).cardView,position);

*/
            setFadeAnimation(((AndroidViewHolder) holder).cardView);
            }
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim2 = new AlphaAnimation(0.0f, 1.0f);
        anim2.setDuration(FADE_DURATION);
       /* ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        AnimationSet set=new AnimationSet(true);
        set.addAnimation(anim);
        set.addAnimation(anim2);*/
        view.startAnimation(anim2);
    }
    /*private void setAnimation(View viewToAnnimate,int postition){
        if(postition>lastPosition){
            Animation animation= AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
            viewToAnnimate.startAnimation(animation);
            lastPosition=postition;
        }
    }*/
    //ViewHolder
     static class AndroidViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.cardViewContainer)
        CardView cardView;
        @Bind(R.id.fragment_android_TvTitle)
        TextView tv_Title;
        @Bind(R.id.fragment_android_TvCato)
        TextView tv_Cato;
        @Bind(R.id.fragment_android_TvDate)
        TextView tv_Date;
        @Bind(R.id.fragment_android_TvIntro)
        TextView tv_Intro;
        @Bind(R.id.fragment_android_ImLogo)
        ImageView im_Logo;
        public AndroidViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
