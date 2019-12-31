package com.example.yuzishun.deomlottery.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/24.
 */

public class SubMixBean implements Serializable{
    public String game_id;

    public List<String> list;
    public List<Double> list_adds;
    private List<String> list_name_bonus;
    private List<String> list_style_bonus;
    public String name;
    public String type;
    public List<Integer> list_single;

    public List<Integer> getList_single() {
        return list_single;
    }

    public void setList_single(List<Integer> list_single) {
        this.list_single = list_single;
    }



    public List<String> getList_name_bonus() {
        return list_name_bonus;
    }

    public void setList_name_bonus(List<String> list_name_bonus) {
        this.list_name_bonus = list_name_bonus;
    }

    public List<String> getList_style_bonus() {
        return list_style_bonus;
    }

    public void setList_style_bonus(List<String> list_style_bonus) {
        this.list_style_bonus = list_style_bonus;
    }

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
