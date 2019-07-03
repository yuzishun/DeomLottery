package com.example.yuzishun.newdeom.documentary.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.documentary.activity.EveryListActivity;
import com.example.yuzishun.newdeom.documentary.activity.OkamiActivity;
import com.example.yuzishun.newdeom.model.SearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/7/1.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<SearchBean.DataBean> list = new ArrayList<>();

    public SearchAdapter(Context context, List<SearchBean.DataBean>  list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_search_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_head()).asBitmap().centerCrop().into(holder.icon_profit);
        holder.text_name.setText(list.get(position).getUname());
        holder.Text_tui.setText(list.get(position).getUser_id()+"");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, OkamiActivity.class);
                intent.putExtra("user_id",list.get(position).getUser_id()+"");

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon_profit;
        TextView text_name,Text_tui;
        public ViewHolder(View itemView) {
            super(itemView);
            icon_profit = itemView.findViewById(R.id.icon_profit);
            text_name = itemView.findViewById(R.id.text_name);
            Text_tui = itemView.findViewById(R.id.Text_tui);

        }
    }
}
