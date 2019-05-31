package com.example.yuzishun.newdeom.main.adapter.baskball;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
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
import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.model.ChooseBaskBean;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.SubMixBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/26.
 */

public class BaskballAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    public static final int  BASKET_TYPE_LEVEL_0 = 0;
    public static final int  BASKET_TYPE_LEVEL_1 = 1;
    public static List<ChooseBaskBean> list_choose = new ArrayList<>();
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
                        .setText(R.id.Text_left,item2.home_team)
                        .setText(R.id.Text_right,item2.guest_team)
                        .setText(R.id.Text_bianhao,item2.sequence_no)
                        .setText(R.id.Text_data,"截止"+item2.begin_time)
                        .setText(R.id.home_socre,item2.let_score)
                        .setText(R.id.let_socre,item2.total_score).setText(R.id.button_zero,item2.list_one.get(0).getId()+item2.list_one.get(0).getOdds())
                        .setText(R.id.button_one,item2.list_one.get(1).getId()+item2.list_one.get(1).getOdds()).setText(R.id.button_two,item2.list_one.get(2).getId()+item2.list_one.get(2).getOdds()
                        ).setText(R.id.button_three,item2.list_one.get(3).getId()+item2.list_one.get(3).getOdds()).setText(R.id.button_four,item2.list_one.get(4).getId()+item2.list_one.get(4).getOdds())
                        .setText(R.id.button_fire,item2.list_one.get(5).getId()+item2.list_one.get(5).getOdds());
                        LinearLayout layout_victorcha = helper.getView(R.id.layout_victorcha);
                        Button button_zero =  helper.getView(R.id.button_zero);
                        Button button_one =  helper.getView(R.id.button_one);

                        Button button_two =  helper.getView(R.id.button_two);

                        Button button_three =  helper.getView(R.id.button_three);

                        Button button_four =  helper.getView(R.id.button_four);
                        Button button_fire =  helper.getView(R.id.button_fire);



                gettext(item2.list_one.get(0).getId(),item2.list_one.get(0).getOdds(),button_zero);
                gettext(item2.list_one.get(1).getId(),item2.list_one.get(1).getOdds(),button_one);

                gettext(item2.list_one.get(2).getId(),item2.list_one.get(2).getOdds(),button_two);

                gettext(item2.list_one.get(3).getId(),item2.list_one.get(3).getOdds(),button_three);

                gettext(item2.list_one.get(4).getId(),item2.list_one.get(4).getOdds(),button_four);

                gettext(item2.list_one.get(5).getId(),item2.list_one.get(5).getOdds(),button_fire);




                getisselect(item2.list_one.get(0).isselect,button_zero);
                getisselect(item2.list_one.get(1).isselect,button_one);
                getisselect(item2.list_one.get(2).isselect,button_two);
                getisselect(item2.list_one.get(3).isselect,button_three);
                getisselect(item2.list_one.get(4).isselect,button_four);
                getisselect(item2.list_one.get(5).isselect,button_fire);

                getonclick(item2,button_zero,0);
                getonclick(item2,button_one,1);
                getonclick(item2,button_two,2);
                getonclick(item2,button_three,3);
                getonclick(item2,button_four,4);
                getonclick(item2,button_fire,5);


                layout_victorcha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    dialog(helper.itemView.getContext(),item2);

                    }
                });

                list_choose = item2.list_choosebena;



                break;

        }


    }

    public List<ChooseBaskBean> getList(){


        return list_choose;
    }
    public void onResh(){
        for (int i = 0; i <list_choose.size() ; i++) {
            List<ItemPoint> onelist = list_choose.get(i).getOnelist();
            List<ItemPoint> twolist = list_choose.get(i).getTwolist();
            List<ItemPoint> threelist = list_choose.get(i).getThreelist();

            for (int j = 0; j <onelist.size() ; j++) {
                onelist.get(j).setIsselect(false);


            }
            for (int j = 0; j <twolist.size() ; j++) {
                twolist.get(j).setIsselect(false);


            }for (int j = 0; j <threelist.size() ; j++) {
                threelist.get(j).setIsselect(false);


            }
        }



    }


    public void getonclick(final Expand1Item_bask item_bask, Button button, final int i){


    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(item_bask.list_one.get(i).isselect){
                item_bask.list_one.get(i).setIsselect(false);

                notifyDataSetChanged();




            }else {
                item_bask.list_one.get(i).setIsselect(true);

                notifyDataSetChanged();


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
            button.setText("--");
            button.setEnabled(false);

        }else {

            button.setText(id+odds);

        }





    }

    @SuppressLint("NewApi")
    private void dialog(Context context,final Expand1Item_bask item) {

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


        name_left.setText(item.home_team);
        name_right.setText(item.guest_team);





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




    }



    public  int getnumber(){

        list_subMixBean.clear();
        final List<ChooseBaskBean> list_chooe = list_choose;

        for (int i = 0; i <list_chooe.size(); i++) {
            list_adds = new ArrayList<>();
            List<ItemPoint> onelist = list_chooe.get(i).getOnelist();
            List<ItemPoint> twolist = list_chooe.get(i).getTwolist();
            List<ItemPoint> threelist = list_chooe.get(i).getThreelist();
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
