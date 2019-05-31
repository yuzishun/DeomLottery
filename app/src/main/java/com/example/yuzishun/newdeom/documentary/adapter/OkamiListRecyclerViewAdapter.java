package com.example.yuzishun.newdeom.documentary.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.documentary.activity.CopydocumentActivity;
//import com.example.yuzishun.newdeom.documentary.activity.Documentdetails_main_Activity;
import com.example.yuzishun.newdeom.my.activity.BetteyAndWinningActivity;
import com.example.yuzishun.newdeom.my.custom.MyTableTextView;

import java.util.ArrayList;

/**
 * Created by yuzishun on 2019/5/7.
 */

/***
 * 大神首页里面的跟单列表
 *
 */
public class OkamiListRecyclerViewAdapter extends RecyclerView.Adapter<OkamiListRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> list = new ArrayList<>();
    private String[] name={"订单金额","单倍金额","参与人数","中奖金额"};


    public OkamiListRecyclerViewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public OkamiListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_okamirecyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(OkamiListRecyclerViewAdapter.ViewHolder holder, int position) {
        initdata(context,holder.MyTable);
        if(position==2){
            Glide.with(context).load(R.mipmap.weijiang).asBitmap().into(holder.jiang_state);
            holder.Button_Okami_gen.setEnabled(false);

        }
        holder.Button_Okami_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, CopydocumentActivity.class));

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1 = new Intent(context,Documentdetails_main_Activity.class);
//
//                //这里传0代表的是订单
//                intent1.putExtra("flag",0);
//
//                intent1.putExtra("type",type);
//                intent1.putExtra("order_id",order_id);
//
//                context.startActivity(intent1);

            }
        });



    }
    private void initdata(Context context,LinearLayout linearLayout) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
        MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
        title.setText(name[0]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));

        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
        title.setText(name[1]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
        title.setText(name[2]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
        title.setText(name[3]);
        title.setTextColor(context.getResources().getColor(R.color.font_gray));


        linearLayout.addView(relativeLayout);
        //初始化内容
        for (int i = 0; i < 1; i++) {
            relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
            txt.setText("5000.0元");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));
            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            txt.setText("2.0元");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            txt.setText("11人");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            txt.setText("14050.0元");
            txt.setTextColor(context.getResources().getColor(R.color.font_gray));


            linearLayout.addView(relativeLayout);
        }



    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout MyTable;
        Button Button_Okami_gen;
        ImageView jiang_state;
        public ViewHolder(View itemView) {
            super(itemView);
            MyTable = itemView.findViewById(R.id.MyTable);
            Button_Okami_gen = itemView.findViewById(R.id.Button_Okami_gen);
            jiang_state = itemView.findViewById(R.id.jiang_state);
        }
    }
}
