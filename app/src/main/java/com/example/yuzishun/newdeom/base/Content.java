package com.example.yuzishun.newdeom.base;


import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.model.ChooseBaskBean;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/9.
 */

public class Content {



    //token
    public static String ToKen="";

    //身份是否认证
    public static int authentication;

    //没办法的静态的足球数据
    public  static List<ChooseMixedBean> list_chooe;
    //没办法的静态的篮球数据
    public  static List<ChooseBaskBean> list_chooe_bask;

    //订单状态
    public static int order_flag = 0;
    //订单状态
    public static int order_flag_bask = 0;

}