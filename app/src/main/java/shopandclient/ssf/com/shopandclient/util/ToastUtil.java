package shopandclient.ssf.com.shopandclient.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/8/2.
 */

public class ToastUtil {
    private static String oldMsg;
    protected static Toast toast = null;
    private static long startTime = 0;
    private static long nextTime = 0;

    public static void showToast(Context context, String s) {
        if (toast == null) {
            toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.show();
            startTime = System.currentTimeMillis();
        } else {
            nextTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (nextTime - startTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        startTime = nextTime;
    }


    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }
}
