package shopandclient.ssf.com.shopandclient.util;

import android.content.Context;
import shopandclient.ssf.com.shopandclient.base.MyApplication;

/**
 * Created by zhg on 2019/5/27.
 */
public class SpConfig extends PreferenceUtil {

    private static final String YOUR_APP_NAME = "shopcliebt_sp";
    public SpConfig() {
        super(YOUR_APP_NAME);
    }

    @Override
    protected Context getContext() {
        return MyApplication.getInstance().mContext;
    }

    private static class SingletonHolder {
        private static final SpConfig INSTANCE = new SpConfig();
    }

    public static SpConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
