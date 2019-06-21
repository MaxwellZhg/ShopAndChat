package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class AllMyOrderStoreInfoAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<OrderInStoreBean> list;

    public AllMyOrderStoreInfoAdapter(Context context, ArrayList<OrderInStoreBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderStoreViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my_order_store_all,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OrderStoreViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class OrderStoreViewHolder extends RecyclerView.ViewHolder{
        private MyRecycleview rv_shop;
        private TextView tv_store_name;
        private ImageView iv_cent;
        public OrderStoreViewHolder(View itemView) {
            super(itemView);
            rv_shop= (MyRecycleview) itemView.findViewById(R.id.rv_shop);
            tv_store_name =(TextView) itemView.findViewById(R.id.tv_store_name);
            iv_cent=(ImageView)itemView.findViewById(R.id.iv_cent);
        }
        public void setData(int position){
            tv_store_name.setText(list.get(position).getStoreName());
            if(position==list.size()-1){
                iv_cent.setVisibility(View.GONE);
            }
            if(rv_shop.getAdapter()==null){
               ReteryGoodsAdapter goodsAdapter=new ReteryGoodsAdapter(context,list.get(position).getOrderDetailBeans());
                rv_shop.setLayoutManager(new LinearLayoutManager(context));
                rv_shop.setAdapter(goodsAdapter);
            }
        }
    }
}
