package shopandclient.ssf.com.shopandclient.ui.fragment;

import android.widget.ListView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.GoodsDetailAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseFragment;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.BrandDetail;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/14.
 */
public class GoodDetailFragment extends BaseFragment implements BaseBiz {
    ArrayList<BrandDetail> shopdetails = new ArrayList<>();
    private ListView list;
    private GoodsDetailAdapter goodsDetailAdapter;

    @Override
    protected int getLayoutResouceId() {
        return R.layout.item_goods_detail;
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
        list = (ListView) findViewById(R.id.list);
        shopdetails.add(new BrandDetail(R.drawable.meinv1, "苍井空"));
        shopdetails.add(new BrandDetail(R.drawable.meinv2, "苍井空1"));
        shopdetails.add(new BrandDetail(R.drawable.meinv3, "苍井空2"));
        goodsDetailAdapter = new GoodsDetailAdapter(MyApplication.getInstance().mContext);
        if(goodsDetailAdapter.getList().size()==0) {
            goodsDetailAdapter.addList(shopdetails);
            list.setAdapter(goodsDetailAdapter);
        }

    }
}
