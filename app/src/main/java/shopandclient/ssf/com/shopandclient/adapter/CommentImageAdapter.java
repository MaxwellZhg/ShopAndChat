package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import shopandclient.ssf.com.shopandclient.R;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/17.
 */
public class CommentImageAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Integer> resId;

    public CommentImageAdapter(Context context, ArrayList<Integer> resId) {
        this.context = context;
        this.resId = resId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_comment_img,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CommentViewHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return resId.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_image;
        public CommentViewHolder(View itemView) {
            super(itemView);
            iv_image=(ImageView)itemView.findViewById(R.id.iv_image);
        }
        public void setData(int position){
            iv_image.setImageResource(resId.get(position));
        }

    }
}
