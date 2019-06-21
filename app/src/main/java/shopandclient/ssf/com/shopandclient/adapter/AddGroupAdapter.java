package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/19.
 */
public class AddGroupAdapter extends RecyclerView.Adapter {
   private ArrayList<OrderDetailBean> arrayList;
   private Context context;

    public AddGroupAdapter( Context context,ArrayList<OrderDetailBean> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddGroupViewHolder(LayoutInflater.from(context).inflate(R.layout.item_group_chat,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AddGroupViewHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class AddGroupViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_friends;
        public AddGroupViewHolder(View itemView) {
            super(itemView);
            iv_friends=(ImageView)itemView.findViewById(R.id.iv_friends);
        }
        public void setData(int position){
            iv_friends.setImageResource(arrayList.get(position).getResId());
        }

    }
}
