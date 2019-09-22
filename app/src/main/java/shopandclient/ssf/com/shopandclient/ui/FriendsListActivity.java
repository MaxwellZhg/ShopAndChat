package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AddFriendsAdapter;
import shopandclient.ssf.com.shopandclient.adapter.AddNewFriendsAdapter;
import shopandclient.ssf.com.shopandclient.adapter.SortAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.Friend;
import shopandclient.ssf.com.shopandclient.entity.FriendListBean;
import shopandclient.ssf.com.shopandclient.entity.PinyinComparator;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;
import shopandclient.ssf.com.shopandclient.net.services.ProductService;
import shopandclient.ssf.com.shopandclient.util.ScreenDipUtil;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zhg on 2019/6/21.
 */
public class FriendsListActivity extends BaseActivity implements BaseBiz,View.OnClickListener,AddNewFriendsAdapter.OnitemClick {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.lv_friends_list)
    ListView lvFriendsList;
    ArrayList<FriendListBean.DataBean.MyFriendBean> list;
    ArrayList<FriendListBean.DataBean.MyFriendBean> myfriendList=new ArrayList<>();
    ArrayList<FriendListBean.DataBean.NewFriendBean> newfriendsList=new ArrayList<>();
    @BindView(R.id.iv_scope)
    ImageView ivScope;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private PopupWindow pop;
    private  boolean state=false;
    private RelativeLayout rl_add_friends;
    private RelativeLayout rl_add_group;
    private RelativeLayout rl_scan;
    private SortAdapter sa;
    private View headerTop;
    private ListView lv_news_friends;
    private AddNewFriendsAdapter anfa;
    private TextView tv_first_word;
    private RelativeLayout rl_group;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_friends_list;
    }

    @Override
    public void onSuccess(Object object) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @Override
    protected void initView() {
        super.initView();
        initPop();
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.friends_list));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        tvSave.setVisibility(View.INVISIBLE);
        ivScope.setImageResource(R.drawable.function);
        getData();
        headerTop = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.item_friends_list_header,null);
        lv_news_friends = (ListView)headerTop.findViewById(R.id.lv_news_friends);
        tv_first_word = (TextView)headerTop.findViewById(R.id.tv_first_word);
        rl_group = (RelativeLayout) headerTop.findViewById(R.id.rl_group);
        rl_group.setOnClickListener(this);
    }


    private void initPop() {
        pop = new PopupWindow();
        View content = LayoutInflater.from(this).inflate(R.layout.item_add_friend_dailog, null);
        pop.setContentView(content);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT - ScreenDipUtil.dip2px(50));
        rl_add_friends = (RelativeLayout)content.findViewById(R.id.rl_add_friends);
        rl_add_friends.setOnClickListener(this);
        rl_add_group = (RelativeLayout)content. findViewById(R.id.rl_add_group);
        rl_add_group.setOnClickListener(this);
        rl_scan = (RelativeLayout)content.findViewById(R.id.rl_scan);
        rl_scan.setOnClickListener(this);
        pop.setOutsideTouchable(false);
    }

    @OnClick({R.id.rl_btn_scope,R.id.rl_btn_back})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.rl_btn_scope:
                if(state==false){
                    state=true;
                    pop.showAsDropDown(rlAction, 0, 0);
                }else{
                    state=false;
                    pop.dismiss();
                }
                break;
            case R.id.rl_btn_back:
                finish();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        state=false;
        if(pop.isShowing()) {
            pop.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        state=false;
        if(pop.isShowing()){
            pop.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        switch (v.getId()){
            case R.id.rl_add_friends:
                state=false;
                if(pop.isShowing()){
                    pop.dismiss();
                }
                openActivity(AddFridendActivity.class);
                break;
            case R.id.rl_add_group:
                state=false;
                if(pop.isShowing()){
                    pop.dismiss();
                }
                bundle.putInt("type",1);
                openActivity(AddGroupChatActivity.class,bundle);
                break;
            case R.id.rl_scan:
                break;
            case R.id.rl_group:
                openActivity(MyGroupActivity.class);
                break;
        }
    }
    private void getData() {
        ChatCenterService service= RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<FriendListBean> call=service.getFriendList();
        call.enqueue(new Callback<FriendListBean>() {
            @Override
            public void onResponse(Call<FriendListBean> call, Response<FriendListBean> response) {
                   if(response.body().getCode()==200){
                       list=response.body().getData().getMyFriend();
                       myfriendList.clear();
                       for(int i=0;i<list.size();i++){
                           myfriendList.add(new FriendListBean.DataBean.MyFriendBean(list.get(i).getFriendID(),list.get(i).getFriendName(),list.get(i).getImg(),list.get(i).getGuidNO(),list.get(i).getState()));
                       }
                       Collections.sort(myfriendList, new PinyinComparator());
                       sa = new SortAdapter(FriendsListActivity.this, myfriendList);
                       newfriendsList.clear();
                       newfriendsList.addAll(response.body().getData().getNewFriend());
                       if(newfriendsList.size()>0) {
                           anfa = new AddNewFriendsAdapter(FriendsListActivity.this, newfriendsList);
                           anfa.setOnitemClickLintener(FriendsListActivity.this);
                           lv_news_friends.setAdapter(anfa);
                           ScreenDipUtil.setListViewHeightBasedOnChildren(lv_news_friends);
                       }else{
                           lv_news_friends.setVisibility(View.GONE);
                           tv_first_word.setVisibility(View.GONE);
                       }
                       if(lvFriendsList.getHeaderViewsCount()==0) {
                           lvFriendsList.addHeaderView(headerTop);
                       }
                       lvFriendsList.setAdapter(sa);
                       sa.notifyDataSetChanged();
                   }
            }

            @Override
            public void onFailure(Call<FriendListBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick() {
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
