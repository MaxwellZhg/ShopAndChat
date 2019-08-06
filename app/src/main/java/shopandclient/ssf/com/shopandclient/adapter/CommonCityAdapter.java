package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.LocalUserBean;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.StreetInfoBean;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/19.
 */
public class CommonCityAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<LocalUserBean.DataBean.ListBean> beans=new ArrayList<>();
    private onItemFriendClick onItemFriendClick;
    public CommonCityAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemFriendClick(onItemFriendClick onItemFriendClick){
        this.onItemFriendClick=onItemFriendClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonCityViewHolder(LayoutInflater.from(context).inflate(R.layout.item_common_city,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CommonCityViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    class CommonCityViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_host_name;
        private LinearLayout ll_content;
        public CommonCityViewHolder(View itemView) {
            super(itemView);
            tv_host_name=(TextView)itemView.findViewById(R.id.tv_host_name);
            ll_content=(LinearLayout)itemView.findViewById(R.id.ll_content);
        }
        public void setData(int position){
           tv_host_name.setText(beans.get(position).getUserName());
           ll_content.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(onItemFriendClick!=null){
                       onItemFriendClick.fiendClick();
                   }
               }
           });
        }
    }
    public interface onItemFriendClick{
        void fiendClick();
    }
    public void addData(ArrayList<LocalUserBean.DataBean.ListBean> beans){
        this.beans.addAll(beans);
        notifyDataSetChanged();
    }

    public void clearData(){
        this.beans.clear();
    }
}
