package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/6/27.
 */
public class CommentBean {

    /**
     * Code : 200
     * Result : 请求成功
     * Data : {"totalNum":1,"list":[{"id":1,"user_id":1,"userName":"兰陵王","commentID":0,"proID":1,"comments":"好！好！好！","reply":"","level":1,"giveALikeNum":0,"times":"2019-06-17 09:40:44","withInComments":[{"id":2,"user_id":14,"userName":"孙悟空","commentID":1,"proID":1,"comments":"好。很好。超级好","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-17 09:41:08","proImg":[]},{"id":3,"user_id":14,"userName":"孙悟空","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-18 05:06:32","proImg":[]},{"id":4,"user_id":14,"userName":"孙悟空","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-18 06:50:40","proImg":[]},{"id":5,"user_id":1,"userName":"兰陵王","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-18 07:28:54","proImg":[]},{"id":6,"user_id":1,"userName":"凯","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-24 04:24:37","proImg":[]},{"id":7,"user_id":1,"userName":"凯","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-24 09:00:04","proImg":[]},{"id":8,"user_id":1,"userName":"凯","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-25 04:51:58","proImg":[]}],"proImg":[{"id":0,"pro_id":0,"fileImg":"/img/2.jpg","typeID":0,"commentID":0}]}]}
     */

    private int Code;
    private String Result;
    private DataBean Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * totalNum : 1
         * list : [{"id":1,"user_id":1,"userName":"兰陵王","commentID":0,"proID":1,"comments":"好！好！好！","reply":"","level":1,"giveALikeNum":0,"times":"2019-06-17 09:40:44","withInComments":[{"id":2,"user_id":14,"userName":"孙悟空","commentID":1,"proID":1,"comments":"好。很好。超级好","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-17 09:41:08","proImg":[]},{"id":3,"user_id":14,"userName":"孙悟空","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-18 05:06:32","proImg":[]},{"id":4,"user_id":14,"userName":"孙悟空","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-18 06:50:40","proImg":[]},{"id":5,"user_id":1,"userName":"兰陵王","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-18 07:28:54","proImg":[]},{"id":6,"user_id":1,"userName":"凯","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-24 04:24:37","proImg":[]},{"id":7,"user_id":1,"userName":"凯","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-24 09:00:04","proImg":[]},{"id":8,"user_id":1,"userName":"凯","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-25 04:51:58","proImg":[]}],"proImg":[{"id":0,"pro_id":0,"fileImg":"/img/2.jpg","typeID":0,"commentID":0}]}]
         */

        private int totalNum;
        private ArrayList<ListBean> list;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public ArrayList<ListBean> getList() {
            return list;
        }

        public void setList(ArrayList<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * user_id : 1
             * userName : 兰陵王
             * commentID : 0
             * proID : 1
             * comments : 好！好！好！
             * reply :
             * level : 1
             * giveALikeNum : 0
             * times : 2019-06-17 09:40:44
             * withInComments : [{"id":2,"user_id":14,"userName":"孙悟空","commentID":1,"proID":1,"comments":"好。很好。超级好","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-17 09:41:08","proImg":[]},{"id":3,"user_id":14,"userName":"孙悟空","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-18 05:06:32","proImg":[]},{"id":4,"user_id":14,"userName":"孙悟空","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-18 06:50:40","proImg":[]},{"id":5,"user_id":1,"userName":"兰陵王","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-18 07:28:54","proImg":[]},{"id":6,"user_id":1,"userName":"凯","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-24 04:24:37","proImg":[]},{"id":7,"user_id":1,"userName":"凯","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-24 09:00:04","proImg":[]},{"id":8,"user_id":1,"userName":"凯","commentID":1,"proID":1,"comments":"评论中的评论","reply":"","level":0,"giveALikeNum":0,"times":"2019-06-25 04:51:58","proImg":[]}]
             * proImg : [{"id":0,"pro_id":0,"fileImg":"/img/2.jpg","typeID":0,"commentID":0}]
             */

            private int id;
            private int user_id;
            private String userName;
            private int commentID;
            private int proID;
            private String comments;
            private String reply;
            private int level;
            private int giveALikeNum;
            private String times;
            private ArrayList<WithInCommentsBean> withInComments;
            private ArrayList<ProImgBean> proImg;

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

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getCommentID() {
                return commentID;
            }

            public void setCommentID(int commentID) {
                this.commentID = commentID;
            }

            public int getProID() {
                return proID;
            }

            public void setProID(int proID) {
                this.proID = proID;
            }

            public String getComments() {
                return comments;
            }

            public void setComments(String comments) {
                this.comments = comments;
            }

            public String getReply() {
                return reply;
            }

            public void setReply(String reply) {
                this.reply = reply;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getGiveALikeNum() {
                return giveALikeNum;
            }

            public void setGiveALikeNum(int giveALikeNum) {
                this.giveALikeNum = giveALikeNum;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public ArrayList<WithInCommentsBean> getWithInComments() {
                return withInComments;
            }

            public void setWithInComments(ArrayList<WithInCommentsBean> withInComments) {
                this.withInComments = withInComments;
            }

            public ArrayList<ProImgBean> getProImg() {
                return proImg;
            }

            public void setProImg(ArrayList<ProImgBean> proImg) {
                this.proImg = proImg;
            }

            public static class WithInCommentsBean {
                /**
                 * id : 2
                 * user_id : 14
                 * userName : 孙悟空
                 * commentID : 1
                 * proID : 1
                 * comments : 好。很好。超级好
                 * reply :
                 * level : 0
                 * giveALikeNum : 0
                 * times : 2019-06-17 09:41:08
                 * proImg : []
                 */

                private int id;
                private int user_id;
                private String userName;
                private int commentID;
                private int proID;
                private String comments;
                private String reply;
                private int level;
                private int giveALikeNum;
                private String times;
                private ArrayList<?> proImg;

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

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public int getCommentID() {
                    return commentID;
                }

                public void setCommentID(int commentID) {
                    this.commentID = commentID;
                }

                public int getProID() {
                    return proID;
                }

                public void setProID(int proID) {
                    this.proID = proID;
                }

                public String getComments() {
                    return comments;
                }

                public void setComments(String comments) {
                    this.comments = comments;
                }

                public String getReply() {
                    return reply;
                }

                public void setReply(String reply) {
                    this.reply = reply;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public int getGiveALikeNum() {
                    return giveALikeNum;
                }

                public void setGiveALikeNum(int giveALikeNum) {
                    this.giveALikeNum = giveALikeNum;
                }

                public String getTimes() {
                    return times;
                }

                public void setTimes(String times) {
                    this.times = times;
                }

                public ArrayList<?> getProImg() {
                    return proImg;
                }

                public void setProImg(ArrayList<?> proImg) {
                    this.proImg = proImg;
                }
            }

            public static class ProImgBean {
                /**
                 * id : 0
                 * pro_id : 0
                 * fileImg : /img/2.jpg
                 * typeID : 0
                 * commentID : 0
                 */

                private int id;
                private int pro_id;
                private String fileImg;
                private int typeID;
                private int commentID;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getPro_id() {
                    return pro_id;
                }

                public void setPro_id(int pro_id) {
                    this.pro_id = pro_id;
                }

                public String getFileImg() {
                    return fileImg;
                }

                public void setFileImg(String fileImg) {
                    this.fileImg = fileImg;
                }

                public int getTypeID() {
                    return typeID;
                }

                public void setTypeID(int typeID) {
                    this.typeID = typeID;
                }

                public int getCommentID() {
                    return commentID;
                }

                public void setCommentID(int commentID) {
                    this.commentID = commentID;
                }
            }
        }
    }
}
