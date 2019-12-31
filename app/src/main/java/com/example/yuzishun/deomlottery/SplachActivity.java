package com.example.yuzishun.deomlottery;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.base.Content;
import com.example.yuzishun.deomlottery.login.activity.LoginActivity;
import com.example.yuzishun.deomlottery.utils.SpUtil;

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
