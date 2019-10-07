package shopandclient.ssf.com.shopandclient.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.DemoHelper;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.util.Observer;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.Subject;
import shopandclient.ssf.com.shopandclient.util.TokenManager;

/**
 * Created by zhg on 2019/6/19.
 */
public class UserCenterActivity extends BaseActivity implements BaseBiz, Observer {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    private TokenManager tokenManager;
    private ProgressDialog dialog;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_user_center;
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
        tokenManager = TokenManager.newInstance();
        tokenManager.registerObserver(this);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void initView() {
        super.initView();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.user_info));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        tvSave.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.save_address));
        ivScope.setVisibility(View.INVISIBLE);
    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }

    private void updateRemoteNick(final String nickName) {
        dialog = ProgressDialog.show(this, getString(R.string.dl_update_nick), getString(R.string.dl_waiting));
        new Thread(new Runnable() {

            @Override
            public void run() {
                boolean updatenick = DemoHelper.getInstance().getUserProfileManager().updateCurrentUserNickName(nickName);
                if (UserCenterActivity.this.isFinishing()) {
                    return;
                }
                if (!updatenick) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(UserCenterActivity.this, getString(R.string.toast_updatenick_fail), Toast.LENGTH_SHORT)
                                    .show();
                            dialog.dismiss();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            Toast.makeText(UserCenterActivity.this, getString(R.string.toast_updatenick_success), Toast.LENGTH_SHORT)
                                    .show();
                            //tvNickName.setText(nickName);
                        }
                    });
                }
            }
        }).start();
    }
}
