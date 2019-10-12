package shopandclient.ssf.com.shopandclient.ui.fragment;

import android.os.Bundle;
import android.widget.ListView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AllMyOrderAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseFragment;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.MyAllOrderBean;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class AllMyOrderFragment extends BaseFragment implements BaseBiz {

    private ListView lv_my_all_order;
    ArrayList<OrderDetailBean> orderDetailBeans1 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans2 = new ArrayList<>();
    ArrayList<OrderDetailBean> orderDetailBeans3 = new ArrayList<>();
    ArrayList<OrderInStoreBean> list = new ArrayList<>();
    ArrayList<OrderInStoreBean> list1 = new ArrayList<>();
    ArrayList<MyAllOrderBean> myList=new ArrayList<>();
    @Override
    protected int getLayoutResouceId() {
        return R.layout.fragment_all_my_order;
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
        lv_my_all_order = (ListView)findViewById(R.id.lv_my_all_order);
        orderDetailBeans1.add(new OrderDetailBean(R.drawable.meinv, "美女0"));
        orderDetailBeans2.add(new OrderDetailBean(R.drawable.meinv1, "美女1"));
        orderDetailBeans2.add(new OrderDetailBean(R.drawable.meinv2, "美女2"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv1, "美女1"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv2, "美女2"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv3, "美女3"));
        orderDetailBeans3.add(new OrderDetailBean(R.drawable.meinv, "美女4"));
        list.add(new OrderInStoreBean("天猫小店", orderDetailBeans1));
        list.add(new OrderInStoreBean("京东小店", orderDetailBeans2));
        list.add(new OrderInStoreBean("淘宝小店", orderDetailBeans3));
        list1.add(new OrderInStoreBean("淘宝小店", orderDetailBeans3));
        myList.add(new MyAllOrderBean("订单号码：011111111111111111111101",list));
        myList.add(new MyAllOrderBean("订单号码：011111111111111111111102",list1));
        AllMyOrderAdapter adapter=new AllMyOrderAdapter(MyApplication.getInstance().mContext,myList);
        lv_my_all_order.setAdapter(adapter);

    }

    public static AllMyOrderFragment newInstance(int type) {
        AllMyOrderFragment newFragment = new AllMyOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        newFragment.setArguments(bundle);
        return newFragment;
    }
}
