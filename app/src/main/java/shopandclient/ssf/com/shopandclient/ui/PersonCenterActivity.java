package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.AddUserResult;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.UserService;
import shopandclient.ssf.com.shopandclient.util.SpConfig;

/**
 * Created by zhg on 2019/6/18.
 */
public class PersonCenterActivity extends BaseActivity implements BaseBiz {
    @BindView(R.id.iv_center)
    ImageView ivCenter;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.iv_user_city)
    ImageView ivUserCity;
    @BindView(R.id.rl_city_user)
    RelativeLayout rlCityUser;
    @BindView(R.id.iv_user_order)
    ImageView ivUserOrder;
    @BindView(R.id.rl_my_order)
    RelativeLayout rlMyOrder;
    @BindView(R.id.iv_user_address)
    ImageView ivUserAddress;
    @BindView(R.id.rl_my_address)
    RelativeLayout rlMyAddress;
    @BindView(R.id.rl_login_out)
    Button rlLoginOut;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_personal_center;
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
    }

    @OnClick({R.id.rl_city_user, R.id.rl_my_order, R.id.rl_my_address, R.id.rl_login_out, R.id.rl_btn_back,R.id.iv_center})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_city_user:
                openActivity(CommonCityActivity.class);
                break;
            case R.id.rl_my_order:
                openActivity(MyOrderActivity.class);
                break;
            case R.id.rl_my_address:
                openActivity(AddressActivity.class);
                break;
            case R.id.rl_login_out:
                loginOut();
                break;
            case R.id.rl_btn_back:
                finish();
                break;
            case R.id.iv_center:
                openActivity(UserCenterActivity.class);
                break;
        }
    }

    public void loginOut(){
        UserService userService = RetrofitHandle.getInstance().retrofit.create(UserService.class);
        Call<AddUserResult> call=userService.loginOut();
        call.enqueue(new Callback<AddUserResult>() {
            @Override
            public void onResponse(Call<AddUserResult> call, Response<AddUserResult> response) {
                Log.e("ttttttttt",response.body().getResult().toString());
                SpConfig.getInstance().putBool("isLogin",false);
                finish();
            }

            @Override
            public void onFailure(Call<AddUserResult> call, Throwable t) {

            }
        });
    }


}
