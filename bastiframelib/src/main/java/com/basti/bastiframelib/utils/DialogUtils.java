package com.basti.bastiframelib.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * DiaLog工蕨类
 * Created by Bowen on 2015-11-02.
 */
public class DialogUtils {

    private ProgressDialog mProgressDialog;
    private Context mContext;

    public DialogUtils(Context context){
        mContext = context;
        mProgressDialog = new ProgressDialog(context);
    }

    /*
    @params:
    show:是否显示progress
    title:显示的标题
    message:显示的信息
     */

    public void showProgressDialog(boolean show,String title,String message){
        if (show){
            mProgressDialog.setTitle(title);
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }else {
            mProgressDialog.hide();
        }
    }

    public void showProgressDialog(boolean show,String message){
        showProgressDialog(show,"",message);
    }

    public void showProgressDialog(boolean show){
        showProgressDialog(show,"");
    }

    /*
    取消，防止泄露
     */
    public void dismiss(){
        mProgressDialog.dismiss();
    }
}
