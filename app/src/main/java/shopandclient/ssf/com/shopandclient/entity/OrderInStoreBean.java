package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/11.
 */
public class OrderInStoreBean {
    private String storeName;
    private ArrayList<OrderDetailBean> orderDetailBeans;

    public OrderInStoreBean(String storeName, ArrayList<OrderDetailBean> orderDetailBeans) {
        this.storeName = storeName;
        this.orderDetailBeans = orderDetailBeans;
    }

    public OrderInStoreBean() {
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public ArrayList<OrderDetailBean> getOrderDetailBeans() {
        return orderDetailBeans;
    }

    public void setOrderDetailBeans(ArrayList<OrderDetailBean> orderDetailBeans) {
        this.orderDetailBeans = orderDetailBeans;
    }

    @Override
    public String toString() {
        return "OrderInStoreBean{" +
                "storeName='" + storeName + '\'' +
                ", orderDetailBeans=" + orderDetailBeans +
                '}';
    }
}
