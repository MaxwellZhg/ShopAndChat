package shopandclient.ssf.com.shopandclient.entity;

import shopandclient.ssf.com.shopandclient.util.SpConfig;

/**
 * Created by zhg on 2019/6/24.
 */
public class AddUser {

    /**
     * phone :
     * pwd :
     * Code :
     */


    private String phone;
    private String pwd;
    private String Code;

    public AddUser(String phone, String pwd, String code) {
        this.phone = phone;
        this.pwd = pwd;
        Code = code;
        setPsw(pwd);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
    public void setPsw(String psw){
        SpConfig.getInstance().putString("mdpsw",psw);
    }
}
