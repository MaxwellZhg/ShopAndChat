package shopandclient.ssf.com.shopandclient.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import shopandclient.ssf.com.shopandclient.swipe.SwipeBackActivityBase;
import shopandclient.ssf.com.shopandclient.swipe.SwipeBackActivityHelper;
import shopandclient.ssf.com.shopandclient.swipe.SwipeBackLayout;
import shopandclient.ssf.com.shopandclient.swipe.Utils;

/**
 * Created by zhg on 2019/5/29.
 */
public class SwipeBackActivity extends AppCompatActivity implements SwipeBackActivityBase {

    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        mHelper.getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        mHelper.getSwipeBackLayout().scrollToFinishActivity();
    }
}
