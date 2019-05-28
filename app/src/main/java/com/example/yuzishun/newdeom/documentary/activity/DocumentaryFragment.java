package com.example.yuzishun.newdeom.documentary.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Basefragment;
import com.example.yuzishun.newdeom.base.LazyFragment;
import com.example.yuzishun.newdeom.documentary.adapter.DocumHotGridViewAdapter;
import com.example.yuzishun.newdeom.documentary.adapter.DocumRecyclerViewAdapter;
import com.example.yuzishun.newdeom.documentary.custom.MyGridView;
import com.example.yuzishun.newdeom.main.custom.MyRecyclerView;
import com.example.yuzishun.newdeom.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 跟单的首界面
 */
public class DocumentaryFragment extends LazyFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private SwipeRefreshLayout Document_Refresh;
    private TextView title_text;
    private LinearLayout image_back;
    private MyGridView Hot_GridView;
    private MyRecyclerView Document_RecyclerView;
    private TextView Text_loading;
    private LinearLayout layout_document;
    private String[] list1=new String[]{"林俊杰","林俊杰","林俊杰","林俊杰","林俊杰","林俊杰","林俊杰","林俊杰","林俊杰","林俊杰","林俊杰"};
    private ArrayList<String> list = new ArrayList<>();
    private LinearLayout layout_profitlist,layout_biggolist,layout_moneylist,layout_followlist;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_documentary);

        initView();
        initData();
    }

    private void initView() {
        Document_Refresh = (SwipeRefreshLayout) findViewById(R.id.Document_Refresh);
        title_text = (TextView) findViewById(R.id.title_text);
        layout_profitlist = (LinearLayout) findViewById(R.id.layout_profitlist);
        layout_biggolist = (LinearLayout) findViewById(R.id.layout_biggolist);
        layout_moneylist = (LinearLayout) findViewById(R.id.layout_moneylist);
        layout_followlist = (LinearLayout) findViewById(R.id.layout_followlist);
        image_back = (LinearLayout) findViewById(R.id.image_back);
        Hot_GridView = (MyGridView) findViewById(R.id.Hot_GridView);
        Text_loading = (TextView) findViewById(R.id.Text_loading);
        Document_RecyclerView = (MyRecyclerView) findViewById(R.id.Document_RecyclerView);
        layout_document = (LinearLayout) findViewById(R.id.layout_document);
        Document_Refresh.setOnRefreshListener(this);
        image_back.setVisibility(View.GONE);
        title_text.setText("跟单大厅");
        Document_RecyclerView.setNestedScrollingEnabled(false);
        layout_profitlist.setOnClickListener(this);
        layout_biggolist.setOnClickListener(this);
        layout_moneylist.setOnClickListener(this);
        layout_followlist.setOnClickListener(this);


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
                Document_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                DocumHotGridViewAdapter documHotGridViewAdapter = new DocumHotGridViewAdapter(getContext(),list);

                Hot_GridView.setAdapter(documHotGridViewAdapter);
                Hot_GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(getActivity(),OkamiActivity.class));

                    }
                });
                DocumRecyclerViewAdapter documRecyclerViewAdapter = new DocumRecyclerViewAdapter(getContext(),list);
                Document_RecyclerView.setAdapter(documRecyclerViewAdapter);
                //完成后，通知主线程更新UI
                handler.sendEmptyMessageDelayed(1, 200);
            }
        }).start();


    }

    @Override
    public void onRefresh() {
        ToastUtil.showToast1(getActivity(),"正在刷新");

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                Document_Refresh.setRefreshing(false);
                ToastUtil.showToast1(getActivity(),"刷新完成");


            }
        },5000);

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
        }
    }

    private void jump(int flag){
        Intent intent = new Intent(getContext(),EveryListActivity.class);
        intent.putExtra("flag",flag);
        startActivity(intent);
    }

}
