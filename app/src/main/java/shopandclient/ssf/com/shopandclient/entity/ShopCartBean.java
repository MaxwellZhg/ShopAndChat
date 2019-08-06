package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

public class ShopCartBean {

    private int Code;
    private String Result;
    private ArrayList<DataBean> Data;

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

    public ArrayList<DataBean> getData() {
        return Data;
    }

    public void setData(ArrayList<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
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
            private double discount;
            private int proID;
            private String L1Name;
            private String L2Name;
            private double NewPrice;
            private boolean isChoose=false;

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

            public double getDiscount() {
                return discount;
            }

            public void setDiscount(double discount) {
                this.discount = discount;
            }

            public int getProID() {
                return proID;
            }

            public void setProID(int proID) {
                this.proID = proID;
            }

            public String getL1Name() {
                return L1Name;
            }

            public void setL1Name(String L1Name) {
                this.L1Name = L1Name;
            }

            public String getL2Name() {
                return L2Name;
            }

            public void setL2Name(String L2Name) {
                this.L2Name = L2Name;
            }

            public double getNewPrice() {
                return NewPrice;
            }

            public void setNewPrice(double NewPrice) {
                this.NewPrice = NewPrice;
            }

            public boolean isChoose() {
                return isChoose;
            }

            public void setChoose(boolean choose) {
                isChoose = choose;
            }

            public double getuPrice() {
                return uPrice;
            }

            public void setuPrice(double uPrice) {
                this.uPrice = uPrice;
            }

            @Override
            public String toString() {
                return "ListProBean{" +
                        "id=" + id +
                        ", img='" + img + '\'' +
                        ", proName='" + proName + '\'' +
                        ", amount=" + amount +
                        ", uPrice=" + uPrice +
                        ", discount=" + discount +
                        ", proID=" + proID +
                        ", L1Name='" + L1Name + '\'' +
                        ", L2Name='" + L2Name + '\'' +
                        ", NewPrice=" + NewPrice +
                        ", isChoose=" + isChoose +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "storeID=" + storeID +
                    ", storeName='" + storeName + '\'' +
                    ", userID=" + userID +
                    ", listPro=" + listPro +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShopCartBean{" +
                "Code=" + Code +
                ", Result='" + Result + '\'' +
                ", Data=" + Data +
                '}';
    }
}
