package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class ShopCartGoodsAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<OrderDetailBean> orderDetailBeans;


    public ShopCartGoodsAdapter(Context context, ArrayList<OrderDetailBean> orderDetailBeans) {
        this.context = context;
        this.orderDetailBeans = orderDetailBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shop_cart_goods,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GoodsViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return orderDetailBeans.size();
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_good_in_order;
        private TextView tv_title;
        private TextView tv_add;
        private TextView tv_reduce;
        private TextView et_count;
        private int a;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            iv_good_in_order= (ImageView) itemView.findViewById(R.id.iv_good_in_order);
            tv_title =(TextView) itemView.findViewById(R.id.tv_title);
            tv_add =(TextView) itemView.findViewById(R.id.tv_add);
            tv_reduce =(TextView) itemView.findViewById(R.id.tv_reduce);
            et_count=(TextView)itemView.findViewById(R.id.et_count);
            a= Integer.valueOf(et_count.getText().toString());
        }
        public void setData(int position){
            iv_good_in_order.setImageResource(orderDetailBeans.get(position).getResId());
            tv_title.setText(orderDetailBeans.get(position).getPrice());
            tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a++;
                    if(a>1){
                        tv_reduce.setClickable(true);
                        tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    }
                    et_count.setText(String.valueOf(a));
                }
            });
            tv_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(a>1) {
                        a--;
                    }else{
                        a=1;
                    }
                    if(a==1){
                        et_count.setText(String.valueOf(1));
                        tv_reduce.setClickable(false);
                        tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                    }else {
                        et_count.setText(String.valueOf(a));
                        tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    }
                }
            });
        }
    }
}
