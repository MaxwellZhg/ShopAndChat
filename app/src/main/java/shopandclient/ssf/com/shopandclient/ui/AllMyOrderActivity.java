package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.TabLayoutAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.ui.fragment.AllMyOrderFragment;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class AllMyOrderActivity extends BaseActivity implements BaseBiz {
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.tab_main)
    TabLayout tabMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> itemtips = new ArrayList<>();
    private TabLayoutAdapter tla;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_history_scan;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void initView() {
        super.initView();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.my_order));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        fragments.add(new AllMyOrderFragment());
        fragments.add(new AllMyOrderFragment());
        fragments.add(new AllMyOrderFragment());
        fragments.add(new AllMyOrderFragment());
        itemtips.add("全部");
        itemtips.add("未支付");
        itemtips.add("未发货");
        itemtips.add("已发货");
        tla = new TabLayoutAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(tla);
        vpMain.setOffscreenPageLimit(3);
        tabMain.setupWithViewPager(vpMain);
        tabMain.getTabAt(0).setText(itemtips.get(0));
        tabMain.getTabAt(1).setText(itemtips.get(1));
        tabMain.getTabAt(2).setText(itemtips.get(2));
        tabMain.getTabAt(3).setText(itemtips.get(3));
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMain));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }
}
