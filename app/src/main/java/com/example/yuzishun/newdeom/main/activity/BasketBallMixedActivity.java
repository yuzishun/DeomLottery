package com.example.yuzishun.newdeom.main.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.main.adapter.BettingListAdapter;
import com.example.yuzishun.newdeom.main.adapter.GridView_Adapter;
import com.example.yuzishun.newdeom.main.adapter.baskball.BaskballAdapter;
import com.example.yuzishun.newdeom.main.adapter.baskball.BasketballSureActivity;

import com.example.yuzishun.newdeom.main.adapter.baskball.BettingSeparateBaskAdapter;
import com.example.yuzishun.newdeom.main.adapter.baskball.Expand1Item_bask;
import com.example.yuzishun.newdeom.main.adapter.baskball.Expand1Mixed_bask;
import com.example.yuzishun.newdeom.main.adapter.baskball.ExpandItem_bask;
import com.example.yuzishun.newdeom.main.adapter.baskball.ExpandMixed_bask;
import com.example.yuzishun.newdeom.main.betting.BettingFootballListAdapter;
import com.example.yuzishun.newdeom.main.betting.BettingSeparateAdapter;
import com.example.yuzishun.newdeom.main.betting.BettingfootActivity;
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
    private View layout_title;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.layout_mixed)
    LinearLayout layout_mixed;
    @BindView(R.id.layout_single)
    LinearLayout layout_single;
    @BindView(R.id.play_messag)
    ImageView play_messag;
    @BindView(R.id.layout_pop)
    LinearLayout layout_pop;
    @BindView(R.id.Lottery_RecyCLerView_single)
    RecyclerView Lottery_RecyCLerView_single;
    @BindView(R.id.Lottery_RecyCLerView_mixed)
    RecyclerView Lottery_RecyCLerView_mixed;
    //混合投注需要的东西
    private BaskballAdapter adapter;
    private ArrayList<MultiItemEntity> list_mixed;
    private ArrayList<MultiItemEntity> list_one_mixed = new ArrayList<>();
    private ArrayList<MultiItemEntity> list_two_mixed = new ArrayList<>();
    private ArrayList<MultiItemEntity> list_three_mixed = new ArrayList<>();
    private ArrayList<MultiItemEntity> list_four_mixed = new ArrayList<>();
    private int count_mixed = 0, mixed = 0, index = 0;
    @BindView(R.id.layout_swipe_mixed)
    SwipeRefreshLayout layout_swipe_mixed;
    @BindView(R.id.layout_bottom_mixed)
    LinearLayout layout_bottom_mixed;
    @BindView(R.id.Text_clear_mixed)
    TextView Text_clear_mixed;
    @BindView(R.id.Scene_TextView_mixed)
    TextView Scene_TextView_mixed;
    @BindView(R.id.button_sure_mixed)
    TextView button_sure_mixed;
    @BindView(R.id.Text_loading_mixed)
    TextView Text_loading_mixed;
    @BindView(R.id.layout_empt_mixed)
    LinearLayout layout_empt_mixed;






    private BasketBallBean basketBallBean;
    private int count;
    private ArrayList<MultiItemEntity> multiItemEntities;




    private String[] String_pop_one = new String[]{"胜负", "让分胜负", "胜分差", "大小分", "混合过关"};
    private String[] String_pop_two = new String[]{"胜负", "让分胜负", "胜分差", "大小分"};
    private int popwindows = 0;

    private List<String> list_pop = new ArrayList<>();


    @Override
    public int intiLayout() {
        return R.layout.activity_basket_ball_mixed;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        title_text.setText("混合投注");
        layout_pop.setEnabled(false);

        layout_single.setVisibility(View.GONE);
        layout_mixed.setVisibility(View.VISIBLE);
        layout_title = findViewById(R.id.layout_title);
        image_back.setOnClickListener(this);
        button_sure_mixed.setOnClickListener(this);
        Text_clear_mixed.setOnClickListener(this);
        play_messag.setOnClickListener(this);
        layout_pop.setOnClickListener(this);
        multiItemEntities = generateData();
        adapter =  new BaskballAdapter(multiItemEntities,5);
        //下拉刷新的圆圈是否显示
        layout_swipe_mixed.setRefreshing(false);

        //设置下拉时圆圈的颜色（可以由多种颜色拼成）
        layout_swipe_mixed.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);

        //设置下拉时圆圈的背景颜色（这里设置成白色）
        layout_swipe_mixed.setProgressBackgroundColorSchemeResource(android.R.color.white);
        layout_swipe_mixed.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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

                        layout_swipe_mixed.setRefreshing(false);
                        Toast.makeText(BasketBallMixedActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();

                    }
                },1000);


            }
        });


        //单关和混合一起加载然后替换集合
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...


                list_mixed = generateData();
                list_one_mixed = generateData_mixed(1);
                list_two_mixed = generateData_mixed(2);
                list_three_mixed = generateData_mixed(3);
                list_four_mixed = generateData_mixed(4);
//
//                list = request(1);
//                list_two = request(2);
//                list_three = request(3);
//                list_four = request(4);
//                list_fire = request(5);


                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 3000);

            }
        }).start();





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
                Lottery_RecyCLerView_mixed.setAdapter(adapter);

                Lottery_RecyCLerView_mixed.setLayoutManager(new LinearLayoutManager(BasketBallMixedActivity.this));


                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 1000);

            }
        }).start();

    }


    public void initmixedrecy(int mixed,ArrayList<MultiItemEntity> list) {


            if (list.size() == 0) {
                Lottery_RecyCLerView_mixed.setVisibility(View.GONE);
                layout_bottom_mixed.setVisibility(View.GONE);
                layout_swipe_mixed.setVisibility(View.GONE);
                layout_empt_mixed.setVisibility(View.VISIBLE);
            } else {
                layout_empt_mixed.setVisibility(View.GONE);
                Text_loading_mixed.setVisibility(View.GONE);
                Lottery_RecyCLerView_mixed.setVisibility(View.VISIBLE);
                layout_bottom_mixed.setVisibility(View.VISIBLE);
                layout_swipe_mixed.setVisibility(View.VISIBLE);
                adapter =  new BaskballAdapter(list_mixed,mixed);


                Lottery_RecyCLerView_mixed.setAdapter(adapter);
                Lottery_RecyCLerView_mixed.setNestedScrollingEnabled(false);
                adapter.expandAll();
            }




    }





        @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.layout_pop:
                popwin(layout_title);

                break;
            case R.id.Text_clear_mixed:
                adapter.onResh();

                adapter.notifyDataSetChanged();
                ToastUtil.showToast1(this,"清空完成");

                break;

            case R.id.play_messag:
                Intent intent1 = new Intent(this,Play_MessageActivity.class);
                intent1.putExtra("flag",1);
                startActivity(intent1);
                break;
            case R.id.button_sure_mixed:

                if(adapter.getnumber()<2){
                    ToastUtil.showToast1(this,"至少选择二场比赛");

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

    private void popwin(View view) {
        View contentView = LayoutInflater.from(view.getContext()).inflate(R.layout.bettingfoot_pop_layout,null);
        PopupWindow popup = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        //popwindow开始的时候设置屏幕变暗
        darkenBackground(0.5f);
        popup.showAsDropDown(view);

        //popwindow消失的时候设置屏幕变亮

        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                darkenBackground(1f);

            }
        });


        TextView choose_one = contentView.findViewById(R.id.choose_one);
        TextView choose_two = contentView.findViewById(R.id.choose_two);
        if (popwindows == 0) {

            choose_one.setTextColor(getResources().getColor(R.color.login_red));
            choose_two.setTextColor(getResources().getColor(R.color.font_black));
            list_pop.clear();

//            layout_mixed.setVisibility(View.VISIBLE);
//
//            layout_single.setVisibility(View.GONE);
            for (int i = 0; i < String_pop_one.length; i++) {
                list_pop.add(String_pop_one[i]);

            }
        } else {
            choose_one.setTextColor(getResources().getColor(R.color.font_black));
            choose_two.setTextColor(getResources().getColor(R.color.login_red));
            list_pop.clear();
//            layout_mixed.setVisibility(View.GONE);
//
//            layout_single.setVisibility(View.VISIBLE);
            for (int i = 0; i < String_pop_two.length; i++) {
                list_pop.add(String_pop_two[i]);

            }

        }

        GridView GridView_betting_Money = contentView.findViewById(R.id.GridView_betting_Money);
//        GridView_Adapter gridView_adapter = new GridView_Adapter(BasketBallMixedActivity.this, list_pop);
//        GridView_betting_Money.setAdapter(gridView_adapter);
        GridView_betting_Money.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                gridView_adapter.choiceState(i);
            }
        });
        choose_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();

                popwindows = 0;
                choose_one.setTextColor(getResources().getColor(R.color.login_red));
                choose_two.setTextColor(getResources().getColor(R.color.font_black));
                list_pop.clear();

                layout_mixed.setVisibility(View.VISIBLE);

                layout_single.setVisibility(View.GONE);
                for (int i = 0; i < String_pop_one.length; i++) {
                    list_pop.add(String_pop_one[i]);

                }
//
//                single_mixed = 3;
                initmixedrecy(5,list_mixed);
                title_text.setText(String_pop_one[4]);
//                gridView_adapter.notifyDataSetChanged();
            }
        });
        choose_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();


                popwindows = 1;

                choose_one.setTextColor(getResources().getColor(R.color.font_black));
                choose_two.setTextColor(getResources().getColor(R.color.login_red));
                list_pop.clear();
                layout_mixed.setVisibility(View.GONE);
                title_text.setText(String_pop_two[0]);

                layout_single.setVisibility(View.VISIBLE);
                for (int i = 0; i < String_pop_two.length; i++) {
                    list_pop.add(String_pop_two[i]);

                }
//                single = 1;
//                initrecycler(1);
                title_text.setText(String_pop_two[0]);
//                gridView_adapter.notifyDataSetChanged();

            }
        });
        GridView_betting_Money.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (list_pop.size() == 5) {

                    switch (position) {
                        case 0:
                            index = 1;
//                            single_mixed = 1;
                            initmixedrecy(1,list_one_mixed);
                            title_text.setText(String_pop_one[0]);
                            change();
                            break;
                        case 1:
                            index = 2;

//                            single_mixed = 2;
                            initmixedrecy(2,list_two_mixed);
                            title_text.setText(String_pop_one[1]);
                            change();

                            break;
                        case 2:
                            index = 0;

//                            single_mixed = 3;
                            initmixedrecy(3,list_three_mixed);
                            title_text.setText(String_pop_one[2]);
                            change();

//                        adapter.onResh();
                            break;
                        case 3:
                            index = 3;

//                            single_mixed = 4;
                            initmixedrecy(4,list_four_mixed);
                            title_text.setText(String_pop_one[3]);
                            change();

//                        adapter.onResh();
                            break;
                        case 4:
                            index = 4;

//                            single_mixed = 5;
                            initmixedrecy(5,list_mixed);
                            title_text.setText(String_pop_one[4]);
                            change();

                            break;

                    }


                    popup.dismiss();


                }
//                else if (list_pop.size() == 5) {
//
//                    switch (position) {
//                        case 0:
//                            single = 1;
////                        adapter.onResh();
//                            initrecycler(1);
//                            title_text.setText(String_pop_two[0]);
//                            change();
//
//                            break;
//                        case 1:
//                            single = 2;
//                            initrecycler(2);
//
////                        adapter.onResh();
//                            title_text.setText(String_pop_two[1]);
//                            change();
//
//                            break;
//                        case 2:
//                            single = 3;
//                            initrecycler(3);
//                            title_text.setText(String_pop_two[2]);
//                            change();
//
////                        adapter.onResh();
//                            break;
//                        case 3:
//                            single = 4;
//                            initrecycler(4);
//                            title_text.setText(String_pop_two[3]);
//                            change();
//
////                        adapter.onResh();
//                            break;
//                        case 4:
//                            single = 5;
//                            initrecycler(5);
//                            title_text.setText(String_pop_two[4]);
//                            change();
//
////                        adapter.onResh();
//                            break;
//
//                    }
//
//
//                    popup.dismiss();
//
//                }


            }
        });

    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(BasketMessage msg) {
        Log.e("YZS", msg.getMessage());
        count = Integer.parseInt(msg.getMessage());
        if(count ==0){
            Scene_TextView_mixed.setText("请选择比赛");

        }else {
            Scene_TextView_mixed.setText("已经选择"+msg.getMessage()+"比赛");

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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

        handler.sendEmptyMessage(1);


    }
    public void change() {

        if (adapter != null) {
            adapter.onResh();
            adapter.notifyDataSetChanged();
            EventBus.getDefault().post(new BasketMessage(BaskballAdapter.getnumber() + ""));


        }



    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if(msg.what==2){
                initmixedrecy(5,list_mixed);
                layout_pop.setEnabled(true);

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
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_no(),"展开更多选项","展开更多选项");

                                        item.addSubItem(expand1Item_bask);


                                    }
                                    res.add(item);


                                    handler.sendEmptyMessage(2);

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



    private ArrayList<MultiItemEntity> generateData_mixed(int mixed) {
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

                                    ExpandMixed_bask item = new ExpandMixed_bask(basketBallBean.getData().getGame_info().get(i).getGame_week()+""+basketBallBean.getData().getGame_info().get(i).getGame_group_time()+"共有"+basketBallBean.getData().getGame_info().get(i).getGame_info().size()+"场比赛可投");
                                    for (int j = 0; j < basketBallBean.getData().getGame_info().get(i).getGame_info().size(); j++) {
                                        ChooseBaskBean chooseBaskBean = new ChooseBaskBean();
                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.HomeScoreOddsBean> home_score_odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getHome_score_odds();
                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.LetScoreOddsBean> home_let_odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getLet_score_odds();
                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.TotalOddsBean> score_odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getTotal_odds();

                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.ScoreGuestOddsBean> total_odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getScore_guest_odds();
                                        List<BasketBallBean.DataBean.GameInfoBeanX.GameInfoBean.ScoreHomeOddsBean> half_odds = basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getScore_home_odds();
                                        List<ItemPoint> list_mixed = new ArrayList<>();
                                        List<ItemPoint> list_mixed_bottom = new ArrayList<>();

                                        switch (mixed) {
                                            case 1:
                                                for (int k = 0; k < home_score_odds.size(); k++) {

                                                    ItemPoint itemPoint = new ItemPoint();
                                                    itemPoint.setIsselect(false);
                                                    itemPoint.setId(home_score_odds.get(k).getOdds_code());
                                                    itemPoint.setGame_odds_id(home_score_odds.get(k).getGame_odds_id());
                                                    itemPoint.setOdds(home_score_odds.get(k).getOdds());
                                                    list_mixed.add(itemPoint);


                                                }
                                                break;
                                            case 2:
                                                for (int k = 0; k < home_let_odds.size(); k++) {

                                                    ItemPoint itemPoint = new ItemPoint();
                                                    itemPoint.setIsselect(false);
                                                    itemPoint.setId(home_let_odds.get(k).getOdds_code());
                                                    itemPoint.setGame_odds_id(home_let_odds.get(k).getGame_odds_id());
                                                    itemPoint.setOdds(home_let_odds.get(k).getOdds());
                                                    list_mixed.add(itemPoint);


                                                }
                                                break;
                                            case 3:
                                                for (int k = 0; k < total_odds.size(); k++) {

                                                    ItemPoint itemPoint = new ItemPoint();
                                                    itemPoint.setIsselect(false);
                                                    itemPoint.setId(total_odds.get(k).getOdds_code());
                                                    itemPoint.setGame_odds_id(total_odds.get(k).getGame_odds_id());
                                                    itemPoint.setOdds(total_odds.get(k).getOdds());
                                                    list_mixed.add(itemPoint);


                                                }
                                                for (int k = 0; k < half_odds.size(); k++) {

                                                    ItemPoint itemPoint = new ItemPoint();
                                                    itemPoint.setIsselect(false);
                                                    itemPoint.setId(half_odds.get(k).getOdds_code());
                                                    itemPoint.setGame_odds_id(half_odds.get(k).getGame_odds_id());
                                                    itemPoint.setOdds(half_odds.get(k).getOdds());
                                                    list_mixed_bottom.add(itemPoint);


                                                }
                                                break;
                                            case 4:

                                                for (int k = 0; k < score_odds.size(); k++) {

                                                ItemPoint itemPoint = new ItemPoint();
                                                itemPoint.setIsselect(false);
                                                itemPoint.setId(score_odds.get(k).getOdds_code());
                                                itemPoint.setGame_odds_id(score_odds.get(k).getGame_odds_id());
                                                itemPoint.setOdds(score_odds.get(k).getOdds());
                                                list_mixed.add(itemPoint);


                                                }
                                                break;

                                        }

                                        chooseBaskBean.setOnelist(list_mixed);
                                        chooseBaskBean.setTwolist(list_mixed_bottom);
                                        chooseBaskBean.setDesc("展开更多选项");

                                        chooseBaskBean.setGame_id(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_id());
                                        chooseBaskBean.setHome_team(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name());

                                        chooseBaskBean.setGuest_team(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name());
                                        chooseBaskBean.setName(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no()+"        "+basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name()
                                                +"        "+"vs"+"        "+basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name());

                                        list_choose.add(chooseBaskBean);


                                        Expand1Mixed_bask expand1Item_bask = new Expand1Mixed_bask(basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_name(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_total_score(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_let_score(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_stop_time(),list_mixed,list_mixed_bottom,list_choose,
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




//    private ArrayList<MultiItemEntity> getBasklist_single(int single){
//
//
//
//
//
//
//
//
//    }





}
