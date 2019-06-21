package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.BrandDetail;
import shopandclient.ssf.com.shopandclient.entity.BrandsDetail;
import shopandclient.ssf.com.shopandclient.weiget.bananer.Banner;
import shopandclient.ssf.com.shopandclient.weiget.bananer.GlideImageLoader;

/**
 * Created by zhg on 2019/6/3.
 */
public class ShopDetailAdapter extends RecyclerView.Adapter {
    private Context context;
    private BrandsDetail brandsDetail;
    private int ITME_HEAD=0xa0;
    private int ITME_NORMAL=0xa1;
    public ShopDetailAdapter(Context context, BrandsDetail brandsDetail) {
        this.context = context;
        this.brandsDetail = brandsDetail;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if(viewType==ITME_HEAD){
           return new BrandsHeaderHolder(LayoutInflater.from(context).inflate(R.layout.item_brands_detail_header,parent,false));
       }else{
           return new BrandsDetailHolder(LayoutInflater.from(context).inflate(R.layout.item_shopdetail_img,parent,false));
       }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if(getItemViewType(position)==ITME_HEAD){
           ((BrandsHeaderHolder) holder).setData(position);
       }else{
           ((BrandsDetailHolder) holder).imageView.setImageResource(brandsDetail.getMdrawlist().get(position-1).getResid());
       }
    }

    @Override
    public int getItemCount() {
        return brandsDetail.getMdrawlist().size()+1;
    }

    @Override
    public int getItemViewType(int position) {
       if(position==0){
           return ITME_HEAD;
       }else{
           return ITME_NORMAL;
       }
    }

    class BrandsHeaderHolder extends RecyclerView.ViewHolder{
         private Banner banner;
         private GlideImageLoader glideImageLoader;
         private TextView tv_count_page;
        public BrandsHeaderHolder(View itemView) {
            super(itemView);
             banner= (Banner) itemView.findViewById(R.id.banner);
             tv_count_page =(TextView) itemView.findViewById(R.id.tv_count_page);
             glideImageLoader = new GlideImageLoader();

        }
        public void setData(int position){
            banner.setImages(brandsDetail.getBanalist()).setImageLoader(glideImageLoader).start();
            banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                   // Log.e("tttttttt","--------------"+position+1);
                    int current=position+1;
                    tv_count_page.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.page_count,current,brandsDetail.getBanalist().size()));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    class BrandsDetailHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public BrandsDetailHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_brands_detail);
        }
        public void setData(int position){

        }
    }
}
