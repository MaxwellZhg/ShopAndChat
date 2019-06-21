package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.CommentStarBean;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/17.
 */
public class CommentStarAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<CommentStarBean> arrayList;
    private OnitemClick onitemClick;
    public CommentStarAdapter(Context context, ArrayList<CommentStarBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentStarViewHolder(LayoutInflater.from(context).inflate(R.layout.item_common_star,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CommentStarViewHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CommentStarViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_star;
        public CommentStarViewHolder(View itemView) {
            super(itemView);
            iv_star=(ImageView) itemView.findViewById(R.id.iv_star);
        }
        public void setData(final int position){
            if(arrayList.get(position).getCheck()){
                iv_star.setImageResource(R.drawable.icon_order_starbright);
            }else{
                iv_star.setImageResource(R.drawable.icon_order_stargrey);
            }
            iv_star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClick.onItemClick(position);
                }
            });
        }
    }
    //定义设置点击事件监听的方法
    public void setOnitemClickLintener (OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }
    //定义一个点击事件的接口
    public interface OnitemClick {
        void onItemClick(int position);
    }
}
