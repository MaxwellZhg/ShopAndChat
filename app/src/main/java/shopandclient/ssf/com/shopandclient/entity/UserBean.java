package shopandclient.ssf.com.shopandclient.entity;

/**
 * Created by zhg on 2019/6/24.
 */
public class UserBean {


    /**
     * accuntNo : 18673837602
     * pwd : e10adc3949ba59abbe56e057f20f883e
     * sPlat : 1
     */

    private String accuntNo;
    private String pwd;
    private int sPlat;


    public UserBean(String accuntNo, String pwd, int sPlat) {
        this.accuntNo = accuntNo;
        this.pwd = pwd;
        this.sPlat = sPlat;
    }

    public String getAccuntNo() {
        return accuntNo;
    }

    public void setAccuntNo(String accuntNo) {
        this.accuntNo = accuntNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getSPlat() {
        return sPlat;
    }

    public void setSPlat(int sPlat) {
        this.sPlat = sPlat;
    }
}
