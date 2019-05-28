package com.example.yuzishun.newdeom.my.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.my.adapter.GridView_Recharge_Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechargeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.GridView_recharge_Money)
    GridView GridView_recharge_Money;
    @BindView(R.id.layout_lineMoney)
    LinearLayout layout_lineMoney;
    private String[] list1=new String[]{"98元","198元","498元","998元","2998元","4998元",};
    private List<String> list = new ArrayList<>();
    private GridView_Recharge_Adapter gridView_recharge_adapter;
    @Override
    public int intiLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("充值");
        image_back.setOnClickListener(this);
        layout_lineMoney.setOnClickListener(this);

    }

    @Override
    public void initData() {
        list.clear();
        for (int i = 0; i <list1.length ; i++) {
            list.add(list1[i]);

        }
        gridView_recharge_adapter =   new GridView_Recharge_Adapter(RechargeActivity.this,list);

        GridView_recharge_Money.setAdapter(gridView_recharge_adapter);
        GridView_recharge_Money.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridView_recharge_adapter.choiceState(position);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.layout_lineMoney:
                startActivity(new Intent(this,LineMoneyActivity.class));
                break;
        }
    }
}
