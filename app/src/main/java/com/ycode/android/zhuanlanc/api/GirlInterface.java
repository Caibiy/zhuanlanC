package com.ycode.android.zhuanlanc.api;

import com.ycode.android.zhuanlanc.bean.GirlBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author:    yangjiadong
 * Time :     2016/8/17
 * Email:      caibiy666@gmail.com
 */
public interface GirlInterface {
    public  static final String BASEURL="http://gank.io/";
    @GET("api/data/{type}/{count}/{page}")
    Observable<GirlBean> getGirl(@Path("type")String type,@Path("count")int count,@Path("page")int page);
}
