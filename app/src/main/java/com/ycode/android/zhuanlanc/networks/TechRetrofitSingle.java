package com.ycode.android.zhuanlanc.networks;

import com.ycode.android.zhuanlanc.api.CaibiyInterface;
import com.ycode.android.zhuanlanc.bean.TechBean;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:    yangjiadong
 * Time :     2016/8/7
 * Email:      caibiy666@gmail.com
 */
public class TechRetrofitSingle {
    private static OkHttpClient okHttpClient=null;
    private static Retrofit mRetrofit;
   private String PARAMS;
    /**
     * 默认的Subscriber,遵循5大原则
     */
    private Subscriber<TechBean> mSubscriber=new Subscriber<TechBean>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(TechBean caibiyBean) {

        }
    };
    private TechRetrofitSingle(){
        initClient();
        initRetrofit();
    }
    public static TechRetrofitSingle getSinIns(){
        return SingleHolder.mRetrofitSingle;
    }
    //初始化okHttpClient
    private void initClient() {
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        //设置连接超时时间
        builder.connectTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(20,TimeUnit.SECONDS)
        .connectTimeout(20,TimeUnit.SECONDS);
        okHttpClient=builder.build();
    }
    public void setSubscriber(String param, Subscriber<TechBean> subscriber){
        this.mSubscriber=subscriber;
        this.PARAMS=param;
        Subscribe();
    }
    private void initRetrofit() {
        mRetrofit=new Retrofit.Builder().baseUrl(CaibiyInterface.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    private void Subscribe(){
        CaibiyInterface CInterface=mRetrofit.create(CaibiyInterface.class);
        Observable<TechBean>observable=CInterface.getAndroid(PARAMS);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(mSubscriber);
    }
    public static  class SingleHolder{
        private static final TechRetrofitSingle mRetrofitSingle=new TechRetrofitSingle();
    }
}
