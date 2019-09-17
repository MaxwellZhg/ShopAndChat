package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.entity.ShopCartBean;
import shopandclient.ssf.com.shopandclient.entity.UpdateCartNumParams;
import shopandclient.ssf.com.shopandclient.event.CartAttrEvent;
import shopandclient.ssf.com.shopandclient.event.DeteleCartEvent;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.services.PesronnalService;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class ShopCartGoodsAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ShopCartBean.DataBean.ListProBean> orderDetailBeans;
    private int pos;

    public ShopCartGoodsAdapter(Context context, ArrayList<ShopCartBean.DataBean.ListProBean> orderDetailBeans,int pos) {
        this.context = context;
        this.orderDetailBeans = orderDetailBeans;
        this.pos=pos;
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
        private TextView tv_price;
        private TextView tv_delete;
        private int a;
        private TextView tv_attrs;
        private CheckBox checkbox;
        public GoodsViewHolder(View itemView) {
            super(itemView);
            iv_good_in_order= (ImageView) itemView.findViewById(R.id.iv_good_in_order);
            tv_title =(TextView) itemView.findViewById(R.id.tv_title);
            tv_attrs=(TextView) itemView.findViewById(R.id.tv_attrs);
            tv_add =(TextView) itemView.findViewById(R.id.tv_add);
            tv_reduce =(TextView) itemView.findViewById(R.id.tv_reduce);
            et_count=(TextView)itemView.findViewById(R.id.et_count);
            tv_price=(TextView) itemView.findViewById(R.id.tv_price);
            tv_delete=(TextView) itemView.findViewById(R.id.tv_delete);
            checkbox=(CheckBox) itemView.findViewById(R.id.checkbox);
            a= Integer.valueOf(et_count.getText().toString());
        }
        public void setData(final int position) {
            tv_title.setText(orderDetailBeans.get(position).getProName());
            tv_attrs.setText("已选：" + orderDetailBeans.get(position).getL1Name() + orderDetailBeans.get(position).getL2Name());
            tv_price.setText("¥" + orderDetailBeans.get(position).getuPrice());
            et_count.setText("" + orderDetailBeans.get(position).getAmount());
            tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    a++;
                    if (a > 1) {
                        tv_reduce.setClickable(true);
                        tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    }
                    UpdateCartGoodsNum(orderDetailBeans.get(position).getId(), a, et_count);
                    orderDetailBeans.get(position).setAmount(a);
                }
            });
            tv_reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (a > 1) {
                        a--;
                    } else {
                        a = 1;
                    }
                    if (a == 1) {
                        UpdateCartGoodsNum(orderDetailBeans.get(position).getId(), 1, et_count);
                        tv_reduce.setClickable(false);
                        tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.important_instance));
                        orderDetailBeans.get(position).setAmount(1);
                    } else {
                        UpdateCartGoodsNum(orderDetailBeans.get(position).getId(), a, et_count);
                        tv_reduce.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                        orderDetailBeans.get(position).setAmount(a);
                    }
                }
            });
            tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detaleData(orderDetailBeans.get(position).getId(), position);
                }
            });
            tv_attrs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new CartAttrEvent(orderDetailBeans.get(position).getId(), orderDetailBeans.get(position).getProID(), orderDetailBeans.get(position).getAmount()));
                }
            });
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    orderDetailBeans.get(position).setChoose(b);
                }
            });
        }
    }
    public void detaleData(int id, final int position){
        PesronnalService service= RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<PostComment> call=service.deteleShopCart(id);
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    orderDetailBeans.remove(position);
                    notifyDataSetChanged();
                    EventBus.getDefault().post(new DeteleCartEvent(pos));
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    public void UpdateCartGoodsNum(int id, final int count, final TextView et_count ){
        PesronnalService service= RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<PostComment> call=service.updateShopCartNum(new UpdateCartNumParams(id,count));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    et_count.setText(String.valueOf(count));
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }
}
