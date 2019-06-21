package shopandclient.ssf.com.shopandclient.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by zhg on 2019/5/27.
 */
public class MyApplication extends MultiDexApplication {
    private final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication instance;
    public Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;
        MultiDex.install(this);
    }

    /**
     * @return
     * @Description: 获取实例
     */
    public static MyApplication getInstance() {
        return instance;

    }

}
