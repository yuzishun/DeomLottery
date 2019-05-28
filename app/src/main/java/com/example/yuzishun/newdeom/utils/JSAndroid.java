package com.example.yuzishun.newdeom.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by yuzishun on 2019/5/15.
 */

public class JSAndroid extends  Object {


    private Context context;
    public JSAndroid(Context context) {
        this.context=context;
    }
    private ConfigerManagner configerManagner;
    @JavascriptInterface
    public void openAndroid(String msg){
        Toast.makeText(context,"返回按钮监控",Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void writeData(String msg) {
        configerManagner=ConfigerManagner.getInstance(context);
        configerManagner.setString("js",msg);
    }
    @JavascriptInterface
    public String giveInformation(String msg){
        configerManagner=ConfigerManagner.getInstance(context);
        String msg1= configerManagner.getString("js");
        return msg1;
    }

}
