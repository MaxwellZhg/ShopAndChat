package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.OrderInStoreBean;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/18.
 */
public class EnsureOrderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderInStoreBean> arrayList;

    public EnsureOrderAdapter(Context context, ArrayList<OrderInStoreBean> arrayList) {
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_ensure_order,parent,false);
            hodler.rv_store=(MyRecycleview) convertView.findViewById(R.id.rv_store);
            hodler.tv_store_name=(TextView) convertView.findViewById(R.id.tv_store_name);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHodler)convertView.getTag();
        }
        ReteryGoodsAdapter goodsAdapter=new ReteryGoodsAdapter(context,arrayList.get(position).getOrderDetailBeans());
        hodler.rv_store.setLayoutManager(new LinearLayoutManager(context));
        hodler.rv_store.setAdapter(goodsAdapter);
        //hodler.tv_store_name.setImageResource(beans.get(position).getResId());
        hodler.tv_store_name.setText(arrayList.get(position).getStoreName());
        return convertView;
    }

    class ViewHodler {
        private TextView tv_store_name;
        private MyRecycleview rv_store;
    }

   /* private void initNoLinkOptionsPicker() {

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                final String tx = reason.get(options1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_choose_retery_reason.setText(tx);
                        tv_choose_retery_reason.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    }
                });
            }
        })
                .setTitleText("退款类型")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
                .setCancelColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
                .setSubmitColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips))
                .setTextColorCenter(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOutSideColor(0x00000000) //设置外部遮罩颜色
                .setOutSideCancelable(false)
                .build();
        pvOptions.setPicker(reason);//二级选择器



    }
    private void getNoLinkData() {
        reason.add("免邮");
        reason.add("EMS");
        reason.add("四通一达");
    }*/
}
