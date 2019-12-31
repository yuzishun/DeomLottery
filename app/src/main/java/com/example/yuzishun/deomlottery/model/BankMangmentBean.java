package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/20.
 */

public class BankMangmentBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"id":2,"create_time":"2019-05-17 12:29:45","card_no":"6216696200006221569","card_bank":"中国银行","card_abbreviation":"BOC"}]
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
         * id : 2
         * create_time : 2019-05-17 12:29:45
         * card_no : 6216696200006221569
         * card_bank : 中国银行
         * card_abbreviation : BOC
         */

        private int id;
        private String create_time;
        private String card_no;
        private String card_bank;
        private String card_abbreviation;
        private String card_img;

        public String getCard_img() {
            return card_img;
        }

        public void setCard_img(String card_img) {
            this.card_img = card_img;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getCard_bank() {
            return card_bank;
        }

        public void setCard_bank(String card_bank) {
            this.card_bank = card_bank;
        }

        public String getCard_abbreviation() {
            return card_abbreviation;
        }

        public void setCard_abbreviation(String card_abbreviation) {
            this.card_abbreviation = card_abbreviation;
        }
    }
}
