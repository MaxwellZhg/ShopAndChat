package shopandclient.ssf.com.shopandclient.entity;

public class UpdateShopGoodsAttrInfo {

    private int Code;
    private String Result;
    private DataBean Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private int id;
        private int amount;
        private int uPrice;
        private int discount;
        private int proID;
        private int NewPrice;

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

        public int getUPrice() {
            return uPrice;
        }

        public void setUPrice(int uPrice) {
            this.uPrice = uPrice;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public int getProID() {
            return proID;
        }

        public void setProID(int proID) {
            this.proID = proID;
        }

        public int getNewPrice() {
            return NewPrice;
        }

        public void setNewPrice(int NewPrice) {
            this.NewPrice = NewPrice;
        }
    }
}
