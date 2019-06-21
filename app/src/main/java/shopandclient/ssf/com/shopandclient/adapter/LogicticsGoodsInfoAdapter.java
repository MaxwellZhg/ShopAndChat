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
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class LogicticsGoodsInfoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderInStoreBean> arrayLists;

    public LogicticsGoodsInfoAdapter(Context context, ArrayList<OrderInStoreBean> arrayLists) {
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
        LogicticViewHolder hodler;
        if(convertView==null){
            hodler= new LogicticViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_logictics_goods_info,parent,false);
            hodler.rv_store=(MyRecycleview) convertView.findViewById(R.id.rv_store);
            hodler.tv_store_name=(TextView) convertView.findViewById(R.id.tv_store_name);
            convertView.setTag(hodler);
        }else{
            hodler=(LogicticViewHolder)convertView.getTag();
        }
        ReteryGoodsAdapter goodsAdapter=new ReteryGoodsAdapter(context,arrayLists.get(position).getOrderDetailBeans());
        hodler.rv_store.setLayoutManager(new LinearLayoutManager(context));
        hodler.rv_store.setAdapter(goodsAdapter);
        //hodler.tv_store_name.setImageResource(beans.get(position).getResId());
        hodler.tv_store_name.setText(arrayLists.get(position).getStoreName());
        return convertView;
    }
    class LogicticViewHolder{
        private TextView tv_store_name;
        private MyRecycleview rv_store;
    }
}
