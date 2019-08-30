package com.example.yuzishun.newdeom.main.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.example.yuzishun.newdeom.main.adapter.baskball.BaskballAdapter;
import com.example.yuzishun.newdeom.main.adapter.baskball.BasketballSureActivity;
import com.example.yuzishun.newdeom.main.adapter.baskball.Expand1Item_bask;
import com.example.yuzishun.newdeom.main.adapter.baskball.ExpandItem_bask;
import com.example.yuzishun.newdeom.main.single.BettingSingleAdapter;
import com.example.yuzishun.newdeom.main.single.SingleMessage;
import com.example.yuzishun.newdeom.model.BasketBallBean;
import com.example.yuzishun.newdeom.model.ChooseBaskBean;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.MainMessage;
import com.example.yuzishun.newdeom.utils.ToastUtil;
import com.example.yuzishun.newdeom.utils.eventbus.BasketAdapterMessage;
import com.example.yuzishun.newdeom.utils.eventbus.BasketMessage;
import com.example.yuzishun.newdeom.utils.eventbus.MIxedMessage;
import com.example.yuzishun.newdeom.utils.eventbus.MixedPostionMessage;

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

public class BasketBallMixedActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.Scene_TextView)
    TextView Scene_TextView;
    @BindView(R.id.baskball_RecyCLerView)
    RecyclerView baskball_RecyCLerView;
    @BindView(R.id.Text_clear)
    TextView Text_clear;
    @BindView(R.id.layout_empt)
    LinearLayout layout_empt;
    @BindView(R.id.play_messag)
    ImageView play_messag;
    private BaskballAdapter adapter;
    private String[] string_one= {"主胜","主负","主胜","主负","大分","小分"};
    private String[] string_two= {"1-5","6-10","11-15","16-20","21-25","26+"};
    private List<String> list_one = new ArrayList<>();
    private List<String> list_two = new ArrayList<>();
    private List<String> list_three = new ArrayList<>();
    private BasketBallBean basketBallBean;
    private int mixed;
    private ArrayList<MultiItemEntity> multiItemEntities;
    @Override
    public int intiLayout() {
        return R.layout.activity_basket_ball_mixed;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        image_back.setOnClickListener(this);
        button_sure.setOnClickListener(this);
        Text_clear.setOnClickListener(this);
        play_messag.setOnClickListener(this);
        initlist();
        multiItemEntities = generateData();
        adapter =  new BaskballAdapter(multiItemEntities);
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
//                        multiItemEntities.clear();
                        multiItemEntities.addAll(generateData());
                        generateData();
                        adapter.onResh();
                        adapter.notifyDataSetChanged();
                        EventBus.getDefault().post(new BasketMessage(adapter.getnumber()+""));

                        layout_swipe.setRefreshing(false);
                        Toast.makeText(BasketBallMixedActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();

                    }
                },1000);


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Content.order_flag_bask==0){
            adapter.notifyDataSetChanged();

        }else {
            adapter.onResh();

            adapter.notifyDataSetChanged();

        }
        EventBus.getDefault().post(new BasketMessage(BaskballAdapter.getnumber()+""));

    }

    @Override
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...
                baskball_RecyCLerView.setAdapter(adapter);

                baskball_RecyCLerView.setLayoutManager(new LinearLayoutManager(BasketBallMixedActivity.this));


                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 1000);

            }
        }).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.Text_clear:
                adapter.onResh();

                adapter.notifyDataSetChanged();
                ToastUtil.showToast1(this,"清空完成");

                break;

            case R.id.play_messag:
                Intent intent1 = new Intent(this,Play_MessageActivity.class);
                intent1.putExtra("flag",1);
                startActivity(intent1);
                break;
            case R.id.button_sure:

                if(adapter.getnumber()==0){
                    ToastUtil.showToast1(this,"至少选择一场");

                }else {
                    if(adapter.getnumber()>15) {

                        ToastUtil.showToast1(this,"最多选15场");

                    }else {

                        Intent intent = new Intent(this,BasketballSureActivity.class);
                    Content.list_chooe_bask = adapter.getList();
                    startActivity(intent);
                    }

                }


                break;



        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(BasketMessage msg) {
        Log.e("YZS", msg.getMessage());
        mixed = Integer.parseInt(msg.getMessage());
        if(mixed ==0){
            Scene_TextView.setText("请选择比赛");

        }else {
            Scene_TextView.setText("已经选择"+msg.getMessage()+"比赛");

        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(BasketAdapterMessage msg) {
        Log.e("YZSYZSYZS", msg.getPostion()+"");
        Content.Text_postion_mixed_basket = msg.getPostion();
        adapter.notifyItemChanged(msg.getPostion());
        if(msg.getPostion()==0){


            adapter.notifyDataSetChanged();
        }else {

        }

    }
    private void initlist() {

        for (int i = 0; i <string_one.length ; i++) {
            list_one.add(string_one[i]+"");

        }

        for (int i = 0; i <string_two.length ; i++) {
            list_two.add(string_two[i]+"");

        }

        for (int i = 0; i <string_two.length ; i++) {
            list_three.add(string_two[i]+"");

        }

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        handler.sendEmptyMessage(1);


    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if(multiItemEntities.size()==0){
                baskball_RecyCLerView.setVisibility(View.GONE);
                layout_bottom.setVisibility(View.GONE);
                layout_swipe.setVisibility(View.GONE);
                layout_empt.setVisibility(View.VISIBLE);
            }else {
                layout_empt.setVisibility(View.GONE);
                Text_loading.setVisibility(View.GONE);
                baskball_RecyCLerView.setVisibility(View.VISIBLE);
                layout_bottom.setVisibility(View.VISIBLE);
                layout_swipe.setVisibility(View.VISIBLE);
                adapter.expandAll();
            }



        }
    };

    private ArrayList<MultiItemEntity> generateData() {
        final ArrayList<MultiItemEntity> res = new ArrayList<>();

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();

        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getBasketballList", new Callback() {
//                    okhttpUtlis.GetAsynMap("http://192.168.1.9/app/ball/getBasketballList", new Callback() {

                @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {

                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                basketBallBean = JSON.parseObject(result,BasketBallBean.class);
                                List<ChooseBaskBean> list_choose = new ArrayList<>();

                                for (int i = 0; i <basketBallBean.getData().getGame_info().size() ; i++) {

                                    ExpandItem_bask item = new ExpandItem_bask(basketBallBean.getData().getGame_info().get(i).getGame_week()+""+basketBallBean.getData().getGame_info().get(i).getGame_group_time()+"共有"+basketBallBean.getData().getGame_info().get(i).getGame_info().size()+"场比赛可投");
                                    for (int j = 0; j < basketBallBean.getData().getGame_info().get(i).getGame_info().size(); j++) {
                                        ChooseBaskBean chooseBaskBean = new ChooseBaskBean();
                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.HomeScoreOddsBean> home_let_odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getHome_score_odds();
                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.LetScoreOddsBean> let_score_odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getLet_score_odds();
                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.TotalOddsBean> total__odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getTotal_odds();

                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.ScoreGuestOddsBean> score_guest_odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getScore_guest_odds();
                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.ScoreHomeOddsBean> score_home_odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getScore_home_odds();
                                        List<ItemPoint> listone = new ArrayList<>();
                                        List<ItemPoint> listtwo = new ArrayList<>();
                                        List<ItemPoint> listthree = new ArrayList<>();
                                        List<ItemPoint> listfour = new ArrayList<>();
                                        List<ItemPoint> listfire = new ArrayList<>();
                                        for (int k = 0; k < home_let_odds.size(); k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);
                                            itemPoint.setId(home_let_odds.get(k).getOdds_code());

                                            itemPoint.setGame_odds_id(home_let_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(home_let_odds.get(k).getOdds());
                                            listone.add(itemPoint);

                                        }
                                        for (int k = 0; k <score_guest_odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(score_guest_odds.get(k).getOdds_code());
                                            itemPoint.setGame_odds_id(score_guest_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(score_guest_odds.get(k).getOdds());
                                            listtwo.add(itemPoint);

                                        }
                                        for (int k = 0; k <score_home_odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(score_home_odds.get(k).getOdds_code());
                                            itemPoint.setGame_odds_id(score_home_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(score_home_odds.get(k).getOdds());
                                            listthree.add(itemPoint);

                                        }
                                        for (int k = 0; k <let_score_odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(let_score_odds.get(k).getOdds_code());
                                            itemPoint.setGame_odds_id(let_score_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(let_score_odds.get(k).getOdds());
                                            listfour.add(itemPoint);

                                        }
                                        for (int k = 0; k <total__odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(total__odds.get(k).getOdds_code());
                                            itemPoint.setGame_odds_id(total__odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(total__odds.get(k).getOdds());
                                            listfire.add(itemPoint);

                                        }
                                        chooseBaskBean.setOnelist(listone);
                                        chooseBaskBean.setTwolist(listtwo);
                                        chooseBaskBean.setThreelist(listthree);
                                        chooseBaskBean.setFourlist(listfour);
                                        chooseBaskBean.setDesc("展开更多选项");

                                        chooseBaskBean.setFirelist(listfire);
                                        chooseBaskBean.setGame_id(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_id());
                                        chooseBaskBean.setHome_team(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name());

                                        chooseBaskBean.setGuest_team(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name());
                                        chooseBaskBean.setName(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no()+"        "+basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name()
                                                +"        "+"vs"+"        "+basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name());

                                        list_choose.add(chooseBaskBean);

                                        
                                        Expand1Item_bask expand1Item_bask = new Expand1Item_bask(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_name(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_total_score(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_let_score(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_stop_time(),listone,listtwo,listthree,listfour,listfire,list_choose,
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_no(),"展开更多选项");

                                        item.addSubItem(expand1Item_bask);


                                    }
                                    res.add(item);



                                }

                            }else {
                                ToastUtil.showToast1(BasketBallMixedActivity.this,msg+"");
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
