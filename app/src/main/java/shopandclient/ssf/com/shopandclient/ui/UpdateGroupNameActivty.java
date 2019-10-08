package shopandclient.ssf.com.shopandclient.ui;

import android.app.Activity;
import android.content.Intent;
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
import shopandclient.ssf.com.shopandclient.base.BaseActivity;
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.PostComment;
import shopandclient.ssf.com.shopandclient.entity.UpdateGroupNameParams;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.services.ChatCenterService;
import shopandclient.ssf.com.shopandclient.util.*;

public class UpdateGroupNameActivty extends BaseActivity implements Observer {
    @BindView(R.id.rl_btn_back)
    RelativeLayout rlBtnBack;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.rl_btn_scope)
    RelativeLayout rlBtnScope;
    @BindView(R.id.rl_action)
    RelativeLayout rlAction;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private int groupId;
    private String str;
    private TokenManager tokenManager;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_update_groupname;
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
        groupId = intent.getIntExtra("groupId", -1);
        rlAction.setBackgroundColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.password_tips));
        ivBack.setBackgroundDrawable(MyApplication.getInstance().mContext.getResources().getDrawable(R.drawable.black));
        tvCenterTitle.setText(MyApplication.getInstance().mContext.getResources().getString(R.string.update_name_ofgroup));
        tvCenterTitle.setTextColor(MyApplication.getInstance().mContext.getResources().getColor(R.color.white));
        rlBtnScope.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        str = etPassword.getText().toString().trim();
        if (str == null) {
            ToastUtil.showToast(this, "请先输入群名称");
            return;
        }
        if (str.equals("")) {
            ToastUtil.showToast(this, "请先输入群名称");
            return;
        }
        postGroupName(groupId,str);
    }

    private void postGroupName(int groupId, String str) {
        ChatCenterService service = RetrofitHandle.getInstance().retrofit.create(ChatCenterService.class);
        Call<PostComment> call=service.updateGroupName(new UpdateGroupNameParams(groupId,str));
        call.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if(response.body().getCode()==200){
                    Intent intent=new Intent();
                    intent.putExtra("groupUpdateName",str);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    @Override
    public void update(Subject subject) {
        SpConfig.getInstance().putBool(Constants.ISLOGIN, false);
    }
}
