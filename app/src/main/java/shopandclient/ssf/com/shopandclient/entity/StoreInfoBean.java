package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/6/28.
 */
public class StoreInfoBean {

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
        private int userID;
        private String storeName;
        private String img;
        private String regName;
        private String Contact;
        private String address;
        private int level;
        private String CID;
        private int state;
        private String times;
        private String endTime;
        private int CollectionNum;
        private ProListBean ProList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getRegName() {
            return regName;
        }

        public void setRegName(String regName) {
            this.regName = regName;
        }

        public String getContact() {
            return Contact;
        }

        public void setContact(String Contact) {
            this.Contact = Contact;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getCID() {
            return CID;
        }

        public void setCID(String CID) {
            this.CID = CID;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getCollectionNum() {
            return CollectionNum;
        }

        public void setCollectionNum(int CollectionNum) {
            this.CollectionNum = CollectionNum;
        }

        public ProListBean getProList() {
            return ProList;
        }

        public void setProList(ProListBean ProList) {
            this.ProList = ProList;
        }

        public static class ProListBean {
            private int totalNum;
            private ArrayList<ListBean> list;

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
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
}
