package com.example.yuzishun.newdeom.my.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.my.adapter.GridView_sendorder_Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendDocumentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back_left)
    LinearLayout image_back_left;
    @BindView(R.id.image_back_right)
    LinearLayout image_back_right;
    @BindView(R.id.Button_one)
    Button Button_one;
    @BindView(R.id.Button_two)
    Button Button_two;
    @BindView(R.id.Button_three)
    Button Button_three;
    @BindView(R.id.GridView_sendorder)
    GridView GridView_sendorder;
    private String[] list1=new String[]{"1%","2%","3%","4%","5%","6%","7%","8%","9%","10%"};
    private List<String> list = new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.activity_send_document;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("发起跟单");
        image_back_left.setOnClickListener(this);
        Button_one.setOnClickListener(this);
        Button_two.setOnClickListener(this);
        Button_three.setOnClickListener(this);



    }

    @Override
    public void initData() {
        list.clear();
        for (int i = 0; i <list1.length ; i++) {
            list.add(list1[i]);

        }
        final GridView_sendorder_Adapter gridView_sendorder_adapter = new GridView_sendorder_Adapter(this,list);
        GridView_sendorder.setAdapter(gridView_sendorder_adapter);
        GridView_sendorder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridView_sendorder_adapter.choiceState(position);

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back_left:
                finish();
                break;
            case R.id.Button_one:
                change(Button_one,Button_two,Button_three);
                break;
            case R.id.Button_two:
                change(Button_two,Button_one,Button_three);

                break;
            case R.id.Button_three:
                change(Button_three,Button_two,Button_one);

                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void change(Button one,Button two,Button three){
        one.setBackground(getResources().getDrawable(R.drawable.sendorder_shape_red));
        two.setBackground(getResources().getDrawable(R.drawable.sendorder_shape));
        three.setBackground(getResources().getDrawable(R.drawable.sendorder_shape));
        one.setTextColor(getResources().getColor(R.color.white));
        two.setTextColor(getResources().getColor(R.color.font_black));
        three.setTextColor(getResources().getColor(R.color.font_black));


    }
}
