package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/26.
 */

public class BasketBallBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"game_group_time":"2019-05-25","game_week":"周六","game_info":[{"game_id":"119182","game_name":"美职篮","game_home_team_name":"猛龙","game_guest_team_name":"雄鹿","game_stop_time":"2019-05-26 08:30","game_begin_time":"08:20","game_total_score":"+212.50","game_let_score":"-1.50","game_no":"20190525301","game_sequence_no":"301","home_let_odds":[{"game_odds_id":"11918200","odds":""},{"game_odds_id":"11918201","odds":""},{"game_odds_id":"11918202","odds":"1.64"},{"game_odds_id":"11918203","odds":"1.76"},{"game_odds_id":"11918204","odds":"1.70"},{"game_odds_id":"11918205","odds":"1.70"}],"score_guest_odds":[{"game_odds_id":"11918206","odds":"4.70"},{"game_odds_id":"11918207","odds":"4.75"},{"game_odds_id":"11918208","odds":"8.50"},{"game_odds_id":"11918209","odds":"14.00"},{"game_odds_id":"11918210","odds":"22.00"},{"game_odds_id":"11918211","odds":"28.00"}],"score_home_odds":[{"game_odds_id":"11918212","odds":"4.40"},{"game_odds_id":"11918213","odds":"4.50"},{"game_odds_id":"11918214","odds":"7.55"},{"game_odds_id":"11918215","odds":"12.00"},{"game_odds_id":"11918216","odds":"18.00"},{"game_odds_id":"11918217","odds":"22.00"}]}]}]
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
         * game_group_time : 2019-05-25
         * game_week : 周六
         * game_info : [{"game_id":"119182","game_name":"美职篮","game_home_team_name":"猛龙","game_guest_team_name":"雄鹿","game_stop_time":"2019-05-26 08:30","game_begin_time":"08:20","game_total_score":"+212.50","game_let_score":"-1.50","game_no":"20190525301","game_sequence_no":"301","home_let_odds":[{"game_odds_id":"11918200","odds":""},{"game_odds_id":"11918201","odds":""},{"game_odds_id":"11918202","odds":"1.64"},{"game_odds_id":"11918203","odds":"1.76"},{"game_odds_id":"11918204","odds":"1.70"},{"game_odds_id":"11918205","odds":"1.70"}],"score_guest_odds":[{"game_odds_id":"11918206","odds":"4.70"},{"game_odds_id":"11918207","odds":"4.75"},{"game_odds_id":"11918208","odds":"8.50"},{"game_odds_id":"11918209","odds":"14.00"},{"game_odds_id":"11918210","odds":"22.00"},{"game_odds_id":"11918211","odds":"28.00"}],"score_home_odds":[{"game_odds_id":"11918212","odds":"4.40"},{"game_odds_id":"11918213","odds":"4.50"},{"game_odds_id":"11918214","odds":"7.55"},{"game_odds_id":"11918215","odds":"12.00"},{"game_odds_id":"11918216","odds":"18.00"},{"game_odds_id":"11918217","odds":"22.00"}]}]
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
             * game_id : 119182
             * game_name : 美职篮
             * game_home_team_name : 猛龙
             * game_guest_team_name : 雄鹿
             * game_stop_time : 2019-05-26 08:30
             * game_begin_time : 08:20
             * game_total_score : +212.50
             * game_let_score : -1.50
             * game_no : 20190525301
             * game_sequence_no : 301
             * home_let_odds : [{"game_odds_id":"11918200","odds":""},{"game_odds_id":"11918201","odds":""},{"game_odds_id":"11918202","odds":"1.64"},{"game_odds_id":"11918203","odds":"1.76"},{"game_odds_id":"11918204","odds":"1.70"},{"game_odds_id":"11918205","odds":"1.70"}]
             * score_guest_odds : [{"game_odds_id":"11918206","odds":"4.70"},{"game_odds_id":"11918207","odds":"4.75"},{"game_odds_id":"11918208","odds":"8.50"},{"game_odds_id":"11918209","odds":"14.00"},{"game_odds_id":"11918210","odds":"22.00"},{"game_odds_id":"11918211","odds":"28.00"}]
             * score_home_odds : [{"game_odds_id":"11918212","odds":"4.40"},{"game_odds_id":"11918213","odds":"4.50"},{"game_odds_id":"11918214","odds":"7.55"},{"game_odds_id":"11918215","odds":"12.00"},{"game_odds_id":"11918216","odds":"18.00"},{"game_odds_id":"11918217","odds":"22.00"}]
             */

            private String game_id;
            private String game_name;
            private String game_home_team_name;
            private String game_guest_team_name;
            private String game_stop_time;
            private String game_begin_time;
            private String game_total_score;
            private String game_let_score;
            private String game_no;
            private String game_sequence_no;
            private List<HomeLetOddsBean> home_let_odds;
            private List<ScoreGuestOddsBean> score_guest_odds;
            private List<ScoreHomeOddsBean> score_home_odds;

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

            public String getGame_begin_time() {
                return game_begin_time;
            }

            public void setGame_begin_time(String game_begin_time) {
                this.game_begin_time = game_begin_time;
            }

            public String getGame_total_score() {
                return game_total_score;
            }

            public void setGame_total_score(String game_total_score) {
                this.game_total_score = game_total_score;
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

            public List<HomeLetOddsBean> getHome_let_odds() {
                return home_let_odds;
            }

            public void setHome_let_odds(List<HomeLetOddsBean> home_let_odds) {
                this.home_let_odds = home_let_odds;
            }

            public List<ScoreGuestOddsBean> getScore_guest_odds() {
                return score_guest_odds;
            }

            public void setScore_guest_odds(List<ScoreGuestOddsBean> score_guest_odds) {
                this.score_guest_odds = score_guest_odds;
            }

            public List<ScoreHomeOddsBean> getScore_home_odds() {
                return score_home_odds;
            }

            public void setScore_home_odds(List<ScoreHomeOddsBean> score_home_odds) {
                this.score_home_odds = score_home_odds;
            }

            public static class HomeLetOddsBean {
                /**
                 * game_odds_id : 11918200
                 * odds :
                 */

                private String game_odds_id;
                private String odds;

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

            public static class ScoreGuestOddsBean {
                /**
                 * game_odds_id : 11918206
                 * odds : 4.70
                 */

                private String game_odds_id;
                private String odds;

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

            public static class ScoreHomeOddsBean {
                /**
                 * game_odds_id : 11918212
                 * odds : 4.40
                 */

                private String game_odds_id;
                private String odds;

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
