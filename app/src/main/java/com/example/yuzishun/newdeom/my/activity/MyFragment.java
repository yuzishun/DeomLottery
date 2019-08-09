package com.example.yuzishun.newdeom.my.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
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
import com.example.yuzishun.newdeom.main.activity.InfomationWebViewActivity;
import com.example.yuzishun.newdeom.model.BankMangmentBean;
import com.example.yuzishun.newdeom.model.UserInfoBean;
import com.example.yuzishun.newdeom.my.adapter.BankCradAdapter;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.SpUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends LazyFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private LinearLayout layout_bankcard;
    private LinearLayout Layout_Bandpay,layout_Sunsheet;
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
    private TextView text_yue,text_caijin,fans,follow;
    private RelativeLayout layout_tixian;
    private int authentication;
    private String available_balance,balance,alipay="";

    private List<BankMangmentBean.DataBean> getbanklist;
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
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/user/refreshUserInfo", hashMap, new Callback() {
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
                                Content.userid = String.valueOf(userInfoBean.getData().getUser_id());

                                Content.username = userInfoBean.getData().getUname();
                                authentication = userInfoBean.getData().getAuthentication();
                                balance = userInfoBean.getData().getAccount().getBalance();
                                alipay = userInfoBean.getData().getAlipay();
                                Content.player = userInfoBean.getData().getPlayer();
                                available_balance = userInfoBean.getData().getAccount().getAvailable_balance();
                                Glide.with(getContext()).load(userInfoBean.getData().getImg_head()).asBitmap().centerCrop().into(icon);
                                text_yue.setText(userInfoBean.getData().getAccount().getBalance());;
                                text_caijin.setText(userInfoBean.getData().getAccount().getFrozen_account());

                                fans.setText("粉丝\n"+userInfoBean.getData().getUser_fans());
                                follow.setText("关注\n"+userInfoBean.getData().getUser_attention());
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

    private void getNotice(){
        HashMap<String,String> hashMap = new HashMap<>();

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/order/getWinPriceByOrder",hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });



    }


    private void initView() {
        fans = (TextView) findViewById(R.id.fans);
        layout_Sunsheet= (LinearLayout) findViewById(R.id.layout_Sunsheet);
        follow  = (TextView) findViewById(R.id.follow);
        layout_tixian = (RelativeLayout) findViewById(R.id.layout_tixian);
        text_yue = (TextView) findViewById(R.id.text_yue);
        text_caijin = (TextView) findViewById(R.id.text_caijin);
        layout_touzhu = (LinearLayout) findViewById(R.id.layout_touzhu);
        layout_zhongjiang = (LinearLayout) findViewById(R.id.layout_zhongjiang);
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
        layout_tixian.setOnClickListener(this);
        layout_bankcard.setOnClickListener(this);
        Layout_Bandpay.setOnClickListener(this);
        Username.setOnClickListener(this);
        icon.setOnClickListener(this);
        layout_setting.setOnClickListener(this);
        layout_recharge.setOnClickListener(this);
        layout_touzhu.setOnClickListener(this);
        layout_Sunsheet.setOnClickListener(this);
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
            case R.id.layout_tixian:
                if(authentication==1){
                    if(getbanklist.size()==0){

                        ToastUtil.showToast1(getActivity(),"请先去绑定银行卡");

                    }else {
                        Content.list_bank = getbanklist;
                        Intent intent = new Intent(getContext(),ReflectActivity.class);
                        intent.putExtra("available_balance",available_balance);
                        startActivity(intent);


                    }

                }else {

                    ToastUtil.showToast1(getActivity(),"请先绑定身份证");
                    startActivity(new Intent(getContext(),IdentityVerificationActivity.class));


                }


                break;
            case R.id.layout_bankcrad:

                if(authentication==1){
                    startActivity(new Intent(getContext(), BankCradManagementActivity.class));


                }else {
                    ToastUtil.showToast1(getActivity(),"请先绑定身份证");

                    startActivity(new Intent(getContext(),IdentityVerificationActivity.class));

                }
                break;
            case R.id.Layout_Bandpay:
                if(alipay.equals("")){
                    Intent intent = new Intent(getContext(),BandingPayActivity.class);
                    intent.putExtra("flag",0);
                    intent.putExtra("available_balance",available_balance);

                    startActivity(intent);

                }else {
                    Intent intent = new Intent(getContext(),BandingPayActivity.class);
                    intent.putExtra("flag",1);
                    intent.putExtra("available_balance",available_balance);

                    startActivity(intent);
                }

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
                Intent intent = new Intent(getContext(),RechargeActivity.class);
                intent.putExtra("balance",balance);
                startActivity(intent);
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
            case R.id.layout_Sunsheet:
                jump(4,BetteyAndWinningActivity.class);

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
                getbanklist = getbanklist();
                Home_Refresh.setRefreshing(false);
                ToastUtil.showToast1(getActivity(),"刷新完成");


            }
        },2000);

    }

    public List<BankMangmentBean.DataBean> getbanklist(){
        final List<BankMangmentBean.DataBean> list = new ArrayList<>();
        HashMap<String,String> hashMap = new HashMap<>();
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/user/getUserBankCardList", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){
                                BankMangmentBean bankMangmentBean = JSON.parseObject(result,BankMangmentBean.class);
                                list.addAll(bankMangmentBean.getData());


                            }else {
                                ToastUtil.showToast(getActivity(), msg+"");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });


        return list;
    }

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        refresh();
        getbanklist = getbanklist();

    }
}
