package shopandclient.ssf.com.shopandclient.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.ReteryAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.util.Observer;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.Subject;
import shopandclient.ssf.com.shopandclient.util.TokenManager;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class ReteryActivty extends BaseActivity implements BaseBiz,View.OnClickListener, Observer {
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
    @BindView(R.id.lv_retrey)
    ListView lvRetrey;
    ArrayList<OrderDetailBean> orderDetailBeans1 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans2 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans3 = new ArrayList<>();
    ArrayList<OrderInStoreBean> list = new ArrayList<>();
    private TextView tv_choose_retery_type;
    private TextView tv_choose_retery_reason;
    private OptionsPickerView pvOptions;
    private OptionsPickerView pvOptions1;
    private ArrayList<String> type = new ArrayList<>();
    private ArrayList<String> reason = new ArrayList<>();
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_retery_goods;
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
        getNoLinkData();
        initNoLinkOptionsPicker1();
        initNoLinkOptionsPicker2();
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
        View tips = LayoutInflater.from(this).inflate(R.layout.item_retery_header, null);
        View bottom = LayoutInflater.from(this).inflate(R.layout.item_retery_goods_bottom, null);
        lvRetrey.addHeaderView(tips);
        ReteryAdapter orderAdapter = new ReteryAdapter(this, list);
        lvRetrey.setAdapter(orderAdapter);
        lvRetrey.addFooterView(bottom);
        tv_choose_retery_type = (TextView)findViewById(R.id.tv_choose_retery_type);
        tv_choose_retery_type.setOnClickListener(this);
        tv_choose_retery_reason = (TextView)findViewById(R.id.tv_choose_retery_reason);
        tv_choose_retery_reason.setOnClickListener(this);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_choose_retery_type:
                pvOptions.show();
                break;
            case R.id.tv_choose_retery_reason:
                pvOptions1.show();
                break;
        }
    }

    private void initNoLinkOptionsPicker1() {// 不联动的多级选项
            pvOptions=null;
            pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    final String tx = type.get(options1);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_choose_retery_type.setText(tx);
                            tv_choose_retery_type.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                        }
                    });
                }
            })
                    .setTitleText("退款类型")
                    .setContentTextSize(20)//设置滚轮文字大小
                    .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                    .setSelectOptions(0)//默认选中项
                    .setBgColor(Color.WHITE)
                    .setTitleBgColor(Color.WHITE)
                    .setTitleColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
                    .setCancelColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
                    .setSubmitColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips))
                    .setTextColorCenter(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
                    .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .setOutSideColor(0x00000000) //设置外部遮罩颜色
                    .setOutSideCancelable(false)
                    .build();
            pvOptions.setPicker(type);//二级选择器



    }
    private void initNoLinkOptionsPicker2() {// 不联动的多级选项
        pvOptions1 = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                final String tx = reason.get(options1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_choose_retery_reason.setText(tx);
                        tv_choose_retery_reason.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    }
                });
            }
        })
                .setTitleText("退款类型")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
                .setCancelColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
                .setSubmitColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips))
                .setTextColorCenter(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .setOutSideCancelable(false)
                .build();
        pvOptions1.setPicker(reason);//二级选择器



    }
    private void getNoLinkData() {
        type.add("退款");
        type.add("退款退货");
        type.add("退货");
        reason.add("产品与描述不相符");
        reason.add("拍错");
        reason.add("其他");
    }
}
