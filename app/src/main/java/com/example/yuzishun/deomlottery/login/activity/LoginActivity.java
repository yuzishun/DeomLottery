package com.example.yuzishun.deomlottery.login.activity;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.deomlottery.MainActivity;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.base.Content;
import com.example.yuzishun.deomlottery.login.custom.CheckEditForButton;
import com.example.yuzishun.deomlottery.login.custom.ClearEditText;
import com.example.yuzishun.deomlottery.login.custom.ClearEditText_pass;
import com.example.yuzishun.deomlottery.login.custom.EditTextChangeListener;
import com.example.yuzishun.deomlottery.model.LoginBean;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.RegexUtils;
import com.example.yuzishun.deomlottery.utils.SpUtil;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 *这是登陆界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private RegexUtils regexUtils;
    @BindView(R.id.layout_login)
    LinearLayout layout_login;
    @BindView(R.id.login_phone)
    ClearEditText login_phone;
    @BindView(R.id.login_password)
    ClearEditText_pass login_password;
    @BindView(R.id.cbDisplayPassword)
    CheckBox cbDisplayPassword;
    @BindView(R.id.button_sure)
    Button button_sure;
    @BindView(R.id.resignTextView)
    TextView resignTextView;
    @BindView(R.id.findTextView)
    TextView findTextView;
    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        regexUtils = new RegexUtils(this);
        button_sure.setOnClickListener(this);
        resignTextView.setOnClickListener(this);
        findTextView.setOnClickListener(this);




    }

    @Override
    public void initData() {
        //监听是不是可以点击的button
        CheckEditForButton checkEditForButton = new CheckEditForButton(button_sure);
        checkEditForButton.addEditText(login_phone,login_password);
        //3.根据接口回调的方法,分别进行操作
        checkEditForButton.setListener(new EditTextChangeListener() {
            @Override
            public void allHasContent(boolean isHasContent) {
                if (isHasContent) {

                    if(regexUtils.isPhone(login_phone.getText().toString().trim()) == true && regexUtils.isPassWord(login_password.getText().toString().trim()) == true){
                        button_sure.setEnabled(true);
                    }else {
                        button_sure.setEnabled(false);

                    }
                } else {
                }
            }
        });
        //对于checkbox的操作
        cbDisplayPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //选择状态 显示明文--设置为可见的密码
                    login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    login_password.setSelection(login_password.getText().length());
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    login_password.setSelection(login_password.getText().length());

                }
            }
        });


    }
//点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_sure:
                login(login_phone.getText().toString().trim(),login_password.getText().toString().trim());

                break;
            case R.id.resignTextView:
                startActivity(new Intent(this,ResignActivity.class));

                break;
            case R.id.findTextView:
                Intent intent = new Intent(this, FindPasswordActivity.class);
                intent.putExtra("flag",0);
                startActivity(intent);
                break;

        }
    }

    private void login(String login_phone,String login_password) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("phone",login_phone);
        hashMap.put("password",login_password);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl+"app/login/login", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("YZS",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            org.json.JSONObject jsonObject = new org.json.JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){
                                LoginBean loginbean = JSON.parseObject(result,LoginBean.class);

                                ToastUtil.showToast1(LoginActivity.this,msg+"");

                                Content.ToKen=loginbean.getData().getToken();
                                SpUtil spUtil = new SpUtil(LoginActivity.this,"token");
                                spUtil.putString("token",loginbean.getData().getToken());
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();

                            }else {
                                ToastUtil.showToast1(LoginActivity.this,msg+"");


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
    protected void onDestroy() {
        super.onDestroy();

    }
}
