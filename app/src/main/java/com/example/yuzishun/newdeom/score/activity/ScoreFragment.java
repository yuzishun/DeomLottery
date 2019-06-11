package com.example.yuzishun.newdeom.score.activity;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
        webView.loadUrl("http://103.9.195.242/static/ball/instant/fb_index.html");
        // 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        webView.getSettings().setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
                                     @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                                         return super.shouldOverrideUrlLoading(view, request);
                                     }
                                 }
        );

//声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(webSettings.getMixedContentMode());
        }
//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可



//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setDatabaseEnabled(true);
//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");

    }





   private void initdata(){

       title_text.setText("比分");
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(webView.canGoBack()){
                    webView.goBack();
                }else {
                }

            }
        });
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
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
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
