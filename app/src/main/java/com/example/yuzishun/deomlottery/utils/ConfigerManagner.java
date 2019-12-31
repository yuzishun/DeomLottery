package com.example.yuzishun.deomlottery.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by yuzishun on 2019/5/15.
 */

public class ConfigerManagner {
    private static ConfigerManagner configManger;
    private static Context context;

    ConfigerManagner(Context context) {
        this.context = context.getApplicationContext();
    }

    public static ConfigerManagner getInstance(Context context) {
        if (configManger == null) {
            configManger = new ConfigerManagner(context);
        }
        return configManger;
    }

    protected SharedPreferences getMySharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean setString(String name, String value) {
        return getMySharedPreferences().edit().putString(name, value).commit();
    }

    public String getString(String name) {
        return getMySharedPreferences().getString(name, "");
    }
}