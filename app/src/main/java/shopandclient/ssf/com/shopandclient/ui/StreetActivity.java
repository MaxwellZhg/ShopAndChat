package shopandclient.ssf.com.shopandclient.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import shopandclient.ssf.com.shopandclient.adapter.StreetInfoAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.ProductTypeInfoParams;
import shopandclient.ssf.com.shopandclient.entity.StreetInfoBean;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.util.*;
import shopandclient.ssf.com.shopandclient.weiget.bananer.Banner;
import shopandclient.ssf.com.shopandclient.weiget.bananer.BannerConfig;
import shopandclient.ssf.com.shopandclient.weiget.bananer.GlideImageLoader;
import shopandclient.ssf.com.shopandclient.weiget.bananer.Transformer;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/4.
 */
public class StreetActivity extends BaseActivity implements BaseBiz, AdapterView.OnItemClickListener , Observer {
    @BindView(R.id.gv_street)
    GridViewWithHeaderAndFooter gvStreet;
    ArrayList<StreetInfoBean.DataBean.ListBean> brandDetails;
    ArrayList<StreetInfoBean.DataBean.ListBean> allList = new ArrayList<>();
    ArrayList<String> banner = new ArrayList<>();
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.erl_obligation)
    EasyRefreshLayout erlObligation;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    private GlideImageLoader glideImageLoader;
    private View header;
    private Banner bannerview;
    private int pageNum = 1;
    private int count = 8;
    private StreetInfoAdapter streetAdapter;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_street;
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
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.back_white));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.street_walk));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        banner.add("http://i9.hexunimg.cn/2013-07-05/155842064.jpg");
        banner.add("http://hbimg.b0.upaiyun.com/fc4e0017928cd9281e13a84c025b5277e5314d2c247e8-VOlLH3_fw658");
        banner.add("http://i6.hexunimg.cn/2013-07-05/155842061.jpg");
        banner.add("http://s10.sinaimg.cn/mw690/006hikKrzy7sly9tEBb49&amp");
        glideImageLoader = new GlideImageLoader();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header = inflater.inflate(R.layout.item_street_header, null);
        bannerview = (Banner) header.findViewById(R.id.street_banner);
        //设置banner样式
        //设置banner动画效果
        bannerview.setBannerAnimation(Transformer.DepthPage);
        bannerview.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerview.setIndicatorGravity(BannerConfig.CENTER);
        bannerview.setImages(banner).setImageLoader(glideImageLoader).start();
        gvStreet.addHeaderView(header);
        streetAdapter = new StreetInfoAdapter(MyApplication.getInstance().mContext);
        gvStreet.setOnItemClickListener(StreetActivity.this);
        getData(1, 1);
    }

    @OnClick({R.id.rl_btn_back,R.id.rl_btn_scope})
    public void onViewClicked(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()) {
            case R.id.rl_btn_back:
                finish();
                break;
            case R.id.rl_btn_scope:
                bundle.putInt("type",1);
                openActivity(SearchProActivity.class,bundle);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", allList.get(position).getStoreID());
        openActivity(GoodsDetailActivity.class, bundle);
    }

    public void getData(int type, int pageNum) {
        ProductService service = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<StreetInfoBean> call = service.postStreetInfo(new ProductTypeInfoParams(type, "", 1));
        call.enqueue(new Callback<StreetInfoBean>() {
            @Override
            public void onResponse(Call<StreetInfoBean> call, Response<StreetInfoBean> response) {
                if (response.body().getCode() == 200) {
                    brandDetails = response.body().getData().getList();
                    if (brandDetails.size() > 0) {
                        allList.addAll(brandDetails);
                        streetAdapter.addData(brandDetails);
                        gvStreet.setAdapter(streetAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<StreetInfoBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        erlObligation.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                if (count < brandDetails.size()) {
                    pageNum++;
                    getData(1, pageNum);
                } else {
                    ToastUtil.showToast(StreetActivity.this, getString(R.string.no_more));
                    erlObligation.loadMoreComplete();
                    erlObligation.setLoadMoreModel(LoadModel.NONE);
                }
            }

            @Override
            public void onRefreshing() {
                pageNum = 1;
                allList.clear();
                streetAdapter.clearData();
                getData(1, pageNum);
                erlObligation.setLoadMoreModel(LoadModel.COMMON_MODEL);
                erlObligation.refreshComplete();
            }
        });
    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }
}
