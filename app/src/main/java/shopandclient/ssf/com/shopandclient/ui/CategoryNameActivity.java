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
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.CategoryAdapter;
import shopandclient.ssf.com.shopandclient.adapter.CategoryNameAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.CatetogryBrandName;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/5/31.
 */
public class CategoryNameActivity extends BaseActivity implements BaseBiz, CategoryAdapter.OnitemClick {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.select_time)
    TextView selectTime;
    @BindView(R.id.select_sale_num)
    TextView selectSaleNum;
    @BindView(R.id.select_sale_price)
    TextView selectSalePrice;
    @BindView(R.id.rv_brands_name)
    RecyclerView rvBrandsName;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    ArrayList<CatetogryBrandName> catetogryBrandNames = new ArrayList<>();
    @BindView(R.id.rl_select_time)
    RelativeLayout rlSelectTime;
    @BindView(R.id.rl_select_sale_num)
    RelativeLayout rlSelectSaleNum;
    @BindView(R.id.rl_select_sale_price)
    RelativeLayout rlSelectSalePrice;
    @BindView(R.id.iv_select_time)
    ImageView ivSelectTime;
    @BindView(R.id.iv_select_sale_num)
    ImageView ivSelectSaleNum;
    @BindView(R.id.iv_select_sale_price)
    ImageView ivSelectSalePrice;
    private CategoryNameAdapter categoryNameAdapter;
    boolean isselectTime = false;
    boolean isselectNum = false;
    boolean isselectPrice = false;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_category_name;
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
        catetogryBrandNames.add(new CatetogryBrandName(R.drawable.meinv, "大家好、、可以约我。。电话15814096599", "我可以出开房钱"));
        catetogryBrandNames.add(new CatetogryBrandName(R.drawable.meinv1, "大家好、、可以约我。。电话15814096599", "我可以出开房钱"));
        catetogryBrandNames.add(new CatetogryBrandName(R.drawable.meinv2, "大家好、、可以约我。。电话15814096599", "我可以出开房钱"));
        catetogryBrandNames.add(new CatetogryBrandName(R.drawable.meinv3, "大家好、、可以约我。。电话15814096599", "我可以出开房钱"));
        catetogryBrandNames.add(new CatetogryBrandName(R.drawable.meinv, "大家好、、可以约我。。电话15814096599", "我可以出开房钱"));
        catetogryBrandNames.add(new CatetogryBrandName(R.drawable.meinv1, "大家好、、可以约我。。电话15814096599", "我可以出开房钱"));
        catetogryBrandNames.add(new CatetogryBrandName(R.drawable.meinv2, "大家好、、可以约我。。电话15814096599", "我可以出开房钱"));
        catetogryBrandNames.add(new CatetogryBrandName(R.drawable.meinv3, "大家好、、可以约我。。电话15814096599", "我可以出开房钱"));
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.category_name));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        categoryNameAdapter = new CategoryNameAdapter(this, catetogryBrandNames);
        rvBrandsName.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
        rvBrandsName.setAdapter(categoryNameAdapter);
        categoryNameAdapter.setOnitemClickLintener(this);
    }


    @OnClick({R.id.rl_select_time, R.id.rl_select_sale_num, R.id.rl_select_sale_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_select_time:
                if (isselectTime == false) {
                    isselectTime = true;
                    selectTime.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.tv_price_color));
                    ivSelectTime.setImageResource(R.drawable.litlite_up);
                } else {
                    isselectTime = false;
                    selectTime.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    ivSelectTime.setImageResource(R.drawable.little_down);
                }
                selectSaleNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectSaleNum.setImageResource(R.drawable.little_down);
                selectSalePrice.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectSalePrice.setImageResource(R.drawable.little_down);
                isselectNum = false;
                isselectPrice = false;
                break;
            case R.id.rl_select_sale_num:
                if (isselectNum == false) {
                    isselectNum = true;
                    selectSaleNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.tv_price_color));
                    ivSelectSaleNum.setImageResource(R.drawable.litlite_up);
                } else {
                    isselectNum = false;
                    selectSaleNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    ivSelectSaleNum.setImageResource(R.drawable.little_down);
                }
                isselectPrice = false;
                isselectTime = false;
                selectTime.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectTime.setImageResource(R.drawable.little_down);
                selectSalePrice.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectSalePrice.setImageResource(R.drawable.little_down);
                break;
            case R.id.rl_select_sale_price:
                if (isselectPrice == false) {
                    isselectPrice = true;
                    selectSalePrice.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.tv_price_color));
                    ivSelectSalePrice.setImageResource(R.drawable.litlite_up);
                } else {
                    isselectPrice = false;
                    selectSalePrice.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    ivSelectSalePrice.setImageResource(R.drawable.little_down);
                }
                isselectTime = false;
                isselectNum = false;
                selectTime.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectTime.setImageResource(R.drawable.little_down);
                selectSaleNum.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                ivSelectSaleNum.setImageResource(R.drawable.little_down);
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        openActivity(GoodsDetailActivity.class);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }
}
