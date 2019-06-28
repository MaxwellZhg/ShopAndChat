package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/28.
 */
public class PostComment {

    /**
     * Code : 200
     * Result : 不能频繁操作，30分钟后再评论
     */

    private int Code;
    private String Result;

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
}
