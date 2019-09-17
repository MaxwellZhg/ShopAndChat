package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/24.
 */
public class AddUserResult {
    /**
     * Code : 200
     * Result : 注册成功
     * Data : {"Phone":"15111111111","Username":"U428277","GuidNo":"B5579FBA-E0AA-4EEA-9570-A95A870B7064","userid":27}
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

    @Override
    public String toString() {
        return "AddUserResult{" +
                "Code=" + Code +
                ", Result='" + Result + '\'' +
                ", Data=" + Data +
                '}';
    }

    public static class DataBean {
        /**
         * Phone : 15111111111
         * Username : U428277
         * GuidNo : B5579FBA-E0AA-4EEA-9570-A95A870B7064
         * userid : 27
         */

        private String Phone;
        private String Username;
        private String GuidNo;
        private int userid;

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getUsername() {
            return Username;
        }

        public void setUsername(String Username) {
            this.Username = Username;
        }

        public String getGuidNo() {
            return GuidNo;
        }

        public void setGuidNo(String GuidNo) {
            this.GuidNo = GuidNo;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "Phone='" + Phone + '\'' +
                    ", Username='" + Username + '\'' +
                    ", GuidNo='" + GuidNo + '\'' +
                    ", userid=" + userid +
                    '}';
        }
    }


}
