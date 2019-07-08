package com.example.yuzishun.newdeom.main.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.example.yuzishun.newdeom.documentary.activity.DocumentaryFragment;
import com.example.yuzishun.newdeom.main.adapter.BettingListAdapter;
import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.main.adapter.ExpandItem;

import com.example.yuzishun.newdeom.main.adapter.GridView_Betting_Adapter;
import com.example.yuzishun.newdeom.main.mixed.BettingMixedFragment;

import com.example.yuzishun.newdeom.main.single.SingleMessage;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.FootballBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.my.activity.MyFragment;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.score.activity.ScoreFragment;
import com.example.yuzishun.newdeom.utils.AdapterMessage;
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
    private String[] list1=new String[]{"混合投注"};
    private String[] list2=new String[]{"胜负平","比分"};
    private List<String> list_pop_one = new ArrayList<>();
    private List<String> list_pop_two = new ArrayList<>();

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
    @BindView(R.id.layout_pop)
    LinearLayout layout_pop;
    @BindView(R.id.Lottery_RecyCLerView)
    RecyclerView Lottery_RecyCLerView;
    private BettingListAdapter adapter;
    private ArrayList<MultiItemEntity> list;
    private FootballBean footballBean;
    @BindView(R.id.Text_clear)
    TextView Text_clear;
    private int flag;
    @BindView(R.id.title_text)
    TextView title_text;
    private Fragment[] mFragments;
    private int mIndex=0;
    @BindView(R.id.Single_Lacontent)
    FrameLayout Single_Lacontent;
    @BindView(R.id.Betting_Lacontent)
    FrameLayout Betting_Lacontent;
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

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",0);
//        initFragment();
        //开启事务
        Fragment mTab_01 = new BettingMixedFragment();

        //添加到数组
        mFragments = new Fragment[]{mTab_01};

        FragmentTransaction ft =
                getSupportFragmentManager().beginTransaction();



        //添加首页
        ft.add(R.id.Betting_Lacontent,mTab_01).commit();
//        FragmentTransaction ft2 =
//                getSupportFragmentManager().beginTransaction();
//
//
//        //添加首页
//        ft2.add(R.id.Single_Lacontent,mTab_02).commit();
//        if(flag==1){
//
//            title_text.setText("混合投注");
////            setIndexSelected(0);
//            Single_Lacontent.setVisibility(View.GONE);
//            Betting_Lacontent.setVisibility(View.VISIBLE);
//
//        }else if(flag==2){
//            title_text.setText("胜负平");
////            setIndexSelected(1);
//
//            Single_Lacontent.setVisibility(View.VISIBLE);
//            Betting_Lacontent.setVisibility(View.GONE);
//        }
//        layout_pop.setOnClickListener(this);


//        EventBus.getDefault().register(this);
//        initlist();
//        list = generateData();
//        adapter = new BettingListAdapter(list);
//        Lottery_RecyCLerView.setAdapter(adapter);
//        Lottery_RecyCLerView.setNestedScrollingEnabled(false);
//        Lottery_RecyCLerView.setLayoutManager(new LinearLayoutManager(BettingActivity.this));
//        //下拉刷新的圆圈是否显示
//        layout_swipe.setRefreshing(false);
//
//        //设置下拉时圆圈的颜色（可以由多种颜色拼成）
//        layout_swipe.setColorSchemeResources(android.R.color.holo_blue_light,
//                android.R.color.holo_red_light,
//                android.R.color.holo_orange_light);
//
//        //设置下拉时圆圈的背景颜色（这里设置成白色）
//        layout_swipe.setProgressBackgroundColorSchemeResource(android.R.color.white);
//
//        layout_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable(){
//
//                    @Override
//                    public void run() {
//                        generateData();
//                        adapter.onResh();
//                        adapter.notifyDataSetChanged();
//                        EventBus.getDefault().post(new MainMessage(BettingListAdapter.getnumber()+""));
//
//                        layout_swipe.setRefreshing(false);
//                        Toast.makeText(BettingActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
//
//                    }
//                },1000);
//
//
//            }
//        });


    }

//    //加载fragment页面
//    private void initFragment() {
//        Fragment mTab_01 = new BettingMixedFragment();
//        Fragment mTab_02 = new BettingSingleFragment();
//
//        //添加到数组
//        mFragments = new Fragment[]{mTab_01,mTab_02};
//
//        //开启事务
//
//        FragmentTransaction ft =
//                getSupportFragmentManager().beginTransaction();
//
//
//        //添加首页
//        ft.add(R.id.Betting_Lacontent,mTab_01).commit();
//
//        //默认设置为第0个
//
//        setIndexSelected(0);
//
//
//    }
//
//    private void setIndexSelected(int index) {
//
//        if(mIndex==index){
//            return;
//        }
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction ft              = fragmentManager.beginTransaction();
//        //隐藏
//        ft.hide(mFragments[mIndex]);
//        //判断是否添加
//        if(!mFragments[index].isAdded()){
//            ft.add(R.id.Betting_Lacontent,mFragments[index]).show(mFragments[index]);
//        }else {
//            ft.show(mFragments[index]);
//        }
//
//
//        ft.commit();
//        //再次赋值
//        mIndex=index;
//    }

//    private void initlist() {
//
//        for (int i = 0; i <string_one.length ; i++) {
//            list_one.add(string_one[i]+"");
//
//        }
//
//        for (int i = 0; i <string_two.length ; i++) {
//            list_two.add(string_two[i]+"");
//
//        }
//        for (int i = 0; i <string_three.length ; i++) {
//            list_three.add(string_three[i]+"");
//
//        }
//        for (int i = 0; i <string_four.length ; i++) {
//            list_four.add(string_four[i]+"");
//
//        }
//
//    }


//    /**
//     * 主线程中执行
//     *
//     * @param msg
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMainEventBus(MainMessage msg) {
//        Log.e(TAG, msg.getMessage());
//        count = Integer.parseInt(msg.getMessage());
//        if(msg.getMessage().equals("0")||msg.getMessage().equals("1")){
//            Scene_TextView.setText("至少选择两场比赛");
//        }else {
//            Scene_TextView.setText("已经选择"+msg.getMessage()+"比赛");
//
//        }
//    }
//    /**
//     * 主线程中执行
//     *
//     * @param msg
//     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMainEventBus(AdapterMessage msg) {
//        Log.e("YZSYZSYZS", msg.getPostion()+"");
//        Content.Text_postion = msg.getPostion();
//        adapter.notifyItemChanged(msg.getPostion());
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(adapter!=null){
//
//        if(Content.order_flag==0){
//            adapter.notifyDataSetChanged();
//
//        }else {
//            adapter.onResh();
//
//            adapter.notifyDataSetChanged();
//
//        }
//        }
//
//        EventBus.getDefault().post(new MainMessage(BettingListAdapter.getnumber()+""));
//
//    }

    @Override
    public void initData() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //异步处理加载数据
//                //...
//
//
//
//                //完成后，通知主线程更新UI
//                handler.sendEmptyMessageDelayed(1, 2000);
//
//            }
//        }).start();

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
                    }else if(count>15){
                        ToastUtil.showToast1(this,"至多选择15场");


                    }else  {

                    Intent intent = new Intent(this,MixedSureActivity.class);
                    Content.list_chooe = adapter.getList();
                    startActivity(intent);
                    }


                break;
            case R.id.layout_pop:


                popwindow(layout_pop);

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

    private void popwindow(View view) {
        list_pop_one.clear();
        for (int i = 0; i <list1.length ; i++) {
            list_pop_one.add(list1[i]);

        }
        list_pop_two.clear();
        for (int i = 0; i <list2.length ; i++) {
            list_pop_two.add(list2[i]);

        }
        final Dialog dialog = new Dialog(this,R.style.dialog);
        dialog.setContentView(R.layout.pop_betting);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setBackgroundDrawable(new BitmapDrawable());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();


        TextView choose_one = dialog.findViewById(R.id.choose_one);
        TextView choose_two = dialog.findViewById(R.id.choose_two);
        GridView GridView_betting_Money = dialog.findViewById(R.id.GridView_betting_Money);
        GridView GridView_betting_Money_two = dialog.findViewById(R.id.GridView_betting_Money_two);
//        GridView_Betting_Adapter gridView_betting_adapter = new GridView_Betting_Adapter(BettingActivity.this,list_pop_one);
//        GridView_betting_Money.setAdapter(gridView_betting_adapter);
//        GridView_Betting_Adapter gridView_betting_adapter2 = new GridView_Betting_Adapter(BettingActivity.this,list_pop_two);
//        GridView_betting_Money_two.setAdapter(gridView_betting_adapter2);
        if(Content.flag_betting_popwindow==0){

            GridView_betting_Money.setVisibility(View.VISIBLE);
            GridView_betting_Money_two.setVisibility(View.GONE);
            choose_one.setTextColor(this.getResources().getColor(R.color.login_red));
            choose_two.setTextColor(this.getResources().getColor(R.color.gray_Overall_hint));


        }else {

            GridView_betting_Money.setVisibility(View.GONE);
            GridView_betting_Money_two.setVisibility(View.VISIBLE);
            choose_one.setTextColor(this.getResources().getColor(R.color.gray_Overall_hint));
            choose_two.setTextColor(this.getResources().getColor(R.color.login_red));
        }
        choose_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridView_betting_Money.setVisibility(View.VISIBLE);
                GridView_betting_Money_two.setVisibility(View.GONE);
                choose_one.setTextColor(BettingActivity.this.getResources().getColor(R.color.login_red));
                choose_two.setTextColor(BettingActivity.this.getResources().getColor(R.color.gray_Overall_hint));

            }
        });
        choose_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GridView_betting_Money.setVisibility(View.GONE);
                GridView_betting_Money_two.setVisibility(View.VISIBLE);
                choose_one.setTextColor(BettingActivity.this.getResources().getColor(R.color.gray_Overall_hint));
                choose_two.setTextColor(BettingActivity.this.getResources().getColor(R.color.login_red));
            }
        });

        GridView_betting_Money.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                gridView_betting_adapter.choiceState(position);
                Single_Lacontent.setVisibility(View.GONE);
                Betting_Lacontent.setVisibility(View.VISIBLE);
                dialog.dismiss();

            }
        });
        GridView_betting_Money_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                gridView_betting_adapter2.choiceState(position);
                int single=0;
                if(position==0){
                    single=1;
                }else {
                    single=3;

                }

                Single_Lacontent.setVisibility(View.VISIBLE);
                Betting_Lacontent.setVisibility(View.GONE);

//                setIndexSelected(1);

                dialog.dismiss();

            }
        });



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//        handler.sendEmptyMessage(1);
//        handler.removeCallbacksAndMessages(null);
//
//    }
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(android.os.Message msg) {
//            Text_loading.setVisibility(View.GONE);
//            Lottery_RecyCLerView.setVisibility(View.GONE);
//            layout_bottom.setVisibility(View.GONE);
//            layout_swipe.setVisibility(View.GONE);
//            adapter.expandAll();
//
//        }
//    };


//    private ArrayList<MultiItemEntity> generateData() {
//        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
//        final ArrayList<MultiItemEntity> res = new ArrayList<>();
//        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getFootballList", new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//                Log.e("YZS",e.getMessage()+"");
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String result = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(result);
//                            int code = jsonObject.getInt("code");
//                            String msg = jsonObject.getString("msg");
//                            if (code == 10000) {
////                                ToastUtil.showToast(BettingActivity.this, msg + "");
//                                footballBean = JSON.parseObject(result, FootballBean.class);
//                                List<ChooseMixedBean> list_choose = new ArrayList<>();
//
//                                for (int i = 0; i < footballBean.getData().size(); i++) {
//
//                                    ExpandItem item = new ExpandItem(footballBean.getData().get(i).getGame_week()+""+footballBean.getData().get(i).getGame_group_time()+"共有"+footballBean.getData().get(i).getGame_info().size()+"场比赛可投");
//                                    for (int j = 0; j <footballBean.getData().get(i).getGame_info().size() ; j++) {
//                                        ChooseMixedBean chooseMixedBean = new ChooseMixedBean();
//                                        List<FootballBean.DataBean.GameInfoBean.HomeLetOddsBean> home_let_odds = footballBean.getData().get(i).getGame_info().get(j).getHome_let_odds();
//                                        List<FootballBean.DataBean.GameInfoBean.ScoreOddsBean> score_odds = footballBean.getData().get(i).getGame_info().get(j).getScore_odds();
//                                        List<FootballBean.DataBean.GameInfoBean.TotalOddsBean> total_odds = footballBean.getData().get(i).getGame_info().get(j).getTotal_odds();
//                                        List<FootballBean.DataBean.GameInfoBean.HalfOddsBean> half_odds = footballBean.getData().get(i).getGame_info().get(j).getHalf_odds();
//
//                                        List<ItemPoint> listone = new ArrayList<>();
//                                        List<ItemPoint> listtwo = new ArrayList<>();
//                                        List<ItemPoint> listthree = new ArrayList<>();
//                                        List<ItemPoint> listfour = new ArrayList<>();
//
//                                        for (int k = 0; k <home_let_odds.size() ; k++) {
//
//                                            ItemPoint itemPoint = new ItemPoint();
//                                            itemPoint.setIsselect(false);
//                                            itemPoint.setId(list_one.get(k));
//
//                                            itemPoint.setGame_odds_id(home_let_odds.get(k).getGame_odds_id());
//                                            itemPoint.setOdds(home_let_odds.get(k).getOdds());
//                                            listone.add(itemPoint);
//
//                                        }
//                                        for (int k = 0; k <score_odds.size() ; k++) {
//                                            ItemPoint itemPoint = new ItemPoint();
//                                            itemPoint.setIsselect(false);
//
//                                            itemPoint.setId(list_two.get(k));
//                                            itemPoint.setGame_odds_id(score_odds.get(k).getGame_odds_id());
//                                            itemPoint.setOdds(score_odds.get(k).getOdds());
//                                            listtwo.add(itemPoint);
//
//                                        }
//                                        for (int k = 0; k <total_odds.size() ; k++) {
//                                            ItemPoint itemPoint = new ItemPoint();
//                                            itemPoint.setIsselect(false);
//
//                                            itemPoint.setId(list_three.get(k));
//                                            itemPoint.setGame_odds_id(total_odds.get(k).getGame_odds_id());
//                                            itemPoint.setOdds(total_odds.get(k).getOdds());
//                                            listthree.add(itemPoint);
//
//                                        }
//                                        for (int k = 0; k <half_odds.size() ; k++) {
//                                            ItemPoint itemPoint = new ItemPoint();
//                                            itemPoint.setIsselect(false);
//                                            itemPoint.setId(list_four.get(k));
//                                            itemPoint.setGame_odds_id(half_odds.get(k).getGame_odds_id());
////                                            if(half_odds.get(k).getOdds().equals("")){
////                                                itemPoint.setOdds("暂未开售");
////
////                                            }
//                                            itemPoint.setOdds(half_odds.get(k).getOdds());
//
//                                            listfour.add(itemPoint);
//
//                                        }
//                                        chooseMixedBean.setOnelist(listone);
//                                        chooseMixedBean.setTwolist(listtwo);
//                                        chooseMixedBean.setThreelist(listthree);
//
//                                        chooseMixedBean.setFourlist(listfour);
//                                        chooseMixedBean.setGame_id(footballBean.getData().get(i).getGame_info().get(j).getGame_id());
//                                        chooseMixedBean.setHome_team(footballBean.getData().get(i).getGame_info().get(j).getGame_home_team_name());
//
//                                        chooseMixedBean.setGuest_team(footballBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name());
//                                        chooseMixedBean.setName(footballBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()+"        "+footballBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
//                                                +"        "+"vs"+"        "+footballBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name());
//                                        chooseMixedBean.setHome_score(footballBean.getData().get(i).getGame_info().get(j).getGame_home_score());
//                                        chooseMixedBean.setGuest_score(footballBean.getData().get(i).getGame_info().get(j).getGame_let_score());
//                                        list_choose.add(chooseMixedBean);
//
//                                        Expand1Item item1 = new Expand1Item(footballBean.getData().get(i).getGame_info().get(j).getGame_name(),
//                                                footballBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
//                                        ,footballBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name(),footballBean.getData().get(i).getGame_info().get(j).getGame_home_score(),
//                                                footballBean.getData().get(i).getGame_info().get(j).getGame_let_score(),footballBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()
//                                        ,footballBean.getData().get(i).getGame_info().get(j).getGame_begin_time()
//                                        ,listone,listtwo,listthree,listfour,list_choose,"展开更多选项");
//
//
//                                        item.addSubItem(item1);
//                                    }
//
//                                    res.add(item);
//                                }
//
//
//                            } else {
//                                ToastUtil.showToast1(BettingActivity.this, msg + "");
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
//
//            }
//        });
//
//        return res;
//    }





    }
