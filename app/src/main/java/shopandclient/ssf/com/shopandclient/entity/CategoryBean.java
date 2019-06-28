package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/6/25.
 */
public class CategoryBean {

    /**
     * Code : 200
     * Result : 操作成功
     * Data : [{"id":8,"typename":"新鲜水果","parentid":0,"img":"","typeid":0,"ifShow":0,"sort":0},{"id":9,"typename":"海鲜水产","parentid":0,"img":"","typeid":0,"ifShow":0,"sort":0},{"id":10,"typename":"禽蛋肉类","parentid":0,"img":"","typeid":0,"ifShow":0,"sort":0},{"id":11,"typename":"豆制品","parentid":0,"img":"","typeid":0,"ifShow":0,"sort":0}]
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
         * id : 8
         * typename : 新鲜水果
         * parentid : 0
         * img :
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
    }
}
