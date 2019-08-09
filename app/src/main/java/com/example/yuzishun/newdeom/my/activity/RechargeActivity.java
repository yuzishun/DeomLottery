package com.example.yuzishun.newdeom.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.login.custom.ClearEditText;
import com.example.yuzishun.newdeom.model.PayBean;
import com.example.yuzishun.newdeom.my.adapter.GridView_Recharge_Adapter;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @BindView(R.id.GridView_recharge_Money)
    GridView GridView_recharge_Money;
    @BindView(R.id.layout_lineMoney)
    LinearLayout layout_lineMoney;
    @BindView(R.id.layout_pay)
    LinearLayout layout_pay;
    @BindView(R.id.money_edit)
    ClearEditText money_edit;
    @BindView(R.id.money)
    TextView money;
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
        Intent intent =getIntent();
        String balance = intent.getStringExtra("balance");
        money.setText(balance);
        image_back.setOnClickListener(this);
        layout_lineMoney.setOnClickListener(this);
        layout_pay.setOnClickListener(this);

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
//                startActivity(new Intent(this,LineMoneyActivity.class));
                break;
            case R.id.layout_pay:
                if(money_edit.getText().toString().equals("")){
                    ToastUtil.showToast1(this,"金额不能为空");

                }else {

                if(Double.parseDouble(money_edit.getText().toString().trim())<10||Double.parseDouble(money_edit.getText().toString().trim())>5000){

                    ToastUtil.showToast1(this,"输入金额，不符合规定");
                }else {

                pay();
                }
                }


                break;
        }
    }

    private void pay() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("amount",money_edit.getText().toString().trim());
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
