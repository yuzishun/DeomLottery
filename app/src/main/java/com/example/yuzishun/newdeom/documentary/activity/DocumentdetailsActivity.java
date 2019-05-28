package com.example.yuzishun.newdeom.documentary.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.documentary.custom.AmountView;
import com.example.yuzishun.newdeom.my.activity.SendDocumentActivity;
import com.example.yuzishun.newdeom.my.custom.MyTableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 * 跟单的详情界面
 *
 */
public class DocumentdetailsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.MyTable)
    LinearLayout MyTable;
    @BindView(R.id.MyTable_gen)
    LinearLayout MyTable_gen;
    private String[] name={"用户名","跟单金额","跟单时间","奖金"};
    @BindView(R.id.button_senddocument)
    Button button_senddocument;
    @BindView(R.id.amountView)
    AmountView amountView;
    @Override
    public int intiLayout() {
        return R.layout.activity_documentdetails;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("跟单详情");
        image_back.setOnClickListener(this);
        button_senddocument.setOnClickListener(this);
    }

    @Override
    public void initData() {
        initdata_details(this,MyTable);
        initdata_documentary(this,MyTable_gen);
        amountView.setGoods_storage(50);
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initdata_details(Context context, LinearLayout linearLayout) {

        //初始化内容
        for (int i = 0; i < 2; i++) {
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.programme_details, null);
            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
            txt.setText("周六035\n04/28 00:30");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            txt.setText("莱加内斯\n维戈塞尔塔");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            txt.setText("平\n让球负\n0:0\n0球\n平平");
            txt.setTextColor(context.getResources().getColor(R.color.login_red));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            txt.setText("让球负(-1)(1.41)");
            txt.setTextColor(context.getResources().getColor(R.color.login_red));


            linearLayout.addView(relativeLayout);
        }
    }




    private void initdata_documentary(Context context,LinearLayout linearLayout) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
        MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
        title.setText(name[0]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));

        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
        title.setText(name[1]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
        title.setText(name[2]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
        title.setText(name[3]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));


        linearLayout.addView(relativeLayout);
        //初始化内容
        for (int i = 0; i < 2; i++) {
            relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
            txt.setText("k**w");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            txt.setText("2800.00元");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            txt.setText("08:36:45");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            txt.setText("----");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));


            linearLayout.addView(relativeLayout);


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.button_senddocument:
                startActivity(new Intent(this, SendDocumentActivity.class));
                break;

        }
    }
}
