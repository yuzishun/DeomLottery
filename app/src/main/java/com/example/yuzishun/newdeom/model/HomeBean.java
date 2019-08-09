package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by apple on 2019/7/29.
 */

public class HomeBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : {"banner":[{"img_location":"192.168.1.26banner/20190614/4567eac0d2d4ed6545fbde987d9bf2fd.jpg","url_location":"192.168.1.26 "}],"announcement":[{"uname":"华*2","bonus_price":"9850.80"}],"yesterday":[{"uname":"飘渺","bonus_price":"11420.00","img_head":"192.168.1.26"}]}
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
        private List<BannerBean> banner;
        private List<AnnouncementBean> announcement;
        private List<YesterdayBean> yesterday;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<AnnouncementBean> getAnnouncement() {
            return announcement;
        }

        public void setAnnouncement(List<AnnouncementBean> announcement) {
            this.announcement = announcement;
        }

        public List<YesterdayBean> getYesterday() {
            return yesterday;
        }

        public void setYesterday(List<YesterdayBean> yesterday) {
            this.yesterday = yesterday;
        }

        public static class BannerBean {
            /**
             * img_location : 192.168.1.26banner/20190614/4567eac0d2d4ed6545fbde987d9bf2fd.jpg
             * url_location : 192.168.1.26
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

        public static class AnnouncementBean {
            /**
             * uname : 华*2
             * bonus_price : 9850.80
             */

            private String uname;
            private String bonus_price;

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getBonus_price() {
                return bonus_price;
            }

            public void setBonus_price(String bonus_price) {
                this.bonus_price = bonus_price;
            }
        }

        public static class YesterdayBean {
            /**
             * uname : 飘渺
             * bonus_price : 11420.00
             * img_head : 192.168.1.26
             */

            private String uname;
            private String bonus_price;
            private String img_head;
            private String order_id;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getBonus_price() {
                return bonus_price;
            }

            public void setBonus_price(String bonus_price) {
                this.bonus_price = bonus_price;
            }

            public String getImg_head() {
                return img_head;
            }

            public void setImg_head(String img_head) {
                this.img_head = img_head;
            }
        }
    }
}
