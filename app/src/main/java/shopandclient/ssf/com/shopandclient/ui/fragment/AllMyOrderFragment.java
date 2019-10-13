package shopandclient.ssf.com.shopandclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ListView;
import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AllMyOrderAdapter;
import shopandclient.ssf.com.shopandclient.adapter.ScanLisstAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseFragment;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.*;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.PesronnalService;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class AllMyOrderFragment extends BaseFragment implements BaseBiz {

    private ListView lv_my_all_order;
    ArrayList<OrderTypeBean.DataBean.ListBean> orderDetailBeans1 = new ArrayList<>();
    private int orderType;
    private int count=8;
    private int pageNum=1;
    private EasyRefreshLayout erlObligation;
    private ArrayList<OrderTypeBean.DataBean.ListBean> list;
    private AllMyOrderAdapter adapter;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle args = getArguments();
        orderType = args.getInt("type");
        erlObligation = (EasyRefreshLayout)findViewById(R.id.erl_obligation);
        lv_my_all_order = (ListView)findViewById(R.id.lv_my_all_order);
        getData( orderType,1);

    }

    public static AllMyOrderFragment newInstance(int type) {
        AllMyOrderFragment newFragment = new AllMyOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    public void getData(int orderType,int page){
        PesronnalService service= RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<OrderTypeBean> call=service.postOrderbyType(orderType,page);
        call.enqueue(new Callback<OrderTypeBean>() {
            @Override
            public void onResponse(Call<OrderTypeBean> call, Response<OrderTypeBean> response) {
                if(response.body().getCode()==200){
                    list = response.body().getData().getList();
                    orderDetailBeans1.addAll(list);
                    adapter = new AllMyOrderAdapter(MyApplication.getInstance().mContext,orderDetailBeans1);
                    lv_my_all_order.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<OrderTypeBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        erlObligation.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                if (count < list.size()) {
                    pageNum++;
                    getData(orderType,pageNum);
                } else {
                    ToastUtil.showToast(MyApplication.getInstance().mContext, getString(R.string.no_more));
                    erlObligation.loadMoreComplete();
                    erlObligation.setLoadMoreModel(LoadModel.NONE);
                }
            }

            @Override
            public void onRefreshing() {
                pageNum = 1;
                orderDetailBeans1.clear();
                getData(orderType,pageNum);
                erlObligation.setLoadMoreModel(LoadModel.COMMON_MODEL);
                erlObligation.refreshComplete();
            }
        });
    }

}
