package com.example.yuzishun.newdeom.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.model.BankMangmentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/4/26.
 */

public class BankCradAdapter extends RecyclerView.Adapter<BankCradAdapter.ViewHolder>{
    private Context context;
    private List<List<BankMangmentBean.DataBean>> list = new ArrayList<>();
    public BankCradAdapter(Context context, List<List<BankMangmentBean.DataBean>> list) {
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
    public BankCradAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_bankcradadapter_item,parent,false));
    }

    @Override
    public void onBindViewHolder(BankCradAdapter.ViewHolder holder, final int position) {

        if (!TextUtils.isEmpty(list.get(0).get(position).getCard_no()) && list.get(0).get(position).getCard_no().length()>=4){
            holder.Text_card_no.setText("尾号"+list.get(0).get(position).getCard_no().substring(list.get(0).get(position).getCard_no().length()-4,list.get(0).get(position).getCard_no().length())+"的储蓄卡");
        }

        holder.Text_card_bank.setText(list.get(0).get(position).getCard_bank());


    }

    @Override
    public int getItemCount() {
        return list.get(0).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Text_card_bank,Text_card_no;
        public ViewHolder(View itemView) {
            super(itemView);
            Text_card_bank = itemView.findViewById(R.id.Text_card_bank);
            Text_card_no = itemView.findViewById(R.id.Text_card_no);


        }
    }
    //  删除数据
    public void removeData(int position) {
        list.get(0).remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
