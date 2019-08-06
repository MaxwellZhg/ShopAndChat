package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import shopandclient.ssf.com.shopandclient.adapter.CommonCityAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.*;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/19.
 */
public class CommonCityActivity extends BaseActivity implements BaseBiz, CommonCityAdapter.onItemFriendClick {
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
    @BindView(R.id.rv_common_city)
    RecyclerView rvCommonCity;
    @BindView(R.id.erl_obligation)
    EasyRefreshLayout erlObligation;
    private CommonCityAdapter cca;
    private int pageNum = 1;
    private int count = 8;
    ArrayList<LocalUserBean.DataBean.ListBean> brandDetails;
    ArrayList<LocalUserBean.DataBean.ListBean> allList=new ArrayList<>();
    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_common_city;
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
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.common_city));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        cca = new CommonCityAdapter(this);
        cca.setOnItemFriendClick(this);
        rvCommonCity.setLayoutManager(new LinearLayoutManager(this));
        getData("深圳",1);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void fiendClick() {
        openActivity(FriendsCenterActivity.class);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        erlObligation.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                if (count <brandDetails.size()) {
                    pageNum++;
                    getData("深圳", pageNum);
                } else {
                    ToastUtil.showToast(CommonCityActivity.this, getString(R.string.no_more));
                    erlObligation.loadMoreComplete();
                    erlObligation.setLoadMoreModel(LoadModel.NONE);
                }
            }

            @Override
            public void onRefreshing() {
                pageNum = 1;
                allList.clear();
                cca.clearData();
                getData("深圳", pageNum);
                erlObligation.setLoadMoreModel(LoadModel.COMMON_MODEL);
                erlObligation.refreshComplete();
            }
        });
    }

    public void getData(String city, int pageNum) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<LocalUserBean> call = service.getLocalUser(new LocalUserParams("深圳",1));
        call.enqueue(new Callback<LocalUserBean>() {
            @Override
            public void onResponse(Call<LocalUserBean> call, Response<LocalUserBean> response) {
                if (response.body().getCode() == 200) {
                    brandDetails = response.body().getData().getList();
                    if(brandDetails.size()>0) {
                        allList.addAll(brandDetails);
                        cca.addData(brandDetails);
                        rvCommonCity.setAdapter(cca);
                    }
                }
            }

            @Override
            public void onFailure(Call<LocalUserBean> call, Throwable t) {

            }
        });
    }
}
