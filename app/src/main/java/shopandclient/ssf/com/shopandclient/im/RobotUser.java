package shopandclient.ssf.com.shopandclient.im;

import com.hyphenate.easeui.domain.EaseUser;

public class RobotUser extends EaseUser {
    public RobotUser(String username) {
        super(username.toLowerCase());
    }
}
