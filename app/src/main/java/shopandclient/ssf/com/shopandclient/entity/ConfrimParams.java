package shopandclient.ssf.com.shopandclient.entity;

public class ConfrimParams {

    private int friendID;
    private int state;

    public ConfrimParams(int friendID, int state) {
        this.friendID = friendID;
        this.state = state;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
