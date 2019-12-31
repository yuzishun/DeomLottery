package com.example.yuzishun.deomlottery.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 * 中奖页面
 *
 */
public class LotteryActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.layout_foot)
    LinearLayout layout_foot;
    @BindView(R.id.layout_back)
    LinearLayout layout_back;
    @Override
    public int intiLayout() {
        return R.layout.activity_lottery;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("开奖");
        image_back.setOnClickListener(this);
        layout_foot.setOnClickListener(this);
        layout_back.setOnClickListener(this);

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
            case R.id.layout_foot:
                jump(0);

                break;
            case R.id.layout_back:
                jump(1);

                break;
        }
    }

    public void jump(int flag){
        Intent intent = new Intent(this,LotterywangqiActivity.class);
        intent.putExtra("flag",flag);
        startActivity(intent);

    }
}
