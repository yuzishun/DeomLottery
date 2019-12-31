package com.example.yuzishun.deomlottery.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.model.PayTestBean;

import java.util.List;

/**
 * Created by apple on 2019/8/13.
 */

public class PayListAdapter extends RecyclerView.Adapter<PayListAdapter.ViewHolder>{
    private Context context;
    private List<PayTestBean.DataBean> list;

    public PayListAdapter(Context context, List<PayTestBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;


    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int pay_type);
    }
    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }

    @Override
    public PayListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_paylist,parent,false));
    }

    @Override
    public void onBindViewHolder(PayListAdapter.ViewHolder holder, int position) {


        holder.pay_text.setText(list.get(position).getPay_name());
        Glide.with(context).load(list.get(position).getImg()).into(holder.image1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳到订单详情
                if (mOnRecyclerViewListener != null) {
                    mOnRecyclerViewListener.onItemClick(list.get(position).getTag());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pay_text;
        ImageView image1;
        public ViewHolder(View itemView) {
            super(itemView);
            pay_text = itemView.findViewById(R.id.pay_text);
            image1 = itemView.findViewById(R.id.image1);
        }
    }
}
