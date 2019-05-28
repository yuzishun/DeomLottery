package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/24.
 */

public class SubMixListBean {

    public String name;

    public List<String> list;





    public String getGame_id() {
        return name;
    }

    public void setGame_id(String game_id) {
        this.name = game_id;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SubMixBean{" +
                "game_id='" + name + '\'' +
                ", list=" + list +
                '}';
    }

}
