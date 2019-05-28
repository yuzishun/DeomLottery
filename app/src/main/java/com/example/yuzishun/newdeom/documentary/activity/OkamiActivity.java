package com.example.yuzishun.newdeom.documentary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.documentary.adapter.OkamiListRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 * 大神主页面
 */
public class OkamiActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.OkamiRecyclerView)
    RecyclerView OkamiRecyclerView;

    @Override
    public int intiLayout() {
        return R.layout.activity_okami;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("大神主页");
        image_back.setOnClickListener(this);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("");

        }
        OkamiRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OkamiListRecyclerViewAdapter okamiListRecyclerViewAdapter = new OkamiListRecyclerViewAdapter(OkamiActivity.this,list);
        OkamiRecyclerView.setAdapter(okamiListRecyclerViewAdapter);

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

        }
    }
}
