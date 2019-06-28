package shopandclient.ssf.com.shopandclient.entity;


import shopandclient.ssf.com.shopandclient.util.SpConfig;

/**
 * Created by zhg on 2019/6/24.
 */
public class User {
    private String accuntNo;
    private String pwd;
    private int sPlat;

    public User(String accuntNo, String pwd, int sPlat) {
        this.accuntNo = accuntNo;
        this.pwd = pwd;
        this.sPlat = sPlat;
        setpsw(pwd);
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

    public int getsPlat() {
        return sPlat;
    }

    public void setsPlat(int sPlat) {
        this.sPlat = sPlat;
    }
    public void setpsw(String psw){
        SpConfig.getInstance().putString("mdpsw",psw);
    }
}
