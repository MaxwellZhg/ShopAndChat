package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import shopandclient.ssf.com.shopandclient.adapter.ChangeGroupManagerAdapter;
import shopandclient.ssf.com.shopandclient.adapter.ForbinGroupMemberAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.*;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;
import shopandclient.ssf.com.shopandclient.util.*;

import java.util.ArrayList;

import static com.hyphenate.chat.core.EMDBManager.a;

public class ChangeGroupManegerActivity extends BaseActivity implements BaseBiz, Observer {
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
    ArrayList<GroupInfoBean.DataBean.ListBean> arrayList;
    ArrayList<CreateGroupParams.ListBean> list = new ArrayList<>();
    @BindView(R.id.rl_limmit_create)
    RelativeLayout rlLimmitCreate;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    private ChangeGroupManagerAdapter ada;
    private ForbinGroupMemberAdapter fga;
    private int type;
    private int groupid;
    private String str="";
    private String unStr="";
    private String a="";
    private String b="";
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
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }

    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        type = intent.getIntExtra("type", -1);
        groupid = intent.getIntExtra("groupId", -1);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.change_group_manager));
        if(type==1) {
            tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.change_group_manager));
            tvTips.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.change_manager));
        }else{
            tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.forbid_member));
            tvTips.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.ensure));
        }
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        getData();
    }

    private void getData() {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<GroupInfoBean> call=service.getGroupInfo(groupid);
        call.enqueue(new Callback<GroupInfoBean>() {
            @Override
            public void onResponse(Call<GroupInfoBean> call, Response<GroupInfoBean> response) {
                if (response.body().getCode() == 200) {
                    arrayList=response.body().getData().getList();
                    if(type==1) {
                        ada = new ChangeGroupManagerAdapter(ChangeGroupManegerActivity.this, arrayList);
                        rvGroup.setLayoutManager(new LinearLayoutManager(ChangeGroupManegerActivity.this));
                        rvGroup.setAdapter(ada);
                    }else{
                        fga = new ForbinGroupMemberAdapter(ChangeGroupManegerActivity.this, arrayList);
                        rvGroup.setLayoutManager(new LinearLayoutManager(ChangeGroupManegerActivity.this));
                        rvGroup.setAdapter(fga);
                    }
                }
            }

            @Override
            public void onFailure(Call<GroupInfoBean> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.rl_limmit_create, R.id.rl_btn_back})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.rl_limmit_create:
                if(type==1) {
                    list.clear();
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i).isChooseGroup() == true) {
                            list.add(new CreateGroupParams.ListBean(arrayList.get(i).getUserID(), arrayList.get(i).getUserName()));
                        }
                    }
                    if (list.size() > 0) {
                        updateGroupAdmin(list);
                    } else {
                        ToastUtil.showToast(ChangeGroupManegerActivity.this, "请选择移交人");
                    }
                }else{
                    str="";
                    unStr="";
                    for (int i = 0; i < arrayList.size(); i++) {
                        if(arrayList.get(i).getIfForbid()==1){
                            str+=arrayList.get(i).getUserID()+",";
                        }
                        if(arrayList.get(i).isChooseGroup()==true){
                            unStr+=arrayList.get(i).getUserID()+",";
                        }
                    }
                    if(!str.equals("")) {
                        a = str.substring(0, str.length() - 1);
                    }
                    if(!unStr.equals("")) {
                        b = unStr.substring(0, unStr.length() - 1);
                    }
                    Log.e("ttttttt",a);
                    Log.e("ttttttt",b);
                    if(!a.equals("")&&a!=null) {
                        if(!b.equals("")&&b!=null) {
                            forbinMember(a, 1, 1);
                        }else{
                            forbinMember(a, 1, 2);
                        }
                    }else if(a.equals("")||a==null){
                        if(!b.equals("")&&b!=null) {
                            forbinMember(b, 0, 2);
                        }else{
                            ToastUtil.showToast(ChangeGroupManegerActivity.this,"请选择禁言成员");
                        }
                    }
                }
                break;
            case R.id.rl_btn_back:
                finish();
                break;
        }
    }


    public void updateGroupAdmin(ArrayList<CreateGroupParams.ListBean> list) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call = service.updateGroupAdmin(list.get(0).getUserid(),groupid);
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    public void forbinMember(String str,int forbin,int forbintype){
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call = service.forbinChatMember(new ForbinChatGroupMemberParams(groupid,str,forbin));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.body().getCode()==200){
                   if(forbintype==1){
                       if(!b.equals("")&&b!=null) {
                           forbinMember(b, 0, 2);
                       }
                   }else{
                       finish();
                   }
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }
}
