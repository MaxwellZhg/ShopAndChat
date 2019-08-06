package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.CategoryAdapter;
import shopandclient.ssf.com.shopandclient.adapter.CategoryNameAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.CatetogryBrandName;
import shopandclient.ssf.com.shopandclient.entity.ProductListBean;
import shopandclient.ssf.com.shopandclient.entity.ProductListParams;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/5/31.
 */
public class CategoryNameActivity extends BaseActivity implements BaseBiz, CategoryAdapter.OnitemClick {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.select_time)
    TextView selectTime;
    @BindView(R.id.select_sale_num)
    TextView selectSaleNum;
    @BindView(R.id.select_sale_price)
    TextView selectSalePrice;
    @BindView(R.id.rv_brands_name)
    RecyclerView rvBrandsName;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    ArrayList<CatetogryBrandName> catetogryBrandNames = new ArrayList<>();
    @BindView(R.id.rl_select_time)
    RelativeLayout rlSelectTime;
    @BindView(R.id.rl_select_sale_num)
    RelativeLayout rlSelectSaleNum;
    @BindView(R.id.rl_select_sale_price)
    RelativeLayout rlSelectSalePrice;
    @BindView(R.id.iv_select_time)
    ImageView ivSelectTime;
    @BindView(R.id.iv_select_sale_num)
    ImageView ivSelectSaleNum;
    @BindView(R.id.iv_select_sale_price)
    ImageView ivSelectSalePrice;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.erl_obligation)
    EasyRefreshLayout erlObligation;
    private CategoryNameAdapter categoryNameAdapter;
    boolean isselectTime = false;
    boolean isselectNum = false;
    boolean isselectPrice = false;
    private int pageNum = 1;
    private int orderType=0;
    private int count=8;
    private int seriersId;
    private int catergoryId;
    private ArrayList<ProductListBean.DataBean.ListBean> alllist=new ArrayList<>();
    private ArrayList<ProductListBean.DataBean.ListBean> list;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_category_name;
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
        Intent bundle=getIntent();
        seriersId = bundle.getIntExtra("seriersId",0);
        catergoryId = bundle.getIntExtra("catergoryId",0);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.category_name));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        getData(2,1);
    }


    @OnClick({R.id.rl_select_time, R.id.rl_select_sale_num, R.id.rl_select_sale_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_select_time:
                if (isselectTime == false) {
                    isselectTime = true;
                    selectTime.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.tv_price_color));
                    ivSelectTime.setImageResource(R.drawable.litlite_up);
                    orderType=1;
                } else {
                    isselectTime = false;
                    selectTime.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    ivSelectTime.setImageResource(R.drawable.little_down);
                    orderType=2;
                }
                selectSaleNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectSaleNum.setImageResource(R.drawable.little_down);
                selectSalePrice.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectSalePrice.setImageResource(R.drawable.little_down);
                isselectNum = false;
                isselectPrice = false;
                break;
            case R.id.rl_select_sale_num:
                if (isselectNum == false) {
                    isselectNum = true;
                    selectSaleNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.tv_price_color));
                    ivSelectSaleNum.setImageResource(R.drawable.litlite_up);
                    orderType=3;
                } else {
                    isselectNum = false;
                    selectSaleNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    ivSelectSaleNum.setImageResource(R.drawable.little_down);
                    orderType=4;
                }
                isselectPrice = false;
                isselectTime = false;
                selectTime.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectTime.setImageResource(R.drawable.little_down);
                selectSalePrice.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectSalePrice.setImageResource(R.drawable.little_down);
                break;
            case R.id.rl_select_sale_price:
                if (isselectPrice == false) {
                    isselectPrice = true;
                    selectSalePrice.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.tv_price_color));
                    ivSelectSalePrice.setImageResource(R.drawable.litlite_up);
                    orderType=5;
                } else {
                    isselectPrice = false;
                    selectSalePrice.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    ivSelectSalePrice.setImageResource(R.drawable.little_down);
                    orderType=6;
                }
                isselectTime = false;
                isselectNum = false;
                selectTime.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectTime.setImageResource(R.drawable.little_down);
                selectSaleNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectSaleNum.setImageResource(R.drawable.little_down);
                break;
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        erlObligation.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                if (count < list.size()) {
                    pageNum++;
                    getData(orderType,pageNum);
                } else {
                    ToastUtil.showToast(CategoryNameActivity.this, getString(R.string.no_more));
                    erlObligation.loadMoreComplete();
                    erlObligation.setLoadMoreModel(LoadModel.NONE);
                }
            }

            @Override
            public void onRefreshing() {
                pageNum = 1;
                alllist.clear();
                getData(orderType,pageNum);
                erlObligation.setLoadMoreModel(LoadModel.COMMON_MODEL);
                erlObligation.refreshComplete();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("id",alllist.get(position).getId());
        openActivity(GoodsDetailActivity.class,bundle);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }
    private void getData(int orderType,int pageNum){
        ProductService service=RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<ProductListBean> call=service.getProductList(pageNum,orderType,catergoryId,seriersId);
        call.enqueue(new Callback<ProductListBean>() {
            @Override
            public void onResponse(Call<ProductListBean> call, Response<ProductListBean> response) {
                if(response.body().getCode()==200) {
                    list = response.body().getData().getList();
                    if(list.size()>0) {
                        alllist.addAll(list);
                        categoryNameAdapter = new CategoryNameAdapter(CategoryNameActivity.this, alllist);
                        rvBrandsName.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
                        rvBrandsName.setAdapter(categoryNameAdapter);
                        categoryNameAdapter.setOnitemClickLintener(CategoryNameActivity.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductListBean> call, Throwable t) {

            }
        });
    }
}
