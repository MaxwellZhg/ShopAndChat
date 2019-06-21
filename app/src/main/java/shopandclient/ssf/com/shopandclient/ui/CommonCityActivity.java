package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.CommonCityAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/19.
 */
public class CommonCityActivity extends BaseActivity implements BaseBiz,CommonCityAdapter.onItemFriendClick {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.rv_common_city)
    RecyclerView rvCommonCity;
    ArrayList<OrderDetailBean> arrayList = new ArrayList<>();
    private CommonCityAdapter cca;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_common_city;
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
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.common_city));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女1"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女2"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女3"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女5"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女1"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女2"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女3"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女5"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女1"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女2"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女3"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女5"));
        cca = new CommonCityAdapter(this, arrayList);
        cca.setOnItemFriendClick(this);
        rvCommonCity.setLayoutManager(new LinearLayoutManager(this));
        rvCommonCity.setAdapter(cca);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void fiendClick() {
        openActivity(FriendsCenterActivity.class);
    }
}
