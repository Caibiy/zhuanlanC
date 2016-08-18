package com.ycode.android.zhuanlanc;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ycode.android.zhuanlanc.baseui.BaseActivity;
import com.ycode.android.zhuanlanc.util.ToastUtil;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:    yangjiadong
 * Time :     2016/8/17
 * Email:      caibiy666@gmail.com
 */
public class ContentActivity extends BaseActivity {
    String url;
    String desc;
    String title;
    String iUrl;
    @Bind(R.id.content_ImageView)
    ImageView imageView;
    @Bind(R.id.collpase_toolbar)
    CollapsingToolbarLayout collpase_toolbar;
    @Bind(R.id.content_WebView)
    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout);
        ButterKnife.bind(this);
        getData();
        initView();
    }

    private void initView() {
        //ImageView
        Glide.with(this).load(iUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
        //Toolbar
        collpase_toolbar.setTitle(title);
        collpase_toolbar.setExpandedTitleColor(Color.WHITE);
        collpase_toolbar.setCollapsedTitleTextColor(Color.RED);
        //WebView
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setAppCacheEnabled(true);
        //
     /*   webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){
                    avLoadingView.setVisibility(View.GONE);
                }
            }
        });*/

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }

    private void getData() {
        desc=getIntent().getStringExtra("desc");
        title=getIntent().getStringExtra("title");
        url=getIntent().getStringExtra("url");
        iUrl=getIntent().getStringExtra("iurl");
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null){
            webView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null){
            webView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null){
            webView.removeAllViews();
            webView.destroy();
        }
    }


}
