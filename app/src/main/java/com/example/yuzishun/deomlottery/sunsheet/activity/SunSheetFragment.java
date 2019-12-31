package com.example.yuzishun.deomlottery.sunsheet.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.deomlottery.MainActivity;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.LazyFragment;
import com.example.yuzishun.deomlottery.login.activity.LoginActivity;
import com.example.yuzishun.deomlottery.model.SunSheetBean;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.sunsheet.adapter.SunSheetMainListAdapter;
import com.example.yuzishun.deomlottery.utils.SpUtil;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SunSheetFragment extends LazyFragment implements View.OnClickListener {
    private TextView title_text;
    private LinearLayout image_back;
    private TextView Text_loading,red_list,black_list;
    private LinearLayout layout_title,layout_sunsheet,layout_red,layout_black;
    private RecyclerView SunSheet_recyclerView;
    private SunSheetMainListAdapter adapter;
    private TwinklingRefreshLayout Sunsheet_Refresh;
    private ImageView red_img,black_img;
    private int index=0,type=1;
    private TextView sunsheet_empt;
    private List<SunSheetBean.DataBean> data = new ArrayList<>();
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_sun_sheet);
        initview();
        initdata();
    }
    private void initview() {
        Sunsheet_Refresh = (TwinklingRefreshLayout) findViewById(R.id.Sunsheet_Refresh);
        Sunsheet_Refresh.setHeaderView(new SinaRefreshView(getActivity()));
        Sunsheet_Refresh.setBottomView(new LoadingView(getContext()));
        sunsheet_empt = (TextView) findViewById(R.id.sunsheet_empt);
        title_text = (TextView) findViewById(R.id.title_text);
        image_back = (LinearLayout) findViewById(R.id.image_back);
        Text_loading = (TextView) findViewById(R.id.Text_loading);
        layout_title = (LinearLayout) findViewById(R.id.layout_title);
        layout_black = (LinearLayout) findViewById(R.id.layout_black);
        layout_red = (LinearLayout) findViewById(R.id.layout_red);
        red_img = (ImageView) findViewById(R.id.red_img);
        black_img = (ImageView) findViewById(R.id.black_img);
        layout_sunsheet = (LinearLayout) findViewById(R.id.layout_sunsheet);
        red_list = (TextView) findViewById(R.id.red_list);
        black_list = (TextView) findViewById(R.id.black_list);
        SunSheet_recyclerView = (RecyclerView) findViewById(R.id.SunSheet_recyclerView);
        layout_black.setOnClickListener(this);
        layout_red.setOnClickListener(this);
        SunSheet_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        title_text.setText("晒单大厅");
        image_back.setVisibility(View.GONE);
        adapter = new SunSheetMainListAdapter(getContext(),data);
        SunSheet_recyclerView.setAdapter(adapter);
        Sunsheet_Refresh.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);

                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        index=0;
                        data.clear();

                        request(type,0,1);
//
                        Sunsheet_Refresh.finishRefreshing();

                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();


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

                        request(type,1,0);
//
                        Sunsheet_Refresh.finishLoadmore();

                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();


                    }
                },1000);

            }
        });
        adapter.setOnRecyclerViewListener(new SunSheetMainListAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position, String bask_id,ImageView imageView) {
                setFabulous(bask_id,position,imageView);


            }
        });


    }

    private void setFabulous(String bask_id,int postion,ImageView imageView) {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",bask_id);
        hashMap.put("like_type",0+"");
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/Comment/likeTags", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject  jsonObject= new JSONObject(result);
                            int code = jsonObject.getInt("code");

                            String msg = jsonObject.getString("msg");

                            if(code==10000){

                                ToastUtil.showToast(getContext(),msg);
                                request(type,0,1);


                            }else {
                                ToastUtil.showToast(getContext(),msg);

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
    protected void onResumeLazy() {
        super.onResumeLazy();

        if(adapter!=null){
//            if(Content.refre_flag==0){
//
//            }else {
//
//                index=0;
//            request(type,0,1);
//                Content.refre_flag=0;
//            }

        }

    }

    private void initdata() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...

                //tablayout加载布局
                request(1,0,1);


                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }).start();

    }

    private void request(int type,int flag,int add) {
        if(flag == 0){

            data.clear();
        }else {

        }
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("red_black",type+"");
        hashMap.put("pagination",index+"");
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/Bask/queryBask", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject  = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                SunSheetBean sunSheetBean  = JSON.parseObject(result,SunSheetBean.class);

                                if(add==1){
                                    data.addAll(sunSheetBean.getData());

                                    if(sunSheetBean.getData().size()==0){

                                        sunsheet_empt.setVisibility(View.VISIBLE);
                                        SunSheet_recyclerView.setVisibility(View.GONE);
                                    }else {
                                        sunsheet_empt.setVisibility(View.GONE);
                                        SunSheet_recyclerView.setVisibility(View.VISIBLE);
                                        adapter.notifyDataSetChanged();

                                    }
                                }else {
                                    data.addAll(sunSheetBean.getData());

                                    adapter.notifyDataSetChanged();
                                }





                            }else if(code==10004){


                                MainActivity.intentsat.finish();
                                startActivity(new Intent(getContext(), LoginActivity.class));
                                SpUtil spUtil = new SpUtil(getContext(),"token");
                                spUtil.putString("token","");

                                ToastUtil.showToast(getContext(),msg+"");

                            }else {
                                ToastUtil.showToast(getContext(),msg+"");


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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_red:
                black_list.setTextColor(getResources().getColor(R.color.font_black));
                layout_black.setBackgroundColor(getResources().getColor(R.color.new_gray_empt));
                red_list.setTextColor(getResources().getColor(R.color.login_red));
                layout_red.setBackgroundColor(getResources().getColor(R.color.white));
                black_img.setImageDrawable(getResources().getDrawable(R.mipmap.black_img_false));
                red_img.setImageDrawable(getResources().getDrawable(R.mipmap.red_img));

                type=1;
                index=0;
                request(type,0,1);
                break;
            case R.id.layout_black:
                red_list.setTextColor(getResources().getColor(R.color.font_black));
                layout_red.setBackgroundColor(getResources().getColor(R.color.new_gray_empt));
                black_list.setTextColor(getResources().getColor(R.color.login_red));
                layout_black.setBackgroundColor(getResources().getColor(R.color.white));
                black_img.setImageDrawable(getResources().getDrawable(R.mipmap.black_img));
                red_img.setImageDrawable(getResources().getDrawable(R.mipmap.red_img_false));
                type=0;
                index=0;

                request(type,0,1);

                break;
        }

    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        handler.removeMessages(1);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            Text_loading.setVisibility(View.GONE);

            Sunsheet_Refresh.setVisibility(View.VISIBLE);
            layout_title.setVisibility(View.VISIBLE);
            layout_sunsheet.setVisibility(View.VISIBLE);
        }
    };


}
