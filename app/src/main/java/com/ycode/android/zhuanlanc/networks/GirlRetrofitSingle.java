package com.ycode.android.zhuanlanc.networks;

import com.ycode.android.zhuanlanc.api.GirlInterface;
import com.ycode.android.zhuanlanc.bean.GirlBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Author:    yangjiadong
 * Time :     2016/8/17
 * Email:      caibiy666@gmail.com
 */
public class GirlRetrofitSingle {
    private OkHttpClient mClient;
    private Retrofit mRetrofit;
    private GirlInterface girlInter;
    private GirlRetrofitSingle(){
        initClient();
        initRetrofit();
    }

    private void initRetrofit() {
            Retrofit.Builder builder=new Retrofit.Builder();
            mRetrofit= builder.baseUrl(GirlInterface.BASEURL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
            girlInter=mRetrofit.create(GirlInterface.class);
    }
    public void onSub(String type, int count, int page, Subscriber<GirlBean>subsc){
            Observable<GirlBean>observable=girlInter.getGirl(type,count,page);
            observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(subsc);

    }
    
    private void initClient() {
            OkHttpClient.Builder builder=new OkHttpClient.Builder();
            builder.connectTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS);
            mClient=builder.build();
    }
    public static GirlRetrofitSingle getInstance(){
        return SingleHolder.INSTANCE;
    }
    public static class SingleHolder{
        public static final GirlRetrofitSingle INSTANCE=new GirlRetrofitSingle();
    }
}
