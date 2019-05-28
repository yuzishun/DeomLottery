package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/27.
 */

public class DetailedBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"id":47,"detail_type":0,"deal_type":2,"deal_price":"6.00","user_account":"531.40","create_time":"2019-05-26 16:03:42"},{"id":19,"detail_type":1,"deal_type":3,"deal_price":"0.00","user_account":"396.70","create_time":"2019-05-24 16:26:15"},{"id":20,"detail_type":1,"deal_type":3,"deal_price":"0.00","user_account":"396.70","create_time":"2019-05-24 16:26:15"},{"id":21,"detail_type":1,"deal_type":3,"deal_price":"0.00","user_account":"396.70","create_time":"2019-05-24 16:26:15"},{"id":22,"detail_type":1,"deal_type":3,"deal_price":"0.00","user_account":"396.70","create_time":"2019-05-24 16:26:15"},{"id":23,"detail_type":1,"deal_type":3,"deal_price":"0.00","user_account":"396.70","create_time":"2019-05-24 16:26:15"},{"id":24,"detail_type":1,"deal_type":3,"deal_price":"0.00","user_account":"396.70","create_time":"2019-05-24 16:26:15"},{"id":25,"detail_type":1,"deal_type":3,"deal_price":"0.00","user_account":"396.70","create_time":"2019-05-24 16:26:15"},{"id":26,"detail_type":1,"deal_type":3,"deal_price":"0.00","user_account":"396.70","create_time":"2019-05-24 16:26:15"},{"id":17,"detail_type":1,"deal_type":3,"deal_price":"140.70","user_account":"390.00","create_time":"2019-05-24 16:26:15"},{"id":18,"detail_type":1,"deal_type":3,"deal_price":"0.00","user_account":"396.70","create_time":"2019-05-24 16:26:15"},{"id":15,"detail_type":1,"deal_type":5,"deal_price":"32.00","user_account":"256.00","create_time":"2019-05-22 18:43:47"},{"id":14,"detail_type":0,"deal_type":2,"deal_price":"32.00","user_account":"224.00","create_time":"2019-05-22 18:43:41"},{"id":13,"detail_type":1,"deal_type":5,"deal_price":"32.00","user_account":"256.00","create_time":"2019-05-22 18:42:19"},{"id":12,"detail_type":0,"deal_type":2,"deal_price":"32.00","user_account":"224.00","create_time":"2019-05-22 18:41:17"}]
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
         * id : 47
         * detail_type : 0
         * deal_type : 2
         * deal_price : 6.00
         * user_account : 531.40
         * create_time : 2019-05-26 16:03:42
         */

        private int id;
        private int detail_type;
        private int deal_type;
        private String deal_price;
        private String user_account;
        private String create_time;

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
    }
}
