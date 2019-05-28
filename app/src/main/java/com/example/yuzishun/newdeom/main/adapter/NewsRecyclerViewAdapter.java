package com.example.yuzishun.newdeom.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuzishun.newdeom.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/4/24.
 */

/***
 * 首页面最下面的列表适配器
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<String> list = new ArrayList<>();

    public NewsRecyclerViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public NewsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_newsrecyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(NewsRecyclerViewAdapter.ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
