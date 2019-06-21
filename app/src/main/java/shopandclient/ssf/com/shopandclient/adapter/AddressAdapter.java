package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.SwipeMenuLayout;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/6.
 */
public class AddressAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Integer> list;

    public AddressAdapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        this.list = list;
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
        private SwipeMenuLayout swipe;
        private TextView tv_editor;
        private TextView tv_delete;
        public AddressViewHolder(View itemView) {
            super(itemView);
            swipe=(SwipeMenuLayout)itemView.findViewById(R.id.swipe);
            tv_editor=(TextView)itemView.findViewById(R.id.tv_editor);
            tv_delete=(TextView)itemView.findViewById(R.id.tv_delete);
        }
        public void setData(final int position) {
            tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("ttttttttt","-------------------detele"+position);
                }
            });
            tv_editor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("ttttttttt","-------------------editor"+position);
                }
            });
        }
    }
}
