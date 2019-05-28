package com.example.yuzishun.newdeom.main.adapter;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.FootballBean;
import com.example.yuzishun.newdeom.model.ItemPoint;

import java.io.Serializable;
import java.util.List;

/**
 * Created by luoxw on 2016/8/10.
 */

public class Expand1Item  implements MultiItemEntity  {
    public String play_name;
    public String home_team;
    public String guest_team;
    public String home_score;
    public String guest_score;
    public String sequence_no;
    public String begin_time;
    public List<FootballBean.DataBean> list_databean;
    public List<ChooseMixedBean> list_choosebena;
    public List<ItemPoint> list_one;
    public List<ItemPoint> list_two;
    public List<ItemPoint> list_three;
    public List<ItemPoint> list_four;

    public Expand1Item(String play_name, String home_team, String guest_team, String home_score, String guest_score, String sequence_no, String begin_time
    ,List<FootballBean.DataBean> list_databean,List<ItemPoint> list_one,List<ItemPoint> list_two,List<ItemPoint> list_three,List<ItemPoint> list_four,List<ChooseMixedBean> list_choosebena) {
        this.play_name = play_name;
        this.home_team = home_team;
        this.guest_team = guest_team;
        this.home_score = home_score;
        this.guest_score = guest_score;
        this.sequence_no = sequence_no;
        this.begin_time = begin_time;
        this.list_databean = list_databean;
        this.list_one = list_one;
        this.list_two = list_two;
        this.list_three = list_three;
        this.list_four = list_four;
        this.list_choosebena = list_choosebena;
    }

    @Override
    public int getItemType() {
        return BettingListAdapter.TYPE_LEVEL_1;
    }



}


