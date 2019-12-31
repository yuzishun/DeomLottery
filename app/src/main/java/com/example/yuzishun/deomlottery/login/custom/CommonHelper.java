package com.example.yuzishun.deomlottery.login.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by JOHN on 2015/12/1.
 */
public class CommonHelper {
    private final static String TAG = CommonHelper.class.getSimpleName();

    /**
     * 将View转化为Bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap getViewBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

//    /**
//     * 获取当前客户端版本信息
//     */
//    public static PackageInfo getPackage() {
//        try {
//            PackageInfo pack = MyApplication..getPackageManager().getPackageInfo(MyApplication.application.getPackageName(), 0);
//            return pack;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace(System.err);
//            return null;
//        }
//    }

    /**
     * 获取随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        int number = random.nextInt(max) % (max - min + 1) + min;
        return number;
    }

    /**
     * 获取一个随机数
     *
     * @return
     */
    public static int getRandomNumber() {
        Random random = new Random();
        int number = random.nextInt();
        return number;
    }

    /**
     * @param bm
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * @param b
     * @return
     */
    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     * 字符串转换Md5
     *
     * @param content
     * @return
     */
    public static String toMd5(String content) {
        try {
            byte[] secretBytes = MessageDigest.getInstance("md5").digest(content.getBytes());
            String strMd5 = new BigInteger(1, secretBytes).toString(16);// 16进制数字
            // 如果生成数字未满32位，需要前面补0
            for (int i = 0; i < 32 - strMd5.length(); i++) {
                strMd5 = "0" + strMd5;
            }
            return strMd5;
        } catch (Exception ex) {
            return "";
        }
    }

    /**
     * @return
     */
    public static String getRandomGUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取当前手机SDK版本
     *
     * @return
     */
    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            Log.e("YZS",e.getMessage());
        }
        return version;
    }

    /**
     * 获取照片exif信息中的旋转角度
     *
     * @param path 照片路径
     * @return 角度获取从相册中选中图片的角度
     */
    public static int getPictureDegree(String path) {
        if (TextUtils.isEmpty(path)) {
            return 0;
        }
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (Exception e) {
        }
        return degree;
    }


    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bitmap 需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bitmap, int degree) {
        Bitmap result = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (bitmap == null) {
            result = bitmap;
        }
        if (bitmap != bitmap) {
            bitmap.recycle();
        }
        return result;
    }

    /**
     * 全角字符串转半角字符串
     *
     * @param input
     * @return
     */
    public static String qj2bj(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);
            }
        }
        String str = new String(c);
        return str;
    }

    /**
     * 半角字符串转全角字符串
     *
     * @param input
     * @return
     */
    public static String bj2qj(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }


    //分享文字
    public static void shareText(Activity activity) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my Share text.");
        shareIntent.setType("text/plain");
        //设置分享列表的标题，并且每次都显示分享列表
        activity.startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    //裁切
    public static void startCrop(Activity activity, Uri url, int PHOTO_CROP_CODE) {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
//        intent.setDataAndType(Uri.parse(url), "image/*");
        intent.setDataAndType(url, "image/*");
        intent.putExtra("crop", "true");//进行修剪
        intent.putExtra("outputX", 340);  //裁剪图片的宽
        intent.putExtra("outputY",340);
        intent.putExtra("aspectX", 1);  //裁剪方框宽的比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);  //是否保持比例
        intent.putExtra("return-data", true);  //是否返回bitmap
        //    File file= new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/hotcache.jpg");
        //Uri uri=Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, url);  //保存图片到指定uri
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());  //输出格式
        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(url);
        activity.sendBroadcast(intentBc);
        activity.startActivityForResult(intent, PHOTO_CROP_CODE);
       // activity.finish();
    }

    //裁切长方形
    public static void startActivityCrop(Activity activity, Uri url, int PHOTO_CROP_CODE) {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
//        intent.setDataAndType(Uri.parse(url), "image/*");
        intent.setDataAndType(url, "image/*");
        intent.putExtra("crop", "true");//进行修剪
        intent.putExtra("outputX", 500);  //裁剪图片的宽
        intent.putExtra("outputY", 500);
        intent.putExtra("aspectX", 16);  //裁剪方框宽的比例
        intent.putExtra("aspectY", 9);
        intent.putExtra("scale", true);  //是否保持比例
        intent.putExtra("return-data", false);  //是否返回bitmap
        // File file= new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/hctxcache.jpg");
        //  Uri uri=Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, url);  //保存图片到指定uri
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());  //输出格式
        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(url);
        activity.sendBroadcast(intentBc);
        activity.startActivityForResult(intent, PHOTO_CROP_CODE);
    }

    public static String saveMyBitmap(Bitmap mBitmap) {
        Date date = new Date();
        File f = new File("/sdcard/hotfitness/" + date.getTime() + ".jpg");
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getPath();
    }

    public static String save(Bitmap mBitmap) {
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            System.out.println("____保存的__sd___下__");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getPath();
//        Toast.makeText(HahItemActivity.this,"保存已经至"+Environment.getExternalStorageDirectory()+"下", Toast.LENGTH_SHORT).show();

    }

    public static void startSysCream(Activity activity, int code) {
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        picture.setType("image/*");
        activity.startActivityForResult(picture, code);
    }
}
