package com.ycode.android.zhuanlanc.pre;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Author:    yangjiadong
 * Time :     2016/9/2
 * Email:      caibiy666@gmail.com
 */
abstract class Prefs<K extends BaseKey>{

    private SharedPreferences mPrefs;//用来保存临时数据

    protected Prefs(@NonNull Context context, @NonNull String presFileName)
    throws  IllegalArgumentException{
        Context appContext=context.getApplicationContext();
        if(appContext!=null){
                mPrefs=appContext.getSharedPreferences(appContext.getPackageName()
                        +presFileName,Context.MODE_PRIVATE);
        }else{
            throw  new IllegalArgumentException("context.getApplicationContext()==error");
        }
    }
    protected void checkKey(K key,Class clazz){
        if(key.getmType()!=clazz){
            throw  new IllegalArgumentException("invalid key");
        }
    }
    public  final boolean getBoolean(K key){
        checkKey(key,Boolean.class);
        return mPrefs.getBoolean(key.toString(), (Boolean) key.getmDefaultValue());
    }
    public final void setBoolean(K key,boolean value){
        checkKey(key,Boolean.class);
        mPrefs.edit().putBoolean(key.toString(),value).apply();
    }
    public final  int getInteger(K key){
        checkKey(key,Integer.class);
        return mPrefs.getInt(key.toString(), (Integer) key.getmDefaultValue());
    }
    public final void setInteger(K key,int value){
        checkKey(key,Integer.class);
        mPrefs.edit().putInt(key.toString(),value).apply();
    }
    public final long getLong(K key){
        checkKey(key,Long.class);
        return mPrefs.getLong(key.toString(), (Long) key.getmDefaultValue());
    }
    public final void setLong(K key,long value){
        checkKey(key,Long.class);
        mPrefs.edit().putLong(key.toString(),value).apply();
    }
    public final String getString(K key){
        checkKey(key,String.class);
        return mPrefs.getString(key.toString(), (String) key.getmDefaultValue());
    }
    public final void setString(K key,String value){
        checkKey(key,String.class);
        mPrefs.edit().putString(key.toString(),value).apply();
    }
}
