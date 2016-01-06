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

    private AsyncHttpClient mClient;
    public NetworkUtils(NetworkCallback networkCallback){
        mCallback = networkCallback;
        mClient = NetworkClient.createClient();
    }

    public void loadData(String url,RequestParams params,RequestMethod method, final int tag){

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
                mClient.get(url,jsonHttpResponseHandler).setTag(tag);
                break;
            case POST:
                mClient.post(url,params,jsonHttpResponseHandler).setTag(tag);
                break;
        }
    }

    //取消网络请求

    /*
    通过Tag取消网络请求
    参数 cancelRunning表示是否取消正在运行的请求
     */
    public void cancelRequestByTag(int tag,boolean cancelRunning){
        mClient.cancelRequestsByTAG(tag, cancelRunning);
    }

    /*
    取消当前client中所有网络请求
    参数 cancelRunning表示是否取消正在运行的请求
     */
    public void cancelAllRequests(boolean cancelRunning){
        mClient.cancelAllRequests(cancelRunning);
    }

    public enum RequestMethod{
        GET,POST
    }
}
