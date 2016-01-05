package com.basti.bastiframelib.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.basti.bastiframelib.network.NetworkCallback;
import com.basti.bastiframelib.network.NetworkUtils;
import com.basti.bastiframelib.utils.DialogUtils;
import com.basti.bastiframelib.utils.LogUtils;
import com.basti.bastiframelib.utils.SnackBarUtils;
import com.basti.bastiframelib.utils.ToastUtils;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by SHIBW-PC on 2016/1/5.
 */
public class BaseActivity extends AppCompatActivity implements NetworkCallback {

    private NetworkUtils mNetworkUtils;
    private LogUtils mLogUtils;
    private ToastUtils mToastUtils;
    private SnackBarUtils mSnackBarUtils;
    private DialogUtils mDialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNetworkUtils = new NetworkUtils(this);
        mLogUtils = new LogUtils(this);
        mToastUtils = new ToastUtils(this);
        mSnackBarUtils = new SnackBarUtils(this);
        mDialogUtils = new DialogUtils(this);
    }

    //post方法
    public void postNetwork(String url,RequestParams params,int tag){
        mNetworkUtils.loadData(url,params, NetworkUtils.RequestMethod.POST,tag);
    }

    //get方法
    public void getNetwork(String url,int tag){
        mNetworkUtils.loadData(url,null, NetworkUtils.RequestMethod.GET,tag);
    }

    //打印Log日志的方法
    //为了代码简洁方便，方法名取Log首字母
    protected void L(String message){
        mLogUtils.log(message);
    }

    //显示Toast
    protected void showToast(String message){
        mToastUtils.showToast(message);
    }

    //显示SnackBar
    protected void showSnackBar(String message){
        mSnackBarUtils.show(message);
    }

    //显示进度条 标题+信息
    protected void showProgressDialog(boolean isShow,String title,String message){
        mDialogUtils.showProgressDialog(isShow, title, message);
    }

    //显示进度条 只显示信息
    protected void showProgressDialog(boolean isShow,String message){
        showProgressDialog(isShow, "", message);
    }

    //显示/关闭进度条
    protected void showProgressDialog(boolean isShow){
        showProgressDialog(isShow,"","");
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject result,int tag) {
        showProgressDialog(false);
    }

    @Override
    public void onFailed(int statusCode, Header[] headers, String result, Throwable throwable,int tag) {
        showProgressDialog(false);
    }
}
