package com.example.yuzishun.deomlottery.my.activity;

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
import com.example.yuzishun.deomlottery.model.DetailedBean;
import com.example.yuzishun.deomlottery.my.adapter.Detailed_RecyclerView_Adapter;
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

public class MyDetailedActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.Detailed_RecyclerView)
    RecyclerView Detailed_RecyclerView;
    @BindView(R.id.Text_loading)
    TextView Text_loading;
    @BindView(R.id.swipe_refresh)
    TwinklingRefreshLayout swipe_refresh;
    private int index=0;
    private Detailed_RecyclerView_Adapter detailed_recyclerView_adapter;
    private List<DetailedBean.DataBean> data = new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.activity_my_detailed;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("我的明细");
        image_back.setOnClickListener(this);
        Detailed_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailed_recyclerView_adapter = new Detailed_RecyclerView_Adapter(MyDetailedActivity.this,data);
        Detailed_RecyclerView.setAdapter(detailed_recyclerView_adapter);

        swipe_refresh.setHeaderView(new SinaRefreshView(MyDetailedActivity.this));
        swipe_refresh.setBottomView(new LoadingView(MyDetailedActivity.this));
        swipe_refresh.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        index=0;
                        data.clear();
                        initnet();

                        swipe_refresh.finishRefreshing();

                        Toast.makeText(MyDetailedActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();


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
                        initnet();

                        swipe_refresh.finishLoadmore();

                        Toast.makeText(MyDetailedActivity.this, "加载完成", Toast.LENGTH_SHORT).show();


                    }
                },1000);

            }
        });


        initnet();


    }

    private void initnet() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("pagination",index+"");

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/user/geyUserAccountDetailsList", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("YZS",e.getMessage()+"");
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
                                ToastUtil.showToast1(MyDetailedActivity.this,msg+"");

                                DetailedBean detailedBean = JSON.parseObject(result,DetailedBean.class);
                                data.addAll(detailedBean.getData());
                                if(data.size()==0){
                                    ToastUtil.showToast1(MyDetailedActivity.this,"暂时没有收入或者支出");
                                    Detailed_RecyclerView.setVisibility(View.GONE);
                                    swipe_refresh.setVisibility(View.GONE);
                                    Text_loading.setVisibility(View.VISIBLE);
                                }else {

                                    detailed_recyclerView_adapter.notifyDataSetChanged();

                                }



                            }else {
                                ToastUtil.showToast1(MyDetailedActivity.this,msg+"");

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
