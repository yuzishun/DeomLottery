package com.example.yuzishun.newdeom.model;

/**
 * Created by yuzishun on 2019/5/16.
 */

public class LoginBean {


    /**
     * code : 10000
     * msg : 登录成功
     * data : {"user_info":{"user_id":3,"phone":"15801693719","uname":"ashun","img_head":"","create_time":"2019-05-16 10:29:16","update_time":"2019-05-16 10:29:16","authentication":0,"compellation":"","identity_card":"","player":0,"god":0,"account":{"id":2,"user_id":3,"balance":"0.00","available_balance":"0.00","no_available_balance":"0.00","frozen_account":"0.00","update_time":""}},"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsYXQiOjE1NTc5OTE1OTQsIm5iZiI6MTU1Nzk5MTU5NCwiZXhwIjoxNTU4NTk2Mzk0LCJ0YWciOjN9.XMyiIUOXo9zBW4kMdHNLx4gxjF0ocnPLXvEJrsga8MY"}
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
         * user_info : {"user_id":3,"phone":"15801693719","uname":"ashun","img_head":"","create_time":"2019-05-16 10:29:16","update_time":"2019-05-16 10:29:16","authentication":0,"compellation":"","identity_card":"","player":0,"god":0,"account":{"id":2,"user_id":3,"balance":"0.00","available_balance":"0.00","no_available_balance":"0.00","frozen_account":"0.00","update_time":""}}
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsYXQiOjE1NTc5OTE1OTQsIm5iZiI6MTU1Nzk5MTU5NCwiZXhwIjoxNTU4NTk2Mzk0LCJ0YWciOjN9.XMyiIUOXo9zBW4kMdHNLx4gxjF0ocnPLXvEJrsga8MY
         */

        private UserInfoBean user_info;
        private String token;

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserInfoBean {
            /**
             * user_id : 3
             * phone : 15801693719
             * uname : ashun
             * img_head :
             * create_time : 2019-05-16 10:29:16
             * update_time : 2019-05-16 10:29:16
             * authentication : 0
             * compellation :
             * identity_card :
             * player : 0
             * god : 0
             * account : {"id":2,"user_id":3,"balance":"0.00","available_balance":"0.00","no_available_balance":"0.00","frozen_account":"0.00","update_time":""}
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
            private AccountBean account;

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

            public AccountBean getAccount() {
                return account;
            }

            public void setAccount(AccountBean account) {
                this.account = account;
            }

            public static class AccountBean {
                /**
                 * id : 2
                 * user_id : 3
                 * balance : 0.00
                 * available_balance : 0.00
                 * no_available_balance : 0.00
                 * frozen_account : 0.00
                 * update_time :
                 */

                private int id;
                private int user_id;
                private String balance;
                private String available_balance;
                private String no_available_balance;
                private String frozen_account;
                private String update_time;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

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
}
