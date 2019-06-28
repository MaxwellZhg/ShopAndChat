package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/24.
 */
public class UserInfo {

    /**
     * Code : 200
     * Result : 操作成功
     * Data : {"UserID":1,"AccuntNo":"Rainbow","Phone":"18673837602","UserName":"凯","UserType":1,"img":"/img/logo.jpg","sign":"好好好","level":0,"creditLevel":0,"giveLikeNum":0,"sex":"男","area":"中国香港","UserState":0}
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
         * UserID : 1
         * AccuntNo : Rainbow
         * Phone : 18673837602
         * UserName : 凯
         * UserType : 1
         * img : /img/logo.jpg
         * sign : 好好好
         * level : 0
         * creditLevel : 0
         * giveLikeNum : 0
         * sex : 男
         * area : 中国香港
         * UserState : 0
         */

        private int UserID;
        private String AccuntNo;
        private String Phone;
        private String UserName;
        private int UserType;
        private String img;
        private String sign;
        private int level;
        private int creditLevel;
        private int giveLikeNum;
        private String sex;
        private String area;
        private int UserState;

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
        }

        public String getAccuntNo() {
            return AccuntNo;
        }

        public void setAccuntNo(String AccuntNo) {
            this.AccuntNo = AccuntNo;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public int getUserType() {
            return UserType;
        }

        public void setUserType(int UserType) {
            this.UserType = UserType;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(int creditLevel) {
            this.creditLevel = creditLevel;
        }

        public int getGiveLikeNum() {
            return giveLikeNum;
        }

        public void setGiveLikeNum(int giveLikeNum) {
            this.giveLikeNum = giveLikeNum;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getUserState() {
            return UserState;
        }

        public void setUserState(int UserState) {
            this.UserState = UserState;
        }
    }
}
