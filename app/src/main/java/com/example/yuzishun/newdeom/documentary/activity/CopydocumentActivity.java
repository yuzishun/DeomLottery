package com.example.yuzishun.newdeom.documentary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.documentary.custom.AmountView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 复制跟单页面
 */
public class CopydocumentActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.amountView)
    AmountView amountView;

    @Override
    public int intiLayout() {
        return R.layout.activity_copydocument;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        title_text.setText("复制跟单");
    }

    @Override
    public void initData() {
        amountView.setGoods_storage(50);
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
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
