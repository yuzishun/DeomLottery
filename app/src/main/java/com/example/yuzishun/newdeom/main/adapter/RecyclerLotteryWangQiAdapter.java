package com.example.yuzishun.newdeom.main.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.main.custom.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created : 2018/3/8 11:01
 * Description :
 * Author : gengbaolong
 */

public class RecyclerLotteryWangQiAdapter extends RecyclerView.Adapter<RecyclerLotteryWangQiAdapter.RecyclerViewHolder>{

    Context mContext;
    List<String> mList;
    LayoutInflater mInflater;

    // 用一组list保存下拉状态（true - 显示下拉， false - 隐藏下拉）
    public ArrayList<Boolean> lDropDown;

    public RecyclerLotteryWangQiAdapter(Context context, List<String> list){
        this.mContext = context;
        this.mList = list;
        mInflater = LayoutInflater.from(context);

        // 初始状态，所有都不显示下拉
        lDropDown = new ArrayList<Boolean>();
        for (int i = 0; i < mList.size(); i++) {
            lDropDown.add(true);
        }
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = mInflater.inflate(R.layout.recyclerview_lotterywangqi_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {


        final int index = position;

        if(lDropDown.get(position)){
            holder.line_bottom.setVisibility(View.VISIBLE);//展开
            holder.status_image.setBackgroundResource(R.mipmap.ic_expand_less);
        }else{
            holder.line_bottom.setVisibility(View.GONE);//隐藏
            holder.status_image.setBackgroundResource(R.mipmap.ic_expand_more);
        }
        //添加子布局
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("");
        }
        holder.RecyclerView_Lottery_son.setLayoutManager(new LinearLayoutManager(mContext));

        RecyclerViewLotterySonAdapter recyclerViewLotterySonAdapter = new RecyclerViewLotterySonAdapter(mContext,list);

        holder.RecyclerView_Lottery_son.setAdapter(recyclerViewLotterySonAdapter);

        holder.rl_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bFlagTemp = lDropDown.get(index);
//                for (int i = 0; i < lData.size(); i++) {//先把所有都隐藏--------这个循环加上，可以让页面同一时间只能展开一个条目,去掉这个循环，可以实现同时展开多个条目，互不影响
//                    lDropDown.set(i, false);
//                }
                lDropDown.set(index, !bFlagTemp);
                if(lDropDown.get(position)){
                    holder.line_bottom.setVisibility(View.VISIBLE);//展开
                    holder.status_image.setBackgroundResource(R.mipmap.ic_expand_less);
                }else{
                    holder.line_bottom.setVisibility(View.GONE);//隐藏
                    holder.status_image.setBackgroundResource(R.mipmap.ic_expand_more);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mList?0:mList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        ImageView status_image;
        LinearLayout line_bottom;
        TextView tv_status;
        RelativeLayout rl_top;
        MyRecyclerView RecyclerView_Lottery_son;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            status_image = itemView.findViewById(R.id.status_image);
            line_bottom = itemView.findViewById(R.id.line_bottom);
            tv_status = itemView.findViewById(R.id.tv_status);
            rl_top = itemView.findViewById(R.id.rl_top);
            RecyclerView_Lottery_son = itemView.findViewById(R.id.RecyclerView_Lottery_son);
        }
    }

}
