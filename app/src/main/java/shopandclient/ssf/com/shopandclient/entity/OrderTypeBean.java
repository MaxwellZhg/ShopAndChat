package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderTypeBean {

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
            private String orderNo;
            private String tranNo;
            private int userID;
            private int addID;
            private double TotalPrice;
            private int storeID;
            private String storeName;
            private int storeUserID;
            private int orderState;
            private String shipmentName;
            private String shipmentNo;
            private String shipmentTime;
            private String times;
            private int payWay;
            private String remark;
            private double courierFees;
            private ArrayList<OrderInfoBean> OrderInfo;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getTranNo() {
                return tranNo;
            }

            public void setTranNo(String tranNo) {
                this.tranNo = tranNo;
            }

            public int getUserID() {
                return userID;
            }

            public void setUserID(int userID) {
                this.userID = userID;
            }

            public int getAddID() {
                return addID;
            }

            public void setAddID(int addID) {
                this.addID = addID;
            }

            public double getTotalPrice() {
                return TotalPrice;
            }

            public void setTotalPrice(double TotalPrice) {
                this.TotalPrice = TotalPrice;
            }

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

            public int getStoreUserID() {
                return storeUserID;
            }

            public void setStoreUserID(int storeUserID) {
                this.storeUserID = storeUserID;
            }

            public int getOrderState() {
                return orderState;
            }

            public void setOrderState(int orderState) {
                this.orderState = orderState;
            }

            public String getShipmentName() {
                return shipmentName;
            }

            public void setShipmentName(String shipmentName) {
                this.shipmentName = shipmentName;
            }

            public String getShipmentNo() {
                return shipmentNo;
            }

            public void setShipmentNo(String shipmentNo) {
                this.shipmentNo = shipmentNo;
            }

            public String getShipmentTime() {
                return shipmentTime;
            }

            public void setShipmentTime(String shipmentTime) {
                this.shipmentTime = shipmentTime;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public int getPayWay() {
                return payWay;
            }

            public void setPayWay(int payWay) {
                this.payWay = payWay;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public double getCourierFees() {
                return courierFees;
            }

            public void setCourierFees(double courierFees) {
                this.courierFees = courierFees;
            }

            public ArrayList<OrderInfoBean> getOrderInfo() {
                return OrderInfo;
            }

            public void setOrderInfo(ArrayList<OrderInfoBean> OrderInfo) {
                this.OrderInfo = OrderInfo;
            }

            public static class OrderInfoBean {
                private int id;
                private String orderNo;
                private int proID;
                private String proName;
                private String proNo;
                private String proDetail;
                private String proImg;
                private int amount;
                private double Uprice;
                private String times;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getOrderNo() {
                    return orderNo;
                }

                public void setOrderNo(String orderNo) {
                    this.orderNo = orderNo;
                }

                public int getProID() {
                    return proID;
                }

                public void setProID(int proID) {
                    this.proID = proID;
                }

                public String getProName() {
                    return proName;
                }

                public void setProName(String proName) {
                    this.proName = proName;
                }

                public String getProNo() {
                    return proNo;
                }

                public void setProNo(String proNo) {
                    this.proNo = proNo;
                }

                public String getProDetail() {
                    return proDetail;
                }

                public void setProDetail(String proDetail) {
                    this.proDetail = proDetail;
                }

                public String getProImg() {
                    return proImg;
                }

                public void setProImg(String proImg) {
                    this.proImg = proImg;
                }

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public double getUprice() {
                    return Uprice;
                }

                public void setUprice(double Uprice) {
                    this.Uprice = Uprice;
                }

                public String getTimes() {
                    return times;
                }

                public void setTimes(String times) {
                    this.times = times;
                }
            }
        }
    }
}
