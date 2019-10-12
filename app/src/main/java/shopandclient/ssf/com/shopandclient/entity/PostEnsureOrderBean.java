package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/10/12
 * Desc:
 */
public class PostEnsureOrderBean {

    /**
     * Code : 200
     * Result : 操作成功
     * Data : ["191012173851385143","191012173852385212"]
     */

    private int Code;
    private String Result;
    private ArrayList<String> Data;

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

    public ArrayList<String> getData() {
        return Data;
    }

    public void setData(ArrayList<String> Data) {
        this.Data = Data;
    }
}
