package com.example.yuzishun.deomlottery.documentary.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.yuzishun.deomlottery.MainActivity;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.base.Content;
import com.example.yuzishun.deomlottery.documentary.adapter.OkamiListRecyclerViewAdapter;
import com.example.yuzishun.deomlottery.login.activity.LoginActivity;
import com.example.yuzishun.deomlottery.model.OkamiBean;
import com.example.yuzishun.deomlottery.model.OkamiListBean;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.CustomClickListener;
import com.example.yuzishun.deomlottery.utils.SpUtil;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    ImageView button_sure;
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
    @BindView(R.id.Okami_att)
    TextView Okami_att;
    private int Attention=0;
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
        button_sure.setOnClickListener(new CustomClickListener() {
            @Override
            protected void onSingleClick() {
//                if(button_sure.getText().equals("取消关注")){
//
//                    Cancelfollow();
//                }else {
//                    follow();
//
//                }
                if(Attention==0){
                    follow();

                }else {
                    Cancelfollow();

                }
            }

            @Override
            protected void onFastClick() {

            }
        });

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
                                int i = 1;
                                if(user_id.equals(Content.userid) ){

                                    i=0;

                                }else {
                                    i=1;
                                }

                                OkamiListRecyclerViewAdapter okamiListRecyclerViewAdapter = new OkamiListRecyclerViewAdapter(OkamiActivity.this,data,i);
                                OkamiRecyclerView.setAdapter(okamiListRecyclerViewAdapter);

                            }else if(code==10004){
                                MainActivity.intentsat.finish();
                                startActivity(new Intent(OkamiActivity.this, LoginActivity.class));
                                SpUtil spUtil = new SpUtil(OkamiActivity.this,"token");
                                spUtil.putString("token","");
                                ToastUtil.showToast(OkamiActivity.this,msg+"");

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
                                Okami_Fans.setText(okamiBean.getData().getFans()+"");
                                Okami_att.setText(okamiBean.getData().getIcon()+"");
                                Attention = okamiBean.getData().getAttention();
                                if(okamiBean.getData().getAttention()==0){
                                    Glide.with(OkamiActivity.this).load(R.mipmap.okami_att_ture).asBitmap().into(button_sure);
                                }else {
                                    Glide.with(OkamiActivity.this).load(R.mipmap.okami_att_false).asBitmap().into(button_sure);

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
                                Glide.with(OkamiActivity.this).load(R.mipmap.okami_att_false).asBitmap().into(button_sure);
                                Attention=1;
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
                                Glide.with(OkamiActivity.this).load(R.mipmap.okami_att_ture).asBitmap().into(button_sure);
                                Attention=0;

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
