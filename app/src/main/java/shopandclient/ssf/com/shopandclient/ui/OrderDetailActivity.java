package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
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
import shopandclient.ssf.com.shopandclient.adapter.OrderAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/11.
 */
public class OrderDetailActivity extends BaseActivity implements BaseBiz {
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
    @BindView(R.id.gotopay)
    TextView gotopay;
    @BindView(R.id.retery)
    TextView retery;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.lv_order_detail)
    ListView lvOrderDetail;
    ArrayList<OrderDetailBean> orderDetailBeans1 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans2 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans3 = new ArrayList<>();
    ArrayList<OrderInStoreBean> list = new ArrayList<>();


    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_order_detail;
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
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.order_detail));
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
        View tips = LayoutInflater.from(this).inflate(R.layout.item_order_detail_tips, null);
        View address = LayoutInflater.from(this).inflate(R.layout.item_devilivery_info, null);
        View recevice = LayoutInflater.from(this).inflate(R.layout.item_reciver_address, null);
        View bottom = LayoutInflater.from(this).inflate(R.layout.item_order_detai_info, null);
        lvOrderDetail.addHeaderView(tips);
        lvOrderDetail.addHeaderView(address);
        lvOrderDetail.addHeaderView(recevice);
        OrderAdapter orderAdapter = new OrderAdapter(this, list);
        lvOrderDetail.setAdapter(orderAdapter);
        lvOrderDetail.addFooterView(bottom);

    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }
}
