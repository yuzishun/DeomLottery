package com.example.yuzishun.deomlottery.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by apple on 2019/9/17.
 */

public class BonusBean implements Serializable{

    public Double oneBetBounsMoney;
    public String SingleAdds_id;
    public String SingleGame_id;
    public int number;
    public String SingleMatch;
    public HashMap<String,String> hashMap;
    public String StringName;

    public String getStringName() {
        return StringName;
    }

    public void setStringName(String stringName) {
        StringName = stringName;
    }

    public String getSingleMatch() {
        return SingleMatch;
    }

    public void setSingleMatch(String singleMatch) {
        SingleMatch = singleMatch;
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSingleAdds_id() {
        return SingleAdds_id;
    }

    public void setSingleAdds_id(String singleAdds_id) {
        SingleAdds_id = singleAdds_id;
    }

    public String getSingleGame_id() {
        return SingleGame_id;
    }

    public void setSingleGame_id(String singleGame_id) {
        SingleGame_id = singleGame_id;
    }

    public Double getOneBetBounsMoney() {
        return oneBetBounsMoney;
    }

    public void setOneBetBounsMoney(Double oneBetBounsMoney) {
        this.oneBetBounsMoney = oneBetBounsMoney;
    }





}
