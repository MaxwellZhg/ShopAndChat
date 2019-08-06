package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.Attr;
import shopandclient.ssf.com.shopandclient.entity.ProductInfo;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/27.
 */
public class AttrInfoAdapter extends RecyclerView.Adapter implements AttrAdapter.OnitemClick {
    private Context context;
    private ArrayList<ProductInfo.DataBean.ProAttrTypeBean> arrayList=new ArrayList<>();
    private Attr attr;
    public AttrInfoAdapter(Context context) {
        this.context = context;
        attr=new Attr();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AttrInfoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_attr_lay,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AttrInfoViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onItemClick(int position) {
      /*  if (!arrayList.get(position).g) {
            for (int i = 0; i < attrs.size(); i++) {
                attrs.get(i).setSelect(false);
            }
            attrs.get(position).setSelect(true);
            attrAdapter.notifyDataSetChanged();
        }*/
    }

    class AttrInfoViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_goods_pro;
        private RecyclerView rv_attr;
        public AttrInfoViewHolder(View itemView) {
            super(itemView);
            tv_goods_pro=(TextView)itemView.findViewById(R.id.tv_goods_pro);
            rv_attr=(RecyclerView) itemView.findViewById(R.id.rv_attr);
        }
        public void setData(int postion){
           tv_goods_pro.setText(arrayList.get(postion).getAttrTypeName());
            rv_attr.setLayoutManager(new GridLayoutManager(context, 3));
            arrayList.get(postion).getProAttrTypeValue().get(0).setSelect(true);
            for(int i=0;i<arrayList.get(postion).getProAttrTypeValue().size();i++){
                arrayList.get(postion).getProAttrTypeValue().get(i).setPos(postion+1);
            }
            for(int i=0;i<arrayList.size();i++){
                if(i==0) {
                    attr.setAttrL1ID(arrayList.get(0).getProAttrTypeValue().get(0).getId());
                }else if(i==1){
                    attr.setAttrL2ID(arrayList.get(1).getProAttrTypeValue().get(0).getId());
                }
            }
            AttrAdapter attrAdapter = new AttrAdapter(context, arrayList.get(postion).getProAttrTypeValue(),attr);
          //  attrAdapter.setOnitemClickLintener(AttrInfoAdapter.this);
            rv_attr.addItemDecoration(new MyPaddingDecoration(context));
            if(rv_attr.getAdapter()==null) {
                rv_attr.setAdapter(attrAdapter);
            }
        }
    }

    public void setData(ArrayList<ProductInfo.DataBean.ProAttrTypeBean> arrayList){
        this.arrayList.addAll(arrayList);
        notifyDataSetChanged();
    }
    public void clearData(){
        this.arrayList.clear();
    }
}
