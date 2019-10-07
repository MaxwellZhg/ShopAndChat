package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.StreetInfoBean;

import java.util.ArrayList;

public class StreetInfoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<StreetInfoBean.DataBean.ListBean> beans=new ArrayList<>();

    public StreetInfoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHodler hodler;
        if(convertView==null){
            hodler=new ViewHodler();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_street_shop,parent,false);
            hodler.iv_street_brands=(ImageView) convertView.findViewById(R.id.iv_street_brands);
            hodler.tv_brands_name=(TextView) convertView.findViewById(R.id.tv_brands_name);
            hodler.tv_price=(TextView) convertView.findViewById(R.id.tv_price);
            hodler.tv_sale_count=(TextView) convertView.findViewById(R.id.tv_sale_count);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        // hodler.iv_street_brands.setImageResource(beans.get(position).get);
        hodler.tv_brands_name.setText(beans.get(position).getProName());
        hodler.tv_price.setText("¥"+beans.get(position).getPrice());
        hodler.tv_sale_count.setText("已售"+beans.get(position).getNum()+"件");
        Glide.with(context).load(beans.get(position).getImg()).into(hodler.iv_street_brands);
        return convertView;
    }
    class ViewHodler{
        private ImageView iv_street_brands;
        private TextView tv_brands_name;
        private TextView tv_price;
        private TextView tv_sale_count;
    }
    public void addData(ArrayList<StreetInfoBean.DataBean.ListBean> beans){
        this.beans.addAll(beans);
        notifyDataSetChanged();
    }

    public void clearData(){
        this.beans.clear();
    }

}
