package com.basti.bastiframesample;

import com.basti.bastiframelib.base.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Bowen on 2016-01-05.
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        //设置调试模式，默认为true
        //setDebugMode(false);
    }
}
