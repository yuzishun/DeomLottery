package com.example.yuzishun.newdeom.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.model.DetailedBean;
import com.example.yuzishun.newdeom.my.activity.Details_mingxiActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/13.
 */

public class Detailed_RecyclerView_Adapter extends RecyclerView.Adapter<Detailed_RecyclerView_Adapter.ViewHolder> {
    private Context context;
    private List<DetailedBean.DataBean> data = new ArrayList<>();


    public Detailed_RecyclerView_Adapter(Context context, List<DetailedBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public Detailed_RecyclerView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.detailed_recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Detailed_RecyclerView_Adapter.ViewHolder holder, int position) {


        switch (data.get(position).getDeal_type()){
            case 0:

                holder.text_state.setText("充值");
                break;
            case 1:
                holder.text_state.setText("提现");

                break;
            case 2:
                holder.text_state.setText("投注");

                break;
            case 3:
                holder.text_state.setText("中奖金额");

                break;
            case 4:
                holder.text_state.setText("奖励金额");

                break;
            case 5:
                holder.text_state.setText("退款");

                break;
            case 6:
                holder.text_state.setText("跟单佣金");

                break;
            case 7:
                holder.text_state.setText("下分");

                break;
            case 8:
                holder.text_state.setText("上分");

                break;
        }

        holder.text_money.setText("余额"+data.get(position).getUser_account());
        holder.text_data.setText(data.get(position).getCreate_time()+"");

        if(data.get(position).getDetail_type()==1){
            holder.text_detailed.setTextColor(context.getResources().getColor(R.color.login_red));
            holder.text_detailed.setText("+"+data.get(position).getDeal_price());

        }else {
            holder.text_detailed.setTextColor(context.getResources().getColor(R.color.font_green));
            holder.text_detailed.setText("-"+data.get(position).getDeal_price());
        }
       // 交易类型【0：充值，1：提现，2：投注，3：中奖金额，4：奖励金额，5：退款，6：跟单佣金，7：下分，8：上分】
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, Details_mingxiActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_state,text_detailed,text_data,text_money;
        public ViewHolder(View itemView) {
            super(itemView);
            text_state = itemView.findViewById(R.id.text_state);
            text_detailed = itemView.findViewById(R.id.text_detailed);
            text_data = itemView.findViewById(R.id.text_data);
            text_money = itemView.findViewById(R.id.text_money);

        }
    }
}
