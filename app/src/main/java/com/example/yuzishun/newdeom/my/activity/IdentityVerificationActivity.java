package com.example.yuzishun.newdeom.my.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
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

public class IdentityVerificationActivity extends BaseActivity implements View.OnClickListener {
    private RegexUtils regexUtils;

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.name_edittext)
    ClearEditText name_edittext;
    @BindView(R.id.id_edittext)
    ClearEditText id_edittext;
    @BindView(R.id.button_bao)
    Button button_bao;
    @Override
    public int intiLayout() {
        return R.layout.activity_identity_verification;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("身份验证");
        image_back.setOnClickListener(this);
        button_bao.setOnClickListener(this);
        regexUtils = new RegexUtils(this);

    }



    @Override
    public void initData() {
        //监听是不是可以点击的button
        CheckEditForButton checkEditForButton = new CheckEditForButton(button_bao);
        checkEditForButton.addEditText(name_edittext,id_edittext);
        //3.根据接口回调的方法,分别进行操作
        checkEditForButton.setListener(new EditTextChangeListener() {
            @Override
            public void allHasContent(boolean isHasContent) {
                if (isHasContent) {

                    if(!name_edittext.getText().toString().trim().equals("") && regexUtils.isLegalId(id_edittext.getText().toString().trim()) == true){
                        button_bao.setEnabled(true);
                    }else {
                        button_bao.setEnabled(false);

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
            case R.id.button_bao:
                net(name_edittext.getText().toString().trim(),id_edittext.getText().toString().trim());
                break;
        }
    }

    private void net(String name,String id) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("compellation",name);
        hashMap.put("identity_card",id);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "user/bindUserIdentityCard", hashMap, new Callback() {
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
                            Content.authentication=1;
                            ToastUtil.showToast1(IdentityVerificationActivity.this,codeBean.getMsg()+"");

                            finish();

                        }else {
                            ToastUtil.showToast1(IdentityVerificationActivity.this,codeBean.getMsg()+"");


                        }


                    }
                });

            }
        });
    }
}
