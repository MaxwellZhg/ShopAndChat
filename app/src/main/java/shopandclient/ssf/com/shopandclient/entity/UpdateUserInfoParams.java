package shopandclient.ssf.com.shopandclient.entity;

public class UpdateUserInfoParams {

    private int userID;
    private String img;
    private String userName;
    private String sign;

    public UpdateUserInfoParams(int userID, String img, String userName, String sign) {
        this.userID = userID;
        this.img = img;
        this.userName = userName;
        this.sign = sign;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
