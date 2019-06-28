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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.entity.Moment;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.entity.PostLikeParams;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.util.CommentFun;
import shopandclient.ssf.com.shopandclient.util.CustomTagHandler;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder hodler;
        if(convertView==null){
            hodler= new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_list_comment,parent,false);
            hodler.rv_show_img=(MyRecycleview) convertView.findViewById(R.id.rv_show_img);
            hodler.tv_host=(TextView) convertView.findViewById(R.id.tv_host);
            hodler.comment_list=(LinearLayout) convertView.findViewById(R.id.comment_list);
            hodler.rl_comment=(LinearLayout) convertView.findViewById(R.id.rl_comment);
            hodler.tv_comment_content=(TextView) convertView.findViewById(R.id.tv_comment_content);
            hodler.tv_comment_time=(TextView) convertView.findViewById(R.id.tv_comment_time);
            hodler.rl_zan=(LinearLayout) convertView.findViewById(R.id.rl_zan);
            convertView.setTag(hodler);
        }else{
            hodler=(ViewHolder)convertView.getTag();
        }
        ((ViewGroup) convertView).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
       CommentImageAdapter commentimage=new CommentImageAdapter(context,moments.get(position).getListResId());
        hodler.rv_show_img.setLayoutManager(new GridLayoutManager(context,3));
        hodler.rv_show_img.setAdapter(commentimage);
        hodler.tv_host.setText(moments.get(position).getName());
        hodler.tv_comment_content.setText(moments.get(position).getConent());
        hodler.tv_comment_time.setText(moments.get(position).getTime());
        CommentFun.parseCommentList(context, moments.get(position).getmComment(),
                hodler.comment_list, hodler.rl_comment, mTagHandler);
        hodler.rl_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onitemClikck.itemclick(v,moments.get(position).getProId(),moments.get(position).getCommentID());
            }
        });
        hodler.rl_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              postLike(moments.get(position).getCommentID(),4);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tv_host;
        private MyRecycleview rv_show_img;
        private LinearLayout comment_list;
        private LinearLayout rl_comment;
        private TextView tv_comment_content;
        private TextView tv_comment_time;
        private LinearLayout rl_zan;
    }

    public interface  onitemClikck{
        void itemclick(View v,int proId,int commentId);
    }

    public void postLike(int id,int type){
        ProductService service=RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<PostComment> call=service.setGiveLike(new PostLikeParams(id,type));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    ToastUtil.showToast(context,response.body().getResult());
                }else{
                    ToastUtil.showToast(context,response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

}
