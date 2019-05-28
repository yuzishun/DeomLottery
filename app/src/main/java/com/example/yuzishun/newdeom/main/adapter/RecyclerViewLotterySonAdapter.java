package com.example.yuzishun.newdeom.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.my.custom.MyTableTextView;

import java.util.ArrayList;

/**
 * Created by yuzishun on 2019/5/5.
 */

/***
 * 中奖页面的子列表适配器
 *
 */
public class RecyclerViewLotterySonAdapter extends RecyclerView.Adapter<RecyclerViewLotterySonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> list = new ArrayList<>();
    private String[] name={"胜负","让分胜负","大小分","胜负差"};
    public RecyclerViewLotterySonAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewLotterySonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_lotterwangqi_son_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewLotterySonAdapter.ViewHolder holder, int position) {

        initData(context,holder.MyTable);

    }

    private void initData(Context context,LinearLayout linearLayout) {

        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
        MyTableTextView title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
        title.setText(name[0]);
        title.setTextColor(context.getResources().getColor(R.color.font_black));

        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
        title.setText(name[1]);
        title.setTextColor(context.getResources().getColor(R.color.font_black));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
        title.setText(name[2]);
        title.setTextColor(context.getResources().getColor(R.color.font_black));
        title = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
        title.setText(name[3]);
        title.setTextColor(context.getResources().getColor(R.color.font_black));


        linearLayout.addView(relativeLayout);
        //初始化内容
        for (int i = 0; i < 1; i++) {
            relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.table, null);
            MyTableTextView txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_1);
            txt.setText("主胜");
            txt.setTextColor(context.getResources().getColor(R.color.login_red));
            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_2);
            txt.setText("（-7.5）让分主负");
            txt.setTextColor(context.getResources().getColor(R.color.login_red));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_3);
            txt.setText("小分（162.5）");
            txt.setTextColor(context.getResources().getColor(R.color.login_red));

            txt = (MyTableTextView) relativeLayout.findViewById(R.id.list_1_4);
            txt.setText("主胜（1-5）");
            txt.setTextColor(context.getResources().getColor(R.color.login_red));


            linearLayout.addView(relativeLayout);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout MyTable;

        public ViewHolder(View itemView) {
            super(itemView);
            MyTable = itemView.findViewById(R.id.MyTable);

        }
    }
}
