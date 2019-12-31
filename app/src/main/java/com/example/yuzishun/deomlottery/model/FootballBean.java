package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by yuzishun on 2019/5/10.
 */

public class FootballBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"game_group_time":"2019-05-22","game_week":"周三","game_info":[{"game_id":"119143","game_name":"亚冠","game_home_team_name":"墨胜利","game_guest_team_name":"广岛三箭","game_stop_time":"2019-05-22 18:00","game_begin_time":"17:50","game_home_score":"0","game_let_score":"+1","game_no":"20190522001","game_sequence_no":"001","home_let_odds":[{"game_odds_id":"11914300","odds":"2.38"},{"game_odds_id":"11914301","odds":"3.35"},{"game_odds_id":"11914302","odds":"2.27"},{"game_odds_id":"11914303","odds":"1.42"},{"game_odds_id":"11914304","odds":"4.05"},{"game_odds_id":"11914305","odds":"4.80"}],"score_odds":[{"game_odds_id":"11914306","odds":"7.75"},{"game_odds_id":"11914307","odds":"12.50"},{"game_odds_id":"11914308","odds":"7.50"},{"game_odds_id":"11914309","odds":"25.00"},{"game_odds_id":"11914310","odds":"20.00"},{"game_odds_id":"11914311","odds":"22.00"},{"game_odds_id":"11914312","odds":"70.00"},{"game_odds_id":"11914313","odds":"50.00"},{"game_odds_id":"11914314","odds":"65.00"},{"game_odds_id":"11914315","odds":"250.0"},{"game_odds_id":"11914316","odds":"200.0"},{"game_odds_id":"11914317","odds":"250.0"},{"game_odds_id":"11914318","odds":"75.00"},{"game_odds_id":"11914319","odds":"9.00"},{"game_odds_id":"11914320","odds":"6.50"},{"game_odds_id":"11914321","odds":"12.00"},{"game_odds_id":"11914322","odds":"60.00"},{"game_odds_id":"11914323","odds":"300.0"},{"game_odds_id":"11914324","odds":"7.75"},{"game_odds_id":"11914325","odds":"11.50"},{"game_odds_id":"11914326","odds":"7.50"},{"game_odds_id":"11914327","odds":"22.00"},{"game_odds_id":"11914328","odds":"19.00"},{"game_odds_id":"11914329","odds":"22.00"},{"game_odds_id":"11914330","odds":"70.00"},{"game_odds_id":"11914331","odds":"42.00"},{"game_odds_id":"11914332","odds":"65.00"},{"game_odds_id":"11914333","odds":"200.0"},{"game_odds_id":"11914334","odds":"150.0"},{"game_odds_id":"11914335","odds":"250.0"},{"game_odds_id":"11914336","odds":"70.00"}],"total_odds":[{"game_odds_id":"11914337","odds":"9.00"},{"game_odds_id":"11914338","odds":"4.10"},{"game_odds_id":"11914339","odds":"3.40"},{"game_odds_id":"11914340","odds":"3.45"},{"game_odds_id":"11914341","odds":"5.50"},{"game_odds_id":"11914342","odds":"9.50"},{"game_odds_id":"11914343","odds":"19.00"},{"game_odds_id":"11914344","odds":"30.00"}],"half_odds":[{"game_odds_id":"11914345","odds":"4.05"},{"game_odds_id":"11914346","odds":"11.00"},{"game_odds_id":"11914347","odds":"23.00"},{"game_odds_id":"11914348","odds":"5.80"},{"game_odds_id":"11914349","odds":"5.20"},{"game_odds_id":"11914350","odds":"5.70"},{"game_odds_id":"11914351","odds":"25.00"},{"game_odds_id":"11914352","odds":"11.00"},{"game_odds_id":"11914353","odds":"3.85"}]}]}]
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

    public static class DataBean  {
        /**
         * game_group_time : 2019-05-22
         * game_week : 周三
         * game_info : [{"game_id":"119143","game_name":"亚冠","game_home_team_name":"墨胜利","game_guest_team_name":"广岛三箭","game_stop_time":"2019-05-22 18:00","game_begin_time":"17:50","game_home_score":"0","game_let_score":"+1","game_no":"20190522001","game_sequence_no":"001","home_let_odds":[{"game_odds_id":"11914300","odds":"2.38"},{"game_odds_id":"11914301","odds":"3.35"},{"game_odds_id":"11914302","odds":"2.27"},{"game_odds_id":"11914303","odds":"1.42"},{"game_odds_id":"11914304","odds":"4.05"},{"game_odds_id":"11914305","odds":"4.80"}],"score_odds":[{"game_odds_id":"11914306","odds":"7.75"},{"game_odds_id":"11914307","odds":"12.50"},{"game_odds_id":"11914308","odds":"7.50"},{"game_odds_id":"11914309","odds":"25.00"},{"game_odds_id":"11914310","odds":"20.00"},{"game_odds_id":"11914311","odds":"22.00"},{"game_odds_id":"11914312","odds":"70.00"},{"game_odds_id":"11914313","odds":"50.00"},{"game_odds_id":"11914314","odds":"65.00"},{"game_odds_id":"11914315","odds":"250.0"},{"game_odds_id":"11914316","odds":"200.0"},{"game_odds_id":"11914317","odds":"250.0"},{"game_odds_id":"11914318","odds":"75.00"},{"game_odds_id":"11914319","odds":"9.00"},{"game_odds_id":"11914320","odds":"6.50"},{"game_odds_id":"11914321","odds":"12.00"},{"game_odds_id":"11914322","odds":"60.00"},{"game_odds_id":"11914323","odds":"300.0"},{"game_odds_id":"11914324","odds":"7.75"},{"game_odds_id":"11914325","odds":"11.50"},{"game_odds_id":"11914326","odds":"7.50"},{"game_odds_id":"11914327","odds":"22.00"},{"game_odds_id":"11914328","odds":"19.00"},{"game_odds_id":"11914329","odds":"22.00"},{"game_odds_id":"11914330","odds":"70.00"},{"game_odds_id":"11914331","odds":"42.00"},{"game_odds_id":"11914332","odds":"65.00"},{"game_odds_id":"11914333","odds":"200.0"},{"game_odds_id":"11914334","odds":"150.0"},{"game_odds_id":"11914335","odds":"250.0"},{"game_odds_id":"11914336","odds":"70.00"}],"total_odds":[{"game_odds_id":"11914337","odds":"9.00"},{"game_odds_id":"11914338","odds":"4.10"},{"game_odds_id":"11914339","odds":"3.40"},{"game_odds_id":"11914340","odds":"3.45"},{"game_odds_id":"11914341","odds":"5.50"},{"game_odds_id":"11914342","odds":"9.50"},{"game_odds_id":"11914343","odds":"19.00"},{"game_odds_id":"11914344","odds":"30.00"}],"half_odds":[{"game_odds_id":"11914345","odds":"4.05"},{"game_odds_id":"11914346","odds":"11.00"},{"game_odds_id":"11914347","odds":"23.00"},{"game_odds_id":"11914348","odds":"5.80"},{"game_odds_id":"11914349","odds":"5.20"},{"game_odds_id":"11914350","odds":"5.70"},{"game_odds_id":"11914351","odds":"25.00"},{"game_odds_id":"11914352","odds":"11.00"},{"game_odds_id":"11914353","odds":"3.85"}]}]
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

        public static class GameInfoBean  {
            /**
             * game_id : 119143
             * game_name : 亚冠
             * game_home_team_name : 墨胜利
             * game_guest_team_name : 广岛三箭
             * game_stop_time : 2019-05-22 18:00
             * game_begin_time : 17:50
             * game_home_score : 0
             * game_let_score : +1
             * game_no : 20190522001
             * game_sequence_no : 001
             * home_let_odds : [{"game_odds_id":"11914300","odds":"2.38"},{"game_odds_id":"11914301","odds":"3.35"},{"game_odds_id":"11914302","odds":"2.27"},{"game_odds_id":"11914303","odds":"1.42"},{"game_odds_id":"11914304","odds":"4.05"},{"game_odds_id":"11914305","odds":"4.80"}]
             * score_odds : [{"game_odds_id":"11914306","odds":"7.75"},{"game_odds_id":"11914307","odds":"12.50"},{"game_odds_id":"11914308","odds":"7.50"},{"game_odds_id":"11914309","odds":"25.00"},{"game_odds_id":"11914310","odds":"20.00"},{"game_odds_id":"11914311","odds":"22.00"},{"game_odds_id":"11914312","odds":"70.00"},{"game_odds_id":"11914313","odds":"50.00"},{"game_odds_id":"11914314","odds":"65.00"},{"game_odds_id":"11914315","odds":"250.0"},{"game_odds_id":"11914316","odds":"200.0"},{"game_odds_id":"11914317","odds":"250.0"},{"game_odds_id":"11914318","odds":"75.00"},{"game_odds_id":"11914319","odds":"9.00"},{"game_odds_id":"11914320","odds":"6.50"},{"game_odds_id":"11914321","odds":"12.00"},{"game_odds_id":"11914322","odds":"60.00"},{"game_odds_id":"11914323","odds":"300.0"},{"game_odds_id":"11914324","odds":"7.75"},{"game_odds_id":"11914325","odds":"11.50"},{"game_odds_id":"11914326","odds":"7.50"},{"game_odds_id":"11914327","odds":"22.00"},{"game_odds_id":"11914328","odds":"19.00"},{"game_odds_id":"11914329","odds":"22.00"},{"game_odds_id":"11914330","odds":"70.00"},{"game_odds_id":"11914331","odds":"42.00"},{"game_odds_id":"11914332","odds":"65.00"},{"game_odds_id":"11914333","odds":"200.0"},{"game_odds_id":"11914334","odds":"150.0"},{"game_odds_id":"11914335","odds":"250.0"},{"game_odds_id":"11914336","odds":"70.00"}]
             * total_odds : [{"game_odds_id":"11914337","odds":"9.00"},{"game_odds_id":"11914338","odds":"4.10"},{"game_odds_id":"11914339","odds":"3.40"},{"game_odds_id":"11914340","odds":"3.45"},{"game_odds_id":"11914341","odds":"5.50"},{"game_odds_id":"11914342","odds":"9.50"},{"game_odds_id":"11914343","odds":"19.00"},{"game_odds_id":"11914344","odds":"30.00"}]
             * half_odds : [{"game_odds_id":"11914345","odds":"4.05"},{"game_odds_id":"11914346","odds":"11.00"},{"game_odds_id":"11914347","odds":"23.00"},{"game_odds_id":"11914348","odds":"5.80"},{"game_odds_id":"11914349","odds":"5.20"},{"game_odds_id":"11914350","odds":"5.70"},{"game_odds_id":"11914351","odds":"25.00"},{"game_odds_id":"11914352","odds":"11.00"},{"game_odds_id":"11914353","odds":"3.85"}]
             */

            private String game_id;
            private String game_name;
            private String game_home_team_name;
            private String game_guest_team_name;
            private String game_stop_time;
            private String game_begin_time;
            private String game_home_score;
            private String game_let_score;
            private String game_no;
            private String game_sequence_no;
            private List<HomeLetOddsBean> home_let_odds;
            private List<ScoreOddsBean> score_odds;
            private List<TotalOddsBean> total_odds;
            private List<HalfOddsBean> half_odds;

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

            public List<HomeLetOddsBean> getHome_let_odds() {
                return home_let_odds;
            }

            public void setHome_let_odds(List<HomeLetOddsBean> home_let_odds) {
                this.home_let_odds = home_let_odds;
            }

            public List<ScoreOddsBean> getScore_odds() {
                return score_odds;
            }

            public void setScore_odds(List<ScoreOddsBean> score_odds) {
                this.score_odds = score_odds;
            }

            public List<TotalOddsBean> getTotal_odds() {
                return total_odds;
            }

            public void setTotal_odds(List<TotalOddsBean> total_odds) {
                this.total_odds = total_odds;
            }

            public List<HalfOddsBean> getHalf_odds() {
                return half_odds;
            }

            public void setHalf_odds(List<HalfOddsBean> half_odds) {
                this.half_odds = half_odds;
            }

            public static class HomeLetOddsBean  {
                /**
                 * game_odds_id : 11914300
                 * odds : 2.38
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

            public static class ScoreOddsBean  {
                /**
                 * game_odds_id : 11914306
                 * odds : 7.75
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

            public static class TotalOddsBean  {
                /**
                 * game_odds_id : 11914337
                 * odds : 9.00
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

            public static class HalfOddsBean  {
                /**
                 * game_odds_id : 11914345
                 * odds : 4.05
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
