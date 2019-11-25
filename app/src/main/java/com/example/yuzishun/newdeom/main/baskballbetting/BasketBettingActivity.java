package com.example.yuzishun.newdeom.main.baskballbetting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;

import butterknife.ButterKnife;

public class BasketBettingActivity extends BaseActivity {







    @Override
    public int intiLayout() {
        return R.layout.activity_basket_betting;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);



    }

    @Override
    public void initData() {

    }
}
