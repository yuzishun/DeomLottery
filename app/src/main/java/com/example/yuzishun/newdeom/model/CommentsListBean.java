package com.example.yuzishun.newdeom.model;

import java.util.List;

/**
 * Created by apple on 2019/8/16.
 */

public class CommentsListBean {


    /**
     * code : 10000
     * msg : 查询成功！
     * data : [{"user_id":56,"uname":"zhenz1","img_head":"http://192.168.1.26//default/default_img_head.png","comment_id":296,"bask_id":14,"type":0,"comment_parent_user_id":43,"comment_user_id":56,"comment_content":"评论内容","like_count":0,"like_type_id":14,"create_time":"2019-08-08 13:07:39","parent_id":0,"comment_is_like":0,"children":[{"uname":"zhenz1","comment_id":299,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":300,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":297,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":298,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"}]},{"user_id":56,"uname":"zhenz1","img_head":"http://192.168.1.26//default/default_img_head.png","comment_id":293,"bask_id":14,"type":0,"comment_parent_user_id":43,"comment_user_id":56,"comment_content":"评论内容","like_count":0,"like_type_id":14,"create_time":"2019-08-08 13:06:38","parent_id":0,"comment_is_like":0,"children":[{"uname":"zhenz1","comment_id":327,"comment_content":"dagou","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":307,"comment_content":"dagou","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":308,"comment_content":"dagou","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":306,"comment_content":"评论内容222222","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":305,"comment_content":"评论内容","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":303,"comment_content":"回复内容3","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":304,"comment_content":"回复内容3","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":294,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":295,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"}]}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
         * user_id : 56
         * uname : zhenz1
         * img_head : http://192.168.1.26//default/default_img_head.png
         * comment_id : 296
         * bask_id : 14
         * type : 0
         * comment_parent_user_id : 43
         * comment_user_id : 56
         * comment_content : 评论内容
         * like_count : 0
         * like_type_id : 14
         * create_time : 2019-08-08 13:07:39
         * parent_id : 0
         * comment_is_like : 0
         * children : [{"uname":"zhenz1","comment_id":299,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":300,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":297,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"},{"uname":"zhenz1","comment_id":298,"comment_content":"回复内容2","comment_parent_user_id":56,"type":1,"by_reply":"zhenz1"}]
         */

        private int user_id;
        private String uname;
        private String img_head;
        private int comment_id;
        private int bask_id;
        private int type;
        private int comment_parent_user_id;
        private int comment_user_id;
        private String comment_content;
        private int like_count;
        private int like_type_id;
        private String create_time;
        private int parent_id;
        private int comment_is_like;
        private List<ChildrenBean> children;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public int getBask_id() {
            return bask_id;
        }

        public void setBask_id(int bask_id) {
            this.bask_id = bask_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getComment_parent_user_id() {
            return comment_parent_user_id;
        }

        public void setComment_parent_user_id(int comment_parent_user_id) {
            this.comment_parent_user_id = comment_parent_user_id;
        }

        public int getComment_user_id() {
            return comment_user_id;
        }

        public void setComment_user_id(int comment_user_id) {
            this.comment_user_id = comment_user_id;
        }

        public String getComment_content() {
            return comment_content;
        }

        public void setComment_content(String comment_content) {
            this.comment_content = comment_content;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getLike_type_id() {
            return like_type_id;
        }

        public void setLike_type_id(int like_type_id) {
            this.like_type_id = like_type_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getParent_id() {
            return parent_id;
        }

        public void setParent_id(int parent_id) {
            this.parent_id = parent_id;
        }

        public int getComment_is_like() {
            return comment_is_like;
        }

        public void setComment_is_like(int comment_is_like) {
            this.comment_is_like = comment_is_like;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * uname : zhenz1
             * comment_id : 299
             * comment_content : 回复内容2
             * comment_parent_user_id : 56
             * type : 1
             * by_reply : zhenz1
             */

            private String uname;
            private int comment_id;
            private String comment_content;
            private int comment_parent_user_id;
            private int type;
            private String by_reply;

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public String getComment_content() {
                return comment_content;
            }

            public void setComment_content(String comment_content) {
                this.comment_content = comment_content;
            }

            public int getComment_parent_user_id() {
                return comment_parent_user_id;
            }

            public void setComment_parent_user_id(int comment_parent_user_id) {
                this.comment_parent_user_id = comment_parent_user_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getBy_reply() {
                return by_reply;
            }

            public void setBy_reply(String by_reply) {
                this.by_reply = by_reply;
            }
        }
    }
}
