package com.example.yuzishun.newdeom.main.single;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.R;
import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.main.adapter.ExpandItem;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.ItemPoint;
import com.example.yuzishun.newdeom.model.SubMixBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/6/20.
 */

public class BettingSingleAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {
    public static final int  TYPE_LEVEL_0 = 0;
    public static final int  TYPE_LEVEL_1 = 1;
    public static List<ChooseMixedBean> list_choose_single = new ArrayList<>();
    //判断选择的有几场，傻瓜方法
    private static List<String> list_adds;
    private static List<SubMixBean> list_subMixBean = new ArrayList<>();
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BettingSingleAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.recyclerview_base_father);
        addItemType(TYPE_LEVEL_1,R.layout.recyclerview_single_son);

    }

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
                rv.setLayoutManager(new GridLayoutManager(helper.itemView.getContext(),3));
                SingleAdapter quickAdapter = new SingleAdapter(item2.list_single,1);
                rv.setAdapter(quickAdapter);
                list_choose_single = item2.list_choosebena;


                break;


        }
        }


    public void onResh(){

        for (int i = 0; i <list_choose_single.size() ; i++) {
            List<ItemPoint> onelist = list_choose_single.get(i).getOnelist();
            for (int j = 0; j <onelist.size() ; j++) {
                onelist.get(j).setIsselect(false);


            }
        }


    }
    public List<ChooseMixedBean> getList(){


        return list_choose_single;
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
