package com.example.yuzishun.newdeom.main.activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.main.adapter.GridView_Betting_Adapter;
import com.example.yuzishun.newdeom.main.single.BettingSingleAdapter;
import com.example.yuzishun.newdeom.main.single.Item1_Single;
import com.example.yuzishun.newdeom.main.single.Item_Single;
import com.example.yuzishun.newdeom.main.single.SingleMessage;
import com.example.yuzishun.newdeom.main.single.SingleSureActivity;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.SingleBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.AdapterMessage;
import com.example.yuzishun.newdeom.utils.ToastUtil;
import com.example.yuzishun.newdeom.utils.eventbus.SinglePostionMessage;
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
//1:点击别的加载的过慢，
//2:只有第一次点击别的按钮是会出来布局，别的不会

public class SingleActivity extends BaseActivity implements View.OnClickListener {

    private TextView Text_loading;
    private SwipeRefreshLayout layout_swipe;
    private LinearLayout layout_bottom;
    private Button button_sure;
    private TextView Scene_TextView;
    private RecyclerView Lottery_RecyCLerView;
    private BettingSingleAdapter adapter;
    private ArrayList<MultiItemEntity> list;
    private ArrayList<MultiItemEntity> list_two;
    private ArrayList<MultiItemEntity> list_three;
    private ArrayList<MultiItemEntity> list_four;
    private ArrayList<MultiItemEntity> list_fire;

    private TextView Text_clear;
    private LinearLayout layout_empt;
    private SingleBean singleBean;
    private int count=0;
    private int single=1;
    @BindView(R.id.layout_pop)
    LinearLayout layout_pop;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.Lottery_RecyCLerView_two)
    RecyclerView Lottery_RecyCLerView_two;
    @BindView(R.id.Lottery_RecyCLerView_three)
    RecyclerView Lottery_RecyCLerView_three;
    @BindView(R.id.Lottery_RecyCLerView_four)
    RecyclerView Lottery_RecyCLerView_four;
    @BindView(R.id.Lottery_RecyCLerView_fire)
    RecyclerView Lottery_RecyCLerView_fire;
    private String[] list1=new String[]{"混合投注"};
    private String[] list2=new String[]{"胜负平","让球胜负平","比分","总进球","半全场"};
    private List<String> list_pop_one = new ArrayList<>();
    private List<String> list_pop_two = new ArrayList<>();
    @Override
    public int intiLayout() {
        return R.layout.activity_single;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initview();
        initdata();

    }

    @Override
    public void initData() {
        layout_pop.setOnClickListener(this);
        image_back.setOnClickListener(this);

    }


    private void initdata() {
        EventBus.getDefault().register(this);




        Lottery_RecyCLerView.setLayoutManager(new LinearLayoutManager(this));
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

                        adapter.onResh(1);
                        adapter.notifyDataSetChanged();
                        EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber()+""));

                        layout_swipe.setRefreshing(false);
                        Toast.makeText(SingleActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();

                    }
                },1000);


            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...



                list = request(single);
                list_two = request(2);
                list_three = request(3);
                list_four = request(4);
                list_fire = request(5);
//                adapter = new BettingSingleAdapter(list,single);
//
//                Lottery_RecyCLerView.setAdapter(adapter);
//                Lottery_RecyCLerView.setNestedScrollingEnabled(false);

                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(2, 2000);

            }
        }).start();
    }

    private void initrecycler(int single) {
        handler.sendEmptyMessageDelayed(1, 1000);

        switch (single){

            case 1:
                if(list.size()==0){
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
                    adapter = new BettingSingleAdapter(list,single);

                    Lottery_RecyCLerView.setAdapter(adapter);
                    Lottery_RecyCLerView.setNestedScrollingEnabled(false);
                    adapter.expandAll();
                }

                break;
            case 2:
                if(list_two.size()==0){
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
                    adapter = new BettingSingleAdapter(list_two,single);

                    Lottery_RecyCLerView.setAdapter(adapter);
                    Lottery_RecyCLerView.setNestedScrollingEnabled(false);
                    adapter.expandAll();
                }

                break;
            case 3:
                if(list_three.size()==0){
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
                    adapter = new BettingSingleAdapter(list_three,single);

                    Lottery_RecyCLerView.setAdapter(adapter);
                    Lottery_RecyCLerView.setNestedScrollingEnabled(false);
                    adapter.expandAll();
                }



                break;
            case 4:
                if(list_four.size()==0){
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
                    adapter = new BettingSingleAdapter(list_four,single);

                    Lottery_RecyCLerView.setAdapter(adapter);
                    Lottery_RecyCLerView.setNestedScrollingEnabled(false);
                    adapter.expandAll();
                }


                break;
            case 5:
                if(list_fire.size()==0){
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
                    adapter = new BettingSingleAdapter(list_fire,single);

                    Lottery_RecyCLerView.setAdapter(adapter);
                    Lottery_RecyCLerView.setNestedScrollingEnabled(false);
                    adapter.expandAll();
                }



                break;

        }


//        ArrayList<MultiItemEntity> list_two= request(2);
//        BettingSingleAdapter adapter_two = new BettingSingleAdapter(list_two,2);
//        Lottery_RecyCLerView_two.setAdapter(adapter_two);
//        Lottery_RecyCLerView_two.setNestedScrollingEnabled(false);
//
//
//        ArrayList<MultiItemEntity> list_three= request(3);
//        BettingSingleAdapter adapter_three = new BettingSingleAdapter(list_three,3);
//        Lottery_RecyCLerView_three.setAdapter(adapter_three);
//        Lottery_RecyCLerView_three.setNestedScrollingEnabled(false);
//
//
//        ArrayList<MultiItemEntity> list_four= request(4);
//        BettingSingleAdapter adapter_four = new BettingSingleAdapter(list_four,4);
//        Lottery_RecyCLerView_four.setAdapter(adapter_four);
//        Lottery_RecyCLerView_four.setNestedScrollingEnabled(false);
//
//        ArrayList<MultiItemEntity> list_fire= request(5);
//        BettingSingleAdapter adapter_fire = new BettingSingleAdapter(list_fire,5);
//        Lottery_RecyCLerView_four.setAdapter(adapter_fire);
//        Lottery_RecyCLerView_four.setNestedScrollingEnabled(false);

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




    private ArrayList<MultiItemEntity> request(int single) {

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


                    runOnUiThread(new Runnable() {
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
                                            chooseMixedBean.setDesc("展开更多选项");
                                            chooseMixedBean.setGame_id(singleBean.getData().get(i).getGame_info().get(j).getGame_id());
                                            chooseMixedBean.setHome_team(singleBean.getData().get(i).getGame_info().get(j).getGame_home_team_name());

                                            chooseMixedBean.setGuest_team(singleBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name());
                                            chooseMixedBean.setName(singleBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()+"        "+singleBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
                                                    +"        "+"vs"+"        "+singleBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name());
                                            chooseMixedBean.setHome_score(singleBean.getData().get(i).getGame_info().get(j).getGame_home_score());
                                            chooseMixedBean.setGuest_score(singleBean.getData().get(i).getGame_info().get(j).getGame_let_score());
                                            list_choose.add(chooseMixedBean);


                                            Log.e("SingleSize",j+"");

                                            Item1_Single item1 = new Item1_Single(singleBean.getData().get(i).getGame_info().get(j).getGame_name(),
                                                    singleBean.getData().get(i).getGame_info().get(j).getGame_home_team_name()
                                                    ,singleBean.getData().get(i).getGame_info().get(j).getGame_guest_team_name(),singleBean.getData().get(i).getGame_info().get(j).getGame_home_score(),
                                                    singleBean.getData().get(i).getGame_info().get(j).getGame_let_score(),singleBean.getData().get(i).getGame_info().get(j).getGame_sequence_no()
                                                    ,singleBean.getData().get(i).getGame_info().get(j).getGame_stop_time()
                                                    ,list_single,list_choose,"展开更多选项");


                                            item_single.addSubItem(item1);




                                        }
                                        res.add(item_single);



                                    }





                                }else {
                                    ToastUtil.showToast1(SingleActivity.this,msg);

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

    private void initview() {

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

            if(msg.what==1){

            }else {
                initrecycler(single);


            }
//            if(singleBean.getData().size()==0){
//                Lottery_RecyCLerView.setVisibility(View.GONE);
//                layout_bottom.setVisibility(View.GONE);
//                layout_swipe.setVisibility(View.GONE);
//                layout_empt.setVisibility(View.VISIBLE);
//            }else {
//                layout_empt.setVisibility(View.GONE);
//                Text_loading.setVisibility(View.GONE);
//                Lottery_RecyCLerView.setVisibility(View.VISIBLE);
//                layout_bottom.setVisibility(View.VISIBLE);
//                layout_swipe.setVisibility(View.VISIBLE);
//                adapter.expandAll();
//            }



        }
    };



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_sure:
                if(count<1){
                    ToastUtil.showToast1(this,"至少选择一场");
                }else if(count>15){
                    ToastUtil.showToast1(this,"至多选择15场");


                }else  {

                    Intent intent = new Intent(this,SingleSureActivity.class);

                    Content.list_chooe_single = adapter.getList();
                    intent.putExtra("single",single);

                    startActivity(intent);
                }


                break;
            case R.id.Text_clear:
                adapter.onResh(1);

                adapter.notifyDataSetChanged();
                EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber()+""));

                ToastUtil.showToast1(this,"清空完成");
                break;
            case R.id.layout_pop:
                popwindow();
                break;
            case R.id.image_back:
                finish();
                break;

        }
    }

    private void popwindow() {
        list_pop_one.clear();
        for (int i = 0; i <list2.length ; i++) {
            list_pop_one.add(list2[i]);

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
//        dialog.setCanceledOnTouchOutside(false);

        dialog.show();


        TextView choose_one = dialog.findViewById(R.id.choose_one);
        TextView choose_two = dialog.findViewById(R.id.choose_two);
        GridView GridView_betting_Money = dialog.findViewById(R.id.GridView_betting_Money);
        GridView GridView_betting_Money_two = dialog.findViewById(R.id.GridView_betting_Money_two);
        GridView_Betting_Adapter gridView_betting_adapter = new GridView_Betting_Adapter(SingleActivity.this,list_pop_one);
        GridView_betting_Money.setAdapter(gridView_betting_adapter);
//        GridView_Betting_Adapter gridView_betting_adapter2 = new GridView_Betting_Adapter(SingleActivity.this,list_pop_two);
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
//        choose_one.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GridView_betting_Money.setVisibility(View.VISIBLE);
//                GridView_betting_Money_two.setVisibility(View.GONE);
//                choose_one.setTextColor(SingleActivity.this.getResources().getColor(R.color.login_red));
//                choose_two.setTextColor(SingleActivity.this.getResources().getColor(R.color.gray_Overall_hint));
//
//            }
//        });
//        choose_two.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GridView_betting_Money.setVisibility(View.GONE);
//                GridView_betting_Money_two.setVisibility(View.VISIBLE);
//                choose_one.setTextColor(SingleActivity.this.getResources().getColor(R.color.gray_Overall_hint));
//                choose_two.setTextColor(SingleActivity.this.getResources().getColor(R.color.login_red));
//            }
//        });

        GridView_betting_Money.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridView_betting_adapter.choiceState(position);
                switch (position){
                    case 0:
                        single=1;
//                        adapter.onResh();
                        initrecycler(1);
                        title_text.setText(list2[0]);
                        break;
                    case 1:
                        single=2;
                        initrecycler(2);

//                        adapter.onResh();
                        title_text.setText(list2[1]);

                        break;
                    case 2:
                        single=3;
                        initrecycler(3);
                        title_text.setText(list2[2]);

//                        adapter.onResh();
                        break;
                    case 3:
                        single=4;
                        initrecycler(4);
                        title_text.setText(list2[3]);

//                        adapter.onResh();
                        break;
                    case 4:
                        single=5;
                        initrecycler(5);
                        title_text.setText(list2[4]);

//                        adapter.onResh();
                        break;

                }


                dialog.dismiss();

            }
        });
//        GridView_betting_Money_two.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                gridView_betting_adapter2.choiceState(position);
//                int single=0;
//                if(position==0){
//                    single=1;
//                }else {
//                    single=3;
//
//                }
//
//
//
//
//                dialog.dismiss();
//
//            }
//        });



    }
    /**
     * 主线程中执行
     *
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(SinglePostionMessage msg) {
        Log.e("YZSYZSYZS", msg.getPostion()+"");
        Content.Text_postion = msg.getPostion();
        adapter.notifyItemChanged(msg.getPostion());
        if(msg.getPostion()==0){


        adapter.notifyDataSetChanged();
        }else {

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null){

            if(Content.order_flag_single==0){
                adapter.notifyDataSetChanged();

            }else {
                adapter.onResh(0);

                adapter.notifyDataSetChanged();

            }
        }


        EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber()+""));
    }


}
