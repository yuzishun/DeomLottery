package com.example.yuzishun.newdeom.documentary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.documentary.adapter.SearchAdapter;
import com.example.yuzishun.newdeom.login.custom.ClearEditText;
import com.example.yuzishun.newdeom.model.SearchBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.Search_RecyclerView)
    RecyclerView Search_RecyclerView;
    @BindView(R.id.image_back)
    LinearLayout  image_back;
    @BindView(R.id.title_text)
    ClearEditText clearEditText;
    private SearchBean searchBean;
    @BindView(R.id.Text_loading)
    TextView Text_loading;
    private List<SearchBean.DataBean> data;
    @Override
    public int intiLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);
        image_back.setOnClickListener(this);
        Search_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        clearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(clearEditText.getText().toString().trim().equals("")){
                    Search_RecyclerView.setVisibility(View.GONE);
                    Text_loading.setVisibility(View.VISIBLE);
                }else {
                    Search_RecyclerView.setVisibility(View.VISIBLE);
                    Text_loading.setVisibility(View.GONE);

                    request(clearEditText.getText().toString().trim());


                }


            }
        });


    }

    private void request(String name) {

//        data.clear();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name",name);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/god/searchGod", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);

                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                SearchBean searchBean = JSON.parseObject(result,SearchBean.class);

                                if(searchBean.getData().size()==0){
                                    Search_RecyclerView.setVisibility(View.GONE);
                                    Text_loading.setVisibility(View.VISIBLE);


                                }else {
                                    Search_RecyclerView.setVisibility(View.VISIBLE);
                                    Text_loading.setVisibility(View.GONE);
                                    data = searchBean.getData();
                                    SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this,data);
                                    Search_RecyclerView.setAdapter(searchAdapter);


                                }


                            }else {
                                ToastUtil.showToast1(SearchActivity.this,msg+"");
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
