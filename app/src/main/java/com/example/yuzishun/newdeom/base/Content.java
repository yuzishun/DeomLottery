package com.example.yuzishun.newdeom.base;


import com.example.yuzishun.newdeom.main.adapter.Expand1Item;
import com.example.yuzishun.newdeom.model.BankMangmentBean;
import com.example.yuzishun.newdeom.model.BonusBean;
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

    //用户id
    public static String userid="";

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

    //静态的足球单关
    public static List<ChooseMixedBean> list_chooe_single;

    //订单状态
    public static int order_flag_single = 0;

    //订单状态
    public static int order_flag = 0;
    //订单状态
    public static int order_flag_bask = 0;
    //银行卡列表
    public static  List<BankMangmentBean.DataBean> list_bank = new ArrayList<>();

    //用户是否是站内用户
    public static int player;

    public static int Text_postion =0;
    public static int Text_postion_mixed =0;
    public static int Text_postion_mixed_basket =0;

    //投注页面显示哪一个
    public static int flag_betting_popwindow=0;

    //晒单id
    public static String back_id;

    //晒单刷新flag;
    public static int refre_flag=0;

    //奖金优化
    public static  List<BonusBean> lastPriceDescList;
    public static  int flag_bonus = 0;


}
