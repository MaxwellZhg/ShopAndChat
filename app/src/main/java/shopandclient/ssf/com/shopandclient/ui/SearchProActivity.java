package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
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
import shopandclient.ssf.com.shopandclient.entity.ProPara;
import shopandclient.ssf.com.shopandclient.entity.ProductListBean;
import shopandclient.ssf.com.shopandclient.entity.StreetInfoBean;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.util.*;

import java.util.ArrayList;

/**
 * Created by Maxwell.
 * E-mail: maxwell_smith@163.com
 * Date: 2019/9/17
 * Desc:
 */
public class SearchProActivity extends BaseActivity implements BaseBiz, TextWatcher , Observer {
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_seach_pro)
    EditText etSeachPro;
    @BindView(R.id.erl_obligation)
    EasyRefreshLayout erlObligation;
    @BindView(R.id.gv_street)
    GridViewWithHeaderAndFooter gvStreet;
    private MyRunnable proRunable;
    private Handler handler = new Handler();
    private int type;
    private int pageNum = 1;
    private int count = 8;
    ArrayList<StreetInfoBean.DataBean.ListBean> brandDetails;
    ArrayList<StreetInfoBean.DataBean.ListBean> allList=new ArrayList<>();
    private String strInfo;
    private StreetInfoAdapter streetAdapter;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_search_pro;
    }

    @Override
    protected void initView() {
        super.initView();
        Intent bundle = getIntent();
        type = bundle.getIntExtra("type", 0);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.pro_search));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        ivScope.setVisibility(View.INVISIBLE);
        etSeachPro.addTextChangedListener(this);
        streetAdapter = new StreetInfoAdapter(MyApplication.getInstance().mContext);
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!TextUtils.isEmpty(s.toString())) {
            strInfo = s.toString();
            handler.removeCallbacks(proRunable);
            proRunable = new MyRunnable(s.toString());
            handler.postDelayed(proRunable, 500);
        }
    }

    public class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            getSearchProInfo(name, 1);
        }
    }

    public void getSearchProInfo(String str, int pageNum) {
        ProductService userService = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<StreetInfoBean> call = userService.postSearchPro(new ProPara(type, str, pageNum));
        call.enqueue(new Callback<StreetInfoBean>() {
            @Override
            public void onResponse(Call<StreetInfoBean> call, Response<StreetInfoBean> response) {
                brandDetails = response.body().getData().getList();
                if(pageNum==1){
                    allList.clear();
                    streetAdapter.clearData();
                }
                if(brandDetails.size()>0) {
                    allList.addAll(brandDetails);
                    streetAdapter.addData(brandDetails);
                    gvStreet.setAdapter(streetAdapter);
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
                    getSearchProInfo(strInfo, pageNum);
                } else {
                    ToastUtil.showToast(SearchProActivity.this, getString(R.string.no_more));
                    erlObligation.loadMoreComplete();
                    erlObligation.setLoadMoreModel(LoadModel.NONE);
                }
            }

            @Override
            public void onRefreshing() {
                pageNum = 1;
                allList.clear();
                getSearchProInfo(strInfo, pageNum);
                erlObligation.setLoadMoreModel(LoadModel.COMMON_MODEL);
                erlObligation.refreshComplete();
            }
        });
    }

}
