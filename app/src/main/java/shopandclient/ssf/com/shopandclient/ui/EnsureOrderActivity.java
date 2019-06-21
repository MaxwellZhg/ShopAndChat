package shopandclient.ssf.com.shopandclient.ui;

import android.graphics.Color;
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
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.EnsureOrderAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/18.
 */
public class EnsureOrderActivity extends BaseActivity implements BaseBiz,View.OnClickListener {
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
    @BindView(R.id.lv_ensure_order)
    ListView lvEnsureOrder;
    ArrayList<OrderDetailBean> orderDetailBeans3 = new ArrayList<>();
    ArrayList<OrderInStoreBean> list = new ArrayList<>();
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_total_num)
    TextView tvTotalNum;
    @BindView(R.id.tv_ensure)
    TextView tvEnsure;
    @BindView(R.id.rl_ensure)
    RelativeLayout rlEnsure;
    private OptionsPickerView pvOptions;
    private ArrayList<String> reason = new ArrayList<>();
    private TextView tv_track_way;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_ensure_order;
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
        getNoLinkData();
        initNoLinkOptionsPicker();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.nav_black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.ensure_order));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv1, "美女1"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv2, "美女2"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv3, "美女3"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        list.add(new OrderInStoreBean("淘宝小店", orderDetailBeans3));
        View tips = LayoutInflater.from(this).inflate(R.layout.item_ensure_order_header, null);
        View footer=LayoutInflater.from(this).inflate(R.layout.item_ensure_order_footer,null);
        tv_track_way = (TextView) footer.findViewById(R.id.tv_track_way);
        tv_track_way.setOnClickListener(this);
        EnsureOrderAdapter eoa = new EnsureOrderAdapter(this, list);
        lvEnsureOrder.addHeaderView(tips);
        lvEnsureOrder.setAdapter(eoa);
        lvEnsureOrder.addFooterView(footer);
        tvTotalNum.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.total_clothes, list.get(0).getOrderDetailBeans().size()));
        tvTotalPrice.setText("总计：" + "¥722.00");
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

   private void initNoLinkOptionsPicker() {
       pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
           @Override
           public void onOptionsSelect(int options1, int options2, int options3, View v) {
               final String tx = reason.get(options1);
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       tv_track_way.setText(tx);
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
       pvOptions.setPicker(reason);//二级选择器
   }
    private void getNoLinkData() {
        reason.add("免邮");
        reason.add("EMS");
        reason.add("四通一达");
    }


    @Override
    public void onClick(View v) {
        pvOptions.show();
    }
}
