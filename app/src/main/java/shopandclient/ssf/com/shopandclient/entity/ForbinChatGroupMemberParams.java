package shopandclient.ssf.com.shopandclient.entity;

public class ForbinChatGroupMemberParams {

    private int groupid;
    private String idArray;
    private int ifForbid;

    public ForbinChatGroupMemberParams(int groupid, String idArray, int ifForbid) {
        this.groupid = groupid;
        this.idArray = idArray;
        this.ifForbid = ifForbid;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getIdArray() {
        return idArray;
    }

    public void setIdArray(String idArray) {
        this.idArray = idArray;
    }

    public int getIfForbid() {
        return ifForbid;
    }

    public void setIfForbid(int ifForbid) {
        this.ifForbid = ifForbid;
    }
}
