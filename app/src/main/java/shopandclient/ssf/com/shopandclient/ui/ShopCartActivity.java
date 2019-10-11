package shopandclient.ssf.com.shopandclient.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AttrInfoAdapter;
import shopandclient.ssf.com.shopandclient.adapter.ShopCartAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.*;
import shopandclient.ssf.com.shopandclient.event.AddCartInfoEvent;
import shopandclient.ssf.com.shopandclient.event.CartAttrEvent;
import shopandclient.ssf.com.shopandclient.event.DeteleCartEvent;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.PesronnalService;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.util.Observer;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.Subject;
import shopandclient.ssf.com.shopandclient.util.TokenManager;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class ShopCartActivity extends BaseActivity implements BaseBiz, Observer, ShopCartAdapter.GotoEnsureOrderListener, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.lv_shop_cart)
    ListView lvShopCart;
    ArrayList<OrderDetailBean> orderDetailBeans1 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans2 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans3 = new ArrayList<>();
    ArrayList<ShopCartBean.DataBean> list;
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
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_total_num)
    TextView tvTotalNum;
    @BindView(R.id.tv_all_select)
    TextView tvAllSelect;
    @BindView(R.id.tv_ensure)
    TextView tvEnsure;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    private ShopCartAdapter orderAdapter;
    private Dialog mShareDialog;
    private TextView tv_price;
    private RecyclerView rv_attr_info;
    private TextView tv_title_dailog;
    private TextView tv_reduce;
    private RelativeLayout rl_ensure;
    private EditText et_count;
    private TextView tv_add;
    private int count;
    private ProductInfo.DataBean data;
    private ArrayList<ProductInfo.DataBean.ProAttrTypeBean> attrs = new ArrayList<>();
    private int typevalue1;
    private int typevalue2;
    private CartAttrEvent attrEvent;
    private Attr attrselect;
    private AttrInfoAdapter aia;
    private TokenManager tokenManager;
    private int totalCount = 0;
    private double totalMoney;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_shop_cart;
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
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.shop_cart));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        getShopCartInfo();
        checkbox.setOnCheckedChangeListener(this);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    public void getShopCartInfo() {
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<ShopCartBean> call = service.getShopCartInfo();
        call.enqueue(new Callback<ShopCartBean>() {
            @Override
            public void onResponse(Call<ShopCartBean> call, Response<ShopCartBean> response) {
                if (response.body().getCode() == 200) {
                    list = response.body().getData();
                    orderAdapter = new ShopCartAdapter(MyApplication.getInstance().mContext);
                    orderAdapter.addData(list);
                    orderAdapter.setOnGotoEnsureOrderListener(ShopCartActivity.this);
                    lvShopCart.setAdapter(orderAdapter);
                }
            }

            @Override
            public void onFailure(Call<ShopCartBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DeteleCartEvent event) {
        getShopCartInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCartAttrEvent(CartAttrEvent event) {
        attrEvent = event;
        attrselect = null;
        getData(event.getProId());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShopAttrEvent(Attr event) {
        attrselect = event;
        getShopCartAttrInfo(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddCartInfoEvent(AddCartInfoEvent event) {
        if (event.getType() == 1) {
            addUpDataCartDataInfo(event.getListbenas());
        } else {
            subcritUpDataCartDataInfo(event.getListbenas());
        }
    }

    private void addUpDataCartDataInfo(ShopCartBean.DataBean.ListProBean listbenas) {
        totalCount += listbenas.getAmount();
        totalMoney += listbenas.getuPrice()*listbenas.getAmount();
        tvTotalNum.setText("共"+totalCount+"件");
        Log.e("ttttttt", totalMoney + "");
    }

    private void subcritUpDataCartDataInfo(ShopCartBean.DataBean.ListProBean listbenas) {
        totalCount -= listbenas.getAmount();
        totalMoney -= listbenas.getUPrice()*listbenas.getAmount();
        tvTotalNum.setText("共"+totalCount+"件");
        Log.e("ttttttt", totalMoney + "");
    }

    /**
     * 初始化分享弹出框
     */
    private void initShareDialog() {
        mShareDialog = new Dialog(this, R.style.dialog_bottom_full);
        mShareDialog.setCanceledOnTouchOutside(false); //手指触碰到外界取消
        mShareDialog.setCancelable(true);             //可取消 为true
        Window window = mShareDialog.getWindow();      // 得到dialog的窗体
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.share_animation);

        View view = View.inflate(this, R.layout.item_shopcart_dailog, null); //获取布局视图
        rv_attr_info = (RecyclerView) view.findViewById(R.id.rv_attr_info);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_title_dailog = (TextView) view.findViewById(R.id.tv_title_dailog);
        tv_reduce = (TextView) view.findViewById(R.id.tv_reduce);
        rl_ensure = (RelativeLayout) view.findViewById(R.id.rl_ensure);
        rl_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (attrselect == null) {
                    UpdateShopCartGoodsAttr(attrEvent.getId(), typevalue1, typevalue2, mShareDialog);
                } else {
                    UpdateShopCartGoodsAttr(attrEvent.getId(), attrselect.getAttrL1ID(), attrselect.getAttrL2ID(), mShareDialog);
                }
            }
        });
        et_count = (EditText) view.findViewById(R.id.et_count);
        tv_add = (TextView) view.findViewById(R.id.tv_add);
        et_count.setText(attrEvent.getCount() + "");
        count = Integer.valueOf(et_count.getText().toString().trim());
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 100) {
                    count++;
                    tv_reduce.setClickable(true);
                    tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    UpdateCartGoodsNum(attrEvent.getId(), count, et_count);
                } else {
                    count = 100;
                    UpdateCartGoodsNum(attrEvent.getId(), count, et_count);
                }
            }
        });

        tv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count <= 1) {
                    UpdateCartGoodsNum(attrEvent.getId(), 1, et_count);
                    tv_reduce.setClickable(false);
                    tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                } else {
                    count--;
                    if (count == 1) {
                        tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                    }
                    UpdateCartGoodsNum(attrEvent.getId(), count, et_count);
                }
            }
        });
        tv_price.setText("¥" + data.getPrice());
        tv_title_dailog.setText(data.getProName());
        aia = new AttrInfoAdapter(this);
        aia.setData(attrs);
        rv_attr_info.setLayoutManager(new LinearLayoutManager(this));
        rv_attr_info.setAdapter(aia);
        aia.notifyDataSetChanged();
        view.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShareDialog != null && mShareDialog.isShowing()) {
                    mShareDialog.dismiss();
                    getShopCartInfo();
                }
            }
        });
        window.setContentView(view);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
    }

    private void getData(int id) {
        ProductService service = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<ProductInfo> call = service.getProductInfo(id);
        call.enqueue(new Callback<ProductInfo>() {
            @Override
            public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
                if (response.body().getCode() == 200) {
                    data = response.body().getData();
                    attrs.clear();
                    attrs.addAll(response.body().getData().getProAttrType());
                    typevalue1 = response.body().getData().getProAttrType().get(0).getProAttrTypeValue().get(0).getId();
                    typevalue2 = response.body().getData().getProAttrType().get(1).getProAttrTypeValue().get(0).getId();
                    showContentDialog();
                }
            }

            @Override
            public void onFailure(Call<ProductInfo> call, Throwable t) {

            }
        });
    }

    /**
     * 显示弹出框
     */
    public void showContentDialog() {
        if (mShareDialog != null) {
            mShareDialog = null;
            aia.clearData();
            initShareDialog();
        } else {
            initShareDialog();
        }

        mShareDialog.show();
    }

    public void getShopGoodsAttr(int id) {
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<ShopCartAttrs> call = service.getShopCartGoodsAttr(id);
        call.enqueue(new Callback<ShopCartAttrs>() {
            @Override
            public void onResponse(Call<ShopCartAttrs> call, Response<ShopCartAttrs> response) {

            }

            @Override
            public void onFailure(Call<ShopCartAttrs> call, Throwable t) {

            }
        });
    }

    public void getShopCartAttrInfo(Attr attr) {
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<ShopCartChooseAttrInfo> call = service.getChooseAttrInfo(attr.getAttrL1ID(), attr.getAttrL2ID());
        call.enqueue(new Callback<ShopCartChooseAttrInfo>() {
            @Override
            public void onResponse(Call<ShopCartChooseAttrInfo> call, Response<ShopCartChooseAttrInfo> response) {
                if (response.body().getCode() == 200) {
                    tv_price.setText(response.body().getData().getNewPrice() + "");
                }
            }

            @Override
            public void onFailure(Call<ShopCartChooseAttrInfo> call, Throwable t) {

            }
        });
    }

    public void UpdateCartGoodsNum(int id, final int count, final EditText et_count) {
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<PostComment> call = service.updateShopCartNum(new UpdateCartNumParams(id, count));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode() == 200) {
                    et_count.setText(String.valueOf(count));
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    public void UpdateShopCartGoodsAttr(int id, int L1, int L2, final Dialog mShareDialog) {
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<UpdateShopGoodsAttrInfo> call = service.updateShopCartAttr(new UpdateShopCartGoodsAttrParams(id, L1, L2));
        call.enqueue(new Callback<UpdateShopGoodsAttrInfo>() {
            @Override
            public void onResponse(Call<UpdateShopGoodsAttrInfo> call, Response<UpdateShopGoodsAttrInfo> response) {
                mShareDialog.dismiss();
                getShopCartInfo();
            }

            @Override
            public void onFailure(Call<UpdateShopGoodsAttrInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void gotoEnsureOrder(String str, int type) {
        Intent intent = new Intent();
        intent.putExtra("str", str);
        intent.putExtra("type", type);
        intent.setClass(this, EnsureOrderActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            updateCartInfoState();
        } else {
            updateCartInfoNoState();
        }
    }

    public void updateCartInfoState() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getListPro().size(); j++) {
                list.get(i).getListPro().get(j).setChoose(true);
            }
        }
        orderAdapter.clearData();
        orderAdapter.addData(list);
    }

    public void updateCartInfoNoState() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getListPro().size(); j++) {
                list.get(i).getListPro().get(j).setChoose(false);
            }
        }
        orderAdapter.clearData();
        orderAdapter.addData(list);
    }
}
