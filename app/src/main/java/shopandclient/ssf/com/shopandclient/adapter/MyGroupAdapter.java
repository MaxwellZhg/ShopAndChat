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
import shopandclient.ssf.com.shopandclient.entity.MyGroupBean;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

public class MyGroupAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MyGroupBean.DataBean> arrayList;

    public MyGroupAdapter(Context context, ArrayList<MyGroupBean.DataBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_group_show,parent,false);
            hodler.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
       hodler.tv_name.setText(arrayList.get(position).getGroupName());
        return convertView;
    }
    class ViewHodler{
        private TextView tv_name;

    }
}
