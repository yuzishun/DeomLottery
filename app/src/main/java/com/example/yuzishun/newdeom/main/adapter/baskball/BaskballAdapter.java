package com.example.yuzishun.newdeom.main.adapter.baskball;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.base.MyApplication;
import com.example.yuzishun.newdeom.main.activity.AnalysisActivity;
import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.main.betting.Expand1Item_football;
import com.example.yuzishun.newdeom.model.ChooseBaskBean;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.SubMixBean;
import com.example.yuzishun.newdeom.utils.eventbus.AdapterMessage;
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

    //判断选择的有几场，傻瓜方法
    private static List<String> list_adds;
    private static List<SubMixBean> list_subMixBean = new ArrayList<>();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaskballAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(BASKET_TYPE_LEVEL_0, R.layout.recyclerview_base_father);
        addItemType(BASKET_TYPE_LEVEL_1, R.layout.recyclerview_basketball_son);

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
                TextView victor_text = helper.getView(R.id.victor_text);
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

                }
                if(item2.desc.equals("展开更多选项")){
                    victor_text.setText(item2.desc+"");

                    victor_text.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.edittext_hintcolor));
                    layout_victorcha.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.login_radios_border_sure_white));

                }else {
                    victor_text.setText(item2.desc+"");
                    victor_text.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.white));
                    layout_victorcha.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.login_radios_border_sure_red));

                }


                layout_victorcha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

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

    public static String setMore(final Expand1Item_bask item){


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
            desc="已选中"+list_id.size()+"项";

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
                String s = setMore(item_bask);
                EventBus.getDefault().post(new BasketAdapterMessage(postion));


                item_bask.desc=s;
            }else {
                list.get(i).setIsselect(true);

                notifyDataSetChanged();

                EventBus.getDefault().post(new BasketMessage(BaskballAdapter.getnumber()+""));
                String s = setMore(item_bask);
                EventBus.getDefault().post(new BasketAdapterMessage(postion));


                item_bask.desc=s;
            }


        }
    });




    }

    public void getisselect(boolean isselect,Button button){
        if(isselect){

            button.setTextColor(MyApplication.getContext().getResources().getColor(R.color.white));
            button.setBackgroundResource(R.drawable.login_radios_border_sure_red);
        }else {
            button.setTextColor(MyApplication.getContext().getResources().getColor(R.color.font_black));

            button.setBackgroundResource(R.drawable.login_radios_border_sure_white);

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


        name_left.setText(item.guest_team);
        name_right.setText(item.home_team);





        LinearLayout layout_cancel = dialog.findViewById(R.id.layout_cancel);
        LinearLayout layout_sure = dialog.findViewById(R.id.layout_sure);
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

        one_rv.setAdapter(new QuickAdapter_basket(item.list_two,1));


        two_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),3));

        two_rv.setAdapter(new QuickAdapter_basket(item.list_three,1));

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                String s = setMore(item);
                EventBus.getDefault().post(new BasketAdapterMessage(postion));


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
