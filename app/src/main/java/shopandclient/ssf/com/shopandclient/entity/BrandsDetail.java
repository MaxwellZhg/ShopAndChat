package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/3.
 */
public class BrandsDetail {
    private ArrayList<String> banalist;
    private ArrayList<BrandDetail> mdrawlist;


    public BrandsDetail() {
    }

    public BrandsDetail(ArrayList<String> banalist, ArrayList<BrandDetail> mdrawlist) {
        this.banalist = banalist;
        this.mdrawlist = mdrawlist;
    }

    public ArrayList<String> getBanalist() {
        return banalist;
    }

    public void setBanalist(ArrayList<String> banalist) {
        this.banalist = banalist;
    }

    public ArrayList<BrandDetail> getMdrawlist() {
        return mdrawlist;
    }

    public void setMdrawlist(ArrayList<BrandDetail> mdrawlist) {
        this.mdrawlist = mdrawlist;
    }

    @Override
    public String toString() {
        return "BrandsDetail{" +
                "banalist=" + banalist +
                ", mdrawlist=" + mdrawlist +
                '}';
    }
}
