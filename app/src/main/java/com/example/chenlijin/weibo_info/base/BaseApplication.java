package com.example.chenlijin.weibo_info.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by chenlijin on 2015/12/8.
 */
public class BaseApplication extends Application {
    private static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mApplication = this;
    }

    public static Context getContext(){
        return mApplication.getApplicationContext();
    }
}
