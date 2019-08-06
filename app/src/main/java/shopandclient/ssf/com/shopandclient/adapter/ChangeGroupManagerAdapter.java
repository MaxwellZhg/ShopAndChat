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
import shopandclient.ssf.com.shopandclient.entity.GroupInfoBean;

import java.util.ArrayList;

public class ChangeGroupManagerAdapter extends RecyclerView.Adapter {
    private ArrayList<GroupInfoBean.DataBean.ListBean> arrayList;
    private Context context;

    public ChangeGroupManagerAdapter(Context context, ArrayList<GroupInfoBean.DataBean.ListBean> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddGroupViewHolder(LayoutInflater.from(context).inflate(R.layout.item_change_group_manager, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AddGroupViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class AddGroupViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_friends;
        private TextView tv_name;
        private ImageView cb_into_group;

        public AddGroupViewHolder(View itemView) {
            super(itemView);
            iv_friends = (ImageView) itemView.findViewById(R.id.iv_friends);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            cb_into_group = (ImageView) itemView.findViewById(R.id.cb_into_group);
        }

        public void setData(int position) {
            tv_name.setText(arrayList.get(position).getUserName());
            if(arrayList.get(position).isChooseGroup()){
                cb_into_group.setBackground(context.getResources().getDrawable(R.drawable.icon_chat_check));
            }else{
                cb_into_group.setBackground(context.getResources().getDrawable(R.drawable.icon_caht_nocheck));
            }
           cb_into_group.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (arrayList.get(position).isChooseGroup()==false) {
                       for (int i=0;i<arrayList.size();i++){
                           arrayList.get(i).setChooseGroup(false);
                       }
                       arrayList.get(position).setChooseGroup(true);
                       cb_into_group.setBackground(context.getResources().getDrawable(R.drawable.icon_chat_check));
                   } else {
                       for (int i=0;i<arrayList.size();i++){
                           arrayList.get(i).setChooseGroup(false);
                       }
                       cb_into_group.setBackground(context.getResources().getDrawable(R.drawable.icon_caht_nocheck));
                   }
                   notifyDataSetChanged();
               }
           });
        }

    }
}
