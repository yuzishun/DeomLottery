package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by apple on 2019/8/8.
 */

public class HomeSingle {


    /**
     * code : 10000
     * msg : 查询成功
     * data : {"game_id":"120351","game_name":"英超","game_home_team_name":"利物浦","game_guest_team_name":"诺维奇","game_stop_time":"02:50","game_home_score":"0","game_let_score":"-2","game_no":"20190809019","game_sequence_no":"周五019","single_odds":[{"game_odds_id":"12035103","odds":"2.00","odds_code":"让球胜"},{"game_odds_id":"12035104","odds":"3.95","odds_code":"让球平"},{"game_odds_id":"12035105","odds":"2.47","odds_code":"让球负"}]}
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
         * game_id : 120351
         * game_name : 英超
         * game_home_team_name : 利物浦
         * game_guest_team_name : 诺维奇
         * game_stop_time : 02:50
         * game_home_score : 0
         * game_let_score : -2
         * game_no : 20190809019
         * game_sequence_no : 周五019
         * single_odds : [{"game_odds_id":"12035103","odds":"2.00","odds_code":"让球胜"},{"game_odds_id":"12035104","odds":"3.95","odds_code":"让球平"},{"game_odds_id":"12035105","odds":"2.47","odds_code":"让球负"}]
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
             * game_odds_id : 12035103
             * odds : 2.00
             * odds_code : 让球胜
             */

            private String game_odds_id;
            private String odds;
            private String odds_code;

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

            public String getOdds_code() {
                return odds_code;
            }

            public void setOdds_code(String odds_code) {
                this.odds_code = odds_code;
            }
        }
    }
}
