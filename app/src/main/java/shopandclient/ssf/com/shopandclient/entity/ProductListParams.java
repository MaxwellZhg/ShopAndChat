package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/26.
 */
public class ProductListParams {
    /**
     * seriesID : 1
     * categroyID : 8
     * orderAsc : 1
     * page : 1
     */

    private int seriesID;
    private int categroyID;
    private int orderAsc;
    private int page;

    public ProductListParams() {
    }

    public ProductListParams(int seriesID, int categroyID, int orderAsc, int page) {
        this.seriesID = seriesID;
        this.categroyID = categroyID;
        this.orderAsc = orderAsc;
        this.page = page;
    }

    public int getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(int seriesID) {
        this.seriesID = seriesID;
    }

    public int getCategroyID() {
        return categroyID;
    }

    public void setCategroyID(int categroyID) {
        this.categroyID = categroyID;
    }

    public int getOrderAsc() {
        return orderAsc;
    }

    public void setOrderAsc(int orderAsc) {
        this.orderAsc = orderAsc;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
