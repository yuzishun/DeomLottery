package com.example.yuzishun.deomlottery.main.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.MyApplication;
import com.example.yuzishun.deomlottery.model.ItemPoint;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/26.
 */
public class QuickAdapter_four extends BaseQuickAdapter<ItemPoint,BaseViewHolder> {
    private int flag_click;
    public QuickAdapter_four(@Nullable List<ItemPoint> data, int flag_click) {
        super(R.layout.dialog_recycler_two, data);
        this.flag_click = flag_click;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ItemPoint item) {
        if(item.getOdds().equals("")){
            helper.setText(R.id.item,"暂未开售").setText(R.id.item_right,item.getOdds());

        }else {
            helper.setText(R.id.item,item.id).setText(R.id.item_right,item.getOdds());
        }

//        helper.setText(R.id.item,item.id).setText(R.id.item_right,item.getOdds());
        final TextView item_name = helper.getView(R.id.item);
        final TextView item_right_name = helper.getView(R.id.item_right);
        final LinearLayout layout_relat = helper.getView(R.id.layout_relat);

        if(item.isIsselect()){

            item_name.setTextColor(MyApplication.getContext().getResources().getColor(R.color.white));
            item_right_name.setTextColor(MyApplication.getContext().getResources().getColor(R.color.white));
            layout_relat.setBackgroundResource(R.drawable.dialog_mixed_true);
        }else {
            item_name.setTextColor(MyApplication.getContext().getResources().getColor(R.color.font_black));
            item_right_name.setTextColor(MyApplication.getContext().getResources().getColor(R.color.gray_Overall_hint));

            layout_relat.setBackgroundResource(R.drawable.dialog_mixed_false_t);

        }
        if(flag_click==1){

            item.isclick=true;

        }else {
            item.isclick=false;

        }

        layout_relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(item.isIsclick()){
                    if(item.getOdds().equals("")) {
                        layout_relat.setOnClickListener(null);

                    }else {

                        if(item.isIsselect()){
                            item.setIsselect(false);
                            notifyDataSetChanged();
                        }else {
                            item.setIsselect(true);

                            notifyDataSetChanged();


                        }
                    }
                    item.isonclick=1;


            }else {

                layout_relat.setOnClickListener(null);


            }

        }
        });


    }




}
