package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;

public class StreetInfoBean {

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
        private int totalNum;
        private String title;
        private ArrayList<ListBean> list;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<ListBean> getList() {
            return list;
        }

        public void setList(ArrayList<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String barcode;
            private String proName;
            private int seriesID;
            private int cateGoryID;
            private String Img;
            private double price;
            private double discount;
            private int amount;
            private int storeID;
            private int proType;
            private int ifXJ;
            private double courierFees;
            private String paraInfo;
            private String remark;
            private String content;
            private int giveLikeNum;
            private int Num;
            private boolean ifCollection;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBarcode() {
                return barcode;
            }

            public void setBarcode(String barcode) {
                this.barcode = barcode;
            }

            public String getProName() {
                return proName;
            }

            public void setProName(String proName) {
                this.proName = proName;
            }

            public int getSeriesID() {
                return seriesID;
            }

            public void setSeriesID(int seriesID) {
                this.seriesID = seriesID;
            }

            public int getCateGoryID() {
                return cateGoryID;
            }

            public void setCateGoryID(int cateGoryID) {
                this.cateGoryID = cateGoryID;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getDiscount() {
                return discount;
            }

            public void setDiscount(double discount) {
                this.discount = discount;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getStoreID() {
                return storeID;
            }

            public void setStoreID(int storeID) {
                this.storeID = storeID;
            }

            public int getProType() {
                return proType;
            }

            public void setProType(int proType) {
                this.proType = proType;
            }

            public int getIfXJ() {
                return ifXJ;
            }

            public void setIfXJ(int ifXJ) {
                this.ifXJ = ifXJ;
            }

            public double getCourierFees() {
                return courierFees;
            }

            public void setCourierFees(double courierFees) {
                this.courierFees = courierFees;
            }

            public String getParaInfo() {
                return paraInfo;
            }

            public void setParaInfo(String paraInfo) {
                this.paraInfo = paraInfo;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getGiveLikeNum() {
                return giveLikeNum;
            }

            public void setGiveLikeNum(int giveLikeNum) {
                this.giveLikeNum = giveLikeNum;
            }

            public int getNum() {
                return Num;
            }

            public void setNum(int Num) {
                this.Num = Num;
            }

            public boolean isIfCollection() {
                return ifCollection;
            }

            public void setIfCollection(boolean ifCollection) {
                this.ifCollection = ifCollection;
            }
        }
    }
}
