package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.Brands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/5/30.
 */
public class CategoryAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Brands> brands;
    private OnitemClick onitemClick;   //定义点击事件接口
    public CategoryAdapter(Context context, ArrayList<Brands> brands) {
        this.context=context;
        this.brands=brands;
    }
    //定义设置点击事件监听的方法
    public void setOnitemClickLintener (OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }
    //定义一个点击事件的接口
    public interface OnitemClick {
        void onItemClick(int position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BrandsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(brands.get(position).getType()==2) {
            ((BrandsViewHolder) holder). tv_category.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
            ((BrandsViewHolder) holder). iv_cent.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        }else{
             ((BrandsViewHolder) holder).tv_category.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
            ((BrandsViewHolder) holder).iv_cent.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        }
        ((BrandsViewHolder) holder).tv_category.setText(brands.get(position).getName());
        if (onitemClick != null) {
            ((BrandsViewHolder) holder).tv_category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //在TextView的地方进行监听点击事件，并且实现接口
                    onitemClick.onItemClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return brands.size();
    }
    class BrandsViewHolder extends RecyclerView.ViewHolder{
        private  TextView tv_category;
        private ImageView iv_cent;
        public BrandsViewHolder(View itemView) {
            super(itemView);
            tv_category= (TextView) itemView.findViewById(R.id.tv_category);
            iv_cent= (ImageView) itemView.findViewById(R.id.iv_cent);
        }
    }
}
