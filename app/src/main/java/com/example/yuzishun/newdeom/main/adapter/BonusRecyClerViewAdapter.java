package com.example.yuzishun.newdeom.main.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.main.custom.AmountView_bonus_adapter;
import com.example.yuzishun.newdeom.main.custom.AmountView_two;
import com.example.yuzishun.newdeom.main.single.BettingSingleAdapter;
import com.example.yuzishun.newdeom.main.single.SingleMessage;
import com.example.yuzishun.newdeom.model.BonusBean;
import com.example.yuzishun.newdeom.utils.eventbus.BonusMessage;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by apple on 2019/9/24.
 */

public class BonusRecyClerViewAdapter extends RecyclerView.Adapter<BonusRecyClerViewAdapter.ViewHolder> {

    private Context context;
    private List<BonusBean> list;


    public BonusRecyClerViewAdapter(Context context, List<BonusBean> list) {
        this.context = context;
        this.list = list;
    }


    // 采用接口回调的方式实现RecyclerView的ItemClick
    public OnRecyclerViewListener mOnRecyclerViewListener;


    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {

        void onItemClick(int position,int amount);
    }
    // 接口回调第二步: 初始化接口的引用
    public void setOnRecyclerViewListener(OnRecyclerViewListener l) {
        this.mOnRecyclerViewListener = l;
    }

    @Override
    public BonusRecyClerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_bonus_list_item,parent,false));

    }

    @Override
    public void onBindViewHolder(BonusRecyClerViewAdapter.ViewHolder holder, int position) {
        NumberFormat nfmin = new DecimalFormat("#.##");
        String pmin = nfmin.format(list.get(position).getOneBetBounsMoney()*list.get(position).getNumber());

        holder.text_money.setText(pmin+"");
        holder.amountView.setGoods_storage(999999);
        holder.amountView.setTextd(list.get(position).getNumber());
        holder.bonus_textView.setText(list.get(position).getStringName()+"");
        holder.amountView.setOnAmountChangeListener_t(new AmountView_bonus_adapter.OnAmountChangeListener_t() {
            @Override
            public void onAmountChange(View view, int amount) {

                EventBus.getDefault().post(new BonusMessage(position,amount));

            }
        });





    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_money,bonus_textView;

        AmountView_bonus_adapter amountView;

        public ViewHolder(View itemView) {
            super(itemView);

            amountView = itemView.findViewById(R.id.amountView);
            text_money = itemView.findViewById(R.id.text_money);
            bonus_textView = itemView.findViewById(R.id.bonus_textView);


        }
    }
}
