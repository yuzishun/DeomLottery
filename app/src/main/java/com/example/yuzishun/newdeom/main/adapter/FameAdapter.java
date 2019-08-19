package com.example.yuzishun.newdeom.main.adapter;

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
import com.example.yuzishun.newdeom.documentary.activity.DocumentdetailsActivity;
import com.example.yuzishun.newdeom.documentary.activity.OkamiActivity;
import com.example.yuzishun.newdeom.model.HomeBean;
import com.example.yuzishun.newdeom.my.activity.BetteyAndWinningActivity;
import com.example.yuzishun.newdeom.my.adapter.BetteyRecyclerViewAdapter;

import java.util.List;

/**
 * Created by apple on 2019/7/22.
 */

public class FameAdapter extends RecyclerView.Adapter<FameAdapter.ViewHolder> {
    private Context context;
    private List<HomeBean.DataBean.YesterdayBean> data;

    public FameAdapter(Context context, List<HomeBean.DataBean.YesterdayBean> data) {
        this.context = context;
        this.data = data;
    }
    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;


    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int order_id);
    }
    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }



    @Override
    public FameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_fame_adapter_item,parent,false));
    }

    @Override
    public void onBindViewHolder(FameAdapter.ViewHolder holder, int position) {
        holder.money_fame.setText(data.get(position).getBonus_price()+"元");
        holder.name_fame.setText(data.get(position).getUname());
        Glide.with(context).load(data.get(position).getImg_head()).asBitmap().centerCrop().into(holder.icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (mOnRecyclerViewListener != null) {
//                    mOnRecyclerViewListener.onItemClick(data.get(position).getOrder_id());
//                }
                Intent intent = new Intent(context,DocumentdetailsActivity.class);
                //这里传0代表的是订单
                intent.putExtra("flag",0);

                intent.putExtra("order_id",Integer.parseInt(data.get(position).getOrder_id()));
                intent.putExtra("plan_id",1);

                context.startActivity(intent);

            }
        });
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentokmin = new Intent(context, OkamiActivity.class);
                //change
                intentokmin.putExtra("user_id",data.get(position).getUser_id());

                context.startActivity(intentokmin);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView money_fame,name_fame;
        ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            money_fame = itemView.findViewById(R.id.money_fame);
            name_fame = itemView.findViewById(R.id.name_fame);
        }
    }

}
