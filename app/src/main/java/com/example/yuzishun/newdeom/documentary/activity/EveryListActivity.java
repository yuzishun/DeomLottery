package com.example.yuzishun.newdeom.documentary.activity;

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
import com.example.yuzishun.newdeom.documentary.adapter.EveryListRecyclerViewAdapter;
import com.example.yuzishun.newdeom.main.adapter.LotteryRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class EveryListActivity extends BaseActivity implements View.OnClickListener {
    private TextView title_text;
    private LinearLayout image_back;
    private int flag;
    private RecyclerView List_RecyclerView;
    @Override
    public int intiLayout() {
        return R.layout.activity_every_list;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text = (TextView) findViewById(R.id.title_text);
        image_back = (LinearLayout) findViewById(R.id.image_back);
        List_RecyclerView = findViewById(R.id.List_RecyclerView);
        List_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add("");
        }

        image_back.setOnClickListener(this);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",0);
        if(flag==1){
            title_text.setText("盈利榜");

        }else if(flag==2){
            title_text.setText("命中榜");

        }else if(flag==3){
            title_text.setText("奖金榜");

        }else if(flag==4){
            title_text.setText("我的关注");

        }
        EveryListRecyclerViewAdapter everyListRecyclerViewAdapter = new EveryListRecyclerViewAdapter(this,list,flag);
        List_RecyclerView.setAdapter(everyListRecyclerViewAdapter);
        everyListRecyclerViewAdapter.setOnRecyclerViewListener(new LotteryRecyclerViewAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
            startActivity(new Intent(EveryListActivity.this,OkamiActivity.class));

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
