package com.example.yuzishun.newdeom.model;

/**
 * Created by yuzishun on 2019/6/3.
 */

public class UpdataBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : {"version":"1.0.0","msg":"本次更新是一次测试","location":"http://192.168.1.9/download/yhcd.apk","code":1,"create_time":"2019-06-02 16:52:11"}
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
         * version : 1.0.0
         * msg : 本次更新是一次测试
         * location : http://192.168.1.9/download/yhcd.apk
         * code : 1
         * create_time : 2019-06-02 16:52:11
         */

        private String version;
        private String msg;
        private String location;
        private int code;
        private String create_time;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
