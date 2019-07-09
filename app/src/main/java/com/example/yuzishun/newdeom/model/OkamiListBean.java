package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/6/20.
 */

public class OkamiListBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"plan_id":80,"order_price":"8000.00","multiple_price":"2.00","order_status":1,"bonus_price":"0.00","plan_follow_price":"452.00","plan_follow_person":"3","cut_off_time":"2019-06-13 23:45:00"}]
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
         * plan_id : 80
         * order_price : 8000.00
         * multiple_price : 2.00
         * order_status : 1
         * bonus_price : 0.00
         * plan_follow_price : 452.00
         * plan_follow_person : 3
         * cut_off_time : 2019-06-13 23:45:00
         */
        private int game_type;
        private int plan_id;
        private String order_price;
        private String multiple_price;
        private int order_status;
        private String bonus_price;
        private String plan_follow_price;
        private String plan_follow_person;
        private String cut_off_time;
        private int cut_off;

        public int getCut_off() {
            return cut_off;
        }

        public void setCut_off(int cut_off) {
            this.cut_off = cut_off;
        }

        public int getGame_type() {
            return game_type;
        }

        public void setGame_type(int game_type) {
            this.game_type = game_type;
        }

        public int getPlan_id() {
            return plan_id;
        }

        public void setPlan_id(int plan_id) {
            this.plan_id = plan_id;
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

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public String getBonus_price() {
            return bonus_price;
        }

        public void setBonus_price(String bonus_price) {
            this.bonus_price = bonus_price;
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
