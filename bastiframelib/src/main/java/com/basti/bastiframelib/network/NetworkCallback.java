package com.basti.bastiframelib.network;

import com.alibaba.fastjson.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by SHIBW-PC on 2016/1/5.
 */
public interface NetworkCallback {

    void onSuccess(int statusCode, Header[] headers,JSONObject result,int tag);

    void onFailed(int statusCode, Header[] headers, String result, Throwable throwable,int tag);

    void onInternetError(int statusCode, Header[] headers, Throwable throwable, org.json.JSONObject errorResponse);

}
