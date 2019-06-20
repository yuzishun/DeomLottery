package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/30.
 */

public class DocumentaryBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"plan_id":8,"uname":"ashun","img_head":"http:192.168.1.9/upload/20190521/b7f8497599868d36a62433b2daaedd2d.jpg","game_type":0,"order_price":"21060.00","multiple_price":"7020.000000","plan_profits":"0.10","order_id":64,"plan_desc":"秀","plan_follow_price":"105300.00","plan_follow_person":"1","cut_off_time":"2019-05-28 18:15:00"}]
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
         * plan_id : 8
         * uname : ashun
         * img_head : http:192.168.1.9/upload/20190521/b7f8497599868d36a62433b2daaedd2d.jpg
         * game_type : 0
         * order_price : 21060.00
         * multiple_price : 7020.000000
         * plan_profits : 0.10
         * order_id : 64
         * plan_desc : 秀
         * plan_follow_price : 105300.00
         * plan_follow_person : 1
         * cut_off_time : 2019-05-28 18:15:00
         */
        private String user_id;
        private int plan_id;
        private String uname;
        private String img_head;
        private int game_type;
        private String order_price;
        private String multiple_price;
        private String plan_profits;
        private int order_id;
        private String plan_desc;
        private String plan_follow_price;
        private String plan_follow_person;
        private String cut_off_time;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getPlan_id() {
            return plan_id;
        }

        public void setPlan_id(int plan_id) {
            this.plan_id = plan_id;
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

        public int getGame_type() {
            return game_type;
        }

        public void setGame_type(int game_type) {
            this.game_type = game_type;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getMultiple_price() {
            return multiple_price;
        }

        public void setMultiple_price(String multiple_price) {
            this.multiple_price = multiple_price;
        }

        public String getPlan_profits() {
            return plan_profits;
        }

        public void setPlan_profits(String plan_profits) {
            this.plan_profits = plan_profits;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getPlan_desc() {
            return plan_desc;
        }

        public void setPlan_desc(String plan_desc) {
            this.plan_desc = plan_desc;
        }

        public String getPlan_follow_price() {
            return plan_follow_price;
        }

        public void setPlan_follow_price(String plan_follow_price) {
            this.plan_follow_price = plan_follow_price;
        }

        public String getPlan_follow_person() {
            return plan_follow_person;
        }

        public void setPlan_follow_person(String plan_follow_person) {
            this.plan_follow_person = plan_follow_person;
        }

        public String getCut_off_time() {
            return cut_off_time;
        }

        public void setCut_off_time(String cut_off_time) {
            this.cut_off_time = cut_off_time;
        }
    }
}
