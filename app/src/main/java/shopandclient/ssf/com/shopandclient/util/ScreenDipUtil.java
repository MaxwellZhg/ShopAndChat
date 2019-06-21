package shopandclient.ssf.com.shopandclient.util;

import android.content.Context;
import shopandclient.ssf.com.shopandclient.base.MyApplication;

/**
 * Created by zhg on 2019/6/4.
 */
public class ScreenDipUtil {
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int dip2px(float dpValue) {
        Context context = MyApplication.getInstance().mContext;
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
