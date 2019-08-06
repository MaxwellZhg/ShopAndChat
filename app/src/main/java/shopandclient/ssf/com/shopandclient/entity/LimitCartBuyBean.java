package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

public class LimitCartBuyBean {

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
        private double allTotal;
        private ArrayList<BuyProBean> buyPro;

        public double getAllTotal() {
            return allTotal;
        }

        public void setAllTotal(double allTotal) {
            this.allTotal = allTotal;
        }

        public ArrayList<BuyProBean> getBuyPro() {
            return buyPro;
        }

        public void setBuyPro(ArrayList<BuyProBean> buyPro) {
            this.buyPro = buyPro;
        }

        public static class BuyProBean {
            private int storeID;
            private String storeName;
            private int userID;
            private ArrayList<ListProBean> listPro;

            public int getStoreID() {
                return storeID;
            }

            public void setStoreID(int storeID) {
                this.storeID = storeID;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public int getUserID() {
                return userID;
            }

            public void setUserID(int userID) {
                this.userID = userID;
            }

            public ArrayList<ListProBean> getListPro() {
                return listPro;
            }

            public void setListPro(ArrayList<ListProBean> listPro) {
                this.listPro = listPro;
            }

            public static class ListProBean {
                private int id;
                private String img;
                private String proName;
                private int amount;
                private double uPrice;
                private int discount;
                private int proID;
                private int NewPrice;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getProName() {
                    return proName;
                }

                public void setProName(String proName) {
                    this.proName = proName;
                }

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public double getUPrice() {
                    return uPrice;
                }

                public void setUPrice(double uPrice) {
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
    }
}
