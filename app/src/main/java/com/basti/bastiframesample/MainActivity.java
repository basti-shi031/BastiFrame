package com.basti.bastiframesample;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.basti.bastiframelib.base.BaseActivity;
import com.basti.bastiframelib.network.NetworkCallback;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends BaseActivity implements NetworkCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showProgressDialog(true,"提示","正在加载");
        getNetwork("https://api.heweather.com/x3/weather?cityid=CN101010100&key=a40167f9dba34922b9c7746c0a511984", 0);
        L("TEST Log");
        showToast("This is a Toast");
        showSnackBar("This is a SnackBar");
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject result, int tag) {
        super.onSuccess(statusCode, headers, result, tag);
        L(result.toString());
    }

    @Override
    public void onFailed(int statusCode, Header[] headers, String result, Throwable throwable, int tag) {
        super.onFailed(statusCode, headers, result, throwable, tag);
    }
}
