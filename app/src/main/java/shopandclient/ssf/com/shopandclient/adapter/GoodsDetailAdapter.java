package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.BrandDetail;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/14.
 */
public class GoodsDetailAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BrandDetail> list=new ArrayList<>();

    public GoodsDetailAdapter(Context context) {
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHodler hodler;
        if(convertView==null){
            hodler= new ViewHodler();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_shopdetail_img,parent,false);
            hodler.iv_brands_detail=(ImageView) convertView.findViewById(R.id.iv_brands_detail);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        hodler.iv_brands_detail.setImageResource(list.get(position).getResid());
        return convertView;
    }
    class ViewHodler{
        private ImageView iv_brands_detail;
    }
    public ArrayList<BrandDetail>  getList(){
        return list;
    }
    public void addList(ArrayList<BrandDetail> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
