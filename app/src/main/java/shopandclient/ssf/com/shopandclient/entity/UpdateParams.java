package shopandclient.ssf.com.shopandclient.entity;

public class UpdateParams {

    private int id;
    private String userName;
    private String address;
    private String addressInfo;
    private String phone;
    private int isDefault;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public UpdateParams(int id, String userName, String address, String addressInfo, String phone, int isDefault) {
        this.id = id;
        this.userName = userName;
        this.address = address;
        this.addressInfo = addressInfo;
        this.phone = phone;
        this.isDefault = isDefault;
    }
}
