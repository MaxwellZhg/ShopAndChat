package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

public class ShopCartAttrs {

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
        private int id;
        private int proID;
        private String attrTypeName;
        private ArrayList<ProAttrTypeValueBean> proAttrTypeValue;

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

        public void setProAttrTypeValue(ArrayList<ProAttrTypeValueBean> proAttrTypeValue) {
            this.proAttrTypeValue = proAttrTypeValue;
        }

        public static class ProAttrTypeValueBean {
            private int id;
            private String attrTypeValue;
            private int typeid;

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
        }
    }
}
