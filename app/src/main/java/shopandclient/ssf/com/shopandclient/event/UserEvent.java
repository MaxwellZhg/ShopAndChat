package shopandclient.ssf.com.shopandclient.event;

public class UserEvent {
    private String username;

    public UserEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
