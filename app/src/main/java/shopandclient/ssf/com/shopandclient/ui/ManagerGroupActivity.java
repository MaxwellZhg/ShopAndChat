package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.GroupMemberAdapter;
import shopandclient.ssf.com.shopandclient.adapter.LvManagerGroupAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/20.
 */
public class ManagerGroupActivity extends BaseActivity implements BaseBiz {
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
    @BindView(R.id.lv_manager_group)
    ListView lvManagerGroup;
    private MyRecycleview rv_manager;
    ArrayList<OrderDetailBean> arrayList = new ArrayList<>();
    ArrayList<String> strings = new ArrayList<>();

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_manager_group;
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
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.manager_group));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女1"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女2"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女3"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女5"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女1"));
        arrayList.add(new OrderDetailBean(R.drawable.meinv, "美女2"));
        strings.add("群聊名称");
        strings.add("管理员移交");
        strings.add("解散该群");
        strings.add("禁言");
        View itemview = LayoutInflater.from(this).inflate(R.layout.item_manager_group_header, null);
        rv_manager = (MyRecycleview) itemview.findViewById(R.id.rv_manager);
        GroupMemberAdapter gma = new GroupMemberAdapter(this);
        gma.setImages(arrayList);
        rv_manager.setLayoutManager(new GridLayoutManager(this, 4));
        rv_manager.setAdapter(gma);
        LvManagerGroupAdapter lma = new LvManagerGroupAdapter(this, strings);
        lvManagerGroup.addHeaderView(itemview);
        lvManagerGroup.setAdapter(lma);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }
}
