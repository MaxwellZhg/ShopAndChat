package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lzy.imagepicker.bean.ImageItem;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.GroupInfoBean;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.ui.AddGroupChatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/6/20.
 */
public class GroupMemberAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<GroupInfoBean.DataBean.ListBean> mData;
    private OnitemClick onitemClick;
    public GroupMemberAdapter(Context context) {
        this.context = context;
    }

    //定义设置点击事件监听的方法
    public void setOnitemClickLintener(OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    //定义一个点击事件的接口
    public interface OnitemClick {
        void onItemClick(int position);
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
             iv_add_friends.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent=new Intent();
                     intent.putExtra("type",2);
                     intent.putExtra("groupId",mData.get(0).getGroupID());
                     intent.setClass(context, AddGroupChatActivity.class);
                     context.startActivity(intent);
                 }
             });
         }else {
             if(mData.get(position).getIfForbid()==1) {
                 iv_group_member_img.setVisibility(View.VISIBLE);
                 iv_unlock.setVisibility(View.VISIBLE);
                 tv_member_name.setVisibility(View.VISIBLE);
                 iv_add_friends.setVisibility(View.INVISIBLE);
                 tv_member_name.setText(mData.get(position).getUserName());
             }else{
                 iv_group_member_img.setVisibility(View.VISIBLE);
                 iv_unlock.setVisibility(View.INVISIBLE);
                 tv_member_name.setVisibility(View.VISIBLE);
                 iv_add_friends.setVisibility(View.INVISIBLE);
                 tv_member_name.setText(mData.get(position).getUserName());
             }
             iv_group_member_img.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if(onitemClick!=null){
                         onitemClick.onItemClick(position);
                     }
                 }
             });
         }
       }
   }

    public void setImages(ArrayList<GroupInfoBean.DataBean.ListBean> data) {
        mData = new ArrayList<>(data);
        mData.add(new GroupInfoBean.DataBean.ListBean());
        notifyDataSetChanged();
    }
}
