package shopandclient.ssf.com.shopandclient.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.ScanLisstAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseFragment;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class AllHistoryScanFragment extends BaseFragment implements BaseBiz {

    RecyclerView rvShop;
    private View mView;
    ArrayList<OrderDetailBean> orderDetailBeans1 = new ArrayList<>();

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected int getLayoutResouceId() {
        return R.layout.fragment_all_history;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initView() {
        super.initView();
        rvShop=(RecyclerView) findViewById(R.id.rv_shop);
        orderDetailBeans1.add(new OrderDetailBean(R.drawable.meinv, "美女0"));
        orderDetailBeans1.add(new OrderDetailBean(R.drawable.meinv1, "美女1"));
        orderDetailBeans1.add(new OrderDetailBean(R.drawable.meinv2, "美女2"));
        orderDetailBeans1.add(new OrderDetailBean(R.drawable.meinv1, "美女1"));
        orderDetailBeans1.add(new OrderDetailBean(R.drawable.meinv2, "美女2"));
        orderDetailBeans1.add(new OrderDetailBean(R.drawable.meinv3, "美女3"));
        orderDetailBeans1.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        ScanLisstAdapter scanLisstAdapter=new ScanLisstAdapter(MyApplication.getInstance().mContext,orderDetailBeans1);
        rvShop.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
        rvShop.setAdapter(scanLisstAdapter);
    }
}
