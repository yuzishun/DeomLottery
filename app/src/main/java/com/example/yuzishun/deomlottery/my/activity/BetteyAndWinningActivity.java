package com.example.yuzishun.deomlottery.my.activity;

import android.content.Intent;
import android.os.Handler;
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
import com.example.yuzishun.deomlottery.base.Content;
import com.example.yuzishun.deomlottery.documentary.activity.DocumentdetailsActivity;
import com.example.yuzishun.deomlottery.model.OrderBean;
import com.example.yuzishun.deomlottery.my.adapter.BetteyRecyclerViewAdapter;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.ToastUtil;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

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

public class BetteyAndWinningActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back_left)
    LinearLayout image_back_left;
    @BindView(R.id.image_back_right)
    LinearLayout image_back_right;
    @BindView(R.id.RecyclerView_BetteyAndwinning)
    RecyclerView RecyclerView_BetteyAndwinning;
    @BindView(R.id.Text_loading)
    TextView Text_loading;
    @BindView(R.id.swipe_refresh)
    TwinklingRefreshLayout swipe_refresh;

    private int flag,index=0;
    private List<OrderBean.DataBean> data = new ArrayList<>();
    private BetteyRecyclerViewAdapter betteyRecyclerViewAdapter;
    @Override
    public int intiLayout() {
        return R.layout.activity_bettey_and_winning;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        final Intent intent = getIntent();
        flag = intent.getIntExtra("flag",0);
        if(flag==1){

            title_text.setText("已投注");
        }else if(flag==2){

            title_text.setText("已中奖");

        }else if(flag==3){
            title_text.setText("全部订单");

        }else if(flag==4){

            title_text.setText("已晒单");
        }
        image_back_left.setOnClickListener(this);
        RecyclerView_BetteyAndwinning.setLayoutManager(new LinearLayoutManager(this));
        betteyRecyclerViewAdapter = new BetteyRecyclerViewAdapter(BetteyAndWinningActivity.this,data);
        RecyclerView_BetteyAndwinning.setAdapter(betteyRecyclerViewAdapter);
        swipe_refresh.setHeaderView(new SinaRefreshView(BetteyAndWinningActivity.this));
        swipe_refresh.setBottomView(new LoadingView(BetteyAndWinningActivity.this));
        swipe_refresh.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        index=0;
                        data.clear();
                        initnet(0);

                        swipe_refresh.finishRefreshing();

                        Toast.makeText(BetteyAndWinningActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();


                    }
                },1000);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        index++;
                        initnet(1);

                        swipe_refresh.finishLoadmore();

                        Toast.makeText(BetteyAndWinningActivity.this, "加载完成", Toast.LENGTH_SHORT).show();


                    }
                },1000);

            }
        });

        initnet(0);

        betteyRecyclerViewAdapter.setOnRecyclerViewListener(new BetteyRecyclerViewAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position,int type,int order_id) {

                Intent intent1 = new Intent(BetteyAndWinningActivity.this,DocumentdetailsActivity.class);
                //这里传0代表的是订单
                intent1.putExtra("flag",0);

                intent1.putExtra("order_id",order_id);
                intent1.putExtra("plan_id",0);

                startActivity(intent1);



            }
        });

    }

    private void initnet(final int i) {
        Log.e("YZSYZS", Content.ToKen+"");

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("pagination",index+"");
        hashMap.put("order_type","");
        if(flag==4){
            hashMap.put("bask_status",1+"");

        }else {

        }
        if(flag==3){

        }else {
            if(flag==4){
                hashMap.put("order_status","");

            }else {
                hashMap.put("order_status",flag+"");

            }

        }

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/order/getOrderList", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){
                                ToastUtil.showToast1(BetteyAndWinningActivity.this,msg+"");
                                OrderBean orderBean = JSON.parseObject(result,OrderBean.class);
                                if(i==0){
                                    if(orderBean.getData().size()==0){

                                        ToastUtil.showToast1(BetteyAndWinningActivity.this,"暂时没有订单");
                                        RecyclerView_BetteyAndwinning.setVisibility(View.GONE);
                                        swipe_refresh.setVisibility(View.GONE);
                                        Text_loading.setVisibility(View.VISIBLE);
                                    }else {

                                        data.addAll(orderBean.getData());
                                        betteyRecyclerViewAdapter.notifyDataSetChanged();


                                    }
                                }else {
                                    data.addAll(orderBean.getData());
                                    betteyRecyclerViewAdapter.notifyDataSetChanged();
                                }


                            }else {
                                ToastUtil.showToast1(BetteyAndWinningActivity.this,msg+"");

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
            case R.id.image_back_left:
                finish();
                break;

        }
    }
}
