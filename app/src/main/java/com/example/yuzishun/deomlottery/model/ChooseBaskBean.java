package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/21.
 */

public class ChooseBaskBean {




    private String game_id;
    private String name;
    private List<ItemPoint> onelist;
    private List<ItemPoint> twolist;
    private List<ItemPoint> threelist;
    private List<ItemPoint> fourlist;
    private List<ItemPoint> firelist;
    private String desc;
    public String home_team;
    public String guest_team;
    public String home_score;
    public String guest_score;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ItemPoint> getFirelist() {
        return firelist;
    }

    public void setFirelist(List<ItemPoint> firelist) {
        this.firelist = firelist;
    }

    public String getHome_score() {
        return home_score;
    }

    public void setHome_score(String home_score) {
        this.home_score = home_score;
    }

    public String getGuest_score() {
        return guest_score;
    }

    public void setGuest_score(String guest_score) {
        this.guest_score = guest_score;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getGuest_team() {
        return guest_team;
    }

    public void setGuest_team(String guest_team) {
        this.guest_team = guest_team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public List<ItemPoint> getOnelist() {
        return onelist;
    }

    public void setOnelist(List<ItemPoint> onelist) {
        this.onelist = onelist;
    }

    public List<ItemPoint> getTwolist() {
        return twolist;
    }

    public void setTwolist(List<ItemPoint> twolist) {
        this.twolist = twolist;
    }

    public List<ItemPoint> getThreelist() {
        return threelist;
    }

    public void setThreelist(List<ItemPoint> threelist) {
        this.threelist = threelist;
    }

    public List<ItemPoint> getFourlist() {
        return fourlist;
    }

    public void setFourlist(List<ItemPoint> fourlist) {
        this.fourlist = fourlist;
    }

        @Override
        public String toString() {
            return "DataBean{" +
                    "postion=" + game_id +
                    ", onelist=" + onelist +
                    ", twolist=" + twolist +
                    ", threelist=" + threelist +
                    ", fourlist=" + fourlist +
                    '}';
        }
    }


