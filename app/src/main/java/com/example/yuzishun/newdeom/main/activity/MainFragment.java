package com.example.yuzishun.newdeom.main.activity;


import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Basefragment;
import com.example.yuzishun.newdeom.base.LazyFragment;
import com.example.yuzishun.newdeom.main.adapter.NewsRecyclerViewAdapter;
import com.example.yuzishun.newdeom.model.MainInfomationBean;
import com.example.yuzishun.newdeom.model.VerticaBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.paradoxie.autoscrolltextview.VerticalTextview;

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
    private ImageView Image_lottery;
    private RecyclerView newsRecycleriView;
    private SwipeRefreshLayout Home_Refresh;
    private TextView Text_loading;
    private LinearLayout layout_scroll;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private ArrayList<String> titleList = new ArrayList<String>();
    private LinearLayout layout_football,layout_baskball;
    private List<MainInfomationBean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_main);
        initView();
        initData();
    }

    private void getVerticaList() {


        HashMap<String,String> hashMap = new HashMap<>();
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "order/getWinPriceByOrder", hashMap, new Callback() {
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
                                titleList.add("恭喜用户"+verticaBean.getData().get(i).getUname()+"**中奖"+verticaBean.getData().get(i).getBonus_price()+"元");
                            }
//                            titleList.add("我们现在是咩有中奖");
//                            titleList.add("如果有人中奖的话");
//                            titleList.add("那一定是你");
//                            titleList.add("没错，就是你，look");
//                            titleList.add("不管你是躺着还是坐着还是站着");
//                            titleList.add("你现在已经是上帝光环附体一样");
//                            titleList.add("然后你踏着七彩祥云离去");
//                            titleList.add("而后我被留在这里");
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
        layout_football.setOnClickListener(this);
        layout_baskball.setOnClickListener(this);
        Button_loser.setOnClickListener(this);
        Button_wushi.setOnClickListener(this);
        Button_erbai.setOnClickListener(this);
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



        Home_Refresh.setColorSchemeResources(
                R.color.login_red, R.color.login_red, R.color.login_red, R.color.login_red);
        Home_Refresh.setOnRefreshListener(this);
        getVerticaList();


    }
    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...




                mainRollPagerView.setAdapter(new ImageNormalAdapter());//设置适配器

                //指示器4兄弟,也就是那小圆点
       /* mRollViewPager1.setHintView(new IconHintView(this,R.drawable.point_focus,R.drawable.point_normal));//自定义指示器
        mRollViewPager1.setHintView(new TextHintView(this));//设置指示器为文字
        mRollViewPager1.setHintView(null);//隐藏指示器*/
                mainRollPagerView.setHintView(new ColorPointHintView(getContext(), Color.WHITE, Color.GRAY));//设置指示器颜色


                mainRollPagerView.setPlayDelay(3000);   //设置播放时间间隔

                mainRollPagerView.setAnimationDurtion(500);   //设置透明度

                recycl(0);

                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 200);
            }
        }).start();
    }




    public void recycl(final int flag){
        list.clear();

        //有接口时在这里做更新recyclerview的操作
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("type",flag+"");

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "information/getBallInformationList", hashMap, new Callback() {
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
               change(Button_victory,Button_ping,Button_loser);


                break;
            case R.id.Button_ping:
                change(Button_ping,Button_victory,Button_loser);

                break;
            case R.id.Button_loser:
                change(Button_loser,Button_ping,Button_victory);

                break;
            case R.id.Button_wushi:
                change(Button_wushi,Button_erbai,Button_wubai);

                break;
            case R.id.Button_erbai:
                change(Button_erbai,Button_wushi,Button_wubai);

                break;
            case R.id.Button_wubai:
                change(Button_wubai,Button_wushi,Button_erbai);

                break;
            case R.id.news:
                changeRecyclerView(news,match);
                recycl(0);
                Toast.makeText(getContext(), "这是足球咨询", Toast.LENGTH_SHORT).show();

                break;
            case R.id.match:
                changeRecyclerView(match,news);
                recycl(1);
                Toast.makeText(getContext(), "这是篮球咨询", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Image_lottery:
                startActivity(new Intent(getContext(),LotteryActivity.class));

                break;
            case R.id.layout_football:
                startActivity(new Intent(getContext(),BettingActivity.class));
                break;
            case R.id.layout_baskball:
                startActivity(new Intent(getContext(),BasketBallMixedActivity.class));

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
        one.setBackground(getResources().getDrawable(R.drawable.main_victory_shape_red));
        two.setBackground(getResources().getDrawable(R.drawable.main_victory_shape));
        three.setBackground(getResources().getDrawable(R.drawable.main_victory_shape));
        one.setTextColor(getResources().getColor(R.color.white));
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
                Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();

            }
        },2000);


    }

    //适配器
    private class ImageNormalAdapter extends StaticPagerAdapter {


        //本地图片资源
        String[] imgs = new String[]{"http://103.9.195.242/banner/banner1.jpg","http://103.9.195.242/banner/banner2.jpg"};

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(getActivity()).load(imgs[position]).into(view);
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
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

}
