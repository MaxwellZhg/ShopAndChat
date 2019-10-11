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
import shopandclient.ssf.com.shopandclient.util.UpdataDataInfoManager;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class ShopCartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShopCartBean.DataBean> orderDetailBeans=new ArrayList<>();
    private GotoEnsureOrderListener onGotoEnsureOrderListener;
    private ShopCartGoodsAdapter goodsAdapter;
    private UpdataDataInfoManager manager;

    public ShopCartAdapter(Context context) {
        this.context = context;
        manager=new UpdataDataInfoManager();
    }

    public void setOnGotoEnsureOrderListener(GotoEnsureOrderListener onGotoEnsureOrderListener) {
        this.onGotoEnsureOrderListener = onGotoEnsureOrderListener;
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
        goodsAdapter = new ShopCartGoodsAdapter(context,orderDetailBeans.get(position).getListPro(),position);
        manager.registerObserver(goodsAdapter);
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
               /*    Intent intent=new Intent();
                   intent.putExtra("str",str);
                   intent.putExtra("type",2);
                   intent.setClass(context, EnsureOrderActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(intent);*/
               if(onGotoEnsureOrderListener!=null){
                   onGotoEnsureOrderListener.gotoEnsureOrder(str,2);
                   }
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
    public interface GotoEnsureOrderListener{
       public void gotoEnsureOrder(String str,int type);
    }

    public void clearData(){
        orderDetailBeans.clear();
    }

    public void addData(ArrayList<ShopCartBean.DataBean> orderDetailBeans){
        this.orderDetailBeans.addAll(orderDetailBeans);
        notifyDataSetChanged();
        manager.notifyAllObservers();
    }
   public ArrayList<ShopCartBean.DataBean> getData(){
        return orderDetailBeans;
     }
}
