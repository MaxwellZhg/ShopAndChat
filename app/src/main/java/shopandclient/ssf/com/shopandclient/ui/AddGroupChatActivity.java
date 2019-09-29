package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMGroupOptions;
import com.hyphenate.exceptions.HyphenateException;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.AddGroupAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.AddIntpGroupParams;
import shopandclient.ssf.com.shopandclient.entity.CreateGroupParams;
import shopandclient.ssf.com.shopandclient.entity.FriendListBean;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;
import shopandclient.ssf.com.shopandclient.util.Observer;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.Subject;
import shopandclient.ssf.com.shopandclient.util.TokenManager;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/19.
 */
public class AddGroupChatActivity extends BaseActivity implements BaseBiz, Observer {
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
    @BindView(R.id.rv_group)
    RecyclerView rvGroup;
    ArrayList<FriendListBean.DataBean.MyFriendBean> arrayList = new ArrayList<>();
    ArrayList<CreateGroupParams.ListBean> list = new ArrayList<>();
    ArrayList<AddIntpGroupParams.ListBean> addlist = new ArrayList<>();
    ArrayList<String> listuser=new ArrayList<>();
    @BindView(R.id.rl_limmit_create)
    RelativeLayout rlLimmitCreate;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    private AddGroupAdapter ada;
    private int type;
    private int groupid;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_create_group_chat;
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
        tokenManager = TokenManager.newInstance();
        tokenManager.registerObserver(this);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips), 0);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        type = intent.getIntExtra("type", -1);
        groupid = intent.getIntExtra("groupId", -1);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        if (type == 1) {
            tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.create_group_chat));
            tvTips.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.limimit_create));
        } else {
            tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.add_into_member_to_group));
            tvTips.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.limimit_addinto));
        }
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        getData();
    }

    private void getData() {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<FriendListBean> call = service.getFriendList();
        call.enqueue(new Callback<FriendListBean>() {
            @Override
            public void onResponse(Call<FriendListBean> call, Response<FriendListBean> response) {
                if (response.body().getCode() == 200) {
                    arrayList.addAll(response.body().getData().getMyFriend());
                    ada = new AddGroupAdapter(AddGroupChatActivity.this, arrayList);
                    rvGroup.setLayoutManager(new LinearLayoutManager(AddGroupChatActivity.this));
                    rvGroup.setAdapter(ada);
                }
            }

            @Override
            public void onFailure(Call<FriendListBean> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.rl_limmit_create, R.id.rl_btn_back})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.rl_limmit_create:
                list.clear();
                addlist.clear();
                listuser.clear();
                if (type == 1) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i).isChooseGroup() == true) {
                            list.add(new CreateGroupParams.ListBean(arrayList.get(i).getFriendID(), arrayList.get(i).getFriendName()));
                            listuser.add(arrayList.get(i).getGuidNO());
                        }
                    }
                    createGroup(list);
                } else {
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i).isChooseGroup() == true) {
                            addlist.add(new AddIntpGroupParams.ListBean(arrayList.get(i).getFriendID(), arrayList.get(i).getFriendName(), groupid));
                            listuser.add(arrayList.get(i).getGuidNO());
                        }
                    }
                    addMemberIntoGroup(addlist);
                }
                break;
            case R.id.rl_btn_back:
                finish();
                break;
        }
    }

    private void addMemberIntoGroup(ArrayList<AddIntpGroupParams.ListBean> addlist) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call = service.addFriendToGroupList(new AddIntpGroupParams(addlist));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode()==200) {
                    creatImGroup(listuser.toArray(new String[listuser.size()]));
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    public void createGroup(ArrayList<CreateGroupParams.ListBean> list) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call = service.createGroup(new CreateGroupParams(list));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode() == 200) {
                    creatImGroup(listuser.toArray(new String[listuser.size()]));
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    private void creatImGroup(String[] members){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMGroupOptions option = new EMGroupOptions();
                    option.maxUsers = 200;
                    option.inviteNeedConfirm = true;

                    String reason = AddGroupChatActivity.this.getString(R.string.invite_join_group);
                    reason  = EMClient.getInstance().getCurrentUser() + reason + "群组";
                    option.style =  EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;
                /*    if(publibCheckBox.isChecked()){
                        option.style = memberCheckbox.isChecked() ? EMGroupManager.EMGroupStyle.EMGroupStylePublicJoinNeedApproval : EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;
                    }else{
                        option.style = memberCheckbox.isChecked()? EMGroupManager.EMGroupStyle.EMGroupStylePrivateMemberCanInvite: EMGroupManager.EMGroupStyle.EMGroupStylePrivateOnlyOwnerInvite;
                    }*/
                    EMClient.getInstance().groupManager().createGroup("群组", "创建的群组", members, reason, option);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            //setResult(RESULT_OK);
                            finish();
                        }
                    });
                } catch (final HyphenateException e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(AddGroupChatActivity.this, "群组"+ e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        }).start();
    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }
}
