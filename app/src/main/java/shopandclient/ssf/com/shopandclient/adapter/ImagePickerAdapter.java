package shopandclient.ssf.com.shopandclient.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.ui.DispatchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhg on 2019/6/17.
 */
public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.SelectedPicViewHolder> {
    private int maxImgCount;
    private Context mContext;
    private List<ImageItem> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private OnDeteleCancelClick onclicker;
    private boolean isAdded;   //是否额外添加了最后一个图片

    public ImagePickerAdapter(int maxImgCount, Context mContext, List<ImageItem> data) {
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data);
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position,int size);
    }
    public interface OnDeteleCancelClick{
        void onCancelClick(View v,int position,int max);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }
    public void setOnCancelClickListener(OnDeteleCancelClick onclicker) {
        this.onclicker = onclicker;
    }
    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_image,parent,false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, int position) {
             holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int clickPosition;
        private ImageView iv_image;
        private ImageView iv_detele;
        private ImageView iv_up_photo;
        private TextView tv_page_count;
        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            iv_image=(ImageView)itemView.findViewById(R.id.iv_image);
            iv_detele=(ImageView)itemView.findViewById(R.id.iv_detele);
            iv_up_photo=(ImageView)itemView.findViewById(R.id.iv_up_photo);
            tv_page_count=(TextView)itemView.findViewById(R.id.tv_page_count);
        }
        public void bind(final int position) {
            //设置条目的点击事件
            itemView.setOnClickListener(this);
            //根据条目位置设置图片
            ImageItem item = mData.get(position);
            if (isAdded && position == getItemCount() - 1) {
                if(position==0) {
                    iv_up_photo.setImageResource(R.drawable.image_order_uploadphotos);
                    iv_up_photo.setVisibility(View.VISIBLE);
                    tv_page_count.setVisibility(View.INVISIBLE);
                    iv_detele.setVisibility(View.INVISIBLE);
                    iv_image.setVisibility(View.INVISIBLE);
                }else{
                    iv_up_photo.setImageResource(R.drawable.image_order_uploadphoto);
                    iv_up_photo.setVisibility(View.VISIBLE);
                    tv_page_count.setVisibility(View.VISIBLE);
                    tv_page_count.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.page_count, getItemCount()-1, maxImgCount));
                    iv_detele.setVisibility(View.INVISIBLE);
                    iv_image.setVisibility(View.INVISIBLE);
                }
                clickPosition = DispatchActivity.IMAGE_ITEM_ADD;
            } else {
                iv_up_photo.setVisibility(View.INVISIBLE);
                tv_page_count.setVisibility(View.INVISIBLE);
                iv_detele.setVisibility(View.VISIBLE);
                iv_image.setVisibility(View.VISIBLE);
                iv_detele.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mData.remove(position);
                        onclicker.onCancelClick(v,position,mData.size()-1);

                        notifyDataSetChanged();
                    }
                });
                ImagePicker.getInstance().getImageLoader().displayImage((Activity) mContext, item.path, iv_image, 0, 0);
                clickPosition = position;
            }
        }
        @Override
        public void onClick(View v) {
            if (listener != null) listener.onItemClick(v, clickPosition,mData.size());
        }
    }

    public void setImages(List<ImageItem> data) {
        mData = new ArrayList<>(data);

        if (getItemCount() <= maxImgCount) {
            mData.add(new ImageItem());
            isAdded = true;
        }
        notifyDataSetChanged();
    }
}
