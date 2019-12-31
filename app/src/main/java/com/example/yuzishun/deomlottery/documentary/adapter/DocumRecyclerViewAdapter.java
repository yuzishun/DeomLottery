package com.example.yuzishun.deomlottery.documentary.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.documentary.activity.CopydocumentActivity;
import com.example.yuzishun.deomlottery.documentary.activity.DocumentdetailsActivity;
import com.example.yuzishun.deomlottery.documentary.activity.OkamiActivity;
import com.example.yuzishun.deomlottery.model.DocumentaryBean;
import com.example.yuzishun.deomlottery.my.custom.MyTableTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/6.
 */

public class DocumRecyclerViewAdapter extends RecyclerView.Adapter<DocumRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<DocumentaryBean.DataBean> list = new ArrayList<>();

    public DocumRecyclerViewAdapter(Context context, List<DocumentaryBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public DocumRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_document_recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(DocumRecyclerViewAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getImg_head()).asBitmap().centerCrop().into(holder.icon);

        holder.username.setText(list.get(position).getUname());
        holder.text_dec.setText(list.get(position).getPlan_desc());
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentokmin = new Intent(context, OkamiActivity.class);
                intentokmin.putExtra("user_id",list.get(position).getUser_id());

                context.startActivity(intentokmin);


            }
        });

        holder.document_shou_button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CopydocumentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",list.get(position).getUname()+"");
                bundle.putString("money",list.get(position).getMultiple_price()+"");
                bundle.putString("plan_id",list.get(position).getPlan_id()+"");
                bundle.putString("time",list.get(position).getCut_off_time()+"");
                bundle.putString("plan_profits",list.get(position).getPlan_profits()+"");
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context,DocumentdetailsActivity.class);


                intent1.putExtra("flag",1);

                intent1.putExtra("order_id",list.get(position).getOrder_id());
                intent1.putExtra("plan_id",list.get(position).getPlan_id());

                context.startActivity(intent1);
            }
        });
        if(list.get(position).getGame_type()==0){
            holder.text_type.setText("竞彩类型：足球");

        }else {
            holder.text_type.setText("竞彩类型：篮球");
        }

        holder.text_time.setText(list.get(position).getCut_off_time()+"截止");
        holder.list_1_1.setText("方案金额");
        holder.list_1_2.setText("单倍金额");

        holder.list_1_3.setText("跟单人数");

        holder.list_1_4.setText("跟单金额");
        holder.list_2_1.setText(list.get(position).getOrder_price());
        holder.list_2_2.setText(list.get(position).getMultiple_price());
        holder.list_2_3.setText(list.get(position).getPlan_follow_person());
        holder.list_2_4.setText(list.get(position).getPlan_follow_price());


    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        MyTableTextView list_1_1,list_1_2,list_1_3,list_1_4,list_2_1,list_2_2,list_2_3,list_2_4;
        TextView username,text_time,text_dec,text_type;
        Button document_shou_button_;
        public ViewHolder(View itemView) {
            super(itemView);
            text_type = itemView.findViewById(R.id.text_type);
            list_1_1 = itemView.findViewById(R.id.list_1_1);
            list_1_2 = itemView.findViewById(R.id.list_1_2);
            list_1_3 = itemView.findViewById(R.id.list_1_3);
            list_1_4 = itemView.findViewById(R.id.list_1_4);
            list_2_1 = itemView.findViewById(R.id.list_2_1);
            list_2_2 = itemView.findViewById(R.id.list_2_2);
            list_2_3 = itemView.findViewById(R.id.list_2_3);
            list_2_4 = itemView.findViewById(R.id.list_2_4);
            icon = itemView.findViewById(R.id.icon);
            text_dec = itemView.findViewById(R.id.text_dec);
            username = itemView.findViewById(R.id.username);
            text_time = itemView.findViewById(R.id.text_time);
            document_shou_button_ = itemView.findViewById(R.id.document_shou_button_);
        }
    }
}
