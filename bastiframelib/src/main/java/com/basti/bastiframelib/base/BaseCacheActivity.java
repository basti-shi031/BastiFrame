package com.basti.bastiframelib.base;

import android.os.Bundle;

import com.basti.bastiframelib.jsoncache.bean.LoadBean;
import com.basti.bastiframelib.jsoncache.listener.DeleteAllListener;
import com.basti.bastiframelib.jsoncache.listener.DeleteListener;
import com.basti.bastiframelib.jsoncache.listener.LoadListener;
import com.basti.bastiframelib.jsoncache.listener.SaveListener;
import com.basti.bastiframelib.jsoncache.utils.JsonHelper;

/**
 * Created by SHIBW-PC on 2016/1/6.
 */
public class BaseCacheActivity extends BaseActivity implements DeleteAllListener, DeleteListener, LoadListener, SaveListener {

    private JsonHelper jsonHelper;
    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initJsonHelper();
    }

    private void initJsonHelper() {
        jsonHelper = new JsonHelper();
        jsonHelper.setOnAllListener(this, this, this, this);
        mPath =  getApplicationContext().getFilesDir().getAbsolutePath()+"/jsoncache";
    }

    //保存数据
    protected void save(String jsonObject,String fileName,int saveTag){
        jsonHelper.saveJson(jsonObject,mPath,fileName,saveTag);
    }

    //读取数据
    protected void load(String filename,int loadTag){
        jsonHelper.loadJson(mPath,filename,loadTag);
    }

    //删除指定数据
    protected void delete(String filename,int deleteTag){
        jsonHelper.clearCache(mPath,filename,deleteTag);
    }

    //删除全部数据
    protected void deleteAll(int deleteTag){
        jsonHelper.clearAllCache(mPath,deleteTag);
    }

    @Override
    public void finishDeleteAll(int deleteTag) {

    }

    @Override
    public void finishDelete(int deleteTag, boolean success) {

    }

    @Override
    public void finishLoad(int loadTag, LoadBean loadBean) {

    }

    //保存数据完成回调
    @Override
    public void finishSave(int saveTag, boolean success) {

    }
}
