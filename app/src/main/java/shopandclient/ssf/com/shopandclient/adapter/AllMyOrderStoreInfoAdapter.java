package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.entity.OrderTypeBean;
import shopandclient.ssf.com.shopandclient.entity.PostComfrimCartParams;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.services.PesronnalService;
import shopandclient.ssf.com.shopandclient.ui.ReteryActivty;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class AllMyOrderStoreInfoAdapter extends RecyclerView.Adapter {
    private Context context;
    private OrderTypeBean.DataBean.ListBean list;
    private String storename;
    public AllMyOrderStoreInfoAdapter(Context context, OrderTypeBean.DataBean.ListBean list,String storename) {
        this.context = context;
        this.list = list;
        this.storename=storename;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderStoreViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my_order_store_all,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OrderStoreViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    class OrderStoreViewHolder extends RecyclerView.ViewHolder{
        private MyRecycleview rv_shop;
        private TextView tv_store_name;
        private ImageView iv_cent;
        private final TextView limmit_pay;
        private final TextView tv_retery;
        public OrderStoreViewHolder(View itemView) {
            super(itemView);
            rv_shop= (MyRecycleview) itemView.findViewById(R.id.rv_shop);
            tv_store_name =(TextView) itemView.findViewById(R.id.tv_store_name);
            iv_cent=(ImageView)itemView.findViewById(R.id.iv_cent);
            limmit_pay = (TextView)itemView.findViewById(R.id.limmit_pay);
            tv_retery=(TextView)itemView.findViewById(R.id.tv_retery);
        }
        public void setData(int position){
            tv_store_name.setText(storename);
            if(list.getOrderState()==0){
                tv_retery.setVisibility(View.GONE);
                limmit_pay.setBackground(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.item_gotopay_bg));
                limmit_pay.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
                limmit_pay.setText("去支付");
            }else if(list.getOrderState()==1){
                tv_retery.setVisibility(View.GONE);
                limmit_pay.setBackground(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.item_default_gotopay_bg));
                limmit_pay.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                limmit_pay.setText("退款/退货");
            }else if(list.getOrderState()==2){
                tv_retery.setVisibility(View.GONE);
                limmit_pay.setBackground(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.item_default_gotopay_bg));
                limmit_pay.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                limmit_pay.setText("退款/退货");
            }else if(list.getOrderState()==3){
                tv_retery.setVisibility(View.VISIBLE);
                limmit_pay.setBackground(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.item_default_gotopay_bg));
                limmit_pay.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                limmit_pay.setText("退款/退货");
            }
            if(rv_shop.getAdapter()==null){
                OrderTypeAdapter goodsAdapter=new OrderTypeAdapter(context,list.getOrderInfo());
                rv_shop.setLayoutManager(new LinearLayoutManager(context));
                rv_shop.setAdapter(goodsAdapter);
            }
            limmit_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(list.getOrderState()==0){
                        gotoPay(list.getOrderNo());
                    }else if(list.getOrderState()==1){
                        gotoRetry(list.getOrderNo());
                    }
                }
            });
        }
    }

    private void gotoRetry(String orderNo) {
        Intent intent=new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, ReteryActivty.class);
        intent.putExtra("orderNo",orderNo);
        context.startActivity(intent);
    }

    private void gotoPay(String orderNo) {
        PesronnalService service= RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<PostComment> call=service.postOrdertoPay(new PostComfrimCartParams(orderNo));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    list.setOrderState(1);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }
}
