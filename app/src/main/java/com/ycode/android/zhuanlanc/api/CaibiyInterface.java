package com.ycode.android.zhuanlanc.api;


import com.ycode.android.zhuanlanc.bean.AndroidBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Author:    yangjiadong
 * Time :     2016/8/5
 * Email:      caibiy666@gmail.com
 */
public interface CaibiyInterface {
    public static final String BASEURL="http://www.caibi-y.com/api/";
    @POST("get_category_posts/")
    public Observable<AndroidBean>getAndroid(@Query("slug") String json);

}
