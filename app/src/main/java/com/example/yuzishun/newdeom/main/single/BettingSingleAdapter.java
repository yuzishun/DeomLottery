package com.example.yuzishun.newdeom.main.single;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.MyApplication;
import com.example.yuzishun.newdeom.main.activity.AnalysisActivity;
import com.example.yuzishun.newdeom.model.ChooseBean;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.MinAndMaxBean;
import com.example.yuzishun.newdeom.model.SubMixBean;
import com.example.yuzishun.newdeom.model.SubMixListBean;
import com.example.yuzishun.newdeom.utils.eventbus.SinglePostionMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/6/20.
 */

public class BettingSingleAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    public static final int  TYPE_LEVEL_0 = 0;
    public static final int  TYPE_LEVEL_1 = 1;
    public static List<ChooseMixedBean> list_choose_single = new ArrayList<>();
    private static List<String> list_id;

    //判断选择的有几场，傻瓜方法
    private static List<String> list_adds;
    private int single=0,type = 0;
    private static List<SubMixBean> list_subMixBean = new ArrayList<>();
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BettingSingleAdapter(List<MultiItemEntity> data,int single,int type) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.recyclerview_base_father);
        addItemType(TYPE_LEVEL_1,R.layout.recyclerview_single_son);
        this.single = single;
        this.type = type;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                final Item_Single item_single = (Item_Single) item;
                helper.setText(R.id.tv_status,item_single.title)
                        .setBackgroundRes(R.id.status_image,item_single.isExpanded() ? R.mipmap.ic_expand_more:R.mipmap.ic_expand_less);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (item_single.isExpanded()) {
                            collapse(pos);
                        } else {

                            expand(pos);
                        }
                    }
                });


                break;
            case TYPE_LEVEL_1:
                final Item1_Single item2 = (Item1_Single) item;
                helper.setText(R.id.Text_diqu,item2.play_name)
                        .setText(R.id.Text_left,item2.home_team)
                        .setText(R.id.Text_right,item2.guest_team)
                        .setText(R.id.Text_bianhao,item2.sequence_no)
                        .setText(R.id.Text_data,"截止"+item2.stop_time);
                RecyclerView rv = helper.getView(R.id.RecyclerView_Mixed_Bet_football);
                RecyclerView rv_zong = helper.getView(R.id.RecyclerView_Mixed_Bet_football_zong);
                LinearLayout layout_analysis = helper.getView(R.id.layout_analysis);
                RelativeLayout layout_red = helper.getView(R.id.layout_red);
                RelativeLayout layout_top = helper.getView(R.id.layout_top);
                ImageView image_single = helper.getView(R.id.image_single);

                layout_analysis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(helper.itemView.getContext(), AnalysisActivity.class);
                        intent.putExtra("game_no",item2.game_no+"");
                        intent.putExtra("flag",0);

                        helper.itemView.getContext().startActivity(intent);

                    }
                });
                Button button_score = helper.getView(R.id.button_score);
                Button button_double = helper.getView(R.id.button_double);

                rv.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(),3));
                SingleAdapter quickAdapter = new SingleAdapter(item2.list_single,1);
                rv.setAdapter(quickAdapter);
                rv_zong.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(),4));
                SingleAdapter quickAdapter_zong = new SingleAdapter(item2.list_single,1);
                rv_zong.setAdapter(quickAdapter_zong);
                list_choose_single = item2.list_choosebena;
                if(single==1){
                    if(type==0){
                        if(item2.list_single.get(0).getSingle()==1){
                            layout_top.setVisibility(View.VISIBLE);
                            image_single.setVisibility(View.VISIBLE);
                        }else {
                            layout_top.setVisibility(View.GONE);
                            image_single.setVisibility(View.GONE);

                        }
                    }

                    layout_red.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.VISIBLE);
                    button_score.setVisibility(View.GONE);
                    rv_zong.setVisibility(View.GONE);
                    button_double.setVisibility(View.GONE);

                }else if(single==3) {
                    rv.setVisibility(View.GONE);
                    button_score.setVisibility(View.VISIBLE);
                    rv_zong.setVisibility(View.GONE);
                    button_double.setVisibility(View.GONE);

                }else if(single==2){
                    if(type==0){
                        if(item2.list_single.get(0).getSingle()==1){
                            layout_top.setVisibility(View.VISIBLE);
                            image_single.setVisibility(View.VISIBLE);
                        }else {
                            layout_top.setVisibility(View.GONE);
                            image_single.setVisibility(View.GONE);

                        }
                    }
                    layout_red.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.VISIBLE);
                    rv_zong.setVisibility(View.GONE);
                    button_double.setVisibility(View.GONE);
                    button_score.setVisibility(View.GONE);
                }else if(single==4){
                    rv_zong.setVisibility(View.VISIBLE);
                    button_double.setVisibility(View.GONE);
                    rv.setVisibility(View.GONE);
                    button_score.setVisibility(View.GONE);

                }else if(single==5){
                    rv_zong.setVisibility(View.GONE);
                    button_double.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                    button_score.setVisibility(View.GONE);
                }
                int ii=0;
                for (int i = 0; i < item2.list_single.size(); i++) {
                    if(item2.list_single.get(i).isselect){

                        ii++;
                    }else {
                    }

                }
                if(ii==0){
                    item2.desc= "展开更多选项";

                }
                    if(item2.desc.equals("展开更多选项")){
                        button_score.setText(item2.desc+"");
                        button_score.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_false));

                        button_score.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.edittext_hintcolor));

                    }else {
                        button_score.setText(item2.desc+"");
                        button_score.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_true));
                        button_score.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.white));

                    }


                    if(item2.desc.equals("展开更多选项")){
                        button_double.setText(item2.desc+"");
                        button_double.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_false));

                        button_double.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.edittext_hintcolor));

                    }else {
                        button_double.setText(item2.desc+"");
                        button_double.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_true));
                        button_double.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.white));

                    }





                button_score.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    dialog_m(item2,helper.itemView.getContext(),helper.getLayoutPosition(),single);
                    }
                });
                button_double.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_m(item2,helper.itemView.getContext(),helper.getLayoutPosition(),single);

                    }
                });
                break;


        }
        }



    @SuppressLint("NewApi")
    public void dialog_m(final Item1_Single item, final Context context, final int postion,int single) {
        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(context);
        alterDiaglog.setView(R.layout.layout_dialog_sing_score);//加载进去
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
        TextView type_text = dialog.findViewById(R.id.type_text);
        ImageView layout_analysis_in = dialog.findViewById(R.id.layout_analysis_in);

        name_left.setText(item.home_team);
        name_right.setText(item.guest_team);
        LinearLayout layout_cancel = dialog.findViewById(R.id.layout_cancel);
        LinearLayout layout_sure = dialog.findViewById(R.id.layout_sure);
        layout_analysis_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnalysisActivity.class);
                intent.putExtra("game_no",item.game_no+"");
                intent.putExtra("flag",0);

                context.startActivity(intent);
            }
        });
        layout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                EventBus.getDefault().post(new SingleMessage(getnumber()+""));


            }
        });
        layout_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                notifyDataSetChanged();
                EventBus.getDefault().post(new SingleMessage(getnumber()+""));

            }
        });
        RecyclerView two_rv = dialog.findViewById(R.id.two_rv);
        two_rv.setAdapter(new QuickAdapter_single(item.list_single,1,1));

        if(single==3){
            type_text.setText("比分");
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
        }else if(single==5){
            type_text.setText("半全场");
            two_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),3));

        }

//        two_rv.setAdapter(new QuickAdapter_two(item.list_choosebena.get(postion).getTwolist()));



//        GridLayoutManager gridLayoutManager = new GridLayoutManager(MyApplication.getContext(),7);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                int zhi = 0;
//                if(position==12){
//                    zhi = 2;
//                }else if(position==17){
//                    zhi = 3;
//
//                }else if(position==30){
//                    zhi = 2;
//
//                }else {
//                    zhi=1;
//                }
//
//                return zhi;
//            }
//        });
//        two_rv.setLayoutManager(gridLayoutManager);


        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                String s = setMore(item);
                EventBus.getDefault().post(new SinglePostionMessage(postion));


                item.desc=s;
            }
        });

    }





    public static String setMore(final Item1_Single item){


        String desc = "";
        list_id = new ArrayList<>();
        List<ItemPoint> onelist = item.list_single;



        for (int j = 0; j < onelist.size(); j++) {
            if(onelist.get(j).isselect){
                list_id.add("比分:"+onelist.get(j).getId());
            }else {

            }


        }




        if(list_id.size()==0) {

            desc="展开更多选项";

        }else {
            String substring = list_id.toString();
            desc=substring;

        }



        return desc;

    }

    public void onResh(int flag){

        for (int i = 0; i <list_choose_single.size() ; i++) {
//

            List<ItemPoint> onelist = list_choose_single.get(i).getOnelist();
            for (int j = 0; j <onelist.size() ; j++) {
                onelist.get(j).setIsselect(false);


            }
        }


    }

    public List<ChooseMixedBean> getList(){


        return list_choose_single;
    }


    public ChooseBean getchooselist(){

        List<ChooseMixedBean> list_chooe_single = getList();
        List<String> list_adds;
        List<String> list_id;
        List<String> list_name_bonus;
        List<String> list_style_bonus;
        List<Double> minlist;
        List<Double> one_max;
        List<Integer> list_single;
        List<SubMixBean> list_subMixBean_choose = new ArrayList<>();
        List<SubMixListBean> list_stbMixListBean = new ArrayList<>();
        List<MinAndMaxBean> list_min_and_max = new ArrayList<>();

        for (int i = 0; i <list_chooe_single.size() ; i++) {
            list_adds = new ArrayList<>();
            list_id = new ArrayList<>();
            list_name_bonus = new ArrayList<>();
            list_style_bonus = new ArrayList<>();
            minlist = new ArrayList<>();
            one_max = new ArrayList<>();
            list_single = new ArrayList<>();

            List<ItemPoint> onelist = list_chooe_single.get(i).getOnelist();
            for (int j = 0; j < onelist.size(); j++) {

                if(onelist.get(j).isselect){
                    list_id.add(""+onelist.get(j).getId());
                    list_name_bonus.add(list_chooe_single.get(i).getHome_team()+"/"+onelist.get(j).getId());
                    list_style_bonus.add(onelist.get(j).getGame_odds_id());
                    list_single.add(onelist.get(j).getSingle());

                    one_max.add(Double.parseDouble(onelist.get(j).getOdds()));
                    minlist.add(Double.parseDouble(onelist.get(j).getOdds()));
                    list_adds.add(onelist.get(j).getGame_odds_id());

                }else {

                }

            }
            if(list_adds.size()==0){


            }else {

                SubMixBean subMixBean = new SubMixBean();
                subMixBean.setGame_id(list_chooe_single.get(i).getGame_id());
                subMixBean.setList(list_adds);
                subMixBean.setList_adds(minlist);
                subMixBean.setName(list_chooe_single.get(i).getHome_team()+list_id);
                subMixBean.setList_name_bonus(list_name_bonus);
                subMixBean.setList_style_bonus(list_style_bonus);
                subMixBean.setList_single(list_single);
                list_subMixBean_choose.add(subMixBean);

                SubMixListBean subMixListBean = new SubMixListBean();
                subMixListBean.setGame_id(list_chooe_single.get(i).getName());

                subMixListBean.setList(list_id);

                list_stbMixListBean.add(subMixListBean);

                MinAndMaxBean minAndMaxBean  = new MinAndMaxBean();


                minAndMaxBean.setOne_mix_and_min(one_max);

                minAndMaxBean.setMinlist(minlist);

                list_min_and_max.add(minAndMaxBean);

            }


        }

        ChooseBean chooseBean = new ChooseBean();
        chooseBean.setList_min_and_max(list_min_and_max);
        chooseBean.setList_stbMixListBean(list_stbMixListBean);
        chooseBean.setList_subMixBean_choose(list_subMixBean_choose);

        return chooseBean;





    }




    public static int getnumber() {
        list_subMixBean.clear();
        final List<ChooseMixedBean> list_chooe = list_choose_single;
        for (int i = 0; i <list_chooe.size() ; i++) {
            list_adds = new ArrayList<>();
            List<ItemPoint> onelist = list_chooe.get(i).getOnelist();

            for (int j = 0; j <onelist.size() ; j++) {
                if(onelist.get(j).isselect){
                    list_adds.add(onelist.get(j).getGame_odds_id());

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



        return list_subMixBean.size();
    }


    }
