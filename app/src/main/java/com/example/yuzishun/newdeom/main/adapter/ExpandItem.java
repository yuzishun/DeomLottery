package com.example.yuzishun.newdeom.main.adapter;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.yuzishun.newdeom.model.FootballBean;

/**
 * Created by yuzishun on 2019/5/22.
 */

public class ExpandItem extends AbstractExpandableItem<Expand1Item> implements MultiItemEntity {
    public String title;

    public ExpandItem(String title) {
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

