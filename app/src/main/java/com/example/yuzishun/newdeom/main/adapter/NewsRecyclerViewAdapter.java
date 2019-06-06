package com.example.yuzishun.newdeom.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.main.activity.InfomationWebViewActivity;
import com.example.yuzishun.newdeom.model.MainInfomationBean;

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
    private List<MainInfomationBean.DataBean> list = new ArrayList<>();
    private int type;
    public NewsRecyclerViewAdapter(Context context, List<MainInfomationBean.DataBean> list,int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    @Override
    public NewsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_newsrecyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(NewsRecyclerViewAdapter.ViewHolder holder, final int position) {

        holder.infomatio_title.setText(list.get(position).getTitle());
        holder.infomatio_data.setText(list.get(position).getCreate_time());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, InfomationWebViewActivity.class);
                intent.putExtra("type",type);
                intent.putExtra("info_no",list.get(position).getInfo_no());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView infomatio_title;
        TextView infomatio_data;
        public ViewHolder(View itemView) {
            super(itemView);
            infomatio_title = itemView.findViewById(R.id.infomatio_title);
            infomatio_data = itemView.findViewById(R.id.infomatio_data);
        }
    }
}
