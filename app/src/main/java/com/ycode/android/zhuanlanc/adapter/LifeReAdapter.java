package com.ycode.android.zhuanlanc.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ycode.android.zhuanlanc.R;
import com.ycode.android.zhuanlanc.bean.GirlBean;
import com.ycode.android.zhuanlanc.bean.TechBean;
import com.ycode.android.zhuanlanc.util.NetWorkUtil;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:    yangjiadong
 * Time :     2016/8/17
 * Email:      caibiy666@gmail.com
 */
public class LifeReAdapter extends BaseRecyclerAdapter<TechBean.PostsBean> {
    private mInterface myInter;
    private List<GirlBean.ResultsBean> GirlList;
    private Context context;
    private final static int FADE_DURATION = 1000; // in milliseconds
    public LifeReAdapter(Context context,mInterface myInter) {
        super(context);
        this.myInter=myInter;
        this.context=context;
    }
    public void setGirlList(List<GirlBean.ResultsBean>GirlList){
        this.GirlList=GirlList;
    }
    private int position=0;


    public static interface mInterface{
        public void LinClick(TechBean.PostsBean bean,GirlBean.ResultsBean GirlBean);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new LifeViewHolder(mLayInflater.inflate(R.layout.fragment_life_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof  LifeViewHolder){
           ((LifeViewHolder) holder).tv_Title.setText(getItemData(position).getTitle());
            ((LifeViewHolder) holder).tv_Intro.setText(NetWorkUtil.ReplaceHtmlP(getItemData(position).getContent()));
            ((LifeViewHolder) holder).cardViewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    myInter.LinClick(getItemData(position),GirlList.get(position));
                }
            });

                Glide.with(context).load(GirlList.get(position).getUrl())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(((LifeViewHolder) holder).im_Pic);

            setFadeAnimation(((LifeViewHolder) holder).cardViewContainer);

        }
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim2 = new AlphaAnimation(0.0f, 1.0f);
        anim2.setDuration(FADE_DURATION);
        view.startAnimation(anim2);
    }
    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        if(holder instanceof LifeViewHolder){
            ((LifeViewHolder) holder).cardViewContainer.clearAnimation();
        }
    }
    public static class LifeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cardViewContainer)
        CardView cardViewContainer;
        @Bind(R.id.fragment_life_item_Image)
        ImageView im_Pic;
        @Bind(R.id.fragment_life_item_Title)
        TextView tv_Title;
        @Bind(R.id.fragment_life_item_Intro)
        TextView tv_Intro;
        public LifeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }
}
