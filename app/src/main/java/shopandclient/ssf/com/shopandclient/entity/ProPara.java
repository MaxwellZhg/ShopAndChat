package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/9/17
 * Desc:
 */
public class ProPara {

    /**
     * sType : 1
     * sContent :
     * page : 1
     */

    private int sType;
    private String sContent;
    private int page;

    public ProPara(int sType, String sContent, int page) {
        this.sType = sType;
        this.sContent = sContent;
        this.page = page;
    }

    public int getSType() {
        return sType;
    }

    public void setSType(int sType) {
        this.sType = sType;
    }

    public String getSContent() {
        return sContent;
    }

    public void setSContent(String sContent) {
        this.sContent = sContent;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
