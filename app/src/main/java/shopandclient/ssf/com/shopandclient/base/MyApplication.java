package shopandclient.ssf.com.shopandclient.base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.push.EMPushHelper;
import com.hyphenate.push.EMPushType;
import com.hyphenate.push.PushListener;
import com.hyphenate.util.EMLog;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by zhg on 2019/5/27.
 */
public class MyApplication extends MultiDexApplication {
    private final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication instance;
    public Context mContext;
    /**
     * nickname for current user, the nickname instead of ID be shown when user receive notification from APNs
     */
    public static String currentUserNick = "";
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = this;
        MultiDex.install(this);
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);

        //init demo helper
        DemoHelper.getInstance().init(mContext);

        // 请确保环信SDK相关方法运行在主进程，子进程不会初始化环信SDK（该逻辑在EaseUI.java中）
        if (EaseUI.getInstance().isMainProcess(this)) {
            // 初始化华为 HMS 推送服务, 需要在SDK初始化后执行
            //HMSPushHelper.getInstance().initHMSAgent(instance);

            EMPushHelper.getInstance().setPushListener(new PushListener() {
                @Override
                public void onError(EMPushType pushType, long errorCode) {
                    // TODO: 返回的errorCode仅9xx为环信内部错误，可从EMError中查询，其他错误请根据pushType去相应第三方推送网站查询。
                    EMLog.e("PushClient", "Push client occur a error: " + pushType + " - " + errorCode);
                }
            });
        }
    }

    /**
     * @return
     * @Description: 获取实例
     */
    public static MyApplication getInstance() {
        return instance;

    }

}
