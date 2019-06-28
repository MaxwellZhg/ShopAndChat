package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/24.
 */
public class SendCodeBean {

    /**
     * Code : 200
     * Result : 发送成功
     * Data : {"id":0,"Code":"601086","phone":"18673837602","regTime":"2019-06-24 11:36:14"}
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
         * Code : 601086
         * phone : 18673837602
         * regTime : 2019-06-24 11:36:14
         */

        private int id;
        private String Code;
        private String phone;
        private String regTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRegTime() {
            return regTime;
        }

        public void setRegTime(String regTime) {
            this.regTime = regTime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", Code='" + Code + '\'' +
                    ", phone='" + phone + '\'' +
                    ", regTime='" + regTime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SendCodeBean{" +
                "Code=" + Code +
                ", Result='" + Result + '\'' +
                ", Data=" + Data +
                '}';
    }
}
