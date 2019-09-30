package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/24.
 */

public class SubMixBean {
    public String game_id;

    public List<String> list;
    public List<Double> list_adds;
    public String name;
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getList_adds() {
        return list_adds;
    }

    public void setList_adds(List<Double> list_adds) {
        this.list_adds = list_adds;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
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
                "game_id='" + game_id + '\'' +
                ", list=" + list +
                '}';
    }
}
