package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.CatetogryBrandName;
import shopandclient.ssf.com.shopandclient.entity.ProductListBean;

import javax.sql.DataSource;
import java.util.ArrayList;

import static shopandclient.ssf.com.shopandclient.im.ui.CallActivity.TAG;

/**
 * Created by zhg on 2019/5/31.
 */
public class CategoryNameAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ProductListBean.DataBean.ListBean> catetogryBrandNames;
    private CategoryAdapter.OnitemClick onitemClick;   //定义点击事件接口
    public CategoryNameAdapter(Context context, ArrayList<ProductListBean.DataBean.ListBean> catetogryBrandNames) {
        this.context = context;
        this.catetogryBrandNames = catetogryBrandNames;
    }
    //定义设置点击事件监听的方法
    public void setOnitemClickLintener (CategoryAdapter.OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }
    //定义一个点击事件的接口
    public interface OnitemClick {
        void onItemClick(int position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BrandsNameViewHolder(LayoutInflater.from(context).inflate(R.layout.item_brands_name,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BrandsNameViewHolder) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return catetogryBrandNames.size();
    }
    class BrandsNameViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_brands_title;
        private TextView tv_brands_tips;
        private ImageView img_brands;
        private RelativeLayout rl_category;
        private TextView tv_price;
        private TextView tv_orginal_price;
        private RequestListener mRequestListener;

        public BrandsNameViewHolder(View itemView) {
            super(itemView);
            tv_brands_title= (TextView) itemView.findViewById(R.id.tv_brands_title);
            img_brands= (ImageView) itemView.findViewById(R.id.img_brands);
            tv_brands_tips=(TextView)itemView.findViewById(R.id.tv_brands_tips);
            rl_category=(RelativeLayout)itemView.findViewById(R.id.rl_category);
            tv_price=(TextView)itemView.findViewById(R.id.tv_price);
            tv_orginal_price=(TextView)itemView.findViewById(R.id.tv_orginal_price);
        }
        public void setData(final int position){
            Glide.with(context).load(catetogryBrandNames.get(position).getImg()).placeholder(R.drawable.meinv).into(img_brands);
            tv_price.setText("¥ "+catetogryBrandNames.get(position).getPrice());
            tv_brands_title.setText(catetogryBrandNames.get(position).getProName());
            tv_brands_tips.setText(catetogryBrandNames.get(position).getRemark());
            if (onitemClick != null) {
                rl_category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       onitemClick.onItemClick(position);
                    }
                });
            }

        }

    }
}
