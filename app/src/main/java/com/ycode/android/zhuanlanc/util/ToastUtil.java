package com.ycode.android.zhuanlanc.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Author:    yangjiadong
 * Time :     2016/8/15
 * Email:      caibiy666@gmail.com
 */
public class ToastUtil {
        public static void show(Context context, String msg){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
}
