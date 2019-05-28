package com.example.yuzishun.newdeom.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by yuzishun on 2019/4/23.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //oom检测工具初始化
        if (LeakCanary.isInAnalyzerProcess(this)) {
                       return;
                     }
            LeakCanary.install(this);


    }
    public static Context getContext(){
        return  context;
    }
}
