package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

public class LimmitBuyBean {


    /**
     * Code : 200
     * Result : 操作成功
     * Data : {"address":{"id":26,"user_id":37,"userName":"习近平","address":"广东省深圳市南山区","addressInfo":"大冲小学","phone":"15823232323","isDefault":1},"buyPro":[{"storeID":1,"storeName":"跑腿兔旗舰店","userID":1,"listPro":[{"id":13,"img":"http://www.xdgia.com/Upload/20190927125401.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":3,"uPrice":63.6,"discount":0.4,"proID":3,"L1Name":"1kg","L2Name":"18-22mm","NewPrice":159,"L1":19,"L2":21},{"id":14,"img":"http://www.xdgia.com/Upload/20190927125401.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":1,"uPrice":123.2,"discount":0.4,"proID":3,"L1Name":"2kg","L2Name":"18-22mm","NewPrice":308,"L1":20,"L2":21},{"id":15,"img":"http://www.xdgia.com/Upload/20191009050450.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":1,"uPrice":99,"discount":0.5,"proID":2,"NewPrice":0,"L1":0,"L2":0},{"id":21,"img":"http://www.xdgia.com/Upload/20191009050218.jpg","proName":"橘子（10斤装）","amount":1,"uPrice":29.8,"discount":1,"proID":1,"NewPrice":0,"L1":0,"L2":0},{"id":22,"img":"http://www.xdgia.com/Upload/20191009050218.jpg","proName":"橘子（10斤装）","amount":2,"uPrice":3,"discount":1,"proID":1,"L1Name":"新鲜采摘（带箱子3-4斤）","NewPrice":3,"L1":2,"L2":0}]},{"storeID":2,"storeName":"王小二水果旗舰店","userID":14,"listPro":[{"id":23,"img":"http://www.xdgia.com/Upload/20190927091515.png","proName":"这是一个橙子","amount":1,"uPrice":4,"discount":0.1,"proID":4,"L1Name":"赣橙3KG实惠","L2Name":"实惠5-8cm","NewPrice":40,"L1":13,"L2":17}]}],"allTotal":452.8}
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
         * buyPro : [{"storeID":1,"storeName":"跑腿兔旗舰店","userID":1,"listPro":[{"id":13,"img":"http://www.xdgia.com/Upload/20190927125401.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":3,"uPrice":63.6,"discount":0.4,"proID":3,"L1Name":"1kg","L2Name":"18-22mm","NewPrice":159,"L1":19,"L2":21},{"id":14,"img":"http://www.xdgia.com/Upload/20190927125401.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":1,"uPrice":123.2,"discount":0.4,"proID":3,"L1Name":"2kg","L2Name":"18-22mm","NewPrice":308,"L1":20,"L2":21},{"id":15,"img":"http://www.xdgia.com/Upload/20191009050450.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":1,"uPrice":99,"discount":0.5,"proID":2,"NewPrice":0,"L1":0,"L2":0},{"id":21,"img":"http://www.xdgia.com/Upload/20191009050218.jpg","proName":"橘子（10斤装）","amount":1,"uPrice":29.8,"discount":1,"proID":1,"NewPrice":0,"L1":0,"L2":0},{"id":22,"img":"http://www.xdgia.com/Upload/20191009050218.jpg","proName":"橘子（10斤装）","amount":2,"uPrice":3,"discount":1,"proID":1,"L1Name":"新鲜采摘（带箱子3-4斤）","NewPrice":3,"L1":2,"L2":0}]},{"storeID":2,"storeName":"王小二水果旗舰店","userID":14,"listPro":[{"id":23,"img":"http://www.xdgia.com/Upload/20190927091515.png","proName":"这是一个橙子","amount":1,"uPrice":4,"discount":0.1,"proID":4,"L1Name":"赣橙3KG实惠","L2Name":"实惠5-8cm","NewPrice":40,"L1":13,"L2":17}]}]
         * allTotal : 452.8
         */

        private AddressBean address;
        private double allTotal;
        private ArrayList<BuyProBean> buyPro;

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

        public ArrayList<BuyProBean> getBuyPro() {
            return buyPro;
        }

        public void setBuyPro(ArrayList<BuyProBean> buyPro) {
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
             * listPro : [{"id":13,"img":"http://www.xdgia.com/Upload/20190927125401.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":3,"uPrice":63.6,"discount":0.4,"proID":3,"L1Name":"1kg","L2Name":"18-22mm","NewPrice":159,"L1":19,"L2":21},{"id":14,"img":"http://www.xdgia.com/Upload/20190927125401.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":1,"uPrice":123.2,"discount":0.4,"proID":3,"L1Name":"2kg","L2Name":"18-22mm","NewPrice":308,"L1":20,"L2":21},{"id":15,"img":"http://www.xdgia.com/Upload/20191009050450.jpg","proName":"美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发","amount":1,"uPrice":99,"discount":0.5,"proID":2,"NewPrice":0,"L1":0,"L2":0},{"id":21,"img":"http://www.xdgia.com/Upload/20191009050218.jpg","proName":"橘子（10斤装）","amount":1,"uPrice":29.8,"discount":1,"proID":1,"NewPrice":0,"L1":0,"L2":0},{"id":22,"img":"http://www.xdgia.com/Upload/20191009050218.jpg","proName":"橘子（10斤装）","amount":2,"uPrice":3,"discount":1,"proID":1,"L1Name":"新鲜采摘（带箱子3-4斤）","NewPrice":3,"L1":2,"L2":0}]
             */

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
                /**
                 * id : 13
                 * img : http://www.xdgia.com/Upload/20190927125401.jpg
                 * proName : 美国进口车厘子带箱3斤大樱桃 新鲜当季水果应季批发
                 * amount : 3
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
