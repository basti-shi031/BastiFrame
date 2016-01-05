package com.basti.bastiframelib.network;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by SHIBW-PC on 2016/1/5.
 */
public class NetworkUtils {

    private NetworkCallback mCallback;

    public NetworkUtils(NetworkCallback networkCallback){
        mCallback = networkCallback;
    }

    public void loadData(String url,RequestParams params,RequestMethod method, final int tag){

        AsyncHttpClient client = NetworkClient.createClient();

        JsonHttpResponseHandler jsonHttpResponseHandler = new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                mCallback.onSuccess(statusCode, headers, JSON.parseObject(response.toString()),tag);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                mCallback.onFailed(statusCode,headers,responseString,throwable,tag);
            }
        };

        switch (method) {
            case GET:
                client.get(url,jsonHttpResponseHandler);
                break;
            case POST:
                client.post(url,params,jsonHttpResponseHandler);
                break;
        }

    }

    public enum RequestMethod{
        GET,POST
    }
}
