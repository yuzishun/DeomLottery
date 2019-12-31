package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/26.
 */

public class OrderBean  {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"order_id":2,"order_type":0,"order_status":0,"pay_status":1,"order_price":"640.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 12:14:06"},{"order_id":3,"order_type":0,"order_status":0,"pay_status":1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 16:49:23"},{"order_id":4,"order_type":0,"order_status":0,"pay_status":1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 16:53:22"},{"order_id":5,"order_type":0,"order_status":0,"pay_status":1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 16:54:03"},{"order_id":6,"order_type":0,"order_status":0,"pay_status":1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 16:55:29"},{"order_id":7,"order_type":0,"order_status":0,"pay_status":1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 16:55:30"},{"order_id":8,"order_type":0,"order_status":0,"pay_status":1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 17:08:54"},{"order_id":9,"order_type":0,"order_status":0,"pay_status":1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 17:12:48"},{"order_id":11,"order_type":0,"order_status":0,"pay_status":1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 18:15:25"},{"order_id":12,"order_type":0,"order_status":0,"pay_status":1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 18:36:04"},{"order_id":13,"order_type":0,"order_status":-1,"pay_status":-1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 18:41:17"},{"order_id":14,"order_type":0,"order_status":-1,"pay_status":-1,"order_price":"32.00","bonus_price":"0.00","game_type":0,"create_time":"2019-05-22 18:43:41"}]
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
         * order_id : 2
         * order_type : 0
         * order_status : 0
         * pay_status : 1
         * order_price : 640.00
         * bonus_price : 0.00
         * game_type : 0
         * create_time : 2019-05-22 12:14:06
         */

        private int order_id;
        private int order_type;
        private int order_status;
        private int pay_status;
        private String order_price;
        private String bonus_price;
        private int game_type;
        private String create_time;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getBonus_price() {
            return bonus_price;
        }

        public void setBonus_price(String bonus_price) {
            this.bonus_price = bonus_price;
        }

        public int getGame_type() {
            return game_type;
        }

        public void setGame_type(int game_type) {
            this.game_type = game_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
