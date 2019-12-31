package com.example.yuzishun.deomlottery.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.model.SureguanBean;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/24.
 */

public class ModeRecyclerViewMNAdapter extends RecyclerView.Adapter<ModeRecyclerViewMNAdapter.ViewHolde> {
    private Context context;
    private List<SureguanBean> list;
    public ModeRecyclerViewMNAdapter(Context context, List<SureguanBean> list) {
        this.context = context;
        this.list = list;

    }
    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;


    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position);
    }
    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }


    @Override
    public ModeRecyclerViewMNAdapter.ViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolde(LayoutInflater.from(context).inflate(R.layout.mode_mixed_recycler_chuan,parent,false));
    }

    @Override
    public void onBindViewHolder(ModeRecyclerViewMNAdapter.ViewHolde holder, final int position) {

        holder.item.setText(list.get(position).getName());


        if(list.size()==1){

            holder.item.setTextColor(context.getResources().getColor(R.color.white));
            holder.item.setBackgroundResource(R.drawable.login_radios_border_sure_red);

        }else {



        if(list.get(position).isIsselect()){

            holder.item.setTextColor(context.getResources().getColor(R.color.white));
            holder.item.setBackgroundResource(R.drawable.login_radios_border_sure_red);


        }else {
            holder.item.setTextColor(context.getResources().getColor(R.color.font_black));
            holder.item.setBackgroundResource(R.drawable.login_radios_border_sure_white);

        }
        }



        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerViewListener != null) {
                    mOnRecyclerViewListener.onItemClick(position);
                }

            }
        });





    }
    /**
     * 改变某一个选项的状态
     * @param post
     */
    public void choiceState(int post) {
        /**
         *  传递过来所点击的position,如果是本身已经是选中状态,就让他变成不是选中状态,
         *  如果本身不是选中状态,就让他变成选中状态
         */
        for (int i = 0; i < list.size(); i++) {

            list.get(post).setIsselect(false);

            if(post==i){
                list.get(post).setIsselect(true);
                this.notifyDataSetChanged();

            }

        }



//            if(list.size()==1){
//                ToastUtil.showToast(context,"只有一个不能不选");
//            }else {
//                list.get(post).isselect = list.get(post).isselect == true ? false : true;
//
//                this.notifyDataSetChanged();
//
//            }




    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        Button item;
        public ViewHolde(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
        }
    }
}
