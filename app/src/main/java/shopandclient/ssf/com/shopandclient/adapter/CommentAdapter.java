package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.Moment;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/14.
 */
public class CommentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Moment> list;

    public CommentAdapter(Context context, ArrayList<Moment> list) {
        this.context = context;
        this.list = list;
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_list_comment,parent,false);
            hodler.rv_show_img=(MyRecycleview) convertView.findViewById(R.id.rv_show_img);
            hodler.tv_host=(TextView) convertView.findViewById(R.id.tv_host);
            hodler.iv_host=(ImageView) convertView.findViewById(R.id.iv_host);
            hodler.comment_list=(LinearLayout) convertView.findViewById(R.id.comment_list);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
    /*    AllMyOrderStoreInfoAdapter goodsAdapter=new AllMyOrderStoreInfoAdapter(context,arrayLists.get(position).getList());
        hodler.rv_my_all_order.setLayoutManager(new LinearLayoutManager(context));
        hodler.rv_my_all_order.setAdapter(goodsAdapter);
        //hodler.tv_store_name.setImageResource(beans.get(position).getResId());
        hodler.tv_order_num.setText(arrayLists.get(position).getOrderNum());
        if(position==arrayLists.size()-1){
            hodler.iv_order_cent.setVisibility(View.GONE);
        }else{
            hodler.iv_order_cent.setVisibility(View.VISIBLE);
        }*/
        return convertView;
    }
    class ViewHodler{
        private MyRecycleview rv_show_img;
        private LinearLayout comment_list;
        private TextView tv_host;
        private ImageView iv_host;
    }
}
