package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/4.
 */
public class Attr {

    /**
     * attrL1ID : 1
     * attrL2ID : 3
     */

    private int attrL1ID;
    private int attrL2ID;
    private int attrL3ID;
    private int attrL4ID;
    private int attrL5ID;
    public int getAttrL1ID() {
        return attrL1ID;
    }

    public void setAttrL1ID(int attrL1ID) {
        this.attrL1ID = attrL1ID;
    }

    public int getAttrL2ID() {
        return attrL2ID;
    }

    public void setAttrL2ID(int attrL2ID) {
        this.attrL2ID = attrL2ID;
    }

    public int getAttrL3ID() {
        return attrL3ID;
    }

    public void setAttrL3ID(int attrL3ID) {
        this.attrL3ID = attrL3ID;
    }

    public int getAttrL4ID() {
        return attrL4ID;
    }

    public void setAttrL4ID(int attrL4ID) {
        this.attrL4ID = attrL4ID;
    }

    public int getAttrL5ID() {
        return attrL5ID;
    }

    public void setAttrL5ID(int attrL5ID) {
        this.attrL5ID = attrL5ID;
    }

    @Override
    public String toString() {
        return "Attr{" +
                "attrL1ID=" + attrL1ID +
                ", attrL2ID=" + attrL2ID +
                ", attrL3ID=" + attrL3ID +
                ", attrL4ID=" + attrL4ID +
                ", attrL5ID=" + attrL5ID +
                '}';
    }
}
