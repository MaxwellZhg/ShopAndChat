package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.util.Observer;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.Subject;
import shopandclient.ssf.com.shopandclient.util.TokenManager;

/**
 * Created by zhg on 2019/6/10.
 */
public class MyOrderActivity extends BaseActivity implements BaseBiz, Observer {
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_order_all)
    RelativeLayout rlOrderAll;
    @BindView(R.id.rl_order_nopay)
    RelativeLayout rlOrderNopay;
    @BindView(R.id.rl_order_nodelivery)
    RelativeLayout rlOrderNodelivery;
    @BindView(R.id.rl_order_delivery)
    RelativeLayout rlOrderDelivery;
    @BindView(R.id.rl_shopcart)
    RelativeLayout rlShopcart;
    @BindView(R.id.rl_history_scan)
    RelativeLayout rlHistoryScan;
    @BindView(R.id.rl_store_collection)
    RelativeLayout rlStoreCollection;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_my_order;
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

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }

    @Override
    protected void initView() {
        super.initView();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.back_white));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.my_order));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.rl_btn_back, R.id.rl_order_all, R.id.rl_order_nopay, R.id.rl_order_nodelivery, R.id.rl_order_delivery, R.id.rl_shopcart, R.id.rl_history_scan, R.id.rl_store_collection})
    public void onViewClicked(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()) {
            case R.id.rl_btn_back:
                finish();
                break;
            case R.id.rl_order_all:
                openActivity(AllMyOrderActivity.class);
                break;
            case R.id.rl_order_nopay:
                openActivity(ReteryActivty.class);
                break;
            case R.id.rl_order_nodelivery:
                break;
            case R.id.rl_order_delivery:
                break;
            case R.id.rl_shopcart:
                openActivity(ShopCartActivity.class);
                break;
            case R.id.rl_history_scan:
                bundle.putInt("scantype",1);
                openActivity(HistoryScanActivity.class,bundle);
                break;
            case R.id.rl_store_collection:
                bundle.putInt("scantype",2);
                openActivity(HistoryScanActivity.class,bundle);
                break;
        }
    }
}
