package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderTypeBean;
import shopandclient.ssf.com.shopandclient.ui.EnsureOrderActivity;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class AllMyOrderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderTypeBean.DataBean.ListBean> arrayLists;

    public AllMyOrderAdapter(Context context, ArrayList<OrderTypeBean.DataBean.ListBean> arrayLists) {
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
            hodler.rl_content=(LinearLayout) convertView.findViewById(R.id.rl_content);
            hodler.rv_my_all_order=(MyRecycleview) convertView.findViewById(R.id.rv_my_all_order);
            hodler.tv_order_num=(TextView) convertView.findViewById(R.id.tv_order_num);
            hodler.iv_order_cent=(ImageView) convertView.findViewById(R.id.iv_order_cent);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        AllMyOrderStoreInfoAdapter goodsAdapter=new AllMyOrderStoreInfoAdapter(context,arrayLists.get(position),arrayLists.get(position).getStoreName());
        hodler.rv_my_all_order.setLayoutManager(new LinearLayoutManager(context));
        hodler.rv_my_all_order.setAdapter(goodsAdapter);
        hodler.tv_order_num.setText("订单编号："+arrayLists.get(position).getOrderNo());
        if(position==arrayLists.size()-1){
            hodler.iv_order_cent.setVisibility(View.GONE);
        }else{
            hodler.iv_order_cent.setVisibility(View.VISIBLE);
        }
        hodler.rl_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("type",3);
                intent.putExtra("orderNo",arrayLists.get(position).getOrderNo());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(context, EnsureOrderActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHodler{
        private LinearLayout rl_content;
        private MyRecycleview rv_my_all_order;
        private TextView tv_order_num;
        private ImageView iv_order_cent;
    }
}
