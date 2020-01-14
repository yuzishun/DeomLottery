package com.example.yuzishun.deomlottery.main.activity;

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
import com.example.yuzishun.deomlottery.main.adapter.GridView_Adapter;
import com.example.yuzishun.deomlottery.main.baskball.BaskballAdapter;
import com.example.yuzishun.deomlottery.main.baskball.BaskballSingleAdapter;
import com.example.yuzishun.deomlottery.main.baskball.BasketballSureActivity;

import com.example.yuzishun.deomlottery.main.baskball.Expand1Item_bask;
import com.example.yuzishun.deomlottery.main.baskball.ExpandItem_bask;

import com.example.yuzishun.deomlottery.main.util.BettFootBaskMixedUtil;
import com.example.yuzishun.deomlottery.main.util.BettFootBaskSIngleUtil;
import com.example.yuzishun.deomlottery.model.BasketBallBean;
import com.example.yuzishun.deomlottery.model.ChooseBaskBean;
import com.example.yuzishun.deomlottery.model.ChooseBean;
import com.example.yuzishun.deomlottery.model.ChoosebettBean;
import com.example.yuzishun.deomlottery.model.ItemPoint;
import com.example.yuzishun.deomlottery.model.SubMixBean;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.ToastUtil;
import com.example.yuzishun.deomlottery.utils.eventbus.BasketAdapterMessage;
import com.example.yuzishun.deomlottery.utils.eventbus.BasketMessage;

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





    //单关
    private BaskballSingleAdapter adapter_single;
    private BasketBallBean basketBallBean;
    private int count;
    private ArrayList<MultiItemEntity> multiItemEntities;
    private ArrayList<MultiItemEntity> list;
    private ArrayList<MultiItemEntity> list_two;
    private ArrayList<MultiItemEntity> list_three;
    private ArrayList<MultiItemEntity> list_four;
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


    private String[] String_pop_one = new String[]{"胜负", "让分胜负", "胜分差", "大小分", "混合过关"};
    private String[] String_pop_two = new String[]{"胜负", "让分胜负", "胜分差", "大小分"};
    private int popwindows = 0;

    private List<ChoosebettBean> list_pop = new ArrayList<>();
    private List<ChoosebettBean> list_pop_two = new ArrayList<>();
    private int single_mixed;


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
        Text_clear_single.setOnClickListener(this);
        button_sure_single.setOnClickListener(this);
        Scene_TextView_single.setOnClickListener(this);
        multiItemEntities = generateData();
        adapter =  new BaskballAdapter(multiItemEntities,5);
        initlist();




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
//
                        change();

                        layout_swipe_mixed.setRefreshing(false);
                        Toast.makeText(BasketBallMixedActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();

                    }
                },1000);


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
                        change();


                        layout_swipe_single.setRefreshing(false);
                        Toast.makeText(BasketBallMixedActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();

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
                BettFootBaskMixedUtil bettFootBaskMixedUtil = new BettFootBaskMixedUtil(BasketBallMixedActivity.this);
                list_one_mixed = bettFootBaskMixedUtil.generateData_mixed(1);
                list_two_mixed = bettFootBaskMixedUtil.generateData_mixed(2);
                list_three_mixed = bettFootBaskMixedUtil.generateData_mixed(3);
                list_four_mixed = bettFootBaskMixedUtil.generateData_mixed(4);


                BettFootBaskSIngleUtil bettFootBaskSIngleUtil = new BettFootBaskSIngleUtil(BasketBallMixedActivity.this);

//
                list = bettFootBaskSIngleUtil.request_bask(1);
                list_two = bettFootBaskSIngleUtil.request_bask(2);
                list_three = bettFootBaskSIngleUtil.request_bask(3);
                list_four = bettFootBaskSIngleUtil.request_bask(4);


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
            if(i==4){

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

    @Override
    protected void onResume() {
        super.onResume();

        if(Content.order_flag_bask==0){
            adapter.notifyDataSetChanged();

        }else {
            change();


        }
        if(popwindows==0){
            EventBus.getDefault().post(new BasketMessage(BaskballAdapter.getnumber()+""));

        }else {
            EventBus.getDefault().post(new BasketMessage(BaskballSingleAdapter.getnumber()+""));

        }

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

    //篮球混合投注
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

    //篮球单关投注
    public void initsinglerescu(int mixed,ArrayList<MultiItemEntity> list){

        if (list.size() == 0) {
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
            adapter_single = new BaskballSingleAdapter(list, mixed);

            Lottery_RecyCLerView_single.setAdapter(adapter_single);
            Lottery_RecyCLerView_single.setNestedScrollingEnabled(false);
            adapter_single.expandAll();
        }




    };






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
                change();
                ToastUtil.showToast1(this, "清空完成");

                break;
            case R.id.Text_clear_single:
                change();
                ToastUtil.showToast1(this, "清空完成");

                break;
            case R.id.play_messag:
                Intent intent1 = new Intent(this,Play_MessageActivity.class);
                intent1.putExtra("flag",1);
                startActivity(intent1);
                break;
            case R.id.button_sure_mixed:
                int isdan=0;

                    ChooseBean chooseList = adapter.getChooseList();
                    List<SubMixBean> list_subMixBean_choose = chooseList.getList_subMixBean_choose();
                    for (int i = 0; i < list_subMixBean_choose.size(); i++) {
                        List<Integer> list_single = list_subMixBean_choose.get(i).getList_single();
                        for (int j = 0; j < list_single.size(); j++) {
                            if(list_single.get(j)==0){
                                isdan =1;
                            }
                        }

                    }
                    if(isdan==1){
                        jumpsure(2,isdan,0);

                    }else {
                        jumpsure(1,isdan,0);

                    }






                break;
            case R.id.button_sure_single:
                jumpsure(1,0,1);


                break;


        }
    }
    public void jumpsure(int chang,int isdan,int single){
            if(single==1){
                if(adapter_single.getnumber()<chang){
                    ToastUtil.showToast1(this,"至少选择"+chang+"场比赛");

                }else {
                    if(adapter_single.getnumber()>15) {

                        ToastUtil.showToast1(this,"最多选15场");

                    }else {
                        Intent intent = new Intent(this,BasketballSureActivity.class);

                        Content.list_chooe_bask = adapter_single.getList();
                        intent.putExtra("isdan",isdan);
                        intent.putExtra("title",title_text.getText().toString().trim());
                        intent.putExtra("single",single);

                        startActivity(intent);

                    }
                }
            }else {
                if(adapter.getnumber()<chang){
                    ToastUtil.showToast1(this,"至少选择"+chang+"场比赛");

                }else {
                    if(adapter.getnumber()>15) {

                        ToastUtil.showToast1(this,"最多选15场");

                    }else {
                        Intent intent = new Intent(this,BasketballSureActivity.class);
                        Content.list_chooe_bask = adapter.getList();

                        intent.putExtra("isdan",isdan);
                        intent.putExtra("title",title_text.getText().toString().trim());
                        intent.putExtra("single",single);
                        startActivity(intent);

                    }
                }
            }


    }

    private void popwin(View view) {
        View contentView = LayoutInflater.from(view.getContext()).inflate(R.layout.bettingfoot_pop_layout,null);
        PopupWindow popup = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        //popwindow开始的时候设置屏幕变暗
        popup.showAsDropDown(view);
        darkenBackground(0.5f);

        //popwindow消失的时候设置屏幕变亮

        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                darkenBackground(1f);

            }
        });


        TextView choose_one = contentView.findViewById(R.id.choose_one);
        TextView choose_two = contentView.findViewById(R.id.choose_two);
        GridView GridView_betting_Money = contentView.findViewById(R.id.GridView_betting_Money);
        GridView GridView_betting_Money_two = contentView.findViewById(R.id.GridView_betting_Money_two);

        GridView_Adapter gridView_adapter = new GridView_Adapter(BasketBallMixedActivity.this, list_pop);
        GridView_Adapter gridView_adapter_two = new GridView_Adapter(BasketBallMixedActivity.this, list_pop_two);
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
//                single_mixed = 3;
                initmixedrecy(5,list_mixed);
                title_text.setText(String_pop_one[4]);
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
                initsinglerescu(1,list);
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
                            initmixedrecy(1,list_one_mixed);
                            title_text.setText(String_pop_one[0]);
                            change();
                            break;
                        case 1:
                            single_mixed = 2;
                            initmixedrecy(2,list_two_mixed);
                            title_text.setText(String_pop_one[1]);
                            change();

                            break;
                        case 2:
                            single_mixed = 3;
                            initmixedrecy(3,list_three_mixed);
                            title_text.setText(String_pop_one[2]);
                            change();

                            break;
                        case 3:
                            single_mixed = 4;
                            initmixedrecy(4,list_four_mixed);
                            title_text.setText(String_pop_one[3]);
                            change();

                            break;
                        case 4:
                            single_mixed = 5;
                            initmixedrecy(5,list_mixed);
                            title_text.setText(String_pop_one[4]);
                            change();

                            break;

                    }
                    popup.dismiss();
                    gridView_adapter.choiceState(position);
            }
        });
        GridView_betting_Money_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                        case 0:
                            initsinglerescu(1,list);
                            title_text.setText(String_pop_two[0]);
                            change();

                            break;
                        case 1:
                            initsinglerescu(2,list_two);
                            title_text.setText(String_pop_two[1]);
                            change();

                            break;
                        case 2:
                            initsinglerescu(3,list_three);
                            title_text.setText(String_pop_two[2]);
                            change();

                            break;
                        case 3:
                            initsinglerescu(4,list_four);
                            title_text.setText(String_pop_two[3]);
                            change();

                            break;
                    }

                  gridView_adapter_two.choiceState(position);

                    popup.dismiss();

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
            Scene_TextView_single.setText("请选择比赛");

        }else {
            Scene_TextView_mixed.setText("已经选择"+msg.getMessage()+"比赛");
            Scene_TextView_single.setText("已经选择"+msg.getMessage()+"比赛");

        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(BasketAdapterMessage msg) {
        Log.e("YZSYZSYZS", msg.getPostion()+"");
        Content.Text_postion_mixed_basket = msg.getPostion();
        if(adapter_single!=null){
            adapter_single.notifyItemChanged(msg.getPostion());

        }
        if(adapter!=null){
            adapter.notifyItemChanged(msg.getPostion());

        }

        if(msg.getPostion()==0){
            if(adapter_single!=null){
                adapter_single.notifyDataSetChanged();

            }
            if(adapter!=null){
                adapter.notifyDataSetChanged();

            }

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
        if(popwindows==0){
            if (adapter != null) {
                adapter.onResh();
                adapter.notifyDataSetChanged();
                EventBus.getDefault().post(new BasketMessage(BaskballAdapter.getnumber() + ""));


            }
        }else {
            if(adapter_single !=null){
                adapter_single.onResh();
                adapter_single.notifyDataSetChanged();
                EventBus.getDefault().post(new BasketMessage(BaskballSingleAdapter.getnumber() + ""));

            }
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
                                            itemPoint.setSingle(home_let_odds.get(k).getSingle());
                                            listone.add(itemPoint);

                                        }
                                        for (int k = 0; k <score_guest_odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(score_guest_odds.get(k).getOdds_code());
                                            itemPoint.setGame_odds_id(score_guest_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(score_guest_odds.get(k).getOdds());
                                            itemPoint.setSingle(score_guest_odds.get(k).getSingle());

                                            listtwo.add(itemPoint);

                                        }
                                        for (int k = 0; k <score_home_odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(score_home_odds.get(k).getOdds_code());
                                            itemPoint.setGame_odds_id(score_home_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(score_home_odds.get(k).getOdds());
                                            itemPoint.setSingle(score_home_odds.get(k).getSingle());

                                            listthree.add(itemPoint);

                                        }
                                        for (int k = 0; k <let_score_odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(let_score_odds.get(k).getOdds_code());
                                            itemPoint.setGame_odds_id(let_score_odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(let_score_odds.get(k).getOdds());
                                            itemPoint.setSingle(let_score_odds.get(k).getSingle());

                                            listfour.add(itemPoint);

                                        }
                                        for (int k = 0; k <total__odds.size() ; k++) {
                                            ItemPoint itemPoint = new ItemPoint();
                                            itemPoint.setIsselect(false);

                                            itemPoint.setId(total__odds.get(k).getOdds_code());
                                            itemPoint.setGame_odds_id(total__odds.get(k).getGame_odds_id());
                                            itemPoint.setOdds(total__odds.get(k).getOdds());
                                            itemPoint.setSingle(total__odds.get(k).getSingle());

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
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getGame_no(),"展开更多选项","展开更多选项",
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getHome_single(),basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getLet_single(),
                                                basketBallBean.getData().getGame_info().get(i).getGame_info().get(j).getTotal_single());

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













}
