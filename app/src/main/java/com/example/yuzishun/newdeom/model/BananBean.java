package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/6/14.
 */

public class BananBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"img_location":"http://192.168.1.9/banner/20190614/a23fd1bb98395cbbef5971b036f68b94.jpg","url_location":"http://192.168.1.9/ "}]
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
         * img_location : http://192.168.1.9/banner/20190614/a23fd1bb98395cbbef5971b036f68b94.jpg
         * url_location : http://192.168.1.9/
         */

        private String img_location;
        private String url_location;

        public String getImg_location() {
            return img_location;
        }

        public void setImg_location(String img_location) {
            this.img_location = img_location;
        }

        public String getUrl_location() {
            return url_location;
        }

        public void setUrl_location(String url_location) {
            this.url_location = url_location;
        }
    }
}
