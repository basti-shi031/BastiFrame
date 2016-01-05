package com.basti.bastiframelib.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * Created by Bowen on 2015-11-02.
 */
public class ToastUtils {

    private Toast mToast;
    private Context mContext;

    public ToastUtils(Context context){
        mContext = context;
        mToast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
    }

    /*
    显示Toast信息
    @params:
    s:Toast信息
     */
    public void showToast(String s){
        mToast.setText(s);
        mToast.show();
    }
}
