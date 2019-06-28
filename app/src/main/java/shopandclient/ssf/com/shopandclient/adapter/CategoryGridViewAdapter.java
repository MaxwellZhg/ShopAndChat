package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.BrandDetail;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/5/31.
 */
public class CategoryGridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BrandDetail> brandDetails =new ArrayList<>();

    public CategoryGridViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return brandDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return brandDetails.get(position);
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_category_detail,parent,false);
            hodler.iv_goods=(ImageView) convertView.findViewById(R.id.iv_goods);
            hodler.tv_brands_name=(TextView) convertView.findViewById(R.id.tv_brands_name);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        hodler.iv_goods.setImageResource(brandDetails.get(position).getResid());
        hodler.tv_brands_name.setText(brandDetails.get(position).getBrandsname());
        return convertView;
    }
    class ViewHodler{
        private ImageView iv_goods;
        private TextView tv_brands_name;
    }

    public void getData(ArrayList<BrandDetail> brandDetails){
        this.brandDetails.addAll(brandDetails);
    }
    public void clearData(){
        brandDetails.clear();
    }
}
