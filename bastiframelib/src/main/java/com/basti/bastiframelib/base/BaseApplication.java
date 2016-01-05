package com.basti.bastiframelib.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Bowen on 2016-01-05.
 */
public class BaseApplication extends Application{

    private static boolean mDebugMode;

    @Override
    public void onCreate() {
        super.onCreate();
        //默认为调试模式
        setDebugMode(true);
        LeakCanary.install(this);
    }

    protected void setDebugMode(boolean debugMode){
        mDebugMode = debugMode;
    }

    public static boolean getDebugMode(){
        return mDebugMode;
    }
}
