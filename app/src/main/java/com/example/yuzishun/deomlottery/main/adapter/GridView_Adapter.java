package com.example.yuzishun.deomlottery.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.model.ChoosebettBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/5.
 */

public class GridView_Adapter extends BaseAdapter {

    private Context context;

    private LayoutInflater inflater;
    private List<ChoosebettBean> results = new ArrayList<ChoosebettBean>();

    public GridView_Adapter(Context context, List<ChoosebettBean> results) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.results = results;

    }
    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.betting_popwindow_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (Button) convertView.findViewById(R.id.item);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(results.get(position).isIsselect()){

            viewHolder.name.setTextColor(convertView.getResources().getColor(R.color.white));

            viewHolder.name.setEnabled(true);
        }else {
            viewHolder.name.setTextColor(convertView.getResources().getColor(R.color.font_black));

            viewHolder.name.setEnabled(false);
        }




        viewHolder.name.setText(results.get(position).getName());
        return convertView;
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

        for (int i = 0; i < results.size(); i++) {

            if(i==post){
                results.get(i).setIsselect(true);

            }else {

            results.get(i).setIsselect(false);
            }

        }


        this.notifyDataSetChanged();
    }


    class ViewHolder {
        Button name;
    }
}
