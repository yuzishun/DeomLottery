package com.example.yuzishun.newdeom.my.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.base.Content;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WebViewPayActivity extends BaseActivity {
    @BindView(R.id.Webview_pay)
    WebView Webview_pay;
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;

    @Override
    public int intiLayout() {
        return R.layout.activity_web_view_pay;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("在线充值");
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String orderid = intent.getStringExtra("orderid");
        String amount = intent.getStringExtra("amount");


        Map<String, String > map = new HashMap<String, String>() ;
        map.put("token",Content.ToKen) ;

        map.put("orderid",orderid) ;
        map.put("amount",amount) ;
        String data = "orderid="+orderid+"&amount="+amount+"&token="+Content.ToKen;
        byte[] bytes = data.getBytes();

        Webview_pay.postUrl(url,bytes);
//        Webview_pay.loadUrl("http://192.168.1.9/plugins/pay/goBack.php");
        Webview_pay.setWebViewClient(new WebViewClient());
        //声明WebSettings子类
        WebSettings webSettings = Webview_pay.getSettings();
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
        Webview_pay.addJavascriptInterface(this, "Android");

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

        Webview_pay.setWebChromeClient(new Webchorom());


    }




    public class Webchorom extends WebChromeClient{
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            Log.e("YZS","onJsPrompt"+url+""+message+result.toString());

            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.e("YZS","onJsAlert"+url+""+message+result.toString());

            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            Log.e("YZS","onJsConfirm"+url+""+message+result.toString());
            return super.onJsConfirm(view, url, message, result);
        }




    }

    @Override
    public void initData() {

    }



        @JavascriptInterface
        public void comment(){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    finish();

                }
            });
        }


}
