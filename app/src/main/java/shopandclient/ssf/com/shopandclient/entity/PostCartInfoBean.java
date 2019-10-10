package shopandclient.ssf.com.shopandclient.entity;

import java.util.List;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/10/10
 * Desc:
 */
public class PostCartInfoBean {


    /**
     * Code : 200
     * Result : 操作成功
     * Data : {"address":{"id":26,"user_id":37,"userName":"习近平","address":"广东省深圳市南山区","addressInfo":"大冲小学","phone":"15823232323","isDefault":1},"buyPro":[{"storeID":1,"storeName":"跑腿兔旗舰店","userID":1,"listPro":[{"id":13,"img":"http://www.xdgia.com/Upload/20190927125401.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":2,"uPrice":63.6,"discount":0.4,"proID":3,"L1Name":"1kg","L2Name":"18-22mm","NewPrice":159,"L1":19,"L2":21}]}],"allTotal":127.2}
     */

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
        /**
         * address : {"id":26,"user_id":37,"userName":"习近平","address":"广东省深圳市南山区","addressInfo":"大冲小学","phone":"15823232323","isDefault":1}
         * buyPro : [{"storeID":1,"storeName":"跑腿兔旗舰店","userID":1,"listPro":[{"id":13,"img":"http://www.xdgia.com/Upload/20190927125401.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":2,"uPrice":63.6,"discount":0.4,"proID":3,"L1Name":"1kg","L2Name":"18-22mm","NewPrice":159,"L1":19,"L2":21}]}]
         * allTotal : 127.2
         */

        private AddressBean address;
        private double allTotal;
        private List<BuyProBean> buyPro;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public double getAllTotal() {
            return allTotal;
        }

        public void setAllTotal(double allTotal) {
            this.allTotal = allTotal;
        }

        public List<BuyProBean> getBuyPro() {
            return buyPro;
        }

        public void setBuyPro(List<BuyProBean> buyPro) {
            this.buyPro = buyPro;
        }

        public static class AddressBean {
            /**
             * id : 26
             * user_id : 37
             * userName : 习近平
             * address : 广东省深圳市南山区
             * addressInfo : 大冲小学
             * phone : 15823232323
             * isDefault : 1
             */

            private int id;
            private int user_id;
            private String userName;
            private String address;
            private String addressInfo;
            private String phone;
            private int isDefault;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAddressInfo() {
                return addressInfo;
            }

            public void setAddressInfo(String addressInfo) {
                this.addressInfo = addressInfo;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(int isDefault) {
                this.isDefault = isDefault;
            }
        }

        public static class BuyProBean {
            /**
             * storeID : 1
             * storeName : 跑腿兔旗舰店
             * userID : 1
             * listPro : [{"id":13,"img":"http://www.xdgia.com/Upload/20190927125401.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":2,"uPrice":63.6,"discount":0.4,"proID":3,"L1Name":"1kg","L2Name":"18-22mm","NewPrice":159,"L1":19,"L2":21}]
             */

            private int storeID;
            private String storeName;
            private int userID;
            private List<ListProBean> listPro;

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

            public List<ListProBean> getListPro() {
                return listPro;
            }

            public void setListPro(List<ListProBean> listPro) {
                this.listPro = listPro;
            }

            public static class ListProBean {
                /**
                 * id : 13
                 * img : http://www.xdgia.com/Upload/20190927125401.jpg
                 * proName : 美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发
                 * amount : 2
                 * uPrice : 63.6
                 * discount : 0.4
                 * proID : 3
                 * L1Name : 1kg
                 * L2Name : 18-22mm
                 * NewPrice : 159.0
                 * L1 : 19
                 * L2 : 21
                 */

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
                private int L1;
                private int L2;

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
            }
        }
    }
}
