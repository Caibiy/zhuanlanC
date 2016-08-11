package com.ycode.android.zhuanlanc.app;

import android.app.Application;

/**
 * Author:    yangjiadong
 * Time :     2016/8/7
 * Email:      caibiy666@gmail.com
 */
public class AppApplication extends Application{
    public static String cacheDir;
//    public static boolean isDeug;
    public static Application application;
    public static void setApplication(Application application){
        AppApplication.application=application;
    }

}
