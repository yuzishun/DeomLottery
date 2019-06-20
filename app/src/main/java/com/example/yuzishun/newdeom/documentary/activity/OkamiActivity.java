package com.example.yuzishun.newdeom.documentary.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.documentary.adapter.OkamiListRecyclerViewAdapter;
import com.example.yuzishun.newdeom.model.OkamiBean;
import com.example.yuzishun.newdeom.model.OkamiListBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/***
 * 大神主页面
 */
public class OkamiActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.OkamiRecyclerView)
    RecyclerView OkamiRecyclerView;
    private String user_id;
    @BindView(R.id.Okami_icon)
    ImageView Okami_icon;
    @BindView(R.id.Okami_Name)
    TextView Okami_Name;
    @BindView(R.id.Okami_Fans)
    TextView Okami_Fans;
    @BindView(R.id.Okami_profit)
    TextView Okami_profit;
    @BindView(R.id.Okami_Reddish)
    TextView Okami_Reddish;
    @BindView(R.id.Okami_Winning)
    TextView Okami_Winning;
    @BindView(R.id.button_sure)
    Button button_sure;
    @BindView(R.id.state_one)
    ImageView state_one;
    @BindView(R.id.state_two)
    ImageView state_two;
    @BindView(R.id.state_three)
    ImageView state_three;
    @BindView(R.id.state_four)
    ImageView state_four;
    @BindView(R.id.state_five)
    ImageView state_five;
    @BindView(R.id.state_one_heng)
    TextView state_one_heng;
    @BindView(R.id.state_two_heng)
    TextView state_two_heng;
    @BindView(R.id.state_three_heng)
    TextView state_three_heng;
    @BindView(R.id.state_four_heng)
    TextView state_four_heng;
    @Override
    public int intiLayout() {
        return R.layout.activity_okami;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("大神主页");
        image_back.setOnClickListener(this);
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");

        setContent();
        OkamiRecyclerView.setLayoutManager(new LinearLayoutManager(OkamiActivity.this));

        setList();
        button_sure.setOnClickListener(this);

    }

    private void setList() {
        HashMap<String,String> hashMap  = new HashMap<>();
        hashMap.put("pagination","0");
        hashMap.put("god_id",user_id);

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/order_plan/getGodOrderPlanList", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){


                                OkamiListBean okamiListBean = JSON.parseObject(result,OkamiListBean.class);
                                List<OkamiListBean.DataBean> data = okamiListBean.getData();

                                OkamiListRecyclerViewAdapter okamiListRecyclerViewAdapter = new OkamiListRecyclerViewAdapter(OkamiActivity.this,data);
                                OkamiRecyclerView.setAdapter(okamiListRecyclerViewAdapter);

                            }else {
                                ToastUtil.showToast1(OkamiActivity.this,msg);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        });



    }

    private void setContent() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("parent_id",user_id);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/god/getUserSevenDetails", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                OkamiBean okamiBean = JSON.parseObject(result,OkamiBean.class);
                                Okami_Name.setText(okamiBean.getData().getUname());
                                Okami_Fans.setText("粉丝："+okamiBean.getData().getFans()+"   关注："+okamiBean.getData().getIcon());
                                if(okamiBean.getData().getAttention()==0){

                                    button_sure.setText("关注");
                                }else {
                                    button_sure.setText("取消关注");

                                }
                                Glide.with(OkamiActivity.this).load(okamiBean.getData().getImg_head()).asBitmap().centerCrop().into(Okami_icon);
                                Okami_profit.setText(okamiBean.getData().getEarnings());
                                Okami_Reddish.setText(okamiBean.getData().getRed_man());
                                Okami_Winning.setText(okamiBean.getData().getSum()+"中"+okamiBean.getData().getWin());
                                List<Integer> win_info = okamiBean.getData().getWin_info();
                                switch (okamiBean.getData().getWin_info().size()){
                                    case 0:
                                        setVisit(0,win_info);

                                        break;

                                    case 1:
                                        setVisit(1,win_info);


                                        break;
                                    case 2:
                                        setVisit(2,win_info);

                                        break;

                                    case 3:
                                        setVisit(3,win_info);

                                        break;
                                    case 4:
                                        setVisit(4,win_info);

                                        break;
                                    case 5:
                                        setVisit(5,win_info);

                                        break;

                                }


                            }else {
                                ToastUtil.showToast1(OkamiActivity.this,msg);
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

    public void setVisit(int size, List<Integer> win_info){
        switch (size){

            case 0:

                state_one.setVisibility(View.GONE);
                state_two.setVisibility(View.GONE);

                state_three.setVisibility(View.GONE);
                state_four.setVisibility(View.GONE);
                state_five.setVisibility(View.GONE);

                state_one_heng.setVisibility(View.GONE);
                state_two_heng.setVisibility(View.GONE);
                state_three_heng.setVisibility(View.GONE);
                state_four_heng.setVisibility(View.GONE);




                break;

            case 1:
                state_two.setVisibility(View.GONE);

                state_three.setVisibility(View.GONE);
                state_four.setVisibility(View.GONE);
                state_five.setVisibility(View.GONE);

                state_one_heng.setVisibility(View.GONE);
                state_two_heng.setVisibility(View.GONE);
                state_three_heng.setVisibility(View.GONE);
                state_four_heng.setVisibility(View.GONE);
                if(win_info.get(0)==2){
                    state_one.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_one.setBackgroundResource(R.mipmap.state_wei);


                }


                break;
            case 2:

                state_three.setVisibility(View.GONE);
                state_four.setVisibility(View.GONE);
                state_five.setVisibility(View.GONE);

                state_two_heng.setVisibility(View.GONE);
                state_three_heng.setVisibility(View.GONE);
                state_four_heng.setVisibility(View.GONE);
                if(win_info.get(0)==2){
                    state_one.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_one.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(1)==2){
                    state_two.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_two.setBackgroundResource(R.mipmap.state_wei);


                }
                break;

            case 3:
                state_four.setVisibility(View.GONE);
                state_five.setVisibility(View.GONE);

                state_three_heng.setVisibility(View.GONE);
                state_four_heng.setVisibility(View.GONE);
                if(win_info.get(0)==2){
                    state_one.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_one.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(1)==2){
                    state_two.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_two.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(2)==2){
                    state_three.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_three.setBackgroundResource(R.mipmap.state_wei);


                }
                break;


            case 4:
                state_five.setVisibility(View.GONE);

                state_four_heng.setVisibility(View.GONE);
                if(win_info.get(0)==2){
                    state_one.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_one.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(1)==2){
                    state_two.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_two.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(2)==2){
                    state_three.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_three.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(3)==2){
                    state_four.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_four.setBackgroundResource(R.mipmap.state_wei);


                }
                break;
            case 5:
                if(win_info.get(0)==2){
                    state_one.setBackgroundResource(R.mipmap.state_zhong);
                }else {
                    state_one.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(1)==2){
                    state_two.setBackgroundResource(R.mipmap.state_zhong);

                }else {
                    state_two.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(2)==2){
                    state_three.setBackgroundResource(R.mipmap.state_zhong);

                }else {
                    state_three.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(3)==2){
                    state_four.setBackgroundResource(R.mipmap.state_zhong);

                }else {
                    state_four.setBackgroundResource(R.mipmap.state_wei);


                }
                if(win_info.get(4)==2){
                    state_five.setBackgroundResource(R.mipmap.state_zhong);

                }else {
                    state_five.setBackgroundResource(R.mipmap.state_wei);


                }

                break;
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
            finish();
            break;
            case R.id.button_sure:
                if(button_sure.getText().equals("取消关注")){

                Cancelfollow();
                }else {
                    follow();

                }


                break;

        }
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
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                ToastUtil.showToast1(OkamiActivity.this,msg);
                                button_sure.setText("取消关注");
                            }else {
                                ToastUtil.showToast1(OkamiActivity.this,msg);
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
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                ToastUtil.showToast1(OkamiActivity.this,msg);
                                button_sure.setText("关注");

                            }else {
                                ToastUtil.showToast1(OkamiActivity.this,msg);
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
