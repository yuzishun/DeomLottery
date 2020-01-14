package com.example.yuzishun.deomlottery.main.betting;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.MyApplication;
import com.example.yuzishun.deomlottery.model.ItemPoint;
import com.example.yuzishun.deomlottery.utils.eventbus.AdapterMessage;
import com.example.yuzishun.deomlottery.utils.MainMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/26.
 */
public class QuickAdapter_football extends BaseQuickAdapter<ItemPoint,BaseViewHolder> {
    private int flag_click,postion;
    private Expand1Item_football item1;
    public QuickAdapter_football(List<ItemPoint> itemPoint, int flag_click,Expand1Item_football item1,int postion) {
        super(R.layout.dialog_recycler_one,itemPoint);
        this.flag_click = flag_click;
        this.item1 = item1;
        this.postion = postion;
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
            item_right_name.setTextColor(MyApplication.getContext().getResources().getColor(R.color.gray_Overall_hint));

            layout_relat.setBackgroundResource(R.drawable.dialog_mixed_false_t);

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

                        EventBus.getDefault().post(new MainMessage(BettingFootballListAdapter.getnumber()+""));

                        String s = BettingFootballListAdapter.setMore(item1);
                        EventBus.getDefault().post(new AdapterMessage(postion));


                        item1.desc=s;

                    }else {
                        item.setIsselect(true);

                        notifyDataSetChanged();
                        EventBus.getDefault().post(new MainMessage(BettingFootballListAdapter.getnumber()+""));
                        String s = BettingFootballListAdapter.setMore(item1);
                        EventBus.getDefault().post(new AdapterMessage(postion));


                        item1.desc=s;
                    }
                    }

                }else {

                    layout_relat.setOnClickListener(null);


                }



            }
        });

    }




}
