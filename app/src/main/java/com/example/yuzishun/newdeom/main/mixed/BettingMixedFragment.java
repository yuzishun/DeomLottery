package com.example.yuzishun.newdeom.main.mixed;


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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.LazyFragment;
import com.example.yuzishun.newdeom.main.activity.BettingActivity;
import com.example.yuzishun.newdeom.main.activity.MixedSureActivity;
import com.example.yuzishun.newdeom.main.activity.Play_MessageActivity;
import com.example.yuzishun.newdeom.main.adapter.BettingListAdapter;
import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.main.adapter.ExpandItem;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.FootballBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.AdapterMessage;
import com.example.yuzishun.newdeom.utils.MainMessage;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BettingMixedFragment extends LazyFragment implements View.OnClickListener {
    private String[] string_four= {"胜胜","胜平","胜负","平胜","平平","平负","负胜","负平","负负"};
    private String[] string_two= {"1:0","2:0","2:1","3:0","3:1","3:2","4:0","4:1","4:2","5:0","5:1","5:2","胜其他","0:0","1:1","2:2"
            ,"3:3","平其他","0:1","0:2","1:2","0:3","1:3","2:3","0:4","1:4","2:4","0:5","1:5","2:5","负其他"};
    private String[] string_three= {"0球","1球","2球","3球","4球","5球","6球","7+球"};
    private String[] string_one= {"主胜","平","主负","让胜","让平","让负"};

    private List<String> list_one = new ArrayList<>();
    private List<String> list_two = new ArrayList<>();
    private List<String> list_three = new ArrayList<>();
    private List<String> list_four = new ArrayList<>();

    private int count=0;

    private TextView Text_loading;
    private SwipeRefreshLayout layout_swipe;
    private LinearLayout layout_bottom;
    private Button button_sure;
    private TextView Scene_TextView;
    private RecyclerView Lottery_RecyCLerView;
    private BettingListAdapter adapter;
    private ArrayList<MultiItemEntity> list;
    private FootballBean footballBean;
    private TextView Text_clear;




    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_betting_mixed);
        initView();
        ininData();
    }



    private void initView() {

        Text_loading = (TextView) findViewById(R.id.Text_loading);
        layout_swipe = (SwipeRefreshLayout) findViewById(R.id.layout_swipe);
        layout_bottom = (LinearLayout) findViewById(R.id.layout_bottom);
        button_sure = (Button) findViewById(R.id.button_sure);
        Scene_TextView = (TextView) findViewById(R.id.Scene_TextView);
        Lottery_RecyCLerView = (RecyclerView) findViewById(R.id.Lottery_RecyCLerView);
        Text_clear = (TextView) findViewById(R.id.Text_clear);
        button_sure.setOnClickListener(this);
        Scene_TextView.setOnClickListener(this);
        Text_clear.setOnClickListener(this);


    }



    private void ininData() {

        EventBus.getDefault().register(this);
        initlist();

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

                list = generateData();
                adapter = new BettingListAdapter(list);
                Lottery_RecyCLerView.setAdapter(adapter);
                Lottery_RecyCLerView.setNestedScrollingEnabled(false);
                Lottery_RecyCLerView.setLayoutManager(new LinearLayoutManager(getContext()));

                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 2000);

            }
        }).start();



    }

    @Override
    public void onClick(View v) {
            switch (v.getId()) {

                case R.id.button_sure:
                    if(count<2){
                        ToastUtil.showToast1(getActivity(),"至少选择两场");
                    }else if(count>15){
                        ToastUtil.showToast1(getActivity(),"至多选择15场");


                    }else  {

                        Intent intent = new Intent(getContext(),MixedSureActivity.class);
                        Content.list_chooe = adapter.getList();
                        startActivity(intent);
                    }


                    break;
                case R.id.Text_clear:
                    adapter.onResh();

                    adapter.notifyDataSetChanged();
                    EventBus.getDefault().post(new MainMessage(BettingListAdapter.getnumber()+""));

                    ToastUtil.showToast1(getActivity(),"清空完成");
                    break;

            }
    }


    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
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
        Log.e("YZS", msg.getMessage());
        count = Integer.parseInt(msg.getMessage());
        if(msg.getMessage().equals("0")||msg.getMessage().equals("1")){
            Scene_TextView.setText("至少选择两场比赛");
        }else {
            Scene_TextView.setText("已经选择"+msg.getMessage()+"比赛");

        }
    }
    /**
     * 主线程中执行
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(AdapterMessage msg) {
        Log.e("YZSYZSYZS", msg.getPostion()+"");
        Content.Text_postion = msg.getPostion();
        adapter.notifyItemChanged(msg.getPostion());

    }



    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
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

    private ArrayList<MultiItemEntity> generateData() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        final ArrayList<MultiItemEntity> res = new ArrayList<>();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getFootballList", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("YZS",e.getMessage()+"");

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
//                                            if(half_odds.get(k).getOdds().equals("")){
//                                                itemPoint.setOdds("暂未开售");
//
//                                            }
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
                                        chooseMixedBean.setName(footballBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()+"        "+footballBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
                                                +"        "+"vs"+"        "+footballBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name());
                                        chooseMixedBean.setHome_score(footballBean.getData().get(i).getGame_info().get(j).getGame_home_score());
                                        chooseMixedBean.setGuest_score(footballBean.getData().get(i).getGame_info().get(j).getGame_let_score());
                                        list_choose.add(chooseMixedBean);

                                        Expand1Item item1 = new Expand1Item(footballBean.getData().get(i).getGame_info().get(j).getGame_name(),
                                                footballBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
                                                ,footballBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name(),footballBean.getData().get(i).getGame_info().get(j).getGame_home_score(),
                                                footballBean.getData().get(i).getGame_info().get(j).getGame_let_score(),footballBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()
                                                ,footballBean.getData().get(i).getGame_info().get(j).getGame_begin_time()
                                                ,listone,listtwo,listthree,listfour,list_choose,"展开更多选项");


                                        item.addSubItem(item1);
                                    }

                                    res.add(item);
                                }


                            } else {
                                ToastUtil.showToast(getActivity(), msg + "");

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
}
