package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/6/21.
 */

public class ProfitBean {

    /**
     * code : 10000
     * msg : 查询成功!
     * data : [{"user_id":46,"uname":"阿斯顿","img_head":"http://192.168.1.9//default/default_img_head.png","earnings":147,"hit":"43%"}]
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
         * user_id : 46
         * uname : 阿斯顿
         * img_head : http://192.168.1.9//default/default_img_head.png
         * earnings : 147
         * hit : 43%
         */

        private int user_id;
        private String uname;
        private String img_head;
        private String earnings;
        private String hit;

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

        public String getEarnings() {
            return earnings;
        }

        public void setEarnings(String earnings) {
            this.earnings = earnings;
        }

        public String getHit() {
            return hit;
        }

        public void setHit(String hit) {
            this.hit = hit;
        }
    }
}
