package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.AddressBean;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.services.PesronnalService;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.SwipeMenuLayout;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/6.
 */
public class AddressAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<AddressBean.DataBean> list;
    private OnitemClick onitemClick;
    public AddressAdapter(Context context, ArrayList<AddressBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    //定义一个点击事件的接口
    public interface OnitemClick {
        void onItemClick(int position);
    }
    public void setOnitemClick(OnitemClick onitemClick){
        this.onitemClick=onitemClick;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(LayoutInflater.from(context).inflate(R.layout.item_address,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AddressViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AddressViewHolder extends RecyclerView.ViewHolder{
        private RelativeLayout rl_left;
        private TextView tv_delete;
        private TextView tv_address;
        private TextView tv_holder;
        private TextView tv_phone;
        private TextView tv_default;
        public AddressViewHolder(View itemView) {
            super(itemView);
            rl_left=(RelativeLayout)itemView.findViewById(R.id.rl_left);
            tv_delete=(TextView)itemView.findViewById(R.id.tv_delete);
            tv_address=(TextView)itemView.findViewById(R.id.tv_address);
            tv_holder=(TextView)itemView.findViewById(R.id.tv_holder);
            tv_phone=(TextView)itemView.findViewById(R.id.tv_phone);
            tv_default=(TextView)itemView.findViewById(R.id.tv_default);
        }
        public void setData(final int position) {
            tv_address.setText(list.get(position).getAddress()+list.get(position).getAddressInfo());
            tv_holder.setText(list.get(position).getUserName());
            tv_phone.setText(list.get(position).getPhone());
            if(list.get(position).getIsDefault()==1){
                tv_default.setVisibility(View.VISIBLE);
            }else{
                tv_default.setVisibility(View.INVISIBLE);
            }
            tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("ttttttttt","-------------------detele"+position);
                    deteleAddress(list.get(position).getId(),position);
                }
            });
            rl_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onitemClick!=null){
                        onitemClick.onItemClick(position);
                    }
                }
            });
        }
    }

    public void deteleAddress(int id, final int position){
        PesronnalService service = RetrofitHandle.getInstance().retrofit.create(PesronnalService.class);
        Call<PostComment> call=service.deteleAddress(id);
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    ToastUtil.showToast(MyApplication.getInstance().mContext,response.body().getResult());
                    list.remove(position);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }
}
