package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/11.
 */
public class OrderDetailBean {
    private int resId;
    private String price;

    public OrderDetailBean() {
    }

    public OrderDetailBean(int resId, String price) {
        this.resId = resId;
        this.price = price;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetailBean{" +
                "resId=" + resId +
                ", price='" + price + '\'' +
                '}';
    }
}
