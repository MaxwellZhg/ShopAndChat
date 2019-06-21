package shopandclient.ssf.com.shopandclient.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.StreetAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.StreetBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/5.
 */
public class StoreDetailActivity extends BaseActivity implements BaseBiz {
    @BindView(R.id.gv_sotre_detail)
    GridViewWithHeaderAndFooter gvSotreDetail;
    ArrayList<StreetBean> brandDetails = new ArrayList<>();
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
    private View header;

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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips),0);
        brandDetails.add(new StreetBean("苍井空", R.drawable.meinv1));
        brandDetails.add(new StreetBean("苍井空1", R.drawable.meinv2));
        brandDetails.add(new StreetBean("苍井空1", R.drawable.meinv3));
        brandDetails.add(new StreetBean("苍井空", R.drawable.meinv));
        brandDetails.add(new StreetBean("苍井空1", R.drawable.meinv2));
        brandDetails.add(new StreetBean("苍井空2", R.drawable.meinv3));
        brandDetails.add(new StreetBean("苍井空", R.drawable.meinv1));
        brandDetails.add(new StreetBean("苍井空2", R.drawable.meinv2));
        brandDetails.add(new StreetBean("苍井空1", R.drawable.meinv3));
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.back_white));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.store_detail));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header = inflater.inflate(R.layout.item_store_detail_header, null);
        gvSotreDetail.addHeaderView(header);
        StreetAdapter streetAdapter = new StreetAdapter(this, brandDetails);
        gvSotreDetail.setAdapter(streetAdapter);
    }


    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }
}
