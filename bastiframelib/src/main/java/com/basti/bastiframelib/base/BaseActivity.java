package com.basti.bastiframelib.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.basti.bastiframelib.network.NetworkCallback;
import com.basti.bastiframelib.network.NetworkUtils;
import com.basti.bastiframelib.utils.LogUtils;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by SHIBW-PC on 2016/1/5.
 */
public class BaseActivity extends AppCompatActivity implements NetworkCallback {

    private NetworkUtils mNetworkUtils;
    private LogUtils mLogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNetworkUtils = new NetworkUtils(this);
        mLogUtils = new LogUtils(this);
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
    public void L(String message){
        mLogUtils.log(message);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject result,int tag) {
    }

    @Override
    public void onFailed(int statusCode, Header[] headers, String result, Throwable throwable,int tag) {
    }
}
