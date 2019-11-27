package com.example.yuzishun.newdeom.main.baskball;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.example.yuzishun.newdeom.base.Content;
import com.example.yuzishun.newdeom.base.MyApplication;
import com.example.yuzishun.newdeom.main.activity.AnalysisActivity;
import com.example.yuzishun.newdeom.model.ChooseBaskBean;
import com.example.yuzishun.newdeom.model.ChooseBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.MinAndMaxBean;
import com.example.yuzishun.newdeom.model.SubMixBean;
import com.example.yuzishun.newdeom.model.SubMixListBean;
import com.example.yuzishun.newdeom.model.SureguanBean;
import com.example.yuzishun.newdeom.utils.eventbus.BasketAdapterMessage;
import com.example.yuzishun.newdeom.utils.eventbus.BasketMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/26.
 */

public class BaskballAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    public static final int  BASKET_TYPE_LEVEL_0 = 0;
    public static final int  BASKET_TYPE_LEVEL_1 = 1;
    public static List<ChooseBaskBean> list_choose = new ArrayList<>();
    private static List<String> list_id;
    private int mixed = 5;
    //判断选择的有几场，傻瓜方法
    private static List<String> list_adds;
    private static List<SubMixBean> list_subMixBean = new ArrayList<>();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaskballAdapter(List<MultiItemEntity> data,int mixed) {
        super(data);
        addItemType(BASKET_TYPE_LEVEL_0, R.layout.recyclerview_base_father);
        addItemType(BASKET_TYPE_LEVEL_1, R.layout.recyclerview_basketball_son);
        this.mixed  =  mixed;
    }

    @SuppressLint("NewApi")
    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()){
            case BASKET_TYPE_LEVEL_0:
                final ExpandItem_bask item1 = (ExpandItem_bask) item;
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

            case BASKET_TYPE_LEVEL_1:

                final Expand1Item_bask item2 = (Expand1Item_bask) item;
                helper.setText(R.id.Text_diqu,item2.play_name)
                        .setText(R.id.Text_left,item2.guest_team)
                        .setText(R.id.Text_right,item2.home_team)
                        .setText(R.id.Text_bianhao,item2.sequence_no)
                        .setText(R.id.Text_data,"截止"+item2.begin_time)
                        .setText(R.id.home_socre,item2.let_score)
                        .setText(R.id.let_socre,item2.total_score).setText(R.id.button_zero,item2.list_one.get(1).getId()+item2.list_one.get(1).getOdds())
                        .setText(R.id.button_one,item2.list_one.get(0).getId()+item2.list_one.get(0).getOdds()).setText(R.id.button_two,item2.list_four.get(1).getId()+item2.list_four.get(1).getOdds()
                        ).setText(R.id.button_three,item2.list_four.get(0).getId()+item2.list_four.get(0).getOdds()).setText(R.id.button_four,item2.list_fire.get(1).getId()+item2.list_fire.get(1).getOdds())
                        .setText(R.id.button_fire,item2.list_fire.get(0).getId()+item2.list_fire.get(0).getOdds());
                        LinearLayout layout_victorcha = helper.getView(R.id.layout_victorcha);
                LinearLayout layout_analysis = helper.getView(R.id.layout_analysis);
                RecyclerView recyclerView_bask = helper.getView(R.id.recyclerView_bask);
                TextView victor_text = helper.getView(R.id.victor_text);
                ImageView image_single = helper.getView(R.id.image_single);

                RelativeLayout layout_visito = helper.getView(R.id.layout_visito);
                LinearLayout layout_gone = helper.getView(R.id.layout_gone);
                LinearLayout layout_fire = helper.getView(R.id.layout_fire);
                RelativeLayout layout_top = helper.getView(R.id.layout_top);
                RelativeLayout layout_top_ = helper.getView(R.id.layout_top_);
                RelativeLayout layout_center_ = helper.getView(R.id.layout_center_);
                RelativeLayout layout_bottom_ = helper.getView(R.id.layout_bottom_);
                Button button_Difference = helper.getView(R.id.button_Difference);
                TextView text_center_socre = helper.getView(R.id.text_center_socre);
                layout_analysis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(helper.itemView.getContext(), AnalysisActivity.class);
                        intent.putExtra("game_no",item2.game_no+"");
                        intent.putExtra("flag",1);
                        helper.itemView.getContext().startActivity(intent);

                    }
                });
                        Button button_zero =  helper.getView(R.id.button_zero);
                        Button button_one =  helper.getView(R.id.button_one);

                        Button button_two =  helper.getView(R.id.button_two);

                        Button button_three =  helper.getView(R.id.button_three);

                        Button button_four =  helper.getView(R.id.button_four);
                        Button button_fire =  helper.getView(R.id.button_fire);
                recyclerView_bask.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(),2));
                MixedBaskAdapter mixedBaskAdapter;


                switch (mixed){
                    case 1:
                        layout_visito.setVisibility(View.VISIBLE);
                        layout_gone.setVisibility(View.GONE);
                        layout_fire.setVisibility(View.GONE);
                        text_center_socre.setVisibility(View.GONE);
                        if(item2.list_one.get(0).getSingle()==0){
                            layout_top.setVisibility(View.GONE);
                            image_single.setVisibility(View.GONE);

                        }else {
                            layout_top.setVisibility(View.VISIBLE);
                            image_single.setVisibility(View.VISIBLE);

                        }
                         mixedBaskAdapter = new MixedBaskAdapter(item2.list_one,1,0);
                        recyclerView_bask.setAdapter(mixedBaskAdapter);
                        break;
                    case 2:
                        layout_visito.setVisibility(View.VISIBLE);
                        layout_gone.setVisibility(View.GONE);
                        layout_fire.setVisibility(View.GONE);
                        text_center_socre.setVisibility(View.VISIBLE);
                        text_center_socre.setText(item2.let_score);
                         mixedBaskAdapter = new MixedBaskAdapter(item2.list_four,1,0);
                        recyclerView_bask.setAdapter(mixedBaskAdapter);
                        if(item2.list_four.get(0).getSingle()==0){
                            layout_top.setVisibility(View.GONE);
                            image_single.setVisibility(View.GONE);

                        }else {
                            layout_top.setVisibility(View.VISIBLE);
                            image_single.setVisibility(View.VISIBLE);

                        }
                        break;
                    case 3:
                        layout_visito.setVisibility(View.GONE);
                        layout_gone.setVisibility(View.VISIBLE);
                        layout_fire.setVisibility(View.GONE);

                        break;
                    case 4:
                        layout_visito.setVisibility(View.VISIBLE);
                        layout_gone.setVisibility(View.GONE);
                        layout_fire.setVisibility(View.GONE);
                        text_center_socre.setVisibility(View.VISIBLE);
                        text_center_socre.setText(item2.total_score);
                        mixedBaskAdapter = new MixedBaskAdapter(item2.list_fire,1,0);
                        recyclerView_bask.setAdapter(mixedBaskAdapter);
                        if(item2.list_fire.get(0).getSingle()==0){
                            layout_top.setVisibility(View.GONE);
                            image_single.setVisibility(View.GONE);

                        }else {
                            layout_top.setVisibility(View.VISIBLE);
                            image_single.setVisibility(View.VISIBLE);

                        }
                        break;
                    case 5:
                        layout_visito.setVisibility(View.GONE);
                        layout_gone.setVisibility(View.GONE);
                        layout_fire.setVisibility(View.VISIBLE);
                        if(item2.list_one.get(0).getSingle()==0){
                            layout_top_.setVisibility(View.GONE);

                        }else {
                            layout_top_.setVisibility(View.VISIBLE);

                        }
                        if(item2.list_four.get(0).getSingle()==0){
                            layout_center_.setVisibility(View.GONE);

                        }else {
                            layout_center_.setVisibility(View.VISIBLE);

                        }
                        if(item2.list_fire.get(0).getSingle()==0){
                            layout_bottom_.setVisibility(View.GONE);

                        }else {
                            layout_bottom_.setVisibility(View.VISIBLE);

                        }
                        if(item2.home_single==1||item2.let_single==1||item2.total_single==1){
                            image_single.setVisibility(View.VISIBLE);
                        }else {
                            image_single.setVisibility(View.GONE);

                        }
                        break;
                }
                gettext(item2.list_one.get(0).getId(),item2.list_one.get(0).getOdds(),button_one);
                gettext(item2.list_one.get(1).getId(),item2.list_one.get(1).getOdds(),button_zero);

                gettext(item2.list_four.get(1).getId(),item2.list_four.get(1).getOdds(),button_two);

                gettext(item2.list_four.get(0).getId(),item2.list_four.get(0).getOdds(),button_three);

                gettext(item2.list_fire.get(1).getId(),item2.list_fire.get(1).getOdds(),button_four);

                gettext(item2.list_fire.get(0).getId(),item2.list_fire.get(0).getOdds(),button_fire);




                getisselect(item2.list_one.get(1).isselect,button_zero);
                getisselect(item2.list_one.get(0).isselect,button_one);
                getisselect(item2.list_four.get(1).isselect,button_two);
                getisselect(item2.list_four.get(0).isselect,button_three);
                getisselect(item2.list_fire.get(1).isselect,button_four);
                getisselect(item2.list_fire.get(0).isselect,button_fire);

                getonclick(item2,button_zero,1,item2.list_one,helper.getLayoutPosition());
                getonclick(item2,button_one,0,item2.list_one,helper.getLayoutPosition());
                getonclick(item2,button_two,1,item2.list_four,helper.getLayoutPosition());
                getonclick(item2,button_three,0,item2.list_four,helper.getLayoutPosition());
                getonclick(item2,button_four,1,item2.list_fire,helper.getLayoutPosition());
                getonclick(item2,button_fire,0,item2.list_fire,helper.getLayoutPosition());




                int ii=0;
                for (int i = 0; i < item2.list_one.size(); i++) {
                    if(item2.list_one.get(i).isselect){

                        ii++;
                    }else {
                    }

                }
                for (int i = 0; i < item2.list_two.size(); i++) {
                    if(item2.list_two.get(i).isselect){

                        ii++;
                    }else {
                    }

                }
                for (int i = 0; i < item2.list_three.size(); i++) {
                    if(item2.list_three.get(i).isselect){

                        ii++;
                    }else {
                    }

                }
                for (int i = 0; i < item2.list_four.size(); i++) {
                    if(item2.list_four.get(i).isselect){

                        ii++;
                    }else {
                    }

                }
                for (int i = 0; i < item2.list_fire.size(); i++) {
                    if(item2.list_fire.get(i).isselect){

                        ii++;
                    }else {
                    }

                }
                if(ii==0){
                    item2.desc= "展开更多选项";
                    item2.desc_victor="展开更多选项";

                }
                if(item2.desc.equals("展开更多选项")){
                    victor_text.setText(item2.desc+"");

                    victor_text.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.edittext_hintcolor));
                    layout_victorcha.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_false));


                    button_Difference.setText(item2.desc_victor+"");
                    button_Difference.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.edittext_hintcolor));
                    button_Difference.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_false));

                }else {
                    victor_text.setText(item2.desc+"");
                    victor_text.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.white));
                    layout_victorcha.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_true));

                    button_Difference.setText(item2.desc_victor+"");
                    button_Difference.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.white));
                    button_Difference.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_true));

                }


                layout_victorcha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    dialog(helper.itemView.getContext(),item2,helper.getLayoutPosition());

                    }
                });
                button_Difference.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog(helper.itemView.getContext(),item2,helper.getLayoutPosition());


                    }
                });
                list_choose = item2.list_choosebena;



                break;

        }


    }

    public List<ChooseBaskBean> getList(){


        return list_choose;
    }




    public ChooseBean getChooseList(){
        List<ChooseBaskBean> list_chooe = getList();

         List<Double>  one_mix_and_min;
         List<Double> two_mix_and_min;
         List<Double> three_mix_and_min;
         List<Double> four_mix_and_min;
         List<Double> fire_mix_and_min;
         List<Double> minlist;
         List<Integer> list_single;
         List<String> list_adds;
         List<String> list_id;
         List<SubMixBean> list_subMixBean = new ArrayList<>();
         List<SubMixListBean> list_stbMixListBean = new ArrayList<>();
         List<MinAndMaxBean> list_min_and_max = new ArrayList<>();

        for (int i = 0; i <list_chooe.size(); i++) {
            list_adds = new ArrayList<>();
            list_id = new ArrayList<>();
            minlist = new ArrayList<>();
            one_mix_and_min = new ArrayList<>();
            two_mix_and_min = new ArrayList<>();
            three_mix_and_min = new ArrayList<>();
            four_mix_and_min = new ArrayList<>();
            fire_mix_and_min = new ArrayList<>();
            list_single = new ArrayList<>();
            List<ItemPoint> onelist = list_chooe.get(i).getOnelist();
            List<ItemPoint> twolist = list_chooe.get(i).getTwolist();
            List<ItemPoint> threelist = list_chooe.get(i).getThreelist();
            List<ItemPoint> fourlist = list_chooe.get(i).getFourlist();
            List<ItemPoint> firelist = list_chooe.get(i).getFirelist();

            for (int j = 0; j <onelist.size() ; j++) {
                if(onelist.get(j).isselect){
                    list_adds.add(onelist.get(j).getGame_odds_id());
                    minlist.add(Double.parseDouble(onelist.get(j).getOdds()));
                    list_id.add("胜负:"+onelist.get(j).getId());
                    one_mix_and_min.add(Double.parseDouble(onelist.get(j).getOdds()));
                    list_single.add(onelist.get(j).getSingle());
                }

            }
            for (int j = 0; j <twolist.size() ; j++) {
                if(twolist.get(j).isselect){
                    list_adds.add(twolist.get(j).getGame_odds_id());
                    list_id.add("胜分差:"+twolist.get(j).getId());
                    two_mix_and_min.add(Double.parseDouble(twolist.get(j).getOdds()));
                    minlist.add(Double.parseDouble(twolist.get(j).getOdds()));
                    list_single.add(twolist.get(j).getSingle());

                }

            }
            for (int j = 0; j <threelist.size() ; j++) {
                if(threelist.get(j).isselect){
                    list_adds.add(threelist.get(j).getGame_odds_id());
                    list_id.add("胜分差:"+threelist.get(j).getId());
                    two_mix_and_min .add(Double.parseDouble(threelist.get(j).getOdds()));
                    minlist.add(Double.parseDouble(threelist.get(j).getOdds()));
                    list_single.add(threelist.get(j).getSingle());

                }

            }
            for (int j = 0; j <fourlist.size() ; j++) {
                if(fourlist.get(j).isselect){
                    list_adds.add(fourlist.get(j).getGame_odds_id());
                    list_id.add("让分胜负:"+fourlist.get(j).getId());
                    four_mix_and_min.add(Double.parseDouble(fourlist.get(j).getOdds()));
                    minlist.add(Double.parseDouble(fourlist.get(j).getOdds()));
                    list_single.add(fourlist.get(j).getSingle());

                }

            }
            for (int j = 0; j <firelist.size() ; j++) {
                if(firelist.get(j).isselect){
                    list_adds.add(firelist.get(j).getGame_odds_id());
                    list_id.add("大小分:"+firelist.get(j).getId());
                    fire_mix_and_min.add(Double.parseDouble(firelist.get(j).getOdds()));
                    minlist.add(Double.parseDouble(firelist.get(j).getOdds()));
                    list_single.add(firelist.get(j).getSingle());

                }

            }

            if(list_adds.size()==0){


            }else {
                SubMixBean subMixBean = new SubMixBean();
                subMixBean.setGame_id(list_chooe.get(i).getGame_id());
                subMixBean.setList(list_adds);

                subMixBean.setList_single(list_single);
                list_subMixBean.add(subMixBean);

                SubMixListBean subMixListBean = new SubMixListBean();
                subMixListBean.setGame_id(list_chooe.get(i).getName());
                subMixListBean.setList(list_id);
                list_stbMixListBean.add(subMixListBean);

                MinAndMaxBean minAndMaxBean  = new MinAndMaxBean();


                minAndMaxBean.setOne_mix_and_min(one_mix_and_min);
                minAndMaxBean.setTwo_mix_and_min(two_mix_and_min);
                minAndMaxBean.setThree_mix_and_min(three_mix_and_min);
                minAndMaxBean.setFour_mix_and_min(four_mix_and_min);
                minAndMaxBean.setMinlist(minlist);

                minAndMaxBean.setFire_mix_and_min(fire_mix_and_min);
                list_min_and_max.add(minAndMaxBean);
            }

        }




        ChooseBean chooseBean = new ChooseBean();
        chooseBean.setList_min_and_max(list_min_and_max);
        chooseBean.setList_stbMixListBean(list_stbMixListBean);
        chooseBean.setList_subMixBean_choose(list_subMixBean);
        return chooseBean;
    }




    public static String setMore(final Expand1Item_bask item,int desc_victor){


        String desc = "";
        list_id = new ArrayList<>();
        List<ItemPoint> onelist = item.list_one;
        List<ItemPoint> twolist = item.list_two;
        List<ItemPoint> threelist = item.list_three;
        List<ItemPoint> fourlist = item.list_four;
        List<ItemPoint> firelist = item.list_fire;

        for (int j = 0; j < onelist.size(); j++) {
            if(onelist.get(j).isselect) {

                        list_id.add(""+onelist.get(j).getId());


            }else {

            }

        }
        for (int j = 0; j < twolist.size(); j++) {
            if(twolist.get(j).isselect){
                list_id.add(twolist.get(j).getId());
            }else {

            }


        }

        for (int j = 0; j < threelist.size(); j++) {
            if(threelist.get(j).isselect){
                list_id.add(threelist.get(j).getId());
            }else {

            }




        }
        for (int j = 0; j < fourlist.size(); j++) {
            if(fourlist.get(j).isselect){
                list_id.add(fourlist.get(j).getId());
            }else {

            }




        }
        for (int j = 0; j < firelist.size(); j++) {
            if(firelist.get(j).isselect){
                list_id.add(firelist.get(j).getId());
            }else {

            }




        }


        if(list_id.size()==0) {

            desc="展开更多选项";

        }else {
            String substring = list_id.toString();
            if(desc_victor==0){
                desc = substring;
            }else {
                desc="已选中"+list_id.size()+"项";

            }

        }



        return desc;

    }



    public void onResh(){
        for (int i = 0; i <list_choose.size() ; i++) {
            List<ItemPoint> onelist = list_choose.get(i).getOnelist();
            List<ItemPoint> twolist = list_choose.get(i).getTwolist();
            List<ItemPoint> threelist = list_choose.get(i).getThreelist();
            List<ItemPoint> fourlist = list_choose.get(i).getFourlist();
            List<ItemPoint> firelist = list_choose.get(i).getFirelist();

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
            for (int j = 0; j <firelist.size() ; j++) {
                firelist.get(j).setIsselect(false);

            }


        }



    }


    public void getonclick(final Expand1Item_bask item_bask, Button button, final int i,List<ItemPoint> list,int postion){


    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(list.get(i).isselect){
                list.get(i).setIsselect(false);

                notifyDataSetChanged();



                EventBus.getDefault().post(new BasketMessage(BaskballAdapter.getnumber()+""));
                String s = setMore(item_bask,1);
                EventBus.getDefault().post(new BasketAdapterMessage(postion));


                item_bask.desc=s;
            }else {
                list.get(i).setIsselect(true);

                notifyDataSetChanged();

                EventBus.getDefault().post(new BasketMessage(BaskballAdapter.getnumber()+""));
                String s = setMore(item_bask,1);
                EventBus.getDefault().post(new BasketAdapterMessage(postion));


                item_bask.desc=s;
            }


        }
    });




    }

    public void getisselect(boolean isselect,Button button){
        if(isselect){

            button.setTextColor(MyApplication.getContext().getResources().getColor(R.color.white));
            button.setBackgroundResource(R.drawable.dialog_mixed_true);
        }else {
            button.setTextColor(MyApplication.getContext().getResources().getColor(R.color.font_black));

            button.setBackgroundResource(R.drawable.dialog_mixed_false);

        }


    }


    public void gettext(String id,String odds,Button button){

        if(odds.equals("")){
            button.setText("暂未开售");
            button.setEnabled(false);

        }else {
            button.setEnabled(true);

            button.setText(id+odds);

        }





    }

    @SuppressLint("NewApi")
    private void dialog(Context context,final Expand1Item_bask item,int postion) {

        AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(context);
        alterDiaglog.setView(R.layout.layout_dialog_basket_mixed);//加载进去
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
        ImageView layout_analysis_in_bask = dialog.findViewById(R.id.layout_analysis_in_bask);

        name_left.setText(item.guest_team);
        name_right.setText(item.home_team);





        LinearLayout layout_cancel = dialog.findViewById(R.id.layout_cancel);
        LinearLayout layout_sure = dialog.findViewById(R.id.layout_sure);
        layout_analysis_in_bask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnalysisActivity.class);
                intent.putExtra("game_no",item.game_no+"");
                intent.putExtra("flag",1);
                context.startActivity(intent);
            }
        });
        layout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
        layout_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });
        RecyclerView one_rv = dialog.findViewById(R.id.one_rv);
        RecyclerView two_rv = dialog.findViewById(R.id.two_rv);
        one_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),3));

        one_rv.setAdapter(new QuickAdapter_basket(item.list_two,1,0));


        two_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),3));

        two_rv.setAdapter(new QuickAdapter_basket(item.list_three,1,0));

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                String s = setMore(item,1);
                String desc_victor = setMore(item,0);

                EventBus.getDefault().post(new BasketAdapterMessage(postion));

                item.desc_victor = desc_victor;
                item.desc=s;
            }
        });


    }



    public static int getnumber(){

        list_subMixBean.clear();
        final List<ChooseBaskBean> list_chooe = list_choose;

        for (int i = 0; i <list_chooe.size(); i++) {
            list_adds = new ArrayList<>();
            List<ItemPoint> onelist = list_chooe.get(i).getOnelist();
            List<ItemPoint> twolist = list_chooe.get(i).getTwolist();
            List<ItemPoint> threelist = list_chooe.get(i).getThreelist();
            List<ItemPoint> fourlist = list_chooe.get(i).getFourlist();
            List<ItemPoint> firelist = list_chooe.get(i).getFirelist();
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
            for (int j = 0; j <firelist.size() ; j++) {
                if(firelist.get(j).isselect){
                    list_adds.add(firelist.get(j).getGame_odds_id());

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
