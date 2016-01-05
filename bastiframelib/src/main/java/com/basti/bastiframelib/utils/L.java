package com.basti.bastiframelib.utils;

import android.util.Log;

/**
 * Log日志工具类
 * 为了调用时的快捷，工具类名取Log的首字母
 * Created by Bowen on 2015-11-02.
 */
public class L {

    public static void log(boolean debugMode,String Tag,String message){
        if (debugMode){
            Log.i(Tag,message);
        }
    }
}
