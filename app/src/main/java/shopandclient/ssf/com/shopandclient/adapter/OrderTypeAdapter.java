package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderTypeBean;

import java.util.ArrayList;

public class OrderTypeAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<OrderTypeBean.DataBean.ListBean.OrderInfoBean> orderDetailBeans;

    public OrderTypeAdapter(Context context, ArrayList<OrderTypeBean.DataBean.ListBean.OrderInfoBean> orderDetailBeans) {
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
        private TextView tv_attrs;
        private TextView tv_money;
        private TextView tv_count;
        public GoodsViewHolder(View itemView) {
            super(itemView);
            iv_good_in_order= (ImageView) itemView.findViewById(R.id.iv_good_in_order);
            tv_title =(TextView) itemView.findViewById(R.id.tv_title);
            tv_attrs =(TextView) itemView.findViewById(R.id.tv_attrs);
            tv_money =(TextView) itemView.findViewById(R.id.tv_money);
            tv_count =(TextView) itemView.findViewById(R.id.tv_count);
        }
        public void setData(int position){
            tv_title.setText(orderDetailBeans.get(position).getProName());
           tv_attrs.setText("已选："+orderDetailBeans.get(position).getProDetail());
            tv_money.setText("¥"+orderDetailBeans.get(position).getUprice());
            tv_count.setText("×"+orderDetailBeans.get(position).getAmount());
        }
    }
}
