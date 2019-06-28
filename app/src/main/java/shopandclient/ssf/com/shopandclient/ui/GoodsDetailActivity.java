package shopandclient.ssf.com.shopandclient.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AttrAdapter;
import shopandclient.ssf.com.shopandclient.adapter.AttrInfoAdapter;
import shopandclient.ssf.com.shopandclient.adapter.SlideFragmentPagerAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.*;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.ui.fragment.GoodCommentFragment;
import shopandclient.ssf.com.shopandclient.ui.fragment.GoodDetailFragment;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;
import shopandclient.ssf.com.shopandclient.weiget.bananer.Banner;
import shopandclient.ssf.com.shopandclient.weiget.bananer.GlideImageLoader;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.FlingScrollDetailsLayout;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.NoneScrollViewPager;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/14.
 */
public class GoodsDetailActivity extends BaseActivity implements BaseBiz {
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
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    NoneScrollViewPager viewpager;
    @BindView(R.id.drag_content)
    FlingScrollDetailsLayout dragContent;
    @BindView(R.id.rl_add_into_shop)
    RelativeLayout rlAddIntoShop;
    ArrayList<String> bannerlist = new ArrayList<>();
    @BindView(R.id.tv_count_page)
    TextView tvCountPage;
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.img_heart)
    ImageView imgHeart;
    @BindView(R.id.tv_store_like)
    TextView tvStoreLike;
    @BindView(R.id.rl_homeshop)
    RelativeLayout rlHomeshop;
    @BindView(R.id.rl_service)
    RelativeLayout rlService;
    @BindView(R.id.rl_collect)
    RelativeLayout rlCollect;
    @BindView(R.id.limmit_buyer)
    RelativeLayout limmitBuyer;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_sale_count)
    TextView tvSaleCount;
    @BindView(R.id.rl_islike_goods)
    RelativeLayout rlIslikeGoods;
    @BindView(R.id.iv_collect_goods)
    ImageView ivCollectGoods;
    private GlideImageLoader glideImageLoader;
    ArrayList<String> itemtips = new ArrayList<>();
    ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<ProductInfo.DataBean.ProAttrTypeBean> attrs = new ArrayList<>();
    private Dialog mShareDialog;
    private TextView tv_reduce;
    private EditText et_count;
    private TextView tv_add;
    private int count;
    private AttrAdapter attrAdapter;
    private RelativeLayout rl_ensure;
    private int id;
    private TextView tv_price;
    private TextView tv_title_dailog;
    private ProductInfo.DataBean data;
    private GoodCommentFragment fragment;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_goods_detail;
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
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.goods_detail));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        glideImageLoader = new GlideImageLoader();
        fragments.add(new GoodDetailFragment());
        fragments.add(new GoodDetailFragment());
        fragment = new GoodCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        fragments.add(fragment);
        itemtips.add("商品详情");
        itemtips.add("商品参数");
        itemtips.add("评价");
        bannerlist.add("http://i9.hexunimg.cn/2013-07-05/155842064.jpg");
        bannerlist.add("http://hbimg.b0.upaiyun.com/fc4e0017928cd9281e13a84c025b5277e5314d2c247e8-VOlLH3_fw658");
        bannerlist.add("http://i6.hexunimg.cn/2013-07-05/155842061.jpg");
        bannerlist.add("http://s10.sinaimg.cn/mw690/006hikKrzy7sly9tEBb49&amp");
        bannerlist.add("http://i6.hexunimg.cn/2013-07-05/155842061.jpg");
        banner.setImages(bannerlist).setImageLoader(glideImageLoader).start();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Log.e("tttttttt","--------------"+position+1);
                int current = position + 1;
                tvCountPage.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.page_count, current, bannerlist.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout.setScrollPosition(position, 0, false);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setAdapter(new SlideFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        viewpager.setOffscreenPageLimit(3);
        tablayout.setupWithViewPager(viewpager);
        tablayout.getTabAt(0).setText(itemtips.get(0));
        tablayout.getTabAt(1).setText(itemtips.get(1));
        tablayout.getTabAt(2).setText(itemtips.get(2));
        dragContent.setOnSlideDetailsListener(new FlingScrollDetailsLayout.OnSlideFinishListener() {
            @Override
            public void onStatueChanged(FlingScrollDetailsLayout.CurrentTargetIndex status) {
                if (status == FlingScrollDetailsLayout.CurrentTargetIndex.UPSTAIRS) {

                } else {

                }

            }
        });
        getData(id);
    }

    private void getData(int id) {
        ProductService service = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<ProductInfo> call = service.getProductInfo(id);
        call.enqueue(new Callback<ProductInfo>() {
            @Override
            public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
                if (response.body().getCode() == 200) {
                    data = response.body().getData();
                    attrs.addAll(response.body().getData().getProAttrType());
                    tvTitle.setText(response.body().getData().getProName());
                    tvStoreLike.setText(response.body().getData().getGiveLikeNum() + "人赞过");
                    tvPrice.setText("¥" + response.body().getData().getPrice());
                    tvSaleCount.setText("月销量" + response.body().getData().getNum());
                    if (response.body().getData().isIfCollection()) {
                      ivCollectGoods.setImageResource(R.drawable.icon_collect);
                    }else{
                        ivCollectGoods.setImageResource(R.drawable.icon_uncollect);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductInfo> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.rl_add_into_shop, R.id.limmit_buyer, R.id.rl_collect, R.id.rl_homeshop, R.id.rl_service, R.id.rl_islike_goods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_add_into_shop:

                break;
            case R.id.limmit_buyer:
                showContentDialog();
                break;
            case R.id.rl_homeshop:
                Bundle bundle=new Bundle();
                bundle.putInt("id",id);
                openActivity(StoreDetailActivity.class,bundle);
                break;
            case R.id.rl_collect:
                setCollect(id);
                break;
            case R.id.rl_islike_goods:
                postLike(id, 2);
                break;
        }
    }

    /**
     * 显示弹出框
     */
    public void showContentDialog() {
        if (mShareDialog == null) {
            initShareDialog();
        }
        mShareDialog.show();
    }

    /**
     * 初始化分享弹出框
     */
    private void initShareDialog() {
        mShareDialog = new Dialog(this, R.style.dialog_bottom_full);
        mShareDialog.setCanceledOnTouchOutside(false); //手指触碰到外界取消
        mShareDialog.setCancelable(true);             //可取消 为true
        Window window = mShareDialog.getWindow();      // 得到dialog的窗体
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.share_animation);

        View view = View.inflate(this, R.layout.item_shopcart_dailog, null); //获取布局视图
        RecyclerView rv_attr_info = (RecyclerView) view.findViewById(R.id.rv_attr_info);
        tv_price = (TextView) view.findViewById(R.id.tv_price);
        tv_title_dailog = (TextView) view.findViewById(R.id.tv_title_dailog);
        tv_reduce = (TextView) view.findViewById(R.id.tv_reduce);
        rl_ensure = (RelativeLayout) view.findViewById(R.id.rl_ensure);
        rl_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareDialog.dismiss();
                openActivity(EnsureOrderActivity.class);

            }
        });
        et_count = (EditText) view.findViewById(R.id.et_count);
        tv_add = (TextView) view.findViewById(R.id.tv_add);
        count = Integer.valueOf(et_count.getText().toString().trim());
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 100) {
                    count++;
                    tv_reduce.setClickable(true);
                    tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    et_count.setText(String.valueOf(count));
                } else {
                    count = 100;
                    et_count.setText(String.valueOf(count));
                }
            }
        });

        tv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count <= 1) {
                    et_count.setText(String.valueOf(1));
                    tv_reduce.setClickable(false);
                    tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                } else {
                    count--;
                    if (count == 1) {
                        tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                    }
                    et_count.setText(String.valueOf(count));
                }
            }
        });
        tv_price.setText("¥" + data.getPrice());
        tv_title_dailog.setText(data.getProName());
        AttrInfoAdapter aia = new AttrInfoAdapter(this);
        aia.setData(attrs);
        rv_attr_info.setLayoutManager(new LinearLayoutManager(this));
        rv_attr_info.setAdapter(aia);
        aia.notifyDataSetChanged();
        view.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShareDialog != null && mShareDialog.isShowing()) {
                    mShareDialog.dismiss();
                }
            }
        });
        window.setContentView(view);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);//设置横向全屏
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Attr event) {
        ProductService service = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<AttrSelectBean> call = service.getAttrSelectInfo(event.getAttrL1ID(), event.getAttrL2ID());
        call.enqueue(new Callback<AttrSelectBean>() {
            @Override
            public void onResponse(Call<AttrSelectBean> call, Response<AttrSelectBean> response) {
                if (response.body().getCode() == 200) {
                    tv_price.setText("¥" + response.body().getData().getNewPrice());
                }
            }

            @Override
            public void onFailure(Call<AttrSelectBean> call, Throwable t) {

            }
        });
    }


    public void setCollect(int id) {
        ProductService service = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<PostComment> call = service.setCollect(new CollectParams(id));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode() == 200) {
                    if(response.body().getResult().equals("收藏成功")){
                        ivCollectGoods.setImageResource(R.drawable.icon_collect);
                    }else{
                        ivCollectGoods.setImageResource(R.drawable.icon_uncollect);
                    }
                    ToastUtil.showToast(GoodsDetailActivity.this, response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    public void postLike(int id, int type) {
        ProductService service = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<PostComment> call = service.setGiveLike(new PostLikeParams(id, type));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode() == 200) {
                    ToastUtil.showToast(GoodsDetailActivity.this, response.body().getResult());
                } else {
                    ToastUtil.showToast(GoodsDetailActivity.this, response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }
}
