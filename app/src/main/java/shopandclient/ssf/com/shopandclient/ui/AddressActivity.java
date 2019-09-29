package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
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
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AddressAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.AddressBean;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.PesronnalService;
import shopandclient.ssf.com.shopandclient.util.Observer;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.Subject;
import shopandclient.ssf.com.shopandclient.util.TokenManager;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/6.
 */
public class AddressActivity extends BaseActivity implements BaseBiz,AddressAdapter.OnitemClick, Observer {
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    ArrayList<AddressBean.DataBean> list;
    private AddressAdapter adressAdapter;
    private int type;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_address;
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
        Intent intent=getIntent();
        type = intent.getIntExtra("type",0);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.back_white));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.address_manager));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        ivScope.setImageResource(R.drawable.function);
        getAddressList();
    }


    public void getAddressList() {
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<AddressBean> call = service.getAddressList();
        call.enqueue(new Callback<AddressBean>() {
            @Override
            public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                if (response.body().getCode() == 200) {
                    list = response.body().getData();
                    adressAdapter = new AddressAdapter(MyApplication.getInstance().mContext, list);
                    adressAdapter.setOnitemClick(AddressActivity.this);
                    rvAddress.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
                    rvAddress.setAdapter(adressAdapter);
                }
            }

            @Override
            public void onFailure(Call<AddressBean> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.rl_btn_back, R.id.rl_btn_scope})
    public void onViewClicked(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()) {
            case R.id.rl_btn_back:
                finish();
                break;
            case R.id.rl_btn_scope:
                bundle.putInt("type",1);
                openActivity(AddintoAddressActivity.class,bundle);
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        if(type==1) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", 2);
            bundle.putInt("id", list.get(position).getId());
            openActivity(AddintoAddressActivity.class, bundle);
        }else{
            Intent intent = new Intent();
            intent.putExtra("pos",position);
            setResult(2,intent);
        }
        finish();
    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }
}
