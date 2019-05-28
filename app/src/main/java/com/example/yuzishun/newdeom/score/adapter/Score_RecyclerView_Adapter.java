package com.example.yuzishun.newdeom.score.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yuzishun on 2019/5/6.
 */

public class Score_RecyclerView_Adapter extends RecyclerView.Adapter<Score_RecyclerView_Adapter.ViewHolder>{
    private Context context;
    private ArrayList<String> list = new ArrayList<>();

    public Score_RecyclerView_Adapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Score_RecyclerView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Score_RecyclerView_Adapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
