package com.example.yuzishun.deomlottery.my.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.base.Content;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 个人信息页面
 */
public class PersonalInformationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.layout_id)
    LinearLayout layout_id;
    @BindView(R.id.layout_seticon)
    LinearLayout layout_seticon;
    @BindView(R.id.Text_ren)
    TextView Text_ren;
    private   byte[] bytes;
    private File imagefile;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.name)
    TextView name;
    @Override
    public int intiLayout() {
        return R.layout.activity_personal_information;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText(R.string.Information);
        image_back.setOnClickListener(this);
        layout_id.setOnClickListener(this);
        layout_seticon.setOnClickListener(this);

        phone.setText(Content.userphone);
        name.setText(Content.username);
        name.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;

            case R.id.button_lgoinout:
                finish();
                break;
            case R.id.layout_id:
                if(Content.authentication==0){

                    startActivity(new Intent(this,IdentityVerificationActivity.class));

                }else if(Content.authentication==1){


                }
                break;
            case R.id.layout_seticon:
                        PhotoPicker.builder()
                                .setPhotoCount(1)
                                .setShowCamera(true)
                                .setShowGif(false)
                                .setPreviewEnabled(false)
                                .start(PersonalInformationActivity.this, PhotoPicker.REQUEST_CODE);

                break;
            case R.id.name:

                if(Content.player==0){

                }else {
                    showInputDialog();

                }

                break;
        }
    }
    private void showInputDialog() {
    /*@setView 装入一个EditView
     */
        final EditText editText = new EditText(PersonalInformationActivity.this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(PersonalInformationActivity.this);
        inputDialog.setTitle("请输入修改的昵称").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(PersonalInformationActivity.this,
//                                editTeqwewqext.getText().toString(),
//                                Toast.LENGTH_SHORT).show();

                        if(editText.getText().toString().equals("")){

                            Toast.makeText(PersonalInformationActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                        }else {

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("uname",editText.getText().toString().trim());
                            OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
                            okhttpUtlis.PostAsynMap(Url.baseUrl + "app/user/editName", hashMap, new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                String result = response.body().string();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {
                                            JSONObject jsonObject = new JSONObject(result);
                                            int code = jsonObject.getInt("code");
                                            String msg = jsonObject.getString("msg");

                                            if(code==10000){

                                                Toast.makeText(PersonalInformationActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                                finish();
                                            }else {
                                                Toast.makeText(PersonalInformationActivity.this, msg+"", Toast.LENGTH_SHORT).show();

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                });

                                }
                            });

                        }

                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                imagefile = new File(photos.get(0));
//                Bitmap bm = BitmapFactory.decodeFile(photos.get(0));
//                bytes = Bitmap2Bytes(bm);
                bytes = getBytes(photos.get(0));

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(photos.get(0), options);
                String type = options.outMimeType;
                Log.e("yzs ", type+photos.get(0));
                seticon(photos.get(0),type);


            }
        }



    }


    private void seticon(String path,String name) {
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(path != null){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse(name), bytes);
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("userImage", path, body);
        }

        Request request = new Request.Builder().url(Url.baseUrl+"app/user/editUserHeadImg").addHeader("token",Content.ToKen).post(requestBody.build()).build();
// readTimeout("请求超时时间" , 时间单位);
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("YZS",e.getMessage()+"");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");

                            if(code==10000){
                                ToastUtil.showToast1(PersonalInformationActivity.this,msg+"");

                            }else {
                                ToastUtil.showToast1(PersonalInformationActivity.this,msg+"");


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });





    }
//这是压缩图片专程byte字节
    private byte[] getBytes(String path) {
        //File file = new File(path);
        //读取图片 只读边,不读内容
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, newOpts);
        //开始按比例缩放图片
        newOpts.inJustDecodeBounds = false;
        int width = newOpts.outWidth;
        int height = newOpts.outHeight;
        float maxSize = 1200;
        int be = 1;
        if (width >= height && width > maxSize) {//缩放比,用高或者宽其中较大的一个数据进行计算
            be = (int) (newOpts.outWidth / maxSize);
            be++;
        } else if (width < height && height > maxSize) {
            be = (int) (newOpts.outHeight / maxSize);
            be++;
        }
        newOpts.inSampleSize = be;//设置采样率
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
        //下面可是图片压缩
        Bitmap bitmap = BitmapFactory.decodeFile(path, newOpts);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//质量压缩方法，把压缩后的数据存放到baos中 (100表示不压缩，0表示压缩到最小)
        while (baos.toByteArray().length > 100 * 1024) {//循环判断如果压缩后图片是否大于指定大小,大于继续压缩
            baos.reset();//重置baos即让下一次的写入覆盖之前的内容
            options -= 5;//图片质量每次减少5
            if (options <= 5) options = 5;//如果图片质量小于5，为保证压缩后的图片质量，图片最底压缩质量为5
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//将压缩后的图片保存到baos中
            if (options == 5) break;//如果图片的质量已降到最低则，不再进行压缩
        }
        Log.e("YZS",baos.toByteArray().length+"");
        return baos.toByteArray();
    }

    /**
     * 把Bitmap转Byte
     * @Author HEH
     * @EditTime 2010-07-19 上午11:45:56
     */
    public static byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
    public static String getPicNameFromPath(String picturePath){
        String temp[] = picturePath.replaceAll("\\\\","/").split("/");
        String fileName = "";
        if(temp.length > 1){
            fileName = temp[temp.length - 1];
        }
        return fileName;
    }
    private Bitmap getBitmapFromUri(Uri uri)
    {
        try
        {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            return bitmap;
        }
        catch (Exception e)
        {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 把batmap 转file
     * @param bitmap
     * @param filepath
     */
    public static File saveBitmapFile(Bitmap bitmap, String filepath){
        File file=new File(filepath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(Content.authentication==0){
            Text_ren.setText("*未认证");
            Text_ren.setTextColor(getResources().getColor(R.color.font_black));
        }else if(Content.authentication==1){
            Text_ren.setText("*已认证");
            Text_ren.setTextColor(getResources().getColor(R.color.login_red));


        }
    }
}
