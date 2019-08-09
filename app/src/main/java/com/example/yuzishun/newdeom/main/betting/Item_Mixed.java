package com.example.yuzishun.newdeom.main.betting;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.main.adapter.BettingListAdapter;
import com.example.yuzishun.newdeom.main.single.Item1_Single;

/**
 * Created by yuzishun on 2019/5/22.
 */

public class Item_Mixed extends AbstractExpandableItem<Item1_Mixed> implements MultiItemEntity {
    public String title;

    public Item_Mixed(String title) {
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

