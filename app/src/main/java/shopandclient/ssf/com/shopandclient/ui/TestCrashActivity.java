package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tencent.bugly.crashreport.CrashReport;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

public class TestCrashActivity extends BaseActivity implements BaseBiz {
    @BindView(R.id.btn_test)
    Button btnTest;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_test_crash;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test)
    public void onViewClicked() {
        CrashReport.testJavaCrash();
    }
}
