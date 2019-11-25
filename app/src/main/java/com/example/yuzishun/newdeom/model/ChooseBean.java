package com.example.yuzishun.newdeom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2019/11/22.
 */

public class ChooseBean implements Serializable{

    public List<SubMixBean> list_subMixBean_choose;
    public List<SubMixListBean> list_stbMixListBean;
    public List<MinAndMaxBean> list_min_and_max;

    public List<SubMixBean> getList_subMixBean_choose() {
        return list_subMixBean_choose;
    }

    public void setList_subMixBean_choose(List<SubMixBean> list_subMixBean_choose) {
        this.list_subMixBean_choose = list_subMixBean_choose;
    }

    public List<SubMixListBean> getList_stbMixListBean() {
        return list_stbMixListBean;
    }

    public void setList_stbMixListBean(List<SubMixListBean> list_stbMixListBean) {
        this.list_stbMixListBean = list_stbMixListBean;
    }

    public List<MinAndMaxBean> getList_min_and_max() {
        return list_min_and_max;
    }

    public void setList_min_and_max(List<MinAndMaxBean> list_min_and_max) {
        this.list_min_and_max = list_min_and_max;
    }
}
