package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.SearchFriend;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/20.
 */
public class AddFriendsAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<SearchFriend.DataBean> arrayList;
    private OnitemClick onitemClick;
    public AddFriendsAdapter(Context context, ArrayList<SearchFriend.DataBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
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
        return new AddFriendsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_add_friends,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AddFriendsViewHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class AddFriendsViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_add_friends_name;
        private RelativeLayout rl_search_user;
        public AddFriendsViewHolder(View itemView) {
            super(itemView);
            tv_add_friends_name=(TextView)itemView.findViewById(R.id.tv_add_friends_name);
            rl_search_user=(RelativeLayout)itemView.findViewById(R.id.rl_search_user);
        }
        public void setData(final int position){
            tv_add_friends_name.setText(arrayList.get(position).getUserName());
            rl_search_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onitemClick!=null){
                        onitemClick.onItemClick(position);
                    }
                }
            });
        }
    }
}
