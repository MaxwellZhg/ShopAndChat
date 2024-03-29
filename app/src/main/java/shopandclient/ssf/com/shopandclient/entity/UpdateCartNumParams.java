package shopandclient.ssf.com.shopandclient.entity;

public class UpdateCartNumParams {

    private int id;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public UpdateCartNumParams(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}
