package com.example.yuzishun.deomlottery.model;

import java.util.List;

/**
 * Created by apple on 2019/8/8.
 */

public class PeriodBean_Bask {


    /**
     * code : 10000
     * msg : 查询成功
     * data : [{"game_id":"120188","game_name":"美职女篮","game_home_team_name":"康涅狄格太阳","game_guest_team_name":"菲尼克斯水星","game_last_score":"62:68","game_no":"20190801301","game_sequence_no":"周四301","win_odds":[{"game_odds_id":"12018800","odds":"1.20","odds_code":"主胜"},{"game_odds_id":"12018803","odds":"1.70","odds_code":"让分主负(-6.50)"},{"game_odds_id":"12018805","odds":"1.75","odds_code":"小分(130)"},{"game_odds_id":"12018813","odds":"3.40","odds_code":"主胜6-10"}]},{"game_id":"120189","game_name":"美职女篮","game_home_team_name":"达拉斯飞马","game_guest_team_name":"纽约自由","game_last_score":"64:87","game_no":"20190801302","game_sequence_no":"周四302","win_odds":[{"game_odds_id":"12018900","odds":"1.63","odds_code":"主胜"},{"game_odds_id":"12018902","odds":"1.77","odds_code":"让分主胜(-1.50)"},{"game_odds_id":"12018904","odds":"1.70","odds_code":"大分(151)"},{"game_odds_id":"12018916","odds":"28.00","odds_code":"主胜21-25"}]},{"game_id":"120190","game_name":"美职女篮","game_home_team_name":"洛杉矶火花","game_guest_team_name":"拉斯维加斯王牌","game_last_score":"68:76","game_no":"20190801303","game_sequence_no":"周四303","win_odds":[{"game_odds_id":"12019000","odds":"1.53","odds_code":"主胜"},{"game_odds_id":"12019002","odds":"1.63","odds_code":"让分主胜(-1.50)"},{"game_odds_id":"12019005","odds":"1.70","odds_code":"小分(144)"},{"game_odds_id":"12019013","odds":"4.30","odds_code":"主胜6-10"}]},{"game_id":"120299","game_name":"美职女篮","game_home_team_name":"亚特兰大梦想","game_guest_team_name":"芝加哥天空","game_last_score":"87:75","game_no":"20190803301","game_sequence_no":"周六301","win_odds":[{"game_odds_id":"12029901","odds":"1.40","odds_code":"主负"},{"game_odds_id":"12029903","odds":"1.70","odds_code":"让分主负(+3.50)"},{"game_odds_id":"12029904","odds":"1.65","odds_code":"大分(162)"},{"game_odds_id":"12029908","odds":"5.80","odds_code":"客胜11-15"}]},{"game_id":"120300","game_name":"美职女篮","game_home_team_name":"印第安纳狂热","game_guest_team_name":"明尼苏达天猫","game_last_score":"75:86","game_no":"20190803302","game_sequence_no":"周六302","win_odds":[{"game_odds_id":"12030000","odds":"2.00","odds_code":"主胜"},{"game_odds_id":"12030002","odds":"1.70","odds_code":"让分主胜(+3.50)"},{"game_odds_id":"12030004","odds":"1.70","odds_code":"大分(161)"},{"game_odds_id":"12030014","odds":"9.00","odds_code":"主胜11-15"}]},{"game_id":"120301","game_name":"美职女篮","game_home_team_name":"达拉斯飞马","game_guest_team_name":"拉斯维加斯王牌","game_last_score":"75:70","game_no":"20190803303","game_sequence_no":"周六303","win_odds":[{"game_odds_id":"12030101","odds":"1.14","odds_code":"主负"},{"game_odds_id":"12030102","odds":"1.70","odds_code":"让分主胜(+7.50)"},{"game_odds_id":"12030105","odds":"1.70","odds_code":"小分(145)"},{"game_odds_id":"12030106","odds":"4.40","odds_code":"客胜1-5"}]},{"game_id":"120302","game_name":"美职女篮","game_home_team_name":"纽约自由","game_guest_team_name":"康涅狄格太阳","game_last_score":"94:79","game_no":"20190804301","game_sequence_no":"周日301","win_odds":[{"game_odds_id":"12030201","odds":"1.23","odds_code":"主负"},{"game_odds_id":"12030203","odds":"1.70","odds_code":"让分主负(+5.50)"},{"game_odds_id":"12030204","odds":"1.65","odds_code":"大分(173)"},{"game_odds_id":"12030208","odds":"4.55","odds_code":"客胜11-15"}]},{"game_id":"120303","game_name":"美职女篮","game_home_team_name":"洛杉矶火花","game_guest_team_name":"西雅图风暴","game_last_score":"75:83","game_no":"20190804302","game_sequence_no":"周日302","win_odds":[{"game_odds_id":"12030300","odds":"1.20","odds_code":"主胜"},{"game_odds_id":"12030302","odds":"1.63","odds_code":"让分主胜(-5.50)"},{"game_odds_id":"12030304","odds":"1.65","odds_code":"大分(158)"},{"game_odds_id":"12030313","odds":"3.40","odds_code":"主胜6-10"}]},{"game_id":"120304","game_name":"美职女篮","game_home_team_name":"菲尼克斯水星","game_guest_team_name":"华盛顿神秘人","game_last_score":"82:103","game_no":"20190804303","game_sequence_no":"周日303","win_odds":[{"game_odds_id":"12030400","odds":"2.90","odds_code":"主胜"},{"game_odds_id":"12030402","odds":"1.70","odds_code":"让分主胜(+6.50)"},{"game_odds_id":"12030404","odds":"1.65","odds_code":"大分(185)"},{"game_odds_id":"12030416","odds":"50.00","odds_code":"主胜21-25"}]}]
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
         * game_id : 120188
         * game_name : 美职女篮
         * game_home_team_name : 康涅狄格太阳
         * game_guest_team_name : 菲尼克斯水星
         * game_last_score : 62:68
         * game_no : 20190801301
         * game_sequence_no : 周四301
         * win_odds : [{"game_odds_id":"12018800","odds":"1.20","odds_code":"主胜"},{"game_odds_id":"12018803","odds":"1.70","odds_code":"让分主负(-6.50)"},{"game_odds_id":"12018805","odds":"1.75","odds_code":"小分(130)"},{"game_odds_id":"12018813","odds":"3.40","odds_code":"主胜6-10"}]
         */

        private String game_id;
        private String game_name;
        private String game_home_team_name;
        private String game_guest_team_name;
        private String game_last_score;
        private String game_no;
        private String game_sequence_no;
        private List<WinOddsBean> win_odds;

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

        public String getGame_last_score() {
            return game_last_score;
        }

        public void setGame_last_score(String game_last_score) {
            this.game_last_score = game_last_score;
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

        public List<WinOddsBean> getWin_odds() {
            return win_odds;
        }

        public void setWin_odds(List<WinOddsBean> win_odds) {
            this.win_odds = win_odds;
        }

        public static class WinOddsBean {
            /**
             * game_odds_id : 12018800
             * odds : 1.20
             * odds_code : 主胜
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
