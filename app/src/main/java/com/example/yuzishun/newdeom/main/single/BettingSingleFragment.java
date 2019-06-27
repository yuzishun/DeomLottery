package com.example.yuzishun.newdeom.main.single;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.LazyFragment;
import com.example.yuzishun.newdeom.main.activity.MixedSureActivity;
import com.example.yuzishun.newdeom.main.adapter.BettingListAdapter;
import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.main.adapter.ExpandItem;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.FootballBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.SingleBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.MainMessage;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BettingSingleFragment extends LazyFragment implements View.OnClickListener {

    private TextView Text_loading;
    private SwipeRefreshLayout layout_swipe;
    private LinearLayout layout_bottom;
    private Button button_sure;
    private TextView Scene_TextView;
    private RecyclerView Lottery_RecyCLerView;
    private BettingSingleAdapter adapter;
    private ArrayList<MultiItemEntity> list;
    private TextView Text_clear;
    private LinearLayout layout_empt;
    private SingleBean singleBean;
    private int count=0;
    private int single=1;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_betting_single);
        initView();
        initData();


    }

    private void initData() {
        EventBus.getDefault().register(this);

        list = request();
        adapter = new BettingSingleAdapter(list,1);
        Lottery_RecyCLerView.setAdapter(adapter);
        Lottery_RecyCLerView.setNestedScrollingEnabled(false);
        Lottery_RecyCLerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //下拉刷新的圆圈是否显示
        layout_swipe.setRefreshing(false);

        //设置下拉时圆圈的颜色（可以由多种颜色拼成）
        layout_swipe.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);

        //设置下拉时圆圈的背景颜色（这里设置成白色）
        layout_swipe.setProgressBackgroundColorSchemeResource(android.R.color.white);

        layout_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        list.addAll(request());

                        adapter.onResh();
                        adapter.notifyDataSetChanged();
                        EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber()+""));

                        layout_swipe.setRefreshing(false);
                        Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();

                    }
                },1000);


            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...



                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 1000);

            }
        }).start();
    }

    /**
     * 主线程中执行
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(SingleMessage msg) {
        Log.e("YZS", msg.getMessage());
        count = Integer.parseInt(msg.getMessage());
        if(count==0){
            Scene_TextView.setText("请选择比赛");

        }else {
            Scene_TextView.setText("已经选择"+msg.getMessage()+"比赛");

        }

    }




    private ArrayList<MultiItemEntity> request() {

        final ArrayList<MultiItemEntity> res = new ArrayList<>();

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getSingleFootballList?single="+single, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                try {


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                singleBean = JSON.parseObject(result,SingleBean.class);
                                List<ChooseMixedBean> list_choose = new ArrayList<>();

                                for (int i = 0; i <singleBean.getData().size() ; i++) {

                                    Item_Single item_single = new Item_Single(singleBean.getData().get(i).getGame_week()+""+singleBean.getData().get(i).getGame_group_time()+"共有"+singleBean.getData().get(i).getGame_info().size()+"场比赛可投");

                                    for (int j = 0; j <singleBean.getData().get(i).getGame_info().size() ; j++) {

                                        ChooseMixedBean chooseMixedBean = new ChooseMixedBean();

                                        List<SingleBean.DataBean.GameInfoBean.SingleOddsBean> single_odds = singleBean.getData().get(i).getGame_info().get(j).getSingle_odds();

                                        List<ItemPoint> list_single = new ArrayList<>();

                                        for (int k = 0; k <single_odds.size() ; k++) {

                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);
                                            itemPoint.setId(single_odds.get(k).getOdds_code());
                                            itemPoint.setGame_odds_id(single_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(single_odds.get(k).getOdds());
                                            list_single.add(itemPoint);


                                        }
                                        chooseMixedBean.setOnelist(list_single);

                                        chooseMixedBean.setGame_id(singleBean.getData().get(i).getGame_info().get(j).getGame_id());
                                        chooseMixedBean.setHome_team(singleBean.getData().get(i).getGame_info().get(j).getGame_home_team_name());

                                        chooseMixedBean.setGuest_team(singleBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name());
                                        chooseMixedBean.setName(singleBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()+"        "+singleBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
                                                +"        "+"vs"+"        "+singleBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name());
                                        chooseMixedBean.setHome_score(singleBean.getData().get(i).getGame_info().get(j).getGame_home_score());
                                        chooseMixedBean.setGuest_score(singleBean.getData().get(i).getGame_info().get(j).getGame_let_score());
                                        list_choose.add(chooseMixedBean);


                                        Item1_Single item1 = new Item1_Single(singleBean.getData().get(i).getGame_info().get(j).getGame_name(),
                                                singleBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
                                                ,singleBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name(),singleBean.getData().get(i).getGame_info().get(j).getGame_home_score(),
                                                singleBean.getData().get(i).getGame_info().get(j).getGame_let_score(),singleBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()
                                                ,singleBean.getData().get(i).getGame_info().get(j).getGame_stop_time()
                                                ,list_single,list_choose,"点击展开更多");


                                        item_single.addSubItem(item1);




                                    }
                                    res.add(item_single);



                                }





                            }else {
                                ToastUtil.showToast(getActivity(),msg);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                }catch (Exception e){

                }

            }
        });

        return res;

    }

    private void initView() {

        Text_loading = (TextView) findViewById(R.id.Text_loading);
        layout_swipe = (SwipeRefreshLayout) findViewById(R.id.layout_swipe);
        layout_bottom = (LinearLayout) findViewById(R.id.layout_bottom);
        button_sure = (Button) findViewById(R.id.button_sure);
        Scene_TextView = (TextView) findViewById(R.id.Scene_TextView);
        Lottery_RecyCLerView = (RecyclerView) findViewById(R.id.Lottery_RecyCLerView);
        Text_clear = (TextView) findViewById(R.id.Text_clear);
        layout_empt = (LinearLayout) findViewById(R.id.layout_empt);
        button_sure.setOnClickListener(this);
        Scene_TextView.setOnClickListener(this);
        Text_clear.setOnClickListener(this);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
        handler.sendEmptyMessage(1);
        handler.removeCallbacksAndMessages(null);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if(singleBean.getData().size()==0){
                Lottery_RecyCLerView.setVisibility(View.GONE);
                layout_bottom.setVisibility(View.GONE);
                layout_swipe.setVisibility(View.GONE);
                layout_empt.setVisibility(View.VISIBLE);
            }else {
                layout_empt.setVisibility(View.GONE);
                Text_loading.setVisibility(View.GONE);
                Lottery_RecyCLerView.setVisibility(View.VISIBLE);
                layout_bottom.setVisibility(View.VISIBLE);
                layout_swipe.setVisibility(View.VISIBLE);
                adapter.expandAll();
            }



        }
    };



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_sure:
                if(count<1){
                    ToastUtil.showToast1(getActivity(),"至少选择一场");
                }else if(count>15){
                    ToastUtil.showToast1(getActivity(),"至多选择15场");


                }else  {

                    Intent intent = new Intent(getContext(),SingleSureActivity.class);

                    Content.list_chooe_single = adapter.getList();


                    startActivity(intent);
                }


                break;
            case R.id.Text_clear:
                adapter.onResh();

                adapter.notifyDataSetChanged();
                EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber()+""));

                ToastUtil.showToast1(getActivity(),"清空完成");
                break;

        }
    }

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        if(adapter!=null){

            if(Content.order_flag_single==0){
                adapter.notifyDataSetChanged();

            }else {
                adapter.onResh();

                adapter.notifyDataSetChanged();

            }
        }


        EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber()+""));
    }
}
