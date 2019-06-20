package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/30.
 */

public class MainOrderBasketBean {


    /**
     * code : 10000
     * msg : 查询成功!
     * data : {"plan_id":16,"order_id":113,"uname":"ashun","img_head":"upload/20190521/b7f8497599868d36a62433b2daaedd2d.jpg","order_status":1,"order_price":"800.00","bonus_price":"0.00","multiple":100,"multiple_price":"8.00","game_type":1,"bunch":["1"],"plan_profits":"0.10","plan_desc":"科恩","plan_follow_price":"2928.00","plan_follow_person":"1","plan_status":0,"cut_off_time":"2019-05-31 08:45:00","see":0,"order_odds_info":[{"game_id":"119264","game_status":0,"win_odds_list":[],"game_home_team_name":"猛龙","game_let_score":"-0.50","game_total_score":"+214.50","game_guest_team_name":"勇士","game_begin_time":"2019-05-31 09:00","guest_score":0,"home_score":0,"total_score":0,"bet_info":[{"bet_name":"让分主胜","bet_odds":"1.70"},{"bet_name":"让分主负","bet_odds":"1.70"},{"bet_name":"大分","bet_odds":"1.70"},{"bet_name":"小分","bet_odds":"1.70"}]}]}
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
         * plan_id : 16
         * order_id : 113
         * uname : ashun
         * img_head : upload/20190521/b7f8497599868d36a62433b2daaedd2d.jpg
         * order_status : 1
         * order_price : 800.00
         * bonus_price : 0.00
         * multiple : 100
         * multiple_price : 8.00
         * game_type : 1
         * bunch : ["1"]
         * plan_profits : 0.10
         * plan_desc : 科恩
         * plan_follow_price : 2928.00
         * plan_follow_person : 1
         * plan_status : 0
         * cut_off_time : 2019-05-31 08:45:00
         * see : 0
         * order_odds_info : [{"game_id":"119264","game_status":0,"win_odds_list":[],"game_home_team_name":"猛龙","game_let_score":"-0.50","game_total_score":"+214.50","game_guest_team_name":"勇士","game_begin_time":"2019-05-31 09:00","guest_score":0,"home_score":0,"total_score":0,"bet_info":[{"bet_name":"让分主胜","bet_odds":"1.70"},{"bet_name":"让分主负","bet_odds":"1.70"},{"bet_name":"大分","bet_odds":"1.70"},{"bet_name":"小分","bet_odds":"1.70"}]}]
         */

        private int plan_id;
        private int order_id;
        private String uname;
        private String img_head;
        private int game_sum;
        private int order_status;
        private String order_price;
        private String bonus_price;
        private int multiple;
        private String multiple_price;
        private int game_type;
        private String plan_profits;
        private String plan_desc;
        private String plan_follow_price;
        private String plan_follow_person;
        private int plan_status;
        private String cut_off_time;
        private int see;
        private List<String> bunch;
        private List<OrderOddsInfoBean> order_odds_info;
        private List<OrderPlanInfoBean> order_plan_info;

        public List<OrderPlanInfoBean> getOrder_plan_info() {
            return order_plan_info;
        }

        public void setOrder_plan_info(List<OrderPlanInfoBean> order_plan_info) {
            this.order_plan_info = order_plan_info;
        }

        public int getGame_sum() {
            return game_sum;
        }

        public void setGame_sum(int game_sum) {
            this.game_sum = game_sum;
        }

        public int getPlan_id() {
            return plan_id;
        }

        public void setPlan_id(int plan_id) {
            this.plan_id = plan_id;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
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

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getBonus_price() {
            return bonus_price;
        }

        public void setBonus_price(String bonus_price) {
            this.bonus_price = bonus_price;
        }

        public int getMultiple() {
            return multiple;
        }

        public void setMultiple(int multiple) {
            this.multiple = multiple;
        }

        public String getMultiple_price() {
            return multiple_price;
        }

        public void setMultiple_price(String multiple_price) {
            this.multiple_price = multiple_price;
        }

        public int getGame_type() {
            return game_type;
        }

        public void setGame_type(int game_type) {
            this.game_type = game_type;
        }

        public String getPlan_profits() {
            return plan_profits;
        }

        public void setPlan_profits(String plan_profits) {
            this.plan_profits = plan_profits;
        }

        public String getPlan_desc() {
            return plan_desc;
        }

        public void setPlan_desc(String plan_desc) {
            this.plan_desc = plan_desc;
        }

        public String getPlan_follow_price() {
            return plan_follow_price;
        }

        public void setPlan_follow_price(String plan_follow_price) {
            this.plan_follow_price = plan_follow_price;
        }

        public String getPlan_follow_person() {
            return plan_follow_person;
        }

        public void setPlan_follow_person(String plan_follow_person) {
            this.plan_follow_person = plan_follow_person;
        }

        public int getPlan_status() {
            return plan_status;
        }

        public void setPlan_status(int plan_status) {
            this.plan_status = plan_status;
        }

        public String getCut_off_time() {
            return cut_off_time;
        }

        public void setCut_off_time(String cut_off_time) {
            this.cut_off_time = cut_off_time;
        }

        public int getSee() {
            return see;
        }

        public void setSee(int see) {
            this.see = see;
        }

        public List<String> getBunch() {
            return bunch;
        }

        public void setBunch(List<String> bunch) {
            this.bunch = bunch;
        }

        public List<OrderOddsInfoBean> getOrder_odds_info() {
            return order_odds_info;
        }

        public void setOrder_odds_info(List<OrderOddsInfoBean> order_odds_info) {
            this.order_odds_info = order_odds_info;
        }

        public static class OrderOddsInfoBean {
            /**
             * game_id : 119264
             * game_status : 0
             * win_odds_list : []
             * game_home_team_name : 猛龙
             * game_let_score : -0.50
             * game_total_score : +214.50
             * game_guest_team_name : 勇士
             * game_begin_time : 2019-05-31 09:00
             * guest_score : 0
             * home_score : 0
             * total_score : 0
             * bet_info : [{"bet_name":"让分主胜","bet_odds":"1.70"},{"bet_name":"让分主负","bet_odds":"1.70"},{"bet_name":"大分","bet_odds":"1.70"},{"bet_name":"小分","bet_odds":"1.70"}]
             */

            private String game_id;
            private int game_status;
            private String game_home_team_name;
            private String game_let_score;
            private String game_total_score;
            private String game_guest_team_name;
            private String game_begin_time;
            private int guest_score;
            private int home_score;
            private int total_score;
            private List<?> win_odds_list;
            private List<BetInfoBean> bet_info;

            public String getGame_id() {
                return game_id;
            }

            public void setGame_id(String game_id) {
                this.game_id = game_id;
            }

            public int getGame_status() {
                return game_status;
            }

            public void setGame_status(int game_status) {
                this.game_status = game_status;
            }

            public String getGame_home_team_name() {
                return game_home_team_name;
            }

            public void setGame_home_team_name(String game_home_team_name) {
                this.game_home_team_name = game_home_team_name;
            }

            public String getGame_let_score() {
                return game_let_score;
            }

            public void setGame_let_score(String game_let_score) {
                this.game_let_score = game_let_score;
            }

            public String getGame_total_score() {
                return game_total_score;
            }

            public void setGame_total_score(String game_total_score) {
                this.game_total_score = game_total_score;
            }

            public String getGame_guest_team_name() {
                return game_guest_team_name;
            }

            public void setGame_guest_team_name(String game_guest_team_name) {
                this.game_guest_team_name = game_guest_team_name;
            }

            public String getGame_begin_time() {
                return game_begin_time;
            }

            public void setGame_begin_time(String game_begin_time) {
                this.game_begin_time = game_begin_time;
            }

            public int getGuest_score() {
                return guest_score;
            }

            public void setGuest_score(int guest_score) {
                this.guest_score = guest_score;
            }

            public int getHome_score() {
                return home_score;
            }

            public void setHome_score(int home_score) {
                this.home_score = home_score;
            }

            public int getTotal_score() {
                return total_score;
            }

            public void setTotal_score(int total_score) {
                this.total_score = total_score;
            }

            public List<?> getWin_odds_list() {
                return win_odds_list;
            }

            public void setWin_odds_list(List<?> win_odds_list) {
                this.win_odds_list = win_odds_list;
            }

            public List<BetInfoBean> getBet_info() {
                return bet_info;
            }

            public void setBet_info(List<BetInfoBean> bet_info) {
                this.bet_info = bet_info;
            }

            public static class BetInfoBean {
                /**
                 * bet_name : 让分主胜
                 * bet_odds : 1.70
                 */

                private String bet_name;
                private String bet_odds;

                public String getBet_name() {
                    return bet_name;
                }

                public void setBet_name(String bet_name) {
                    this.bet_name = bet_name;
                }

                public String getBet_odds() {
                    return bet_odds;
                }

                public void setBet_odds(String bet_odds) {
                    this.bet_odds = bet_odds;
                }
            }
        }

        public static class OrderPlanInfoBean {
            /**
             * uname : 红领巾
             * follow_price : 2
             */

            private String uname;
            private String order_price;
            private String bonus_price;
            private String create_time;

            public String getOrder_price() {
                return order_price;
            }

            public void setOrder_price(String order_price) {
                this.order_price = order_price;
            }

            public String getBonus_price() {
                return bonus_price;
            }

            public void setBonus_price(String bonus_price) {
                this.bonus_price = bonus_price;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }


        }
    }
}
