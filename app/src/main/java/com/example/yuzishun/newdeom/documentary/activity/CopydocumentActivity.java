package com.example.yuzishun.newdeom.documentary.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.documentary.custom.AmountView;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 复制跟单页面
 */
public class CopydocumentActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.amountView)
    AmountView amountView;
    @BindView(R.id.text_moneytwo)
    TextView text_moneytwo;
    @BindView(R.id.text_name)
    TextView text_name;
    @BindView(R.id.text_money)
    TextView text_money;
    @BindView(R.id.text_plan_profits)
    TextView text_plan_profits;
    @BindView(R.id.text_time)
    TextView text_time;
    private String plan_id;
    private int multiple=1;
    @BindView(R.id.button_submit)
    Button button_submit;
    @Override
    public int intiLayout() {
        return R.layout.activity_copydocument;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        button_submit.setOnClickListener(this);
        title_text.setText("复制跟单");
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        text_name.setText(bundle.getString("name"));
        text_moneytwo.setText(bundle.getString("money"));
        text_money.setText(bundle.getString("money"));
        text_plan_profits.setText(Double.parseDouble(bundle.getString("plan_profits"))*100+"%");
        text_time.setText(bundle.getString("time")+"");
        plan_id = bundle.getString("plan_id");


    }

    @Override
    public void initData() {
        amountView.setGoods_storage(99999);
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                multiple = amount;
                text_moneytwo.setText(Double.parseDouble(text_money.getText().toString().trim())*amount+"");
//                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.button_submit:
                if(Double.parseDouble(text_moneytwo.getText().toString().trim())<100.00){

                    ToastUtil.showToast1(CopydocumentActivity.this,"跟单金额不能小于100");
                }else {
                    submit();

                }

                break;

        }
    }

    private void submit() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("plan_id",plan_id+"");
        hashMap.put("multiple",multiple+"");
        OkhttpUtlis okhttpUtlis  = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/order/installFollowOrder", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CodeBean codeBean = JSON.parseObject(result,CodeBean.class);
                        if(codeBean.getCode()==10000){
                            finish();

                            ToastUtil.showToast1(CopydocumentActivity.this,codeBean.getMsg()+"");

                        }else {
                            ToastUtil.showToast1(CopydocumentActivity.this,codeBean.getMsg()+"");

                        }



                    }
                });



            }
        });



    }


}
