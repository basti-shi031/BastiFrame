package com.basti.bastiframesample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.basti.bastiframelib.base.BaseCacheActivity;
import com.basti.bastiframelib.jsoncache.bean.LoadBean;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends BaseCacheActivity{

    private Button bt_GetData,bt_ShowToast,bt_ShowSnackbar;
    private EditText et_Username,et_Password;
    private Button bt_Save,bt_Load,bt_Delete;
    private Button bt_Upload,bt_downLoad;
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

        bt_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar("developing...");
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

        bt_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(et_Username.getText().toString(),et_Password.getText().toString());

                String data = JSON.toJSONString(user);
                L(data);
                save(data, "/user.txt", 0);
            }
        });

        bt_Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load("/user.txt", 0);
            }
        });

        bt_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete("/user.txt", 0);
            }
        });

        bt_downLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNetwork("http://www.wandoujia.com/apps/com.box.sjfood_v_1_0/binding",1);
            }
        });
    }

    private void initView() {
        bt_GetData = (Button) findViewById(R.id.getdata);
        bt_ShowToast = (Button) findViewById(R.id.showToast);
        bt_ShowSnackbar = (Button) findViewById(R.id.showSnackbar);

        et_Username = (EditText) findViewById(R.id.username);
        et_Password = (EditText) findViewById(R.id.password);

        bt_Save = (Button) findViewById(R.id.save);
        bt_Load = (Button) findViewById(R.id.load);
        bt_Delete = (Button) findViewById(R.id.delete);

        bt_Upload = (Button) findViewById(R.id.upload);
        bt_downLoad = (Button) findViewById(R.id.download);
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

    @Override
    public void onProgress(long bytesWritten, long totalSize,int tag) {
        super.onProgress(bytesWritten, totalSize,tag);
        if (tag == 1)
        L(bytesWritten+"    /    "+totalSize);
    }

    @Override
    public void finishDeleteAll(int deleteTag) {
        super.finishDeleteAll(deleteTag);
    }

    @Override
    public void finishDelete(int deleteTag, boolean success) {
        super.finishDelete(deleteTag, success);
        L("finishDelete");
    }

    @Override
    public void finishLoad(int loadTag, LoadBean loadBean) {
        super.finishLoad(loadTag, loadBean);
        L("finishLoad");
        if (loadBean.isSuccess()){
            L(loadBean.getResult());

            User user = JSON.parseObject(loadBean.getResult(),User.class);

            et_Password.setText(user.getPassword());
            et_Username.setText(user.getUsername());
        }

    }

    @Override
    public void finishSave(int saveTag, boolean success) {
        super.finishSave(saveTag, success);
        L("finishSave");
    }
}
