package com.basti.bastiframesample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.basti.bastiframelib.base.BaseActivity;
import com.basti.bastiframelib.network.NetworkCallback;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends BaseActivity implements NetworkCallback{

    private Button bt_GetData,bt_ShowToast,bt_ShowSnackbar;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvents();
    }

    private void initEvents() {
        bt_GetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog(true, "提示", "正在加载");
                getNetwork("https://api.heweather.com/x3/weather?cityid=CN101010100&key=a40167f9dba34922b9c7746c0a511984", 0);
                //cancelRequestByTag(0,true);
                //getNetwork("https://api.heweather.com/x3/weather?cityid=CN101010100&key=a40167f9dba34922b9c7746c0a511984", 1);
                //cancelAllRequest(true);
            }
        });

        bt_ShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("This is a Toast");
            }
        });

        bt_ShowSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar("This is a Snackbar", "action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("this is another Toast");
                    }
                });
            }
        });
    }

    private void initView() {
        bt_GetData = (Button) findViewById(R.id.getdata);
        bt_ShowToast = (Button) findViewById(R.id.showToast);
        bt_ShowSnackbar = (Button) findViewById(R.id.showSnackbar);
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
