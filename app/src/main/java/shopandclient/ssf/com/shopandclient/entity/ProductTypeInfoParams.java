package shopandclient.ssf.com.shopandclient.entity;

public class ProductTypeInfoParams {

    private int sType;
    private String sContent;
    private int page;

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

    public ProductTypeInfoParams(int sType, String sContent, int page) {
        this.sType = sType;
        this.sContent = sContent;
        this.page = page;
    }

    @Override
    public String toString() {
        return "ProductTypeInfoParams{" +
                "sType=" + sType +
                ", sContent='" + sContent + '\'' +
                ", page=" + page +
                '}';
    }
}
