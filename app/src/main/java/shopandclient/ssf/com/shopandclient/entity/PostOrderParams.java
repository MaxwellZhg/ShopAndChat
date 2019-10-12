package shopandclient.ssf.com.shopandclient.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/10/12
 * Desc:
 */
public class PostOrderParams {

    /**
     * shop : [{"proID":4,"amount":10,"L1":12,"L2":18},{"proID":2,"amount":10,"L1":0,"L2":0}]
     * userid : 1
     * totalPrice : 1.5
     * addressID : 15
     * deliveryType : 1
     * remark : 尽快发货
     */

    private int userid;
    private double totalPrice;
    private int addressID;
    private int deliveryType;
    private String remark;
    private ArrayList<ShopBean> shop;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ArrayList<ShopBean> getShop() {
        return shop;
    }

    public void setShop(ArrayList<ShopBean> shop) {
        this.shop = shop;
    }

    public static class ShopBean {
        /**
         * proID : 4
         * amount : 10
         * L1 : 12
         * L2 : 18
         */

        private int proID;
        private int amount;
        private int L1;
        private int L2;

        public int getProID() {
            return proID;
        }

        public void setProID(int proID) {
            this.proID = proID;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getL1() {
            return L1;
        }

        public void setL1(int L1) {
            this.L1 = L1;
        }

        public int getL2() {
            return L2;
        }

        public void setL2(int L2) {
            this.L2 = L2;
        }
    }
}
