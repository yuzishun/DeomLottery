package com.example.yuzishun.deomlottery.main.betting;

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
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;
import com.example.yuzishun.deomlottery.base.Content;
import com.example.yuzishun.deomlottery.main.activity.MixedSureActivity;
import com.example.yuzishun.deomlottery.main.adapter.GridView_Adapter;
import com.example.yuzishun.deomlottery.main.single.BettingSingleAdapter;
import com.example.yuzishun.deomlottery.main.single.SingleMessage;
import com.example.yuzishun.deomlottery.main.single.SingleSureActivity;
import com.example.yuzishun.deomlottery.main.util.BettFootBaskMixedUtil;
import com.example.yuzishun.deomlottery.main.util.BettFootBaskSIngleUtil;
import com.example.yuzishun.deomlottery.model.ChooseBean;
import com.example.yuzishun.deomlottery.model.ChooseMixedBean;
import com.example.yuzishun.deomlottery.model.ChoosebettBean;
import com.example.yuzishun.deomlottery.model.ItemPoint;
import com.example.yuzishun.deomlottery.model.MixedFootballBean;
import com.example.yuzishun.deomlottery.model.SubMixBean;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.eventbus.AdapterMessage;
import com.example.yuzishun.deomlottery.utils.MainMessage;
import com.example.yuzishun.deomlottery.utils.ToastUtil;
import com.example.yuzishun.deomlottery.utils.eventbus.SinglePostionMessage;

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

public class BettingfootActivity extends BaseActivity implements View.OnClickListener {
    private View layout_title;

    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.layout_pop)
    LinearLayout layout_pop;
    @BindView(R.id.play_messag)
    ImageView play_messag;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.layout_mixed)
    LinearLayout layout_mixed;
    @BindView(R.id.layout_single)
    LinearLayout layout_single;
    @BindView(R.id.Lottery_RecyCLerView_single)
    RecyclerView Lottery_RecyCLerView_single;
    @BindView(R.id.Lottery_RecyCLerView_mixed)
    RecyclerView Lottery_RecyCLerView_mixed;
    private int  flag = 1;
    private int single_mixed = 3;
    private int popwindows = 0;
    //混合投注需要的东西
    private ArrayList<MultiItemEntity> list_mixed;
    private ArrayList<MultiItemEntity> list_one_mixed = new ArrayList<>();

    private ArrayList<MultiItemEntity> list_two_mixed = new ArrayList<>();
    private ArrayList<MultiItemEntity> list_four_mixed = new ArrayList<>();
    private ArrayList<MultiItemEntity> list_five_mixed = new ArrayList<>();
    private ArrayList<MultiItemEntity> list_six_mixed = new ArrayList<>();
    private int count_mixed = 0;
    private BettingFootballListAdapter adapter_mixed;
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


    //单关所需要的东西
    private BettingSingleAdapter adapter;
    private ArrayList<MultiItemEntity> list;
    private ArrayList<MultiItemEntity> list_two;
    private ArrayList<MultiItemEntity> list_three;
    private ArrayList<MultiItemEntity> list_four;
    private ArrayList<MultiItemEntity> list_fire;

    @BindView(R.id.layout_swipe_single)
    SwipeRefreshLayout layout_swipe_single;
    @BindView(R.id.layout_bottom)
    LinearLayout layout_bottom;
    @BindView(R.id.layout_empt_single)
    LinearLayout layout_empt_single;
    @BindView(R.id.Text_loading_single)
    TextView Text_loading_single;
    @BindView(R.id.Text_clear_single)
    TextView Text_clear_single;
    @BindView(R.id.Scene_TextView_single)
    TextView Scene_TextView_single;
    @BindView(R.id.button_sure_single)
    Button button_sure_single;
    private int count = 0;

    private String[] String_pop_one = new String[]{"胜平负", "让球胜平负", "混合投注", "比分", "总进球", "半全场"};
    private String[] String_pop_two = new String[]{"胜平负", "让球胜平负", "比分", "总进球", "半全场"};

    private List<ChoosebettBean> list_pop = new ArrayList<>();
    private List<ChoosebettBean> list_pop_two = new ArrayList<>();


    @Override
    public int intiLayout() {
        return R.layout.activity_bettingfoot;
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        layout_pop.setEnabled(false);
        layout_title = findViewById(R.id.layout_title);
        image_back.setOnClickListener(this);
        layout_pop.setOnClickListener(this);
        Text_clear_single.setOnClickListener(this);
        button_sure_single.setOnClickListener(this);
        Text_clear_mixed.setOnClickListener(this);
        button_sure_mixed.setOnClickListener(this);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        if (flag == 1) {
            title_text.setText("混合投注");
            layout_single.setVisibility(View.GONE);
            layout_mixed.setVisibility(View.VISIBLE);
            popwindows = 0;

        } else {
            title_text.setText("胜平负");

            layout_single.setVisibility(View.VISIBLE);
            layout_mixed.setVisibility(View.GONE);
            popwindows = 1;

        }

        initlist();

        //混合
        Lottery_RecyCLerView_mixed.setLayoutManager(new LinearLayoutManager(this));


        layout_swipe_mixed.setRefreshing(false);
        layout_swipe_mixed.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);
        layout_swipe_mixed.setProgressBackgroundColorSchemeResource(android.R.color.white);
        layout_swipe_mixed.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                       change();
                        layout_swipe_mixed.setRefreshing(false);
                        Toast.makeText(BettingfootActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();


                    }
                }, 1000);


            }
        });
        //单关
        Lottery_RecyCLerView_single.setLayoutManager(new LinearLayoutManager(this));

        //下拉刷新的圆圈是否显示
        layout_swipe_single.setRefreshing(false);

        //设置下拉时圆圈的颜色（可以由多种颜色拼成）
        layout_swipe_single.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light);

        //设置下拉时圆圈的背景颜色（这里设置成白色）
        layout_swipe_single.setProgressBackgroundColorSchemeResource(android.R.color.white);

        layout_swipe_single.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        adapter.onResh(1);
                        adapter.notifyDataSetChanged();
                        EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber() + ""));

                        layout_swipe_single.setRefreshing(false);
                        Toast.makeText(BettingfootActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();

                    }
                }, 1000);


            }
        });

        //单关和混合一起加载然后替换集合
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...


                list_mixed = generateData();
                BettFootBaskMixedUtil footMixedUtil = new BettFootBaskMixedUtil(BettingfootActivity.this);
                list_one_mixed = footMixedUtil.getlist_mixed(1);
                list_two_mixed = footMixedUtil.getlist_mixed(2);
                list_four_mixed = footMixedUtil.getlist_mixed(3);
                list_five_mixed = footMixedUtil.getlist_mixed(4);
                list_six_mixed = footMixedUtil.getlist_mixed(5);

                BettFootBaskSIngleUtil footSIngleUtil = new BettFootBaskSIngleUtil(BettingfootActivity.this);
                list = footSIngleUtil.request(1);
                list_two = footSIngleUtil.request(2);
                list_three = footSIngleUtil.request(3);
                list_four = footSIngleUtil.request(4);
                list_fire = footSIngleUtil.request(5);



                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 3000);

            }
        }).start();

    }


    public void initlist(){
        list_pop.clear();
        ChoosebettBean choosebettBean;
        for (int i = 0; i < String_pop_one.length; i++){
            choosebettBean = new ChoosebettBean();
            choosebettBean.setName(String_pop_one[i]);
            if(i==2){

                choosebettBean.setIsselect(true);


            }else {
                choosebettBean.setIsselect(false);
            }
            list_pop.add(choosebettBean);

        }


        list_pop_two.clear();
        ChoosebettBean choosebettBean_two;

        for (int i = 0; i < String_pop_two.length; i++) {
            choosebettBean_two = new ChoosebettBean();
            choosebettBean_two.setName(String_pop_two[i]);
            if(i==0){

                choosebettBean_two.setIsselect(true);


            }else {
                choosebettBean_two.setIsselect(false);
            }
            list_pop_two.add(choosebettBean_two);

        }


    }

    /**
     * 主线程中执行//单关
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(SingleMessage msg) {
        Log.e("YZS", msg.getMessage());
        count = Integer.parseInt(msg.getMessage());
        if(popwindows==0){
            if (msg.getMessage().equals("0") || msg.getMessage().equals("1")) {
                Scene_TextView_mixed.setText("请至少选择两场比赛");
            } else {
                Scene_TextView_mixed.setText("已经选择" + msg.getMessage() + "比赛");

            }
        }else {
            if (count == 0) {

                Scene_TextView_single.setText("请选择比赛");

            } else {
                Scene_TextView_single.setText("已经选择" + msg.getMessage() + "比赛");
            }
        }


    }

    /**
     * 主线程中执行//单关
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(SinglePostionMessage msg) {
        Log.e("YZSYZSYZS", msg.getPostion() + "");
        Content.Text_postion = msg.getPostion();
        adapter.notifyItemChanged(msg.getPostion());
        if (msg.getPostion() == 0) {


            adapter.notifyDataSetChanged();
        } else {

        }

    }

    /**
     * 主线程中执行//混合投注
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(MainMessage msg) {
        Log.e("YZS", msg.getMessage());
        count_mixed = Integer.parseInt(msg.getMessage());
        if (msg.getMessage().equals("0") || msg.getMessage().equals("1")) {
            Scene_TextView_mixed.setText("请至少选择两场比赛");
        } else {
            Scene_TextView_mixed.setText("已经选择" + msg.getMessage() + "比赛");

        }
    }

    /**
     * 主线程中执行//混合投注
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(AdapterMessage msg) {
        Log.e("YZSYZSYZS", msg.getPostion() + "");
        Content.Text_postion = msg.getPostion();
        adapter_mixed.notifyItemChanged(msg.getPostion());

    }

    public void bottom_text(boolean isture){
        if(isture){

            Scene_TextView_mixed.setText("请至少选择两场比赛");

        }else {
            Scene_TextView_mixed.setText("请至少选择一场比赛");

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {

            if (Content.order_flag_single == 0) {
                adapter.notifyDataSetChanged();


            } else {
                adapter.onResh(0);

                adapter.notifyDataSetChanged();

            }
            EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber() + ""));


        }
        if (adapter_mixed != null) {


            if (Content.order_flag == 0) {
                adapter_mixed.notifyDataSetChanged();
            } else {
                adapter_mixed.onResh();

                adapter_mixed.notifyDataSetChanged();

            }
            EventBus.getDefault().post(new MainMessage(BettingFootballListAdapter.getnumber() + ""));

        }



    }


    @Override
    public void initData() {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.image_back:
                finish();
                break;
            case R.id.layout_pop:
                popwind(layout_title);


                break;

            case R.id.Text_clear_single:
                adapter.onResh(1);

                adapter.notifyDataSetChanged();
                EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber() + ""));

                ToastUtil.showToast1(this, "清空完成");


                break;
            case R.id.button_sure_single:
                if (count < 1) {
                    ToastUtil.showToast1(this, "至少选择一场");
                } else if (count > 15) {
                    ToastUtil.showToast1(this, "至多选择15场");


                } else {

                    Intent intent = new Intent(this, SingleSureActivity.class);

                    Content.list_chooe_single = adapter.getList();
                    intent.putExtra("single", title_text.getText().toString());
                    intent.putExtra("flag", 1);
                    intent.putExtra("flag_guan", 0);
                    intent.putExtra("issingle",0);

                    startActivity(intent);
                }
                break;
            case R.id.Text_clear_mixed:
              change();


                ToastUtil.showToast1(this, "清空完成");
                break;
            case R.id.button_sure_mixed:
                if (single_mixed == 3) {
                    int issingle=0;
                    ChooseBean chooseList = adapter_mixed.getChooseList();
                    List<SubMixBean> list_subMixBean_choose = chooseList.getList_subMixBean_choose();

                    Log.e("YZS",list_subMixBean_choose.size()+"");

                    for (int i = 0; i < list_subMixBean_choose.size(); i++) {
                        List<Integer> list_single = list_subMixBean_choose.get(i).getList_single();
                        for (int j = 0; j < list_single.size(); j++) {
                         if(list_single.get(j)==0){
                             issingle =1;
                         }

                        }

                    }

                    if (count_mixed < 2) {

                        if(issingle==0&&count_mixed>0){

                            jumpmixed(0,chooseList);

                        }else {
                            ToastUtil.showToast1(this, "至少选择两场");

                        }
                    } else if (count_mixed > 15) {
                        ToastUtil.showToast1(this, "至多选择15场");


                    } else {
                        if(issingle==0){
                            jumpmixed(0,chooseList);

                        }else {
                            jumpmixed(1,chooseList);

                        }

                    }

                } else {
                    int issingle = 0;
                    ChooseBean getchooselist = adapter.getchooselist();
                    List<SubMixBean> list_subMixBean_choose = getchooselist.getList_subMixBean_choose();
                    for (int i = 0; i < list_subMixBean_choose.size(); i++) {
                        List<Integer> list_single = list_subMixBean_choose.get(i).getList_single();
                        for (int j = 0; j < list_single.size(); j++) {
                            if(list_single.get(j)==0){
                                issingle =1;
                            }

                        }

                    }


                    //选择几场可以进去
                    int chang;
                    if (single_mixed >= 4) {
                        chang = 1;

                    } else {
                        if(issingle==0){
                            chang = 1;

                        }else {
                            chang = 2;

                        }
                    }
                    if (count < chang) {
                        if (chang == 2) {
                            ToastUtil.showToast1(this, "至少选择二场");

                        } else {
                            ToastUtil.showToast1(this, "至少选择一场");

                        }
                    } else if (count > 15) {
                        ToastUtil.showToast1(this, "至多选择15场");


                    } else {

                        if (chang == 2) {
                            Intent intent = new Intent(this, SingleSureActivity.class);

                            Content.list_chooe_single = adapter.getList();
                            intent.putExtra("single", title_text.getText().toString());
                            intent.putExtra("flag", 0);
                            intent.putExtra("flag_guan", 1);
                            intent.putExtra("issingle",issingle);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(this, SingleSureActivity.class);

                            Content.list_chooe_single = adapter.getList();
                            intent.putExtra("single", title_text.getText().toString());
                            intent.putExtra("flag", 1);
                            intent.putExtra("flag_guan", 1);
                            intent.putExtra("issingle",issingle);

                            startActivity(intent);


                        }

                    }
                }

                break;

        }

    }

    private void jumpmixed(int single,ChooseBean chooseList){

        Intent intent = new Intent(this, MixedSureActivity.class);
        Content.list_chooe = adapter_mixed.getList();
        intent.putExtra("choosebean",chooseList);
        intent.putExtra("issingle",single);
        startActivity(intent);
    }

    //混合

    private void initmixedrecy(int mixed,ArrayList<MultiItemEntity> list,int mm) {

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
                if(mixed==3){
                    adapter_mixed = new BettingFootballListAdapter(list);

                    Lottery_RecyCLerView_mixed.setAdapter(adapter_mixed);
                    adapter_mixed.expandAll();
                    bottom_text(true);
                }else {
                    adapter = new BettingSingleAdapter(list, mm,0);

                    Lottery_RecyCLerView_mixed.setAdapter(adapter);
                    adapter.expandAll();
                    bottom_text(false);

                }

                Lottery_RecyCLerView_mixed.setNestedScrollingEnabled(false);
            }




    }


    //单关
    private void initrecycler(int single,ArrayList<MultiItemEntity> list_single) {

        if (list_single.size() == 0) {
            Lottery_RecyCLerView_single.setVisibility(View.GONE);
            layout_bottom.setVisibility(View.GONE);
            layout_swipe_single.setVisibility(View.GONE);
            layout_empt_single.setVisibility(View.VISIBLE);
        } else {
            layout_empt_single.setVisibility(View.GONE);
            Text_loading_single.setVisibility(View.GONE);
            Lottery_RecyCLerView_single.setVisibility(View.VISIBLE);
            layout_bottom.setVisibility(View.VISIBLE);
            layout_swipe_single.setVisibility(View.VISIBLE);
            adapter = new BettingSingleAdapter(list_single, single,1);

            Lottery_RecyCLerView_single.setAdapter(adapter);
            Lottery_RecyCLerView_single.setNestedScrollingEnabled(false);
            adapter.expandAll();
        }





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

            if (msg.what == 1) {

                if (flag == 0) {
                    initrecycler(1,list);
                    layout_pop.setEnabled(true);
                } else {

                }

            } else {
                //在这里处理首次进入加载混合投注

                initmixedrecy(3,list_mixed,3);
                layout_pop.setEnabled(true);


            }


        }
    };

    private void popwind(View view) {
        View contentView = LayoutInflater.from(view.getContext()).inflate(R.layout.bettingfoot_pop_layout, null);
        PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        darkenBackground(0.5f);
        popupWindow.showAsDropDown(view);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                darkenBackground(1f);

            }
        });
        TextView choose_one = contentView.findViewById(R.id.choose_one);
        TextView choose_two = contentView.findViewById(R.id.choose_two);
        GridView GridView_betting_Money = contentView.findViewById(R.id.GridView_betting_Money);
        GridView GridView_betting_Money_two = contentView.findViewById(R.id.GridView_betting_Money_two);

        GridView_Adapter gridView_adapter = new GridView_Adapter(BettingfootActivity.this, list_pop);
        GridView_Adapter gridView_adapter_two = new GridView_Adapter(BettingfootActivity.this, list_pop_two);
        GridView_betting_Money_two.setAdapter(gridView_adapter_two);
        GridView_betting_Money.setAdapter(gridView_adapter);
        if (popwindows == 0) {

            choose_one.setTextColor(getResources().getColor(R.color.login_red));
            choose_two.setTextColor(getResources().getColor(R.color.font_black));


            layout_mixed.setVisibility(View.VISIBLE);

            layout_single.setVisibility(View.GONE);
            GridView_betting_Money.setVisibility(View.VISIBLE);
            GridView_betting_Money_two.setVisibility(View.GONE);


        } else {
            choose_one.setTextColor(getResources().getColor(R.color.font_black));
            choose_two.setTextColor(getResources().getColor(R.color.login_red));


            layout_mixed.setVisibility(View.GONE);
            GridView_betting_Money.setVisibility(View.GONE);
            GridView_betting_Money_two.setVisibility(View.VISIBLE);
            layout_single.setVisibility(View.VISIBLE);


        }


        choose_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();

                popwindows = 0;
                choose_one.setTextColor(getResources().getColor(R.color.login_red));
                choose_two.setTextColor(getResources().getColor(R.color.font_black));


                layout_mixed.setVisibility(View.VISIBLE);
                GridView_betting_Money.setVisibility(View.VISIBLE);
                GridView_betting_Money_two.setVisibility(View.GONE);
                layout_single.setVisibility(View.GONE);
                initlist();
                single_mixed = 3;
                initmixedrecy(3,list_mixed,3);
                title_text.setText(String_pop_one[2]);
                gridView_adapter.notifyDataSetChanged();
            }
        });
        choose_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();


                popwindows = 1;

                choose_one.setTextColor(getResources().getColor(R.color.font_black));
                choose_two.setTextColor(getResources().getColor(R.color.login_red));


                layout_mixed.setVisibility(View.GONE);
                GridView_betting_Money.setVisibility(View.GONE);
                GridView_betting_Money_two.setVisibility(View.VISIBLE);

                layout_single.setVisibility(View.VISIBLE);
                initlist();
                initrecycler(1,list);
                title_text.setText(String_pop_two[0]);
                gridView_adapter_two.notifyDataSetChanged();

            }
        });
        GridView_betting_Money.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    switch (position) {
                        case 0:
                            single_mixed = 1;
                            initmixedrecy(1,list_one_mixed,1);
                            title_text.setText(String_pop_one[0]);
                            change();
                            break;
                        case 1:
                            single_mixed = 2;
                            initmixedrecy(2,list_two_mixed,2);
                            title_text.setText(String_pop_one[1]);
                            change();

                            break;
                        case 2:
                            single_mixed = 3;
                            initmixedrecy(3,list_mixed,3);
                            title_text.setText(String_pop_one[2]);
                            change();

                            break;
                        case 3:
                            single_mixed = 4;
                            initmixedrecy(4,list_four_mixed,3);
                            title_text.setText(String_pop_one[3]);
                            change();

                            break;
                        case 4:
                            single_mixed = 5;
                            initmixedrecy(5,list_five_mixed,4);
                            title_text.setText(String_pop_one[4]);
                            change();

                            break;
                        case 5:
                            single_mixed = 6;
                            initmixedrecy(6,list_six_mixed,5);
                            title_text.setText(String_pop_one[5]);
                            change();

                            break;

                    }


                    popupWindow.dismiss();



                gridView_adapter.choiceState(position);


            }
        });
        GridView_betting_Money_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        initrecycler(1,list);
                        title_text.setText(String_pop_two[0]);
                        change();

                        break;
                    case 1:
                        initrecycler(2,list_two);

                        title_text.setText(String_pop_two[1]);
                        change();

                        break;
                    case 2:
                        initrecycler(3,list_three);
                        title_text.setText(String_pop_two[2]);
                        change();

                        break;
                    case 3:
                        initrecycler(4,list_four);
                        title_text.setText(String_pop_two[3]);
                        change();

                        break;
                    case 4:
                        initrecycler(5,list_fire);
                        title_text.setText(String_pop_two[4]);
                        change();

                        break;

                }
                gridView_adapter_two.choiceState(position);

                popupWindow.dismiss();


            }
        });

    }


    public void change() {

        if (adapter != null) {
            adapter.onResh(1);
            adapter.notifyDataSetChanged();
            EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber() + ""));

        }
        if (adapter_mixed != null) {
            adapter_mixed.onResh();
            adapter_mixed.notifyDataSetChanged();
            EventBus.getDefault().post(new MainMessage(BettingFootballListAdapter.getnumber() + ""));

        }



    }




    //混合投注

    private ArrayList<MultiItemEntity> generateData() {
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        final ArrayList<MultiItemEntity> res = new ArrayList<>();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getFootballList", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.e("YZS", e.getMessage() + "");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                try {


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                int code = jsonObject.getInt("code");
                                String msg = jsonObject.getString("msg");
                                if (code == 10000) {
//                                ToastUtil.showToast(BettingActivity.this, msg + "");
                                    MixedFootballBean footballBean = JSON.parseObject(result, MixedFootballBean.class);
                                    List<ChooseMixedBean> list_choose = new ArrayList<>();

                                    for (int i = 0; i < footballBean.getData().getGame_info().size(); i++) {

                                        ExpandItem_football item = new ExpandItem_football(footballBean.getData().getGame_info().get(i).getGame_week() + "" + footballBean.getData().getGame_info().get(i).getGame_group_time() + "共有" + footballBean.getData().getGame_info().get(i).getGame_info().size() + "场比赛可投");
                                        for (int j = 0; j < footballBean.getData().getGame_info().get(i).getGame_info().size(); j++) {
                                            ChooseMixedBean chooseMixedBean = new ChooseMixedBean();
                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.HomeScoreOddsBean> home_score_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getHome_score_odds();

                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.LetScoreOddsBean> home_let_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getLet_score_odds();
                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.ScoreOddsBean> score_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getScore_odds();
                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.TotalOddsBean> total_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getTotal_odds();
                                            List<MixedFootballBean.DataBean.GameInfoBeanX.GameInfoBean.HalfOddsBean> half_odds = footballBean.getData().getGame_info().get(i).getGame_info().get(j).getHalf_odds();

                                            List<ItemPoint> listone = new ArrayList<>();
                                            List<ItemPoint> listtwo = new ArrayList<>();
                                            List<ItemPoint> listthree = new ArrayList<>();
                                            List<ItemPoint> listfour = new ArrayList<>();

                                            List<ItemPoint> listfive = new ArrayList<>();

                                            for (int k = 0; k < home_score_odds.size(); k++) {

                                                ItemPoint itemPoint = new ItemPoint();
                                                itemPoint.setIsselect(false);
                                                itemPoint.setId(home_score_odds.get(k).getOdds_code());
                                                itemPoint.setSingle(home_score_odds.get(k).getSingle());
                                                itemPoint.setGame_odds_id(home_score_odds.get(k).getGame_odds_id());
                                                itemPoint.setOdds(home_score_odds.get(k).getOdds());
                                                listfive.add(itemPoint);

                                            }

                                            for (int k = 0; k < home_let_odds.size(); k++) {

                                                ItemPoint itemPoint = new ItemPoint();
                                                itemPoint.setIsselect(false);
                                                itemPoint.setId(home_let_odds.get(k).getOdds_code());
                                                itemPoint.setSingle(home_let_odds.get(k).getSingle());

                                                itemPoint.setGame_odds_id(home_let_odds.get(k).getGame_odds_id());
                                                itemPoint.setOdds(home_let_odds.get(k).getOdds());
                                                listone.add(itemPoint);

                                            }
                                            listfive.addAll(listone);


                                            for (int k = 0; k < score_odds.size(); k++) {
                                                ItemPoint itemPoint = new ItemPoint();
                                                itemPoint.setIsselect(false);
                                                itemPoint.setSingle(score_odds.get(k).getSingle());

                                                itemPoint.setId(score_odds.get(k).getOdds_code());
                                                itemPoint.setGame_odds_id(score_odds.get(k).getGame_odds_id());
                                                itemPoint.setOdds(score_odds.get(k).getOdds());
                                                listtwo.add(itemPoint);

                                            }
                                            for (int k = 0; k < total_odds.size(); k++) {
                                                ItemPoint itemPoint = new ItemPoint();
                                                itemPoint.setIsselect(false);
                                                itemPoint.setSingle(total_odds.get(k).getSingle());

                                                itemPoint.setId(total_odds.get(k).getOdds_code());
                                                itemPoint.setGame_odds_id(total_odds.get(k).getGame_odds_id());
                                                itemPoint.setOdds(total_odds.get(k).getOdds());
                                                listthree.add(itemPoint);

                                            }
                                            for (int k = 0; k < half_odds.size(); k++) {
                                                ItemPoint itemPoint = new ItemPoint();
                                                itemPoint.setIsselect(false);
                                                itemPoint.setId(half_odds.get(k).getOdds_code());
                                                itemPoint.setGame_odds_id(half_odds.get(k).getGame_odds_id());
                                                itemPoint.setSingle(half_odds.get(k).getSingle());

                                                itemPoint.setOdds(half_odds.get(k).getOdds());

                                                listfour.add(itemPoint);

                                            }
                                            chooseMixedBean.setOnelist(listfive);
                                            chooseMixedBean.setTwolist(listtwo);
                                            chooseMixedBean.setThreelist(listthree);

                                            chooseMixedBean.setFourlist(listfour);
                                            chooseMixedBean.setGame_id(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_id());
                                            chooseMixedBean.setHome_team(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name());

                                            chooseMixedBean.setGuest_team(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name());
                                            chooseMixedBean.setName(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no() + "        " + footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name()
                                                    + "        " + "vs" + "        " + footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name());
                                            chooseMixedBean.setHome_score(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_score());
                                            chooseMixedBean.setGuest_score(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_let_score());
                                            list_choose.add(chooseMixedBean);

                                            Expand1Item_football item1 = new Expand1Item_football(footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_name(),
                                                    footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_team_name()
                                                    , footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_guest_team_name(), footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_home_score(),
                                                    footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_let_score(), footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_sequence_no()
                                                    , footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_stop_time()
                                                    , listfive, listtwo, listthree, listfour, list_choose, "展开更多选项", footballBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_no(),footballBean.getData().getGame_info().get(i).getGame_info().get(j).getHome_single(),footballBean.getData().getGame_info().get(i).getGame_info().get(j).getLet_single());


                                            item.addSubItem(item1);
                                        }

                                        res.add(item);
                                    }


//                                handler.sendEmptyMessageDelayed(1, 3000);
                                    handler.sendEmptyMessage(2);

                                } else {
                                    ToastUtil.showToast(BettingfootActivity.this, msg + "");

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                } catch (Exception e) {

                }
            }

        });


        return res;
    }





}
