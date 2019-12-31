package com.example.yuzishun.deomlottery.documentary.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.model.BonusDetailsBean;
import com.example.yuzishun.deomlottery.my.custom.MyTableTextView;

import java.util.List;

/**
 * Created by apple on 2019/10/18.
 */

public class BonusDetails_adapter extends RecyclerView.Adapter<BonusDetails_adapter.ViewHolder> {
    private Context context;
    private List<BonusDetailsBean.DataBean.SeoInfoBean> list;

    public BonusDetails_adapter(Context context, List<BonusDetailsBean.DataBean.SeoInfoBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BonusDetails_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_bonusdetails_item,parent,false));
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(BonusDetails_adapter.ViewHolder holder, int position) {
        String game_name ="";
        for (int i = 0; i < list.get(position).getGame_name().size(); i++) {
            game_name+= list.get(position).getGame_name().get(i)+"\n";
        }
        holder.tab_1.setText(game_name);
        holder.tab_2.setText(list.get(position).getPour());
        holder.tab_3.setText(list.get(position).getBonus_price()+"");
        if(list.get(position).getOdds_status()==0){
            holder.tab_3.setTextColor(context.getColor(R.color.bonus_color));

        }else {
            holder.tab_3.setTextColor(context.getColor(R.color.login_red));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyTableTextView tab_1,tab_2,tab_3;
        public ViewHolder(View itemView) {
            super(itemView);
            tab_1 = itemView.findViewById(R.id.tab_1);
            tab_2 = itemView.findViewById(R.id.tab_2);
            tab_3 = itemView.findViewById(R.id.tab_3);

        }
    }
}
