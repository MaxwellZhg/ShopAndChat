/*
package shopandclient.ssf.com.shopandclient.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AttrAdapter;
import shopandclient.ssf.com.shopandclient.adapter.MyPaddingDecoration;
import shopandclient.ssf.com.shopandclient.adapter.ShopDetailAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.Attr;
import shopandclient.ssf.com.shopandclient.entity.BrandDetail;
import shopandclient.ssf.com.shopandclient.entity.BrandsDetail;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.util.ScreenDipUtil;

import java.util.ArrayList;
import java.util.Timer;

*/
/**
 * Created by zhg on 2019/6/3.
 *//*

public class BrandsDetailActivity extends BaseActivity implements BaseBiz, AttrAdapter.OnitemClick {
    @BindView(R.id.rv_brands_detail)
    RecyclerView rvBrandsDetail;
    @BindView(R.id.rl_add_into_shop)
    RelativeLayout rlAddIntoShop;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    private BrandsDetail brandsDetail;
    ArrayList<BrandDetail> shopdetails = new ArrayList<>();
    ArrayList<String> banner = new ArrayList<>();
    private ArrayList<Attr> attrs = new ArrayList<>();
    private ShopDetailAdapter shopDetailAdapter;
    private Dialog mShareDialog;
    private AttrAdapter attrAdapter;
    private int count;
    private EditText et_count;
    private TextView tv_reduce;
    private TextView tv_add;
    Timer timer = new Timer();
    int trans = ScreenDipUtil.dip2px(250);

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_brands_detail;
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
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.white), 0);
    }

    @Override
    protected void initView() {
        super.initView();
        brandsDetail = new BrandsDetail();
        banner.add("http://i9.hexunimg.cn/2013-07-05/155842064.jpg");
        banner.add("http://hbimg.b0.upaiyun.com/fc4e0017928cd9281e13a84c025b5277e5314d2c247e8-VOlLH3_fw658");
        banner.add("http://i6.hexunimg.cn/2013-07-05/155842061.jpg");
        banner.add("http://s10.sinaimg.cn/mw690/006hikKrzy7sly9tEBb49&amp");
        banner.add("http://i6.hexunimg.cn/2013-07-05/155842061.jpg");
        shopdetails.add(new BrandDetail(R.drawable.meinv1, "苍井空"));
        shopdetails.add(new BrandDetail(R.drawable.meinv2, "苍井空1"));
        shopdetails.add(new BrandDetail(R.drawable.meinv3, "苍井空2"));
        attrs.add(new Attr("属性一", true));
        attrs.add(new Attr("属性二", false));
        attrs.add(new Attr("属性三", false));
        attrs.add(new Attr("属性四", false));
        brandsDetail.setBanalist(banner);
        brandsDetail.setMdrawlist(shopdetails);
        shopDetailAdapter = new ShopDetailAdapter(this, brandsDetail);
        rvBrandsDetail.setLayoutManager(new LinearLayoutManager(this));
        rvBrandsDetail.setAdapter(shopDetailAdapter);
    }

    */
/**
     * 显示弹出框
     *//*

    private void showDialog() {
        if (mShareDialog == null) {
            initShareDialog();
        }
        mShareDialog.show();
    }

    */
/**
     * 初始化分享弹出框
     *//*

    private void initShareDialog() {
        mShareDialog = new Dialog(this, R.style.dialog_bottom_full);
        mShareDialog.setCanceledOnTouchOutside(false); //手指触碰到外界取消
        mShareDialog.setCancelable(true);             //可取消 为true
        Window window = mShareDialog.getWindow();      // 得到dialog的窗体
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.share_animation);

        View view = View.inflate(this, R.layout.item_shopcart_dailog, null); //获取布局视图
        RecyclerView rv_attr = (RecyclerView) view.findViewById(R.id.rv_attr);
        tv_reduce = (TextView) view.findViewById(R.id.tv_reduce);

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
        rv_attr.setLayoutManager(new GridLayoutManager(this, 3));
       */
/* attrAdapter = new AttrAdapter(this, attrs);
        attrAdapter.setOnitemClickLintener(this);*//*

        rv_attr.addItemDecoration(new MyPaddingDecoration(this));
        rv_attr.setAdapter(attrAdapter);
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
    public void onItemClick(int position) {
        if (!attrs.get(position).getChecked()) {
            for (int i = 0; i < attrs.size(); i++) {
                attrs.get(i).setChecked(false);
            }
            attrs.get(position).setChecked(true);
            attrAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.rl_btn_back, R.id.rl_add_into_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_btn_back:
                finish();
                break;
            case R.id.rl_add_into_shop:
                showDialog();
                break;
        }
    }
}
*/
