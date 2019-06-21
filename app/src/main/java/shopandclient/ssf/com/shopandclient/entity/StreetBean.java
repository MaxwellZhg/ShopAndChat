package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/5.
 */
public class StreetBean {
    private String name;
    private int resId;

    public StreetBean() {
    }

    public StreetBean(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "StreetBean{" +
                "name='" + name + '\'' +
                ", resId=" + resId +
                '}';
    }
}
