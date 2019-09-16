package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.ConfrimParams;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.im.ui.ChatActivity;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;

/**
 * Created by zhg on 2019/6/19.
 */
public class FriendsCenterActivity extends BaseActivity implements BaseBiz {
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
    @BindView(R.id.add_friends)
    RelativeLayout addFriends;
    @BindView(R.id.rl_scan_chat_record)
    RelativeLayout rlScanChatRecord;
    @BindView(R.id.rl_black_name)
    RelativeLayout rlBlackName;
    @BindView(R.id.rl_detele_friends)
    RelativeLayout rlDeteleFriends;
    @BindView(R.id.tv_add_or_chat)
    TextView tvAddOrChat;
    @BindView(R.id.tv_black_name)
    TextView tvBlackName;
    private Intent intent;
    private int id;
    private int type;
    private int state;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_friend_center;
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
        intent = getIntent();
        id = intent.getIntExtra("id", -1);
        type = intent.getIntExtra("type", -1);
        state = intent.getIntExtra("state", -1);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.friend_center));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        if (type == 1) {
            rlScanChatRecord.setVisibility(View.GONE);
            rlBlackName.setVisibility(View.GONE);
            rlDeteleFriends.setVisibility(View.GONE);
            tvAddOrChat.setText("添加好友");
        } else {
            rlScanChatRecord.setVisibility(View.VISIBLE);
            rlBlackName.setVisibility(View.VISIBLE);
            rlDeteleFriends.setVisibility(View.VISIBLE);
            tvAddOrChat.setText("立即聊天");
        }
    }


    private void AddFriend(int id) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call = service.addFriend(id);
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode() == 200) {
                    ToastUtil.showToast(FriendsCenterActivity.this, "已添加成功，待好友验证通过");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }


    @OnClick({R.id.rl_scan_chat_record, R.id.rl_black_name, R.id.rl_detele_friends, R.id.add_friends, R.id.rl_btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_scan_chat_record:
                break;
            case R.id.rl_black_name:
                if (state == 1) {
                    setState(id, 3, state);
                } else {
                    setState(id, 1, state);
                }
                break;
            case R.id.rl_detele_friends:
                setFriendsDetele(id);
                break;
            case R.id.rl_btn_back:
                finish();
                break;
            case R.id.add_friends:
                if (type == 1) {
                    AddFriend(id);
                }else{
                    startActivity(new Intent(FriendsCenterActivity.this, ChatActivity.class).putExtra("userId", "13625843690"));

                }
                break;
        }
    }

    public void setState(int friendId, int type, int states) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call = service.comfrimFriendState(new ConfrimParams(friendId, type));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode() == 200) {
                    if (states == 1) {
                        state=3;
                       tvBlackName.setText("解除黑名单");
                    }else{
                        state=1;
                        tvBlackName.setText("拉黑名单");
                    }
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    public void setFriendsDetele(int id){
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call = service.deteleFriends(id);
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    ToastUtil.showToast(FriendsCenterActivity.this,"成功删除好友");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }
}
