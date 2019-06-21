package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/11.
 */
public class OrderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderInStoreBean> orderDetailBeans;

    public OrderAdapter(Context context, ArrayList<OrderInStoreBean> orderDetailBeans) {
        this.context = context;
        this.orderDetailBeans = orderDetailBeans;
    }

    @Override
    public int getCount() {
        return orderDetailBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return orderDetailBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHodler hodler;
        if(convertView==null){
            hodler= new ViewHodler();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_shopgoods_count,parent,false);
            hodler.rv_store=(MyRecycleview) convertView.findViewById(R.id.rv_store);
            hodler.tv_store_name=(TextView) convertView.findViewById(R.id.tv_store_name);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        OrderGoodsAdapter goodsAdapter=new OrderGoodsAdapter(context,orderDetailBeans.get(position).getOrderDetailBeans());
        hodler.rv_store.setLayoutManager(new LinearLayoutManager(context));
        hodler.rv_store.setAdapter(goodsAdapter);
        //hodler.tv_store_name.setImageResource(beans.get(position).getResId());
        hodler.tv_store_name.setText(orderDetailBeans.get(position).getStoreName());
        return convertView;
    }
    class ViewHodler{
        private MyRecycleview  rv_store;
        private TextView tv_store_name;
    }
}
