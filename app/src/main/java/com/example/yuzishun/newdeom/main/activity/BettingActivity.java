package com.example.yuzishun.newdeom.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.main.adapter.BettingListAdapter;
import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.main.adapter.ExpandItem;

import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.FootballBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.MainMessage;
import com.example.yuzishun.newdeom.utils.ToastUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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

public class BettingActivity extends BaseActivity implements View.OnClickListener {

    private String[] string_four= {"胜胜","胜平","胜负","平胜","平平","平负","负胜","负平","负负"};
    private String[] string_two= {"1:0","2:0","2:1","3:0","3:1","3:2","4:0","4:1","4:2","5:0","5:1","5:2","胜其他","0:0","1:1","2:2"
            ,"3:3","平其他","0:1","0:2","1:2","0:3","1:3","2:3","0:4","1:4","2:4","0:5","1:5","2:5","负其他"};
    private String[] string_three= {"0球","1球","2球","3球","4球","5球","6球","7+球"};
    private String[] string_one= {"主胜","平","主负","让胜","让平","让负"};

    private List<String> list_one = new ArrayList<>();
    private List<String> list_two = new ArrayList<>();
    private List<String> list_three = new ArrayList<>();
    private List<String> list_four = new ArrayList<>();
    //tttttt
    private int count=0;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.Text_loading)
    TextView Text_loading;
    @BindView(R.id.layout_swipe)
    SwipeRefreshLayout layout_swipe;
    @BindView(R.id.layout_bottom)
    LinearLayout layout_bottom;
    @BindView(R.id.button_sure)
    Button button_sure;
    @BindView(R.id.play_messag)
    ImageView play_messag;
    @BindView(R.id.Scene_TextView)
    TextView Scene_TextView;
    @BindView(R.id.Lottery_RecyCLerView)
    RecyclerView Lottery_RecyCLerView;
    private BettingListAdapter adapter;
    private ArrayList<MultiItemEntity> list;
    private FootballBean footballBean;
    @BindView(R.id.Text_clear)
    TextView Text_clear;
    @Override
    public int intiLayout() {
        return R.layout.activity_betting;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        play_messag.setOnClickListener(this);
        button_sure.setOnClickListener(this);
        Scene_TextView.setOnClickListener(this);
        Text_clear.setOnClickListener(this);
        EventBus.getDefault().register(this);
        initlist();
        list = generateData();
        adapter = new BettingListAdapter(list);
        Lottery_RecyCLerView.setAdapter(adapter);
        Lottery_RecyCLerView.setNestedScrollingEnabled(false);
        Lottery_RecyCLerView.setLayoutManager(new LinearLayoutManager(BettingActivity.this));
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
                        generateData();
                        adapter.onResh();
                        adapter.notifyDataSetChanged();
                        EventBus.getDefault().post(new MainMessage(BettingListAdapter.getnumber()+""));

                        layout_swipe.setRefreshing(false);
                        Toast.makeText(BettingActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();

                    }
                },1000);


            }
        });


    }

    private void initlist() {

        for (int i = 0; i <string_one.length ; i++) {
            list_one.add(string_one[i]+"");

        }

        for (int i = 0; i <string_two.length ; i++) {
            list_two.add(string_two[i]+"");

        }
        for (int i = 0; i <string_three.length ; i++) {
            list_three.add(string_three[i]+"");

        }
        for (int i = 0; i <string_four.length ; i++) {
            list_four.add(string_four[i]+"");

        }

    }


    /**
     * 主线程中执行
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(MainMessage msg) {
        Log.e(TAG, msg.getMessage());
        count = Integer.parseInt(msg.getMessage());
        if(msg.getMessage().equals("0")||msg.getMessage().equals("1")){
            Scene_TextView.setText("至少选择两场比赛");
        }else {
            Scene_TextView.setText("已经选择"+msg.getMessage()+"比赛");

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null){

        if(Content.order_flag==0){
            adapter.notifyDataSetChanged();

        }else {
            adapter.onResh();

            adapter.notifyDataSetChanged();

        }
        }

        EventBus.getDefault().post(new MainMessage(BettingListAdapter.getnumber()+""));

    }

    @Override
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...



                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 2000);

            }
        }).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.button_sure:
                    if(count<2){
                    ToastUtil.showToast1(this,"至少选择两场");
                    }else {

                    Intent intent = new Intent(this,MixedSureActivity.class);
                    Content.list_chooe = adapter.getList();
                    startActivity(intent);
                    }


                break;
            case R.id.Text_clear:
                adapter.onResh();

                adapter.notifyDataSetChanged();
                EventBus.getDefault().post(new MainMessage(BettingListAdapter.getnumber()+""));

                ToastUtil.showToast1(this,"清空完成");
                break;
            case R.id.play_messag:
                Intent intent = new Intent(this,Play_MessageActivity.class);
                intent.putExtra("flag",0);
                startActivity(intent);

                break;
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        handler.sendEmptyMessage(1);
        handler.removeCallbacksAndMessages(null);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            Text_loading.setVisibility(View.GONE);
            Lottery_RecyCLerView.setVisibility(View.VISIBLE);
            layout_bottom.setVisibility(View.VISIBLE);
            layout_swipe.setVisibility(View.VISIBLE);
            adapter.expandAll();

        }
    };


    private ArrayList<MultiItemEntity> generateData() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        final ArrayList<MultiItemEntity> res = new ArrayList<>();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "ball/getFootballList", new Callback() {
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
                            if (code == 10000) {
//                                ToastUtil.showToast(BettingActivity.this, msg + "");
                                footballBean = JSON.parseObject(result, FootballBean.class);
                                List<ChooseMixedBean> list_choose = new ArrayList<>();

                                for (int i = 0; i < footballBean.getData().size(); i++) {

                                    ExpandItem item = new ExpandItem(footballBean.getData().get(i).getGame_week()+""+footballBean.getData().get(i).getGame_group_time()+"共有"+footballBean.getData().get(i).getGame_info().size()+"场比赛可投");
                                    for (int j = 0; j <footballBean.getData().get(i).getGame_info().size() ; j++) {
                                        ChooseMixedBean chooseMixedBean = new ChooseMixedBean();
                                        List<FootballBean.DataBean.GameInfoBean.HomeLetOddsBean> home_let_odds = footballBean.getData().get(i).getGame_info().get(j).getHome_let_odds();
                                        List<FootballBean.DataBean.GameInfoBean.ScoreOddsBean> score_odds = footballBean.getData().get(i).getGame_info().get(j).getScore_odds();
                                        List<FootballBean.DataBean.GameInfoBean.TotalOddsBean> total_odds = footballBean.getData().get(i).getGame_info().get(j).getTotal_odds();
                                        List<FootballBean.DataBean.GameInfoBean.HalfOddsBean> half_odds = footballBean.getData().get(i).getGame_info().get(j).getHalf_odds();

                                        List<ItemPoint> listone = new ArrayList<>();
                                        List<ItemPoint> listtwo = new ArrayList<>();
                                        List<ItemPoint> listthree = new ArrayList<>();
                                        List<ItemPoint> listfour = new ArrayList<>();

                                        for (int k = 0; k <home_let_odds.size() ; k++) {

                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);
                                            itemPoint.setId(list_one.get(k));

                                            itemPoint.setGame_odds_id(home_let_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(home_let_odds.get(k).getOdds());
                                            listone.add(itemPoint);

                                        }
                                        for (int k = 0; k <score_odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(list_two.get(k));
                                            itemPoint.setGame_odds_id(score_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(score_odds.get(k).getOdds());
                                            listtwo.add(itemPoint);

                                        }
                                        for (int k = 0; k <total_odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(list_three.get(k));
                                            itemPoint.setGame_odds_id(total_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(total_odds.get(k).getOdds());
                                            listthree.add(itemPoint);

                                        }
                                        for (int k = 0; k <half_odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);
                                            itemPoint.setId(list_four.get(k));
                                            itemPoint.setGame_odds_id(half_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(half_odds.get(k).getOdds());
                                            listfour.add(itemPoint);

                                        }
                                        chooseMixedBean.setOnelist(listone);
                                        chooseMixedBean.setTwolist(listtwo);
                                        chooseMixedBean.setThreelist(listthree);

                                        chooseMixedBean.setFourlist(listfour);
                                        chooseMixedBean.setGame_id(footballBean.getData().get(i).getGame_info().get(j).getGame_id());
                                        chooseMixedBean.setHome_team(footballBean.getData().get(i).getGame_info().get(j).getGame_home_team_name());

                                        chooseMixedBean.setGuest_team(footballBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name());
                                        chooseMixedBean.setName(footballBean.getData().get(i).getGame_week()+footballBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()+"        "+footballBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
                                                +"        "+"vs"+"        "+footballBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name());
                                        chooseMixedBean.setHome_score(footballBean.getData().get(i).getGame_info().get(j).getGame_home_score());
                                        chooseMixedBean.setGuest_score(footballBean.getData().get(i).getGame_info().get(j).getGame_let_score());
                                        list_choose.add(chooseMixedBean);

                                        Expand1Item item1 = new Expand1Item(footballBean.getData().get(i).getGame_info().get(j).getGame_name(),
                                                footballBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
                                        ,footballBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name(),footballBean.getData().get(i).getGame_info().get(j).getGame_home_score(),
                                                footballBean.getData().get(i).getGame_info().get(j).getGame_let_score(),footballBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()
                                        ,footballBean.getData().get(i).getGame_info().get(j).getGame_begin_time(),footballBean.getData()
                                        ,listone,listtwo,listthree,listfour,list_choose);


                                        item.addSubItem(item1);
                                    }

                                    res.add(item);
                                }


                            } else {
                                ToastUtil.showToast1(BettingActivity.this, msg + "");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });

        return res;
    }





    }
