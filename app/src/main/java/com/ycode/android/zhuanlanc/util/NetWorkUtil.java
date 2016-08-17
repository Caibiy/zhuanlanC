package com.ycode.android.zhuanlanc.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ycode.android.zhuanlanc.bean.AndroidBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        //去除p标签
        public static String ReplaceHtmlP(String hString){
                return hString.replaceAll("\\<p>|</p>","");
        }
        //格式化时间
        public  static String FormatTime(String date){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Date date1=null;
                try {
                    date1=sdf.parse(date);

                } catch (ParseException e) {
                        e.printStackTrace();
                }
                return sdf.format(date1);
        }
        //返回类别
        public static String getCate(List<AndroidBean.PostsBean.CategoriesBean> tList){
                List<AndroidBean.PostsBean.CategoriesBean> list=tList;
                String temp="Category：";
                for(AndroidBean.PostsBean.CategoriesBean ca:list){
                        temp+=ca.getSlug()+" ";
                }
                return temp;
        }

}
