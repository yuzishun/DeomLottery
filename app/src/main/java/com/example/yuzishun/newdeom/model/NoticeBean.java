package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/6/3.
 */

public class NoticeBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"uname":"ashun","bonus_price":"686.18"},{"uname":"阿斯顿","bonus_price":"343.09"},{"uname":"阿斯顿","bonus_price":"256.00"},{"uname":"ashun","bonus_price":"101.50"},{"uname":"ashun","bonus_price":"685.00"},{"uname":"ashun","bonus_price":"2284.50"},{"uname":"You","bonus_price":"612.00"},{"uname":"阿斯顿","bonus_price":"270.79"},{"uname":"You","bonus_price":"101.20"},{"uname":"ashun","bonus_price":"680.00"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uname : ashun
         * bonus_price : 686.18
         */

        private String uname;
        private String bonus_price;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getBonus_price() {
            return bonus_price;
        }

        public void setBonus_price(String bonus_price) {
            this.bonus_price = bonus_price;
        }
    }
}
