package com.example.yuzishun.deomlottery.main.baskball;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.deomlottery.main.adapter.BettingListAdapter;

/**
 * Created by yuzishun on 2019/5/22.
 */

public class ExpandMixed_bask extends AbstractExpandableItem<Expand1Mixed_bask> implements MultiItemEntity {
    public String title;

    public ExpandMixed_bask(String title) {
        this.title = title;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return BettingListAdapter.TYPE_LEVEL_0;
    }
}

