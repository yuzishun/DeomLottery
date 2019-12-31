package com.example.yuzishun.deomlottery.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.model.PeriodBean;
import com.example.yuzishun.deomlottery.my.custom.MyTableTextView;

import java.util.ArrayList;

/**
 * Created by yuzishun on 2019/5/5.
 */

/***
 * 中奖页面的子列表适配器
 *
 */
public class RecyclerViewLotterySonAdapter extends RecyclerView.Adapter<RecyclerViewLotterySonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PeriodBean.DataBean> list = new ArrayList<>();
    public RecyclerViewLotterySonAdapter(Context context, ArrayList<PeriodBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewLotterySonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_lotterwangqi_son_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewLotterySonAdapter.ViewHolder holder, int position) {



        holder.game_name.setText(list.get(position).getGame_name());
        holder.no_number.setText(list.get(position).getGame_sequence_no());
        holder.left_name.setText(list.get(position).getGame_home_team_name());
        holder.right_name.setText(list.get(position).getGame_guest_team_name());
        holder.score.setText(list.get(position).getGame_last_score());

        holder.list_2_1.setText(list.get(position).getWin_odds().get(0).getOdds_code());
        holder.list_2_2.setText(list.get(position).getWin_odds().get(1).getOdds_code());
        holder.list_2_3.setText(list.get(position).getWin_odds().get(2).getOdds_code());
        holder.list_2_4.setText(list.get(position).getWin_odds().get(3).getOdds_code());
        holder.list_2_5.setText(list.get(position).getWin_odds().get(4).getOdds_code());

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView game_name,no_number,left_name,score,right_name;
        MyTableTextView list_1_1,list_1_2,list_1_3,list_1_4,list_1_5,list_2_1,list_2_2,list_2_3,list_2_4,list_2_5;
        public ViewHolder(View itemView) {
            super(itemView);
            game_name = itemView.findViewById(R.id.game_name);
            no_number = itemView.findViewById(R.id.no_number);
            left_name = itemView.findViewById(R.id.left_name);
            score = itemView.findViewById(R.id.score);
            right_name = itemView.findViewById(R.id.right_name);
            list_1_1 = itemView.findViewById(R.id.list_1_1);
            list_1_2 = itemView.findViewById(R.id.list_1_2);
            list_1_3 = itemView.findViewById(R.id.list_1_3);
            list_1_4 = itemView.findViewById(R.id.list_1_4);
            list_1_5 = itemView.findViewById(R.id.list_1_5);
            list_2_1 = itemView.findViewById(R.id.list_2_1);
            list_2_2 = itemView.findViewById(R.id.list_2_2);
            list_2_3 = itemView.findViewById(R.id.list_2_3);
            list_2_4 = itemView.findViewById(R.id.list_2_4);
            list_2_5 = itemView.findViewById(R.id.list_2_5);

        }
    }
}
