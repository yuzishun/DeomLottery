package com.example.yuzishun.deomlottery.my.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.model.OrderBean;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/13.
 */

public class BetteyRecyclerViewAdapter extends RecyclerView.Adapter<BetteyRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<OrderBean.DataBean> data = new ArrayList<>();
    private int flag;
    public BetteyRecyclerViewAdapter(Context context, List<OrderBean.DataBean> data) {
        this.context = context;
        this.data = data;
        this.flag = flag;
    }
    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;


    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position,int type,int order_id);
    }
    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }


    @Override
    public BetteyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.bettey_andwinney_recycler,parent,false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(BetteyRecyclerViewAdapter.ViewHolder holder, final int position) {

            if(data.get(position).getGame_type()==0){
                holder.gmae_type.setText("竞彩足球-普通投注");
            }else {
                holder.gmae_type.setText("竞彩篮球-普通投注");

            }


            switch (data.get(position).getOrder_status()){
                case 1:
                    holder.state_betteyandwinnit.setText("待开奖");
                    holder.state_betteyandwinnit.setTextColor(context.getResources().getColor(R.color.font_black));
                    break;
                case 2:
                    NumberFormat nfmin = new DecimalFormat("#.##");

//                    String winning_money = nfmin.format(Double.parseDouble(data.get(position).getBonus_price()) + Double.parseDouble(data.get(position).getBonus_price()) * 0.05);

                    holder.state_betteyandwinnit.setText("中"+data.get(position).getBonus_price()+"元");
                    holder.state_betteyandwinnit.setTextColor(context.getResources().getColor(R.color.login_red));

                    break;
                case 3:
                    holder.state_betteyandwinnit.setText("未中奖");
                    holder.state_betteyandwinnit.setTextColor(context.getResources().getColor(R.color.font_black));

                    break;
                case 0:
                    holder.state_betteyandwinnit.setText("待出票");
                    holder.state_betteyandwinnit.setTextColor(context.getResources().getColor(R.color.font_black));

                    break;
                case -1:
                    holder.state_betteyandwinnit.setText("已取消");
                    holder.state_betteyandwinnit.setTextColor(context.getResources().getColor(R.color.font_black));

                    break;
            }
            holder.game_time.setText(data.get(position).getCreate_time());
            holder.game_money.setText("金额消费"+data.get(position).getOrder_price()+"元");




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳到订单详情
                if (mOnRecyclerViewListener != null) {
                    mOnRecyclerViewListener.onItemClick(position,data.get(position).getGame_type(),data.get(position).getOrder_id());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView state_betteyandwinnit,gmae_type,game_time,game_money;
        public ViewHolder(View itemView) {
            super(itemView);
            state_betteyandwinnit = itemView.findViewById(R.id.state_betteyandwinnit);
            gmae_type = itemView.findViewById(R.id.gmae_type);
            game_time = itemView.findViewById(R.id.game_time);
            game_money = itemView.findViewById(R.id.game_money);

        }
    }
}
