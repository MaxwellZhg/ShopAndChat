package shopandclient.ssf.com.shopandclient.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.Moment;
import shopandclient.ssf.com.shopandclient.util.CommentFun;
import shopandclient.ssf.com.shopandclient.util.CustomTagHandler;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/17.
 */
public class CommentListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Moment> moments;
    private CustomTagHandler mTagHandler;
    private onitemClikck onitemClikck;
    public CommentListAdapter(Context context, ArrayList<Moment> moments, CustomTagHandler mTagHandler) {
        this.context = context;
        this.moments = moments;
        this.mTagHandler = mTagHandler;
    }
    public void setOnitemClikck(onitemClikck onitemClikck){
        this.onitemClikck=onitemClikck;
    }
    @Override
    public int getCount() {
        return moments.size();
    }

    @Override
    public Object getItem(int position) {
        return moments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hodler;
        if(convertView==null){
            hodler= new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_list_comment,parent,false);
            hodler.rv_show_img=(MyRecycleview) convertView.findViewById(R.id.rv_show_img);
            hodler.tv_host=(TextView) convertView.findViewById(R.id.tv_host);
            hodler.comment_list=(LinearLayout) convertView.findViewById(R.id.comment_list);
            hodler.rl_comment=(LinearLayout) convertView.findViewById(R.id.rl_comment);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHolder)convertView.getTag();
        }
        ((ViewGroup) convertView).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
       CommentImageAdapter commentimage=new CommentImageAdapter(context,moments.get(position).getListResId());
        hodler.rv_show_img.setLayoutManager(new GridLayoutManager(context,3));
        hodler.rv_show_img.setAdapter(commentimage);
        hodler.tv_host.setText(moments.get(position).getName());
        CommentFun.parseCommentList(context, moments.get(position).getmComment(),
                hodler.comment_list, hodler.rl_comment, mTagHandler);
        hodler.rl_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemClikck.itemclick(v);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tv_host;
        private MyRecycleview rv_show_img;
        private LinearLayout comment_list;
        private LinearLayout rl_comment;
    }

    public interface  onitemClikck{
        void itemclick(View v);
    }

}
