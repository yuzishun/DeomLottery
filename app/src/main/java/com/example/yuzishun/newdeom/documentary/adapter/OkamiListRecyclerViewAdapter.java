package com.example.yuzishun.newdeom.documentary.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.documentary.activity.CopydocumentActivity;
import com.example.yuzishun.newdeom.documentary.activity.DocumentdetailsActivity;
import com.example.yuzishun.newdeom.model.OkamiListBean;
import com.example.yuzishun.newdeom.my.activity.BetteyAndWinningActivity;
import com.example.yuzishun.newdeom.my.custom.MyTableTextView;
import com.example.yuzishun.newdeom.sunsheet.activity.SunSheetDetailsActivity;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/7.
 */

/***
 * 大神首页里面的跟单列表
 *
 */
public class OkamiListRecyclerViewAdapter extends RecyclerView.Adapter<OkamiListRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<OkamiListBean.DataBean> list = new ArrayList<>();
    private String[] name={"订单金额","单倍金额","参与人数","中奖金额"};
    private int flag;

    public OkamiListRecyclerViewAdapter(Context context, List<OkamiListBean.DataBean> list,int flag) {
        this.context = context;
        this.list = list;
        this.flag = flag;
    }

    @Override
    public OkamiListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_okamirecyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(OkamiListRecyclerViewAdapter.ViewHolder holder, final int position) {

        holder.Text_data.setText("截止时间:"+list.get(position).getCut_off_time());
        holder.Text_money.setText(list.get(position).getPlan_follow_price());

        holder.list_1_1.setText(name[0]);
        holder.list_1_2.setText(name[1]);
        holder.list_1_3.setText(name[2]);
        holder.list_1_4.setText(name[3]);
        holder.list_2_1.setText(list.get(position).getOrder_price()+"");
        holder.list_2_2.setText(list.get(position).getMultiple_price()+"");
        holder.list_2_3.setText(list.get(position).getPlan_follow_person()+"");
        holder.list_2_4.setText(list.get(position).getBonus_price()+"");

        switch (list.get(position).getOrder_status()){
            case 1:
                holder.jiang_state.setVisibility(View.GONE);


                break;
            case 2:
                holder.jiang_state.setVisibility(View.VISIBLE);

                Glide.with(context).load(R.mipmap.readyjiang).asBitmap().into(holder.jiang_state);
                break;
            case 3:
                holder.jiang_state.setVisibility(View.VISIBLE);

                Glide.with(context).load(R.mipmap.weijiang).asBitmap().into(holder.jiang_state);
                break;
        }
        if(list.get(position).getFollow_plan_permission()==0){

            holder.Button_Okami_gen.setEnabled(false);


        }else {
            holder.Button_Okami_gen.setEnabled(true);

        }

        if(list.get(position).getBask_id().equals("")){
            holder.Button_Okami_gen.setVisibility(View.VISIBLE);
            holder.Button_Okami_ping.setVisibility(View.GONE);
            holder.text_sunsheet.setVisibility(View.GONE);
            if(flag==0){

                holder.Button_Okami_gen.setVisibility(View.GONE);

            }else {
                holder.Button_Okami_gen.setVisibility(View.VISIBLE);

            }
        }else {
            holder.text_sunsheet.setVisibility(View.VISIBLE);
            holder.Button_Okami_gen.setVisibility(View.GONE);
            //先注释
//            holder.Button_Okami_ping.setVisibility(View.VISIBLE);
            holder.Button_Okami_ping.setVisibility(View.GONE);


            if(flag==0){

                holder.Button_Okami_gen.setVisibility(View.GONE);

            }else {
                holder.Button_Okami_gen.setVisibility(View.VISIBLE);

            }
        }

        if(list.get(position).getGame_type()==0){
            holder.text_type.setText("竞彩类型：足球");

        }else {
            holder.text_type.setText("竞彩类型：篮球");
        }

        holder.Button_Okami_ping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SunSheetDetailsActivity.class);
                Content.back_id = String.valueOf(list.get(position).getBask_id());
                context.startActivity(intent);
            }
        });

        holder.Button_Okami_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(context,DocumentdetailsActivity.class);


                intent1.putExtra("flag",1);

                intent1.putExtra("order_id",list.get(position).getOrder_id());
                intent1.putExtra("plan_id",list.get(position).getPlan_id());


                context.startActivity(intent1);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                //这里传0代表的是订单
//                intent1.putExtra("flag",0);
//
//                intent1.putExtra("type",type);
//                intent1.putExtra("order_id",order_id);
//
//                context.startActivity(intent1);

                Intent intent1 = new Intent(context,DocumentdetailsActivity.class);


                intent1.putExtra("flag",1);

                intent1.putExtra("order_id",list.get(position).getOrder_id());
                intent1.putExtra("plan_id",list.get(position).getPlan_id());
                context.startActivity(intent1);
            }
        });



    }
//    private void initdata(Context context,LinearLayout linearLayout,List<OkamiListBean.DataBean> list,int postion) {
//        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
//        MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
//        title.setText(name[0]);
//        title.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
//        title.setText(name[1]);
//        title.setTextColor(context.getResources().getColor(R.color.font_gray));
//        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
//        title.setText(name[2]);
//        title.setTextColor(context.getResources().getColor(R.color.font_gray));
//        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
//        title.setText(name[3]);
//        title.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//
//        linearLayout.addView(relativeLayout);
//        //初始化内容
//        for (int i = 0; i < 1; i++) {
//            relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
//            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
//            txt.setText(list.get(postion).getOrder_price()+"");
//            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
//            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
//            txt.setText(list.get(postion).getMultiple_price()+"");
//            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
//            txt.setText(list.get(postion).getPlan_follow_person()+"");
//            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
//            txt.setText(list.get(postion).getBonus_price()+"");
//            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
//
//
//            linearLayout.addView(relativeLayout);
//        }
//
//
//
//    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button Button_Okami_gen,Button_Okami_ping;
        ImageView jiang_state;
        TextView Text_data,Text_money,text_type,text_sunsheet;
        MyTableTextView list_1_1,list_1_2,list_1_3,list_1_4,list_2_1,list_2_2,list_2_3,list_2_4;
        public ViewHolder(View itemView) {
            super(itemView);

            text_sunsheet = itemView.findViewById(R.id.text_sunsheet);
            list_1_1 = itemView.findViewById(R.id.list_1_1);
            list_1_2 = itemView.findViewById(R.id.list_1_2);

            list_1_3 = itemView.findViewById(R.id.list_1_3);
            list_1_4 = itemView.findViewById(R.id.list_1_4);
            list_2_1 = itemView.findViewById(R.id.list_2_1);
            list_2_2 = itemView.findViewById(R.id.list_2_2);
            list_2_3 = itemView.findViewById(R.id.list_2_3);
            list_2_4 = itemView.findViewById(R.id.list_2_4);
            text_type = itemView.findViewById(R.id.text_type);
            Button_Okami_ping = itemView.findViewById(R.id.Button_Okami_ping);
            Button_Okami_gen = itemView.findViewById(R.id.Button_Okami_gen);
            Text_money = itemView.findViewById(R.id.Text_money);
            jiang_state = itemView.findViewById(R.id.jiang_state);
            Text_data = itemView.findViewById(R.id.Text_data);
        }
    }
}
