package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by apple on 2019/8/13.
 */

public class PayTestBean {

    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"tag":0,"pay_name":"支付宝(AM 8:00-PM 00:00)"},{"tag":1,"pay_name":"支付宝(24小时开放)"}]
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
         * tag : 0
         * pay_name : 支付宝(AM 8:00-PM 00:00)
         */

        private int tag;
        private String pay_name;
        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }
    }

}
