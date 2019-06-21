package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/17.
 */
public class CommentStarBean {
    private Boolean check;

    public CommentStarBean(Boolean check) {
        this.check = check;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "CommentStarBean{" +
                "check=" + check +
                '}';
    }
}
