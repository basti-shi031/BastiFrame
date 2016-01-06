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

    private void setAction(String action,View.OnClickListener onClickListener){
        mSnackbar.setAction(action,onClickListener);
    }

    public void show(String message){
        setAction("",null);
        setDuration(Snackbar.LENGTH_LONG);
        showMessage(message);
    }

    public void show(String message,int duration){
        setAction("",null);
        setDuration(duration);
        showMessage(message);
    }

    public void show(String message,String action,View.OnClickListener onClickListener){
        setAction(action,onClickListener);
        setDuration(Snackbar.LENGTH_LONG);
        showMessage(message);
    }

    public void show(String message,String action,View.OnClickListener onClickListener,int duration){
        setAction(action,onClickListener);
        setDuration(duration);
        showMessage(message);
    }

    private void setDuration(int duration){
        mSnackbar.setDuration(duration);
    }

    private void showMessage(String message){
        mSnackbar.setText(message).show();
    }

}
