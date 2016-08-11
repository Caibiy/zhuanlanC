package com.ycode.android.zhuanlanc.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Author:    yangjiadong
 * Time :     2016/8/7
 * Email:      caibiy666@gmail.com
 */
public class NetWorkUtil {
        //判断移动网络是否打开
        public static boolean isNetConnected(Context context){
                if(context!=null){
                        ConnectivityManager mConnect= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                        NetworkInfo mNetworkInfo=mConnect.getActiveNetworkInfo();
                        return mNetworkInfo.isAvailable();
                }
             return false;
        }
}
