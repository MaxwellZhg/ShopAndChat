package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

public class GroupInfoBean {

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
        private int totalNum;
        private String title;
        private ArrayList<ListBean> list;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<ListBean> getList() {
            return list;
        }

        public void setList(ArrayList<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private int groupID;
            private int UserID;
            private String UserName;
            private int groupAdminID;
            private int ifForbid;
            private String Img;
            private boolean isChooseGroup=false;

            public boolean isChooseGroup() {
                return isChooseGroup;
            }

            public void setChooseGroup(boolean chooseGroup) {
                isChooseGroup = chooseGroup;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGroupID() {
                return groupID;
            }

            public void setGroupID(int groupID) {
                this.groupID = groupID;
            }

            public int getUserID() {
                return UserID;
            }

            public void setUserID(int UserID) {
                this.UserID = UserID;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public int getGroupAdminID() {
                return groupAdminID;
            }

            public void setGroupAdminID(int groupAdminID) {
                this.groupAdminID = groupAdminID;
            }

            public int getIfForbid() {
                return ifForbid;
            }

            public void setIfForbid(int ifForbid) {
                this.ifForbid = ifForbid;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id=" + id +
                        ", groupID=" + groupID +
                        ", UserID=" + UserID +
                        ", UserName='" + UserName + '\'' +
                        ", groupAdminID=" + groupAdminID +
                        ", ifForbid=" + ifForbid +
                        ", Img='" + Img + '\'' +
                        ", isChooseGroup=" + isChooseGroup +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "totalNum=" + totalNum +
                    ", title='" + title + '\'' +
                    ", list=" + list +
                    '}';
        }
    }
}
