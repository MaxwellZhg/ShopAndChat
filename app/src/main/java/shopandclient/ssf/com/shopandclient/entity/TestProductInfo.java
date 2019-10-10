package shopandclient.ssf.com.shopandclient.entity;

import java.util.List;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/10/10
 * Desc:
 */
public class TestProductInfo {

    /**
     * Code : 200
     * Result : 操作成功
     * Data : {"id":1,"barcode":"B001","proName":"橘子（10斤装）","seriesID":1,"cateGoryID":8,"price":29.8,"discount":1,"amount":100,"storeID":1,"proType":1,"ifXJ":0,"courierFees":0,"paraInfo":"阿道夫","remark":"&nbsp;啊发发","content":"/Upload/1/20191009170248_3754.jpg|/Upload/1/20191009170248_4662.jpg|/Upload/1/20191009170248_5268.jpg|/Upload/1/20191009170248_5834.jpg|","giveLikeNum":1,"Num":0,"ifCollection":false,"proImg":[{"id":0,"pro_id":0,"fileImg":"http://www.xdgia.com/Upload/20191009050228.jpg","typeID":0,"commentID":0},{"id":0,"pro_id":0,"fileImg":"http://www.xdgia.com/Upload/20191009050231.jpg","typeID":0,"commentID":0},{"id":0,"pro_id":0,"fileImg":"http://www.xdgia.com/Upload/20191009050235.jpg","typeID":0,"commentID":0}],"proAttrType":[{"id":1,"proID":0,"attrTypeName":"容量","proAttrTypeValue":[{"id":1,"attrTypeValue":"新鲜采摘（带箱子2-3斤）","typeid":0},{"id":2,"attrTypeValue":"新鲜采摘（带箱子3-4斤）","typeid":0},{"id":3,"attrTypeValue":"新鲜采摘（带箱子4-5斤）","typeid":0},{"id":4,"attrTypeValue":"新鲜采摘买五送五（带箱子9-10斤）","typeid":0}]}]}
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
         * id : 1
         * barcode : B001
         * proName : 橘子（10斤装）
         * seriesID : 1
         * cateGoryID : 8
         * price : 29.8
         * discount : 1.0
         * amount : 100
         * storeID : 1
         * proType : 1
         * ifXJ : 0
         * courierFees : 0.0
         * paraInfo : 阿道夫
         * remark : &nbsp;啊发发
         * content : /Upload/1/20191009170248_3754.jpg|/Upload/1/20191009170248_4662.jpg|/Upload/1/20191009170248_5268.jpg|/Upload/1/20191009170248_5834.jpg|
         * giveLikeNum : 1
         * Num : 0
         * ifCollection : false
         * proImg : [{"id":0,"pro_id":0,"fileImg":"http://www.xdgia.com/Upload/20191009050228.jpg","typeID":0,"commentID":0},{"id":0,"pro_id":0,"fileImg":"http://www.xdgia.com/Upload/20191009050231.jpg","typeID":0,"commentID":0},{"id":0,"pro_id":0,"fileImg":"http://www.xdgia.com/Upload/20191009050235.jpg","typeID":0,"commentID":0}]
         * proAttrType : [{"id":1,"proID":0,"attrTypeName":"容量","proAttrTypeValue":[{"id":1,"attrTypeValue":"新鲜采摘（带箱子2-3斤）","typeid":0},{"id":2,"attrTypeValue":"新鲜采摘（带箱子3-4斤）","typeid":0},{"id":3,"attrTypeValue":"新鲜采摘（带箱子4-5斤）","typeid":0},{"id":4,"attrTypeValue":"新鲜采摘买五送五（带箱子9-10斤）","typeid":0}]}]
         */

        private int id;
        private String barcode;
        private String proName;
        private int seriesID;
        private int cateGoryID;
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
        private List<ProImgBean> proImg;
        private List<ProAttrTypeBean> proAttrType;

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

        public List<ProImgBean> getProImg() {
            return proImg;
        }

        public void setProImg(List<ProImgBean> proImg) {
            this.proImg = proImg;
        }

        public List<ProAttrTypeBean> getProAttrType() {
            return proAttrType;
        }

        public void setProAttrType(List<ProAttrTypeBean> proAttrType) {
            this.proAttrType = proAttrType;
        }

        public static class ProImgBean {
            /**
             * id : 0
             * pro_id : 0
             * fileImg : http://www.xdgia.com/Upload/20191009050228.jpg
             * typeID : 0
             * commentID : 0
             */

            private int id;
            private int pro_id;
            private String fileImg;
            private int typeID;
            private int commentID;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPro_id() {
                return pro_id;
            }

            public void setPro_id(int pro_id) {
                this.pro_id = pro_id;
            }

            public String getFileImg() {
                return fileImg;
            }

            public void setFileImg(String fileImg) {
                this.fileImg = fileImg;
            }

            public int getTypeID() {
                return typeID;
            }

            public void setTypeID(int typeID) {
                this.typeID = typeID;
            }

            public int getCommentID() {
                return commentID;
            }

            public void setCommentID(int commentID) {
                this.commentID = commentID;
            }
        }

        public static class ProAttrTypeBean {
            /**
             * id : 1
             * proID : 0
             * attrTypeName : 容量
             * proAttrTypeValue : [{"id":1,"attrTypeValue":"新鲜采摘（带箱子2-3斤）","typeid":0},{"id":2,"attrTypeValue":"新鲜采摘（带箱子3-4斤）","typeid":0},{"id":3,"attrTypeValue":"新鲜采摘（带箱子4-5斤）","typeid":0},{"id":4,"attrTypeValue":"新鲜采摘买五送五（带箱子9-10斤）","typeid":0}]
             */

            private int id;
            private int proID;
            private String attrTypeName;
            private List<ProAttrTypeValueBean> proAttrTypeValue;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getProID() {
                return proID;
            }

            public void setProID(int proID) {
                this.proID = proID;
            }

            public String getAttrTypeName() {
                return attrTypeName;
            }

            public void setAttrTypeName(String attrTypeName) {
                this.attrTypeName = attrTypeName;
            }

            public List<ProAttrTypeValueBean> getProAttrTypeValue() {
                return proAttrTypeValue;
            }

            public void setProAttrTypeValue(List<ProAttrTypeValueBean> proAttrTypeValue) {
                this.proAttrTypeValue = proAttrTypeValue;
            }

            public static class ProAttrTypeValueBean {
                /**
                 * id : 1
                 * attrTypeValue : 意大利品种
                 * typeid : 0
                 */

                private int pos;
                private int id;
                private String attrTypeValue;
                private int typeid;
                private boolean isSelect=false;

                public boolean isSelect() {
                    return isSelect;
                }

                public void setSelect(boolean select) {
                    isSelect = select;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAttrTypeValue() {
                    return attrTypeValue;
                }

                public void setAttrTypeValue(String attrTypeValue) {
                    this.attrTypeValue = attrTypeValue;
                }

                public int getTypeid() {
                    return typeid;
                }

                public void setTypeid(int typeid) {
                    this.typeid = typeid;
                }

                public int getPos() {
                    return pos;
                }

                public void setPos(int pos) {
                    this.pos = pos;
                }
            }
        }
    }
}
