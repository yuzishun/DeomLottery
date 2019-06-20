package com.example.yuzishun.newdeom.login.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
 * 这是注册界面
 */
public class ResignActivity extends BaseActivity implements View.OnClickListener {
    private RegexUtils regexUtils;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.button_resign)
    Button button_resign;
    @BindView(R.id.usernameEditText)
    ClearEditText usernameEditText;
    @BindView(R.id.passwordEditText)
    ClearEditText passwordEditText;
    @BindView(R.id.phoneEditText)
    ClearEditText phoneEditText;
    @BindView(R.id.phonecodeEditText)
    ClearEditText phonecodeEditText;
    @BindView(R.id.yaoqingEditText)
    ClearEditText yaoqingEditText;
    @BindView(R.id.check_xieyi)
    CheckBox check_xieyi;
    @BindView(R.id.Hou_Code)
    Button Hou_Code;

    @Override
    public int intiLayout() {
        return R.layout.activity_resign;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        regexUtils = new RegexUtils(this);
        image_back.setOnClickListener(this);
        button_resign.setOnClickListener(this);
        title_text.setText(R.string.UserResign);
        Hou_Code.setOnClickListener(this);
    }

    @Override
    public void initData() {
        CheckEditForButton checkEditForButton = new CheckEditForButton(button_resign);
        checkEditForButton.addEditText(usernameEditText,phoneEditText,passwordEditText,phonecodeEditText,yaoqingEditText);
        checkEditForButton.setListener(new EditTextChangeListener() {
            @Override
            public void allHasContent(boolean isHasContent) {
                if (isHasContent) {

                    if(!usernameEditText.getText().toString().trim().equals("")&&regexUtils.isPhone(phoneEditText.getText().toString().trim()) == true
                            &&regexUtils.isPassWord(passwordEditText.getText().toString().trim())==true
                            &&!phonecodeEditText.getText().toString().trim().equals("")
                            && !yaoqingEditText.getText().toString().equals("")){
                        button_resign.setEnabled(true);
                    }else {
                        button_resign.setEnabled(false);

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
            case R.id.button_resign:
                resign(usernameEditText.getText().toString().trim(),phoneEditText.getText().toString().trim(),passwordEditText.getText().toString().trim()
                ,phonecodeEditText.getText().toString().trim(),yaoqingEditText.getText().toString().trim());

                break;
            case R.id.Hou_Code:
                //点击这里开始倒计时
                if(regexUtils.isPhone(phoneEditText.getText().toString().trim())==false){
                    Toast.makeText(this, "该号码不存在", Toast.LENGTH_SHORT).show();
                }else {
                    code(phoneEditText.getText().toString().trim());
                }
                break;
        }
    }

    private void resign(String username,String phone,String password,String auth_code,String invite_code) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("phone",phone);
        hashMap.put("uname",username);
        hashMap.put("password",password);
        hashMap.put("auth_code",auth_code);
        hashMap.put("invite_code",invite_code);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/user/register", hashMap, new Callback() {
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
                            startActivity(new Intent(ResignActivity.this,LoginActivity.class));
                            ToastUtil.showToast1(ResignActivity.this,codeBean.getMsg()+"");

                        }else {
                            ToastUtil.showToast1(ResignActivity.this,codeBean.getMsg()+"");


                        }


                    }
                });

            }
        });


    }

    public void code(String resign_phone){

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("phone",resign_phone);
        hashMap.put("code_type","0");

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

                            ToastUtil.showToast1(ResignActivity.this,codeBean.getMsg()+"");
                        }else {
                            ToastUtil.showToast1(ResignActivity.this,codeBean.getMsg()+"");



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
            Hou_Code.setClickable(false);
            Hou_Code.setEnabled(false);

            Hou_Code.setText(l / 1000 + "s");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            Hou_Code.setText("重新获取");
            Hou_Code.setEnabled(true);
            //设置可点击
            Hou_Code.setClickable(true);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
