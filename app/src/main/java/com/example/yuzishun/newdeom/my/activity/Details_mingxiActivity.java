package com.example.yuzishun.newdeom.my.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Details_mingxiActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back_left)
    LinearLayout image_back_left;
    @BindView(R.id.image_back_right)
    LinearLayout image_back_right;

    @Override
    public int intiLayout() {
        return R.layout.activity_whole_order;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("明细详情");
        image_back_left.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back_left:
                finish();

                break;

        }
    }
}
