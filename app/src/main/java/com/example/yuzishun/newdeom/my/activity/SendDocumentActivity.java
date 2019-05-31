package com.example.yuzishun.newdeom.my.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.login.custom.ClearEditText;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.my.adapter.GridView_sendorder_Adapter;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    @BindView(R.id.button_send)
    Button button_send;
    @BindView(R.id.phoneEditText)
    ClearEditText phoneEditText;
    @BindView(R.id.money_Textview)
    TextView money_Textview;
    private  GridView_sendorder_Adapter gridView_sendorder_adapter;
    private String[] list1=new String[]{"1%","2%","3%","4%","5%","6%","7%","8%","9%","10%"};
    private List<String> list = new ArrayList<>();
    private int plan_status=0,order_id,multiple_price,multiple;
    @Override
    public int intiLayout() {
        return R.layout.activity_send_document;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("发起跟单");
        Intent intent = getIntent();
        order_id = intent.getIntExtra("order_id",0);
        multiple_price = intent.getIntExtra("multiple_price",0);

        multiple = intent.getIntExtra("multiple",0);

        image_back_left.setOnClickListener(this);
        Button_one.setOnClickListener(this);
        Button_two.setOnClickListener(this);
        Button_three.setOnClickListener(this);
        button_send.setOnClickListener(this);
        money_Textview.setText("跟单金额"+multiple_price+"元,共"+multiple+"倍");

    }

    @Override
    public void initData() {
        list.clear();
        for (int i = 0; i <list1.length ; i++) {
            list.add(list1[i]);

        }
        gridView_sendorder_adapter = new GridView_sendorder_Adapter(this,list);
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
                plan_status = 0;
                break;
            case R.id.Button_two:
                change(Button_two,Button_one,Button_three);
                plan_status = 2;

                break;
            case R.id.Button_three:
                change(Button_three,Button_two,Button_one);
                plan_status = 1;

                break;
            case R.id.button_send:
                if(phoneEditText.getText().toString().trim().equals("")){
                    ToastUtil.showToast1(this,"文案宣言不能不填");
                }else {
                    sendorder();

                }



                break;
        }
    }

    private void sendorder() {

        int selection = gridView_sendorder_adapter.getSelection();


        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("plan_desc",phoneEditText.getText().toString().trim());
        hashMap.put("plan_profits",selection+"");
        hashMap.put("order_id",order_id+"");
        hashMap.put("plan_status",plan_status+"");
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl + "order/initiateOrderPlan", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        CodeBean codeBean = JSON.parseObject(result,CodeBean.class);
                        if(codeBean.getCode()==10000){
                            finish();
                            Toast.makeText(SendDocumentActivity.this, codeBean.getMsg()+"", Toast.LENGTH_SHORT).show();
                            
                        }else {

                            Toast.makeText(SendDocumentActivity.this, codeBean.getMsg()+"", Toast.LENGTH_SHORT).show();

                        }



                    }
                });



            }
        });




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
