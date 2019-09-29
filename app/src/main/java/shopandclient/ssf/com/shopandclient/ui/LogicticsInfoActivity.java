package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.LogicticsGoodsInfoAdapter;
import shopandclient.ssf.com.shopandclient.adapter.LogicticsTrackInfoAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.LogicticsBean;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.util.Observer;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.Subject;
import shopandclient.ssf.com.shopandclient.util.TokenManager;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class LogicticsInfoActivity extends BaseActivity implements BaseBiz, Observer {
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.lv_logistics)
    ListView lvLogistics;
    ArrayList<OrderDetailBean> orderDetailBeans1 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans2 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans3 = new ArrayList<>();
    ArrayList<OrderInStoreBean> list = new ArrayList<>();
    ArrayList<LogicticsBean> arrayList = new ArrayList<>();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private MyRecycleview rv_logictics;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_logistics_order_info;
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
    protected void initView() {
        super.initView();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.logictics_info));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        orderDetailBeans1.add(new OrderDetailBean(R.drawable.meinv, "美女0"));
        orderDetailBeans2.add(new OrderDetailBean(R.drawable.meinv1, "美女1"));
        orderDetailBeans2.add(new OrderDetailBean(R.drawable.meinv2, "美女2"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv1, "美女1"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv2, "美女2"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv3, "美女3"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        list.add(new OrderInStoreBean("天猫小店", orderDetailBeans1));
        list.add(new OrderInStoreBean("京东小店", orderDetailBeans2));
        list.add(new OrderInStoreBean("淘宝小店", orderDetailBeans3));
        arrayList.add(new LogicticsBean("06-13", "15:55", "测试", 1));
        arrayList.add(new LogicticsBean("06-13", "15:55", "测试", 2));
        arrayList.add(new LogicticsBean("06-13", "15:55", "测试", 3));
        arrayList.add(new LogicticsBean("06-13", "15:55", "测试", 4));
        View tips = LayoutInflater.from(this).inflate(R.layout.item_retery_header, null);
        View bottom = LayoutInflater.from(this).inflate(R.layout.item_logictics_info, null);
        lvLogistics.addHeaderView(tips);
       // LogicticsGoodsInfoAdapter laia = new LogicticsGoodsInfoAdapter(this, list);
        LogicticsTrackInfoAdapter ltia = new LogicticsTrackInfoAdapter(this, arrayList);
        rv_logictics = (MyRecycleview) bottom.findViewById(R.id.rv_logictics);
        rv_logictics.setLayoutManager(new LinearLayoutManager(this));
        rv_logictics.setAdapter(ltia);
       // lvLogistics.setAdapter(laia);
        lvLogistics.addFooterView(bottom);

    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }
}
