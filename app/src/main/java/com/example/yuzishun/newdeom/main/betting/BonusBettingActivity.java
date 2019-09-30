package com.example.yuzishun.newdeom.main.betting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.documentary.custom.AmountView;
import com.example.yuzishun.newdeom.main.adapter.BonusRecyClerViewAdapter;
import com.example.yuzishun.newdeom.model.BonusBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BonusBettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.Bouns_RecyClerView)
    RecyclerView Bouns_RecyClerView;
    @BindView(R.id.amountView)
    AmountView amountView;
    @BindView(R.id.Bonus_Money_text)
    TextView Bonus_Money_text;
    @BindView(R.id.Range_Text)
    TextView Range_Text;
    private BonusRecyClerViewAdapter adapter;
    private List<BonusBean> list_bonus = new ArrayList<>();
    private List<Double> list_bonus_Range = new ArrayList<>();

    private String BonusMoney;
    @Override
    public int intiLayout() {
        return R.layout.activity_bonus_betting;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("奖金优化");
        image_back.setOnClickListener(this);
        Intent intent  = getIntent();
        list_bonus = (List<BonusBean>) intent.getSerializableExtra("getlist");

        BonusMoney = intent.getStringExtra("BonusMoney");
        amountView.setGoods_storage(99999);
        amountView.setTextd(Integer.parseInt(BonusMoney));
        Bonus_Money_text.setText(Integer.parseInt(BonusMoney)/2+"注 共"+BonusMoney+"元");
        for (int j = 0; j <list_bonus.size() ; j++) {

            Log.e("YZS",list_bonus.get(j).getOneBetBounsMoney()+"");
            Log.e("YZS",list_bonus.get(j).getSingleAdds_id());
            Log.e("YZS",list_bonus.get(j).getSingleGame_id());
            list_bonus_Range.add(list_bonus.get(j).getOneBetBounsMoney()*list_bonus.get(j).getNumber());

        }
        Log.e("YZS",list_bonus_Range.size()+"");
        Range_Text.setText(getRange());

        Bouns_RecyClerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BonusRecyClerViewAdapter(this,list_bonus);
        Bouns_RecyClerView.setAdapter(adapter);


    }

    @Override
    public void initData() {

    }

    //这个是单独计算奖金范围的
    public String getRange(){
        String range="奖金范围：";
        double max = list_bonus_Range.get(0);
        double min = list_bonus_Range.get(0);
        for (int i = 0; i < list_bonus_Range.size(); i++) {
            if(list_bonus_Range.get(i)>max){
                max = list_bonus_Range.get(i);
            }
            if(list_bonus_Range.get(i)<min){
                min = list_bonus_Range.get(i);
            }

        }

        range = "奖金范围："+min+" ～ "+max;

        return range;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_back:
                finish();
                break;

        }
    }
}
