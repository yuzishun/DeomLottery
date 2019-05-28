package com.example.yuzishun.newdeom;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.login.activity.LoginActivity;
import com.example.yuzishun.newdeom.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplachActivity extends BaseActivity {
    private Handler handler;
    private Runnable runnable;
    @BindView(R.id.Splach_id)
    ImageView mSplach_id;


    @Override
    public int intiLayout() {
        return R.layout.activity_splach;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {

                SpUtil spUtil = new SpUtil(SplachActivity.this,"token");
                String token =  spUtil.getString("token","null");
                if(token.equals("")){
                    finish();
                    startActivity(new Intent(SplachActivity.this, LoginActivity.class));
                }else {
                    finish();
                    Content.ToKen=token;
                    startActivity(new Intent(SplachActivity.this, MainActivity.class));

                }

            }
        },2000);

    }
}
