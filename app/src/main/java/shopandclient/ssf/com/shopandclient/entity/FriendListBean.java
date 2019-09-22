package shopandclient.ssf.com.shopandclient.entity;

import shopandclient.ssf.com.shopandclient.util.PinyinUtils;

import java.util.ArrayList;
import java.util.List;

public class FriendListBean {

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
        private ArrayList<NewFriendBean> newFriend;
        private ArrayList<MyFriendBean> myFriend;

        public ArrayList<NewFriendBean> getNewFriend() {
            return newFriend;
        }

        public void setNewFriend(ArrayList<NewFriendBean> newFriend) {
            this.newFriend = newFriend;
        }

        public ArrayList<MyFriendBean> getMyFriend() {
            return myFriend;
        }

        public void setMyFriend(ArrayList<MyFriendBean> myFriend) {
            this.myFriend = myFriend;
        }

        public static class NewFriendBean {
            private int friendID;
            private String friendName;
            private String Img;
            private String GuidNO;
            private int state;

            public int getFriendID() {
                return friendID;
            }

            public void setFriendID(int friendID) {
                this.friendID = friendID;
            }

            public String getFriendName() {
                return friendName;
            }

            public void setFriendName(String friendName) {
                this.friendName = friendName;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getGuidNO() {
                return GuidNO;
            }

            public void setGuidNO(String guidNO) {
                GuidNO = guidNO;
            }

            @Override
            public String toString() {
                return "NewFriendBean{" +
                        "friendID=" + friendID +
                        ", friendName='" + friendName + '\'' +
                        ", Img='" + Img + '\'' +
                        ", state=" + state +
                        '}';
            }
        }

        public static class MyFriendBean {
            private int friendID;
            private String friendName;
            private String Img;
            private int state;
            private String GuidNO;
            private String sortLetters;//显示数据拼音的首字母
            private boolean isChooseGroup=false;

            public MyFriendBean(int friendID, String friendName, String img, String GuidNO,int state) {
                this.friendID = friendID;
                this.friendName = friendName;
                Img = img;
                this.state = state;
                this.GuidNO=GuidNO;
                String pinyin = PinyinUtils.getPingYin(friendName);
                String sortString = pinyin.substring(0, 1).toUpperCase();
                setSortLetters(sortString);
            }

            public int getFriendID() {
                return friendID;
            }

            public void setFriendID(int friendID) {
                this.friendID = friendID;
            }

            public String getFriendName() {
                return friendName;
            }

            public void setFriendName(String friendName) {
                this.friendName = friendName;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public boolean isChooseGroup() {
                return isChooseGroup;
            }

            public void setChooseGroup(boolean chooseGroup) {
                isChooseGroup = chooseGroup;
            }

            public String getSortLetters() {
                return sortLetters;
            }

            public void setSortLetters(String sortLetters) {
                this.sortLetters = sortLetters;
            }

            public String getGuidNO() {
                return GuidNO;
            }

            public void setGuidNO(String guidNO) {
                GuidNO = guidNO;
            }

            @Override
            public String toString() {
                return "MyFriendBean{" +
                        "friendID=" + friendID +
                        ", friendName='" + friendName + '\'' +
                        ", Img='" + Img + '\'' +
                        ", state=" + state +
                        ", sortLetters='" + sortLetters + '\'' +
                        ", isChooseGroup=" + isChooseGroup +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "newFriend=" + newFriend +
                    ", myFriend=" + myFriend +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FriendListBean{" +
                "Code=" + Code +
                ", Result='" + Result + '\'' +
                ", Data=" + Data +
                '}';
    }
}
