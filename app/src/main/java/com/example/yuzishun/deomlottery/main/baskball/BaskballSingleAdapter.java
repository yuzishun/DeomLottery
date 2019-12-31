package com.example.yuzishun.deomlottery.main.baskball;

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
import com.example.yuzishun.deomlottery.R;
import com.example.yuzishun.deomlottery.base.MyApplication;
import com.example.yuzishun.deomlottery.main.activity.AnalysisActivity;
import com.example.yuzishun.deomlottery.model.ChooseBaskBean;
import com.example.yuzishun.deomlottery.model.ItemPoint;
import com.example.yuzishun.deomlottery.model.SubMixBean;
import com.example.yuzishun.deomlottery.utils.eventbus.BasketAdapterMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/26.
 */

public class BaskballSingleAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    public static final int  BASKET_TYPE_LEVEL_0 = 0;
    public static final int  BASKET_TYPE_LEVEL_1 = 1;
    public static List<ChooseBaskBean> list_choose_single_bask = new ArrayList<>();
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
    public BaskballSingleAdapter(List<MultiItemEntity> data, int mixed) {
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
                final ExpandMixed_bask item1 = (ExpandMixed_bask) item;
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

                final Expand1Mixed_bask item2 = (Expand1Mixed_bask) item;
                helper.setText(R.id.Text_diqu,item2.play_name)
                        .setText(R.id.Text_left,item2.guest_team)
                        .setText(R.id.Text_right,item2.home_team)
                        .setText(R.id.Text_bianhao,item2.sequence_no)
                        .setText(R.id.Text_data,"截止"+item2.stop_time);
                ImageView image_single = helper.getView(R.id.image_single);

                LinearLayout layout_victorcha = helper.getView(R.id.layout_victorcha);
                LinearLayout layout_analysis = helper.getView(R.id.layout_analysis);
                RecyclerView recyclerView_bask = helper.getView(R.id.recyclerView_bask);
                TextView victor_text = helper.getView(R.id.victor_text);
                RelativeLayout layout_visito = helper.getView(R.id.layout_visito);
                LinearLayout layout_gone = helper.getView(R.id.layout_gone);
                LinearLayout layout_fire = helper.getView(R.id.layout_fire);
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
//

                recyclerView_bask.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(),2));
                MixedBaskAdapter mixedBaskAdapter;

                switch (mixed){
                    case 1:

                        layout_visito.setVisibility(View.VISIBLE);
                        layout_gone.setVisibility(View.GONE);
                        layout_fire.setVisibility(View.GONE);
                        text_center_socre.setVisibility(View.GONE);

                         mixedBaskAdapter = new MixedBaskAdapter(item2.list_mixed,1,1);
                        recyclerView_bask.setAdapter(mixedBaskAdapter);
                        break;
                    case 2:

                        layout_visito.setVisibility(View.VISIBLE);
                        layout_gone.setVisibility(View.GONE);
                        layout_fire.setVisibility(View.GONE);
                        text_center_socre.setVisibility(View.VISIBLE);
                        text_center_socre.setText(item2.let_score);
                         mixedBaskAdapter = new MixedBaskAdapter(item2.list_mixed,1,1);
                        recyclerView_bask.setAdapter(mixedBaskAdapter);

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
                        mixedBaskAdapter = new MixedBaskAdapter(item2.list_mixed,1,1);
                        recyclerView_bask.setAdapter(mixedBaskAdapter);
                        break;
                    case 5:
                        layout_visito.setVisibility(View.GONE);
                        layout_gone.setVisibility(View.GONE);
                        layout_fire.setVisibility(View.VISIBLE);

                        break;
                }

                int ii=0;
                for (int i = 0; i < item2.list_mixed.size(); i++) {
                    if(item2.list_mixed.get(i).isselect){

                        ii++;
                    }else {
                    }

                }
                if(mixed==3){
                    for (int i = 0; i < item2.list_mixed_bottom.size(); i++) {
                        if(item2.list_mixed_bottom.get(i).isselect){

                            ii++;
                        }else {
                        }

                    }
                }

                if(ii==0){
                    item2.desc= "展开更多选项";

                }
                if(item2.desc.equals("展开更多选项")){



                    button_Difference.setText(item2.desc+"");
                    button_Difference.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.edittext_hintcolor));
                    button_Difference.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_false));

                }else {


                    button_Difference.setText(item2.desc+"");
                    button_Difference.setTextColor(helper.itemView.getContext().getResources().getColor(R.color.white));
                    button_Difference.setBackground(helper.itemView.getContext().getResources().getDrawable(R.drawable.dialog_mixed_true));

                }



                button_Difference.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog(helper.itemView.getContext(),item2,helper.getLayoutPosition());


                    }
                });
                list_choose_single_bask = item2.list_choosebena;



                break;

        }


    }

    public List<ChooseBaskBean> getList(){


        return list_choose_single_bask;
    }

    public static String setMore(final Expand1Mixed_bask item){


        String desc = "";
        list_id = new ArrayList<>();
        List<ItemPoint> onelist = item.list_mixed;
        List<ItemPoint> bottomlist = item.list_mixed_bottom;


        for (int j = 0; j < onelist.size(); j++) {
            if(onelist.get(j).isselect) {

                        list_id.add(""+onelist.get(j).getId());


            }else {

            }

        }
        for (int j = 0; j < bottomlist.size(); j++) {
            if(bottomlist.get(j).isselect) {

                list_id.add(""+bottomlist.get(j).getId());


            }else {

            }

        }

        if(list_id.size()==0) {

            desc="展开更多选项";

        }else {
            String substring = list_id.toString();
                desc = substring;

        }



        return desc;

    }



    public void onResh(){
        for (int i = 0; i <list_choose_single_bask.size() ; i++) {
            List<ItemPoint> onelist = list_choose_single_bask.get(i).getOnelist();
            List<ItemPoint> twolist = list_choose_single_bask.get(i).getTwolist();


            for (int j = 0; j <onelist.size() ; j++) {
                onelist.get(j).setIsselect(false);


            }
            if(twolist.size()!=0){
                for (int j = 0; j <twolist.size() ; j++) {
                    twolist.get(j).setIsselect(false);


                }
            }



        }



    }


    @SuppressLint("NewApi")
    private void dialog(Context context,final Expand1Mixed_bask item,int postion) {

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

        one_rv.setAdapter(new QuickAdapter_basket(item.list_mixed,1,1));


        two_rv.setLayoutManager(new GridLayoutManager(MyApplication.getContext(),3));

        two_rv.setAdapter(new QuickAdapter_basket(item.list_mixed_bottom,1,1));

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
        final List<ChooseBaskBean> list_chooe = list_choose_single_bask;

        for (int i = 0; i <list_chooe.size(); i++) {
            list_adds = new ArrayList<>();
            List<ItemPoint> onelist = list_chooe.get(i).getOnelist();
            List<ItemPoint> twolist = list_chooe.get(i).getTwolist();

            for (int j = 0; j <onelist.size() ; j++) {
                if(onelist.get(j).isselect){
                    list_adds.add(onelist.get(j).getGame_odds_id());
                }else {

                }

            }
            if(twolist.size()!=0){
                for (int j = 0; j <twolist.size() ; j++) {
                    if(twolist.get(j).isselect){
                        list_adds.add(twolist.get(j).getGame_odds_id());

                    }else {

                    }

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
