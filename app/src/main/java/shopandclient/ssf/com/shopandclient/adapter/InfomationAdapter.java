package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/20.
 */
public class InfomationAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<OrderDetailBean> arrayList;

    public InfomationAdapter(Context context, ArrayList<OrderDetailBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_info,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((InfoViewHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class InfoViewHolder extends RecyclerView.ViewHolder{
         private TextView tv_host_name;
        public InfoViewHolder(View itemView) {
            super(itemView);
            tv_host_name= itemView.findViewById(R.id.tv_host_name);
        }
        public void setData(int position){
            tv_host_name.setText(arrayList.get(position).getPrice());
        }
    }
}
