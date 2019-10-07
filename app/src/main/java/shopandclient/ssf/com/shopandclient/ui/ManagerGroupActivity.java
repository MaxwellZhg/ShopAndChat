package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.hyphenate.chat.EMClient;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.GroupMemberAdapter;
import shopandclient.ssf.com.shopandclient.adapter.LvManagerGroupAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.GroupInfoBean;
import shopandclient.ssf.com.shopandclient.entity.OrderDetailBean;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;
import shopandclient.ssf.com.shopandclient.util.*;
import shopandclient.ssf.com.shopandclient.weiget.bananer.view.MyRecycleview;

import java.util.ArrayList;

/**
 * Created by zhg on 2019/6/20.
 */
public class ManagerGroupActivity extends BaseActivity implements BaseBiz, AdapterView.OnItemClickListener,GroupMemberAdapter.OnitemClick, Observer {
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
    @BindView(R.id.lv_manager_group)
    ListView lvManagerGroup;
    private MyRecycleview rv_manager;
    ArrayList<GroupInfoBean.DataBean.ListBean> arrayList ;
    ArrayList<String> strings = new ArrayList<>();
    private GroupMemberAdapter gma;
    private LvManagerGroupAdapter lma;
    private View itemview;
    private int groupId;
    private int groupAdminID;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_manager_group;
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
        Intent intent=getIntent();
        groupId = intent.getIntExtra("groupId",-1);
        groupAdminID = intent.getIntExtra("groupAdminID",-1);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.manager_group));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        itemview = LayoutInflater.from(this).inflate(R.layout.item_manager_group_header, null);
        rv_manager = (MyRecycleview) itemview.findViewById(R.id.rv_manager);
        gma = new GroupMemberAdapter(this);
        gma.setOnitemClickLintener(this);
        lma = new LvManagerGroupAdapter(this, strings);
        getData(groupId);
    }

    private void getData(int groupId) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<GroupInfoBean> call=service.getGroupInfo(groupId);
        call.enqueue(new Callback<GroupInfoBean>() {
            @Override
            public void onResponse(Call<GroupInfoBean> call, Response<GroupInfoBean> response) {
                  if(response.body().getCode()==200) {
                      strings.clear();
                      arrayList=response.body().getData().getList();
                      strings.add(MyApplication.getInstance().mContext.getResources().getString(R.string.group_chat_name));
                      for (int i=0;i<arrayList.size();i++){
                          if(arrayList.get(i).getUserID()==SpConfig.getInstance().getInt(Constants.USERID)){
                              if(arrayList.get(i).getGroupAdminID()==1) {
                                  strings.add(MyApplication.getInstance().mContext.getResources().getString(R.string.change_group_manager));
                                  strings.add(MyApplication.getInstance().mContext.getResources().getString(R.string.detele_group));
                                  strings.add(MyApplication.getInstance().mContext.getResources().getString(R.string.forbid_member));
                              }else{
                                  strings.add(MyApplication.getInstance().mContext.getResources().getString(R.string.out_group));
                              }
                          }
                      }
                     /* if(groupAdminID== SpConfig.getInstance().getInt(Constants.USERID)) {
                          strings.add(MyApplication.getInstance().mContext.getResources().getString(R.string.change_group_manager));
                      }
                      if(groupAdminID== SpConfig.getInstance().getInt(Constants.USERID)) {
                          strings.add(MyApplication.getInstance().mContext.getResources().getString(R.string.detele_group));
                      }else{
                          strings.add(MyApplication.getInstance().mContext.getResources().getString(R.string.out_group));
                      }
                      if(groupAdminID== SpConfig.getInstance().getInt(Constants.USERID)) {
                          strings.add(MyApplication.getInstance().mContext.getResources().getString(R.string.forbid_member));
                      }*/
                      gma.setImages(arrayList);
                      rv_manager.setLayoutManager(new GridLayoutManager(ManagerGroupActivity.this, 4));
                      rv_manager.setAdapter(gma);
                      if(lvManagerGroup.getHeaderViewsCount()==0) {
                          lvManagerGroup.addHeaderView(itemview);
                      }
                      lvManagerGroup.setAdapter(lma);
                      lvManagerGroup.setOnItemClickListener(ManagerGroupActivity.this);
                  }
            }

            @Override
            public void onFailure(Call<GroupInfoBean> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle =new Bundle();
        switch (strings.get(position-1)){
            case "群聊名称":
                bundle.putInt("groupId",groupId);
                openActivity(UpdateGroupNameActivty.class,bundle);
                finish();
                break;
            case "解散该群":
                 deteleGroup(groupId);
                break;
            case "退出群":
                outGroup(groupId);
                break;
            case "管理员移交":
                bundle.putInt("groupId",groupId);
                bundle.putInt("type",1);
                openActivity(ChangeGroupManegerActivity.class,bundle);
                break;
            case "禁言":
                bundle.putInt("groupId",groupId);
                bundle.putInt("type",2);
                openActivity(ChangeGroupManegerActivity.class,bundle);
                break;
        }
    }

    private void outGroup(int groupId) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call=service.outGroupMember(groupId);
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                   if(response.body().getCode()==200){
                       openActivity(FriendsListActivity.class);
                       finish();
                   }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    public void deteleGroup(int groupId){
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call=service.deteleGroup(groupId);
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData(groupId);
    }

    @Override
    public void onItemClick(int position) {
        if(groupAdminID== SpConfig.getInstance().getInt(Constants.USERID)) {
            new ActionSheetDialog(this)
                    .builder()
                    .setCancelable(false)
                    .setCanceledOnTouchOutside(false)
                    .addSheetItem("移除出群", ActionSheetDialog.SheetItemColor.Blue,
                            new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    setState(arrayList.get(position).getUserID(), arrayList.get(position).getGroupID());
                                }
                            }).show();
        }
    }

    private void setState(int userID, int groupID) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call=service.deteleSingleFormGroup(userID,groupID);
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    getData(groupId);
                }else{
                    ToastUtil.showToast(ManagerGroupActivity.this,response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    @Override
    public void update(Subject subject) {

    }

  /*  *//**
     * 退出群组
     *
     *//*
    private void exitGrop() {
        String st1 = getResources().getString(R.string.Exit_the_group_chat_failure);
        new Thread(new Runnable() {
            public void run() {
                try {
                    EMClient.getInstance().groupManager().leaveGroup(groupId);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            setResult(RESULT_OK);
                            finish();
                            if(ChatActivity.activityInstance != null)
                                ChatActivity.activityInstance.finish();
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.Exit_the_group_chat_failure) + " " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    *//**
     * 解散群组
     *
     *//*
    private void deleteGrop() {
        final String st5 = getResources().getString(R.string.Dissolve_group_chat_tofail);
        new Thread(new Runnable() {
            public void run() {
                try {
                    EMClient.getInstance().groupManager().destroyGroup(groupId);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            setResult(RESULT_OK);
                            finish();
                            if(ChatActivity.activityInstance != null)
                                ChatActivity.activityInstance.finish();
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), st5 + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    *//**
     * 增加群成员
     *
     * @param newmembers
     *//*
    private void addMembersToGroup(final String[] newmembers) {
        final String st6 = getResources().getString(R.string.Add_group_members_fail);
        new Thread(new Runnable() {

            public void run() {
                try {
                    // 创建者调用add方法
                    if (EMClient.getInstance().getCurrentUser().equals(group.getOwner())) {
                        EMClient.getInstance().groupManager().addUsersToGroup(groupId, newmembers);
                    } else {
                        // 一般成员调用invite方法
                        EMClient.getInstance().groupManager().inviteUser(groupId, newmembers, null);
                    }
                    updateGroup();
                    refreshMembersAdapter();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            ((TextView) findViewById(R.id.group_name)).setText(group.getGroupName() + "(" + group.getMemberCount()
                                    + st);
                            progressDialog.dismiss();
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), st6 + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }*/
}
