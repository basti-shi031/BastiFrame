package com.basti.bastiframelib.network;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.basti.bastiframelib.utils.LogUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by SHIBW-PC on 2016/1/5.
 */
public class NetworkUtils {

    private NetworkCallback mCallback;
    private Context mContext;

    private AsyncHttpClient mClient;
    private int mTimeout = 10*1000;
    public NetworkUtils(NetworkCallback networkCallback,Context context){
        mCallback = networkCallback;
        mClient = NetworkClient.createClient();
        mContext = context;
        mClient.setTimeout(mTimeout);
    }

    public void setTimeout(int timeout){
        mTimeout = timeout;
        mClient.setTimeout(mTimeout);
    }

    public void loadData(String url,RequestParams params,RequestMethod method, final int tag){

        JsonHttpResponseHandler jsonHttpResponseHandler = new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                mCallback.onSuccess(statusCode, headers, JSON.parseObject(response.toString()), tag);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                mCallback.onFailed(statusCode, headers, responseString, throwable, tag);
                Log.i("TAG","1");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("TAG", "2");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mCallback.onInternetError(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
                mCallback.onProgress(bytesWritten, totalSize,tag);
            }
        };

        switch (method) {
            case GET:
                mClient.get(mContext,url,jsonHttpResponseHandler).setTag(tag);
                break;
            case POST:
                mClient.post(mContext,url,params,jsonHttpResponseHandler).setTag(tag);
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
