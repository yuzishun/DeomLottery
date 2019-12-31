package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/6/5.
 */

public class MainInfomationBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"info_no":"2019060447442","title":"周二004：弗拉已占先机 科林状态在线-竞彩网","create_time":"2019-06-04"}]
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
         * info_no : 2019060447442
         * title : 周二004：弗拉已占先机 科林状态在线-竞彩网
         * create_time : 2019-06-04
         */

        private String info_no;
        private String title;
        private String create_time;

        public String getInfo_no() {
            return info_no;
        }

        public void setInfo_no(String info_no) {
            this.info_no = info_no;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
