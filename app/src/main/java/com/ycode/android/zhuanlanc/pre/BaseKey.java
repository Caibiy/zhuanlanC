package com.ycode.android.zhuanlanc.pre;

/**
 * Author:    yangjiadong
 * Time :     2016/9/2
 * Email:      caibiy666@gmail.com
 */
abstract class BaseKey {
    private final String mStr;
    private final Class mType;
    private final Object mDefaultValue;

    public String getmStr() {
        return mStr;
    }

    public Class getmType() {
        return mType;
    }

    public Object getmDefaultValue() {
        return mDefaultValue;
    }

    <T>BaseKey(String str, Class<T> type, T defaultType){
        this.mStr=str;
        this.mType=type;
        this.mDefaultValue=defaultType;
    }

}
