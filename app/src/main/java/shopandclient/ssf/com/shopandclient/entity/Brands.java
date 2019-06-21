package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/5/30.
 */
public class Brands {
    private String name;
    private int type;

    public Brands() {
    }

    public Brands(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
