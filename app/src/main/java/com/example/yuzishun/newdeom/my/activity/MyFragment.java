package com.example.yuzishun.newdeom.my.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.MainActivity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Basefragment;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.LazyFragment;
import com.example.yuzishun.newdeom.login.activity.LoginActivity;
import com.example.yuzishun.newdeom.model.UserInfoBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.SpUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends LazyFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private TextView title_text;
    private LinearLayout image_back;
    private LinearLayout layout_bankcard;
    private LinearLayout Layout_Bandpay;
    private TextView Username;
    private ImageView icon;
    private LinearLayout layout_setting;
    private LinearLayout layout_recharge;
    private TextView Text_loading;
    private ScrollView layout_scroll;
    private LinearLayout layout_zhongjiang,layout_touzhu;
    private RelativeLayout Relat_order;
    private LinearLayout layout_detailt;
    private SwipeRefreshLayout Home_Refresh;
    private TextView text_yue,text_caijin;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_my);
        initView();
        initData();
    }

    private void refresh() {
        HashMap<String,String> hashMap = new HashMap<>();
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "user/refreshUserInfo", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result=  response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {


                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){
                                UserInfoBean userInfoBean = JSON.parseObject(result,UserInfoBean.class);
                                Username.setText(userInfoBean.getData().getUname());
                                Content.authentication = userInfoBean.getData().getAuthentication();
                                Content.userurl = userInfoBean.getData().getImg_head();
                                Content.username = userInfoBean.getData().getUname();
                                Glide.with(getContext()).load(userInfoBean.getData().getImg_head()).asBitmap().centerCrop().into(icon);
                                text_yue.setText(userInfoBean.getData().getAccount().getBalance());;
                                text_caijin.setText(userInfoBean.getData().getAccount().getFrozen_account());

                            }else if(code==10004){
                                MainActivity.intentsat.finish();
                                startActivity(new Intent(getContext(), LoginActivity.class));
                                SpUtil spUtil = new SpUtil(getContext(),"token");
                                spUtil.putString("token","");

                                ToastUtil.showToast(getContext(),msg+"");

                            }else {
                                ToastUtil.showToast(getContext(),msg+"");


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        });



    }


    private void initView() {
        text_yue = (TextView) findViewById(R.id.text_yue);
        text_caijin = (TextView) findViewById(R.id.text_caijin);
        layout_touzhu = (LinearLayout) findViewById(R.id.layout_touzhu);
        layout_zhongjiang = (LinearLayout) findViewById(R.id.layout_zhongjiang);
        title_text = (TextView) findViewById(R.id.title_text);
        image_back = (LinearLayout) findViewById(R.id.image_back);
        layout_bankcard = (LinearLayout) findViewById(R.id.layout_bankcrad);
        Layout_Bandpay = (LinearLayout) findViewById(R.id.Layout_Bandpay);
        Username = (TextView) findViewById(R.id.Username);
        icon = (ImageView) findViewById(R.id.icon);
        Home_Refresh  = (SwipeRefreshLayout) findViewById(R.id.Home_Refresh);
        Relat_order = (RelativeLayout) findViewById(R.id.Relat_order);
        Text_loading = (TextView) findViewById(R.id.Text_loading);
        layout_setting = (LinearLayout) findViewById(R.id.layout_setting);
        layout_recharge = (LinearLayout) findViewById(R.id.layout_recharge);
        layout_scroll = (ScrollView) findViewById(R.id.layout_scroll);
        layout_detailt = (LinearLayout) findViewById(R.id.layout_detailt);
        title_text.setText(R.string.MyCenter);
        image_back.setVisibility(View.GONE);
        layout_bankcard.setOnClickListener(this);
        Layout_Bandpay.setOnClickListener(this);
        Username.setOnClickListener(this);
        icon.setOnClickListener(this);
        layout_setting.setOnClickListener(this);
        layout_recharge.setOnClickListener(this);
        layout_touzhu.setOnClickListener(this);
        layout_zhongjiang.setOnClickListener(this);
        Relat_order.setOnClickListener(this);
        layout_detailt.setOnClickListener(this);
        Home_Refresh.setColorSchemeResources(
                R.color.login_red, R.color.login_red, R.color.login_red, R.color.login_red);
        Home_Refresh.setOnRefreshListener(this);

    }
    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...

                //tablayout加载布局


                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 200);
            }
        }).start();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_bankcrad:
                startActivity(new Intent(getContext(), BankCradManagementActivity.class));
                break;
            case R.id.Layout_Bandpay:
                startActivity(new Intent(getContext(),BandingPayActivity.class));
                break;
            case R.id.Username:
                startActivity(new Intent(getContext(),PersonalInformationActivity.class));
                break;
            case R.id.icon:
                startActivity(new Intent(getContext(),PersonalInformationActivity.class));
                break;
            case R.id.layout_setting:
                startActivity(new Intent(getContext(),SettingActivity.class));
                break;
            case R.id.layout_recharge:
                startActivity(new Intent(getContext(),RechargeActivity.class));
                break;
            case R.id.layout_touzhu:
                jump(1,BetteyAndWinningActivity.class);
                break;
            case R.id.layout_zhongjiang:
                jump(2,BetteyAndWinningActivity.class);

                break;
            case R.id.Relat_order:
                jump(3,BetteyAndWinningActivity.class);

                break;
            case R.id.layout_detailt:
                startActivity(new Intent(getContext(),MyDetailedActivity.class));
                break;
        }
    }

    public void jump(int flag, Class<?> activity){
        Intent intent = new Intent(getContext(),activity);
        intent.putExtra("flag",flag);
        startActivity(intent);
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        handler.sendEmptyMessage(1);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            Text_loading.setVisibility(View.GONE);

            Home_Refresh.setVisibility(View.VISIBLE);
            layout_scroll.setVisibility(View.VISIBLE);
        }
    };


    @Override
    public void onRefresh() {
        ToastUtil.showToast1(getActivity(),"正在刷新");

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                refresh();
                Home_Refresh.setRefreshing(false);
                ToastUtil.showToast1(getActivity(),"刷新完成");


            }
        },2000);

    }

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        refresh();
    }
}
