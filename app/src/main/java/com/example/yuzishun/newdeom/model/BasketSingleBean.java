package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by apple on 2019/11/25.
 */

public class BasketSingleBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : {"game_info":[{"game_group_time":"2019-11-21","game_week":"周四","game_info":[{"game_id":"123090","game_name":"欧篮联","game_home_team_name":"柏林阿尔巴","game_guest_team_name":"奥林匹亚科斯","game_stop_time":"02:50","game_home_score":"0","game_let_score":"-1.50","game_no":"20191121301","game_sequence_no":"周四301","single_odds":[{"single":1,"game_odds_id":"12309006","odds":"4.05","odds_code":"客胜1-5"},{"single":1,"game_odds_id":"12309007","odds":"4.75","odds_code":"客胜6-10"},{"single":1,"game_odds_id":"12309008","odds":"8.90","odds_code":"客胜11-15"},{"single":1,"game_odds_id":"12309009","odds":"20.00","odds_code":"客胜16-20"},{"single":1,"game_odds_id":"12309010","odds":"39.00","odds_code":"客胜21-25"},{"single":1,"game_odds_id":"12309011","odds":"54.00","odds_code":"客胜26 "},{"single":1,"game_odds_id":"12309012","odds":"3.65","odds_code":"主胜1-5"},{"single":1,"game_odds_id":"12309013","odds":"4.05","odds_code":"主胜6-10"},{"single":1,"game_odds_id":"12309014","odds":"7.25","odds_code":"主胜11-15"},{"single":1,"game_odds_id":"12309015","odds":"14.50","odds_code":"主胜16-20"},{"single":1,"game_odds_id":"12309016","odds":"24.00","odds_code":"主胜21-25"},{"single":1,"game_odds_id":"12309017","odds":"38.00","odds_code":"主胜26 "}]},{"game_id":"123091","game_name":"欧篮联","game_home_team_name":"巴伦西亚","game_guest_team_name":"莫斯科希姆基","game_stop_time":"02:50","game_home_score":"0","game_let_score":" 3.50","game_no":"20191121302","game_sequence_no":"周四302","single_odds":[{"single":1,"game_odds_id":"12309106","odds":"3.50","odds_code":"客胜1-5"},{"single":1,"game_odds_id":"12309107","odds":"3.90","odds_code":"客胜6-10"},{"single":1,"game_odds_id":"12309108","odds":"6.45","odds_code":"客胜11-15"},{"single":1,"game_odds_id":"12309109","odds":"11.00","odds_code":"客胜16-20"},{"single":1,"game_odds_id":"12309110","odds":"19.00","odds_code":"客胜21-25"},{"single":1,"game_odds_id":"12309111","odds":"28.00","odds_code":"客胜26 "},{"single":1,"game_odds_id":"12309112","odds":"4.40","odds_code":"主胜1-5"},{"single":1,"game_odds_id":"12309113","odds":"5.65","odds_code":"主胜6-10"},{"single":1,"game_odds_id":"12309114","odds":"10.00","odds_code":"主胜11-15"},{"single":1,"game_odds_id":"12309115","odds":"24.00","odds_code":"主胜16-20"},{"single":1,"game_odds_id":"12309116","odds":"50.00","odds_code":"主胜21-25"},{"single":1,"game_odds_id":"12309117","odds":"60.00","odds_code":"主胜26 "}]},{"game_id":"123094","game_name":"美职篮","game_home_team_name":"密尔沃基雄鹿","game_guest_team_name":"波特兰开拓者","game_stop_time":"02:50","game_home_score":"0","game_let_score":"-12.50","game_no":"20191121303","game_sequence_no":"周四303","single_odds":[{"single":1,"game_odds_id":"12309406","odds":"15.00","odds_code":"客胜1-5"},{"single":1,"game_odds_id":"12309407","odds":"23.00","odds_code":"客胜6-10"},{"single":1,"game_odds_id":"12309408","odds":"45.00","odds_code":"客胜11-15"},{"single":1,"game_odds_id":"12309409","odds":"70.00","odds_code":"客胜16-20"},{"single":1,"game_odds_id":"12309410","odds":"100.0","odds_code":"客胜21-25"},{"single":1,"game_odds_id":"12309411","odds":"150.0","odds_code":"客胜26 "},{"single":1,"game_odds_id":"12309412","odds":"6.20","odds_code":"主胜1-5"},{"single":1,"game_odds_id":"12309413","odds":"3.85","odds_code":"主胜6-10"},{"single":1,"game_odds_id":"12309414","odds":"3.95","odds_code":"主胜11-15"},{"single":1,"game_odds_id":"12309415","odds":"4.25","odds_code":"主胜16-20"},{"single":1,"game_odds_id":"12309416","odds":"6.00","odds_code":"主胜21-25"},{"single":1,"game_odds_id":"12309417","odds":"4.55","odds_code":"主胜26 "}]},{"game_id":"123095","game_name":"美职篮","game_home_team_name":"菲尼克斯太阳","game_guest_team_name":"新奥尔良鹈鹕","game_stop_time":"11:20","game_home_score":"0","game_let_score":"-3.50","game_no":"20191121304","game_sequence_no":"周四304","single_odds":[{"single":1,"game_odds_id":"12309506","odds":"4.75","odds_code":"客胜1-5"},{"single":1,"game_odds_id":"12309507","odds":"5.50","odds_code":"客胜6-10"},{"single":1,"game_odds_id":"12309508","odds":"10.50","odds_code":"客胜11-15"},{"single":1,"game_odds_id":"12309509","odds":"22.00","odds_code":"客胜16-20"},{"single":1,"game_odds_id":"12309510","odds":"37.00","odds_code":"客胜21-25"},{"single":1,"game_odds_id":"12309511","odds":"52.00","odds_code":"客胜26 "},{"single":1,"game_odds_id":"12309512","odds":"4.20","odds_code":"主胜1-5"},{"single":1,"game_odds_id":"12309513","odds":"3.75","odds_code":"主胜6-10"},{"single":1,"game_odds_id":"12309514","odds":"5.90","odds_code":"主胜11-15"},{"single":1,"game_odds_id":"12309515","odds":"9.60","odds_code":"主胜16-20"},{"single":1,"game_odds_id":"12309516","odds":"19.50","odds_code":"主胜21-25"},{"single":1,"game_odds_id":"12309517","odds":"20.00","odds_code":"主胜26 "}]}]}],"game_name_list":[{"game_name":"欧篮联","type":0,"status":1},{"game_name":"美职篮","type":0,"status":1}]}
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
        private List<GameInfoBeanX> game_info;
        private List<GameNameListBean> game_name_list;

        public List<GameInfoBeanX> getGame_info() {
            return game_info;
        }

        public void setGame_info(List<GameInfoBeanX> game_info) {
            this.game_info = game_info;
        }

        public List<GameNameListBean> getGame_name_list() {
            return game_name_list;
        }

        public void setGame_name_list(List<GameNameListBean> game_name_list) {
            this.game_name_list = game_name_list;
        }

        public static class GameInfoBeanX {
            /**
             * game_group_time : 2019-11-21
             * game_week : 周四
             * game_info : [{"game_id":"123090","game_name":"欧篮联","game_home_team_name":"柏林阿尔巴","game_guest_team_name":"奥林匹亚科斯","game_stop_time":"02:50","game_home_score":"0","game_let_score":"-1.50","game_no":"20191121301","game_sequence_no":"周四301","single_odds":[{"single":1,"game_odds_id":"12309006","odds":"4.05","odds_code":"客胜1-5"},{"single":1,"game_odds_id":"12309007","odds":"4.75","odds_code":"客胜6-10"},{"single":1,"game_odds_id":"12309008","odds":"8.90","odds_code":"客胜11-15"},{"single":1,"game_odds_id":"12309009","odds":"20.00","odds_code":"客胜16-20"},{"single":1,"game_odds_id":"12309010","odds":"39.00","odds_code":"客胜21-25"},{"single":1,"game_odds_id":"12309011","odds":"54.00","odds_code":"客胜26 "},{"single":1,"game_odds_id":"12309012","odds":"3.65","odds_code":"主胜1-5"},{"single":1,"game_odds_id":"12309013","odds":"4.05","odds_code":"主胜6-10"},{"single":1,"game_odds_id":"12309014","odds":"7.25","odds_code":"主胜11-15"},{"single":1,"game_odds_id":"12309015","odds":"14.50","odds_code":"主胜16-20"},{"single":1,"game_odds_id":"12309016","odds":"24.00","odds_code":"主胜21-25"},{"single":1,"game_odds_id":"12309017","odds":"38.00","odds_code":"主胜26 "}]},{"game_id":"123091","game_name":"欧篮联","game_home_team_name":"巴伦西亚","game_guest_team_name":"莫斯科希姆基","game_stop_time":"02:50","game_home_score":"0","game_let_score":" 3.50","game_no":"20191121302","game_sequence_no":"周四302","single_odds":[{"single":1,"game_odds_id":"12309106","odds":"3.50","odds_code":"客胜1-5"},{"single":1,"game_odds_id":"12309107","odds":"3.90","odds_code":"客胜6-10"},{"single":1,"game_odds_id":"12309108","odds":"6.45","odds_code":"客胜11-15"},{"single":1,"game_odds_id":"12309109","odds":"11.00","odds_code":"客胜16-20"},{"single":1,"game_odds_id":"12309110","odds":"19.00","odds_code":"客胜21-25"},{"single":1,"game_odds_id":"12309111","odds":"28.00","odds_code":"客胜26 "},{"single":1,"game_odds_id":"12309112","odds":"4.40","odds_code":"主胜1-5"},{"single":1,"game_odds_id":"12309113","odds":"5.65","odds_code":"主胜6-10"},{"single":1,"game_odds_id":"12309114","odds":"10.00","odds_code":"主胜11-15"},{"single":1,"game_odds_id":"12309115","odds":"24.00","odds_code":"主胜16-20"},{"single":1,"game_odds_id":"12309116","odds":"50.00","odds_code":"主胜21-25"},{"single":1,"game_odds_id":"12309117","odds":"60.00","odds_code":"主胜26 "}]},{"game_id":"123094","game_name":"美职篮","game_home_team_name":"密尔沃基雄鹿","game_guest_team_name":"波特兰开拓者","game_stop_time":"02:50","game_home_score":"0","game_let_score":"-12.50","game_no":"20191121303","game_sequence_no":"周四303","single_odds":[{"single":1,"game_odds_id":"12309406","odds":"15.00","odds_code":"客胜1-5"},{"single":1,"game_odds_id":"12309407","odds":"23.00","odds_code":"客胜6-10"},{"single":1,"game_odds_id":"12309408","odds":"45.00","odds_code":"客胜11-15"},{"single":1,"game_odds_id":"12309409","odds":"70.00","odds_code":"客胜16-20"},{"single":1,"game_odds_id":"12309410","odds":"100.0","odds_code":"客胜21-25"},{"single":1,"game_odds_id":"12309411","odds":"150.0","odds_code":"客胜26 "},{"single":1,"game_odds_id":"12309412","odds":"6.20","odds_code":"主胜1-5"},{"single":1,"game_odds_id":"12309413","odds":"3.85","odds_code":"主胜6-10"},{"single":1,"game_odds_id":"12309414","odds":"3.95","odds_code":"主胜11-15"},{"single":1,"game_odds_id":"12309415","odds":"4.25","odds_code":"主胜16-20"},{"single":1,"game_odds_id":"12309416","odds":"6.00","odds_code":"主胜21-25"},{"single":1,"game_odds_id":"12309417","odds":"4.55","odds_code":"主胜26 "}]},{"game_id":"123095","game_name":"美职篮","game_home_team_name":"菲尼克斯太阳","game_guest_team_name":"新奥尔良鹈鹕","game_stop_time":"11:20","game_home_score":"0","game_let_score":"-3.50","game_no":"20191121304","game_sequence_no":"周四304","single_odds":[{"single":1,"game_odds_id":"12309506","odds":"4.75","odds_code":"客胜1-5"},{"single":1,"game_odds_id":"12309507","odds":"5.50","odds_code":"客胜6-10"},{"single":1,"game_odds_id":"12309508","odds":"10.50","odds_code":"客胜11-15"},{"single":1,"game_odds_id":"12309509","odds":"22.00","odds_code":"客胜16-20"},{"single":1,"game_odds_id":"12309510","odds":"37.00","odds_code":"客胜21-25"},{"single":1,"game_odds_id":"12309511","odds":"52.00","odds_code":"客胜26 "},{"single":1,"game_odds_id":"12309512","odds":"4.20","odds_code":"主胜1-5"},{"single":1,"game_odds_id":"12309513","odds":"3.75","odds_code":"主胜6-10"},{"single":1,"game_odds_id":"12309514","odds":"5.90","odds_code":"主胜11-15"},{"single":1,"game_odds_id":"12309515","odds":"9.60","odds_code":"主胜16-20"},{"single":1,"game_odds_id":"12309516","odds":"19.50","odds_code":"主胜21-25"},{"single":1,"game_odds_id":"12309517","odds":"20.00","odds_code":"主胜26 "}]}]
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
                 * game_id : 123090
                 * game_name : 欧篮联
                 * game_home_team_name : 柏林阿尔巴
                 * game_guest_team_name : 奥林匹亚科斯
                 * game_stop_time : 02:50
                 * game_home_score : 0
                 * game_let_score : -1.50
                 * game_no : 20191121301
                 * game_sequence_no : 周四301
                 * single_odds : [{"single":1,"game_odds_id":"12309006","odds":"4.05","odds_code":"客胜1-5"},{"single":1,"game_odds_id":"12309007","odds":"4.75","odds_code":"客胜6-10"},{"single":1,"game_odds_id":"12309008","odds":"8.90","odds_code":"客胜11-15"},{"single":1,"game_odds_id":"12309009","odds":"20.00","odds_code":"客胜16-20"},{"single":1,"game_odds_id":"12309010","odds":"39.00","odds_code":"客胜21-25"},{"single":1,"game_odds_id":"12309011","odds":"54.00","odds_code":"客胜26 "},{"single":1,"game_odds_id":"12309012","odds":"3.65","odds_code":"主胜1-5"},{"single":1,"game_odds_id":"12309013","odds":"4.05","odds_code":"主胜6-10"},{"single":1,"game_odds_id":"12309014","odds":"7.25","odds_code":"主胜11-15"},{"single":1,"game_odds_id":"12309015","odds":"14.50","odds_code":"主胜16-20"},{"single":1,"game_odds_id":"12309016","odds":"24.00","odds_code":"主胜21-25"},{"single":1,"game_odds_id":"12309017","odds":"38.00","odds_code":"主胜26 "}]
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
                     * single : 1
                     * game_odds_id : 12309006
                     * odds : 4.05
                     * odds_code : 客胜1-5
                     */

                    private int single;
                    private String game_odds_id;
                    private String odds;
                    private String odds_code;

                    public int getSingle() {
                        return single;
                    }

                    public void setSingle(int single) {
                        this.single = single;
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

                    public String getOdds_code() {
                        return odds_code;
                    }

                    public void setOdds_code(String odds_code) {
                        this.odds_code = odds_code;
                    }
                }
            }
        }

        public static class GameNameListBean {
            /**
             * game_name : 欧篮联
             * type : 0
             * status : 1
             */

            private String game_name;
            private int type;
            private int status;

            public String getGame_name() {
                return game_name;
            }

            public void setGame_name(String game_name) {
                this.game_name = game_name;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
