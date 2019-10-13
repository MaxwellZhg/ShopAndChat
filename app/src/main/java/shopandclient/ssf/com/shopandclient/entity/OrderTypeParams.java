package shopandclient.ssf.com.shopandclient.entity;

public class OrderTypeParams {

    private int typeid;
    private int page;

    public OrderTypeParams(int typeid, int page) {
        this.typeid = typeid;
        this.page = page;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
