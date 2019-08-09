package com.example.yuzishun.newdeom.documentary.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.BaseActivity;
import com.example.yuzishun.newdeom.documentary.adapter.EveryListRecyclerViewAdapter;
import com.example.yuzishun.newdeom.model.BonusListBean;
import com.example.yuzishun.newdeom.model.DocumentListBean;
import com.example.yuzishun.newdeom.model.HitBean;
import com.example.yuzishun.newdeom.model.MyConcernBean;
import com.example.yuzishun.newdeom.model.ProfitBean;
import com.example.yuzishun.newdeom.net.OkhttpUtlis;
import com.example.yuzishun.newdeom.net.Url;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EveryListActivity extends BaseActivity implements View.OnClickListener {
    private TextView title_text;
    private LinearLayout image_back;
    private int flag;
    private TextView text_vi;
    private RecyclerView List_RecyclerView;
    private EveryListRecyclerViewAdapter everyListRecyclerViewAdapter;
    @Override
    public int intiLayout() {
        return R.layout.activity_every_list;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        title_text = (TextView) findViewById(R.id.title_text);
        text_vi = findViewById(R.id.text_vi);
        image_back = (LinearLayout) findViewById(R.id.image_back);
        List_RecyclerView = findViewById(R.id.List_RecyclerView);
        List_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add("");
        }

        image_back.setOnClickListener(this);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag",0);
        if(flag==1){
            title_text.setText("盈利榜");

        }else if(flag==2){
            title_text.setText("命中榜");

        }else if(flag==3){
            title_text.setText("奖金榜");

        }else if(flag==4){
            title_text.setText("我的关注");

        }








    }

    @Override
    protected void onResume() {
        super.onResume();
        request();

    }

    private void request() {
        String url="";
        switch (flag){
            case 1:
                url="app/example/profit";
                break;
            case 2:
                url="app/example/shoot";

                break;
            case 3:
                url="app/example/bonus";

                break;
            case 4:
                url="app/example/attention";

                break;

        }

        HashMap<String,String> hashMap = new HashMap<>();
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + url, hashMap, new Callback() {
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
                            DocumentListBean documentListBean = new DocumentListBean();
                            documentListBean.setFlag(flag);
                            final List<DocumentListBean.Item_list> list = new ArrayList<>();
                            if(code==10000){

                                switch (flag){
                                    case 1:
                                        //ProfitBean

                                        ProfitBean profitBean = JSON.parseObject(result,ProfitBean.class);
                                        for (int i = 0; i <profitBean.getData().size() ; i++) {

                                            DocumentListBean.Item_list item_list = new DocumentListBean.Item_list();
                                            item_list.setImg_head(profitBean.getData().get(i).getImg_head());
                                            item_list.setUname(profitBean.getData().get(i).getUname());
                                            item_list.setEarnings(profitBean.getData().get(i).getEarnings());
                                            item_list.setUser_id(profitBean.getData().get(i).getUser_id());
                                            item_list.setHit(profitBean.getData().get(i).getHit());

                                            list.add(item_list);
                                        }

                                        documentListBean.setList(list);



                                        break;
                                    case 2:

                                        //HitBean

                                        HitBean hitBean = JSON.parseObject(result,HitBean.class);
                                        for (int i = 0; i <hitBean.getData().size() ; i++) {

                                            DocumentListBean.Item_list item_list = new DocumentListBean.Item_list();
                                            item_list.setImg_head(hitBean.getData().get(i).getImg_head());
                                            item_list.setUname(hitBean.getData().get(i).getUname());
                                            item_list.setUser_id(hitBean.getData().get(i).getUser_id());
                                            item_list.setHit(hitBean.getData().get(i).getHit());
                                            item_list.setOrder_win(hitBean.getData().get(i).getOrder_win());
                                            item_list.setOrder_sum(hitBean.getData().get(i).getOrder_sum());

                                            list.add(item_list);
                                        }

                                        documentListBean.setList(list);

                                        break;
                                    case 3:


                                        //BounsListBean

                                        BonusListBean bonusListBean = JSON.parseObject(result,BonusListBean.class);
                                        for (int i = 0; i <bonusListBean.getData().size() ; i++) {

                                            DocumentListBean.Item_list item_list = new DocumentListBean.Item_list();
                                            item_list.setImg_head(bonusListBean.getData().get(i).getImg_head());
                                            item_list.setUname(bonusListBean.getData().get(i).getUname());
                                            item_list.setUser_id(bonusListBean.getData().get(i).getUser_id());
                                            item_list.setBonus_price(bonusListBean.getData().get(i).getBonus_price());
                                            item_list.setOrder_win(bonusListBean.getData().get(i).getOrder_win());
                                            item_list.setOrder_sum(bonusListBean.getData().get(i).getOrder_sum());

                                            list.add(item_list);
                                        }

                                        documentListBean.setList(list);

                                        break;
                                    case 4:

                                        //MyConcernBean
                                        MyConcernBean myConcernBean = JSON.parseObject(result,MyConcernBean.class);
                                        for (int i = 0; i <myConcernBean.getData().size() ; i++) {

                                            DocumentListBean.Item_list item_list = new DocumentListBean.Item_list();
                                            item_list.setImg_head(myConcernBean.getData().get(i).getImg_head());
                                            item_list.setUname(myConcernBean.getData().get(i).getUname());
                                            item_list.setUser_id(myConcernBean.getData().get(i).getUser_id());

                                            list.add(item_list);
                                        }

                                        documentListBean.setList(list);

                                        break;


                                }


                                if(documentListBean.getList().size()==0){

                                    List_RecyclerView.setVisibility(View.GONE);
                                    text_vi.setVisibility(View.VISIBLE);

                                }else {
                                    List_RecyclerView.setVisibility(View.VISIBLE);
                                    text_vi.setVisibility(View.GONE);
                                everyListRecyclerViewAdapter = new EveryListRecyclerViewAdapter(EveryListActivity.this,documentListBean,flag);
                                List_RecyclerView.setAdapter(everyListRecyclerViewAdapter);
                                everyListRecyclerViewAdapter.setOnRecyclerViewListener(new EveryListRecyclerViewAdapter.OnRecyclerViewListener() {
                                    @Override
                                    public void onItemClick(int position,String user_id) {
                                        Intent intent = new Intent(EveryListActivity.this, OkamiActivity.class);
                                        intent.putExtra("user_id",user_id);

                                        startActivity(intent);
                                    }
                                });

                                }

                            }else {

                                ToastUtil.showToast1(EveryListActivity.this,msg);
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
