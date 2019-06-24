package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/6/21.
 */

public class BonusListBean {


    /**
     * code : 10000
     * msg : 查询成功!
     * data : [{"user_id":1,"uname":"小大","img_head":"http://192.168.1.9//default/default_img_head.png","bonus_price":"28121.60","order_win":3,"order_sum":21}]
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
         * user_id : 1
         * uname : 小大
         * img_head : http://192.168.1.9//default/default_img_head.png
         * bonus_price : 28121.60
         * order_win : 3
         * order_sum : 21
         */

        private int user_id;
        private String uname;
        private String img_head;
        private String bonus_price;
        private int order_win;
        private int order_sum;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

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

        public String getBonus_price() {
            return bonus_price;
        }

        public void setBonus_price(String bonus_price) {
            this.bonus_price = bonus_price;
        }

        public int getOrder_win() {
            return order_win;
        }

        public void setOrder_win(int order_win) {
            this.order_win = order_win;
        }

        public int getOrder_sum() {
            return order_sum;
        }

        public void setOrder_sum(int order_sum) {
            this.order_sum = order_sum;
        }
    }
}
