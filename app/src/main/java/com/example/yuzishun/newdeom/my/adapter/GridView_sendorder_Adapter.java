package com.example.yuzishun.newdeom.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.yuzishun.newdeom.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/5.
 */

/***
 * 足球投注页面的tab栏的gridview
 *
 */
public class GridView_sendorder_Adapter extends BaseAdapter {

    private Context context;
    private boolean[] isCheck;
    private int mSelectorPosition;

    private LayoutInflater inflater;
    private List<String> results = new ArrayList<String>();

    public GridView_sendorder_Adapter(Context context, List<String> results) {
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
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.sendorder_gridview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (Button) convertView.findViewById(R.id.item);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(mSelectorPosition==position){

            viewHolder.name.setTextColor(convertView.getResources().getColor(R.color.white));
            viewHolder.name.setEnabled(true);
        }else {
            viewHolder.name.setTextColor(convertView.getResources().getColor(R.color.font_black));

            viewHolder.name.setEnabled(false);
        }


        viewHolder.name.setText(results.get(position));
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

        mSelectorPosition=post;

        this.notifyDataSetChanged();
    }


    class ViewHolder {
        Button name;
    }
}
