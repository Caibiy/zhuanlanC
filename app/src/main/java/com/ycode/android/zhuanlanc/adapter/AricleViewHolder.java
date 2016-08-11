package com.ycode.android.zhuanlanc.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.ycode.android.zhuanlanc.R;
import com.ycode.android.zhuanlanc.bean.CaibiyBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:    yangjiadong
 * Time :     2016/8/11
 * Email:      caibiy666@gmail.com
 */
public class AricleViewHolder extends BaseViewHolder<CaibiyBean.PostsBean> {
    int[] pictures={R.drawable.log1,R.drawable.log2,R.drawable.log3,R.drawable.log4};
    TextView tv_Title;
    TextView tv_Intro;
    TextView tv_Date;
    TextView tv_Cato;
    ImageView im_Logo;

    public AricleViewHolder(ViewGroup parent) {
        super(parent, R.layout.main_item_layout);
        tv_Cato=$(R.id.mainItem_TvCato);
        tv_Date=$(R.id.mainItem_TvDate);
        tv_Intro=$(R.id.mainItem_TvIntro);
        tv_Title=$(R.id.mainItem_TvTitle);
        im_Logo=$(R.id.mainItem_ImLogo);
    }


    @Override
    public void setData(CaibiyBean.PostsBean data) {
        super.setData(data);
      tv_Title.setText(data.getTitle());
       tv_Intro.setText(data.getExcerpt().replaceAll("\\<p>|</p>",""));
       tv_Date.setText(data.getDate());
     im_Logo.setImageResource(pictures[getPosition()%4]);
        tv_Cato.setText("Android");
    }
}
