package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.FriendListBean;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/19.
 */
public class AddGroupAdapter extends RecyclerView.Adapter {
   private ArrayList<FriendListBean.DataBean.MyFriendBean > arrayList;
   private Context context;

    public AddGroupAdapter( Context context,ArrayList<FriendListBean.DataBean.MyFriendBean > arrayList) {
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
        private TextView tv_name;
        private CheckBox cb_into_group;
        public AddGroupViewHolder(View itemView) {
            super(itemView);
             iv_friends=(ImageView)itemView.findViewById(R.id.iv_friends);
             tv_name=(TextView) itemView.findViewById(R.id.tv_name);
             cb_into_group=(CheckBox)itemView.findViewById(R.id.cb_into_group);
        }
        public void setData(int position){
          tv_name.setText(arrayList.get(position).getFriendName());
          cb_into_group.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  if(isChecked){
                      arrayList.get(position).setChooseGroup(true);
                  }else{
                      arrayList.get(position).setChooseGroup(false);
                  }
              }
          });
        }

    }
}
