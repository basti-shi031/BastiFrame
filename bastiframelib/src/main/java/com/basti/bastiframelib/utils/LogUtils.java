package com.basti.bastiframelib.utils;

import android.content.Context;
import android.util.Log;

import com.basti.bastiframelib.base.BaseApplication;

/**
 * Log日志工具类
 * 为了调用时的快捷，工具类名取Log的首字母
 * Created by Bowen on 2015-11-02.
 */
public class LogUtils {

    private Context mContext;

    public LogUtils(Context context){
        mContext = context;
    }

    private void log(boolean debugMode,String Tag,String message){
        if (debugMode){
            Log.d(Tag,message);
        }
    }

    public void log(String message){
        log(BaseApplication.getDebugMode(),mContext.getClass().getName(),message);
    }
}
