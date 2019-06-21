package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.StreetBean;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/5.
 */
public class StreetAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<StreetBean> beans;

    public StreetAdapter(Context context, ArrayList<StreetBean> beans) {
        this.context = context;
        this.beans = beans;
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_street_shop,parent,false);
            hodler.iv_street_brands=(ImageView) convertView.findViewById(R.id.iv_street_brands);
            hodler.tv_brands_name=(TextView) convertView.findViewById(R.id.tv_brands_name);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        hodler.iv_street_brands.setImageResource(beans.get(position).getResId());
        hodler.tv_brands_name.setText(beans.get(position).getName());
        return convertView;
    }
    class ViewHodler{
        private ImageView iv_street_brands;
        private TextView tv_brands_name;
    }
}
