package com.basti.bastiframelib.base;

import android.app.Fragment;
import android.os.Bundle;
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
 * Created by Bowen on 2016-01-05.
 */
public class BaseFragment extends Fragment implements NetworkCallback {

    private NetworkUtils mNetworkUtils;
    private LogUtils mLogUtils;
    private ToastUtils mToastUtils;
    private SnackBarUtils mSnackBarUtils;
    private DialogUtils mDialogUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNetworkUtils = new NetworkUtils(this,getActivity());
        mLogUtils = new LogUtils(getActivity());
        mToastUtils = new ToastUtils(getActivity());
        mSnackBarUtils = new SnackBarUtils(getActivity());
        mDialogUtils = new DialogUtils(getActivity());
    }

    //post方法
    public void postNetwork(String url,RequestParams params,int tag){
        mNetworkUtils.loadData(url,params, NetworkUtils.RequestMethod.POST,tag);
    }

    //get方法
    public void getNetwork(String url,int tag){
        mNetworkUtils.loadData(url,null, NetworkUtils.RequestMethod.GET,tag);
    }

    //设置超时时间，默认为10s
    public void setTimeout(int value){
        mNetworkUtils.setTimeout(value);
    }

    //打印Log日志的方法
    //为了代码简洁方便，方法名取Log首字母
    public void L(String message){
        mLogUtils.log(message);
    }

    //显示SnackBar
    protected void showSnackbar(String message){
        showSnackbar(message, "", null);
    }
    //显示带操作的SnackBar
    protected void showSnackbar(String message,String action,View.OnClickListener onClickListener){
        SnackBarUtils.show(getActivity().getWindow().getDecorView().findViewById(android.R.id.content), message,action,onClickListener);
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
        showProgressDialog(isShow, "", "");
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject result, int tag) {
        showProgressDialog(false);
    }

    @Override
    public void onFailed(int statusCode, Header[] headers, String result, Throwable throwable, int tag) {
        showProgressDialog(false);
    }

    @Override
    public void onInternetError(int statusCode, Header[] headers, Throwable throwable, org.json.JSONObject errorResponse) {
        showProgressDialog(false);
    }

    @Override
    public void onProgress(long bytesWritten, long totalSize, int tag) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNetworkUtils.cancelAllRequests(true);
    }
}
