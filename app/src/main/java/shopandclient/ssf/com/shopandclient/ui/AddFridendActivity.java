package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AddFriendsAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.*;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/20.
 */
public class AddFridendActivity extends BaseActivity implements BaseBiz, TextWatcher,AddFriendsAdapter.OnitemClick {
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
    @BindView(R.id.rv_add_friends)
    RecyclerView rvAddFriends;
    ArrayList<SearchFriend.DataBean> arrayList;
    @BindView(R.id.iv_serach_scope)
    ImageView ivSerachScope;
    @BindView(R.id.et_search)
    EditText etSearch;
    private AddFriendsAdapter afa;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_add_friends;
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
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.add_friends));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        etSearch.addTextChangedListener(this);
        rvAddFriends.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!s.toString().matches("^[0-9]*$")) {
            postSearchUser(s.toString());
        }
    }

    private void postSearchUser(String toString) {
        ChatCenterService service= RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<SearchFriend> call=service.serachFriend(new SearchUserParams(toString));
        call.enqueue(new Callback<SearchFriend>() {
            @Override
            public void onResponse(Call<SearchFriend> call, Response<SearchFriend> response) {
                if(response.body().getCode()==200){
                    arrayList=response.body().getData();
                    afa = new AddFriendsAdapter(MyApplication.getInstance().mContext, arrayList);
                    afa.setOnitemClick(AddFridendActivity.this);
                    rvAddFriends.setAdapter(afa);
                }
            }

            @Override
            public void onFailure(Call<SearchFriend> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("id",arrayList.get(position).getUserID());
        if(arrayList.get(position).getIfFriends()==0) {
            bundle.putInt("type", 1);
        }else{
            bundle.putInt("type", 2);
        }
        openActivity(FriendsCenterActivity.class,bundle);
    }

    /*private void addFriend(int position) {
        ChatCenterService service= RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call=service.addFriend(arrayList.get(position).getUserID());
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                    Intent intent = new Intent();
                    setResult(2,intent);
                    finish();
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }*/
}
