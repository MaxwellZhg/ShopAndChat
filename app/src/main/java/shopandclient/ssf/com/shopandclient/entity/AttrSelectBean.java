package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/27.
 */
public class AttrSelectBean {

    /**
     * Code : 200
     * Result : 请求成功
     * Data : {"id":0,"attrTypeID":0,"attrTypeValueID":0,"newPrice":45.98,"img":""}
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
         * id : 0
         * attrTypeID : 0
         * attrTypeValueID : 0
         * newPrice : 45.98
         * img :
         */

        private int id;
        private int attrTypeID;
        private int attrTypeValueID;
        private double newPrice;
        private String img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAttrTypeID() {
            return attrTypeID;
        }

        public void setAttrTypeID(int attrTypeID) {
            this.attrTypeID = attrTypeID;
        }

        public int getAttrTypeValueID() {
            return attrTypeValueID;
        }

        public void setAttrTypeValueID(int attrTypeValueID) {
            this.attrTypeValueID = attrTypeValueID;
        }

        public double getNewPrice() {
            return newPrice;
        }

        public void setNewPrice(double newPrice) {
            this.newPrice = newPrice;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
