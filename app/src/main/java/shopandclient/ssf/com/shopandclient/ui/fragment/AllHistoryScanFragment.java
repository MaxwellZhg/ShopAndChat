package shopandclient.ssf.com.shopandclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.ScanLisstAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseFragment;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.ProductListBean;
import shopandclient.ssf.com.shopandclient.entity.ScanListBean;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.PesronnalService;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.ui.CategoryNameActivity;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class AllHistoryScanFragment extends BaseFragment implements BaseBiz {
    private RecyclerView rvShop;
    private  ArrayList<ScanListBean.DataBean.ListBean> orderDetailBeans1 = new ArrayList<>();
    private ArrayList<ScanListBean.DataBean.ListBean> list;
    private EasyRefreshLayout erlObligation;
    private int count=8;
    private int pageNum=1;
    private int orderType;
    private ScanLisstAdapter scanLisstAdapter;
    private int scantype;


    public static AllHistoryScanFragment newInstance(int type,int scantype) {
        AllHistoryScanFragment newFragment = new AllHistoryScanFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("scantype", scantype);
        newFragment.setArguments(bundle);
        return newFragment;
    }

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        orderType = args.getInt("type");
        scantype = args.getInt("scantype");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected void initView() {
        super.initView();
        erlObligation = (EasyRefreshLayout)findViewById(R.id.erl_obligation);
        rvShop=(RecyclerView) findViewById(R.id.rv_shop);
        if(scantype==1) {
            getData(orderType, 1);
        }else if(scantype==2&&orderType==1){
            getCollectionStore(1);
        }else if(scantype==2&&orderType==2){
            getCollectionGoods(1);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
            erlObligation.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
                @Override
                public void onLoadMore() {
                    if (count < list.size()) {
                        pageNum++;
                        if(scantype==1) {
                            getData(orderType,pageNum);
                        }else if(scantype==2&&orderType==1){
                            getCollectionStore(pageNum);
                        }else if(scantype==2&&orderType==2){
                            getCollectionGoods(pageNum);
                        }
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
                    if(scantype==1) {
                        getData(orderType, pageNum);
                    }else if(scantype==2&&orderType==1){
                        getCollectionStore(pageNum);
                    }else if(scantype==2&&orderType==2){
                        getCollectionGoods(pageNum);
                    }
                    erlObligation.setLoadMoreModel(LoadModel.COMMON_MODEL);
                    erlObligation.refreshComplete();
                }
            });
        }

    private void getData(int orderType, int pageNum) {
        PesronnalService service= RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<ScanListBean> call=service.getScanlist(orderType,pageNum);
        call.enqueue(new Callback<ScanListBean>() {
            @Override
            public void onResponse(Call<ScanListBean> call, Response<ScanListBean> response) {
                if(response.body().getCode()==200) {
                    list=response.body().getData().getList();
                    orderDetailBeans1.addAll(list);
                    scanLisstAdapter = new ScanLisstAdapter(MyApplication.getInstance().mContext, orderDetailBeans1,scantype);
                    rvShop.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
                    rvShop.setAdapter(scanLisstAdapter);
                    scanLisstAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ScanListBean> call, Throwable t) {

            }
        });
    }

    public void getCollectionGoods(int pageNum){
        PesronnalService service= RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<ScanListBean> call=service.getCollectionGoods(pageNum);
        call.enqueue(new Callback<ScanListBean>() {
            @Override
            public void onResponse(Call<ScanListBean> call, Response<ScanListBean> response) {
                if(response.body().getCode()==200) {
                    list = response.body().getData().getList();
                    orderDetailBeans1.addAll(list);
                    scanLisstAdapter = new ScanLisstAdapter(MyApplication.getInstance().mContext, orderDetailBeans1,scantype);
                    rvShop.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
                    rvShop.setAdapter(scanLisstAdapter);
                    scanLisstAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ScanListBean> call, Throwable t) {

            }
        });
    }

    public void getCollectionStore(int pageNum){
        PesronnalService service= RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<ScanListBean> call=service.getCollectionStore(pageNum);
        call.enqueue(new Callback<ScanListBean>() {
            @Override
            public void onResponse(Call<ScanListBean> call, Response<ScanListBean> response) {
                if(response.body().getCode()==200) {
                    list = response.body().getData().getList();
                    orderDetailBeans1.addAll(list);
                    scanLisstAdapter = new ScanLisstAdapter(MyApplication.getInstance().mContext, orderDetailBeans1,scantype);
                    rvShop.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance().mContext));
                    rvShop.setAdapter(scanLisstAdapter);
                    scanLisstAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ScanListBean> call, Throwable t) {

            }
        });
    }
}
