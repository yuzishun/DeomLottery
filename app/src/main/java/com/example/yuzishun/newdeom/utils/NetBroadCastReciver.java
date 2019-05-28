package com.example.yuzishun.newdeom.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;


import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;

/**
 * Created by yuzishun on 2018/7/30.
 */

public class NetBroadCastReciver extends BroadcastReceiver {
    /**
     * 只有当网络改变的时候才会 经过广播。
     */
    private View view;

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            switch (networkInfo.getType()) {
                case TYPE_MOBILE:
//                    ToastUtil.showToast(context, R.mipmap.sogh_toast,"正在使用2G/3G/4G网络",3,"");

                    break;
                case TYPE_WIFI:
//                    ToastUtil.showToast(context, R.mipmap.sogh_toast,"正在使用wifi上网",3,"");

                    break;
                default:
                    break;
            }
        } else {
//            ToastUtil.showToast(context, R.mipmap.sogh_toast,"当前无网络连接",3,"");

            Toast.makeText(context, "当前没有网络连接", Toast.LENGTH_SHORT).show();
        }
    }


    }

