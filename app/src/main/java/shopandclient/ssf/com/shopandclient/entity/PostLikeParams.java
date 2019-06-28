package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/28.
 */
public class PostLikeParams {

    /**
     * resID : 1
     * typeid : 2
     */

    private int resID;
    private int typeid;

    public PostLikeParams(int resID, int typeid) {
        this.resID = resID;
        this.typeid = typeid;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }
}
