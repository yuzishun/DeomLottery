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

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Basefragment;
import com.example.yuzishun.newdeom.base.LazyFragment;
import com.example.yuzishun.newdeom.main.adapter.NewsRecyclerViewAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.paradoxie.autoscrolltextview.VerticalTextview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_main);
        initView();
        initData();
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
        titleList.add("你是天上最受宠的一架钢琴");
        titleList.add("我是丑人脸上的鼻涕");
        titleList.add("你发出完美的声音");
        titleList.add("我被默默揩去");
        titleList.add("你冷酷外表下藏着诗情画意");
        titleList.add("我已经够胖还吃东西");
        titleList.add("你踏着七彩祥云离去");
        titleList.add("我被留在这里");
        VerticaTextView.setTextList(titleList);
        VerticaTextView.setText(11, 0, Color.parseColor("#C08FA6"));//设置属性
        VerticaTextView.setTextStillTime(3000);//设置停留时长间隔
        VerticaTextView.setAnimTime(300);//设置进入和退出的时间间隔
        VerticaTextView.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "点击了 : " + titleList.get(position), Toast.LENGTH_SHORT).show();
            }
        });


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




    public void recycl(int flag){
        //有接口时在这里做更新recyclerview的操作
        List<String> list = new ArrayList<>();

        newsRecycleriView.setNestedScrollingEnabled(false);

        for (int i = 0; i <10 ; i++) {
            list.add(""+i);

        }
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getContext(),list);

        newsRecycleriView.setAdapter(newsRecyclerViewAdapter);
        Home_Refresh.setColorSchemeResources(
                R.color.login_red, R.color.login_red, R.color.login_red, R.color.login_red);
        Home_Refresh.setOnRefreshListener(this);


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
                Toast.makeText(getContext(), "这是最新咨询", Toast.LENGTH_SHORT).show();

                break;
            case R.id.match:
                changeRecyclerView(match,news);
                recycl(1);
                Toast.makeText(getContext(), "这是赛事咨询", Toast.LENGTH_SHORT).show();
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
        },5000);


    }

    //适配器
    private class ImageNormalAdapter extends StaticPagerAdapter {
        //本地图片资源
        int[] imgs = new int[]{
                R.mipmap.banner,
                R.mipmap.banner,
                R.mipmap.banner,
                R.mipmap.banner,
                R.mipmap.banner,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setImageResource(imgs[position]);
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
