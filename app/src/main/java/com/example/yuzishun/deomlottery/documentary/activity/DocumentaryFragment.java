package com.example.yuzishun.deomlottery.documentary.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.yuzishun.deomlottery.MainActivity;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.LazyFragment;
import com.example.yuzishun.deomlottery.documentary.adapter.DocumHotGridViewAdapter;
import com.example.yuzishun.deomlottery.documentary.adapter.DocumRecyclerViewAdapter;
import com.example.yuzishun.deomlottery.documentary.custom.MyGridView;
import com.example.yuzishun.deomlottery.login.activity.LoginActivity;
import com.example.yuzishun.deomlottery.main.custom.MyRecyclerView;
import com.example.yuzishun.deomlottery.model.DocumentaryBean;
import com.example.yuzishun.deomlottery.model.HotGodBean;
import com.example.yuzishun.deomlottery.net.OkhttpUtlis;
import com.example.yuzishun.deomlottery.net.Url;
import com.example.yuzishun.deomlottery.utils.SpUtil;
import com.example.yuzishun.deomlottery.utils.ToastUtil;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * 跟单的首界面
 */
public class DocumentaryFragment extends LazyFragment implements  View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    private TwinklingRefreshLayout Document_Refresh;
    private TextView title_text;
    private LinearLayout image_back;
    private MyGridView Hot_GridView;
    private MyRecyclerView Document_RecyclerView;
    private TextView Text_loading;
    private LinearLayout layout_document;
    private TextView hot_empt;
    private TextView screen;
    private TextView seat_one,seat_two;
    private LinearLayout image_seach;
    private List<DocumentaryBean.DataBean> data = new ArrayList<>();
    private DocumRecyclerViewAdapter documRecyclerViewAdapter;
    private String[] name={"方案金额","单倍金额","跟单人数","跟单金额"};
    private List<String> list_name = new ArrayList<>();
    private String[] list1=new String[]{"阿斯顿","ashun","you","篮球之夜","灭绝师太","艾瑞莉娅","跟我买","我爱刀妹","红单王者","跟着红单走",""};
    private ArrayList<String> list = new ArrayList<>();
    private int index=0,sort=0;
    private LinearLayout layout_profitlist,layout_biggolist,layout_moneylist,layout_followlist;
    private String type="",attention = "";
    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_documentary);

        initView();
        initData();
        initrecy(0,type);
        setrequest();

    }

    private void setrequest() {
        HashMap<String,String> hashMap = new HashMap<>();
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/god/getRecommendGodList", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String result = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){

                                final HotGodBean hotGodBean = JSON.parseObject(result,HotGodBean.class);
                                List<HotGodBean.DataBean> data = hotGodBean.getData();
                                DocumHotGridViewAdapter documHotGridViewAdapter = new DocumHotGridViewAdapter(getContext(),data);

                                Hot_GridView.setAdapter(documHotGridViewAdapter);
                                Hot_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        //跳转到大神首页，
                                        Intent intent = new Intent(getActivity(), OkamiActivity.class);
                                        intent.putExtra("user_id",hotGodBean.getData().get(position).getUser_id()+"");

                                        startActivity(intent);
                                    }
                                });



                            }else {
                                Toast.makeText(getActivity(), msg+"", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        });


    }


    private void initView() {
        seat_one = (TextView) findViewById(R.id.seat_one);
        seat_two = (TextView) findViewById(R.id.seat_two);
        Document_Refresh = (TwinklingRefreshLayout) findViewById(R.id.Document_Refresh);
        title_text = (TextView) findViewById(R.id.title_text);
        screen = (TextView) findViewById(R.id.screen);
        image_seach = (LinearLayout) findViewById(R.id.image_seach);
        layout_profitlist = (LinearLayout) findViewById(R.id.layout_profitlist);
        layout_biggolist = (LinearLayout) findViewById(R.id.layout_biggolist);
        layout_moneylist = (LinearLayout) findViewById(R.id.layout_moneylist);
        layout_followlist = (LinearLayout) findViewById(R.id.layout_followlist);
        image_back = (LinearLayout) findViewById(R.id.image_back);
        Hot_GridView = (MyGridView) findViewById(R.id.Hot_GridView);
        Text_loading = (TextView) findViewById(R.id.Text_loading);
        Document_RecyclerView = (MyRecyclerView) findViewById(R.id.Document_RecyclerView);
        layout_document = (LinearLayout) findViewById(R.id.layout_document);
        image_back.setVisibility(View.GONE);
        hot_empt = (TextView) findViewById(R.id.hot_empt);
        title_text.setText("跟单大厅");
        Document_RecyclerView.setNestedScrollingEnabled(false);
        layout_profitlist.setOnClickListener(this);
        layout_biggolist.setOnClickListener(this);
        layout_moneylist.setOnClickListener(this);
        screen.setOnClickListener(this);
        layout_followlist.setOnClickListener(this);
        Document_Refresh.setHeaderView(new SinaRefreshView(getActivity()));
        Document_Refresh.setBottomView(new LoadingView(getContext()));
        image_seach.setVisibility(View.VISIBLE);
        image_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                startActivity(new Intent(getContext(),SearchActivity.class));
            }
        });
        Document_Refresh.setOnRefreshListener(new TwinklingRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        index=0;
                        data.clear();

                        initrecy(0,type);
//
                        setrequest();
                        Document_Refresh.finishRefreshing();

                        Toast.makeText(getContext(), "刷新完成", Toast.LENGTH_SHORT).show();


                    }
                },1000);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        index++;
                        initrecy(1,type);
//
                        Document_Refresh.finishLoadmore();

                        Toast.makeText(getContext(), "加载完成", Toast.LENGTH_SHORT).show();


                    }
                },1000);

            }
        });



    }

    private void initrecy(final int i,String type) {

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("pagination",index+"");
        hashMap.put("game_type",type);
        hashMap.put("sort",sort+"");
        hashMap.put("attention",attention);
        OkhttpUtlis okhttpUtlis = new OkhttpUtlis();
        okhttpUtlis.PostAsynMap(Url.baseUrl + "app/order_plan/getOrderPlanList", hashMap, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int code = jsonObject.getInt("code");
                            String msg = jsonObject.getString("msg");
                            if(code==10000){
                                DocumentaryBean documentaryBean = JSON.parseObject(result,DocumentaryBean.class);

                                if(i==0){
                                    data.addAll(documentaryBean.getData());

                                if(documentaryBean.getData().size()==0){

                                    hot_empt.setVisibility(View.VISIBLE);
                                    Document_RecyclerView.setVisibility(View.GONE);

                                }else {
                                    hot_empt.setVisibility(View.GONE);
                                    Document_RecyclerView.setVisibility(View.VISIBLE);
                                    documRecyclerViewAdapter.notifyDataSetChanged();



                                }
                                }else {

                                    data.addAll(documentaryBean.getData());
                                    documRecyclerViewAdapter.notifyDataSetChanged();

                                }




                            }else if(code==10004) {
                                MainActivity.intentsat.finish();
                                startActivity(new Intent(getContext(), LoginActivity.class));
                                SpUtil spUtil = new SpUtil(getContext(),"token");
                                spUtil.putString("token","");
                                ToastUtil.showToast(getContext(),msg+"");
                            }else {
                                ToastUtil.showToast(getContext(),msg+"");

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });


            }
        });



    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //异步处理加载数据
                //...
                for (int i = 0; i < list1.length; i++) {
                    list.add(list1[i]);

                }
                Log.e("YZS",list.size()+"");

                list_name.clear();
                for (int j = 0; j < name.length; j++) {
                    list_name.add(name[j]);

                }

                Document_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                documRecyclerViewAdapter = new DocumRecyclerViewAdapter(getContext(),data);
                Document_RecyclerView.setAdapter(documRecyclerViewAdapter);
                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 200);
            }
        }).start();


    }





    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            Text_loading.setVisibility(View.GONE);
            layout_document.setVisibility(View.VISIBLE);
            Document_Refresh.setVisibility(View.VISIBLE);
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_profitlist:
                jump(1);
                break;
            case R.id.layout_biggolist:

                jump(2);

                break;
            case R.id.layout_moneylist:

                jump(3);

                break;
            case R.id.layout_followlist:
                jump(4);


                break;
            case R.id.screen:
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(getContext(), v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.document_pop, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(this);
                //显示(这一行代码不要忘记了)
                popup.show();



                break;
        }
    }

    private void jump(int flag){
        //跳转到列表页面，先不开
        Intent intent = new Intent(getContext(),EveryListActivity.class);
        intent.putExtra("flag",flag);
        startActivity(intent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.all_father:
                attention  = "";

                index=0;
                data.clear();
                initrecy(0,type);
                popup_one();

                break;

            case R.id.myguan:
                attention  = "1";
                index=0;
                data.clear();
                initrecy(0,type);
                popup_one();

                break;
            case R.id.football:
                type="0";
                index=0;
                data.clear();

                initrecy(0,type);
                popup_two();

                break;
            case R.id.basketball:

                type="1";
                index=0;
                data.clear();
                initrecy(0,type);
                popup_two();



                break;
            case R.id.all_son:
                type="";
                index=0;
                data.clear();
                initrecy(0,type);
                popup_two();

                break;
            case R.id.Popularity:
                sort=0;
                index=0;
                data.clear();
                initrecy(0,type);
                break;
            case R.id.Record:
                sort=1;
                index=0;
                data.clear();
                initrecy(0,type);
                break;
            case R.id.higt:
                sort=2;
                index=0;
                data.clear();
                initrecy(0,type);
                break;
            case R.id.low:
                sort=3;
                index=0;
                data.clear();
                initrecy(0,type);
                break;
            default:
                break;
        }
        return false;
    }
    public void popup_one(){

        //创建弹出式菜单对象（最低版本11）
        PopupMenu popup = new PopupMenu(getContext(), seat_one);//第二个参数是绑定的那个view
        //获取菜单填充器
        MenuInflater inflater = popup.getMenuInflater();
        //填充菜单
        inflater.inflate(R.menu.document_pop_one, popup.getMenu());
        //绑定菜单项的点击事件
        popup.setOnMenuItemClickListener(this);
        //显示(这一行代码不要忘记了)
        popup.show();
    }
    public void popup_two(){

        //创建弹出式菜单对象（最低版本11）
        PopupMenu popup = new PopupMenu(getContext(), seat_one);//第二个参数是绑定的那个view
        //获取菜单填充器
        MenuInflater inflater = popup.getMenuInflater();
        //填充菜单
        inflater.inflate(R.menu.document_pop_two, popup.getMenu());
        //绑定菜单项的点击事件
        popup.setOnMenuItemClickListener(this);
        //显示(这一行代码不要忘记了)
        popup.show();
    }

}
