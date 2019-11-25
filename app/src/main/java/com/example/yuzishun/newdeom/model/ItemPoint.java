package com.example.yuzishun.newdeom.model;

/**
 * Created by yuzishun on 2019/5/23.
 */

public class ItemPoint {
    public String name;
    public String game_odds_id;
    public String odds;
    public boolean isselect;
    public String id;
    public int Single;
    public int isonclick=0;
    public boolean isclick=true;

    public int getSingle() {
        return Single;
    }

    public void setSingle(int single) {
        Single = single;
    }

    public boolean isIsclick() {
        return isclick;
    }

    public void setIsclick(boolean isclick) {
        this.isclick = isclick;
    }

    public int getIsonclick() {
        return isonclick;
    }

    public void setIsonclick(int isonclick) {
        this.isonclick = isonclick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGame_odds_id() {
        return game_odds_id;
    }

    public void setGame_odds_id(String game_odds_id) {
        this.game_odds_id = game_odds_id;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public boolean isIsselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }
}
