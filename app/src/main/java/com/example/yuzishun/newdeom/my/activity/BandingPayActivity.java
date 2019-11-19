package com.example.yuzishun.newdeom.my.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.login.custom.CheckEditForButton;
import com.example.yuzishun.newdeom.login.custom.ClearEditText;
import com.example.yuzishun.newdeom.login.custom.EditTextChangeListener;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.RegexUtils;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 绑定支付宝页面
 */
public class BandingPayActivity extends BaseActivity implements View.OnClickListener {
    private RegexUtils regexUtils;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.pay_phone)
    ClearEditText pay_phone;

    @BindView(R.id.button_binding)
    Button button_binding;
    @BindView(R.id.layout_tixian)
    LinearLayout layout_tixian;
    @BindView(R.id.layout_bind)
    LinearLayout layout_bind;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.money_edit)
    ClearEditText money_edit;
    @BindView(R.id.button_ti)
    Button button_ti;
    private int flag;
    private Double available_balance;
    @Override
    public int intiLayout() {
        return R.layout.activity_banding_pay;
    }
    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        button_binding.setOnClickListener(this);
        regexUtils  = new RegexUtils(this);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",0);
        available_balance = Double.parseDouble(intent.getStringExtra("available_balance"));

        button_ti.setOnClickListener(this);
        money.setText(available_balance+"");
        if(flag==0){
            layout_tixian.setVisibility(View.GONE);
            layout_bind.setVisibility(View.VISIBLE);
            title_text.setText(R.string.BindPaybao);

        }else {
            layout_tixian.setVisibility(View.VISIBLE);
            layout_bind.setVisibility(View.GONE);
            title_text.setText("支付宝提现");


        }

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
            case R.id.button_binding:

                bing();

                break;
            case R.id.button_ti:
                if(money_edit.getText().toString().equals("")){
                    ToastUtil.showToast1(this,"提现金额不能为空");
                }else {
                    if(Double.parseDouble(money_edit.getText().toString().trim())>available_balance){
                        ToastUtil.showToast1(this,"输入的金额不能大于可提现金额");


                    }else {


                        refilect();


                    }


                }

                break;
        }
    }

    private void refilect() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("withdraw_type",0+"");
        hashMap.put("withdraw_price",money_edit.getText().toString().trim()+"");
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl+"app/account/addWithdraw", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        CodeBean codeBean  = JSON.parseObject(result,CodeBean.class);
                        if(codeBean.getCode()==10000){
                            finish();
                            ToastUtil.showToast1(BandingPayActivity.this,"提交成功");
                        }else {
                            ToastUtil.showToast1(BandingPayActivity.this,codeBean.getMsg()+"");

                        }

                    }
                });


            }
        });




    }


    private void bing() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("pay_phone",pay_phone.getText().toString().trim());

        OkhttpUtlis okhttpUtlis= new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/user/bindUserAliPay", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        CodeBean codeBean = JSON.parseObject(result,CodeBean.class);
                        if(codeBean.getCode()==10000){
                            finish();
                            ToastUtil.showToast1(BandingPayActivity.this,codeBean.getMsg()+"");

                        }else {
                            ToastUtil.showToast1(BandingPayActivity.this,codeBean.getMsg()+"");
                        }


                    }
                });


            }
        });


    }
}
