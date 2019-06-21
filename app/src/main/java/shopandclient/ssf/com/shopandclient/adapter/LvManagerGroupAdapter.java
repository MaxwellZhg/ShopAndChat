package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/20.
 */
public class LvManagerGroupAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> strings;

    public LvManagerGroupAdapter(Context context, ArrayList<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hodler;
        if(convertView==null){
            hodler= new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_add_group_footer,parent,false);
            hodler.tv_add_group_name=(TextView) convertView.findViewById(R.id.tv_add_group_name);

            convertView.setTag(hodler);
        }else{
            hodler=(ViewHolder)convertView.getTag();
        }
        hodler.tv_add_group_name.setText(strings.get(position));
        return convertView;
    }

    class ViewHolder{
        private TextView tv_add_group_name;
    }
}
