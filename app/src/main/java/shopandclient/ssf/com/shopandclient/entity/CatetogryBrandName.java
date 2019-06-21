package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/5/31.
 */
public class CatetogryBrandName {
    private int resid;
    private String brandsname;
    private String tips;

    public CatetogryBrandName(int resid, String brandsname, String tips) {
        this.resid = resid;
        this.brandsname = brandsname;
        this.tips = tips;
    }

    public CatetogryBrandName() {
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public String getBrandsname() {
        return brandsname;
    }

    public void setBrandsname(String brandsname) {
        this.brandsname = brandsname;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public String toString() {
        return "CatetogryBrandName{" +
                "resid=" + resid +
                ", brandsname='" + brandsname + '\'' +
                ", tips='" + tips + '\'' +
                '}';
    }
}
