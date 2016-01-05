package com.basti.bastiframelib.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 *
 * SnackBar工具类
 * Created by SHIBW-PC on 2016/1/5.
 */
public class SnackBarUtils {

    private Snackbar mSnackbar;

    public SnackBarUtils(Context context,View v){
        mSnackbar = Snackbar.make(v,"",Snackbar.LENGTH_SHORT);
    }

    public void show(String message){
        mSnackbar.setText(message).show();
    }

}
