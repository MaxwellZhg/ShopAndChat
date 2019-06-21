package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class ReteryGoodsAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<OrderDetailBean> orderDetailBeans;


    public ReteryGoodsAdapter(Context context, ArrayList<OrderDetailBean> orderDetailBeans) {
        this.context = context;
        this.orderDetailBeans = orderDetailBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_retery_goods,parent,false));
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
        public GoodsViewHolder(View itemView) {
            super(itemView);
            iv_good_in_order= (ImageView) itemView.findViewById(R.id.iv_good_in_order);
            tv_title =(TextView) itemView.findViewById(R.id.tv_title);
        }
        public void setData(int position){
            iv_good_in_order.setImageResource(orderDetailBeans.get(position).getResId());
            tv_title.setText(orderDetailBeans.get(position).getPrice());
        }
    }
}
