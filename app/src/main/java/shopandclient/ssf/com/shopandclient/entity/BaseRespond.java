package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/4/15.
 */
public class BaseRespond {
    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
