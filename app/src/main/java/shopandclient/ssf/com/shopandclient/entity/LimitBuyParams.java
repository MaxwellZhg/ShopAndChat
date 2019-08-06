package shopandclient.ssf.com.shopandclient.entity;

public class LimitBuyParams {

    private int proID;
    private int amount;
    private int L1;
    private int L2;

    public int getProID() {
        return proID;
    }

    public void setProID(int proID) {
        this.proID = proID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public LimitBuyParams(int proID, int amount, int l1, int l2) {
        this.proID = proID;
        this.amount = amount;
        L1 = l1;
        L2 = l2;
    }
}
