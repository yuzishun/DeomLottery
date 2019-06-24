package com.example.yuzishun.newdeom.main.single;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.main.adapter.BettingListAdapter;
import com.example.yuzishun.newdeom.main.adapter.Expand1Item;

/**
 * Created by yuzishun on 2019/5/22.
 */

public class Item_Single extends AbstractExpandableItem<Item1_Single> implements MultiItemEntity {
    public String title;

    public Item_Single(String title) {
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

