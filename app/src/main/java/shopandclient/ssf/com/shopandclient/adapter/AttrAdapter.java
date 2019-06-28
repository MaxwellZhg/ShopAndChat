package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.greenrobot.eventbus.EventBus;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.Attr;
import shopandclient.ssf.com.shopandclient.entity.ProductInfo;
import shopandclient.ssf.com.shopandclient.event.AttrEvent;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/4.
 */
public class AttrAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ProductInfo.DataBean.ProAttrTypeBean.ProAttrTypeValueBean> attrs;
    private OnitemClick onitemClick;   //定义点击事件接口
    private Attr attr;
    public AttrAdapter(Context context, ArrayList<ProductInfo.DataBean.ProAttrTypeBean.ProAttrTypeValueBean> attrs,Attr attr) {
        this.context = context;
        this.attrs = attrs;
        this.attr=attr;
    }

    //定义设置点击事件监听的方法
    public void setOnitemClickLintener(OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    //定义一个点击事件的接口
    public interface OnitemClick {
        void onItemClick(int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AttrViewHodler(LayoutInflater.from(context).inflate(R.layout.item_attrbute, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AttrViewHodler) holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return attrs.size();
    }

    class AttrViewHodler extends RecyclerView.ViewHolder {
        private RelativeLayout rl_attr;
        private TextView tv_attr;

        public AttrViewHodler(View itemView) {
            super(itemView);
            rl_attr = (RelativeLayout) itemView.findViewById(R.id.rl_attr);
            tv_attr = (TextView) itemView.findViewById(R.id.tv_attr);
        }

        public void setData(final int position) {
            tv_attr.setText(attrs.get(position).getAttrTypeValue());
            if (attrs.get(position).isSelect()) {
                rl_attr.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.attrbute_check_bg));
                tv_attr.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.tv_price_color));
            } else {
                rl_attr.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.attrbute_uncheck_bg));
                tv_attr.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
            }
          /*  if (onitemClick != null) {
                rl_attr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //在TextView的地方进行监听点击事件，并且实现接口
                        onitemClick.onItemClick(position);
                    }
                });
            }*/
            rl_attr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //在TextView的地方进行监听点击事件，并且实现接口
                    if (!attrs.get(position).isSelect()) {
                        for (int i = 0; i < attrs.size(); i++) {
                            attrs.get(i).setSelect(false);
                        }
                        attrs.get(position).setSelect(true);
                        notifyDataSetChanged();
                        if(attrs.get(position).getPos()==1){
                          attr.setAttrL1ID(attrs.get(position).getId());
                        }else if(attrs.get(position).getPos()==2){
                            attr.setAttrL2ID(attrs.get(position).getId());
                        }
                        EventBus.getDefault().post(attr);
                    }
                }
            });
        }
    }
}
