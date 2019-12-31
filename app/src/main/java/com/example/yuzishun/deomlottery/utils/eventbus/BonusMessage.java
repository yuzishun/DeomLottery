package com.example.yuzishun.deomlottery.utils.eventbus;

/**
 * Created by yuzishun on 2019/5/22.
 */

public class BonusMessage {
    private int postion;
    private int amount;
    public int getPostion() {
        return postion;
    }

    public int getAmount() {
        return amount;
    }



    public BonusMessage(int postion,int amount) {
        this.postion = postion;
        this.amount = amount;
    }


}

