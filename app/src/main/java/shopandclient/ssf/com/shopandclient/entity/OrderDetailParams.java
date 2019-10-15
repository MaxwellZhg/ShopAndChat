package shopandclient.ssf.com.shopandclient.entity;

public class OrderDetailParams {

    private String orderNo;

    public OrderDetailParams(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
