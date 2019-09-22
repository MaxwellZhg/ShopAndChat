package shopandclient.ssf.com.shopandclient.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.TabLayoutAdapter;
import shopandclient.ssf.com.shopandclient.adapter.ViewPagerAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.ui.fragment.AllHistoryScanFragment;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class HistoryScanActivity extends BaseActivity implements BaseBiz, TabLayout.OnTabSelectedListener {
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
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> itemtips = new ArrayList<>();
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    private Intent intent;
    private int scantype;
    private CommonNavigator commonNavigator;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_history_scan;
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
        intent = getIntent();
        scantype = intent.getIntExtra("scantype", 0);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.history_scan));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        if (scantype == 1) {
            fragments.add(AllHistoryScanFragment.newInstance(0, scantype));
            fragments.add(AllHistoryScanFragment.newInstance(1, scantype));
            fragments.add(AllHistoryScanFragment.newInstance(2, scantype));
        } else {
            fragments.add(AllHistoryScanFragment.newInstance(1, scantype));
            fragments.add(AllHistoryScanFragment.newInstance(2, scantype));
        }
        if (scantype == 1) {
            itemtips.add("全部");
            itemtips.add("批发商品/店铺");
            itemtips.add("零售商品/店铺");
        } else {
            itemtips.add("收藏店铺");
            itemtips.add("收藏商品");
        }
        initPageIndecator();
   /*     tla = new TabLayoutAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(tla);
        vpMain.setOffscreenPageLimit(3);
        tabMain.setupWithViewPager(vpMain);
        if (scantype == 1) {
            tabMain.getTabAt(0).setText(itemtips.get(0));
            tabMain.getTabAt(1).setText(itemtips.get(1));
            tabMain.getTabAt(2).setText(itemtips.get(2));
        } else {
            tabMain.getTabAt(0).setText(itemtips.get(0));
            tabMain.getTabAt(1).setText(itemtips.get(1));
        }
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabMain));*/

    }

    private void initPageIndecator() {
        // 设置viewpager指示器
        commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return itemtips == null ? 0 : itemtips.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                colorTransitionPagerTitleView.setSelectedColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
                colorTransitionPagerTitleView.setText(itemtips.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vpMain.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(getResources().getColor(R.color.password_tips));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        // 设置viewpager页面缓存数量
        vpMain.setOffscreenPageLimit(fragments.size());
        vpMain.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragments));
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
               magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
        // 指示器绑定viewpager
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vpMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vpMain.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        vpMain.setCurrentItem(tab.getPosition());
    }
}
