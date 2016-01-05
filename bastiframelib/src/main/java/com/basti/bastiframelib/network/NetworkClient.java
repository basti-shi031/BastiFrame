package com.basti.bastiframelib.network;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by SHIBW-PC on 2016/1/5.
 */
public class NetworkClient {

    public static AsyncHttpClient createClient(){
        return new AsyncHttpClient();
    }
}
