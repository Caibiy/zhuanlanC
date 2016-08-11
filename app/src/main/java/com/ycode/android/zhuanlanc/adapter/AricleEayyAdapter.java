package com.ycode.android.zhuanlanc.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.ycode.android.zhuanlanc.bean.CaibiyBean;

/**
 * Author:    yangjiadong
 * Time :     2016/8/11
 * Email:      caibiy666@gmail.com
 */
public class AricleEayyAdapter extends RecyclerArrayAdapter<CaibiyBean.PostsBean> {
    public AricleEayyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AricleViewHolder(parent);
    }
}
