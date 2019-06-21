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
import com.jaeger.library.StatusBarUtil;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.StreetAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.StreetBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.inter.OnItemClickListener;
import shopandclient.ssf.com.shopandclient.weiget.bananer.Banner;
import shopandclient.ssf.com.shopandclient.weiget.bananer.BannerConfig;
import shopandclient.ssf.com.shopandclient.weiget.bananer.GlideImageLoader;
import shopandclient.ssf.com.shopandclient.weiget.bananer.Transformer;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/4.
 */
public class StreetActivity extends BaseActivity implements BaseBiz,AdapterView.OnItemClickListener {
    @BindView(R.id.gv_street)
    GridViewWithHeaderAndFooter gvStreet;
    ArrayList<StreetBean> brandDetails = new ArrayList<>();
    ArrayList<String> banner = new ArrayList<>();
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    private GlideImageLoader glideImageLoader;
    private View header;
    private Banner bannerview;

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
        brandDetails.add(new StreetBean("苍井空", R.drawable.meinv1));
        brandDetails.add(new StreetBean("苍井空1", R.drawable.meinv2));
        brandDetails.add(new StreetBean("苍井空1", R.drawable.meinv3));
        brandDetails.add(new StreetBean("苍井空", R.drawable.meinv));
        brandDetails.add(new StreetBean("苍井空1", R.drawable.meinv2));
        brandDetails.add(new StreetBean("苍井空2", R.drawable.meinv3));
        brandDetails.add(new StreetBean("苍井空", R.drawable.meinv1));
        brandDetails.add(new StreetBean("苍井空2", R.drawable.meinv2));
        brandDetails.add(new StreetBean("苍井空1", R.drawable.meinv3));
        gvStreet.addHeaderView(header);
        StreetAdapter streetAdapter = new StreetAdapter(this, brandDetails);
        gvStreet.setAdapter(streetAdapter);
        gvStreet.setOnItemClickListener(this);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        openActivity(StoreDetailActivity.class);
    }
}
