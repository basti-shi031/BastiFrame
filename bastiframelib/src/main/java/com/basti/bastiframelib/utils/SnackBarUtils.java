package com.basti.bastiframelib.utils;

import android.app.Activity;
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

    public SnackBarUtils(Activity activity){
        mSnackbar = Snackbar.make(activity.getWindow().getDecorView().findViewById(android.R.id.content),"",Snackbar.LENGTH_SHORT);
    }

    public void show(String message){
        mSnackbar.setText(message).show();
    }

}
