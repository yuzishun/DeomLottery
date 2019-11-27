package com.example.yuzishun.newdeom.main.baskball;

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
    public List<ItemPoint> list_four;
    public List<ItemPoint> list_fire;

    public List<ChooseBaskBean> list_choosebena;
    public String game_no;
    public String desc;
    public String desc_victor;
    public int home_single;
    public int let_single;
    public int total_single;
    public Expand1Item_bask(String play_name, String home_team, String guest_team, String total_score, String let_score, String sequence_no, String begin_time
    ,List<ItemPoint> list_one,List<ItemPoint> list_two,List<ItemPoint> list_three,List<ItemPoint> list_four,List<ItemPoint> list_fire,List<ChooseBaskBean> list_choosebena,String game_no,String desc,String desc_victor,int home_single,int let_single,int total_single) {
        this.play_name = play_name;
        this.home_team = home_team;
        this.guest_team = guest_team;
        this.total_score = total_score;
        this.let_score = let_score;
        this.sequence_no = sequence_no;
        this.begin_time = begin_time;
        this.list_one = list_one;
        this.list_two = list_two;
        this.game_no = game_no;
        this.list_three = list_three;
        this.list_four = list_four;
        this.list_fire = list_fire;
        this.list_choosebena = list_choosebena;
        this.desc = desc;
        this.desc_victor = desc_victor;
        this.home_single = home_single;
        this.let_single = let_single;
        this.total_single = total_single;
    }

    @Override
    public int getItemType() {
        return BettingListAdapter.TYPE_LEVEL_1;
    }



}


