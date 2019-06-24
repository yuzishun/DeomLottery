package com.example.yuzishun.newdeom.documentary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.main.adapter.LotteryRecyclerViewAdapter;
import com.example.yuzishun.newdeom.model.DocumentListBean;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by yuzishun on 2019/5/7.
 */

/***
 *
 *
 */
public class EveryListRecyclerViewAdapter extends RecyclerView.Adapter<EveryListRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private DocumentListBean documentListBean;
    private int flag;
    public EveryListRecyclerViewAdapter(Context context, DocumentListBean documentListBean,int flag) {
        this.context = context;
        this.documentListBean = documentListBean;
        this.flag = flag;
    }
    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;


    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position,String user_id);
    }
    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }

    @Override
    public EveryListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_every_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(EveryListRecyclerViewAdapter.ViewHolder holder, final int position) {


        if(position==0){
            holder.level.setVisibility(View.VISIBLE);
            holder.text_level.setVisibility(View.GONE);


        }else if(position==1){
            holder.level.setVisibility(View.VISIBLE);
            holder.text_level.setVisibility(View.GONE);

            Glide.with(context).load(R.mipmap.yin_crown).asBitmap().centerCrop().into(holder.level);

        }else if(position==2){
            holder.level.setVisibility(View.VISIBLE);
            holder.text_level.setVisibility(View.GONE);

            Glide.with(context).load(R.mipmap.tong_crown).asBitmap().centerCrop().into(holder.level);


        }else {
            holder.level.setVisibility(View.GONE);
            holder.text_level.setVisibility(View.VISIBLE);
            holder.text_level.setText(position+1+"");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerViewListener != null) {
                    mOnRecyclerViewListener.onItemClick(position,documentListBean.getList().get(position).getUser_id()+"");
                }

            }
        });

     if(flag==1){
         Glide.with(context).load(documentListBean.getList().get(position).getImg_head()).asBitmap().centerCrop().into(holder.icon_profit);
         holder.text_name.setText(documentListBean.getList().get(position).getUname());

         holder.Text_tui.setText("命中率");
         holder.text_zhong.setText(documentListBean.getList().get(position).getHit());
         holder.text_lilv.setText(documentListBean.getList().get(position).getEarnings());


     }else if(flag==2){
         Glide.with(context).load(documentListBean.getList().get(position).getImg_head()).asBitmap().centerCrop().into(holder.icon_profit);
         holder.text_name.setText(documentListBean.getList().get(position).getUname());
         holder.text_lilv.setText(documentListBean.getList().get(position).getOrder_sum()+"中"+documentListBean.getList().get(position).getOrder_win());
         holder.text_wen.setText("近七天命中率");
         holder.Text_tui.setVisibility(View.GONE);
         holder.text_zhong.setVisibility(View.GONE);
         holder.text_name.setPadding(0,15,0,0);
     }else if(flag==3){
         Glide.with(context).load(documentListBean.getList().get(position).getImg_head()).asBitmap().centerCrop().into(holder.icon_profit);
         holder.text_name.setText(documentListBean.getList().get(position).getUname());

         holder.text_zhong.setText(documentListBean.getList().get(position).getOrder_sum()+"中"+documentListBean.getList().get(position).getOrder_win());
         holder.text_wen.setText("推荐中奖（元）");
         holder.text_lilv.setText(documentListBean.getList().get(position).getBonus_price()+"");

     }else if(flag==4){
         Glide.with(context).load(documentListBean.getList().get(position).getImg_head()).asBitmap().centerCrop().into(holder.icon_profit);
         holder.text_name.setText(documentListBean.getList().get(position).getUname());
         holder.level.setVisibility(View.GONE);
         holder.text_level.setVisibility(View.GONE);
         holder.Text_tui.setVisibility(View.GONE);
         holder.text_zhong.setText(documentListBean.getList().get(position).getUser_id()+"");
         holder.text_wen.setVisibility(View.GONE);
         holder.text_lilv.setVisibility(View.GONE);
     }


    }

    @Override
    public int getItemCount() {
        return documentListBean.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView level;
        TextView text_name,Text_tui,text_zhong,text_lilv,text_wen,text_level;
        ImageView icon_profit;
        public ViewHolder(View itemView) {
            super(itemView);
            icon_profit = itemView.findViewById(R.id.icon_profit);
            level = itemView.findViewById(R.id.level);
            text_name = itemView.findViewById(R.id.text_name);
            Text_tui = itemView.findViewById(R.id.Text_tui);
            text_zhong = itemView.findViewById(R.id.text_zhong);
            text_lilv = itemView.findViewById(R.id.text_lilv);
            text_wen = itemView.findViewById(R.id.text_wen);
            text_level = itemView.findViewById(R.id.text_level);

        }
    }
}
