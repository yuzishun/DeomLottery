package com.example.yuzishun.newdeom.main.single;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.MyApplication;
import com.example.yuzishun.newdeom.model.ItemPoint;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/26.
 */
public class SingleAdapter extends BaseQuickAdapter<ItemPoint,BaseViewHolder> {
    private int flag_click;

    public SingleAdapter(List<ItemPoint> itemPoint, int flag_click) {
        super(R.layout.dialog_recycler_one,itemPoint);
        this.flag_click = flag_click;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ItemPoint item) {
        final LinearLayout layout_relat = helper.getView(R.id.layout_relat);

        if(item.getOdds().equals("")){
            helper.setText(R.id.item,"暂未开售").setText(R.id.item_right,item.getOdds());

        }else {
            helper.setText(R.id.item,item.id).setText(R.id.item_right,item.getOdds());
        }


        final TextView item_name = helper.getView(R.id.item);
        final TextView item_right_name = helper.getView(R.id.item_right);


        if(flag_click==1){

            item.isclick=true;

        }else {
            item.isclick=false;

        }


        if(item.isIsselect()){

            item_name.setTextColor(MyApplication.getContext().getResources().getColor(R.color.white));
            item_right_name.setTextColor(MyApplication.getContext().getResources().getColor(R.color.white));
            layout_relat.setBackgroundResource(R.drawable.dialog_mixed_true);
        }else {
            item_name.setTextColor(MyApplication.getContext().getResources().getColor(R.color.font_black));
            item_right_name.setTextColor(MyApplication.getContext().getResources().getColor(R.color.font_black));

            layout_relat.setBackgroundResource(R.drawable.dialog_mixed_false);

        }

        layout_relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isIsclick()){
                    if(item.getOdds().equals("")) {
                        layout_relat.setOnClickListener(null);

                    }else {

                        item.isonclick=1;
                    if(item.isIsselect()){
                        notifyDataSetChanged();

                        item.setIsselect(false);

                        EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber()+""));


                    }else {
                        item.setIsselect(true);

                        notifyDataSetChanged();
                        EventBus.getDefault().post(new SingleMessage(BettingSingleAdapter.getnumber()+""));

                    }
                    }

                }else {

                    layout_relat.setOnClickListener(null);


                }



            }
        });

    }




}
