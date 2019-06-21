package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/4.
 */
public class Attr {
    private String attrStr;
    private Boolean checked;

    public Attr(String attrStr, Boolean checked) {
        this.attrStr = attrStr;
        this.checked = checked;
    }

    public Attr() {
    }

    public String getAttrStr() {
        return attrStr;
    }

    public void setAttrStr(String attrStr) {
        this.attrStr = attrStr;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "Attr{" +
                "attrStr='" + attrStr + '\'' +
                ", checked=" + checked +
                '}';
    }
}
