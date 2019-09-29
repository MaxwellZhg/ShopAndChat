package shopandclient.ssf.com.shopandclient.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.CategoryAdapter;
import shopandclient.ssf.com.shopandclient.adapter.CategoryGridViewAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.BrandDetail;
import shopandclient.ssf.com.shopandclient.entity.Brands;
import shopandclient.ssf.com.shopandclient.entity.CategoryBean;
import shopandclient.ssf.com.shopandclient.entity.Prosirers;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.net.services.UserService;
import shopandclient.ssf.com.shopandclient.util.Observer;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.Subject;
import shopandclient.ssf.com.shopandclient.util.TokenManager;
import shopandclient.ssf.com.shopandclient.weiget.bananer.Banner;
import shopandclient.ssf.com.shopandclient.weiget.bananer.GlideImageLoader;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/5/30.
 */
public class CategoryActivity extends BaseActivity implements BaseBiz, CategoryAdapter.OnitemClick, AdapterView.OnItemClickListener, Observer {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.rv_category)
    RecyclerView rvCategory;
    ArrayList<Prosirers.DataBean> brands ;
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
    private TextView tv_categroy_type;
    private ArrayList<CategoryBean.DataBean> list;
    private int  seriesId=0;
    private int  categroyid=0;
    private TokenManager tokenManager;

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
        tv_categroy_type = (TextView) header.findViewById(R.id.tv_categroy_type);
        getProeris();

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
    public void onItemClick(int position) {
        //Log.e("tttttt",position+"----------");
        if (brands.get(position).isSelect() == false) {
            for (int i = 0; i < brands.size(); i++) {
                brands.get(i).setSelect(false);
            }
            brands.get(position).setSelect(true);
            categoryAdapter.notifyDataSetChanged();
        }
        getCategory(brands.get(position).getId());
        seriesId = brands.get(position).getId();
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle=new Bundle();
        bundle.putInt("seriersId",seriesId);
        bundle.putInt("catergoryId",list.get(position).getId());
        openActivity(CategoryNameActivity.class,bundle);
    }

    public void getProeris(){
        ProductService productService = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<Prosirers> call =productService.getProirers();
        call.enqueue(new Callback<Prosirers>() {
            @Override
            public void onResponse(Call<Prosirers> call, Response<Prosirers> response) {
                if(response.body().getCode()==200) {
                    Log.e("tttttttt", response.body().getData().toString());
                    brands = response.body().getData();
                    brands.get(0).setSelect(true);
                    seriesId=brands.get(0).getId();
                    getCategory(brands.get(0).getId());
                    categoryAdapter = new CategoryAdapter(CategoryActivity.this, brands);
                    rvCategory.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
                    rvCategory.setAdapter(categoryAdapter);
                    categoryAdapter.setOnitemClickLintener(CategoryActivity.this);
                }
            }

            @Override
            public void onFailure(Call<Prosirers> call, Throwable t) {

            }
        });
    }

    public void getCategory(int sid){
        ProductService productService = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<CategoryBean> call =productService.getCategory(sid);
        call.enqueue(new Callback<CategoryBean>() {
            @Override
            public void onResponse(Call<CategoryBean> call, Response<CategoryBean> response) {
                if(response.body().getCode()==200) {
                    list = response.body().getData();
                    tv_categroy_type.setText(list.get(0).getTypename());
                    brandDetails.clear();
                    for(int i=1;i<list.size();i++){
                        brandDetails.add(new BrandDetail(R.drawable.meinv,list.get(i).getTypename()));
                    }
                    cgv = new CategoryGridViewAdapter(CategoryActivity.this);
                    if(rvBandGv.getChildCount()==0) {
                        rvBandGv.addHeaderView(header);
                    }
                    cgv.clearData();
                    cgv.getData(brandDetails);
                    rvBandGv.setAdapter(cgv);
                    cgv.notifyDataSetChanged();
                    rvBandGv.setOnItemClickListener(CategoryActivity.this);
                }
            }

            @Override
            public void onFailure(Call<CategoryBean> call, Throwable t) {

            }
        });
    }
}
