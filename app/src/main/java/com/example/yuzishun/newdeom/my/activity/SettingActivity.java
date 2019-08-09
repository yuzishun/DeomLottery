package com.example.yuzishun.newdeom.my.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzishun.newdeom.MainActivity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.SplachActivity;
import com.example.yuzishun.newdeom.WebViewCustomerActivity;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.login.activity.FindPasswordActivity;
import com.example.yuzishun.newdeom.login.activity.LoginActivity;
import com.example.yuzishun.newdeom.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置页面
 *
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.layout_feedback)
    LinearLayout layout_feedback;
    @BindView(R.id.button_lgoinout)
    Button button_lgoinout;
    @BindView(R.id.layout_changePassword)
    LinearLayout layout_changePassword;
    @BindView(R.id.help_center)
    LinearLayout help_center;
    @Override
    public int intiLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText(R.string.setting);
        image_back.setOnClickListener(this);
        layout_feedback.setOnClickListener(this);
        button_lgoinout.setOnClickListener(this);
        layout_changePassword.setOnClickListener(this);
        help_center.setOnClickListener(this);
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
            case R.id.layout_feedback:
                //跳转到一健反馈页面


                break;
            case R.id.help_center:
                startActivity(new Intent(this,WebViewCustomerActivity.class));


                break;
            case R.id.button_lgoinout:
                finish();
                MainActivity.intentsat.finish();
                SpUtil spUtil = new SpUtil(SettingActivity.this,"token");
                spUtil.putString("token","");
                Toast.makeText(this, "退出成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.layout_changePassword:
                Intent intent = new Intent(this, FindPasswordActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
                break;
        }
    }
}
