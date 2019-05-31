package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/29.
 */

public class BasketballOrderBean {


    /**
     * code : 10000
     * msg : 查询成功!
     * data : {"order_id":31,"order_no":"201905261603421100021973520","user_id":2,"pour":3,"multiple":1,"order_price":"6.00","bonus_price":"0.00","order_type":0,"order_status":3,"pay_status":1,"game_type":1,"create_time":"2019-05-26 16:03:42","update_time":"2019-05-26 16:45:19","bunch":["1"],"multiple_price":24,"order_odds_info":[{"game_id":"119182","game_status":3,"win_odds_list":["主胜","让分主胜","小分","主胜6-10"],"game_home_team_name":"猛龙","game_let_score":"-1.50","game_total_score":"+212.50","game_guest_team_name":"雄鹿","game_begin_time":"2019-05-26 08:30","guest_score":94,"home_score":100,"total_score":194,"bet_info":[{"bet_name":"让分主负","bet_odds":"1.76"},{"bet_name":"主胜6-10","bet_odds":"4.50"},{"bet_name":"主胜16-20","bet_odds":"12.00"}]}],"order_plan":{"plan_follow_price":"0.00","plan_follow_person":"0","cut_off_time":"2019-05-27 21:15:00","plan_profits":"0.02","plan_status":0},"order_follow_plan":{"plan_status":2,"plan_profits":"0.02","uname":"ashun"}}
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
         * order_id : 31
         * order_no : 201905261603421100021973520
         * user_id : 2
         * pour : 3
         * multiple : 1
         * order_price : 6.00
         * bonus_price : 0.00
         * order_type : 0
         * order_status : 3
         * pay_status : 1
         * game_type : 1
         * create_time : 2019-05-26 16:03:42
         * update_time : 2019-05-26 16:45:19
         * bunch : ["1"]
         * multiple_price : 24
         * order_odds_info : [{"game_id":"119182","game_status":3,"win_odds_list":["主胜","让分主胜","小分","主胜6-10"],"game_home_team_name":"猛龙","game_let_score":"-1.50","game_total_score":"+212.50","game_guest_team_name":"雄鹿","game_begin_time":"2019-05-26 08:30","guest_score":94,"home_score":100,"total_score":194,"bet_info":[{"bet_name":"让分主负","bet_odds":"1.76"},{"bet_name":"主胜6-10","bet_odds":"4.50"},{"bet_name":"主胜16-20","bet_odds":"12.00"}]}]
         * order_plan : {"plan_follow_price":"0.00","plan_follow_person":"0","cut_off_time":"2019-05-27 21:15:00","plan_profits":"0.02","plan_status":0}
         * order_follow_plan : {"plan_status":2,"plan_profits":"0.02","uname":"ashun"}
         */

        private int order_id;
        private String order_no;
        private int user_id;
        private int pour;
        private int multiple;
        private String order_price;
        private String bonus_price;
        private int order_type;
        private int order_status;
        private int pay_status;
        private int game_type;
        private String create_time;
        private String update_time;
        private int multiple_price;
        private OrderPlanBean order_plan;
        private OrderFollowPlanBean order_follow_plan;
        private List<String> bunch;
        private List<OrderOddsInfoBean> order_odds_info;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getPour() {
            return pour;
        }

        public void setPour(int pour) {
            this.pour = pour;
        }

        public int getMultiple() {
            return multiple;
        }

        public void setMultiple(int multiple) {
            this.multiple = multiple;
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

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public int getGame_type() {
            return game_type;
        }

        public void setGame_type(int game_type) {
            this.game_type = game_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public int getMultiple_price() {
            return multiple_price;
        }

        public void setMultiple_price(int multiple_price) {
            this.multiple_price = multiple_price;
        }

        public OrderPlanBean getOrder_plan() {
            return order_plan;
        }

        public void setOrder_plan(OrderPlanBean order_plan) {
            this.order_plan = order_plan;
        }

        public OrderFollowPlanBean getOrder_follow_plan() {
            return order_follow_plan;
        }

        public void setOrder_follow_plan(OrderFollowPlanBean order_follow_plan) {
            this.order_follow_plan = order_follow_plan;
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

        public static class OrderPlanBean {
            /**
             * plan_follow_price : 0.00
             * plan_follow_person : 0
             * cut_off_time : 2019-05-27 21:15:00
             * plan_profits : 0.02
             * plan_status : 0
             */

            private String plan_follow_price;
            private String plan_follow_person;
            private String cut_off_time;
            private String plan_profits;
            private int plan_status;

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

            public String getCut_off_time() {
                return cut_off_time;
            }

            public void setCut_off_time(String cut_off_time) {
                this.cut_off_time = cut_off_time;
            }

            public String getPlan_profits() {
                return plan_profits;
            }

            public void setPlan_profits(String plan_profits) {
                this.plan_profits = plan_profits;
            }

            public int getPlan_status() {
                return plan_status;
            }

            public void setPlan_status(int plan_status) {
                this.plan_status = plan_status;
            }
        }

        public static class OrderFollowPlanBean {
            /**
             * plan_status : 2
             * plan_profits : 0.02
             * uname : ashun
             */

            private int plan_status;
            private String plan_profits;
            private String uname;

            public int getPlan_status() {
                return plan_status;
            }

            public void setPlan_status(int plan_status) {
                this.plan_status = plan_status;
            }

            public String getPlan_profits() {
                return plan_profits;
            }

            public void setPlan_profits(String plan_profits) {
                this.plan_profits = plan_profits;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }
        }

        public static class OrderOddsInfoBean {
            /**
             * game_id : 119182
             * game_status : 3
             * win_odds_list : ["主胜","让分主胜","小分","主胜6-10"]
             * game_home_team_name : 猛龙
             * game_let_score : -1.50
             * game_total_score : +212.50
             * game_guest_team_name : 雄鹿
             * game_begin_time : 2019-05-26 08:30
             * guest_score : 94
             * home_score : 100
             * total_score : 194
             * bet_info : [{"bet_name":"让分主负","bet_odds":"1.76"},{"bet_name":"主胜6-10","bet_odds":"4.50"},{"bet_name":"主胜16-20","bet_odds":"12.00"}]
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
            private List<String> win_odds_list;
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

            public List<String> getWin_odds_list() {
                return win_odds_list;
            }

            public void setWin_odds_list(List<String> win_odds_list) {
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
                 * bet_name : 让分主负
                 * bet_odds : 1.76
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
    }
}