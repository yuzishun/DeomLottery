package com.example.yuzishun.newdeom.main.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.MyApplication;

import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.FootballBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.SubMixBean;
import com.example.yuzishun.newdeom.model.SubMixListBean;
import com.example.yuzishun.newdeom.utils.MainMessage;
import com.example.yuzishun.newdeom.utils.ToastUtil;


import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/22.
 */

public class BettingListAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    public static final int  TYPE_LEVEL_0 = 0;
    public static final int  TYPE_LEVEL_1 = 1;
    public static List<ChooseMixedBean> list_choose = new ArrayList<>();
    public static int flag_dialog=0;

    //判断选择的有几场，傻瓜方法
    private static List<String> list_adds;

    private static List<SubMixBean> list_subMixBean = new ArrayList<>();




    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BettingListAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.recyclerview_base_father);
        addItemType(TYPE_LEVEL_1,R.layout.recyclerview_base_son);

    }


    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        switch (helper.getItemViewType()){
            case TYPE_LEVEL_0:
                final ExpandItem item1 = (ExpandItem) item;
                helper.setText(R.id.tv_status,item1.title)
                        .setBackgroundRes(R.id.status_image,item1.isExpanded() ? R.mipmap.ic_expand_more:R.mipmap.ic_expand_less);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (item1.isExpanded()) {
                            collapse(pos);
                        } else {

                            expand(pos);
                        }
                        }
                });
                break;
            case TYPE_LEVEL_1:
                final Expand1Item item2 = (Expand1Item) item;
                helper.setText(R.id.Text_diqu,item2.play_name)
                        .setText(R.id.Text_left,item2.home_team)
                        .setText(R.id.Text_right,item2.guest_team)
                        .setText(R.id.Text_bianhao,item2.sequence_no)
                        .setText(R.id.Text_data,"截止"+item2.begin_time)
                        .setText(R.id.Text_top,item2.home_score)
                        .setText(R.id.Text_bottom,item2.guest_score);

                if(Integer.parseInt(item2.home_score)<0){

                    helper.setBackgroundRes(R.id.Text_top,R.drawable.jianone_rang);

                }else if(Integer.parseInt(item2.home_score)>0){
                    helper.setBackgroundRes(R.id.Text_top,R.drawable.jiaone_rang);

                }else if(Integer.parseInt(item2.home_score)==0){
                    helper.setBackgroundRes(R.id.Text_top,R.drawable.zero_rang);

                }
                if(Integer.parseInt(item2.guest_score)<0){
                    helper.setBackgroundRes(R.id.Text_bottom,R.drawable.jianone_rang);

                }else if(Integer.parseInt(item2.guest_score)>0){
                    helper.setBackgroundRes(R.id.Text_bottom,R.drawable.jiaone_rang);

                }else if(Integer.parseInt(item2.guest_score)==0){
                    helper.setBackgroundRes(R.id.Text_bottom,R.drawable.zero_rang);



                }



                helper.addOnClickListener(R.id.RecyclerView_Mixed_Bet_football);
                RecyclerView rv = helper.getView(R.id.RecyclerView_Mixed_Bet_football);
                rv.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(),3));

//                    QuickAdapter quickAdapter = new QuickAdapter(item2.list_choosebena.get(helper.getAdapterPosition()-1).getOnelist());
//                    Log.e("YZS",helper.getAdapterPosition()+"");
                QuickAdapter quickAdapter = new QuickAdapter(item2.list_one,1);
                rv.setAdapter(quickAdapter);
                list_choose = item2.list_choosebena;



                helper.getView(R.id.layout_low).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_m(item2,helper.itemView.getContext(),helper.getLayoutPosition()-1);
//
                    }
                });







                break;

        }

    }
    public List<ChooseMixedBean> getList(){


        return list_choose;
    }



    public void onResh(){
        for (int i = 0; i <list_choose.size() ; i++) {
            List<ItemPoint> onelist = list_choose.get(i).getOnelist();
            List<ItemPoint> twolist = list_choose.get(i).getTwolist();
            List<ItemPoint> threelist = list_choose.get(i).getThreelist();
            List<ItemPoint> fourlist = list_choose.get(i).getFourlist();

            for (int j = 0; j <onelist.size() ; j++) {
                onelist.get(j).setIsselect(false);


            }
            for (int j = 0; j <twolist.size() ; j++) {
                twolist.get(j).setIsselect(false);


            }for (int j = 0; j <threelist.size() ; j++) {
                threelist.get(j).setIsselect(false);


            }for (int j = 0; j <fourlist.size() ; j++) {
                fourlist.get(j).setIsselect(false);


            }
        }



    }




    @SuppressLint("NewApi")
    public void dialog_m(final Expand1Item item, Context context,int postion) {
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(context);
        alterDiaglog.setView(R.layout.layout_dialog_mixed);//加载进去
        final AlertDialog dialog = alterDiaglog.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //获取屏幕宽度，9.0的dialog是不会主动居中的，这里做了处理
        DisplayMetrics dm = MyApplication.getContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        //放在show()之后，不然有些属性是没有效果的，比如height和width
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // 设置宽度
        p.width = (int) (width * 0.95); // 宽度设置为屏幕的0.95
        p.gravity = Gravity.CENTER;//设置位置
        //p.alpha = 0.8f;//设置透明度
        dialogWindow.setAttributes(p);

        TextView name_left = dialog.findViewById(R.id.name_left);
        TextView name_right = dialog.findViewById(R.id.name_right);
        TextView victor = dialog.findViewById(R.id.victor);
        TextView loser = dialog.findViewById(R.id.loser);
        victor.setText(item.home_score);
        loser.setText(item.guest_score);
        name_left.setText(item.home_team);
        name_right.setText(item.guest_team);
        LinearLayout layout_cancel = dialog.findViewById(R.id.layout_cancel);
        LinearLayout layout_sure = dialog.findViewById(R.id.layout_sure);
        layout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


                EventBus.getDefault().post(new MainMessage(getnumber()+""));




            }
        });
        layout_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                flag_dialog = 1;
                notifyDataSetChanged();
                EventBus.getDefault().post(new MainMessage(getnumber()+""));
            }
        });
        RecyclerView one_rv = dialog.findViewById(R.id.one_rv);
        RecyclerView two_rv = dialog.findViewById(R.id.two_rv);
        RecyclerView three_rv = dialog.findViewById(R.id.three_rv);
        RecyclerView four_rv = dialog.findViewById(R.id.four_rv);

        one_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),3));
//        one_rv.setAdapter(new QuickAdapter(item.list_choosebena.get(postion).getOnelist()));
        one_rv.setAdapter(new QuickAdapter(item.list_one,1));

//        two_rv.setAdapter(new QuickAdapter_two(item.list_choosebena.get(postion).getTwolist()));
        two_rv.setAdapter(new QuickAdapter_two(item.list_two,1));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MyApplication.getContext(),7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int zhi = 0;
                if(position==12){
                    zhi = 2;
                }else if(position==17){
                    zhi = 3;

                }else if(position==30){
                    zhi = 2;

                }else {
                    zhi=1;
                }

                return zhi;
            }
        });
        two_rv.setLayoutManager(gridLayoutManager);
        three_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),4));
//        three_rv.setAdapter(new QuickAdapter_three(item.list_choosebena.get(postion).getThreelist()));
        three_rv.setAdapter(new QuickAdapter_three(item.list_three,1));

        four_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),5));
//        four_rv.setAdapter(new QuickAdapter_four(item.list_choosebena.get(postion).getFourlist()));
        four_rv.setAdapter(new QuickAdapter_four(item.list_four,1));

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {


            }
        });

    }
public static int getnumber(){

    list_subMixBean.clear();
    final List<ChooseMixedBean> list_chooe = list_choose;

    for (int i = 0; i <list_chooe.size(); i++) {
        list_adds = new ArrayList<>();
        List<ItemPoint> onelist = list_chooe.get(i).getOnelist();
        List<ItemPoint> twolist = list_chooe.get(i).getTwolist();
        List<ItemPoint> threelist = list_chooe.get(i).getThreelist();
        List<ItemPoint> fourlist = list_chooe.get(i).getFourlist();
        for (int j = 0; j <onelist.size() ; j++) {
            if(onelist.get(j).isselect){
                list_adds.add(onelist.get(j).getGame_odds_id());
            }else {

            }

        }
        for (int j = 0; j <twolist.size() ; j++) {
            if(twolist.get(j).isselect){
                list_adds.add(twolist.get(j).getGame_odds_id());

            }else {

            }

        }
        for (int j = 0; j <threelist.size() ; j++) {
            if(threelist.get(j).isselect){
                list_adds.add(threelist.get(j).getGame_odds_id());

            }else {

            }

        }
        for (int j = 0; j <fourlist.size() ; j++) {
            if(fourlist.get(j).isselect){
                list_adds.add(fourlist.get(j).getGame_odds_id());

            }else {

            }

        }
        if(list_adds.size()==0){


        }else {
            SubMixBean subMixBean = new SubMixBean();
            subMixBean.setGame_id(list_chooe.get(i).getGame_id());
            subMixBean.setList(list_adds);

            list_subMixBean.add(subMixBean);




        }




    }
    Log.e("YZS",list_subMixBean.toString());
        return list_subMixBean.size();
}









}


