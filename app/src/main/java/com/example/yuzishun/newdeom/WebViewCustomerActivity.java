package com.example.yuzishun.newdeom;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.utils.JSAndroid;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 客服页面
 */
public class WebViewCustomerActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.Webviews)
    WebView Webviews;
    @Override
    public int intiLayout() {
        return R.layout.activity_web_view_customer;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText(R.string.customer);
        image_back.setOnClickListener(this);
        Webviews.loadUrl("https://tb.53kf.com/code/client/87e87c80448d45d624fdee85c734facc/1?device=android");
        Webviews.getSettings().setJavaScriptEnabled(true);
        Webviews.getSettings().setDomStorageEnabled(true);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Webviews.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        Webviews.setWebViewClient(new WebViewClient());
        Webviews.addJavascriptInterface(new JSAndroid(this),"Android");
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
