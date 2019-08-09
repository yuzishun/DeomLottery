package com.example.yuzishun.newdeom.model;

/**
 * Created by yuzishun on 2019/5/16.
 */

public class UserInfoBean {


    /**
     * code : 10000
     * msg : 查询成功
     * data : {"user_id":2,"phone":"17557280734","uname":"xiele","img_head":"http:192.168.1.9/upload/20190517/6d0de43986de392a63d7b186b13c2b0d.png","create_time":"2019-05-13 17:49:15","update_time":"2019-05-17 10:17:20","authentication":1,"compellation":"谢勇","identity_card":"430422199711291010","player":0,"god":0,"alipay":"17557280734","account":{"balance":"0.00","available_balance":"0.00","no_available_balance":"0.00","frozen_account":"0.00","update_time":"2019-05-13 17:49:15"}}
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
         * user_id : 2
         * phone : 17557280734
         * uname : xiele
         * img_head : http:192.168.1.9/upload/20190517/6d0de43986de392a63d7b186b13c2b0d.png
         * create_time : 2019-05-13 17:49:15
         * update_time : 2019-05-17 10:17:20
         * authentication : 1
         * compellation : 谢勇
         * identity_card : 430422199711291010
         * player : 0
         * god : 0
         * alipay : 17557280734
         * account : {"balance":"0.00","available_balance":"0.00","no_available_balance":"0.00","frozen_account":"0.00","update_time":"2019-05-13 17:49:15"}
         */

        private int user_id;
        private String phone;
        private String uname;
        private String img_head;
        private String create_time;
        private String update_time;
        private int authentication;
        private String compellation;
        private String identity_card;
        private int player;
        private int god;
        private String alipay;
        private AccountBean account;
        private int user_fans;
        private int user_attention;


        public int getUser_fans() {
            return user_fans;
        }

        public void setUser_fans(int user_fans) {
            this.user_fans = user_fans;
        }

        public int getUser_attention() {
            return user_attention;
        }

        public void setUser_attention(int user_attention) {
            this.user_attention = user_attention;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public int getAuthentication() {
            return authentication;
        }

        public void setAuthentication(int authentication) {
            this.authentication = authentication;
        }

        public String getCompellation() {
            return compellation;
        }

        public void setCompellation(String compellation) {
            this.compellation = compellation;
        }

        public String getIdentity_card() {
            return identity_card;
        }

        public void setIdentity_card(String identity_card) {
            this.identity_card = identity_card;
        }

        public int getPlayer() {
            return player;
        }

        public void setPlayer(int player) {
            this.player = player;
        }

        public int getGod() {
            return god;
        }

        public void setGod(int god) {
            this.god = god;
        }

        public String getAlipay() {
            return alipay;
        }

        public void setAlipay(String alipay) {
            this.alipay = alipay;
        }

        public AccountBean getAccount() {
            return account;
        }

        public void setAccount(AccountBean account) {
            this.account = account;
        }

        public static class AccountBean {
            /**
             * balance : 0.00
             * available_balance : 0.00
             * no_available_balance : 0.00
             * frozen_account : 0.00
             * update_time : 2019-05-13 17:49:15
             */

            private String balance;
            private String available_balance;
            private String no_available_balance;
            private String frozen_account;
            private String update_time;

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getAvailable_balance() {
                return available_balance;
            }

            public void setAvailable_balance(String available_balance) {
                this.available_balance = available_balance;
            }

            public String getNo_available_balance() {
                return no_available_balance;
            }

            public void setNo_available_balance(String no_available_balance) {
                this.no_available_balance = no_available_balance;
            }

            public String getFrozen_account() {
                return frozen_account;
            }

            public void setFrozen_account(String frozen_account) {
                this.frozen_account = frozen_account;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}
