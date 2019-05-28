package com.example.yuzishun.newdeom.score.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Basefragment;
import com.example.yuzishun.newdeom.base.LazyFragment;
import com.example.yuzishun.newdeom.score.utils.ContentFragmentScore;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends LazyFragment implements View.OnClickListener {

    private TextView title_text;
    private LinearLayout image_back;
    private ViewPager ViewPager_score;
    private TabLayout tabLayout;
    private TextView Text_loading;
    private LinearLayout layout_ye;
    private List<String> datas = new ArrayList<>();//页卡标题集合
    private String[] strings = {"即时","完场","赛程","关注"};
    private List<Fragment> fragments;


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_score);
        initview();
        initdata();

    }

    private void initview() {
        title_text = (TextView) findViewById(R.id.title_text);
        image_back = (LinearLayout) findViewById(R.id.image_back);
        ViewPager_score = (ViewPager) findViewById(R.id.ViewPager_score);
        tabLayout = (TabLayout) findViewById(R.id.tab_lyout);
        Text_loading = (TextView) findViewById(R.id.Text_loading);
        layout_ye = (LinearLayout) findViewById(R.id.layout_ye);
    }


   private void initdata(){

       title_text.setText("中奖");
       image_back.setVisibility(View.GONE);

       new Thread(new Runnable() {
           @Override
           public void run() {
               //异步处理加载数据
               //...

               //tablayout加载布局
               fragments = new ArrayList<>();
               tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
               for (int i = 0; i < strings.length; i++) {
                   datas.add(strings[i]);

               }
               for (int i = 0; i < datas.size(); i++) {
                   ContentFragmentScore fragment = ContentFragmentScore.newInstance();
                   fragments.add(fragment);
               }
               for (int i = 0; i < datas.size(); i++) {

                   tabLayout.addTab(tabLayout.newTab().setText(datas.get(i)));//添加tab选项

               }
               FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
                   @Override
                   public Fragment getItem(int position) {
                       return fragments.get(position);
                   }

                   @Override
                   public int getCount() {
                       return fragments.size();
                   }

                   //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
                   @Override
                   public CharSequence getPageTitle(int position) {
                       return datas.get(position);
                   }
               };

               ViewPager_score.setAdapter(mAdapter);
               tabLayout.setupWithViewPager(ViewPager_score);
               //完成后，通知主线程更新UI
               handler.sendEmptyMessageDelayed(1, 200);
           }
       }).start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


        }
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        handler.removeMessages(1);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            Text_loading.setVisibility(View.GONE);


            layout_ye.setVisibility(View.VISIBLE);
        }
    };
}
