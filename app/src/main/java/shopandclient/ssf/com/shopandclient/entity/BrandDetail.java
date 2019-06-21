package shopandclient.ssf.com.shopandclient.entity;

import android.support.annotation.DrawableRes;

/**
 * Created by zhg on 2019/5/31.
 */
public class BrandDetail {
    private int resid;
    private String brandsname;

    public BrandDetail(@DrawableRes int resid, String brandsname) {
        this.resid = resid;
        this.brandsname = brandsname;
    }

    public BrandDetail() {
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

    @Override
    public String toString() {
        return "BrandDetail{" +
                "resid=" + resid +
                ", brandsname='" + brandsname + '\'' +
                '}';
    }
}
