package com.example.yuzishun.deomlottery.base;

import android.app.Application;
import android.content.Context;


/**
 * Created by yuzishun on 2019/4/23.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //初始化配置信息


    }
    public static Context getContext(){
        return  context;
    }


}
