package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.BrandDetail;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.ScanListBean;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/12.
 */
public class ScanLisstAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ScanListBean.DataBean.ListBean> brandDetails;
    private int scantype;

    public ScanLisstAdapter(Context context, ArrayList<ScanListBean.DataBean.ListBean> brandDetails,int scantype) {
        this.context = context;
        this.brandDetails = brandDetails;
        this.scantype=scantype;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_scan_goods,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GoodsViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return brandDetails.size();
    }
    class GoodsViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_goods;
        private TextView tv_title;
        private TextView tv_price_count;
        public GoodsViewHolder(View itemView) {
            super(itemView);
            iv_goods= (ImageView) itemView.findViewById(R.id.iv_goods);
            tv_title =(TextView) itemView.findViewById(R.id.tv_title);
            tv_price_count=(TextView)itemView.findViewById(R.id.tv_price_count);
        }
        public void setData(int position){
            tv_title.setText(brandDetails.get(position).getProName());
            tv_price_count.setText("¥" + brandDetails.get(position).getUprice());
        }
    }

}
