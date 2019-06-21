package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class MyAllOrderBean {
    private String orderNum;
    private ArrayList<OrderInStoreBean> list;

    public MyAllOrderBean() {
    }

    public MyAllOrderBean(String orderNum, ArrayList<OrderInStoreBean> list) {
        this.orderNum = orderNum;
        this.list = list;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public ArrayList<OrderInStoreBean> getList() {
        return list;
    }

    public void setList(ArrayList<OrderInStoreBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MyAllOrderBean{" +
                "orderNum='" + orderNum + '\'' +
                ", list=" + list +
                '}';
    }
}
