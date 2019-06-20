package com.example.yuzishun.newdeom.login.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.MainActivity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.login.custom.CheckEditForButton;
import com.example.yuzishun.newdeom.login.custom.ClearEditText;
import com.example.yuzishun.newdeom.login.custom.EditTextChangeListener;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.my.activity.SettingActivity;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.RegexUtils;
import com.example.yuzishun.newdeom.utils.SpUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 密码找回页面和修改密码页面
 */
public class FindPasswordActivity extends BaseActivity implements View.OnClickListener {
    private RegexUtils regexUtils;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.button_findpassword)
    Button button_findpassword;
    @BindView(R.id.phonecodeEditText)
    ClearEditText phonecodeEditText;
    @BindView(R.id.phoneEditText)
    ClearEditText phoneEditText;
    @BindView(R.id.newPassword)
    ClearEditText newPassword;
    @BindView(R.id.snd_code)
    Button snd_code;
    private int flag;
    @Override
    public int intiLayout() {
        return R.layout.activity_find_password;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        regexUtils  = new RegexUtils(this);
        image_back.setOnClickListener(this);
        snd_code.setOnClickListener(this);
        button_findpassword.setOnClickListener(this);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        //0是忘记密码，1是修改密码
        if(flag==0){
            title_text.setText("密码忘记");

        }else if (flag==1){
            title_text.setText(R.string.findpassword);

        }





    }

    @Override
    public void initData() {
        CheckEditForButton checkEditForButton = new CheckEditForButton(button_findpassword);
        checkEditForButton.addEditText(phoneEditText,newPassword);
        checkEditForButton.setListener(new EditTextChangeListener() {
            @Override
            public void allHasContent(boolean isHasContent) {
                if (isHasContent) {

                    if(!phonecodeEditText.getText().toString().trim().equals("")&&regexUtils.isPhone(phoneEditText.getText().toString().trim())&&regexUtils.isPassWord(newPassword.getText().toString().trim())==true
                         ){
                        button_findpassword.setEnabled(true);
                    }else {
                        button_findpassword.setEnabled(false);

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
            case R.id.snd_code:
                code(phoneEditText.getText().toString().trim());

                break;
            case R.id.button_findpassword:

                forget(flag,phoneEditText.getText().toString().trim(),phonecodeEditText.getText().toString().trim(),newPassword.getText().toString().trim());
                break;

        }
    }

    private void forget(final int flag, String find_phone, String find_codemsg, String find_password) {

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("phone",find_phone);
        hashMap.put("auth_code",find_codemsg);

        hashMap.put("password",find_password);
        hashMap.put("tag",flag+"");

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/user/forgetPwd", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CodeBean codeBean = JSON.parseObject(result,CodeBean.class);
                        if(codeBean.getCode()==10000){
                            if(flag==0){
                                finish();

                            }else if(flag==1){
                                finish();
                                SpUtil spUtil = new SpUtil(FindPasswordActivity.this,"token");
                                spUtil.putString("token","");
                                Intent intent = new Intent(FindPasswordActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

//                                startActivity(new Intent(FindPasswordActivity.this,LoginActivity.class));

                            }
                            ToastUtil.showToast1(FindPasswordActivity.this,codeBean.getMsg()+"");

                        }else {
                            ToastUtil.showToast1(FindPasswordActivity.this,codeBean.getMsg()+"");



                        }

                    }
                });

            }
        });
    }


    public void code(String resign_phone){

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("phone",resign_phone);
        hashMap.put("code_type","1");

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
                            ToastUtil.showToast1(FindPasswordActivity.this,codeBean.getMsg()+"");

                        }else {
                            ToastUtil.showToast1(FindPasswordActivity.this,codeBean.getMsg()+"");

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
            snd_code.setClickable(false);
            snd_code.setEnabled(false);

            snd_code.setText(l / 1000 + "s");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            snd_code.setText("重新获取");
            snd_code.setEnabled(true);
            //设置可点击
            snd_code.setClickable(true);
        }
    }
}
