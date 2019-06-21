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
import shopandclient.ssf.com.shopandclient.entity.LogicticsBean;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.TimeLineMarkerView;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/13.
 */
public class LogicticsTrackInfoAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<LogicticsBean> list;

    public LogicticsTrackInfoAdapter(Context context, ArrayList<LogicticsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LogicticsTrackViewHolder(LayoutInflater.from(context).inflate(R.layout.item_track_info,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LogicticsTrackViewHolder)holder).setData(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class LogicticsTrackViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_time;
        private TextView tv_content;
        private TimeLineMarkerView tv_timelineview;
        private TextView tv_date;
        public LogicticsTrackViewHolder(View itemView) {
            super(itemView);
            tv_date=(TextView) itemView.findViewById(R.id.tv_date);
            tv_time= (TextView) itemView.findViewById(R.id.tv_time);
            tv_content= (TextView) itemView.findViewById(R.id.tv_content);
            tv_timelineview=(TimeLineMarkerView)itemView.findViewById(R.id.tv_timelineview);
        }
        public void setData(int position){
            tv_date.setText(list.get(position).getDate());
            tv_time.setText(list.get(position).getTime());
            tv_content.setText(list.get(position).getInfo());
            if(list.size()>1) {
                if (position == 0) {
                    tv_timelineview.setBeginLine(null);
                    tv_content.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    tv_date.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                    tv_time.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                } else if (position == list.size() - 1) {
                    tv_timelineview.setEndLine(null);
                }
                if(list.get(position).getState()==1){
                    tv_timelineview.setMarkerDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.icon_logistics_goodsreceived_green));
                }
            }else{
                tv_content.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                tv_date.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                tv_time.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.text_bg));
                tv_timelineview.setBeginLine(null);
                tv_timelineview.setEndLine(null);
            }
        }
    }
}
