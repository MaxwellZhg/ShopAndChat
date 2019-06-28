package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/6/24.
 */
public class Prosirers {

    /**
     * Code : 200
     * Result : 操作成功
     * Data : [{"id":1,"typename":"生鲜","parentid":0,"img":"/img/img1.jpg","typeid":0,"ifShow":0,"sort":0},{"id":2,"typename":"食品","parentid":0,"img":"","typeid":0,"ifShow":0,"sort":0},{"id":3,"typename":"百货","parentid":0,"img":"","typeid":0,"ifShow":0,"sort":0},{"id":4,"typename":"鲜花","parentid":0,"img":"","typeid":0,"ifShow":0,"sort":0},{"id":5,"typename":"家电","parentid":0,"img":"","typeid":0,"ifShow":0,"sort":0},{"id":6,"typename":"其他","parentid":0,"img":"","typeid":0,"ifShow":0,"sort":0}]
     */

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
        /**
         * id : 1
         * typename : 生鲜
         * parentid : 0
         * img : /img/img1.jpg
         * typeid : 0
         * ifShow : 0
         * sort : 0
         */

        private int id;
        private String typename;
        private int parentid;
        private String img;
        private int typeid;
        private int ifShow;
        private int sort;
        private boolean isSelect=false;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getIfShow() {
            return ifShow;
        }

        public void setIfShow(int ifShow) {
            this.ifShow = ifShow;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", typename='" + typename + '\'' +
                    ", parentid=" + parentid +
                    ", img='" + img + '\'' +
                    ", typeid=" + typeid +
                    ", ifShow=" + ifShow +
                    ", sort=" + sort +
                    ", isSelect=" + isSelect +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Prosirers{" +
                "Code=" + Code +
                ", Result='" + Result + '\'' +
                ", Data=" + Data +
                '}';
    }
}
