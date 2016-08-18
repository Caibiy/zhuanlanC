package com.ycode.android.zhuanlanc.api;


import com.ycode.android.zhuanlanc.bean.TechBean;

import retrofit2.http.POST;
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
    public Observable<TechBean>getAndroid(@Query("slug") String json);

}
