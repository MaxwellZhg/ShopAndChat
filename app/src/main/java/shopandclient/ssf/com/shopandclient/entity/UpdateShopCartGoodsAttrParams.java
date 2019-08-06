package shopandclient.ssf.com.shopandclient.entity;

public class UpdateShopCartGoodsAttrParams {

    private int id;
    private int L1;
    private int L2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getL1() {
        return L1;
    }

    public void setL1(int L1) {
        this.L1 = L1;
    }

    public int getL2() {
        return L2;
    }

    public void setL2(int L2) {
        this.L2 = L2;
    }

    public UpdateShopCartGoodsAttrParams(int id, int l1, int l2) {
        this.id = id;
        L1 = l1;
        L2 = l2;
    }
}
