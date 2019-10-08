package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.Friend;
import shopandclient.ssf.com.shopandclient.entity.FriendListBean;
import shopandclient.ssf.com.shopandclient.ui.FriendsCenterActivity;

import java.util.ArrayList;
import java.util.List;

public class SortAdapter extends BaseAdapter {
    private Context context;

    private List<FriendListBean.DataBean.MyFriendBean> list;

    public SortAdapter(Context context, List<FriendListBean.DataBean.MyFriendBean> list) {
        this.context = context;
        this.list = list;
    }

    public void refresh(List<FriendListBean.DataBean.MyFriendBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_contact, null);
        }
        ViewHolder holder = ViewHolder.getHolder(convertView);

        //设置数据
        FriendListBean.DataBean.MyFriendBean friend = list.get(position);
        holder.tv_name.setText(friend.getFriendName());

        String currentWord = friend.getSortLetters().charAt(0) + "";
        //获取上一个item的首字母
        if (position > 0) {
            String lastWord = list.get(position - 1).getSortLetters().charAt(0) + "";
            if (currentWord.equals(lastWord)) {
                //首字母相同
                holder.tv_first_world.setVisibility(View.GONE);
            } else {
                //首字母不同
                //由于布局是复用的，所以需要设置可见
                holder.tv_first_world.setVisibility(View.VISIBLE);
                holder.tv_first_world.setText(currentWord);
            }
        } else {
            //第一个
            holder.tv_first_world.setVisibility(View.VISIBLE);
            holder.tv_first_world.setText(currentWord);
        }
        holder.rl_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("id",list.get(position).getFriendID());
                intent.putExtra("type",2);
                intent.putExtra("state",list.get(position).getState());
                intent.putExtra("GuidNo",list.get(position).getGuidNO());
                intent.putExtra("username",list.get(position).getFriendName());
                intent.setClass(context, FriendsCenterActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        TextView tv_first_world, tv_name;
        RelativeLayout rl_content;

        //构造方法
        public ViewHolder(View convertView) {
            tv_first_world = (TextView) convertView.findViewById(R.id.tv_first_word);
            tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            rl_content=(RelativeLayout) convertView.findViewById(R.id.rl_content);
        }

        //对ViewHolder的封装
        public static ViewHolder getHolder(View convertView) {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }
}