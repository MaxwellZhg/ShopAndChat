package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lzy.imagepicker.bean.ImageItem;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/6/20.
 */
public class GroupMemberAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<OrderDetailBean> mData;
    public GroupMemberAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupMemberViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_groud,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GroupMemberViewHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
   class GroupMemberViewHolder extends RecyclerView.ViewHolder{
       private ImageView iv_group_member_img;
       private TextView tv_member_name;
       private ImageView iv_unlock;
       private ImageView iv_add_friends;
       public GroupMemberViewHolder(View itemView) {
           super(itemView);
           iv_group_member_img=(ImageView)itemView.findViewById(R.id.iv_group_member_img);
           tv_member_name=(TextView)itemView.findViewById(R.id.tv_member_name);
           iv_unlock=(ImageView)itemView.findViewById(R.id.iv_unlock);
           iv_add_friends=(ImageView)itemView.findViewById(R.id.iv_add_friends);
       }

       public void setData(int position){
         if(position==mData.size()-1){
             iv_group_member_img.setVisibility(View.INVISIBLE);
             iv_unlock.setVisibility(View.INVISIBLE);
             tv_member_name.setVisibility(View.INVISIBLE);
             iv_add_friends.setVisibility(View.VISIBLE);
         }else if(position==mData.size()-2){
             iv_group_member_img.setVisibility(View.VISIBLE);
             iv_unlock.setVisibility(View.VISIBLE);
             tv_member_name.setVisibility(View.VISIBLE);
             iv_add_friends.setVisibility(View.INVISIBLE);
             iv_group_member_img.setImageResource(mData.get(position).getResId());
             tv_member_name.setText(mData.get(position).getPrice());
         }else{
             iv_group_member_img.setVisibility(View.VISIBLE);
             iv_unlock.setVisibility(View.INVISIBLE);
             tv_member_name.setVisibility(View.VISIBLE);
             iv_add_friends.setVisibility(View.INVISIBLE);
             iv_group_member_img.setImageResource(mData.get(position).getResId());
             tv_member_name.setText(mData.get(position).getPrice());
         }
       }
   }

    public void setImages(ArrayList<OrderDetailBean> data) {
        mData = new ArrayList<>(data);
        mData.add(new OrderDetailBean());
        notifyDataSetChanged();
    }
}
