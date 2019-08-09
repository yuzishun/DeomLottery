package com.example.yuzishun.newdeom.main.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.main.adapter.NewsRecyclerViewAdapter;
import com.example.yuzishun.newdeom.model.MainInfomationBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ConsultationActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.image_back)
    LinearLayout image_back;
    @BindView(R.id.football_layout)
    LinearLayout football_layout;
    @BindView(R.id.football_img)
    ImageView football_img;
    @BindView(R.id.football_text)
    TextView football_text;
    @BindView(R.id.basket_layout)
    LinearLayout basket_layout;
    @BindView(R.id.basket_img)
    ImageView basket_img;
    @BindView(R.id.basket_text)
    TextView basket_text;
    @BindView(R.id.Consulta_recyclerView)
    RecyclerView Consulta_recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private List<MainInfomationBean.DataBean> list = new ArrayList<>();
    private int flag=0;
    @Override
    public int intiLayout() {
        return R.layout.activity_consultation;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text.setText("赛事咨询");
        image_back.setOnClickListener(this);
        football_layout.setOnClickListener(this);
        basket_layout.setOnClickListener(this);
        Consulta_recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initData() {
        recycl();
    }

    public void recycl(){
        list.clear();

        //有接口时在这里做更新recyclerview的操作
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("type",flag+"");

        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/home/getBallInformationList", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                MainInfomationBean mainInfomationBean = JSON.parseObject(result,MainInfomationBean.class);
                                list.addAll(mainInfomationBean.getData());
                                newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(ConsultationActivity.this,list,flag);

                                Consulta_recyclerView.setAdapter(newsRecyclerViewAdapter);

                            }else {

                                Toast.makeText(ConsultationActivity.this, msg+"", Toast.LENGTH_SHORT).show();
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.football_layout:
                football_layout.setBackgroundColor(getResources().getColor(R.color.white));
                basket_layout.setBackgroundColor(getResources().getColor(R.color.new_gray_empt));
                basket_img.setImageDrawable(getResources().getDrawable(R.mipmap.basket_consulta_false));
                football_img.setImageDrawable(getResources().getDrawable(R.mipmap.football_consulta_true));
                football_text.setTextColor(getResources().getColor(R.color.login_red));
                basket_text.setTextColor(getResources().getColor(R.color.font_black));

                flag=0;
                recycl();

                break;
            case R.id.basket_layout:
                football_layout.setBackgroundColor(getResources().getColor(R.color.new_gray_empt));

                basket_layout.setBackgroundColor(getResources().getColor(R.color.white));
                basket_img.setImageDrawable(getResources().getDrawable(R.mipmap.basketconsultat_true));
                football_img.setImageDrawable(getResources().getDrawable(R.mipmap.football_consulta_false));
                football_text.setTextColor(getResources().getColor(R.color.font_black));
                basket_text.setTextColor(getResources().getColor(R.color.login_red));
                flag=1;
                recycl();

                break;

        }
    }
}
