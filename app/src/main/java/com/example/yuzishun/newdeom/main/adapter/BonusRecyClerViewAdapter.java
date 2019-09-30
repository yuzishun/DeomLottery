package com.example.yuzishun.newdeom.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.documentary.custom.AmountView;
import com.example.yuzishun.newdeom.model.BonusBean;

import java.util.List;

/**
 * Created by apple on 2019/9/24.
 */

public class BonusRecyClerViewAdapter extends RecyclerView.Adapter<BonusRecyClerViewAdapter.ViewHolder> {

    private Context context;
    private List<BonusBean> list;

    public BonusRecyClerViewAdapter(Context context, List<BonusBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BonusRecyClerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_bonus_list_item,parent,false));

    }

    @Override
    public void onBindViewHolder(BonusRecyClerViewAdapter.ViewHolder holder, int position) {
        holder.text_money.setText(list.get(position).getOneBetBounsMoney()*list.get(position).getNumber()+"");
        holder.amountView.setGoods_storage(99999);
        holder.amountView.setTextd(list.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_money;
        AmountView amountView;
        public ViewHolder(View itemView) {
            super(itemView);
            text_money = itemView.findViewById(R.id.text_money);
            amountView = itemView.findViewById(R.id.amountView);
        }
    }
}
