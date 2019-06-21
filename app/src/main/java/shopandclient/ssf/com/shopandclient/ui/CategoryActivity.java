package shopandclient.ssf.com.shopandclient.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import shopandclient.ssf.com.shopandclient.adapter.CategoryAdapter;
import shopandclient.ssf.com.shopandclient.adapter.CategoryGridViewAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.BrandDetail;
import shopandclient.ssf.com.shopandclient.entity.Brands;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.weiget.bananer.Banner;
import shopandclient.ssf.com.shopandclient.weiget.bananer.GlideImageLoader;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/5/30.
 */
public class CategoryActivity extends BaseActivity implements BaseBiz, CategoryAdapter.OnitemClick, AdapterView.OnItemClickListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    ArrayList<Brands> brands = new ArrayList<>();
    ArrayList<BrandDetail> brandDetails = new ArrayList<>();
    ArrayList<String> banner = new ArrayList<>();
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rv_band_gv)
    GridViewWithHeaderAndFooter rvBandGv;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    private CategoryAdapter categoryAdapter;
    private CategoryGridViewAdapter cgv;
    private View header;
    private Banner bannerview;
    private GlideImageLoader glideImageLoader;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_category;
    }

    @Override
    protected void initView() {
        super.initView();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.nav_black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.category));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        brandDetails.add(new BrandDetail(R.drawable.meinv1, "苍井空"));
        brandDetails.add(new BrandDetail(R.drawable.meinv2, "苍井空1"));
        brandDetails.add(new BrandDetail(R.drawable.meinv3, "苍井空2"));
        brands.add(new Brands("蔬菜", 2));
        brands.add(new Brands("家电", 1));
        brands.add(new Brands("生鲜", 1));
        brands.add(new Brands("食品", 1));
        brands.add(new Brands("百货", 1));
        brands.add(new Brands("鲜花", 1));
        banner.add("http://i9.hexunimg.cn/2013-07-05/155842064.jpg");
        banner.add("http://hbimg.b0.upaiyun.com/fc4e0017928cd9281e13a84c025b5277e5314d2c247e8-VOlLH3_fw658");
        banner.add("http://i6.hexunimg.cn/2013-07-05/155842061.jpg");
        banner.add("http://s10.sinaimg.cn/mw690/006hikKrzy7sly9tEBb49&amp");
        glideImageLoader = new GlideImageLoader();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header = inflater.inflate(R.layout.item_cetogroy_detail_header, null);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        rlBtnScope.setVisibility(View.INVISIBLE);
        bannerview = (Banner) header.findViewById(R.id.banner);
        bannerview.setImages(banner).setImageLoader(glideImageLoader).start();
        categoryAdapter = new CategoryAdapter(this, brands);
        rvCategory.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
        rvCategory.setAdapter(categoryAdapter);
        categoryAdapter.setOnitemClickLintener(this);
        cgv = new CategoryGridViewAdapter(this, brandDetails);
        rvBandGv.addHeaderView(header);
        rvBandGv.setAdapter(cgv);
        rvBandGv.setOnItemClickListener(this);

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
    public void onItemClick(int position) {
        //Log.e("tttttt",position+"----------");
        if (brands.get(position).getType() == 1) {
            for (int i = 0; i < brands.size(); i++) {
                brands.get(i).setType(1);
            }
            brands.get(position).setType(2);
            categoryAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        openActivity(CategoryNameActivity.class);
    }
}
