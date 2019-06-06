package com.example.yuzishun.newdeom.base;


import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.model.BankMangmentBean;
import com.example.yuzishun.newdeom.model.ChooseBaskBean;
import com.example.yuzishun.newdeom.model.ChooseMixedBean;
import com.example.yuzishun.newdeom.my.activity.SettingActivity;
import com.example.yuzishun.newdeom.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzishun on 2019/5/9.
 */

public class Content {



    //token

    public static String ToKen="";

    //用户头像
    public static String userurl = "";


    //用户姓名
    public static String username = "";


    //用户手机号
    public static String userphone="";

    //身份是否认证
    public static int authentication;

    //没办法的静态的足球数据
    public  static List<ChooseMixedBean> list_chooe;
    //没办法的静态的篮球数据
    public  static List<ChooseBaskBean> list_chooe_bask;

    //篮球选择的场次

    //订单状态
    public static int order_flag = 0;
    //订单状态
    public static int order_flag_bask = 0;
    //银行卡列表
    public static  List<BankMangmentBean.DataBean> list_bank = new ArrayList<>();

}
