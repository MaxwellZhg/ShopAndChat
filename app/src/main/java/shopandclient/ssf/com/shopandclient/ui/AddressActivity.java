package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AddressAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/6.
 */
public class AddressActivity extends BaseActivity implements BaseBiz {
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    ArrayList<Integer> list = new ArrayList<>();
    private AddressAdapter adressAdapter;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_address;
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
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @Override
    protected void initView() {
        super.initView();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.back_white));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.address_manager));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        ivScope.setImageResource(R.drawable.function);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        adressAdapter = new AddressAdapter(this, list);
        rvAddress.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
        rvAddress.setAdapter(adressAdapter);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }
}
