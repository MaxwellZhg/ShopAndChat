package shopandclient.ssf.com.shopandclient.base;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;
import butterknife.ButterKnife;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.StatusBarCompat;

/**
 * Created by zhg on 2019/5/28.
 */
public abstract class BaseActivity extends SwipeBackActivity {
    protected Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //沉浸式状态栏，设置window模式
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initNotitle();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
       ButterKnife.bind(this);
        mContext = this;
        AppManager.getAppManager().addActivity(this);

        initDate(savedInstanceState);
        initView();
        initEvent();
        setSwipeBackEnable(true);
    }

    protected void initNotitle() {

    }


    public abstract int getLayoutResourceId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivity(this);
    }

    protected void initView() {
    }

    protected void initDate(Bundle savedInstanceState) {
    }

    protected void initEvent() {
    }


    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     */
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    public void toastShortShow(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    public void toastLongShow(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
    }

    public boolean isLogin() {
        if (TextUtils.isEmpty(SpConfig.getInstance().getString(Constants.SESSIONID_STRING))) {
            //openActivity(LoginActivity.class);
            return false;
        }
        return true;
    }

    public boolean isLogin1() {
        if (TextUtils.isEmpty(SpConfig.getInstance().getString(Constants.SESSIONID_STRING))) {
            return false;
        }
        return true;
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @param isCheckNetwork 是否检查网络，true表示检查，false表示不检查
     * @return
     */
    public boolean isNetworkConnected(Context context, boolean isCheckNetwork) {
        if (isCheckNetwork && context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnectedOrConnecting();
        } else {
            return true;
        }
    }
}
