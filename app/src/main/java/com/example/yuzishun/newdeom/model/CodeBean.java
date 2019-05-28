package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/16.
 */

public class CodeBean {


    /**
     * code : 10005
     * msg : 手机号必填
     * data : []
     */

    private int code;
    private String msg;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
