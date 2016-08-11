package com.ycode.android.zhuanlanc.api;

import com.ycode.android.zhuanlanc.bean.CaibiyBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author:    yangjiadong
 * Time :     2016/8/5
 * Email:      caibiy666@gmail.com
 */
public interface CaibiyInterface {
    public static final String BASEURL="http://www.caibi-y.com/";
    @GET("/")
    public Observable<CaibiyBean>get(@Query("json") String json);
}
