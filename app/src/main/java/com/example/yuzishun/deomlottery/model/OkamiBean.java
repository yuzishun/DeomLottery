package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/6/20.
 */

public class OkamiBean {


    /**
     * code : 10000
     * msg : 查询成功!
     * data : {"uname":"小大","img_head":"","user_id":1,"attention":1,"fans":1,"icon":0,"sum":13,"win":0,"earnings":"0.00","red_man":"0","win_info":[3,3,3,3,3]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uname : 小大
         * img_head :
         * user_id : 1
         * attention : 1
         * fans : 1
         * icon : 0
         * sum : 13
         * win : 0
         * earnings : 0.00
         * red_man : 0
         * win_info : [3,3,3,3,3]
         */

        private String uname;
        private String img_head;
        private int user_id;
        private int attention;
        private int fans;
        private int icon;
        private int sum;
        private int win;
        private String earnings;
        private String red_man;
        private List<Integer> win_info;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getImg_head() {
            return img_head;
        }

        public void setImg_head(String img_head) {
            this.img_head = img_head;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getAttention() {
            return attention;
        }

        public void setAttention(int attention) {
            this.attention = attention;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public int getWin() {
            return win;
        }

        public void setWin(int win) {
            this.win = win;
        }

        public String getEarnings() {
            return earnings;
        }

        public void setEarnings(String earnings) {
            this.earnings = earnings;
        }

        public String getRed_man() {
            return red_man;
        }

        public void setRed_man(String red_man) {
            this.red_man = red_man;
        }

        public List<Integer> getWin_info() {
            return win_info;
        }

        public void setWin_info(List<Integer> win_info) {
            this.win_info = win_info;
        }
    }
}
