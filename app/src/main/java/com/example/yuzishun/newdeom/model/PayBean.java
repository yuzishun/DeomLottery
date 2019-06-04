package com.example.yuzishun.newdeom.model;

/**
 * Created by yuzishun on 2019/6/2.
 */

public class PayBean {


    /**
     * code : 10000
     * msg : 请求成功
     * data : {"pay":"192.168.1.9/plugins/pay/index.php","orderid":"201906021450439781899","amount":"12"}
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
         * pay : 192.168.1.9/plugins/pay/index.php
         * orderid : 201906021450439781899
         * amount : 12
         */

        private String pay;
        private String orderid;
        private String amount;

        public String getPay() {
            return pay;
        }

        public void setPay(String pay) {
            this.pay = pay;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
