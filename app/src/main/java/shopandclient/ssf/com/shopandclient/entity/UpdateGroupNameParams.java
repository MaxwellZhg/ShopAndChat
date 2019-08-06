package shopandclient.ssf.com.shopandclient.entity;

public class UpdateGroupNameParams {

    private int groupID;
    private String groupName;

    public UpdateGroupNameParams(int groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
