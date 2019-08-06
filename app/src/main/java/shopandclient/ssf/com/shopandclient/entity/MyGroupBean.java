package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

public class MyGroupBean {

    private int Code;
    private String Result;
    private ArrayList<DataBean> Data;

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

    public ArrayList<DataBean> getData() {
        return Data;
    }

    public void setData(ArrayList<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private int GroupAdminID;
        private int id;
        private int groupID;
        private int UserID;
        private String groupName;

        public int getGroupAdminID() {
            return GroupAdminID;
        }

        public void setGroupAdminID(int GroupAdminID) {
            this.GroupAdminID = GroupAdminID;
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

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "GroupAdminID=" + GroupAdminID +
                    ", id=" + id +
                    ", groupID=" + groupID +
                    ", UserID=" + UserID +
                    ", groupName='" + groupName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyGroupBean{" +
                "Code=" + Code +
                ", Result='" + Result + '\'' +
                ", Data=" + Data +
                '}';
    }
}
