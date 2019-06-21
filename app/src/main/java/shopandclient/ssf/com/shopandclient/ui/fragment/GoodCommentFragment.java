package shopandclient.ssf.com.shopandclient.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.CommentListAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseFragment;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.Comment;
import shopandclient.ssf.com.shopandclient.entity.Moment;
import shopandclient.ssf.com.shopandclient.entity.User;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.ui.GoodsDetailActivity;
import shopandclient.ssf.com.shopandclient.ui.MainActivity;
import shopandclient.ssf.com.shopandclient.util.CommentFun;
import shopandclient.ssf.com.shopandclient.util.CustomTagHandler;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/14.
 */
public class GoodCommentFragment extends BaseFragment implements BaseBiz,CommentListAdapter.onitemClikck{

    private ListView list;
    private CommentListAdapter cla;

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
        list = (ListView)findViewById(R.id.list);
        ArrayList<Moment> moments = new ArrayList<Moment>();
        for (int i = 0; i < 20; i++) {
            ArrayList<Comment> comments = new ArrayList<Comment>();
            ArrayList<Integer> arrayList=new ArrayList<>();
            arrayList.add(R.drawable.meinv1);
            arrayList.add(R.drawable.meinv2);
            arrayList.add(R.drawable.meinv3);
            comments.add(new Comment(new User(i + 2, "用户" + i), "评论" + i, null));
            comments.add(new Comment(new User(i + 100, "用户" + (i + 100)), "评论" + i, new User(i + 200, "用户" + (i + 200))));
            comments.add(new Comment(new User(i + 200, "用户" + (i + 200)), "评论" + i, null));
            comments.add(new Comment(new User(i + 300, "用户" + (i + 300)), "评论" + i, null));
             moments.add(new Moment("动态 " + i,i,"测试测试。。。。。。。。。。。。" ,comments,arrayList));
        }
        cla = new CommentListAdapter(MyApplication.getInstance().mContext,moments,new CustomTagHandler(MyApplication.getInstance().mContext, new CustomTagHandler.OnCommentClickListener() {
            @Override
            public void onCommentatorClick(View view, User commentator) {

            }

            @Override
            public void onReceiverClick(View view, User receiver) {

            }

            @Override
            public void onContentClick(View view, User commentator, User receiver) {

            }
        }));
        cla.setOnitemClikck(this);
        list.setAdapter(cla);
    }

    public void inputComment(final View v) {
        inputComment(v, null);
    }

    /**
     * 弹出评论对话框
     * @param v
     * @param receiver
     */
    public void inputComment(final View v, User receiver) {
        CommentFun.inputComment(getActivity(), list, v, receiver, new CommentFun.InputCommentListener() {
            @Override
            public void onCommitComment() {
                cla.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void itemclick(View v) {
        inputComment(v);
    }
}
