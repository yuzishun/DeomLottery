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
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Basefragment;
import com.example.yuzishun.newdeom.base.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends LazyFragment implements View.OnClickListener {

    private TextView title_text;
    private LinearLayout image_back;
    private WebView webView;
    private TextView Text_loading;
    private LinearLayout layout_ye;


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
        webView = (WebView) findViewById(R.id.webView);
        Text_loading = (TextView) findViewById(R.id.Text_loading);
        layout_ye = (LinearLayout) findViewById(R.id.layout_ye);
        webView.loadUrl("http://192.168.1.9/static/ball/instant/fb_index.html");

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
