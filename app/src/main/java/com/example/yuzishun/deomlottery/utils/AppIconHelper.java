package com.example.yuzishun.deomlottery.utils;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by apple on 2019/10/25.
 */
//解决bitmap8。0以上的崩溃问题1
public class AppIconHelper {
    public static Bitmap getAppIcon(PackageManager mPackageManager, String packageName) {

        if (Build.VERSION.SDK_INT >= 26) {
            return AppIconHelperV26.getAppIcon(mPackageManager, packageName);
        }

        try {
            Drawable drawable = mPackageManager.getApplicationIcon(packageName);
            return ((BitmapDrawable) drawable).getBitmap();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
