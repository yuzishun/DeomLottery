package com.example.yuzishun.newdeom.main.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.main.adapter.LotteryRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 * 中奖页面
 *
 */
public class LotteryActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.Lottery_RecyCLerView)
    RecyclerView Lottery_RecyCLerView;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    private LotteryRecyclerViewAdapter lotteryRecyclerViewAdapter;
    @Override
    public int intiLayout() {
        return R.layout.activity_lottery;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("中奖");
        image_back.setOnClickListener(this);
        Lottery_RecyCLerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("");

        }
        lotteryRecyclerViewAdapter = new LotteryRecyclerViewAdapter(LotteryActivity.this,list);
        Lottery_RecyCLerView.setAdapter(lotteryRecyclerViewAdapter);
        lotteryRecyclerViewAdapter.setOnRecyclerViewListener(new LotteryRecyclerViewAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(LotteryActivity.this,LotterywangqiActivity.class));

            }
        });

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
