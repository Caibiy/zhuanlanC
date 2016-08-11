package com.ycode.android.zhuanlanc.manager;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Created by wss on 2016/6/14.
 *
 * activity 的管理类
 */
public class ActivityManager {
    private Stack<WeakReference<Activity>> activities =  new Stack<>();
    private static ActivityManager instance;
    private ActivityManager() {}

    /**
     * 单一实例
     */
    public static ActivityManager getAppManager() {
        if (instance == null) {
            synchronized (ActivityManager.class){
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(WeakReference<Activity> activity) {
        activities.add(activity);
    }
    /**
     *删除Activity到堆栈
     */
    public void removeActivity(WeakReference<Activity> activity) {
        activities.remove(activity);
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activities.lastElement().get();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity() {
        WeakReference<Activity> activity = activities.lastElement();
        activities.remove(activity);
        Activity ac=activity.get();
        if (ac!=null){
            ac.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (WeakReference<Activity> ac : activities) {
            Activity activity=ac.get();
            if (activity!=null){
                if (activity.getClass().equals(cls)) {
                    activities.remove(ac);
                    activity.finish();
                    break;
                }
            }
        }
    }
    /**
     * 结束所有Activity WithoutTopActivity
     */
    public void finishAllWithoutTopActivity() {
        for (int i = 1, size = activities.size(); i < size; i++) {
            WeakReference<Activity> ac=activities.get(i);
            if (null != ac) {
                Activity activity=ac.get();
                if (activity!=null){
                    activity.finish();
                }
            }
        }
        WeakReference<Activity> ac=activities.get(0);
        activities.clear();
        activities.add(ac);
    }



    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activities.size(); i < size; i++) {
            WeakReference<Activity> ac=activities.get(i);
            if (null != ac) {
                Activity activity=ac.get();
                if (activity!=null){
                    activity.finish();
                }
            }
        }
        activities.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
