package com.basti.bastiframesample;

import android.os.Bundle;
import android.util.Log;

import com.basti.bastiframelib.base.BaseActivity;

public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getNetwork("https://api.heweather.com/x3/weather?cityid=CN101010100&key=a40167f9dba34922b9c7746c0a511984",0);
        L("TEST Log");
    }

}
