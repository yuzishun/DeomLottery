package com.example.yuzishun.newdeom.main.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.main.adapter.RecyclerLotteryWangQiAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LotterywangqiActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.RecyclerView_Lottery_Wangqi)
    RecyclerView RecyclerView_Lottery_Wangqi;
    @Override
    public int intiLayout() {
        return R.layout.activity_lotterywangqi;
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);
        title_text.setText("竞猜篮球往期开奖");
        image_back.setOnClickListener(this);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("");
        }
        RecyclerView_Lottery_Wangqi.setNestedScrollingEnabled(false);
        RecyclerView_Lottery_Wangqi.setLayoutManager(new LinearLayoutManager(this));
        RecyclerLotteryWangQiAdapter recyclerLotteryWangQiAdapter = new RecyclerLotteryWangQiAdapter(this,list);

        RecyclerView_Lottery_Wangqi.setAdapter(recyclerLotteryWangQiAdapter);


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
