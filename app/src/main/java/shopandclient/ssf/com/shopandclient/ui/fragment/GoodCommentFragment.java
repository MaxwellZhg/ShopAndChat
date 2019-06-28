package shopandclient.ssf.com.shopandclient.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.CommentListAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseFragment;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.*;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.util.CommentFun;
import shopandclient.ssf.com.shopandclient.util.CustomTagHandler;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/14.
 */
public class GoodCommentFragment extends BaseFragment implements BaseBiz,CommentListAdapter.onitemClikck{

    private ListView list;
    private CommentListAdapter cla;
    private int id;
    private ArrayList<CommentBean.DataBean.ListBean> listBean;
    @Override
    protected int getLayoutResouceId() {
        return R.layout.item_list;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle=getArguments();
        int id=bundle.getInt("id");
        Log.e("tttttttt","=============="+id);
        getData(id);
        list = (ListView)findViewById(R.id.list);
    /*    ArrayList<Moment> moments = new ArrayList<Moment>();
        for (int i = 0; i < 20; i++) {
            ArrayList<Comment> comments = new ArrayList<Comment>();
            ArrayList<Integer> arrayList=new ArrayList<>();
            arrayList.add(R.drawable.meinv1);
            arrayList.add(R.drawable.meinv2);
            arrayList.add(R.drawable.meinv3);
            comments.add(new Comment(new CommentUser(i + 2, "用户" + i), "评论" + i, null));
            comments.add(new Comment(new CommentUser(i + 100, "用户" + (i + 100)), "评论" + i, new CommentUser(i + 200, "用户" + (i + 200))));
            comments.add(new Comment(new CommentUser(i + 200, "用户" + (i + 200)), "评论" + i, null));
            comments.add(new Comment(new CommentUser(i + 300, "用户" + (i + 300)), "评论" + i, null));
             moments.add(new Moment("动态 " + i,i,"测试测试。。。。。。。。。。。。" ,comments,arrayList));
        }*/
    }

    public void inputComment(final View v,int proId, int commentId) {
        inputComment(v, null,proId,commentId);
    }

    /**
     * 弹出评论对话框
     * @param v
     * @param receiver
     */
    public void inputComment(final View v, CommentUser receiver,int proId,int commentId) {
        CommentFun.inputComment(getActivity(), list, v, receiver, new CommentFun.InputCommentListener() {
            @Override
            public void onCommitComment() {
                cla.notifyDataSetChanged();
            }
        },proId,commentId);
    }


  /*  @Override
    public void itemclick(View v) {
        inputComment(v);
    }*/
    public void getData(final int id){
        ProductService service = RetrofitHandle.getInstance().retrofit.create(ProductService.class);
        Call<CommentBean> call = service.getCommentInfo(id,1);
        call.enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                ArrayList<Moment> moments = new ArrayList<Moment>();
                listBean=response.body().getData().getList();
                for(int i=0;i<listBean.size();i++){
                    ArrayList<CommentBean.DataBean.ListBean.WithInCommentsBean> commentsBeanArrayList=listBean.get(i).getWithInComments();
                    ArrayList<Comment> comments = new ArrayList<Comment>();
                    ArrayList<Integer> arrayList=new ArrayList<>();
                    arrayList.add(R.drawable.meinv1);
                    arrayList.add(R.drawable.meinv2);
                    arrayList.add(R.drawable.meinv3);
                    for(int j=0;j<commentsBeanArrayList.size();j++){
                        comments.add(new Comment(new CommentUser(commentsBeanArrayList.get(j).getId(),commentsBeanArrayList.get(j).getUserName()),commentsBeanArrayList.get(j).getComments(),new CommentUser(listBean.get(i).getId(),listBean.get(i).getUserName())));
                    }
                    moments.add(new Moment(listBean.get(i).getUserName(),listBean.get(i).getId(),listBean.get(i).getComments() ,listBean.get(i).getTimes(),comments,arrayList,listBean.get(i).getId(),id));
                }
                cla = new CommentListAdapter(MyApplication.getInstance().mContext,moments,new CustomTagHandler(MyApplication.getInstance().mContext, new CustomTagHandler.OnCommentClickListener() {
                    @Override
                    public void onCommentatorClick(View view, CommentUser commentator) {

                    }

                    @Override
                    public void onReceiverClick(View view, CommentUser receiver) {

                    }

                    @Override
                    public void onContentClick(View view, CommentUser commentator, CommentUser receiver) {

                    }
                }));
                cla.setOnitemClikck(GoodCommentFragment.this);
                list.setAdapter(cla);

            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void itemclick(View v, int proId, int commentId) {
        inputComment(v,proId,commentId);
    }
}
