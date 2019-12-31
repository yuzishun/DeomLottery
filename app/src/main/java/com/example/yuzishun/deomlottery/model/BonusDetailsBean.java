package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by apple on 2019/10/18.
 */

public class BonusDetailsBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : {"order_id":102871,"pour":1400,"seo_status":1,"seo_info":[{"pour":"114","game_name":["阿瓦伊|让平|3.30"],"bonus_price":2370.06,"odds_status":0}]}
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
         * order_id : 102871
         * pour : 1400
         * seo_status : 1
         * seo_info : [{"pour":"114","game_name":["阿瓦伊|让平|3.30"],"bonus_price":2370.06,"odds_status":0}]
         */

        private int order_id;
        private int pour;
        private int seo_status;
        private List<SeoInfoBean> seo_info;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getPour() {
            return pour;
        }

        public void setPour(int pour) {
            this.pour = pour;
        }

        public int getSeo_status() {
            return seo_status;
        }

        public void setSeo_status(int seo_status) {
            this.seo_status = seo_status;
        }

        public List<SeoInfoBean> getSeo_info() {
            return seo_info;
        }

        public void setSeo_info(List<SeoInfoBean> seo_info) {
            this.seo_info = seo_info;
        }

        public static class SeoInfoBean {
            /**
             * pour : 114
             * game_name : ["阿瓦伊|让平|3.30"]
             * bonus_price : 2370.06
             * odds_status : 0
             */

            private String pour;
            private double bonus_price;
            private int odds_status;
            private List<String> game_name;

            public String getPour() {
                return pour;
            }

            public void setPour(String pour) {
                this.pour = pour;
            }

            public double getBonus_price() {
                return bonus_price;
            }

            public void setBonus_price(double bonus_price) {
                this.bonus_price = bonus_price;
            }

            public int getOdds_status() {
                return odds_status;
            }

            public void setOdds_status(int odds_status) {
                this.odds_status = odds_status;
            }

            public List<String> getGame_name() {
                return game_name;
            }

            public void setGame_name(List<String> game_name) {
                this.game_name = game_name;
            }
        }
    }
}
