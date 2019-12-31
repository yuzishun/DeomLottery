package com.example.yuzishun.deomlottery.documentary.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.documentary.adapter.BonusDetails_adapter;
import com.example.yuzishun.deomlottery.model.BonusDetailsBean;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BonusdetailsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.Seo_RecyclerView)
    RecyclerView Seo_RecyclerView;
    @BindView(R.id.seo_state_text)
    TextView seo_state_text;
    @BindView(R.id.seo_number)
    TextView seo_number;
    private String order_id;
    private BonusDetails_adapter adapter;
    @Override
    public int intiLayout() {
        return R.layout.activity_bonusdetails;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("优化详情");
        image_back.setOnClickListener(this);
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        Seo_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getdata();
    }

    private void getdata() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/order/getOrderSeoInfoByOrderID?order_id=" + order_id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                BonusDetailsBean bonusDetailsBean = com.alibaba.fastjson.JSONObject.parseObject(result,BonusDetailsBean.class);

                                switch (bonusDetailsBean.getData().getSeo_status()){
                                    case 1:
                                        seo_state_text.setText("优化方式：平均优化");

                                        break;
                                    case 2:
                                        seo_state_text.setText("优化方式：博热优化");

                                        break;
                                    case 3:
                                        seo_state_text.setText("优化方式：博冷优化");

                                        break;

                                }
                                seo_number.setText("方案详情：注数："+bonusDetailsBean.getData().getPour()+"注");
                                adapter = new BonusDetails_adapter(BonusdetailsActivity.this,bonusDetailsBean.getData().getSeo_info());
                                Seo_RecyclerView.setAdapter(adapter);

                            }else {
                                ToastUtil.showToast1(BonusdetailsActivity.this,msg);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_back:
                finish();
                break;

        }
    }
}
