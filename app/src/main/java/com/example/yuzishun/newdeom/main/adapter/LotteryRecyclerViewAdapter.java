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
 * Created by yuzishun on 2019/5/5.
 */

/***
 * 中奖页面的列表适配器
 *
 */
public class LotteryRecyclerViewAdapter extends RecyclerView.Adapter<LotteryRecyclerViewAdapter.ViewHolder> {

    private Context context;

    private List<String> list = new ArrayList<>();

    public LotteryRecyclerViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;


    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position);
    }
    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }
    @Override
    public LotteryRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.lottery_recyclerview_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(LotteryRecyclerViewAdapter.ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerViewListener != null) {
                    mOnRecyclerViewListener.onItemClick(position);
                }
            }
        });

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
