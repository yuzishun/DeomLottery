package com.example.yuzishun.newdeom.main.adapter.baskball;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.main.adapter.BettingListAdapter;
import com.example.yuzishun.newdeom.model.ChooseBaskBean;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.FootballBean;
import com.example.yuzishun.newdeom.model.ItemPoint;

import java.util.List;

/**
 * Created by luoxw on 2016/8/10.
 */

public class Expand1Item_bask implements MultiItemEntity  {
    public String play_name;
    public String home_team;
    public String guest_team;
    public String total_score;
    public String let_score;
    public String sequence_no;
    public String begin_time;
    public List<ItemPoint> list_one;
    public List<ItemPoint> list_two;
    public List<ItemPoint> list_three;
    public List<ChooseBaskBean> list_choosebena;



    public Expand1Item_bask(String play_name, String home_team, String guest_team, String total_score, String let_score, String sequence_no, String begin_time
    ,List<ItemPoint> list_one,List<ItemPoint> list_two,List<ItemPoint> list_three,List<ChooseBaskBean> list_choosebena) {
        this.play_name = play_name;
        this.home_team = home_team;
        this.guest_team = guest_team;
        this.total_score = total_score;
        this.let_score = let_score;
        this.sequence_no = sequence_no;
        this.begin_time = begin_time;
        this.list_one = list_one;
        this.list_two = list_two;
        this.list_three = list_three;
        this.list_choosebena = list_choosebena;

    }

    @Override
    public int getItemType() {
        return BettingListAdapter.TYPE_LEVEL_1;
    }



}


