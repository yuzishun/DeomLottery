package com.example.yuzishun.deomlottery.main.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.main.adapter.RecyclerViewLotterySonAdapter;
import com.example.yuzishun.deomlottery.main.adapter.RecyclerViewLotterySon_bask_Adapter;
import com.example.yuzishun.deomlottery.model.PeriodBean;
import com.example.yuzishun.deomlottery.model.PeriodBean_Bask;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class  LotterywangqiActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.RecyclerView_Lottery_Wangqi)
    RecyclerView RecyclerView_Lottery_Wangqi;
    @BindView(R.id.layout_empt)
    LinearLayout layout_empt;
    private int flag;
    private ArrayList<PeriodBean.DataBean> list = new ArrayList<>();
    private ArrayList<PeriodBean_Bask.DataBean> list2 = new ArrayList<>();

    @Override
    public int intiLayout()  {
        return R.layout.activity_lotterywangqi;
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",0);
        if(flag==0){
            title_text.setText("竞彩足球往期开奖");

        }else {
            title_text.setText("竞彩篮球往期开奖");

        }
        image_back.setOnClickListener(this);
        RecyclerView_Lottery_Wangqi.setLayoutManager(new LinearLayoutManager(this));
        request();


    }

    private void request() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getPastBallList?game_type=" + flag, new Callback() {
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

                                if(flag==0){
                                    PeriodBean periodBean = JSON.parseObject(result,PeriodBean.class);

                                    if(periodBean.getData().size()==0){

                                        layout_empt.setVisibility(View.VISIBLE);

                                    }else {
                                        layout_empt.setVisibility(View.GONE);
                                        List<PeriodBean.DataBean> data = periodBean.getData();
                                        list.addAll(data);
                                        RecyclerViewLotterySonAdapter recyclerViewLotterySonAdapter = new RecyclerViewLotterySonAdapter(LotterywangqiActivity.this,list);
                                        RecyclerView_Lottery_Wangqi.setAdapter(recyclerViewLotterySonAdapter);
                                    }

                                }else {
                                    PeriodBean_Bask periodBean = JSON.parseObject(result,PeriodBean_Bask.class);

                                    if(periodBean.getData().size()==0){

                                        layout_empt.setVisibility(View.VISIBLE);

                                    }else {
                                        layout_empt.setVisibility(View.GONE);

                                        List<PeriodBean_Bask.DataBean> data = periodBean.getData();
                                        list2.addAll(data);
                                        RecyclerViewLotterySon_bask_Adapter recyclerViewLotterySonAdapter = new RecyclerViewLotterySon_bask_Adapter(LotterywangqiActivity.this,list2);
                                        RecyclerView_Lottery_Wangqi.setAdapter(recyclerViewLotterySonAdapter);
                                    }

                                }




                            }else {
                                ToastUtil.showToast1(LotterywangqiActivity.this,msg);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;

        }
    }
}
