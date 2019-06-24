package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/6/20.
 */

public class SingleBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"game_group_time":"2019-06-20","game_week":"周四","game_info":[{"game_id":"119546","game_name":"美洲杯","game_home_team_name":"乌拉圭","game_guest_team_name":"日本","game_stop_time":"06:50","game_home_score":"0","game_let_score":"-2","game_no":"20190620001","game_sequence_no":"周四001","single_odds":[{"game_odds_id":"11954600","odds":"1.12"},{"game_odds_id":"11954601","odds":"5.80"},{"game_odds_id":"11954602","odds":"11.00"}]}]}]
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
         * game_group_time : 2019-06-20
         * game_week : 周四
         * game_info : [{"game_id":"119546","game_name":"美洲杯","game_home_team_name":"乌拉圭","game_guest_team_name":"日本","game_stop_time":"06:50","game_home_score":"0","game_let_score":"-2","game_no":"20190620001","game_sequence_no":"周四001","single_odds":[{"game_odds_id":"11954600","odds":"1.12"},{"game_odds_id":"11954601","odds":"5.80"},{"game_odds_id":"11954602","odds":"11.00"}]}]
         */

        private String game_group_time;
        private String game_week;
        private List<GameInfoBean> game_info;

        public String getGame_group_time() {
            return game_group_time;
        }

        public void setGame_group_time(String game_group_time) {
            this.game_group_time = game_group_time;
        }

        public String getGame_week() {
            return game_week;
        }

        public void setGame_week(String game_week) {
            this.game_week = game_week;
        }

        public List<GameInfoBean> getGame_info() {
            return game_info;
        }

        public void setGame_info(List<GameInfoBean> game_info) {
            this.game_info = game_info;
        }

        public static class GameInfoBean {
            /**
             * game_id : 119546
             * game_name : 美洲杯
             * game_home_team_name : 乌拉圭
             * game_guest_team_name : 日本
             * game_stop_time : 06:50
             * game_home_score : 0
             * game_let_score : -2
             * game_no : 20190620001
             * game_sequence_no : 周四001
             * single_odds : [{"game_odds_id":"11954600","odds":"1.12"},{"game_odds_id":"11954601","odds":"5.80"},{"game_odds_id":"11954602","odds":"11.00"}]
             */

            private String game_id;
            private String game_name;
            private String game_home_team_name;
            private String game_guest_team_name;
            private String game_stop_time;
            private String game_home_score;
            private String game_let_score;
            private String game_no;
            private String game_sequence_no;
            private List<SingleOddsBean> single_odds;

            public String getGame_id() {
                return game_id;
            }

            public void setGame_id(String game_id) {
                this.game_id = game_id;
            }

            public String getGame_name() {
                return game_name;
            }

            public void setGame_name(String game_name) {
                this.game_name = game_name;
            }

            public String getGame_home_team_name() {
                return game_home_team_name;
            }

            public void setGame_home_team_name(String game_home_team_name) {
                this.game_home_team_name = game_home_team_name;
            }

            public String getGame_guest_team_name() {
                return game_guest_team_name;
            }

            public void setGame_guest_team_name(String game_guest_team_name) {
                this.game_guest_team_name = game_guest_team_name;
            }

            public String getGame_stop_time() {
                return game_stop_time;
            }

            public void setGame_stop_time(String game_stop_time) {
                this.game_stop_time = game_stop_time;
            }

            public String getGame_home_score() {
                return game_home_score;
            }

            public void setGame_home_score(String game_home_score) {
                this.game_home_score = game_home_score;
            }

            public String getGame_let_score() {
                return game_let_score;
            }

            public void setGame_let_score(String game_let_score) {
                this.game_let_score = game_let_score;
            }

            public String getGame_no() {
                return game_no;
            }

            public void setGame_no(String game_no) {
                this.game_no = game_no;
            }

            public String getGame_sequence_no() {
                return game_sequence_no;
            }

            public void setGame_sequence_no(String game_sequence_no) {
                this.game_sequence_no = game_sequence_no;
            }

            public List<SingleOddsBean> getSingle_odds() {
                return single_odds;
            }

            public void setSingle_odds(List<SingleOddsBean> single_odds) {
                this.single_odds = single_odds;
            }

            public static class SingleOddsBean {
                /**
                 * game_odds_id : 11954600
                 * odds : 1.12
                 */

                private String game_odds_id;
                private String odds;
                private String odds_code;

                public String getOdds_code() {
                    return odds_code;
                }

                public void setOdds_code(String odds_code) {
                    this.odds_code = odds_code;
                }

                public String getGame_odds_id() {
                    return game_odds_id;
                }

                public void setGame_odds_id(String game_odds_id) {
                    this.game_odds_id = game_odds_id;
                }

                public String getOdds() {
                    return odds;
                }

                public void setOdds(String odds) {
                    this.odds = odds;
                }
            }
        }
    }
}
