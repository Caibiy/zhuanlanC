package com.ycode.android.zhuanlanc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycode.android.zhuanlanc.R;
import com.ycode.android.zhuanlanc.bean.CaibiyBean;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:    yangjiadong
 * Time :     2016/8/11
 * Email:      caibiy666@gmail.com
 */
public class AricleRecyclerAdapter extends BaseRecyclerAdapter<CaibiyBean.PostsBean > {
    int[] pictures={R.drawable.log1,R.drawable.log2,R.drawable.log3,R.drawable.log4};
    public AricleRecyclerAdapter(Context context) {
        super(context);
        Log.i("ViewHolder","false--------false--------false");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new mViewHolder(mLayInflater.inflate(R.layout.main_item_layout,parent,false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof mViewHolder){
            CaibiyBean.PostsBean postsBean = mDataList.get(position);
            ((mViewHolder)holder).tv_Title.setText(postsBean.getTitle());
            ((mViewHolder)holder).tv_Intro.setText(postsBean.getExcerpt().replaceAll("\\<p>|</p>",""));
            ((mViewHolder)holder).tv_Date.setText(postsBean.getDate());
            ((mViewHolder)holder).im_Logo.setImageResource(pictures[position%4]);
            ((mViewHolder)holder).tv_Cato.setText("Android");
            Log.i("ViewHolder","true--------true--------true");
        }
        Log.i("ViewHolder","true--------true--------false");
    }
    static class mViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.mainItem_TvTitle)
        TextView tv_Title;
        @Bind(R.id.mainItem_TvIntro)
        TextView tv_Intro;
        @Bind(R.id.mainItem_TvDate)
        TextView tv_Date;
        @Bind(R.id.mainItem_TvCato)
        TextView tv_Cato;
        @Bind(R.id.mainItem_ImLogo)
        ImageView im_Logo;
        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
