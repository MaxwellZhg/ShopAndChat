package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/5/28.
 */
public class BaseBean {

    private MetaBean meta;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public class MetaBean {
        /**
         * code : 0
         * message : null
         */

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
