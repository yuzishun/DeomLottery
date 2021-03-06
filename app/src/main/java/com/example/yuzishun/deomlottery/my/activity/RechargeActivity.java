package com.example.yuzishun.deomlottery.my.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.login.custom.ClearEditText;
import com.example.yuzishun.deomlottery.model.PayBean;
import com.example.yuzishun.deomlottery.model.PayTestBean;
import com.example.yuzishun.deomlottery.my.adapter.PayListAdapter;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RechargeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;



    @BindView(R.id.money_edit)
    ClearEditText money_edit;
    @BindView(R.id.money)
    TextView money;


    @BindView(R.id.Pay_recyclerView)
    RecyclerView Pay_recyclerView;
    private int pay_type=0;
    private PayListAdapter payListAdapter;
    @Override
    public int intiLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("充值");
        Intent intent =getIntent();
        String balance = intent.getStringExtra("balance");
        money.setText(balance);
        image_back.setOnClickListener(this);

        Pay_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        request();

    }
    private void request() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        HashMap<String,String> hashMap = new HashMap<>();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/account/queryAccountPayUrl", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        PayTestBean payTestBean = JSON.parseObject(result,PayTestBean.class);
                        if(payTestBean.getCode()==10000){

                            payListAdapter = new PayListAdapter(RechargeActivity.this,payTestBean.getData());
                            Pay_recyclerView.setAdapter(payListAdapter);
                            payListAdapter.setOnRecyclerViewListener(new PayListAdapter.OnRecyclerViewListener() {
                                @Override
                                public void onItemClick(int pay_e) {
                                    pay_type=pay_e;
                                    if(money_edit.getText().toString().equals("")){
                                        ToastUtil.showToast1(RechargeActivity.this,"金额不能为空");

                                    }else {

//                                        if(Double.parseDouble(money_edit.getText().toString().trim())<10||Double.parseDouble(money_edit.getText().toString().trim())>5000){
//
//                                            ToastUtil.showToast1(RechargeActivity.this,"输入金额，不符合规定");
//                                        }else {

                                            pay();
//                                        }
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(RechargeActivity.this, payTestBean.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

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

    private void pay() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("amount",money_edit.getText().toString().trim());
        hashMap.put("pay_type",pay_type+"");
        OkhttpUtlis okhttpUtlis= new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl+"app/account/accountPay", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("YZS",result.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(result);

                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){
                                PayBean payBean = JSON.parseObject(result,PayBean.class);
                            String url = payBean.getData().getUrl();
                            String orderid = payBean.getData().getOrder_id();
                            String amount = payBean.getData().getAmount();
                                    finish();
                                    Intent intent  = new Intent(RechargeActivity.this,WebViewPayActivity.class);
                                    intent.putExtra("url",url);
                                    intent.putExtra("orderid",orderid);
                                    intent.putExtra("amount",amount);
                                    startActivity(intent);
                            }else {
                                ToastUtil.showToast1(RechargeActivity.this,msg+"");

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





//                            Uri uri = Uri.parse(url+"?orderid="+orderid+"&amount="+amount);
//                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                            startActivity(intent);



                    }
                });


            }
        });


    }


}
