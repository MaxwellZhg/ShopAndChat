package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.LimmitBuyBean;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailInfoBean;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

public class OrderDetailAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderDetailInfoBean.DataBean.OrderDetailBean> arrayList;

    public OrderDetailAdapter(Context context, ArrayList<OrderDetailInfoBean.DataBean.OrderDetailBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_ensure_order,parent,false);
            hodler.rv_store=(MyRecycleview) convertView.findViewById(R.id.rv_store);
            hodler.tv_store_name=(TextView) convertView.findViewById(R.id.tv_store_name);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        OrderInfoGoodsAdapter goodsAdapter=new OrderInfoGoodsAdapter(context,arrayList.get(position).getOrderInfo());
        hodler.rv_store.setLayoutManager(new LinearLayoutManager(context));
        hodler.rv_store.setAdapter(goodsAdapter);
        //hodler.tv_store_name.setImageResource(beans.get(position).getResId());
        hodler.tv_store_name.setText(arrayList.get(position).getStoreName());
        return convertView;
    }

    class ViewHodler {
        private TextView tv_store_name;
        private MyRecycleview rv_store;
    }
}
