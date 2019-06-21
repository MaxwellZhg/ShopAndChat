package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.MyAllOrderBean;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class AllMyOrderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MyAllOrderBean> arrayLists;

    public AllMyOrderAdapter(Context context, ArrayList<MyAllOrderBean> arrayLists) {
        this.context = context;
        this.arrayLists = arrayLists;
    }

    @Override
    public int getCount() {
        return arrayLists.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayLists.get(position);
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_my_order_all,parent,false);
            hodler.rv_my_all_order=(MyRecycleview) convertView.findViewById(R.id.rv_my_all_order);
            hodler.tv_order_num=(TextView) convertView.findViewById(R.id.tv_order_num);
            hodler.iv_order_cent=(ImageView) convertView.findViewById(R.id.iv_order_cent);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        AllMyOrderStoreInfoAdapter goodsAdapter=new AllMyOrderStoreInfoAdapter(context,arrayLists.get(position).getList());
        hodler.rv_my_all_order.setLayoutManager(new LinearLayoutManager(context));
        hodler.rv_my_all_order.setAdapter(goodsAdapter);
        //hodler.tv_store_name.setImageResource(beans.get(position).getResId());
        hodler.tv_order_num.setText(arrayLists.get(position).getOrderNum());
        if(position==arrayLists.size()-1){
            hodler.iv_order_cent.setVisibility(View.GONE);
        }else{
            hodler.iv_order_cent.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
    class ViewHodler{
        private MyRecycleview rv_my_all_order;
        private TextView tv_order_num;
        private ImageView iv_order_cent;
    }
}
