package com.example.yuzishun.deomlottery.sunsheet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.Content;
import com.example.yuzishun.deomlottery.documentary.activity.OkamiActivity;
import com.example.yuzishun.deomlottery.model.SunSheetBean;
import com.example.yuzishun.deomlottery.my.custom.MyTableTextView;
import com.example.yuzishun.deomlottery.sunsheet.activity.SunSheetDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2019/7/23.
 */

public class SunSheetMainListAdapter extends RecyclerView.Adapter<SunSheetMainListAdapter.ViewHolder> {
    private Context context;

    private List<SunSheetBean.DataBean> list = new ArrayList<>();

    public SunSheetMainListAdapter(Context context, List<SunSheetBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;


    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position,String bask_id,ImageView imageView);
    }
    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }


    @Override
    public SunSheetMainListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.sunsheetlist_item,parent,false));
    }

    @Override
    public void onBindViewHolder(SunSheetMainListAdapter.ViewHolder holder, int position) {


        holder.text_dec.setText(list.get(position).getManifesto());
        holder.username.setText(list.get(position).getUname());
        holder.Text_time.setText(list.get(position).getCreate_time());
        holder.text_money.setText("¥："+list.get(position).getWin_money());
        holder.list_2_2.setText(list.get(position).getOrder_price()+"元");
        holder.list_2_3.setText(list.get(position).getWin_money()+"元");

        holder.list_2_4.setText(list.get(position).getMultiple()+"倍");
        if(list.get(position).getRed_black()==0){

            holder.text_money.setVisibility(View.GONE);
        }else {
            holder.text_money.setVisibility(View.VISIBLE);

        }


        if(list.get(position).getIs_like()==0){
            holder.Fabulous.setImageDrawable(context.getResources().getDrawable(R.mipmap.dianzan));

        }else {
            holder.Fabulous.setImageDrawable(context.getResources().getDrawable(R.mipmap.dianzan_red));


        }
        if(list.get(position).getGame_type()==0){

            holder.list_2_1.setText("足球");
        }else {
            holder.list_2_1.setText("篮球");


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SunSheetDetailsActivity.class);
                Content.back_id = String.valueOf(list.get(position).getBask_id());
                context.startActivity(intent);

            }
        });

        Glide.with(context).load(list.get(position).getImg_head()).asBitmap().centerCrop().into(holder.icon);
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentokmin = new Intent(context, OkamiActivity.class);
                intentokmin.putExtra("user_id",list.get(position).getUser_id()+"");

                context.startActivity(intentokmin);

            }
        });
        holder.Fabulous_TextView.setText(list.get(position).getLike_count()+"");
        holder.Fabulous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(list.get(position).getIs_like()==0){
                    if (mOnRecyclerViewListener != null) {
                        mOnRecyclerViewListener.onItemClick(position,list.get(position).getBask_id()+"",holder.Fabulous);
                    }
                }else {
                    Toast.makeText(context, "已经点过赞了", Toast.LENGTH_SHORT).show();
                }


            }
        });

        holder.comment_count.setText(list.get(position).getComment_count()+"");


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyTableTextView list_2_1,list_2_2,list_2_3,list_2_4;
        ImageView icon,Fabulous;
        TextView username,text_money,text_dec,Fabulous_TextView,Text_time,comment_count;
        public ViewHolder(View itemView) {
            super(itemView);
            list_2_1 = itemView.findViewById(R.id.list_2_1);
            list_2_2 = itemView.findViewById(R.id.list_2_2);
            list_2_3 = itemView.findViewById(R.id.list_2_3);
            list_2_4 = itemView.findViewById(R.id.list_2_4);
            icon=  itemView.findViewById(R.id.icon);
            Fabulous = itemView.findViewById(R.id.Fabulous);
            username = itemView.findViewById(R.id.username);
            text_money = itemView.findViewById(R.id.text_money);
            text_dec = itemView.findViewById(R.id.text_dec);
            Fabulous_TextView = itemView.findViewById(R.id.Fabulous_TextView);
            Text_time = itemView.findViewById(R.id.Text_time);
            comment_count = itemView.findViewById(R.id.comment_count);
        }
    }
}
