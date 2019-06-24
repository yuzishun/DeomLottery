package com.example.yuzishun.newdeom.documentary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.model.HotGodBean;
import com.example.yuzishun.newdeom.my.adapter.GridView_Recharge_Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/6.
 */

/***
 * 跟单页面大神gridview
 *
 */
public class DocumHotGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<HotGodBean.DataBean> list = new ArrayList<>();
    private LayoutInflater inflater;

    public DocumHotGridViewAdapter(Context context, List<HotGodBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_document_hot_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.god_image = convertView.findViewById(R.id.god_image);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getUname());
        Glide.with(context).load(list.get(position).getImg_head()).asBitmap().centerCrop().into(viewHolder.god_image);
        return convertView;

    }

    class ViewHolder {
        TextView name;
        ImageView god_image;
    }
}
