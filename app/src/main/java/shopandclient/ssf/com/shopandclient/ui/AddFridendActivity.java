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
import shopandclient.ssf.com.shopandclient.adapter.AddFriendsAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/20.
 */
public class AddFridendActivity extends BaseActivity implements BaseBiz {
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
    @BindView(R.id.rv_add_friends)
    RecyclerView rvAddFriends;
    ArrayList<OrderDetailBean> arrayList = new ArrayList<>();
    @BindView(R.id.iv_serach_scope)
    ImageView ivSerachScope;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_add_friends;
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
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.add_friends));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        arrayList.add(new OrderDetailBean(R.drawable.meinv1, "美女1"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv2, "美女2"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv3, "美女3"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv1, "美女1"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv2, "美女2"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv3, "美女3"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        AddFriendsAdapter afa = new AddFriendsAdapter(this, arrayList);
        rvAddFriends.setLayoutManager(new LinearLayoutManager(this));
        rvAddFriends.setAdapter(afa);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }
}
