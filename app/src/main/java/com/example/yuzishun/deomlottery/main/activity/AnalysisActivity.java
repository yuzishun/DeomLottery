package com.example.yuzishun.deomlottery.main.activity;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnalysisActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.analysis_webview)
    WebView analysis_webview;


    @Override
    public int intiLayout() {
        return R.layout.activity_analysis;
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        title_text.setText("分析");

        Intent intent = getIntent();
        String game_no = intent.getStringExtra("game_no");
        int flag = intent.getIntExtra("flag",0);
        if(flag ==0){
            analysis_webview.loadUrl("https://m.fox008.com/analysis/lishi?matchKey="+game_no+"&cooph5=87caizhan");

        }else {
            analysis_webview.loadUrl("https://data.fox008.com/basketball/lqHistory?matchKey="+game_no+"&&cooph5=87caizhan");

        }
        Log.e("YZS",game_no+"");

        analysis_webview.setWebViewClient(new WebViewClient());
        //声明WebSettings子类
        WebSettings webSettings = analysis_webview.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(webSettings.getMixedContentMode());
        }

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可



//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(false); // 缩放至屏幕的大小

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

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:

                finish();
                break;


        }
    }
}
