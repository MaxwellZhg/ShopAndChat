package shopandclient.ssf.com.shopandclient.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jaeger.library.StatusBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shopandclient.ssf.com.shopandclient.R;
import shopandclient.ssf.com.shopandclient.adapter.MyGroupAdapter;
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.MyGroupBean;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;

import java.util.ArrayList;

public class MyGroupActivity extends BaseActivity implements AdapterView.OnItemClickListener {
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
    @BindView(R.id.iv_back)
    ImageView ivBack;
    ArrayList<MyGroupBean.DataBean> arrayList;
    private MyGroupAdapter mga;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_my_group;
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
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.my_group));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
        lvFriendsList.setOnItemClickListener(this);
        getMyGroupData();
    }

    private void getMyGroupData() {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<MyGroupBean> call = service.getMyGroup();
        call.enqueue(new Callback<MyGroupBean>() {
            @Override
            public void onResponse(Call<MyGroupBean> call, Response<MyGroupBean> response) {
                if (response.body().getCode() == 200) {
                    arrayList = response.body().getData();
                    mga = new MyGroupAdapter(MyGroupActivity.this, arrayList);
                    lvFriendsList.setAdapter(mga);
                }
            }

            @Override
            public void onFailure(Call<MyGroupBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle=new Bundle();
        bundle.putInt("groupId",arrayList.get(position).getGroupID());
        bundle.putInt("groupAdminID",arrayList.get(position).getGroupAdminID());
        openActivity(ManagerGroupActivity.class,bundle);
    }

    @OnClick(R.id.rl_btn_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMyGroupData();
    }
}