package com.example.yuzishun.deomlottery.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.model.ChoosebettBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2019/11/21.
 */

public class BettChooseAdapter  extends RecyclerView.Adapter<BettChooseAdapter.ViewHolder>{

    private Context context;

    private List<ChoosebettBean> list  = new ArrayList<>();

    public BettChooseAdapter(Context context, List<ChoosebettBean> list) {
        this.context = context;
        this.list = list;
    }

    public OnBettCHooseListene onBettCHooseListene;

    public interface OnBettCHooseListene{

        void itemClick(int postion);
    }

    public void setOnBettChooseListene(OnBettCHooseListene o){

        this.onBettCHooseListene = o;
    }

    @Override
    public BettChooseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.betting_popwindow_item,parent,false));
    }

    @Override
    public void onBindViewHolder(BettChooseAdapter.ViewHolder holder, int position) {

        holder.name.setText(list.get(position).getName());
        if(list.get(position).isIsselect()){

            holder.name.setTextColor(context.getResources().getColor(R.color.white));

            holder.name.setEnabled(true);
        }else {
            holder.name.setTextColor(context.getResources().getColor(R.color.gray_Overall_hint));

            holder.name.setEnabled(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onBettCHooseListene != null) {
                    onBettCHooseListene.itemClick(position);
                }

            }
        });



    }

    public void choiceState(int post) {
        /**
         *  传递过来所点击的position,如果是本身已经是选中状态,就让他变成不是选中状态,
         *  如果本身不是选中状态,就让他变成选中状态
         */

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIsselect(false);

        }
        list.get(post).setIsselect(true);


        this.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button name;
        public ViewHolder(View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.item);

        }
    }
}
