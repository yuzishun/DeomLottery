package com.example.yuzishun.newdeom.main.betting;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.main.adapter.BettingListAdapter;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.model.ItemPoint;

import java.util.List;

/**
 * Created by luoxw on 2016/8/10.
 */

public class Item1_Mixed implements MultiItemEntity  {
    public String play_name;
    public String home_team;
    public String guest_team;
    public String home_score;
    public String guest_score;
    public String sequence_no;
    public String stop_time;
    public String game_no;
    public List<ItemPoint> list_single;
    public List<ChooseMixedBean> list_choosebena;
    public String desc;
    public Item1_Mixed(String play_name, String home_team, String guest_team, String home_score, String guest_score, String sequence_no, String stop_time
    , List<ItemPoint> list_single, List<ChooseMixedBean> list_choosebena, String desc, String game_no) {
        this.play_name = play_name;
        this.home_team = home_team;
        this.guest_team = guest_team;
        this.home_score = home_score;
        this.guest_score = guest_score;
        this.sequence_no = sequence_no;
        this.stop_time = stop_time;
        this.desc = desc;
        this.game_no = game_no;
        this.list_single = list_single;
        this.list_choosebena = list_choosebena;
    }

    @Override
    public int getItemType() {
        return BettingListAdapter.TYPE_LEVEL_1;
    }



}


