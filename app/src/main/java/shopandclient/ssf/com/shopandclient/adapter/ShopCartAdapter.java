package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.entity.ShopCartBean;
import shopandclient.ssf.com.shopandclient.ui.EnsureOrderActivity;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class ShopCartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShopCartBean.DataBean> orderDetailBeans;

    public ShopCartAdapter(Context context, ArrayList<ShopCartBean.DataBean> orderDetailBeans) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHodler hodler;
        if(convertView==null){
            hodler= new ViewHodler();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_shop_cart,parent,false);
            hodler.rv_shop=(MyRecycleview) convertView.findViewById(R.id.rv_shop);
            hodler.tv_store_name=(TextView) convertView.findViewById(R.id.tv_store_name);
            hodler.limmit_pay=(TextView) convertView.findViewById(R.id.limmit_pay);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
       ShopCartGoodsAdapter goodsAdapter=new ShopCartGoodsAdapter(context,orderDetailBeans.get(position).getListPro(),position);
        hodler.rv_shop.setLayoutManager(new LinearLayoutManager(context));
        hodler.rv_shop.setAdapter(goodsAdapter);
        //hodler.tv_store_name.setImageResource(beans.get(position).getResId());
        hodler.tv_store_name.setText(orderDetailBeans.get(position).getStoreName());
        hodler.limmit_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str="";
               for(int i=0;i<orderDetailBeans.get(position).getListPro().size();i++){
                   if(orderDetailBeans.get(position).getListPro().get(i).isChoose()){
                       str=str+orderDetailBeans.get(position).getListPro().get(i).getId()+",";
                   }
               }
               if(str.equals("")){
                   ToastUtil.showToast(context,"请选择商品");
               }else{
                   Intent intent=new Intent();
                   intent.putExtra("str",str);
                   intent.putExtra("type",2);
                   intent.setClass(context, EnsureOrderActivity.class);
                   context.startActivity(intent);
               }
            }
        });
        return convertView;
    }
    class ViewHodler{
        private MyRecycleview rv_shop;
        private TextView tv_store_name;
        private TextView limmit_pay;
    }
}
