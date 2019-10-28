package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/29.
 */

public class FootBallOrderBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : {"order_id":3740,"order_no":"201906190940051100465862322","user_id":46,"pour":2,"multiple":26,"order_price":"104.00","bonus_price":"136.24","order_type":1,"order_status":2,"pay_status":1,"game_type":1,"create_time":"2019-06-19 09:40:05","update_time":"2019-06-21 15:00:01","bunch":["2"],"theory_bonus":"","cite_price":"6.81","game_sum":2,"plan_permission":1,"follow_plan_permission":0,"order_odds_info":[{"game_id":"119550","game_status":3,"win_odds_list":["主胜","让分主胜","大分","主胜6-10"],"game_home_team_name":"梦想","game_let_score":"-2.50","game_guest_team_name":"狂热","game_begin_time":"2019-06-19 23:00","bet_info":[{"bet_name":"主胜(1.48)","status":1},{"bet_name":"小分(1.70)","status":0}]},{"game_id":"119551","game_status":3,"win_odds_list":["主负","让分主负","大分","客胜6-10"],"game_home_team_name":"自由","game_let_score":"-1.50","game_guest_team_name":"天空","game_begin_time":"2019-06-20 07:00","bet_info":[{"bet_name":"主负(1.77)","status":1}]}],"user_info":{"uname":"阿斯顿","img_head":"http://192.168.1.26/upload/20190611/ebfb18c821a5f81b8068be69585a5881.png"},"multiple_price":4,"order_plan":{"plan_profit_price":"2.75","plan_follow_price":"20.00","plan_follow_person":"2","cut_off_time":"2019-06-19 22:45:00","plan_profits":"0.10","plan_status":0},"order_follow_plan":{"plan_status":"","plan_profits":"","uname":""},"order_plan_info":[{"uname":"追*","order_price":"16.00","bonus_price":"20.96","create_time":"2019-06-19 10:27:50"},{"uname":"1*2","order_price":"4.00","bonus_price":"5.24","create_time":"2019-06-19 10:19:15"}]}
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
         * order_id : 3740
         * order_no : 201906190940051100465862322
         * user_id : 46
         * pour : 2
         * multiple : 26
         * order_price : 104.00
         * bonus_price : 136.24
         * order_type : 1
         * order_status : 2
         * pay_status : 1
         * game_type : 1
         * create_time : 2019-06-19 09:40:05
         * update_time : 2019-06-21 15:00:01
         * bunch : ["2"]
         * theory_bonus :
         * cite_price : 6.81
         * game_sum : 2
         * plan_permission : 1
         * follow_plan_permission : 0
         * order_odds_info : [{"game_id":"119550","game_status":3,"win_odds_list":["主胜","让分主胜","大分","主胜6-10"],"game_home_team_name":"梦想","game_let_score":"-2.50","game_guest_team_name":"狂热","game_begin_time":"2019-06-19 23:00","bet_info":[{"bet_name":"主胜(1.48)","status":1},{"bet_name":"小分(1.70)","status":0}]},{"game_id":"119551","game_status":3,"win_odds_list":["主负","让分主负","大分","客胜6-10"],"game_home_team_name":"自由","game_let_score":"-1.50","game_guest_team_name":"天空","game_begin_time":"2019-06-20 07:00","bet_info":[{"bet_name":"主负(1.77)","status":1}]}]
         * user_info : {"uname":"阿斯顿","img_head":"http://192.168.1.26/upload/20190611/ebfb18c821a5f81b8068be69585a5881.png"}
         * multiple_price : 4
         * order_plan : {"plan_profit_price":"2.75","plan_follow_price":"20.00","plan_follow_person":"2","cut_off_time":"2019-06-19 22:45:00","plan_profits":"0.10","plan_status":0}
         * order_follow_plan : {"plan_status":"","plan_profits":"","uname":""}
         * order_plan_info : [{"uname":"追*","order_price":"16.00","bonus_price":"20.96","create_time":"2019-06-19 10:27:50"},{"uname":"1*2","order_price":"4.00","bonus_price":"5.24","create_time":"2019-06-19 10:19:15"}]
         */
        private int bask_permission;
        private int bask_status;
        private int order_id;
        private String order_no;
        private int user_id;
        private int pour;
        private int seo_status;
        private int multiple;
        private String order_price;
        private String bonus_price;
        private int order_type;
        private int order_status;
        private int pay_status;
        private int game_type;
        private String bask_id;
        private String create_time;
        private String update_time;
        private String theory_bonus;
        private String cite_price;
        private int game_sum;
        private int plan_permission;
        private int follow_plan_permission;
        private UserInfoBean user_info;
        private int multiple_price;
        private OrderPlanBean order_plan;
        private OrderFollowPlanBean order_follow_plan;
        private List<String> bunch;
        private List<OrderOddsInfoBean> order_odds_info;
        private List<OrderPlanInfoBean> order_plan_info;

        public int getSeo_status() {
            return seo_status;
        }

        public void setSeo_status(int seo_status) {
            this.seo_status = seo_status;
        }

        public String getBask_id() {
            return bask_id;
        }

        public void setBask_id(String bask_id) {
            this.bask_id = bask_id;
        }

        public int getBask_permission() {
            return bask_permission;
        }

        public void setBask_permission(int bask_permission) {
            this.bask_permission = bask_permission;
        }

        public int getBask_status() {
            return bask_status;
        }

        public void setBask_status(int bask_status) {
            this.bask_status = bask_status;
        }

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

        public String getTheory_bonus() {
            return theory_bonus;
        }

        public void setTheory_bonus(String theory_bonus) {
            this.theory_bonus = theory_bonus;
        }

        public String getCite_price() {
            return cite_price;
        }

        public void setCite_price(String cite_price) {
            this.cite_price = cite_price;
        }

        public int getGame_sum() {
            return game_sum;
        }

        public void setGame_sum(int game_sum) {
            this.game_sum = game_sum;
        }

        public int getPlan_permission() {
            return plan_permission;
        }

        public void setPlan_permission(int plan_permission) {
            this.plan_permission = plan_permission;
        }

        public int getFollow_plan_permission() {
            return follow_plan_permission;
        }

        public void setFollow_plan_permission(int follow_plan_permission) {
            this.follow_plan_permission = follow_plan_permission;
        }

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
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

        public List<OrderPlanInfoBean> getOrder_plan_info() {
            return order_plan_info;
        }

        public void setOrder_plan_info(List<OrderPlanInfoBean> order_plan_info) {
            this.order_plan_info = order_plan_info;
        }

        public static class UserInfoBean {
            /**
             * uname : 阿斯顿
             * img_head : http://192.168.1.26/upload/20190611/ebfb18c821a5f81b8068be69585a5881.png
             */

            private String uname;
            private String img_head;

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
        }

        public static class OrderPlanBean {
            /**
             * plan_profit_price : 2.75
             * plan_follow_price : 20.00
             * plan_follow_person : 2
             * cut_off_time : 2019-06-19 22:45:00
             * plan_profits : 0.10
             * plan_status : 0
             */

            private String plan_profit_price;
            private String plan_follow_price;
            private String plan_follow_person;
            private String cut_off_time;
            private String plan_profits;
            private String plan_status;
            private String plan_desc;

            public String getPlan_desc() {
                return plan_desc;
            }

            public void setPlan_desc(String plan_desc) {
                this.plan_desc = plan_desc;
            }

            public String getPlan_profit_price() {
                return plan_profit_price;
            }

            public void setPlan_profit_price(String plan_profit_price) {
                this.plan_profit_price = plan_profit_price;
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

            public String getPlan_status() {
                return plan_status;
            }

            public void setPlan_status(String plan_status) {
                this.plan_status = plan_status;
            }
        }

        public static class OrderFollowPlanBean {
            /**
             * plan_status :
             * plan_profits :
             * uname :
             */

            private String plan_status;
            private String plan_profits;
            private String uname;

            public String getPlan_status() {
                return plan_status;
            }

            public void setPlan_status(String plan_status) {
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
             * game_id : 119550
             * game_status : 3
             * win_odds_list : ["主胜","让分主胜","大分","主胜6-10"]
             * game_home_team_name : 梦想
             * game_let_score : -2.50
             * game_guest_team_name : 狂热
             * game_begin_time : 2019-06-19 23:00
             * bet_info : [{"bet_name":"主胜(1.48)","status":1},{"bet_name":"小分(1.70)","status":0}]
             */

            private String game_id;
            private int game_status;
            private String game_home_team_name;
            private String game_let_score;
            private String game_guest_team_name;
            private String game_begin_time;
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
                 * bet_name : 主胜(1.48)
                 * status : 1
                 */

                private String bet_name;
                private int status;

                public String getBet_name() {
                    return bet_name;
                }

                public void setBet_name(String bet_name) {
                    this.bet_name = bet_name;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }

        public static class OrderPlanInfoBean {
            /**
             * uname : 追*
             * order_price : 16.00
             * bonus_price : 20.96
             * create_time : 2019-06-19 10:27:50
             */

            private String uname;
            private String order_price;
            private String bonus_price;
            private String create_time;

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
