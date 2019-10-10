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
     * Data : {"buyPro":[],"allTotal":0}
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
         * buyPro : []
         * allTotal : 0
         */

        private int allTotal;
        private List<?> buyPro;

        public int getAllTotal() {
            return allTotal;
        }

        public void setAllTotal(int allTotal) {
            this.allTotal = allTotal;
        }

        public List<?> getBuyPro() {
            return buyPro;
        }

        public void setBuyPro(List<?> buyPro) {
            this.buyPro = buyPro;
        }
    }
}
