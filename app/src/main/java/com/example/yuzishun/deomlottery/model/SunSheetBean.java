package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by apple on 2019/8/5.
 */

public class SunSheetBean {


    /**
     * code : 10000
     * msg : 查询成功！
     * data : [{"user_id":43,"uname":"中国牛批","img_head":"http://192.168.1.26/upload/20190610/ef45bc7748cdb56005193cae8969a4d3.png","create_time":"2019-08-05 10:41:20","manifesto":"你好","order_price":"6.00","multiple":1,"win_money":"0.00","game_type":0,"bask_id":14,"red_black":0,"like_count":0,"comment_count":0},{"user_id":43,"uname":"中国牛批","img_head":"http://192.168.1.26/upload/20190610/ef45bc7748cdb56005193cae8969a4d3.png","create_time":"2019-08-02 14:19:58","manifesto":"你好","order_price":"20.00","multiple":10,"win_money":"0.00","game_type":0,"bask_id":13,"red_black":0,"like_count":0,"comment_count":0},{"user_id":43,"uname":"中国牛批","img_head":"http://192.168.1.26/upload/20190610/ef45bc7748cdb56005193cae8969a4d3.png","create_time":"2019-08-02 14:07:33","manifesto":"你好","order_price":"5000.00","multiple":2500,"win_money":"0.00","game_type":0,"bask_id":12,"red_black":0,"like_count":0,"comment_count":2}]
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
         * user_id : 43
         * uname : 中国牛批
         * img_head : http://192.168.1.26/upload/20190610/ef45bc7748cdb56005193cae8969a4d3.png
         * create_time : 2019-08-05 10:41:20
         * manifesto : 你好
         * order_price : 6.00
         * multiple : 1
         * win_money : 0.00
         * game_type : 0
         * bask_id : 14
         * red_black : 0
         * like_count : 0
         * comment_count : 0
         */
        private int is_like;
        private int user_id;
        private String uname;
        private String img_head;
        private String create_time;
        private String manifesto;
        private String order_price;
        private int multiple;
        private String win_money;
        private int game_type;
        private int bask_id;
        private int red_black;
        private int like_count;
        private int comment_count;

        public int getIs_like() {
            return is_like;
        }

        public void setIs_like(int is_like) {
            this.is_like = is_like;
        }

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

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getManifesto() {
            return manifesto;
        }

        public void setManifesto(String manifesto) {
            this.manifesto = manifesto;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public int getMultiple() {
            return multiple;
        }

        public void setMultiple(int multiple) {
            this.multiple = multiple;
        }

        public String getWin_money() {
            return win_money;
        }

        public void setWin_money(String win_money) {
            this.win_money = win_money;
        }

        public int getGame_type() {
            return game_type;
        }

        public void setGame_type(int game_type) {
            this.game_type = game_type;
        }

        public int getBask_id() {
            return bask_id;
        }

        public void setBask_id(int bask_id) {
            this.bask_id = bask_id;
        }

        public int getRed_black() {
            return red_black;
        }

        public void setRed_black(int red_black) {
            this.red_black = red_black;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }
    }
}
