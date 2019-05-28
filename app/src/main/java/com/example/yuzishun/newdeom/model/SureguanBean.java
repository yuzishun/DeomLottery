package com.example.yuzishun.newdeom.model;

/**
 * Created by yuzishun on 2019/5/25.
 */

public class SureguanBean {

   public String name;
   public boolean isselect = false;
   public String bunch;

    public String getBunch() {
        return bunch;
    }

    public void setBunch(String bunch) {
        this.bunch = bunch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }
}
