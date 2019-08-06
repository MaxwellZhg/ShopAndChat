package shopandclient.ssf.com.shopandclient.event;

public class CartAttrEvent {
    private int id;
    private int proId;
    private int count;

    public CartAttrEvent(int id, int proId, int count) {
        this.id = id;
        this.proId = proId;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CartAttrEvent{" +
                "id=" + id +
                ", proId=" + proId +
                ", count=" + count +
                '}';
    }
}
