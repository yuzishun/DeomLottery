package com.example.yuzishun.deomlottery.model;

/**
 * Created by yuzishun on 2019/6/2.
 */

public class PayBean {


    /**
     * code : 10000
     * msg : 请求成功
     * data : {"order_id":"201908081116082422586","amount":"11","url":"http://192.168.1.26/pay/pay.php"}
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
         * order_id : 201908081116082422586
         * amount : 11
         * url : http://192.168.1.26/pay/pay.php
         */

        private String order_id;
        private String amount;
        private String url;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
