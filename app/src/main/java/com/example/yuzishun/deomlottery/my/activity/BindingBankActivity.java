package com.example.yuzishun.deomlottery.my.activity;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.login.custom.CheckEditForButton;
import com.example.yuzishun.deomlottery.login.custom.ClearEditText;
import com.example.yuzishun.deomlottery.login.custom.EditTextChangeListener;
import com.example.yuzishun.deomlottery.model.CodeBean;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.RegexUtils;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BindingBankActivity extends BaseActivity implements View.OnClickListener {
    private RegexUtils regexUtils;

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.button_bingd)
    Button button_bingd;
    @BindView(R.id.Edittext_bannumber)
    ClearEditText Edittext_bannumber;

    @BindView(R.id.Edittext_phonenumber)
    ClearEditText Edittext_phonenumber;
    @BindView(R.id.phonecodeEditText)
    ClearEditText phonecodeEditText;
    @BindView(R.id.hou_code)
    Button hou_code;


    @Override
    public int intiLayout() {
        return R.layout.activity_binding_bank;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("绑定银行卡");
        regexUtils = new RegexUtils(this);
        hou_code.setOnClickListener(this);
        image_back.setOnClickListener(this);
        button_bingd.setOnClickListener(this);
    }

    @Override
    public void initData() {
        //监听是不是可以点击的button
        CheckEditForButton checkEditForButton = new CheckEditForButton(button_bingd);
        checkEditForButton.addEditText(Edittext_bannumber,Edittext_phonenumber,phonecodeEditText);
        //3.根据接口回调的方法,分别进行操作
        checkEditForButton.setListener(new EditTextChangeListener() {
            @Override
            public void allHasContent(boolean isHasContent) {
                if (isHasContent) {

                    if(regexUtils.checkBankCard(Edittext_bannumber.getText().toString().trim()) == true && regexUtils.isPhone(Edittext_phonenumber.getText().toString().trim()) == true
                            && !phonecodeEditText.getText().toString().trim().equals("")
                            ){
                        button_bingd.setEnabled(true);
                    }else {
                        button_bingd.setEnabled(false);

                    }
                } else {
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.button_bingd:
                bind(Edittext_bannumber.getText().toString().trim(),Edittext_phonenumber.getText().toString().trim(),phonecodeEditText.getText().toString().trim());
                break;
            case R.id.hou_code:

                code(Edittext_phonenumber.getText().toString().trim());

                break;
        }
    }

    private void bind(String banknumber,String phonenumber,String phonecode) {

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("card_no",banknumber);
        hashMap.put("card_phone",phonenumber);
        hashMap.put("auth_code",phonecode);

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/user/bindBankCard", hashMap, new Callback() {
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
                            ToastUtil.showToast1(BindingBankActivity.this,codeBean.getMsg()+"");
                            finish();
                        }else {
                            ToastUtil.showToast1(BindingBankActivity.this,codeBean.getMsg()+"");


                        }

                    }
                });


            }
        });



    }

    public void code(String resign_phone){

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("phone",resign_phone);
        hashMap.put("code_type","2");

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/auth_code/sendCode", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CodeBean codeBean = JSON.parseObject(result, CodeBean.class);

                        if(codeBean.getCode()==10000){

                            final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);
                            myCountDownTimer.start();
                            ToastUtil.showToast1(BindingBankActivity.this,codeBean.getMsg()+"");

                        }else {
                            ToastUtil.showToast1(BindingBankActivity.this,codeBean.getMsg()+"");


                        }


                    }
                });



            }
        });


    }

    //倒计时函数
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            hou_code.setClickable(false);
            hou_code.setEnabled(false);

            hou_code.setText(l / 1000 + "s");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            hou_code.setText("重新获取");
            hou_code.setEnabled(true);
            //设置可点击
            hou_code.setClickable(true);
        }
    }

}
