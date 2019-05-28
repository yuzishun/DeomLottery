package com.example.yuzishun.newdeom.my.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.login.custom.CheckEditForButton;
import com.example.yuzishun.newdeom.login.custom.ClearEditText;
import com.example.yuzishun.newdeom.login.custom.EditTextChangeListener;
import com.example.yuzishun.newdeom.utils.RegexUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.msg_phone)
    ClearEditText msg_phone;
    @BindView(R.id.button_binding)
    Button button_binding;
    @Override
    public int intiLayout() {
        return R.layout.activity_banding_pay;
    }
    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText(R.string.BindPaybao);
        image_back.setOnClickListener(this);
        regexUtils  = new RegexUtils(this);
    }
    @Override
    public void initData() {
        CheckEditForButton checkEditForButton = new CheckEditForButton(button_binding);
        checkEditForButton.addEditText(pay_phone,msg_phone);
        checkEditForButton.setListener(new EditTextChangeListener() {
            @Override
            public void allHasContent(boolean isHasContent) {
                if (isHasContent) {
                    if(!msg_phone.getText().toString().trim().equals("")&&regexUtils.isPassWord(pay_phone.getText().toString().trim())==true){
                        button_binding.setEnabled(true);
                    }else {
                        button_binding.setEnabled(false);
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
        }
    }
}
