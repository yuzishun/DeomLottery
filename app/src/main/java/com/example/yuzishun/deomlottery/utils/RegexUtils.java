package com.example.yuzishun.deomlottery.utils;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yuzishun on 2018/8/1.
 */
//正则表达式工具类
public class RegexUtils {

    private Context context;

    public RegexUtils(Context context) {
        this.context = context;
    }

    //判断手机号是否合法
    public  boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^(()(14[0-9])|()(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(16[0-9])|(19[0-9])|(12[0-9])|(11[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }
    //判断密码是否合格
    public  boolean isPassWord(String inputText){
//        Pattern z1_ = Pattern.compile("^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$");
        Pattern z1_ = Pattern.compile("^.{6,16}$");

        boolean PassWord = z1_.matcher(inputText).matches();
        return PassWord;
    }

    /**
     * 验证输入的名字是否为“中文”或者是否包含“·”
     */
    public  boolean isLegalName(String name){
        if (name.contains("·") || name.contains("•")){
            if (name.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]+$")){
                return true;
            }else {
                return false;
            }
        }else {
            if (name.matches("^[\\u4e00-\\u9fa5]+$")){
                return true;
            }else {
                return false;
            }
        }
    }




            
    /**
     * 验证输入的身份证号是否合法
     */
    public  boolean isLegalId(String id){
        if (id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")){
            return true;
        }else {
            return false;
        }
    }


    //验证银行卡号
    public  boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }
    //从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
    public  char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');

    }
}
