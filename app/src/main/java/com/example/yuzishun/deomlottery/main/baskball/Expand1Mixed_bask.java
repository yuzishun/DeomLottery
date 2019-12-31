package com.example.yuzishun.deomlottery.main.baskball;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.deomlottery.main.adapter.BettingListAdapter;
import com.example.yuzishun.deomlottery.model.ChooseBaskBean;
import com.example.yuzishun.deomlottery.model.ItemPoint;

import java.util.List;

/**
 * Created by luoxw on 2016/8/10.
 */

public class Expand1Mixed_bask implements MultiItemEntity  {
    public String play_name;
    public String home_team;
    public String guest_team;
    public String total_score;
    public String let_score;
    public String sequence_no;
    public String stop_time;
    public List<ItemPoint> list_mixed;
    public List<ItemPoint> list_mixed_bottom;

    public List<ChooseBaskBean> list_choosebena;
    public String game_no;
    public String desc;

    public Expand1Mixed_bask(String play_name, String home_team, String guest_team, String total_score, String let_score, String sequence_no, String stop_time
    , List<ItemPoint> list_mixed,List<ItemPoint> list_mixed_bottom, List<ChooseBaskBean> list_choosebena, String game_no, String desc) {
        this.play_name = play_name;
        this.home_team = home_team;
        this.guest_team = guest_team;
        this.total_score = total_score;
        this.let_score = let_score;
        this.sequence_no = sequence_no;
        this.stop_time = stop_time;
        this.list_mixed = list_mixed;
        this.list_mixed_bottom = list_mixed_bottom;
        this.game_no = game_no;

        this.list_choosebena = list_choosebena;
        this.desc = desc;
    }

    @Override
    public int getItemType() {
        return BettingListAdapter.TYPE_LEVEL_1;
    }



}


