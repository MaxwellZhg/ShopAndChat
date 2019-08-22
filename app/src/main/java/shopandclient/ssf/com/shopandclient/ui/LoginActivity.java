package shopandclient.ssf.com.shopandclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import shopandclient.ssf.com.shopandclient.base.Constants;
import shopandclient.ssf.com.shopandclient.base.MyApplication;
import shopandclient.ssf.com.shopandclient.entity.User;
import shopandclient.ssf.com.shopandclient.entity.UserLoginBean;
import shopandclient.ssf.com.shopandclient.net.RetrofitHandle;
import shopandclient.ssf.com.shopandclient.net.inter.BaseBiz;
import shopandclient.ssf.com.shopandclient.net.services.UserService;
import shopandclient.ssf.com.shopandclient.util.MD5Utils;
import shopandclient.ssf.com.shopandclient.util.SpConfig;
import shopandclient.ssf.com.shopandclient.util.ToastUtil;

/**
 * Created by zhg on 2019/5/27.
 */
public class LoginActivity extends BaseActivity implements BaseBiz, TextWatcher,View.OnClickListener {


    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_pass_tips)
    TextView tvPassTips;
    @BindView(R.id.rl_logo)
    RelativeLayout rlLogo;
    @BindView(R.id.rl_add_tips)
    TextView rlAddTips;
    @BindView(R.id.et_phone)
    EditText etPhone;
    Button btnLogin;
    private String mPhone;
    private String mPswd;

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    public void luancherLogin(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(MyApplication.getInstance().mContext, clazz);
        startActivity(intent);
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
        ButterKnife.bind(this);
        etPassword.addTextChangedListener(this);
        StatusBarUtil.setColor(this, MyApplication.getInstance().mContext.getResources().getColor(R.color.white), 0);
    }


    @OnClick({R.id.tv_register, R.id.tv_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                luancherLogin(RegisterActivity.class);
                break;
            case R.id.tv_forget:
                luancherLogin(ForgetActivity.class);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() < 6) {
            tvPassTips.setVisibility(View.VISIBLE);
        } else {
            tvPassTips.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        btnLogin=(Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mPhone = etPhone.getText().toString();
        mPswd = etPassword.getText().toString();
        switch (v.getId()){
            case R.id.btn_login:
                if (mPhone == null) {
                    ToastUtil.showToast(this, "请先填写账号，获取验证码");
                    return;
                }
                if (mPhone.equals("")) {
                    ToastUtil.showToast(this, "账号不能为空");
                    return;
                }
                if(mPhone.matches("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$")){

                }else{
                    ToastUtil.showToast(this, "输入的手机号格式不对");
                    return;
                }
                if (mPswd.equals("")) {
                    ToastUtil.showToast(this, "请输入密码");
                    return;
                }
                if (mPswd.matches("[0-9A-Za-z_]*")) {
                    //Log.e(TAG, " 账号是否通过？ --> " + mPswd.matches("[0-9A-Za-z_]*"));
                } else {
                    ToastUtil.showToast(this, "密码只能输入字母、数字、下划线");
                    return;
                }
                if (mPswd.length() < 6) {
                    ToastUtil.showToast(this, "密码至少为6位数");
                    return;
                }
                UserService userService = RetrofitHandle.getInstance().retrofit.create(UserService.class);
                Call<UserLoginBean> call = userService.postUserLogin(new User(etPhone.getText().toString().trim(),MD5Utils.getMd5Str(mPswd),2));
                call.enqueue(new Callback<UserLoginBean>() {
                    @Override
                    public void onResponse(Call<UserLoginBean> call, Response<UserLoginBean> response) {
                        if(response.body().getCode()==200) {
                            SpConfig.getInstance().putBool(Constants.ISLOGIN, true);
                            SpConfig.getInstance().putString(Constants.TOKEN,response.body().getData().getUserToken());
                            SpConfig.getInstance().putInt(Constants.USERID,response.body().getData().getUserID());
                            finish();
                        }else{
                            ToastUtil.showToast(mContext,response.body().getResult().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserLoginBean> call, Throwable t) {

                    }
                });
                break;
        }
    }
}
