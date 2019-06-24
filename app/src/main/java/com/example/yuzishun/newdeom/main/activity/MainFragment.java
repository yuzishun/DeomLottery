package com.example.yuzishun.newdeom.main.activity;


import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.MainActivity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Basefragment;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.LazyFragment;
import com.example.yuzishun.newdeom.login.activity.LoginActivity;
import com.example.yuzishun.newdeom.login.custom.ClearEditText;
import com.example.yuzishun.newdeom.main.adapter.NewsRecyclerViewAdapter;
import com.example.yuzishun.newdeom.main.single.Item1_Single;
import com.example.yuzishun.newdeom.main.single.Item_Single;
import com.example.yuzishun.newdeom.main.single.SingleSureActivity;
import com.example.yuzishun.newdeom.model.BananBean;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.CodeBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.MainInfomationBean;
import com.example.yuzishun.newdeom.model.SingleBean;
import com.example.yuzishun.newdeom.model.VerticaBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.SpUtil;
import com.example.yuzishun.newdeom.utils.ToastUtil;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.paradoxie.autoscrolltextview.VerticalTextview;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * 首页面的fragment
 */
public class MainFragment extends LazyFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RollPagerView mainRollPagerView;
    private VerticalTextview VerticaTextView;
    private Button Button_victory;
    private Button Button_ping;
    private Button Button_loser;
    private Button Button_wushi;
    private Button Button_erbai;
    private Button Button_wubai;
    private Button news;
    private Button match;
    private LinearLayout dan_layout;
    private ImageView Image_lottery;
    private RecyclerView newsRecycleriView;
    private SwipeRefreshLayout Home_Refresh;
    private TextView Text_loading;
    private LinearLayout layout_scroll;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private ArrayList<String> titleList;
    private LinearLayout layout_football,layout_baskball;
    private  int flag=0,bett=0,money_flag=0,edit_flag=0,multiple;

    private int flag_all=0;
    private List<String>  imgs;
    private long mLastClickTime = 0;
    public static final long TIME_INTERVAL = 1000L;
    private List<MainInfomationBean.DataBean> list = new ArrayList<>();
    private TextView Text_football_data;
    private Button Text_betting;
    private String game_id;
    private Dialog dialog;

    private TextView left_team,right_team,Text_prour;
    private ClearEditText Multiple_Money;
    private List<SingleBean.DataBean.GameInfoBean.SingleOddsBean> single_odds;
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_main);
        initView();
        initData();
        request();
    }

    private void request() {

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.GetAsynMap(Url.baseUrl + "app/ball/getSingleFootballList?single=1", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if (code == 10000) {

                               SingleBean singleBean = JSON.parseObject(result, SingleBean.class);
                                if(singleBean.getData().size()==0){
                                    dan_layout.setVisibility(View.GONE);
                                }else {
                                    Text_football_data.setText(singleBean.getData().get(0).getGame_info().get(0).getGame_stop_time());
                                    left_team.setText(singleBean.getData().get(0).getGame_info().get(0).getGame_home_team_name());
                                    right_team.setText(singleBean.getData().get(0).getGame_info().get(0).getGame_guest_team_name());
                                    Button_victory.setText(singleBean.getData().get(0).getGame_info().get(0).getSingle_odds().get(0).getOdds_code()+
                                            singleBean.getData().get(0).getGame_info().get(0).getSingle_odds().get(0).getOdds());
                                    Button_ping.setText(singleBean.getData().get(0).getGame_info().get(0).getSingle_odds().get(1).getOdds_code()+
                                            singleBean.getData().get(0).getGame_info().get(0).getSingle_odds().get(1).getOdds());
                                    Button_loser.setText(singleBean.getData().get(0).getGame_info().get(0).getSingle_odds().get(2).getOdds_code()+
                                            singleBean.getData().get(0).getGame_info().get(0).getSingle_odds().get(2).getOdds());
                                    game_id = singleBean.getData().get(0).getGame_info().get(0).getGame_id();
                                    single_odds = singleBean.getData().get(0).getGame_info().get(0).getSingle_odds();



                                }



                            }else {
                                ToastUtil.showToast(getActivity(),msg);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });



            }
        });





    }

    private void getVerticaList() {
        titleList = new ArrayList<>();
        HashMap<String,String> hashMap = new HashMap<>();
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/order/getWinPriceByOrder", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        VerticaBean verticaBean = JSON.parseObject(result,VerticaBean.class);
                        if(verticaBean.getCode()==10000){

                            for (int i = 0; i <verticaBean.getData().size() ; i++) {
                                titleList.add("恭喜用户"+verticaBean.getData().get(i).getUname()+"中奖"+verticaBean.getData().get(i).getBonus_price()+"元");
                            }

                            VerticaTextView.setTextList(titleList);
//                            VerticaTextView.notifyAll();
//                            VerticaTextView.startAutoScroll();

                        }else {


                        }


                    }
                });


            }
        });


    }


    private void initView() {
        Multiple_Money = (ClearEditText) findViewById(R.id.Multiple_Money);
        Text_betting = (Button) findViewById(R.id.Text_betting);
        left_team = (TextView) findViewById(R.id.left_team);
        right_team = (TextView) findViewById(R.id.right_team);
        Text_prour = (TextView) findViewById(R.id.Text_prour);
        Text_football_data = (TextView) findViewById(R.id.Text_football_data);
        dan_layout = (LinearLayout) findViewById(R.id.dan_layout);
        Text_loading = (TextView) findViewById(R.id.Text_loading);
        layout_scroll = (LinearLayout) findViewById(R.id.layout_scroll);
        Home_Refresh  = (SwipeRefreshLayout) findViewById(R.id.Home_Refresh);
        newsRecycleriView = (RecyclerView) findViewById(R.id.newsRecycleriView);
        Image_lottery = (ImageView) findViewById(R.id.Image_lottery);
        match = (Button) findViewById(R.id.match);
        news = (Button) findViewById(R.id.news);
        layout_football = (LinearLayout) findViewById(R.id.layout_football);
        layout_baskball = (LinearLayout) findViewById(R.id.layout_baskball);
        mainRollPagerView = (RollPagerView) findViewById(R.id.mainRollPagerView);
        Button_ping = (Button) findViewById(R.id.Button_ping);
        Button_loser = (Button) findViewById(R.id.Button_loser);
        Button_wushi = (Button) findViewById(R.id.Button_wushi);
        Button_erbai = (Button) findViewById(R.id.Button_erbai);
        Button_wubai = (Button) findViewById(R.id.Button_wubai);
        Button_victory = (Button) findViewById(R.id.Button_victory);
        VerticaTextView = (VerticalTextview) findViewById(R.id.VerticaTextView);
        Button_victory.setOnClickListener(this);
        Button_ping.setOnClickListener(this);
        Text_betting.setOnClickListener(this);
        layout_football.setOnClickListener(this);
        layout_baskball.setOnClickListener(this);
        Button_loser.setOnClickListener(this);
        Button_wushi.setOnClickListener(this);
        Button_erbai.setOnClickListener(this);
        dan_layout.setOnClickListener(this);
        Button_wubai.setOnClickListener(this);
        news.setOnClickListener(this);
        match.setOnClickListener(this);
        Image_lottery.setOnClickListener(this);
        newsRecycleriView.setLayoutManager(new LinearLayoutManager(getContext()));
        //这是后来要删除的集合，所以就不用在strings里面去写了
        VerticaTextView.setText(11, 0, Color.parseColor("#C08FA6"));//设置属性
        VerticaTextView.setTextStillTime(3000);//设置停留时长间隔
        VerticaTextView.setAnimTime(300);//设置进入和退出的时间间隔
        VerticaTextView.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(getContext(), "点击了 : " + titleList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        newsRecycleriView.setNestedScrollingEnabled(false);

        Multiple_Money.addTextChangedListener(textWatcher);

        Home_Refresh.setColorSchemeResources(
                R.color.login_red, R.color.login_red, R.color.login_red, R.color.login_red);
        Home_Refresh.setOnRefreshListener(this);
        getVerticaList();


    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(Multiple_Money.getText().toString().trim().equals("")){
                edit_flag=0;
            }else {
                flag_all=3;
                change(Button_wushi,Button_erbai,Button_wubai);
//                BigInteger n = new BigInteger(Multiple_Money.getText().toString().trim());
                Double n = Double.parseDouble(Multiple_Money.getText().toString().trim());
                Log.e("YZS",n%2+"");
                if(n%2==1){
                    edit_flag=0;
                    ToastUtil.showToast1(getActivity(),"请输入2的倍数");
                }else {
                    edit_flag=1;
                    money_flag=4;

                    double poer = Double.parseDouble(single_odds.get(bett).getOdds()) * Double.parseDouble(Multiple_Money.getText().toString().trim());
                    NumberFormat nf = new DecimalFormat("#.##");
                    String format = nf.format(poer);
                    Text_prour.setText(format);

                }

            }




        }
    };

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...

                recycl();

                showbanan();



                //指示器4兄弟,也就是那小圆点
       /* mRollViewPager1.setHintView(new IconHintView(this,R.drawable.point_focus,R.drawable.point_normal));//自定义指示器
        mRollViewPager1.setHintView(new TextHintView(this));//设置指示器为文字
        mRollViewPager1.setHintView(null);//隐藏指示器*/
                mainRollPagerView.setHintView(new ColorPointHintView(getContext(), Color.WHITE, Color.GRAY));//设置指示器颜色


                mainRollPagerView.setPlayDelay(3000);   //设置播放时间间隔

                mainRollPagerView.setAnimationDurtion(500);   //设置透明度

                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 200);
            }
        }).start();
    }

    private void showbanan() {
        imgs = new ArrayList<>();

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.GetAsynMap(Url.baseUrl+"app/operation/getInquireList", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
            final String result = response.body().string();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        int code = jsonObject.getInt("code");
                        String msg = jsonObject.getString("msg");
                        if(code==10000){


                            BananBean bananBean = JSON.parseObject(result,BananBean.class);

                            for (int i = 0; i < bananBean.getData().size(); i++) {
                                imgs.add(bananBean.getData().get(i).getImg_location());

                            }
                            mainRollPagerView.setAdapter(new ImageNormalAdapter());//设置适配器



                        }else if(code==10004){
                            MainActivity.intentsat.finish();
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            SpUtil spUtil = new SpUtil(getActivity(),"token");
                            spUtil.putString("token","");
                            ToastUtil.showToast(getActivity(),msg+"");

                        }else {
                            Toast.makeText(getActivity(), msg+"", Toast.LENGTH_SHORT).show();

                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });

            }
        });


    }


    public void recycl(){
        list.clear();

        //有接口时在这里做更新recyclerview的操作
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("type",flag+"");

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/information/getBallInformationList", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                MainInfomationBean mainInfomationBean = JSON.parseObject(result,MainInfomationBean.class);
                                list.addAll(mainInfomationBean.getData());
                                newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getContext(),list,flag);

                                newsRecycleriView.setAdapter(newsRecyclerViewAdapter);

                            }else {

                                Toast.makeText(getActivity(), msg+"", Toast.LENGTH_SHORT).show();
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Button_victory:

                bett=0;
               change(Button_victory,Button_ping,Button_loser);


                break;
            case R.id.Button_ping:

                bett=1;

                change(Button_ping,Button_victory,Button_loser);

                break;
            case R.id.Button_loser:

                bett=2;

                change(Button_loser,Button_ping,Button_victory);

                break;
            case R.id.Button_wushi:

                money_flag=0;
                change(Button_wushi,Button_erbai,Button_wubai);

                break;
            case R.id.Button_erbai:

                money_flag=1;

                change(Button_erbai,Button_wushi,Button_wubai);

                break;
            case R.id.Button_wubai:

                money_flag=2;

                change(Button_wubai,Button_wushi,Button_erbai);

                break;
            case R.id.news:
                changeRecyclerView(news,match);
                flag=0;

                recycl();
                Toast.makeText(getContext(), "这是足球咨询", Toast.LENGTH_SHORT).show();

                break;
            case R.id.match:
                changeRecyclerView(match,news);
                flag=1;
                recycl();
                Toast.makeText(getContext(), "这是篮球咨询", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Image_lottery:
                startActivity(new Intent(getContext(),LotteryActivity.class));

                break;
            case R.id.layout_football:
                Intent intent = new Intent(getContext(),BettingActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
                break;
            case R.id.layout_baskball:
                startActivity(new Intent(getContext(),BasketBallMixedActivity.class));

                break;
            case R.id.dan_layout:
                Intent intent_dan = new Intent(getContext(),BettingActivity.class);
                intent_dan.putExtra("flag",2);
                startActivity(intent_dan);
                break;
            case R.id.Text_betting:


                if(money_flag==4&&edit_flag==0){
                    ToastUtil.showToast1(getActivity(),"请输入金额");


                }else {
                    submit();

                }


                break;
        }

    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void changeRecyclerView(Button one,Button two){
        one.setBackground(getResources().getDrawable(R.drawable.main_radio_righttop_shape));
        two.setBackground(getResources().getDrawable(R.color.transparent));
        one.setTextColor(getResources().getColor(R.color.login_red));
        two.setTextColor(getResources().getColor(R.color.font_black));

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void change(Button one,Button two,Button three){
        if(flag_all==0) {
            Multiple_Money.setText("");
            one.setBackground(getResources().getDrawable(R.drawable.main_victory_shape_red));
            one.setTextColor(getResources().getColor(R.color.white));

            if(edit_flag==0){
               Double zhi = 0.00;
                switch (money_flag){
                    case 0:
                        zhi=50.00;

                        break;
                    case 1:
                        zhi=200.00;

                        break;
                    case 2:
                        zhi=500.00;
                        break;
                }
                double poer = Double.parseDouble(single_odds.get(bett).getOdds()) * zhi;
                NumberFormat nf = new DecimalFormat("#.##");
                String format = nf.format(poer);
                Text_prour.setText(format);
            }else {
                double poer = Double.parseDouble(single_odds.get(bett).getOdds()) * Double.parseDouble(Multiple_Money.getText().toString().trim());
                NumberFormat nf = new DecimalFormat("#.##");
                String format = nf.format(poer);
                Text_prour.setText(format);
            }

        }else if(flag_all==3){

            one.setBackground(getResources().getDrawable(R.drawable.main_victory_shape));
            one.setTextColor(getResources().getColor(R.color.login_red));
            two.setBackground(getResources().getDrawable(R.drawable.main_victory_shape));
            three.setBackground(getResources().getDrawable(R.drawable.main_victory_shape));
            two.setTextColor(getResources().getColor(R.color.login_red));
            three.setTextColor(getResources().getColor(R.color.login_red));
            flag_all=0;
        }


        two.setBackground(getResources().getDrawable(R.drawable.main_victory_shape));
        three.setBackground(getResources().getDrawable(R.drawable.main_victory_shape));
        two.setTextColor(getResources().getColor(R.color.login_red));
        three.setTextColor(getResources().getColor(R.color.login_red));


    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(), "正在刷新", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                Home_Refresh.setRefreshing(false);
                getVerticaList();
                recycl();
                showbanan();
                Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();

            }
        },2000);


    }

    //适配器
    private class ImageNormalAdapter extends StaticPagerAdapter {


        //本地图片资源
//        String[] imgs = new String[]{"http://103.9.195.242/banner/banner1.png","http://103.9.195.242/banner/banner2.jpg"};

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(getActivity()).load(imgs.get(position)).into(view);
            return view;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }
    }


    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        VerticaTextView.startAutoScroll();

    }

    @Override
    protected void onPauseLazy() {
        super.onPauseLazy();
        VerticaTextView.stopAutoScroll();

    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        handler.sendEmptyMessage(1);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            Text_loading.setVisibility(View.GONE);
            Home_Refresh.setVisibility(View.VISIBLE);
            layout_scroll.setVisibility(View.VISIBLE);
        }
    };


    private void submit() {

        dialog = new Dialog(getActivity(),R.style.dialog);
        dialog.setContentView(R.layout.dialog_sure_mixed);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        getActivity().getWindow().setBackgroundDrawable(new BitmapDrawable());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);


        dialog.show();
        TextView text_bei = dialog.findViewById(R.id.text_bei);
        TextView pop_money = dialog.findViewById(R.id.pop_money);
        TextView pop_bunch = dialog.findViewById(R.id.pop_bunch);
        Button button_cancle = dialog.findViewById(R.id.button_cancle);

            if(edit_flag==0){
                switch (money_flag){
                    case 0:
                        pop_money.setText("50元");
                        multiple = 50/2;
                        text_bei.setText(multiple+"倍");
                        pop_bunch.setText("1注");
                        break;
                    case 1:
                        pop_money.setText("200元");
                        multiple = 200/2;
                        text_bei.setText(multiple+"倍");
                        pop_bunch.setText("1注");
                        break;
                    case 2:
                        pop_money.setText("500元");

                        multiple = 500/2;
                        text_bei.setText(multiple+"倍");
                        pop_bunch.setText("1注");
                        break;

                }




            }else {
                pop_money.setText(Multiple_Money.getText().toString().trim()+"元");
                multiple = Integer.parseInt(Multiple_Money.getText().toString().trim())/2;
                text_bei.setText(Integer.parseInt(Multiple_Money.getText().toString().trim())/2+"倍");
                pop_bunch.setText("1注");

            }


//            pop_money.setText(Integer.parseInt(format)*Integer.parseInt(edit_Multiple.getText().toString())*2+"元");


        Button submit_order =  dialog.findViewById(R.id.submit_order);
        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long nowTime = System.currentTimeMillis();
                if (nowTime - mLastClickTime > TIME_INTERVAL) {
                    suborder();

                    // do something
                    mLastClickTime = nowTime;
                } else {
                    ToastUtil.showToast1(getActivity(),"不要重复点击");
                }
            }
        });
    }


    private void suborder(){
        try {






            // 创建json对象
            JSONObject jsonObject = new JSONObject();
            org.json.JSONArray jsonArray1 = new org.json.JSONArray();
            List<JSONObject> list_jsonobject = new ArrayList<>();

                JSONObject jsonObject_son = new JSONObject();

                org.json.JSONArray jsonArray_son = new org.json.JSONArray();

                    jsonArray_son.put(single_odds.get(bett).getGame_odds_id());

                jsonObject_son.put("game_id",game_id);
                jsonObject_son.put("bet_id",jsonArray_son);

                list_jsonobject.add(jsonObject_son);

            for (int i = 0; i <list_jsonobject.size() ; i++) {

                jsonArray1.put(list_jsonobject.get(i));

            }

            org.json.JSONArray jsonArray = getlist_guan();

            jsonObject.put("multiple",multiple+"");
            jsonObject.put("bunch",jsonArray);
            jsonObject.put("game_type","0");
            jsonObject.put("game_bet",jsonArray1);
            jsonObject.put("theory_bonus","预计奖金:"+Text_prour.getText().toString());
            String data = jsonObject.toString();
            Log.e("YZS",data.toString());

            OkHttpClient client = new OkHttpClient();


            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), data);
            final Request request = new Request.Builder()
                    .url(Url.baseUrl+"app/order/addOrder")
                    .addHeader("token", Content.ToKen)
                    .post(body)

                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String result = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {


                                CodeBean codeBean = JSON.parseObject(result,CodeBean.class);
                                if(codeBean.getCode()==10000){
                                    dialog.dismiss();
                                    ToastUtil.showToast(getActivity(),codeBean.getMsg()+"");
                                }else if(codeBean.getCode()==10004){
                                    MainActivity.intentsat.finish();
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                    SpUtil spUtil = new SpUtil(getActivity(),"token");
                                    spUtil.putString("token","");
                                    ToastUtil.showToast(getActivity(),codeBean.getMsg()+"");

                                }else {
                                    ToastUtil.showToast(getActivity(),codeBean.getMsg()+"");

                                }
                            }catch (Exception e){
                                ToastUtil.showToast(getActivity(),"返回数据有误");

                            }

                        }
                    });
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    //获取几串几的集合
    public org.json.JSONArray getlist_guan(){
        org.json.JSONArray jsonArray = new org.json.JSONArray();

        jsonArray.put(1);



        return jsonArray;
    }
}
