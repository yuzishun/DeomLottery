package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/27.
 */

public class DetailedBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"id":1166,"detail_type":0,"deal_type":2,"deal_price":"0.00","user_account":"151923171.38","create_time":"2019-06-05 19:57:41","pay_status":"","withdraw_status":""}]
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
         * id : 1166
         * detail_type : 0
         * deal_type : 2
         * deal_price : 0.00
         * user_account : 151923171.38
         * create_time : 2019-06-05 19:57:41
         * pay_status :
         * withdraw_status :
         */

        private int id;
        private int detail_type;
        private int deal_type;
        private String deal_price;
        private String user_account;
        private String create_time;
        private String pay_status;
        private String withdraw_status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDetail_type() {
            return detail_type;
        }

        public void setDetail_type(int detail_type) {
            this.detail_type = detail_type;
        }

        public int getDeal_type() {
            return deal_type;
        }

        public void setDeal_type(int deal_type) {
            this.deal_type = deal_type;
        }

        public String getDeal_price() {
            return deal_price;
        }

        public void setDeal_price(String deal_price) {
            this.deal_price = deal_price;
        }

        public String getUser_account() {
            return user_account;
        }

        public void setUser_account(String user_account) {
            this.user_account = user_account;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getWithdraw_status() {
            return withdraw_status;
        }

        public void setWithdraw_status(String withdraw_status) {
            this.withdraw_status = withdraw_status;
        }
    }
}
