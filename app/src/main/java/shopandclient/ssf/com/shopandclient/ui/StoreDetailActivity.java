package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.jaeger.library.StatusBarUtil;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.StreetAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.CollectParams;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.entity.StoreInfoBean;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.util.*;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/5.
 */
public class StoreDetailActivity extends BaseActivity implements BaseBiz, Observer {
    @BindView(R.id.gv_sotre_detail)
    GridViewWithHeaderAndFooter gvSotreDetail;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.iv_store_icon)
    ImageView ivStoreIcon;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_collect_store_count)
    TextView tvCollectStoreCount;
    @BindView(R.id.tv_host)
    TextView tvHost;
    @BindView(R.id.tv_host_number)
    TextView tvHostNumber;
    @BindView(R.id.tv_host_address)
    TextView tvHostAddress;
    @BindView(R.id.erl_obligation)
    EasyRefreshLayout erlObligation;
    @BindView(R.id.ll_store_name)
    LinearLayout llStoreName;
    @BindView(R.id.ll_store_level)
    LinearLayout llStoreLevel;
    @BindView(R.id.ll_addinto_collect)
    LinearLayout llAddintoCollect;
    @BindView(R.id.iv_collect_goods)
    ImageView ivCollectGoods;
    private View header;
    private int storeId;
    private int pageNum = 1;
    private int count = 8;
    private ArrayList<StoreInfoBean.DataBean.ProListBean.ListBean> alllist = new ArrayList<>();
    private ArrayList<StoreInfoBean.DataBean.ProListBean.ListBean> list;
    private StreetAdapter streetAdapter;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_shopstore_detail;
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
        Intent intent = getIntent();
        storeId = intent.getIntExtra("id", 0);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.back_white));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.store_detail));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        getData(storeId, 1);
    }

    private void getData(int storeId, int page) {
        ProductService service = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<StoreInfoBean> call = service.getStoreInfo(storeId, page);
        call.enqueue(new Callback<StoreInfoBean>() {
            @Override
            public void onResponse(Call<StoreInfoBean> call, Response<StoreInfoBean> response) {
                if (response.body().getCode() == 200) {
                    tvStoreName.setText(response.body().getData().getStoreName());
                    tvCollectStoreCount.setText(response.body().getData().getCollectionNum() + "人收藏过");
                    tvHost.setText(response.body().getData().getRegName());
                    tvHostNumber.setText(response.body().getData().getContact());
                    tvHostAddress.setText(response.body().getData().getAddress());
                    list = response.body().getData().getProList().getList();
                    alllist.addAll(list);
                    streetAdapter.addData(alllist);
                    gvSotreDetail.setAdapter(streetAdapter);
                }
            }

            @Override
            public void onFailure(Call<StoreInfoBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        tokenManager = TokenManager.newInstance();
        tokenManager.registerObserver(this);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
        streetAdapter = new StreetAdapter(this);
    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }


    @OnClick({R.id.rl_btn_back, R.id.ll_addinto_collect,R.id.iv_collect_goods})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.rl_btn_back:
                finish();
                break;
            case R.id.ll_addinto_collect:
                break;
            case R.id.iv_collect_goods:
                setCollectStore(storeId);
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
                    getData(storeId, pageNum);
                } else {
                    ToastUtil.showToast(StoreDetailActivity.this, getString(R.string.no_more));
                    erlObligation.loadMoreComplete();
                    erlObligation.setLoadMoreModel(LoadModel.NONE);
                }
            }

            @Override
            public void onRefreshing() {
                pageNum = 1;
                alllist.clear();
                streetAdapter.clearData();
                getData(storeId, pageNum);
                erlObligation.setLoadMoreModel(LoadModel.COMMON_MODEL);
                erlObligation.refreshComplete();
            }
        });
    }


    public void setCollectStore(int id) {
        ProductService service = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<PostComment> call = service.setStoreCollect(new CollectParams(id));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode() == 200) {
                    if(response.body().getResult().equals(MyApplication.getInstance().mContext.getResources().getString(R.string.collect_success))){
                      runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              ivCollectGoods.setImageResource(R.drawable.icon_collect);
                          }
                      });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ivCollectGoods.setImageResource(R.drawable.icon_uncollect);
                            }
                        });
                    }
                    ToastUtil.showToast(StoreDetailActivity.this, response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

}
