package com.example.yuzishun.newdeom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/28.
 */

public class MinAndMaxBean implements Serializable{
    private List<Double> one_mix_and_min = new ArrayList<>();
    private List<Double> two_mix_and_min = new ArrayList<>();
    private List<Double> three_mix_and_min = new ArrayList<>();
    private List<Double> four_mix_and_min = new ArrayList<>();
    private List<Double> fire_mix_and_min = new ArrayList<>();
    private List<Double> minlist = new ArrayList<>();
    private double max;
    private double min;

    public List<Double> getMinlist() {
        return minlist;
    }

    public void setMinlist(List<Double> minlist) {
        this.minlist = minlist;
    }

    public List<Double> getTwo_mix_and_min() {
        return two_mix_and_min;
    }

    public void setTwo_mix_and_min(List<Double> two_mix_and_min) {
        this.two_mix_and_min = two_mix_and_min;
    }

    public List<Double> getThree_mix_and_min() {
        return three_mix_and_min;
    }

    public void setThree_mix_and_min(List<Double> three_mix_and_min) {
        this.three_mix_and_min = three_mix_and_min;
    }

    public List<Double> getFour_mix_and_min() {
        return four_mix_and_min;
    }

    public void setFour_mix_and_min(List<Double> four_mix_and_min) {
        this.four_mix_and_min = four_mix_and_min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public List<Double> getOne_mix_and_min() {
        return one_mix_and_min;
    }

    public void setOne_mix_and_min(List<Double> one_mix_and_min) {
        this.one_mix_and_min = one_mix_and_min;
    }

    public List<Double> getFire_mix_and_min() {
        return fire_mix_and_min;
    }

    public void setFire_mix_and_min(List<Double> fire_mix_and_min) {
        this.fire_mix_and_min = fire_mix_and_min;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
}
