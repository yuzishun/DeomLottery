package com.example.yuzishun.newdeom.sunsheet.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.documentary.activity.OkamiActivity;
import com.example.yuzishun.newdeom.model.SunSheetDetailsBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.CommentExpandableListView;
import com.example.yuzishun.newdeom.utils.CustomClickListener;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SunSheetDetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.fang_details)
    LinearLayout fang_details;
    @BindView(R.id.usericon)
    ImageView usericon;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.usedr_id)
    TextView usedr_id;
    @BindView(R.id.text_dec)
    TextView text_dec;
    @BindView(R.id.list_2_1)
    TextView list_2_1;
    @BindView(R.id.list_2_2)
    TextView list_2_2;
    @BindView(R.id.list_2_3)
    TextView list_2_3;
    @BindView(R.id.list_2_4)
    TextView list_2_4;
    @BindView(R.id.follow_button)
    Button follow_button;
    @BindView(R.id.commet_count)
    TextView commet_count;
    @BindView(R.id.lisk_count)
    TextView lisk_count;
    @BindView(R.id.layout_like)
    LinearLayout layout_like;
    @BindView(R.id.dianzan)
    ImageView dianzan;
    @BindView(R.id.detail_page_lv_comment)
    CommentExpandableListView detail_page_lv_comment;
    private String user_id,order_id;
    private String bask_id;
    @Override
    public int intiLayout() {
        return R.layout.activity_sun_sheet_details;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        fang_details.setOnClickListener(this);
        layout_like.setOnClickListener(this);
        title_text.setText("晒单详情");
        bask_id = Content.back_id;
        request();
        follow_button.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
                if(follow_button.getText().equals("取消关注")){

                    Cancelfollow();
                }else {
                    follow();

                }
            }

            @Override
            protected void onFastClick() {

            }
        });
    }

    private void request() {

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("bask_id",bask_id);

        okhttpUtlis.PostAsynMap(Url.baseUrl+"app/Bask/baskDetails", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");

                            String msg = jsonObject.getString("msg");

                            if(code==10000){

                                SunSheetDetailsBean sunSheetDetailsBean = JSON.parseObject(result,SunSheetDetailsBean.class);

                                user_id = String.valueOf(sunSheetDetailsBean.getData().getUser_id());

                                Glide.with(SunSheetDetailsActivity.this).load(sunSheetDetailsBean.getData().getImg_head()).asBitmap().centerCrop().into(usericon);
                                username.setText(sunSheetDetailsBean.getData().getUname());
                                usedr_id.setText(sunSheetDetailsBean.getData().getUser_id()+"");
                                if(sunSheetDetailsBean.getData().getIs_fans()==0){

                                    follow_button.setText("关注");
                                    follow_button.setBackground(getResources().getDrawable(R.drawable.login_shap));
                                }else {
                                    follow_button.setText("取消关注");
                                    follow_button.setBackground(getResources().getDrawable(R.drawable.login_change));

                                }
                                text_dec.setText(sunSheetDetailsBean.getData().getManifesto());

                                order_id = sunSheetDetailsBean.getData().getOrder_id()+"";
                                if(sunSheetDetailsBean.getData().getGame_type()==0){

                                    list_2_1.setText("足球");
                                }else {
                                    list_2_1.setText("篮球");

                                }
                                list_2_2.setText(sunSheetDetailsBean.getData().getOrder_price());
                                list_2_3.setText(sunSheetDetailsBean.getData().getWin_money());

                                list_2_4.setText(sunSheetDetailsBean.getData().getMultiple()+"倍");
                                commet_count.setText(sunSheetDetailsBean.getData().getComment_count()+"");
                                lisk_count.setText(sunSheetDetailsBean.getData().getLike_count()+"");
                                if(sunSheetDetailsBean.getData().getBask_is_like()==0){

                                    dianzan.setImageDrawable(getResources().getDrawable(R.mipmap.dianzan));


                                }else {
                                    dianzan.setImageDrawable(getResources().getDrawable(R.mipmap.dianzan_red));

                                }

                            }else {

                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });




    }

    @Override
    public void initData() {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_back:

                finish();
                break;
            case R.id.fang_details:
                Intent intent = new Intent(this,ProgrammeActivity.class);
                intent.putExtra("order_id",order_id);
                startActivity(intent);
                break;
            case R.id.layout_like:
                setFabulous(bask_id,dianzan);

                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Content.refre_flag=1;

    }

    private void follow() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("parent_id",user_id);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/god/attention", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
                                follow_button.setText("取消关注");
                                follow_button.setBackground(getResources().getDrawable(R.drawable.login_change));

                            }else {
                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        });


    }


    private void setFabulous(String bask_id,ImageView imageView) {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",bask_id);
        hashMap.put("like_type",0+"");
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/Comment/likeTags", hashMap, new Callback() {
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
                            JSONObject  jsonObject= new JSONObject(result);
                            int code = jsonObject.getInt("code");

                            String msg = jsonObject.getString("msg");

                            if(code==10000){

                                ToastUtil.showToast(SunSheetDetailsActivity.this,msg);
                               request();


                            }else {
                                ToastUtil.showToast(SunSheetDetailsActivity.this,msg);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });



            }
        });


    }


    private void Cancelfollow() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("parent_id",user_id);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/god/abolish", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @SuppressLint("NewApi")
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
                                follow_button.setText("关注");
                                follow_button.setBackground(getResources().getDrawable(R.drawable.login_shap));

                            }else {
                                ToastUtil.showToast1(SunSheetDetailsActivity.this,msg);
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
